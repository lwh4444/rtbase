package cn_rt.idsbase;

import android.app.NotificationManager;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.PowerManager;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import cn_rt.idsbase.Global;
import cn_rt.idsbase.PrefUtils;
import cn_rt.idsbase.UiUtils;

/**
 *  copyright:  https://github.com/slightlywobbly/adbwireless
 */
public class adbWirelessUtils {

    public static NotificationManager mNotificationManager;
    public static PowerManager.WakeLock mWakeLock;





    @SuppressWarnings("deprecation")
    public static boolean adbStart(Context context) {
        try {
            if (!Global.USB_DEBUG) {
                setProp("service.adb.tcp.port", Global.PORT);
                try {
                    if (isProcessRunning("adbd")) {
                        runRootCommand("stop adbd");
                    }
                } catch (Exception e) {
                }
               runRootCommand("start adbd");
            }
            try {
                Global.mState = true;
            } catch (Exception e) {
            }

            PrefUtils.putBoolean(UiUtils.getContext(),"mState",true);

            // Wake Lock
            boolean key_screenon = PrefUtils.getBoolean(UiUtils.getContext(), "key_screenon", false);
            if (key_screenon) {
                final PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
                mWakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, context.getClass().getName());
                mWakeLock.acquire();
            }


        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean adbStop(Context context) throws Exception {
        try {
            if (!Global.USB_DEBUG) {
                if (Global.mState) {
                    setProp("service.adb.tcp.port", "-1");
                    runRootCommand("stop adbd");
                    //runRootCommand("start adbd");
                }
            }
            Global.mState = false;


            PrefUtils.putBoolean(UiUtils.getContext(),"mState",false);


            // Wake Lock
            if(mWakeLock != null) {
                mWakeLock.release();
            }


        } catch (Exception e) {
            return false;
        }
        return true;

    }


    public static boolean isProcessRunning(String processName) throws Exception {
        boolean running = false;
        Process process = null;
        process = Runtime.getRuntime().exec("ps");
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
            if (line.contains(processName)) {
                running = true;
                break;
            }
        }
        in.close();
        process.waitFor();
        return running;
    }

    public static boolean hasRootPermission() {
        Process process = null;
        DataOutputStream os = null;
        boolean rooted = true;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
            if (process.exitValue() != 0) {
                rooted = false;
            }
        } catch (Exception e) {
            rooted = false;
        } finally {
            if (os != null) {
                try {
                    os.close();
                    process.destroy();
                } catch (Exception e) {
                }
            }
        }
        return rooted;
    }

    public static boolean runRootCommand(String command) {
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
        return true;
    }

    public static boolean setProp(String property, String value) {
        return runRootCommand("setprop " + property + " " + value);
    }

    public static String getWifiIp(Context context) {
        WifiManager mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int ip = mWifiManager.getConnectionInfo().getIpAddress();
        return (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + ((ip >> 24) & 0xFF);
    }


    public static boolean checkWifiState(Context context) {
        try {
            WifiManager mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
            if (!mWifiManager.isWifiEnabled() || wifiInfo.getSSID() == null) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
