package com.shaiing.code1229.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shaiing.code1229.R;
import com.shaiing.code1229.activity.PhotoAlbumActivity;
import com.shaiing.code1229.util.CommonUtil;

import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImageFragment extends Fragment implements SurfaceHolder.Callback, View.OnClickListener {
    private static final String TAG = "ImageFragment";

    private static final int PHOTO_WIDTH = 1224;
    private static final int PHOTO_HEIGHT = 1224;
    private static final int PHOTO_ANIMATION_DURATION = 800;

    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFile = CommonUtil.getOutputMediaFile(CommonUtil.MEDIA_TYPE_IMAGE);
            if (pictureFile == null) {
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                bos.write(data);
                bos.close();
                fos.close();
                Toast.makeText(getActivity(), pictureFile.toString(), Toast.LENGTH_SHORT).show();

                MediaScannerConnection.scanFile(
                        context,
                        new String[]{pictureFile.toString()},
                        null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.d("hlm", "Scanned " + path + ":");
                                Log.d("hlm", "-> uri=" + uri);
                            }
                        }
                );

                mCamera.startPreview();

                final RelativeLayout relativeLayout = new RelativeLayout(context);

                ImageView iv = new ImageView(context);
                iv.setAdjustViewBounds(true);
                Bitmap bitmap = BitmapFactory.decodeFile(pictureFile.getAbsolutePath());
                Matrix matrix = new Matrix();
                matrix.setRotate(90);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
                iv.setImageBitmap(bitmap);

                TextView tv = new TextView(context);
                tv.setText(getString(R.string.fa_close));
                tv.setTypeface(font);
                tv.setTextColor(getResources().getColor(R.color.fanqiehong));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                tv.setGravity(Gravity.CENTER);
                tv.setClickable(true);
                tv.setOnClickListener(tvOnClickListener);
                RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                rl_lp.width = _20dp;
                rl_lp.height = _20dp;
                rl_lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);

                relativeLayout.addView(iv);
                relativeLayout.addView(tv, rl_lp);

                mListTextViews.add(tv);
                mListRelativeLayouts.add(relativeLayout);
                mListPhotos.add(pictureFile.getAbsolutePath());

                LinearLayout.LayoutParams lp = null;
                for (int i = 0; i < mListRelativeLayouts.size(); i++) {
                    if (i != mListRelativeLayouts.size() - 1) {
                        lp = (LinearLayout.LayoutParams) mListRelativeLayouts.get(i).getLayoutParams();
                        if (lp == null) {
                            lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        }
                        lp.rightMargin = (int) _3dp;
                        mListRelativeLayouts.get(i).setLayoutParams(lp);
                    }
                }

                relativeLayout.setOnClickListener(rlOnClickListener);

                float thumbnailWidth = horizontalScrollView.getMeasuredHeight() - 2 * _3dp;
                int size = mListRelativeLayouts.size();
                if (screenWidth - 2 * _3dp < thumbnailWidth * size + (size - 1) * _3dp) {
                    final int s = (int) ((thumbnailWidth * size + (size - 1) * _3dp) - (screenWidth - 2 * _3dp));
                    Log.d("hlm", "s = " + s);
                    horizontalScrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            horizontalScrollView.smoothScrollTo(s, 0);
                        }
                    });
                }

                ll_photo_album.addView(relativeLayout);

                ObjectAnimator.ofFloat(relativeLayout, "alpha", 0, 1)
                        .setDuration(PHOTO_ANIMATION_DURATION)
                        .start();

//                ObjectAnimator.ofFloat(relativeLayout, "ScaleX", 2.0f, 1.0f)
//                        .setDuration(PHOTO_ANIMATION_DURATION)
//                        .start();

//                ObjectAnimator.ofFloat(relativeLayout, "ScaleY", 0.0f, 1.0f)
//                        .setDuration(PHOTO_ANIMATION_DURATION)
//                        .start();

//                ObjectAnimator.ofFloat(relativeLayout, "Rotation", 0, 360)
//                        .setDuration(PHOTO_ANIMATION_DURATION)
//                        .start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private Camera mCamera;
    private SurfaceView mPreview;
    private SurfaceHolder mHolder;
    private MediaRecorder mMediaRecorder;

    private TextView tv_camera_switch;
    private TextView tv_camera_mode_switch;
    private TextView tv_ic_flash;
    private TextView tv_next;

    private TextView tv_kacha;
    private TextView tv_video_recording;

    private LinearLayout ll_photo_album;
    private HorizontalScrollView horizontalScrollView;

    private View view;

    private boolean isFrontCamera = false;//是否是前置摄像头
    private boolean isPhotoMode = true;//是否是拍照模式
    private int screenWidth;

    private ArrayList<String> mListPhotos;
    private List<RelativeLayout> mListRelativeLayouts;
    private List<TextView> mListTextViews;

    private Context context;
    private Typeface font;

    private int _20dp;
    private float _3dp;

    private long ad_time, au_time;

    private View.OnClickListener rlOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int clicked = 0;
            for (int i = 0; i < mListRelativeLayouts.size(); i++) {
                if (v == mListRelativeLayouts.get(i)) {
                    clicked = i;
                    break;
                }
            }

            Intent intent = new Intent(context, PhotoAlbumActivity.class);
            intent.putStringArrayListExtra("mListPhotos", mListPhotos);
            intent.putExtra("currentIndex", clicked);
            startActivity(intent);
        }
    };

    private View.OnClickListener tvOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int currentIndex = 0;
            for (int i = 0; i < mListTextViews.size(); i++) {
                if (v == mListTextViews.get(i)) {
                    currentIndex = i;
                    break;
                }
            }

            ll_photo_album.removeViewAt(currentIndex);
            mListTextViews.remove(currentIndex);
            mListRelativeLayouts.remove(currentIndex);
            mListPhotos.remove(currentIndex);
        }
    };

    @Override
    public void onAttach(Context context) {
        Log.e("hlm", "onAttach()");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("hlm", "onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //获取context
        context = ImageFragment.this.getContext();

        //获取屏幕宽度
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        Log.d("hlm", "screenWidth = " + screenWidth);

        //
        _20dp = getResources().getDimensionPixelSize(R.dimen._20dp);
        _3dp = getResources().getDimension(R.dimen._3dp);

        Log.e("hlm", "onCreateView()");
        view = inflater.inflate(R.layout.fragment_image, container, false);

        //init view
        mPreview = (SurfaceView) view.findViewById(R.id.sv_preview);
        mHolder = mPreview.getHolder();
        mHolder.addCallback(this);

        tv_camera_switch = (TextView) view.findViewById(R.id.tv_camera_switch);
        tv_camera_mode_switch = (TextView) view.findViewById(R.id.tv_camera_mode_switch);
        tv_ic_flash = (TextView) view.findViewById(R.id.tv_ic_flash);
        tv_kacha = (TextView) view.findViewById(R.id.tv_kacha);
        tv_next = (TextView) view.findViewById(R.id.tv_next);

        ll_photo_album = (LinearLayout) view.findViewById(R.id.ll_photo_album);
        horizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.horizontalScrollView);

        tv_video_recording = (TextView) view.findViewById(R.id.tv_video_recording);
        //init data
        font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
        tv_camera_switch.setTypeface(font);
        tv_camera_mode_switch.setTypeface(font);
        tv_ic_flash.setTypeface(font);
        tv_next.setTypeface(font);
        tv_kacha.setTypeface(font);
        tv_video_recording.setTypeface(font);

        mListPhotos = new ArrayList<>();
        mListRelativeLayouts = new ArrayList<>();
        mListTextViews = new ArrayList<>();

        //init event
        mPreview.setOnClickListener(this);
        tv_camera_switch.setOnClickListener(this);
        tv_camera_mode_switch.setOnClickListener(this);
        tv_kacha.setOnClickListener(this);
        tv_next.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("hlm", "onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("hlm", "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("hlm", "onResume()");
        AsyncTask asyncTask = new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] params) {
                if (mCamera == null) {
                    mCamera = getCameraInstance();
                    if (mHolder != null) {
                        setStartPreview(mCamera, mHolder);
                    }
                }
                return null;
            }

        };

        asyncTask.execute();

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("hlm", "onPause()");
        releaseMediaRecorder();
        releaseCamera();

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("hlm", "onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("hlm", "onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("hlm", "onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("hlm", "onDetach()");
    }

    /**
     * 获取Camera实例
     */
    private Camera getCameraInstance() {
        int cameraId = isFrontCamera ? Camera.CameraInfo.CAMERA_FACING_FRONT : Camera.CameraInfo.CAMERA_FACING_BACK;
        Camera camera;
        try {
            camera = Camera.open(cameraId);
        } catch (Exception e) {
            camera = null;
            e.printStackTrace();
        }
        return camera;
    }

    /**
     * 开始实时预览
     */
    private void setStartPreview(Camera camera, SurfaceHolder holder) {
        try {
            if (camera != null) {
                camera.setPreviewDisplay(holder);
                camera.setDisplayOrientation(90);
                camera.startPreview();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

//    private boolean prepareVideoRecorder() {
//        if (mCamera == null) {
//            mCamera = getCameraInstance();
//        }
//
//        mMediaRecorder = new MediaRecorder();
//
//        // Step 1: Unlock and set camera to MediaRecorder
//        mCamera.unlock();
//        mMediaRecorder.setCamera(mCamera);
//
//        // Step 2: Set sources
//        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
//        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
//
//        // Step 3: Set a CamcorderProfile (requires API Level 8 or higher)
//        mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
//
//        // Step 4: Set output file
//        mMediaRecorder.setOutputFile(getOutputMediaFile(MEDIA_TYPE_VIDEO).toString());
//
//        // Step 5: Set the preview output
//        mMediaRecorder.setPreviewDisplay(mPreview.getHolder().getSurface());
//
//        // Step 6: Prepare configured MediaRecorder
//        try {
//            mMediaRecorder.prepare();
//        } catch (IllegalStateException e) {
//            releaseMediaRecorder();
//            return false;
//        } catch (IOException e) {
//            releaseMediaRecorder();
//            return false;
//        }
//        return true;
//    }

    private void releaseMediaRecorder() {
        if (mMediaRecorder != null) {
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
            mCamera.lock();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e("hlm", "surfaceCreated");
        setStartPreview(mCamera, mHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e("hlm", "surfaceChanged");
        if (mCamera != null) {
            mCamera.stopPreview();
        }
        setStartPreview(mCamera, mHolder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e("hlm", "surfaceDestroyed");
        releaseCamera();
    }

    /**
     * Create a file Uri for saving an image or video
     */
    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(CommonUtil.getOutputMediaFile(type));
    }

    /**
     * Create a File for saving an image or video
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_kacha:
                //拍照
                kacha();
                Log.d("hlm", "kacha");
                break;
            case R.id.sv_preview:
                //手动对焦
                mCamera.autoFocus(null);
                break;
            case R.id.tv_next:

                break;
            case R.id.tv_camera_mode_switch:
                isPhotoMode = !isPhotoMode;
                if (isPhotoMode) {
                    tv_kacha.setVisibility(View.VISIBLE);
                    tv_video_recording.setVisibility(View.GONE);
                    tv_camera_mode_switch.setText(getResources().getString(R.string.fa_camera_retro));
                } else {
                    tv_kacha.setVisibility(View.GONE);
                    tv_video_recording.setVisibility(View.VISIBLE);
                    tv_camera_mode_switch.setText(getResources().getString(R.string.fa_video_camera));
                }
                break;
            case R.id.tv_camera_switch:
                releaseCamera();
                isFrontCamera = !isFrontCamera;
                onResume();
                break;
        }
    }

    private void kacha() {
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPictureFormat(ImageFormat.JPEG);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        parameters.setPictureSize(PHOTO_WIDTH, PHOTO_HEIGHT);
        Camera.Size size = parameters.getPictureSize();
        Log.d("hlm", size.width + " x " + size.height);
        mCamera.setParameters(parameters);
        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    Log.d("hlm", "success");
                    mCamera.takePicture(null, null, mPictureCallback);
                }
            }
        });
    }
}
