package com.ljy.loadmore;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Mr.LJY on 2017/8/18.
 */

public class LjySwipeFooter extends LinearLayout {

    private ImageView imageView;
    private ProgressDrawable progress;
    private LinearLayout mFooter;
    private LayoutInflater mLayoutInflater;

    public LjySwipeFooter(Context context) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
        mFooter = (LinearLayout) mLayoutInflater.inflate(R.layout.layout_swpie_footer, this, true);
        imageView = (ImageView) mFooter.findViewById(R.id.ivLoadMore);
        progress = new ProgressDrawable(context, this);
        setFooterProgressColor(R.color.theme_red);//设置进度条的颜色
        progress.setBackgroundColor(0xffffffff);
        imageView.setImageDrawable(progress);
        startProgress();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setCircleBackGround(int resId){
        imageView.setBackground(getResources().getDrawable(resId));
    }

    public void setCircleBackGroundNone(){
        imageView.setBackgroundColor(0x00ffffff);
    }

    public void startProgress() {
        progress.setAlpha(255);
        progress.setProgressRotation(1f);//设置旋转角度
        progress.setStartEndTrim(0.1f, 0.8f);//设置进度条的开始和结尾，也就是长度
        progress.start();
        setVisibility(View.VISIBLE);
    }

    public void stopProgress() {
        progress.stop();
        setVisibility(View.GONE);
    }

    public LjySwipeFooter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setFooterProgressColor( int...  colorResIds) {
        Resources res = this.getResources();
        int[] colorRes = new int[colorResIds.length];

        for(int i = 0; i < colorResIds.length; ++i) {
            colorRes[i] = res.getColor(colorResIds[i]);
        }
        progress.setColorSchemeColors(colorRes);
    }
}
