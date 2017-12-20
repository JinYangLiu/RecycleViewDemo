package mr.ljy.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import mr.ljy.recycleviewdemo.R;
import mr.ljy.recycleviewdemo.adapter.LjyBaseAdapter;
import mr.ljy.recycleviewdemo.adapter.LjyViewHolder;
import mr.ljy.recycleviewdemo.bean.SwipeCardBean;
import mr.ljy.recycleviewdemo.callback.SwipeCardCallback;
import mr.ljy.recycleviewdemo.config.SwipeCardConfig;
import mr.ljy.recycleviewdemo.manager.SwipeCardLayoutManager;

public class SwipeCardActivity extends BaseActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_card);
        initRecyclerView();

    }

    private void initRecyclerView() {
        /**
         recycleview:
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         否则SwipeCardLayoutManager.onLayoutChildren中获取不到尺寸
         */


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new SwipeCardLayoutManager());
        recyclerView.setAdapter(adapter = new LjyBaseAdapter<SwipeCardBean>(mContext, mDatas = SwipeCardBean.initData(), R.layout.layout_item_swipe_card) {
            @Override
            public void convert(LjyViewHolder holder, SwipeCardBean item) {
                holder.setBackgroundColor(R.id.itemRoot,item.getCardColor());
                holder.setText(R.id.textViewSwipeCardName, item.getName());
                holder.setText(R.id.textViewSwipeCardPosition, item.getPosition()  + "/" + mDatas.size());
                Picasso.with(mContext).load(item.getUrl()).into((ImageView) holder.getView(R.id.imageViewSwipeCardImg));
            }
        });
        SwipeCardConfig.init(mContext);
        ItemTouchHelper helper = new ItemTouchHelper(new SwipeCardCallback(recyclerView, adapter, mDatas));
        helper.attachToRecyclerView(recyclerView);

    }
}
