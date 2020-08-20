package cn_rt.idsbase.netbean;

import java.io.File;

/**
 * Created by ${zml} on 2019/6/27.
 */
public class ShotScreenContract {


    interface Presenter{
        void getInfo(String num, File request);
    }


}
