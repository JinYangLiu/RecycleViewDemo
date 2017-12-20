package mr.ljy.recycleviewdemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Mr.LJY on 2017/8/9.
 */

public class LjyViewHolder extends ViewHolder {

    private Context mContext;
    private View mConvertView;
    private SparseArray<View> mViews;

    public LjyViewHolder(View itemView) {
        super(itemView);
        mConvertView = itemView;
        this.mViews = new SparseArray<View>();
    }

    public static LjyViewHolder get(Context context, ViewGroup parent, int layoutId, int position) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new LjyViewHolder(view);
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {

        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符�?
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 设置背景颜色
     * @param viewId
     * @param color
     * @return
     */
    public ViewHolder setBackgroundColor(int viewId,int color){
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 设置点击事件
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setOnClickListener(int viewId,View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    public View getConvertView() {
        return mConvertView;
    }
}