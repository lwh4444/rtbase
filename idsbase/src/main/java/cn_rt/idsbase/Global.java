package cn_rt.idsbase;

import android.os.Environment;

/**
 * Created by ${zml} on 2018/1/24.
 */
public class Global {
    public static int DELAY_MONITOR = 3600000;
    public static int DELAY_RESET = 3000;
    public static int DOWNLOADCLEANCACHE = 60 * 60 * 1000; //60分钟清除一次缓存

    //定时连接服务器得时间
    public static int DELAY_SERVICE_TIME = 3000;

    //周期性同步服务器时间
    public static int DELAY_NTP = 1 * 60 * 60 * 1000; //1小时同步一次

    //启动页跳转主页面时间
    public static int DELAY_SPLASH = 5000;

    public static int DELAY_NET = 5000;

    public static int VLC_TIME = 1000;

    //和服务器交互消息message_type定义
    public final static int MSG_NULL_PLAY_REQ = 864;//暂无节目
    public final static int MSG_FREE_PLAY_REQ = 865;//空闲播放
    public final static int MSG_TIME_PLAY_REQ = 867;//按时播放
    public final static int MSG_INTERVAL_PLAY_REQ = 869;//插播播放
    public final static int MSG_PROGRAM_UNDO = 871;//撤消节目
    public final static int MSG_DEVICE_REBOOT = 873;//重启设备
    public final static int MSG_STATE_REPORT = 875;//上报状态(CP),MEERO,D等
    public final static int MSG_ID_SET_SERVER_ADDRESS = 885; //配置服务器地址
    public final static int MSG_ID_IDS2_DEVICE_REBOOT_TIMER = 889; //定时重启任务
    public final static int MSG_ID_IDS2_DEVICE_REBOOT_TIMER_UNDO = 891; //定时重启任务撤销
    public final static int MSG_ID_IDS2_DEVICE_UPDATE_VERSION = 901; //版本更新
    public final static int MSG_ID_IDS2_DEVICE_SEND_MODE = 902; //前端设置终端逻辑
    public final static int MSG_ID_IDS2_DEVICE_CLEAR_CACHE = 905; //清除终端离线缓存逻辑
    public final static int MSG_ID_IDS2_DEVICE_PLAY_AUDIO = 906; //播放背景音乐
    public final static int MSG_ID_IDS2_DEVICE_STOP_AUDIO = 907; //停止背景音乐

    //会议节目
    public final static int MSG_ID_IDS2_MEETING_PLAY_REQ = 893;//会议节目
    public final static int MSG_ID_IDS2_MEETING_UNDO = 895;
    public final static int MSG_ID_IDS2_CHANGE_MEETING_ROOM = 904; //设备更换了会议室
    public final static int MSG_ID_IDS2_MEETING_UPDATE_MODE = 903; //修改会议模板的协议

    public static int MSG_DEVICE_REGISTER = 882;

    public final static int MSG_ID_IDS2_DOC_PROGRAM_FINISHED = 910; //pdf文档播放结束
    public final static int MSG_ID_IDS2_STORAGE_LIMITATION = 911; //存储空间不足
    public final static int MSG_ID_IDS2_TRIGGER_DOWNLOAD_RESUME = 912;
    public final static int MSG_ID_IDS2_TRIGGER_DOWNLOAD_COMPLETE = 913;
    public final static int MSG_ID_IDS2_TRIGGER_RESET_CURRENT_PROGRAM = 914;

    public final static int MSG_ID_IDS2_SHOTSCREEN = 1004; //上报截图
    public static int MSG_ID_IDS2_VOICE = 1002; //调节音量
    public static int MSG_ID_IDS2_ADBSTATE = 1005; //adb 打开关闭
    public static int MSG_ID_IDS2_LOGCAT = 1006; //获取设备相关日志
    //上报警报状态
    public static int ALARM_NOAMAL = 0;
    public static int ALARM_CPU = 1;
    public static int ALARM_MEMORY = 2;
    public static int ALARM_both = 3;
    public static int ALARM_sign = 4;

    //在线还是离线节目标识
    public static int online = 1;
    public static int offline = 0;


    //用于区分当前是结束节目还是开始节目
    public static int PROGRAM_START = 0;
    public static int PROGRAM_END = 1;

    //发送红外
    public static int MSG_ID_IDS2_SENDRED = 909;

    //判断节目类型
    public static int WHEELTYPE = 1; //快速发布得节目类型

    //按时节目正常播放
    public static int CAR_NORMAL = 0;
    public static int CAR_CONTINUOUS = 1;

    public static String PROGRAM_ACTION_INDICATION = "com.ids.program.action";
    public static String PROGRAM_PAUSE_INDICATION = "com.ids.program.pause";
    public static String PROGRAM_STOP_INDICATION = "com.ids.program.stop";
    public static String PROGRAM_DOC_ACTION_INDICATION = "com.ids.doc.action";
    public static String PROGRAM_DOC_DONE_INDICATION = "com.ids.doc.done";
    public static String SCREENSHOT = "screenshot";


    //区分当前播放的是否h5节目或 rtsp节目
    public static int PLAYTYPENOR = 1;
    public static int PLAYTYPERTSP = 2;


    //定时任务类型
    public final static int TASKTYPE_REBOOT = 1; // 定时重启
    public final static int TASKTYPE_SHUTDOWN = 2; // 定时开关机
    public final static int TASKTYPE_SCREEN = 3; // 定时熄亮屏
    public final static int TASKTYPE_SHUTDOWN_0830 = 4; // 0830B设备关机时间
    public final static int TASKTYPE_SENDRED = 5;//发送红外


    public static final String PORT = "5555";
    public static final boolean USB_DEBUG = false;
    public static boolean mState = false;


    public static String LOGCATNAME = Environment.getExternalStorageDirectory()
            + "/" + DateUitils.getCurrentStringDate() + " " + DateUitils.getCurrentSingleTime() + "_" + UiUtils.getMacAddr() + ".txt";

    public static String getBaseUrl(String buildUrl) {
        String baseUrl = PrefUtils.getString(UiUtils.getContext(), "server_addr", "");
        if (baseUrl.isEmpty()) {
            baseUrl = buildUrl;
        }
        return baseUrl;
    }

    public static String getBasePort(String buildPort) {
        String ipPort = PrefUtils.getString(UiUtils.getContext(), "ipPort", "");
        if (ipPort.isEmpty()) {
            ipPort = buildPort;
        }
        return ipPort;
    }

    /**
     * url拼接
     */
    public static String getFullUrl(String buildUrl, String buildPort) {
        return "http://" + getBaseUrl(buildUrl) + ":" + getBasePort(buildPort) +
                "/";
    }
}
