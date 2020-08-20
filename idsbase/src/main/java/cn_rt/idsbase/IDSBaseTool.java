package cn_rt.idsbase;

import android.app.Activity;
import android.content.Context;

import com.rt.mylibrary.http.RetrofitServiceManager;

import java.util.ArrayList;

public class IDSBaseTool {
    private static Context mContext;
    private static ArrayList<Activity> activities;

    public static void init(Context context, ArrayList<Activity> activitiys, String baseUrl, String basePort) {
        mContext = context;
        activities = activitiys;
        RetrofitServiceManager.init(Global.getFullUrl(baseUrl, basePort));
    }

    public static Context getAppContext() {
        if (mContext == null)
            throw new NullPointerException("application context is null,initialization in your application");
        return mContext;
    }

    public static ArrayList<Activity> getActivities() {
        return activities;
    }
}
