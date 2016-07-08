package com.shaiing.code1229.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shaiing.code1229.R;
import com.shaiing.code1229.adapter.GalleryAdapter;
import com.shaiing.code1229.bean.FolderBean;
import com.shaiing.code1229.util.CommonUtil;
import com.shaiing.code1229.view.ListFolderPopupWindow;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GalleryActivity extends Activity implements View.OnClickListener {
    private GridView mGridView;
    private List<String> mImgs;
    private GalleryAdapter mAdapter;

    private RelativeLayout mRlBottom;
    private TextView mTvAngleLeft;
    private TextView mTvDirName;
    private TextView mTvDirCount;

    private File mCurrentDir;
    private int mMaxCount;

    private List<FolderBean> mFolderBeans = new ArrayList<>();

    private ProgressDialog mProgressDialog;
    private ListFolderPopupWindow mListFolderPopupWindow;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mProgressDialog.dismiss();
            data2View();

            initListFolderPopupWindow();
        }
    };

    private void initListFolderPopupWindow() {
        mListFolderPopupWindow = new ListFolderPopupWindow(this, mFolderBeans);
        mListFolderPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lightOn();
            }
        });
        mListFolderPopupWindow.setOnFolderSelectedListener(new ListFolderPopupWindow.OnFolderSelectedListener() {
            @Override
            public void onSelected(FolderBean folderBean) {
                mCurrentDir = new File(folderBean.getDir());
                mImgs = Arrays.asList(mCurrentDir.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String filename) {
                        return filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png");
                    }
                }));

                mAdapter = new GalleryAdapter(GalleryActivity.this, mImgs, mCurrentDir.getAbsolutePath());
                mGridView.setAdapter(mAdapter);

                mTvDirName.setText(folderBean.getName());
                mTvDirCount.setText(String.valueOf(mImgs.size()));

                mListFolderPopupWindow.dismiss();
            }
        });
    }

    private void data2View() {
        if (mCurrentDir == null) {
            Toast.makeText(GalleryActivity.this, "未扫描到任何图片", Toast.LENGTH_SHORT).show();
            return;
        }

        mImgs = Arrays.asList(mCurrentDir.list());
        mAdapter = new GalleryAdapter(this, mImgs, mCurrentDir.getAbsolutePath());
        mGridView.setAdapter(mAdapter);

        mTvDirName.setText(mCurrentDir.getName());
        mTvDirCount.setText(mMaxCount + "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        mTvAngleLeft.setOnClickListener(this);

        mRlBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListFolderPopupWindow.showAsDropDown(mRlBottom, 0, 0);
                lightOff();
            }
        });
    }

    private void lightOn() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1.0f;
        getWindow().setAttributes(lp);
    }

    private void lightOff() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = .3f;
        getWindow().setAttributes(lp);
    }

    private void initData() {
        CommonUtil.setFontAwesome(this, mTvAngleLeft);

        //扫描图片
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(GalleryActivity.this, "当前存储卡不可用！", Toast.LENGTH_SHORT).show();
            return;
        }

        mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

        new Thread() {
            @Override
            public void run() {
                Uri imgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver cr = GalleryActivity.this.getContentResolver();

                Cursor cursor = cr.query(
                        imgUri,
                        null,
                        MediaStore.Images.Media.MIME_TYPE + " = ? or " + MediaStore.Images.Media.MIME_TYPE + " = ? ",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED
                );

                Set<String> parentPaths = new HashSet<String>();
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        File parentFile = new File(path).getParentFile();
                        if (parentFile == null) continue;

                        String parentDir = parentFile.getAbsolutePath();
                        FolderBean folderBean = null;

                        if (parentPaths.contains(parentDir)) {
                            continue;
                        } else {
                            parentPaths.add(parentDir);
                            folderBean = new FolderBean();
                            folderBean.setDir(parentDir);
                            folderBean.setFirstImgPath(path);
                        }

                        if (parentFile.list() == null) {
                            continue;
                        }

                        int picCount = parentFile.list(new FilenameFilter() {
                            @Override
                            public boolean accept(File dir, String filename) {
                                return filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png");
                            }
                        }).length;

                        folderBean.setCount(picCount);
                        mFolderBeans.add(folderBean);

                        if (picCount > mMaxCount) {
                            mMaxCount = picCount;
                            mCurrentDir = parentFile;
                        }

                    }
                    cursor.close();
                    mHandler.sendEmptyMessage(0x110);
                }

            }
        }.start();


    }

    private void initView() {
        mGridView = (GridView) findViewById(R.id.gridView);
        mRlBottom = (RelativeLayout) findViewById(R.id.rl_bottom);
        mTvAngleLeft = (TextView) findViewById(R.id.tv_angle_left);
        mTvDirName = (TextView) findViewById(R.id.tv_dir_name);
        mTvDirCount = (TextView) findViewById(R.id.tv_dir_count);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_angle_left:
                finish();
                break;
        }
    }
}
