package cn_rt.idsbase.netbean;

/**
 * Created by ${zml} on 2017/5/11.
 */
public class BaseResponse<T> extends ResponseText {

    private int code;
    private String message;
    private T data;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
