package cn_rt.idsbase.netbean;

import android.content.Context;

import com.rt.mylibrary.http.RetrofitServiceManager;

import cn_rt.idsbase.netbean.ApiService;


/**
 * Created by ${zml} on 2017/5/10.
 */
public class BasePresenter {

    protected Context mcontext;
    protected ApiService mService;

    public BasePresenter(Context ctx) {
        mcontext = ctx;
        mService = RetrofitServiceManager.getInstance().create(ApiService.class);
    }
}
