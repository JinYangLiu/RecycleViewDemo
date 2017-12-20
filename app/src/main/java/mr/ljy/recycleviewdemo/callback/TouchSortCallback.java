package mr.ljy.recycleviewdemo.callback;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Mr.LJY on 2017/8/9.
 */

public class TouchSortCallback extends ItemTouchHelper.Callback {
    /**
     * item操作的回掉
     */
    private final OnItemTouchListener onItemTouchListener;

    public TouchSortCallback(OnItemTouchListener onItemTouchListener) {
        this.onItemTouchListener = onItemTouchListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int dragFlag = 0;
        int swipeFlag = 0;
        if (layoutManager instanceof GridLayoutManager) {
            dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP;
            swipeFlag = 0;// flag如果值是0，相当于这个功能被关闭
            return makeMovementFlags(dragFlag, swipeFlag);
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int orientation = linearLayoutManager.getOrientation();
            if (orientation == LinearLayoutManager.HORIZONTAL) {//如果是横向的
                dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                swipeFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            } else if (orientation == LinearLayoutManager.VERTICAL) {//如果是竖向的
                dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                swipeFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            }
            return makeMovementFlags(dragFlag, swipeFlag);
        }
        return 0;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (onItemTouchListener != null) {
            return onItemTouchListener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (onItemTouchListener != null) {
            onItemTouchListener.onSwiped(viewHolder.getAdapterPosition());
        }
    }




    public interface OnItemTouchListener {

        /**
         * 当某个item滑动删除时
         *
         * @param position
         */
        void onSwiped(int position);

        /**
         * 拖拽排序时
         *
         * @param currentPosition 当前拖拽的item
         * @param targetPosition  目的地item
         * @return
         */
        boolean onMove(int currentPosition, int targetPosition);

    }
}
