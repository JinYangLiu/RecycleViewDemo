package mr.ljy.recycleviewdemo.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by LJY on 2016/9/12.
 */
public class L {
    /**
     * 闁哄嫷鍨伴幆浣割噜阌熶粙宕ラ惀妾坆ug
     */
    private static boolean isDebug =true;
    /**
     * 闁哄嫷鍨伴幆渚€寮伴崜褋浠汱og阎庤鐭紞锟?
     */
    private static boolean isShowLineNum = true;
    /**
     * 濮掓稒顭堥濠氭儍閸戠都g
     */
    private static String finalTag="LJY";


    /**
     * 闂佹宁鐟ㄩ锟?
     *
     * @param msg
     */
    public static void e( String msg) {
        e(null,msg);
    }
    private static void e(String tag, String msg) {
        if (isDebug()) {
            if (TextUtils.isEmpty(tag))
                tag=finalTag;
            if (isShowLineNum) {
                StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
                Log.e(tag, "(" + targetStackTraceElement.getFileName() + ":"
                        + targetStackTraceElement.getLineNumber() + ")");
            }
            Log.e(tag, "---->"+msg);
        }
    }


    /**
     * 濞ｅ洠鍓濇导锟?
     *
     * @param msg
     */
    public static void i( String msg) {
        i(null,msg);
    }
    public static void i(String tag, String msg) {
        if (isDebug()) {
            if (TextUtils.isEmpty(tag))
                tag=finalTag;
            if (isShowLineNum) {
                StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
                Log.i(tag, "(" + targetStackTraceElement.getFileName() + ":"
                        + targetStackTraceElement.getLineNumber() + ")");
            }
            Log.i(tag,"---->"+ msg);
        }
    }

    /**
     * 阎狅拷阌曪拷阉诧拷
     *
     * @param msg
     */
    public static void w( String msg) {
        w(null,msg);
    }
    private static void w(String tag, String msg) {
        if (isDebug()) {
            if (TextUtils.isEmpty(tag))
                tag=finalTag;
            if (isShowLineNum) {
                StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
                Log.w(tag, "(" + targetStackTraceElement.getFileName() + ":"
                        + targetStackTraceElement.getLineNumber() + ")");
            }
            Log.w(tag, "---->"+msg);
        }
    }




    private static StackTraceElement getTargetStackTraceElement() {
        // find the target invoked method
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isLogMethod = stackTraceElement.getClassName().equals(L.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }
	public static boolean isDebug() {
		return isDebug;
	}
	public static void setDebug(boolean isDebug) {
		L.isDebug = isDebug;
	}
}
