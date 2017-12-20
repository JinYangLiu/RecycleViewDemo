package mr.ljy.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import mr.ljy.recycleviewdemo.R;
import mr.ljy.recycleviewdemo.adapter.LjyBaseAdapter;
import mr.ljy.recycleviewdemo.adapter.LjyViewHolder;
import mr.ljy.recycleviewdemo.bean.SwipeCardBean;
import mr.ljy.refreshmore.recycleviews.LjyRecyclerView;
import mr.ljy.refreshmore.recycleviews.MyItemDecoration;

public class RefreshLoadActivity extends BaseActivity {
    private LjyRecyclerView recyclerViewL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_loadmore);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerViewL = (LjyRecyclerView) findViewById(R.id.recyclerView);
        recyclerViewL.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewL.setItemAnimator(new DefaultItemAnimator());
        recyclerViewL.addItemDecoration(new MyItemDecoration(mContext, MyItemDecoration.VERTICAL_LIST));
        recyclerViewL.setAdapter(adapter = new LjyBaseAdapter<SwipeCardBean>(mContext, mDatas = SwipeCardBean.initData(0, pageSize), R.layout.layout_item_touchsort) {
            @Override
            public void convert(LjyViewHolder holder, SwipeCardBean item) {
                holder.setBackgroundColor(R.id.itemRoot, item.getCardColor());
                holder.setText(R.id.textViewSwipeCardName, item.getName());
                holder.setText(R.id.textViewSwipeCardPosition, item.getPosition() + "/" + mDatas.size());
                Picasso.with(mContext).load(item.getUrl()).into((ImageView) holder.getView(R.id.imageViewSwipeCardImg));
            }
        });
        recyclerViewL.setPullToRefreshListener(new LjyRecyclerView.PullToRefreshListener() {
            @Override
            public void onRefreshing() {
                refreshData();
            }
        });
        recyclerViewL.setLoadMoreListener(new LjyRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMoreData();
            }
        });
    }

    private int pageCount=1;
    private int pageSize=5;
    private void loadMoreData() {
        recyclerViewL.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.list = SwipeCardBean.initData(0,++pageCount*pageSize);
                adapter.notifyDataSetChanged();
                recyclerViewL.setLoadMoreComplete();
            }
        }, 1000 * 2);
    }

    private void refreshData() {
        recyclerViewL.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.list = SwipeCardBean.initData(0,pageSize);
                adapter.notifyDataSetChanged();
                recyclerViewL.setRefreshComplete();
            }
        }, 1000 * 2);
    }
}
