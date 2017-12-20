package mr.ljy.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.ljy.loadmore.LjyRecyclerView;
import com.ljy.loadmore.LjySwipeRefreshView;
import com.squareup.picasso.Picasso;

import java.util.List;

import mr.ljy.recycleviewdemo.R;
import mr.ljy.recycleviewdemo.adapter.LjyBaseAdapter;
import mr.ljy.recycleviewdemo.adapter.LjyViewHolder;
import mr.ljy.recycleviewdemo.bean.SwipeCardBean;

public class LjyRefreshActivity extends AppCompatActivity {

    private LjySwipeRefreshView mSwipeLayout;
    private RecyclerView recyclerView;
    private android.content.Context mContext=this;
    private LjyBaseAdapter<SwipeCardBean> mAdapter;
    private int pageCount=1;
    private int pageSize=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ljy_refresh);
        initView();
        initRecyclerData(SwipeCardBean.initData());
    }

    private void initView() {
        recyclerView = (LjyRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mSwipeLayout = (LjySwipeRefreshView) findViewById(R.id.content_view);
        mSwipeLayout.setColorSchemeResources(R.color.theme_red);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        mSwipeLayout.setOnLoadMoreListener(new LjySwipeRefreshView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMoreData();
            }
        });
    }

    private void initRecyclerData(final List listData) {
        recyclerView.setAdapter(mAdapter=new LjyBaseAdapter<SwipeCardBean>(mContext,listData , R.layout.layout_item_touchsort) {
            @Override
            public void convert(LjyViewHolder holder, SwipeCardBean item) {
                holder.setBackgroundColor(R.id.itemRoot, item.getCardColor());
                holder.setText(R.id.textViewSwipeCardName, item.getName());
                holder.setText(R.id.textViewSwipeCardPosition, item.getPosition() + "/" + listData.size());
                Picasso.with(mContext).load(item.getUrl()).into((ImageView) holder.getView(R.id.imageViewSwipeCardImg));
            }
        });
    }

    private void loadMoreData() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.addList(SwipeCardBean.initData(pageCount++*pageSize,pageCount*pageSize));
                mSwipeLayout.setLoadMoreSuccess();
            }
        }, 1000 * 2);
    }

    private void refreshData() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                pageCount=1;
                initRecyclerData(SwipeCardBean.initData(0,pageCount*pageSize));
                mSwipeLayout.setRefreshing(false);
            }
        }, 1000 * 2);
    }


}
