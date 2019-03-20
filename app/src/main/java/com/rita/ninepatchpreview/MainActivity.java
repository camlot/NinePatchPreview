package com.rita.ninepatchpreview;

import android.content.Context;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Context mContext;

    private int mScreenHeight;
    private int mScreenWidth;

    private String mImagePath;

    private ImageView mNinePatchIv;
    private TextView mWidthTv;
    private TextView mHeightTv;
    private MySeekBar mWidthSeekBar;
    private MySeekBar mHeightSeekBar;
    private EditText mImageNameEt;
    private ImageView mAddImageIv;

    private NinePatch mNinePatch;
    private NinePatchDrawable mNinePatchDrawable = null;
    private ViewGroup.LayoutParams mNinePatchIvLayoutParams;
    private int mCurNinePatchWidth;
    private int mCurNinePatchHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
    }

    private void initView() {

        DisplayMetrics dm = getResources().getDisplayMetrics();
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
        Log.d(TAG, "mScreenWidth=" + mScreenWidth + " mScreenHeight=" + mScreenHeight);

        mNinePatchIv = findViewById(R.id.main_iv_nine_patch_image);

        mImageNameEt = findViewById(R.id.main_et_image_name);
        mAddImageIv = findViewById(R.id.main_iv_add_image);
        mWidthTv = findViewById(R.id.main_tv_image_width_value);
        mHeightTv = findViewById(R.id.main_tv_image_height_value);
        mWidthSeekBar = findViewById(R.id.main_seekbar_set_width);
        mHeightSeekBar = findViewById(R.id.main_seekbar_set_height);

        mWidthSeekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Log.d(TAG, "onTouch event:" + event);
                if (mNinePatchDrawable == null) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Toast.makeText(mContext, R.string.choose_image_hint, Toast.LENGTH_SHORT).show();
                    }
                    return true;
                } else if (mNinePatch.getWidth() >= mScreenWidth) {
                    Toast.makeText(mContext, R.string.image_full_screen_hint, Toast.LENGTH_SHORT).show();
                }
                return false;
            }

        });

        mHeightSeekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Log.d(TAG, "onTouch event:" + event);
                if (mNinePatchDrawable == null) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Toast.makeText(mContext, R.string.choose_image_hint, Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }

        });

        mWidthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "mWidthSeekBar progress:" + progress);
                int originWidth = mNinePatch.getWidth();
                mCurNinePatchWidth = originWidth + (mScreenWidth - originWidth) * progress / 100;
                mWidthTv.setText(String.valueOf(mCurNinePatchWidth));
                scaleWidth(mCurNinePatchWidth);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                int startProgress = seekBar.getProgress();
                Log.d(TAG, "mWidthSeekBar onStartTrackingTouch " + startProgress);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "mWidthSeekBar onStopTrackingTouch " + seekBar.getProgress());

            }
        });

        mHeightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "mHeightSeekBar progress:" + progress);
                int originHeight = mNinePatch.getHeight();
                mCurNinePatchHeight = originHeight + (mScreenHeight - originHeight) * progress / 100;
                mHeightTv.setText(String.valueOf(mCurNinePatchHeight));
                scaleHeight(mCurNinePatchHeight);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                int startProgress = seekBar.getProgress();
                Log.d(TAG, "mHeightSeekBar onStartTrackingTouch " + startProgress);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "mHeightSeekBar onStopTrackingTouch " + seekBar.getProgress());

            }
        });

        mImageNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged:" + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged:" + s);

            }

            @Override
            public void afterTextChanged(Editable s) {
                mImagePath = FileUtil.getNinePatchRootPath() + s + ".png";
                Log.d(TAG, "afterTextChanged - " + mImagePath);
                boolean isExist = FileUtil.isFileExists(mImagePath);
                Log.d(TAG, "afterTextChanged - isExist:" + isExist);
                if (isExist) {
                    mAddImageIv.setVisibility(View.VISIBLE);
                } else {
                    mAddImageIv.setVisibility(View.INVISIBLE);
                }
            }
        });

        mAddImageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage(mImagePath);
                mImageNameEt.setText(null);
            }
        });

    }

    private void loadImage(String imagePath) {
        clearSeekBarProgress();
        mNinePatch = NinePatchUtil.LoadNinePatchImageFromLocal(getResources(), imagePath);
        if (mNinePatch == null) {
            Log.d(TAG, "mNinePatchDrawable = null" + " picDir:" + imagePath);
        } else {
            mNinePatchDrawable = mNinePatch.getNinePatchDrawable();
            mNinePatchIv.setBackground(mNinePatchDrawable);
            int originWidth = mNinePatch.getWidth();
            int originHeight = mNinePatch.getHeight();
            mNinePatchIvLayoutParams = mNinePatchIv.getLayoutParams();
            mNinePatchIvLayoutParams.width = originWidth;
            mNinePatchIvLayoutParams.height = originHeight;
            mNinePatchIv.setLayoutParams(mNinePatchIvLayoutParams);
            mWidthTv.setText(String.valueOf(originWidth));
            mHeightTv.setText(String.valueOf(originHeight));
        }
    }

    private void clearSeekBarProgress() {
        mWidthSeekBar.setProgress(0);
        mHeightSeekBar.setProgress(0);
        mWidthTv.setText(String.valueOf(0));
        mHeightTv.setText(String.valueOf(0));
    }

    private void scaleWidth(int width) {
        mNinePatchIvLayoutParams.width = width;
        mNinePatchIv.setLayoutParams(mNinePatchIvLayoutParams);
    }

    private void scaleHeight(int height) {
        mNinePatchIvLayoutParams.height = height;
        mNinePatchIv.setLayoutParams(mNinePatchIvLayoutParams);
    }

}
