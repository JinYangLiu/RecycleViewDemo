package com.ljy.loadmore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Mr.LJY on 2017/8/22.
 */

public class LoadingViewBbs extends RelativeLayout {
    private LayoutInflater mLayoutInflater;
    private RelativeLayout mRLayout;
    Context mContext;

    public LoadingViewBbs(Context context) {
        this(context,null);
    }


    public LoadingViewBbs(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mRLayout = (RelativeLayout) mLayoutInflater.inflate(
                R.layout.loadingview_bbs, LoadingViewBbs.this, true);
        ImageView imageView= (ImageView) mRLayout.findViewById(R.id.imageView);
        ProgressDrawable progress = new ProgressDrawable(context, this);
        progress.setColorSchemeColors(getResources().getColor(R.color.theme_red));//设置进度条的颜色
        progress.setBackgroundColor(0xffffffff);
        progress.setAlpha(255);
        progress.setProgressRotation(1f);//设置旋转角度
        progress.setStartEndTrim(0.1f, 0.8f);//设置进度条的开始和结尾，也就是长度
        progress.start();
        imageView.setImageDrawable(progress);
    }




}