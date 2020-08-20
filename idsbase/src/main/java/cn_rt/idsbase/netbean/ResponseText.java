package cn_rt.idsbase.netbean;

import com.google.gson.Gson;

/**
 * Created by ${zml} on 2017/5/11.
 */
public class ResponseText {

    public String toString (){
        return new Gson().toJson(this);
    }
}
