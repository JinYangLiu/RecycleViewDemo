package mr.ljy.recycleviewdemo.mvp.impl;

import mr.ljy.recycleviewdemo.bean.SwipeCardBean;
import mr.ljy.recycleviewdemo.mvp.interfaces.IPicModel;

/**
 * Created by Mr.LJY on 2017/9/8.
 */

public class PicModelImpl implements IPicModel {
    @Override
    public void loadPic(PicOnLoadListener listener) {
        listener.onCall(SwipeCardBean.initData());
    }
}
