package mr.ljy.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import mr.ljy.recycleviewdemo.R;
import mr.ljy.recycleviewdemo.adapter.LjyBaseAdapter;
import mr.ljy.recycleviewdemo.adapter.LjyViewHolder;
import mr.ljy.recycleviewdemo.bean.SwipeCardBean;
import mr.ljy.swipemenu.SwipeMenuRecyclerView;

/**
 * 可通过xml中设置android:visibility="gone"，隐藏某一侧的菜单
 */
public class SwipMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_menu);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = (SwipeMenuRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
