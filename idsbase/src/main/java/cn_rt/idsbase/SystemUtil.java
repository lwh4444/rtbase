package cn_rt.idsbase;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

import java.util.Locale;

/**
 * Created by ${zml} on 2019/8/20.
 */
public class SystemUtil {

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return  语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        //return android.os.Build.BRAND;
        return Build.MANUFACTURER;
    }



    public static String getPackageName(Context context) {
        PackageManager manager = context.getPackageManager();
        String name = null;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return name;
    }

    public static int getSystemVoice(){
        AudioManager mAudioManager = (AudioManager)UiUtils.getContext(). getSystemService(Context.AUDIO_SERVICE);
        //最大音量
        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //当前音量
        int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        return (int) (currentVolume/ (float) maxVolume *100);
    }

    public static void setPlusVoice(int voice){
        AudioManager mAudioManager = (AudioManager)UiUtils.getContext(). getSystemService(Context.AUDIO_SERVICE);
        //最大音量
        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int i = (int) ((voice /100d) * maxVolume);

        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,AudioManager.FLAG_PLAY_SOUND);

    }



    public static int getSystemBrightness() {
        int systemBrightness = 0;
        try {
            systemBrightness = Settings.System.getInt(UiUtils.getContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return systemBrightness;

    }

    public static void SetSystemBrightness(Activity activity , int value) {
        try {
            Settings.System.putInt(activity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, value);
        } catch (Exception localException) {
            localException.printStackTrace();
        }


    }

    public static void stopAutoBrightness(Activity activity,int value) {
        Window localWindow = activity.getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        float f = value / 255.0F;
        localLayoutParams.screenBrightness = f;
        localWindow.setAttributes(localLayoutParams);
    }

    public static void saveBrightness(ContentResolver resolver, int brightness) {
        Uri uri = Settings.System
                .getUriFor("screen_brightness");

        Settings.System.putInt(resolver, "screen_brightness",
                brightness);
        // resolver.registerContentObserver(uri, true, myContentObserver);
        resolver.notifyChange(uri, null);
    }

}
