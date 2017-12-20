package mr.ljy.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.squareup.picasso.Picasso;

import mr.ljy.recycleviewdemo.R;
import mr.ljy.recycleviewdemo.adapter.LjyBaseAdapter;
import mr.ljy.recycleviewdemo.adapter.LjyViewHolder;
import mr.ljy.recycleviewdemo.bean.SwipeCardBean;
import mr.ljy.refreshmore.recycleviews.MyItemDecoration;

public class HeaderFooterActivity extends BaseActivity {


    private SwipeToLoadLayout swipeToLoadLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_footer);
        initRecyclerView();
    }
    private void initRecyclerView() {
        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.init();
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMoreData();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.swipe_target);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyItemDecoration(mContext, MyItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter = new LjyBaseAdapter<SwipeCardBean>(mContext, mDatas = SwipeCardBean.initData(0, pageSize), R.layout.layout_item_touchsort) {
            @Override
            public void convert(LjyViewHolder holder, SwipeCardBean item) {
                holder.setBackgroundColor(R.id.itemRoot, item.getCardColor());
                holder.setText(R.id.textViewSwipeCardName, item.getName());
                holder.setText(R.id.textViewSwipeCardPosition, item.getPosition() + "/" + mDatas.size());
                Picasso.with(mContext).load(item.getUrl()).into((ImageView) holder.getView(R.id.imageViewSwipeCardImg));
            }
        });

    }

    private int pageCount=1;
    private int pageSize=5;
    private void loadMoreData() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.list = SwipeCardBean.initData(0,++pageCount*pageSize);
                adapter.notifyDataSetChanged();
                swipeToLoadLayout.setLoadingMore(false);
            }
        }, 1000 * 2);
    }

    private void refreshData() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.list = SwipeCardBean.initData(0,pageSize);
                adapter.notifyDataSetChanged();
                swipeToLoadLayout.setRefreshing(false);
            }
        }, 1000 * 2);
    }
}
