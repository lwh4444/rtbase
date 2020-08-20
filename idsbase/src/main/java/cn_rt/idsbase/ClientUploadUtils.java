package cn_rt.idsbase;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by  on 2020/8/13.
 */
public class ClientUploadUtils {
    public static ResponseBody upload(String url, File file, String num) throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("logFile", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"), file))
                .addFormDataPart("serial_no",num)
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + UUID.randomUUID())
                .url(url)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);else Log.i("logCatch","upload Success");

        return response.body();
    }

    public static ResponseBody uploadImg(String url, File file,String num) throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("screenShot", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"), file))
                .addFormDataPart("serial_no",num)
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + UUID.randomUUID())
                .url(url)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);else Log.i("logCatch","upload Success");

        return response.body();
    }

}
