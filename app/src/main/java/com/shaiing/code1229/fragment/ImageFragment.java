package com.shaiing.code1229.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shaiing.code1229.R;
import com.shaiing.code1229.activity.PhotoAlbumActivity;

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

    public static final int MEDIA_TYPE_IMAGE = 0;
    public static final int MEDIA_TYPE_VIDEO = 1;
    private static final int PHOTO_WIDTH = 1224;
    private static final int PHOTO_HEIGHT = 1224;
    private static final int PHOTO_ANIMATION_DURATION = 600;

    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
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
                tv.setText(getString(R.string.ic_close));
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
                        lp.rightMargin = _3dp;
                        mListRelativeLayouts.get(i).setLayoutParams(lp);
                    }
                }

                relativeLayout.setOnClickListener(rlOnClickListener);

                ll_photo_album.addView(relativeLayout);

                ObjectAnimator.ofFloat(relativeLayout, "alpha", 0, 1)
                        .setDuration(PHOTO_ANIMATION_DURATION)
                        .start();

                ObjectAnimator.ofFloat(relativeLayout, "ScaleX", 0.1f, 1.0f)
                        .setDuration(PHOTO_ANIMATION_DURATION)
                        .start();

                ObjectAnimator.ofFloat(relativeLayout, "ScaleY", 0.1f, 1.0f)
                        .setDuration(PHOTO_ANIMATION_DURATION)
                        .start();

                ObjectAnimator.ofFloat(relativeLayout, "Rotation", 0, 360)
                        .setDuration(PHOTO_ANIMATION_DURATION)
                        .start();
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

    private Button btn_kacha;
    private Button btn_video_recording;

    private ProgressBar mProgressBar;

    private LinearLayout ll_photo_album;
    private HorizontalScrollView horizontalScrollView;

    private View view;

    private boolean isFrontCamera = false;//是否是前置摄像头
    private boolean isPhotoMode = true;//是否是拍照模式

    private ArrayList<String> mListPhotos;
    private List<RelativeLayout> mListRelativeLayouts;
    private List<TextView> mListTextViews;

    private int screenWidth;
    private Context context;
    private Typeface font;

    private int _20dp;
    private int _3dp;

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

        //
        _20dp = getResources().getDimensionPixelSize(R.dimen._20dp);
        _3dp = getResources().getDimensionPixelSize(R.dimen._3dp);

        //获取屏幕width
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();

        Log.e("hlm", "onCreateView()");
        view = inflater.inflate(R.layout.fragment_image, container, false);

        //init view
        mPreview = (SurfaceView) view.findViewById(R.id.sv_preview);
        mHolder = mPreview.getHolder();
        mHolder.addCallback(this);

        tv_camera_switch = (TextView) view.findViewById(R.id.tv_camera_switch);
        tv_camera_mode_switch = (TextView) view.findViewById(R.id.tv_camera_mode_switch);
        tv_ic_flash = (TextView) view.findViewById(R.id.tv_ic_flash);
        btn_kacha = (Button) view.findViewById(R.id.btn_kacha);
        tv_next = (TextView) view.findViewById(R.id.tv_next);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        ll_photo_album = (LinearLayout) view.findViewById(R.id.ll_photo_album);
        horizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.horizontalScrollView);

        btn_video_recording = (Button) view.findViewById(R.id.btn_video_recording);
        //init data
        font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
        tv_camera_switch.setTypeface(font);
        tv_camera_mode_switch.setTypeface(font);
        tv_ic_flash.setTypeface(font);
        tv_next.setTypeface(font);

        mListPhotos = new ArrayList<>();
        mListRelativeLayouts = new ArrayList<>();
        mListTextViews = new ArrayList<>();

        //init event
        mPreview.setOnClickListener(this);
//        tv_camera_switch.setOnClickListener(this);
        tv_camera_mode_switch.setOnClickListener(this);
        btn_kacha.setOnClickListener(this);
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
        new Thread() {
            @Override
            public void run() {
                if (mCamera == null) {
                    mCamera = getCameraInstance();
                    if (mHolder != null) {
                        setStartPreview(mCamera, mHolder);
                    }
                }
            }
        }.start();

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
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * Create a File for saving an image or video
     */
    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFile(int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Shaiing");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("Shaiing", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_kacha:
                //拍照
                kacha();
                break;
            case R.id.sv_preview:
                mCamera.autoFocus(null);
                break;
            case R.id.tv_next:

                break;
            case R.id.tv_camera_mode_switch:
                isPhotoMode = !isPhotoMode;
                if (isPhotoMode) {
                    btn_kacha.setVisibility(View.VISIBLE);
                    mProgressBar.setVisibility(View.GONE);
                    btn_video_recording.setVisibility(View.GONE);
                    tv_camera_mode_switch.setText(getResources().getString(R.string.ic_camera));
                } else {
                    btn_kacha.setVisibility(View.GONE);
                    mProgressBar.setVisibility(View.VISIBLE);
                    btn_video_recording.setVisibility(View.VISIBLE);
                    tv_camera_mode_switch.setText(getResources().getString(R.string.ic_video));
                }
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
                    mCamera.takePicture(null, null, mPictureCallback);
                }
            }
        });
    }
}
