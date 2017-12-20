package mr.ljy.recycleviewdemo.activity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import mr.ljy.recycleviewdemo.R;
import mr.ljy.recycleviewdemo.windowimageview.WindowImageView;

public class WindowImageActivity extends AppCompatActivity {

    RecyclerView rv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_image);
        rv_content = (RecyclerView) findViewById(R.id.rv_content);
        rv_content.setLayoutManager(new LinearLayoutManager(this));
        rv_content.setAdapter(new MyAdapter());

    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {


        @Override
        public int getItemViewType(int position) {
            if (position == 3) {
                return 1;
            }
            if (position == 13) {
                return 1;
            }
            return 0;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            if (viewType == 1) {
                view = View.inflate(WindowImageActivity.this, R.layout.item1, null);
            } else {
                view = View.inflate(WindowImageActivity.this, R.layout.item0, null);
                RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                view.setLayoutParams(lp);
            }
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            if (position == 3) {
                holder.windowImageView.bindRecyclerView(rv_content);
                holder.windowImageView.setFrescoEnable(true);
//                holder.windowImageView.setImageURI(Uri.parse("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D51%2C0%2C1816%2C1199%3Bc0%3Dbaike220%2C5%2C5%2C220%2C73/sign=85adb8780023dd54353cfd28ec3c82f2/bf096b63f6246b60428d7668e2f81a4c510fa22f.jpg"));
                holder.windowImageView.setImageResource(R.drawable.bg2);
            } else if (position == 13) {
                holder.windowImageView.bindRecyclerView(rv_content);
                holder.windowImageView.setFrescoEnable(true);
                holder.windowImageView.setImageURI(Uri.parse("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=f7e2398fd300baa1ae214fe92679d277/10dfa9ec8a1363270a0f3650938fa0ec09fac7f7.jpg"));
            } else {
                holder.itemView.setBackgroundColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
            }
        }

        @Override
        public int getItemCount() {
            return 30;
        }

        class MyHolder extends RecyclerView.ViewHolder {

            WindowImageView windowImageView;

            public MyHolder(View itemView) {
                super(itemView);
                windowImageView = (WindowImageView) itemView.findViewById(R.id.wiv);
            }
        }
    }

}
