package mr.ljy.recycleviewdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mr.ljy.recycleviewdemo.R;
import mr.ljy.recycleviewdemo.utils.DeviceUtil;
import mr.ljy.recycleviewdemo.utils.L;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        L.i("deviceInfo:" + DeviceUtil.getDeviceInfo(this));
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_change_manager:
                startActivity(new Intent(MainActivity.this, LinearGridActivity.class));
                break;
            case R.id.btn_swipe_card:
                startActivity(new Intent(MainActivity.this, SwipeCardActivity.class));
                break;
            case R.id.btn_touch_sort:
                startActivity(new Intent(MainActivity.this, TouchSortActivity.class));
                break;
            case R.id.btn_swip_menu:
                startActivity(new Intent(MainActivity.this, SwipMenuActivity.class));
                break;
            case R.id.btn_refresh_loadmore:
                startActivity(new Intent(MainActivity.this, RefreshLoadActivity.class));
                break;
            case R.id.btn_add_head:
                startActivity(new Intent(MainActivity.this, AddHeadActivity.class));
                break;
            case R.id.btn_mvvm:
                startActivity(new Intent(MainActivity.this, MvvmActivity.class));
                break;
            case R.id.btn_hf:
                startActivity(new Intent(MainActivity.this, HeaderFooterActivity.class));
                break;
            case R.id.btn_window_image:
                startActivity(new Intent(MainActivity.this, WindowImageActivity.class));
                break;
            case R.id.btn_scollTitleBar:
                startActivity(new Intent(MainActivity.this, ScolleTitleBarActivity.class));
                break;
            case R.id.btn_ljyRefresh:
                startActivity(new Intent(MainActivity.this, LjyRefreshActivity.class));
                break;
            default:
                break;
        }
    }
}
