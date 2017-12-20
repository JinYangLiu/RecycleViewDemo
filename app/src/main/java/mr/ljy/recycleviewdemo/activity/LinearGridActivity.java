package mr.ljy.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import mr.ljy.recycleviewdemo.R;
import mr.ljy.recycleviewdemo.adapter.LjyBaseAdapter;
import mr.ljy.recycleviewdemo.adapter.LjyViewHolder;
import mr.ljy.recycleviewdemo.bean.SwipeCardBean;

public class LinearGridActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_card);
        findViewById(R.id.btn_change).setVisibility(View.VISIBLE);
        initRecyclerView(new LinearLayoutManager(mContext),R.layout.layout_item_touchsort);
    }
    private void initRecyclerView(RecyclerView.LayoutManager layoutManager, int layout) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter = new LjyBaseAdapter<SwipeCardBean>(mContext, mDatas = SwipeCardBean.initData(), layout) {
            @Override
            public void convert(LjyViewHolder holder, SwipeCardBean item) {
                holder.setBackgroundColor(R.id.itemRoot, item.getCardColor());
                holder.setText(R.id.textViewSwipeCardName, item.getName());
                holder.setText(R.id.textViewSwipeCardPosition, item.getPosition() + "/" + mDatas.size());
                Picasso.with(mContext).load(item.getUrl()).into((ImageView) holder.getView(R.id.imageViewSwipeCardImg));
            }
        });
    }
    private int count=0;
    public void click(View view) {
        switch (count%6){
            case 0:
                initRecyclerView(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false),R.layout.layout_item_swipe_card);
                count++;
                break;
            case 1:
                initRecyclerView(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,true),R.layout.layout_item_swipe_card);
                count++;
                break;
            case 2:
                initRecyclerView(new GridLayoutManager(mContext,2),R.layout.layout_item_swipe_card);
                count++;
                break;
            case 3:
                initRecyclerView(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false),R.layout.layout_item_touchsort);
                count++;
            case 4:
                initRecyclerView(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,true),R.layout.layout_item_touchsort);
                count++;
                break;
            case 5:
                initRecyclerView(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL),R.layout.layout_item_staggered);
                count++;
                break;
        }
    }
}
