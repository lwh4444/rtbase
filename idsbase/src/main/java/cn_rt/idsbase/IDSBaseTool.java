package cn_rt.idsbase;

import android.content.Context;

import com.rt.mylibrary.http.RetrofitServiceManager;

public class IDSBaseTool {
    private static Context mContext;

    public static void init(Context context,String baseUrl,String basePort) {
        mContext = context;
        RetrofitServiceManager.init(Global.getFullUrl(baseUrl, basePort));
    }

    public static Context getAppContext() {
        if (mContext == null)
            throw new NullPointerException("application context is null,initialization in your application");
        return mContext;
    }
}
