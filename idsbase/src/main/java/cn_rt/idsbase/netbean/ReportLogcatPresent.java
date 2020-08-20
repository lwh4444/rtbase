package cn_rt.idsbase.netbean;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * Created by ${zml} on 2019/6/27.
 */
public class ReportLogcatPresent extends BasePresenter implements ReportLogcatContract.Presenter {

    private ReportLogcatContract.View view;

    public ReportLogcatPresent(ReportLogcatContract.View mview, Context ctx) {
        super(ctx);
        this.view = mview;

    }

    @SuppressLint("CheckResult")
    @Override
    public void reportLogcat(String num, int type, File files) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), files);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("logFile", files.getName(), requestFile);
        mService.reportLogcat(num, type, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stringBaseResponse -> {
                    String message = stringBaseResponse.getMessage();
                    view.onSuccess(stringBaseResponse.getCode(), message);
                    Log.d("---", message);
                });
    }
}
