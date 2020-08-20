package cn_rt.idsbase.netbean;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * Created by ${zml} on 2019/6/27.
 */
public class ShotScreenPresent extends BasePresenter implements ShotScreenContract.Presenter {

    private BaseView view;

    public ShotScreenPresent(BaseView mview, Context ctx) {
        super(ctx);
        this.view = mview;

    }

    @SuppressLint("CheckResult")
    @Override
    public void getInfo(String num, File files) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), files);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("screenShot", files.getName(), requestFile);
        mService.shotScreen(num, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String stringBaseResponse) throws Exception {
                        Log.d("---", stringBaseResponse);
                    }

                });


    }
}
