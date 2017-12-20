package mr.ljy.recycleviewdemo.mvp.interfaces;

import java.util.List;

import mr.ljy.recycleviewdemo.bean.SwipeCardBean;

/**
 * Created by Mr.LJY on 2017/9/8.
 */

public interface IPicModel {
    void loadPic(PicOnLoadListener listener);
    interface PicOnLoadListener{
        void onCall(List<SwipeCardBean> list);
    }
}
