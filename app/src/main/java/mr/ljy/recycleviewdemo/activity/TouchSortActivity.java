package mr.ljy.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Collections;

import mr.ljy.recycleviewdemo.R;
import mr.ljy.recycleviewdemo.adapter.LjyBaseAdapter;
import mr.ljy.recycleviewdemo.adapter.LjyViewHolder;
import mr.ljy.recycleviewdemo.bean.SwipeCardBean;
import mr.ljy.recycleviewdemo.callback.TouchSortCallback;

public class TouchSortActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_card);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new LjyBaseAdapter<SwipeCardBean>(mContext, mDatas = SwipeCardBean.initData(), R.layout.layout_item_touchsort) {
            @Override
            public void convert(LjyViewHolder holder, SwipeCardBean item) {
                holder.setBackgroundColor(R.id.itemRoot, item.getCardColor());
                holder.setText(R.id.textViewSwipeCardName, item.getName());
                holder.setText(R.id.textViewSwipeCardPosition, item.getPosition() + "/" + mDatas.size());
                Picasso.with(mContext).load(item.getUrl()).into((ImageView) holder.getView(R.id.imageViewSwipeCardImg));
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(new TouchSortCallback(new TouchSortCallback.OnItemTouchListener() {
            @Override
            public void onSwiped(int position) {
                if (mDatas!=null){
                    adapter.notifyItemRemoved(position);
                    mDatas.remove(position);
                }
            }

            @Override
            public boolean onMove(int currentPosition, int targetPosition) {
                if (mDatas!=null){
                    //更新UI
                    adapter.notifyItemMoved(currentPosition,targetPosition);
                    //更换数据源中item的位置
                    Collections.swap(mDatas,currentPosition,targetPosition);
                }
                return false;
            }
        }));
        helper.attachToRecyclerView(recyclerView);
    }
}
