package cn_rt.idsbase;

import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${zml} on 2017/5/11.
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        if (null == str || str.trim().equals("") || str.equals(null)) {
            return true;
        }

        return false;
    }

    /**
     * 验证手机号码
     */
    public static boolean judgePhoneNums(String tellphone) {
        if (isMatchLength(tellphone, 11)) {
            return true;
        }
        return false;
    }

    // 验证手机号码的长度
    public static boolean isMatchLength(String tellphone, int length) {
        if (TextUtils.isEmpty(tellphone)) {
            return false;
        } else {
            return tellphone.length() == length ? true : false;
        }
    }

    //验证是否为hex
    public static boolean isHex(String str) {
        String regex = "^[A-Fa-f0-9]+$";
        if (str.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }


    //验证是否是表情的字符串
    public static boolean isEmoji(String string) {
        Pattern p = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        return m.find();
    }

    //获取0-9范围内的随机数
    public static int getRandom() {
        int max = 57;
        int min = 48;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    //查找当前文件中以"xml"命名的文件

    public static String getFileUrl(File file) {
        String fileUrl = "";
        if (file.getName().endsWith("php")) {//end为后缀名
            fileUrl = file.getAbsolutePath();
        }
        return fileUrl;
    }

    public static byte[] intToByteArray(int a) {
        byte[] ret = new byte[4];
        ret[3] = (byte) (a & 0xFF);
        ret[2] = (byte) ((a >> 8) & 0xFF);
        ret[1] = (byte) ((a >> 16) & 0xFF);
        ret[0] = (byte) ((a >> 24) & 0xFF);
        return ret;
    }

    public static List<File> readFile(String fileDir) {
        List<File> fileList = new ArrayList<File>();
        File file = new File(fileDir);
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹
        if (files == null) {// 如果目录为空，直接退出
            return null;
        }

        // 遍历，目录下的所有文件
        for (File f : files) {
            if (f.isFile()) {
                fileList.add(f);
            } else if (f.isDirectory()) {
                System.out.println(f.getAbsolutePath());
                readFile(f.getAbsolutePath());
            }
        }
        for (File f1 : fileList) {
            System.out.println(f1.getName());
        }
        return fileList;
    }

    public static byte[] string2byte(String str) {
        byte[] m;
        String[] strs = str.split(",");
        if (strs.length <= 0) return new byte[0];
        m = new byte[strs.length];
        for (int i = 0; i < strs.length; i++) {
            m[i] = (byte) Integer.valueOf(strs[i]).byteValue();
        }
        return m;
    }

}
