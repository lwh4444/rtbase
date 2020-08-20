package cn_rt.idsbase.netbean;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ${zml} on 2019/6/27.
 */
public class ReportProcessPresent extends BasePresenter implements ReportProcessContract.Presenter {

    private ReportProcessContract.View view;
    private int tempPercent = 0;

    public ReportProcessPresent(ReportProcessContract.View mview, Context ctx) {
        super(ctx);
        this.view = mview;
        tempPercent = 0;

    }

    @SuppressLint("CheckResult")
    @Override
    public void getInfos(String program_id, int sub_id, String serial_no, int percent) {
        if (percent / 10 != tempPercent) {
            tempPercent = percent / 10;
            if (percent == 100) tempPercent = 0;
            Log.d("---", program_id + "  percent:" + tempPercent);
            mService.reportProcess(program_id, sub_id, serial_no, percent)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(stringBaseResponse -> {
                        String message = stringBaseResponse;
                        Log.d("---", message);
                    });
        }

    }
}
