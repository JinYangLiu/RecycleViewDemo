package mr.ljy.recycleviewdemo.config;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Mr.LJY on 2017/8/9.
 */

public class SwipeCardConfig {

    public static int MAX_SHOW_COUNT;//屏幕最多显示item个数

    public static float SCALE_GAP;//每个item的缩放值

    public static int TRANS_Y_GAP;//每个item的位移值

    public static void init(Context context){
        MAX_SHOW_COUNT=4;
        SCALE_GAP=0.05f;
        TRANS_Y_GAP= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,15,context.getResources().getDisplayMetrics());
    }

}
