package com.ljy.loadmore;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;


/**
 * Created by Mr.LJY on 2016/11/8.
 */
public class LjyRecyclerView extends RecyclerView {

    public ArrayList<FixedViewInfo> mFooterViewInfos = new ArrayList<>();
    public static final int BASE_HEADER_VIEW_TYPE = -1 << 10;
    public static final int BASE_FOOTER_VIEW_TYPE = -1 << 11;

    public LjyRecyclerView(Context context) {
        this(context,null);
    }

    public LjyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LjyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addFooterView(View view) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);

        FixedViewInfo info = new FixedViewInfo();
        info.view = view;
        info.viewType = BASE_FOOTER_VIEW_TYPE + mFooterViewInfos.size();
        mFooterViewInfos.add(info);

        if (getAdapter() != null) {
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (!(adapter instanceof WrapperRecyclerViewAdapter))
            adapter  = new WrapperRecyclerViewAdapter(new ArrayList<FixedViewInfo>(), mFooterViewInfos, adapter);
        super.setAdapter(adapter);

    }

    public int getFooterViewsCount() {
        return mFooterViewInfos.size();
    }

    public class FixedViewInfo {
        public View view;
        public int viewType;
    }

}
