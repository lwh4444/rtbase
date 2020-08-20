package cn_rt.idsbase.widget;


import org.greenrobot.eventbus.EventBus;

/**
 * Created by ${zml} on 2017/2/22.
 */

public class AppEvent<T> {
    public T data;
    public Message msg;
    public enum Message{
        sendMessage,destoryWeb,sendProgram,reboot,timerReboot,
        doTimerReboot,rebootRecall,sendData,wifisend,
        screenon,screenoff,sendMode,sendMeetingMode,updateVersion,
        sendSub,clearChche,changeTime,refresh,SHOTSCREEN,VOICEP,ADBSTATE,
        getLogcat,sendRed
    }

    public AppEvent(Message msg, T data){
        this.msg = msg;
        this.data = data;
    }

    public Message getMessage() {
        return msg;
    }

    public <T>T getData(){
        if(data == null){

        }
        return (T)data;
    }

    public static <T> void post(Message msg, T data){
        EventBus.getDefault().postSticky(new AppEvent(msg, data));
    }
}
