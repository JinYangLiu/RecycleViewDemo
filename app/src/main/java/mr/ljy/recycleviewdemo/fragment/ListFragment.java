package mr.ljy.recycleviewdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import mr.ljy.recycleviewdemo.R;
import mr.ljy.recycleviewdemo.adapter.LjyBaseAdapter;
import mr.ljy.recycleviewdemo.adapter.LjyViewHolder;
import mr.ljy.recycleviewdemo.bean.SwipeCardBean;
import mr.ljy.swipemenu.SwipeMenuRecyclerView;

/**
 * Created by Mr.LJY on 2017/12/18.
 */

public class ListFragment extends Fragment {
    private SwipeMenuRecyclerView recyclerView;
    private LjyBaseAdapter<SwipeCardBean> adapter;
    private Context mContext;
    private List<SwipeCardBean> mDatas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.list_fragment, container, false);
        initRecyclerView(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    private void initRecyclerView(View view) {
        recyclerView = (SwipeMenuRecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter = new LjyBaseAdapter<SwipeCardBean>(mContext, mDatas = SwipeCardBean.initData(), R.layout.layout_item_swipe_menu) {
            @Override
            public void convert(LjyViewHolder holder, final SwipeCardBean item) {
                holder.setBackgroundColor(R.id.itemRoot, item.getCardColor());
                holder.setText(R.id.textViewSwipeCardName, item.getName());
                holder.setText(R.id.textViewSwipeCardPosition, item.getPosition() + "/" + mDatas.size());
                holder.setOnClickListener(R.id.itemRoot, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext,"item content Click__"+item.getPosition(),Toast.LENGTH_SHORT).show();
                    }
                });
                holder.setOnClickListener(R.id.left_menu, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext,"Left Click__"+item.getPosition(),Toast.LENGTH_SHORT).show();
                    }
                });
                holder.setOnClickListener(R.id.right_menu, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext,"Right Click__"+item.getPosition(),Toast.LENGTH_SHORT).show();
                    }
                });
                Picasso.with(mContext).load(item.getUrl()).into((ImageView) holder.getView(R.id.imageViewSwipeCardImg));
            }
        });
    }
}