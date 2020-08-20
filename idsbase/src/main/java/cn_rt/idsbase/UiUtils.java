package cn_rt.idsbase;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by ${zml} on 2018/1/5.
 */
public class UiUtils {


    public static Context getContext() {
        return IDSBaseTool.getAppContext();
    }

    //根据图片的名称转化为图片ID
    public static int getDrawResourceID(String resourceName) {
        int id = getContext().getResources().getIdentifier(resourceName, "drawable", getContext().getPackageName());
        return id;

    }

    public final static void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }


    /**
     * 保存方法
     */
    public static File saveImage(Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "screenshot");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;

    }


    public static String getDataFromDevice() {
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();

        return absolutePath;
    }

    //获取手机mac地址
    public static String getMacAddress() {
        String mac_s = "";
        StringBuilder buf = new StringBuilder();
        try {
            byte[] mac;
            NetworkInterface ne = NetworkInterface.getByInetAddress(InetAddress.getByName(getIpAddress(getContext())));
            mac = ne.getHardwareAddress();
            for (byte b : mac) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            String[] split = buf.toString().split(":");
            for (int i = 0; i < split.length; i++) {
                mac_s += split[i];
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mac_s;
    }


    public static String getIpAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            // 3/4g网络
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                try {
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                //  wifi网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());
                return ipAddress;
            } else if (info.getType() == ConnectivityManager.TYPE_ETHERNET) {
                // 有限网络
                return getLocalIp();
            }
        }
        return null;
    }

    private static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);

    }

    //获取手机ip地址
    public static String getIPAddress(Context ctx, int type) {
        WifiManager wifi_service = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcpInfo = wifi_service.getDhcpInfo();
        // WifiInfo wifiinfo = wifi_service.getConnectionInfo();
        String ip = Formatter.formatIpAddress(dhcpInfo.ipAddress);
        String netmask = Formatter.formatIpAddress(dhcpInfo.netmask);
        String gatway = Formatter.formatIpAddress(dhcpInfo.gateway);
        //DhcpInfo中的ipAddress是一个int型的变量，通过Formatter将其转化为字符串IP地址
        if (type == 2) {
            return ip;
        } else if (type == 3) {
            return netmask;
        } else if (type == 4) {
            return gatway;
        }
        return "";

    }

    //删除文件夹和文件夹里面的文件
    public static void deleteDir(final String pPath) {
        File dir = new File(pPath);
        deleteDirWihtFile(dir);
    }

    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }


    // 获取有限网IP
    private static String getLocalIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {

        }
        return "0.0.0.0";

    }

    //获取当前存储的mac地址
    public static String getMacAddr() {
        String macAddr = "";
        macAddr = PrefUtils.getString(UiUtils.getContext(), "mac", "");
        if (StringUtils.isEmpty(macAddr)) {
            macAddr = getMacAddress();
            PrefUtils.putString(UiUtils.getContext(), "mac", macAddr);
        }
        Log.d("mac", macAddr);
        return macAddr;

    }

    // param folderPath 文件夹完整绝对路径

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // param path 文件夹完整绝对路径
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    //判断本地资源是否已经存在，存在时即可不用再去下载
    public static boolean isExistsResources(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public static byte[] InputStream2ByteArray(String filePath) throws IOException {

        InputStream in = new FileInputStream(filePath);
        byte[] data = toByteArray(in);
        in.close();

        return data;
    }

    private static byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    public static ArrayList<String> readFileByLines(String fileName) {
        ArrayList<String> lists = new ArrayList<>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;      // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                lists.add(tempString);
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {

                }
            }
        }
        return lists;
    }


    /**
     * Dialog;
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static AlertDialog getDialog(Context context, int resId) {
        //  AlertDialog dialog = new getShow.Builder(context, AlertDialog.THEME_TRADITIONAL).create();     //AlertDialog.THEME_TRADITIONAL表示默认的背景为透明
        AlertDialog dialog = new AlertDialog.Builder(context, R.style.tipsDialog_style).create();     //AlertDialog.THEME_TRADITIONAL表示默认的背景为透明
        dialog.setView(new EditText(getContext()));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.setContentView(resId);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
//        int screenWidth = ScreenUtil.getInstance(UiUtils.getContext()).getScreenWidth();
//        attributes.width = screenWidth;
        attributes.gravity = Gravity.CENTER;
        window.setAttributes(attributes);
        return dialog;
    }


    //使用正则验证minicc的ip地址，子网掩码，网关
    public static boolean isIP(String ipAddres) {
        if (ipAddres.length() < 7 || ipAddres.length() > 15 || "".equals(ipAddres)) {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(ipAddres);

        boolean ipAddress = mat.find();

        return ipAddress;
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

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleFile(String url) {
        File file = new File(url);
        if (file.exists()) {
            file.delete();
        }
    }


    public static int getRabbitPort(int buildRabbitPort) {
        int port = PrefUtils.getInt(UiUtils.getContext(), "port", 0);
        return port == 0 ? buildRabbitPort : port;
    }

    public static String getRabbitUser(String buildRabbitName) {
        String username = PrefUtils.getString(UiUtils.getContext(), "username", "");
        return StringUtils.isEmpty(username) ? buildRabbitName : username;
    }

    public static String getRabbitPwd(String buildRabbitPassWord) {
        String password = PrefUtils.getString(UiUtils.getContext(), "password", "");
        return StringUtils.isEmpty(password) ? buildRabbitPassWord : password;
    }

    public static String getServerAddr(String buildUrl) {
        String ip = "";
        String server_addr = PrefUtils.getString(UiUtils.getContext(), "server_addr", "");
        if (StringUtils.isEmpty(server_addr)) {
            //为空 不用去下发地址
            ip = Global.getBaseUrl(buildUrl);
        } else {
            ip = server_addr;
        }
        return ip;
    }

    public static int countFloat(float f1, float f2) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(0);
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        String result = numberFormat.format((f1 / f2) * 100).replaceAll(",", "");
        return Integer.parseInt(result);
    }


    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    public static boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    public static void hideKeyboard(IBinder token, Application context) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) {
                im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
