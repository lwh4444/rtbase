package cn_rt.idsbase.netbean;

import java.io.File;

/**
 * Created by ${zml} on 2019/6/27.
 */
public class ReportLogcatContract {


    interface Presenter{
        void reportLogcat(String serial_no, int type,File request);
    }

    public interface View extends BaseView {
        void onSuccess(int data,String mes);
    }
}
