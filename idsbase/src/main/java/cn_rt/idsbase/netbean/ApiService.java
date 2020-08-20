package cn_rt.idsbase.netbean;


import cn_rt.idsbase.netbean.BaseResponse;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Author: zml
 * Description:所有网络请求接口
 */
public interface ApiService {
    //截图上报
    @Multipart
    @POST("api/diffusion/equipment/screenShot")
    io.reactivex.Observable<String> shotScreen(
            @Part("serial_no") String id,
            @Part MultipartBody.Part file);

    //进度上报
    @Multipart
    @POST("api/diffusion/sendShow/updateSendShowMaterialPercent")
    io.reactivex.Observable<String> reportProcess(
            @Part("program_id") String id,
            @Part("sub_id") int sub_id,
            @Part("serial_no") String serial_no,
            @Part("percent") int percent);

    //获取到的日志上报
    @Multipart
    @POST("api/diffusion/equipment/uploadLog")
    io.reactivex.Observable<BaseResponse<String>> reportLogcat(
            @Part("serial_no") String serial_no,
            @Part("type") int type,
            @Part MultipartBody.Part file);

    @Multipart
    @POST("api/diffusion/equipment/addinfrared")
//todo 上传地址
    io.reactivex.Observable<String> sendRed(
            @Part("createTime") long createTime,
            @Part("datalen") int lengh,
            @Part("irdata") String mdata);

    @GET("api/diffusion/equipment/checkNetwork")
    io.reactivex.Observable<String> pingTest();

}
