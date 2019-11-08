package com.example.mvp.common.view;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mvp.common.R;

public class TitleBar extends LinearLayout {
    private Context context;
    private TextView leftTv;
    private TextView rightTv;
    private TextView titleTv;
    private ImageView leftImg;
    private ImageView rightImg;
    private ITitleViewClickListener listener;

    public TitleBar(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public TitleBar(Context context,  AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.title_bar_layout, this);

        leftTv = findViewById(R.id.leftTv);
        leftImg = findViewById(R.id.leftImg);
        rightTv = findViewById(R.id.rightTv);
        rightImg = findViewById(R.id.rightImg);
        titleTv = findViewById(R.id.titleTv);

        leftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) {
                    listener.onLeftImgClick();
                }
            }
        });
        rightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) {
                    listener.onRightImgClick();
                }
            }
        });
        rightTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) {
                    listener.onRightTvClick();
                }
            }
        });

    }

    public void setTitleClickListener(ITitleViewClickListener listener) {
        this.listener = listener;
    }
    public void clearListener() {
        this.listener = null;
    }

    public void setLeftText(String text) {
        leftTv.setText(text);
    }
    public void setRightTvText(String text) {
        rightTv.setText(text);
    }
    public void setTitleText(String text) {
        titleTv.setText(text);
    }

    public void setLeftImage(@DrawableRes int imageId) {
        leftImg.setImageResource(imageId);
    }

    public void setRightImage(@DrawableRes int imageId) {
        rightImg.setImageResource(imageId);
    }

    public void showLeftTv() {
        leftTv.setVisibility(VISIBLE);
    }

    public void showRightImg() {
        rightImg.setVisibility(VISIBLE);
    }

    public interface ITitleViewClickListener {
        void onLeftImgClick();
        void onRightImgClick();
        void onRightTvClick();
    }
}
