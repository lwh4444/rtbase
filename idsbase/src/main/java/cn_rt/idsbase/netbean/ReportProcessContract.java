package cn_rt.idsbase.netbean;

/**
 * Created by ${zml} on 2019/6/27.
 */
public class ReportProcessContract {


    interface Presenter{
        void getInfos(String program_id, int sub_id,String serial_no,int percent);
    }

    public interface View extends BaseView {
        void onSuccess(int data,String mes);
    }
}
