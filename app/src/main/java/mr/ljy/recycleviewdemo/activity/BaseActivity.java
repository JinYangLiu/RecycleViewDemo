package mr.ljy.recycleviewdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import mr.ljy.recycleviewdemo.adapter.LjyBaseAdapter;
import mr.ljy.recycleviewdemo.bean.SwipeCardBean;

/**
 * Created by Mr.LJY on 2017/8/16.
 */

public class BaseActivity extends Activity {
     RecyclerView recyclerView;
     LjyBaseAdapter<SwipeCardBean> adapter;
     Context mContext;
     List<SwipeCardBean> mDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
    }
}
