package mr.ljy.recycleviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mr.LJY on 2017/8/9.
 */

public abstract class LjyBaseAdapter<T> extends RecyclerView.Adapter<LjyViewHolder> {
    private Context mContext;
    public List<T> list;
    protected LayoutInflater mInflater;
    private int mItemLayoutId;

    public LjyBaseAdapter(Context context) {
        // TODO Auto-generated constructor stub
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mItemLayoutId = new LinearLayout(mContext).getId();
        this.list = new ArrayList<T>();

    }

    public LjyBaseAdapter(Context context, List<T> list) {
        // TODO Auto-generated constructor stub
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mItemLayoutId = new LinearLayout(mContext).getId();
        this.list = list;

    }

    public LjyBaseAdapter(Context context, List<T> list, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mItemLayoutId = itemLayoutId;
        this.list = list;

    }

    public LjyBaseAdapter(Context context, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mItemLayoutId = itemLayoutId;
        this.list = new ArrayList<T>();

    }

    public void setitemLayoutId(int itemLayoutId) {
        this.mItemLayoutId = itemLayoutId;
    }

    public List<T> getList() {
        return this.list;
    }

    public void appendList(List<T> list) {
        // TODO Auto-generated method stub
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void addList(List<T> list2) {
        // TODO Auto-generated method stub
        this.list.addAll((Collection<? extends T>) list2);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    boolean hasHeader = false;
    boolean hasFooter = false;
    View headerView;
    View footerView;

    public void setHeaderView(View headerView) {
        hasHeader=true;
        this.headerView = headerView;
    }

    public void setFooterView(View footerView) {
        hasFooter = true;
        this.footerView = footerView;
    }

    public View getHeaderView() {
        return headerView;
    }

    public View getFooterView() {
        return footerView;
    }

    @Override
    public void onBindViewHolder(LjyViewHolder holder, int position) {
        if (hasHeader && position == 0) {
            return;
        } else if (hasFooter && position == (list.size() + (hasHeader ? 1 : 0))) {
            return;
        } else
            convert(holder, (T) list.get(position));
    }

    @Override
    public LjyViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        if (hasHeader && position == 0) {
            return new LjyViewHolder(headerView);
        } else if (hasFooter && position == (list.size() + (hasHeader ? 1 : 0))) {
            return new LjyViewHolder(footerView);
        } else
            return LjyViewHolder.get(mContext, parent, mItemLayoutId, position);

    }
    //这里定义抽象方法，我们在匿名内部类实现的时候实现此方法来调用控件
    public abstract void convert(LjyViewHolder holder, T item);
}
