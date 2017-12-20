package mr.ljy.recycleviewdemo.callback;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.List;

import mr.ljy.recycleviewdemo.adapter.LjyBaseAdapter;
import mr.ljy.recycleviewdemo.bean.SwipeCardBean;
import mr.ljy.recycleviewdemo.config.SwipeCardConfig;

/**
 * Created by Mr.LJY on 2017/8/9.
 */

public class SwipeCardCallback extends ItemTouchHelper.SimpleCallback{


    private final RecyclerView recyclerView;
    private final LjyBaseAdapter adapter;
    private final List<SwipeCardBean> mDatas;

    public SwipeCardCallback(RecyclerView recyclerView, LjyBaseAdapter adapter, List<SwipeCardBean> mDatas) {
        super(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT|ItemTouchHelper.DOWN|ItemTouchHelper.UP);
        this.recyclerView=recyclerView;
        this.adapter=adapter;
        this.mDatas=mDatas;

    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //在数据集合中真正的删除item的数据
        SwipeCardBean remove=mDatas.remove(viewHolder.getLayoutPosition());
        //为了循环效果，将删除的放到数据最下面
        mDatas.add(0,remove);
        adapter.notifyDataSetChanged();
    }

    /**
     * 监听child滑动
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        //执行动画
        //动画的临界点：与滑动的比例系数有关，执行动画的百分比
        double maxDistance=recyclerView.getWidth()*0.5f;//临界点长度
        double distance=Math.sqrt(dX*dX+dY*dY);//滑动的距离
        double fraction=distance/maxDistance;//
        if (fraction>1)
            fraction=1;
        int childCount=recyclerView.getChildCount();
        for (int position = 0; position < childCount; position++) {
            View child=recyclerView.getChildAt(position);
            int level=childCount-position-1;
            if (level>0){
                if (level< SwipeCardConfig.MAX_SHOW_COUNT-1){
                    child.setTranslationY((float) (SwipeCardConfig.TRANS_Y_GAP*level-fraction*SwipeCardConfig.TRANS_Y_GAP));
                    child.setScaleX((float) (1-SwipeCardConfig.SCALE_GAP*level+fraction*SwipeCardConfig.SCALE_GAP));
                    child.setScaleY((float) (1-SwipeCardConfig.SCALE_GAP*level+fraction*SwipeCardConfig.SCALE_GAP));
                }
            }

        }

    }
}



//        extends ItemTouchHelper.Callback {
//    @Override
//    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        return 0;
//    }
//
//    @Override
//    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//        return false;
//    }
//
//    @Override
//    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//
//    }
//}
