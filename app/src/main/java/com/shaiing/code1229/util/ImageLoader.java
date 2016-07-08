package com.shaiing.code1229.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by mike on 2016/6/21.
 */
public class ImageLoader {
    private static ImageLoader mInstance;

    private LruCache<String, Bitmap> mLruCache;

    private ExecutorService mThreadPool;
    private static final int DEFAULT_THREAD_COUNT = 1;

    private Type mType = Type.LIFO;

    private LinkedList<Runnable> mTaskQueue;

    private Thread mPoolThread;
    private Handler mPoolThreadHandler;

    private Handler mUIHandler;

    private Semaphore mSemaphorePoolThreadHandler = new Semaphore(0);
    private Semaphore mSemaphoreThreadPool;

    public enum Type {
        FIFO, LIFO
    }

    private ImageLoader(int threadCount, Type type) {
        init(threadCount, type);
    }

    private void init(int threadCount, Type type) {
        mPoolThread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                mPoolThreadHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        mThreadPool.execute(getTask());
                        try {
                            mSemaphoreThreadPool.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                mSemaphorePoolThreadHandler.release();
                Looper.loop();
            }
        };

        mPoolThread.start();

        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMemory = maxMemory / 8;
        mLruCache = new LruCache<String, Bitmap>(cacheMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };

        mThreadPool = Executors.newFixedThreadPool(threadCount);
        mTaskQueue = new LinkedList<>();
        mType = type;

        mSemaphoreThreadPool = new Semaphore(threadCount);
    }

    private Runnable getTask() {
        if (mType == Type.FIFO) {
            return mTaskQueue.removeFirst();
        } else if (mType == Type.LIFO) {
            return mTaskQueue.removeLast();
        }

        return null;
    }

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(DEFAULT_THREAD_COUNT, Type.LIFO);
                }
            }
        }

        return mInstance;
    }

    public static ImageLoader getInstance(int threadCount, Type type) {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(threadCount, type);
                }
            }
        }

        return mInstance;
    }

    public void loadImage(final String path, final ImageView imageView) {
        imageView.setTag(path);
        mUIHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                ImgBeanHolder holder = (ImgBeanHolder) msg.obj;
                Bitmap bm = holder.bitmap;
                ImageView imageView = holder.imageView;
                String path = holder.path;

                if (imageView.getTag().toString().equals(path)) {
                    imageView.setImageBitmap(bm);
                }
            }
        };

        Bitmap bm = getBitmapFromLruCache(path);

        if (bm != null) {
            refreshBitmap(bm, imageView, path);
        } else {
            addTask(new Runnable() {
                @Override
                public void run() {
                    //加载图片，压缩图片
                    ImageSize imageSize = getImageViewSize(imageView);
                    Bitmap bm = decodeSampledBitmapFromPath(path, imageSize.width, imageSize.height);
                    //加入缓存
                    addBitmapToLruCache(path, bm);

                    refreshBitmap(bm, imageView, path);
                    mSemaphoreThreadPool.release();
                }
            });
        }
    }

    private void refreshBitmap(Bitmap bm, ImageView imageView, String path) {
        Message msg = Message.obtain();
        ImgBeanHolder holder = new ImgBeanHolder();
        holder.bitmap = bm;
        holder.imageView = imageView;
        holder.path = path;
        msg.obj = holder;
        mUIHandler.sendMessage(msg);
    }

    private void addBitmapToLruCache(String path, Bitmap bm) {
        if (getBitmapFromLruCache(path) == null) {
            if (bm != null) {
                mLruCache.put(path, bm);
            }
        }
    }

    private Bitmap decodeSampledBitmapFromPath(String path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        int inSampleSize = calculateInSampleSize(options, width, height);
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;

        int inSampleSize = 1;
        if (width > reqWidth || height > reqHeight) {
            int widthRadio = Math.round(width * 1.0f / reqWidth);
            int heightRadio = Math.round(height * 1.0f / reqHeight);
            inSampleSize = Math.max(widthRadio, heightRadio);
        }
        return inSampleSize;
    }

    private ImageSize getImageViewSize(ImageView imageView) {
        ImageSize imageSize = new ImageSize();
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();

        DisplayMetrics displayMetrics = imageView.getContext().getResources().getDisplayMetrics();
        int width = imageView.getWidth();
        if (width <= 0) {
            width = lp.width;
        }

        if (width <= 0) {
            width = imageView.getMaxWidth();
        }

        if (width <= 0) {
            width = displayMetrics.widthPixels;
        }

        int height = imageView.getHeight();
        if (height <= 0) {
            height = lp.height;
        }

        if (height <= 0) {
            height = imageView.getMaxHeight();
        }

        if (height <= 0) {
            height = displayMetrics.heightPixels;
        }

        imageSize.width = width;
        imageSize.height = height;
        return imageSize;
    }

    private synchronized void addTask(Runnable runnable) {
        mTaskQueue.add(runnable);
        if (mPoolThreadHandler == null) {
            try {
                mSemaphorePoolThreadHandler.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mPoolThreadHandler.sendEmptyMessage(0x110);
    }

    private Bitmap getBitmapFromLruCache(String path) {
        return mLruCache.get(path);
    }

    private class ImgBeanHolder {
        Bitmap bitmap;
        ImageView imageView;
        String path;
    }

    private class ImageSize {
        int width;
        int height;
    }
}
