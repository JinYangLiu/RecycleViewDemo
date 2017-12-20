package mr.ljy.recycleviewdemo.manager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import mr.ljy.recycleviewdemo.config.SwipeCardConfig;

/**
 * Created by Mr.LJY on 2017/8/9.
 */

public class SwipeCardLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);

        //1.在布局layout前，将所有子view先全部都detach，然后放入scrap缓存集合中缓存
        detachAndScrapAttachedViews(recycler);
        //2.只将上面的max_show_count个view添加到recyclerView容器中
        int itemCount = getItemCount();//item总数
        int bottomPosition;//初始化显示的最后一个item的角标
        if (itemCount < 1)
            return;
        if (itemCount < SwipeCardConfig.MAX_SHOW_COUNT)
            bottomPosition = 0;
        else
            bottomPosition = itemCount - SwipeCardConfig.MAX_SHOW_COUNT;

        for (int position = bottomPosition; position < itemCount; position++) {
            View view = recycler.getViewForPosition(position);
            addView(view);
            //1.首先是测量
            measureChildWithMargins(view, 0, 0);
            //2.下面是摆放view
            int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
            int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);

            layoutDecorated(
                    view,
                    widthSpace / 2,
                    heightSpace / 2,
                    widthSpace / 2 + getDecoratedMeasuredWidth(view),
                    heightSpace/2 + getDecoratedMeasuredHeight(view)
            );
           // 进行缩放和位移
            int level = itemCount - position - 1;
            if (level > 0) {
                if (level < SwipeCardConfig.MAX_SHOW_COUNT - 1) {
                    //偏移
                    view.setTranslationY(SwipeCardConfig.TRANS_Y_GAP * level);
                    //缩放
                    view.setScaleX(1 - SwipeCardConfig.SCALE_GAP * level);
                    view.setScaleY(1 - SwipeCardConfig.SCALE_GAP * level);
                } else {//最后两个重叠
                    view.setTranslationY(SwipeCardConfig.TRANS_Y_GAP * (level - 1));
                    view.setScaleX(1 - SwipeCardConfig.SCALE_GAP * (level - 1));
                    view.setScaleY(1 - SwipeCardConfig.SCALE_GAP * (level - 1));
                }
            }
        }

    }
}
