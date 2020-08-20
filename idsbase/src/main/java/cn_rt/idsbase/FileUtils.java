package cn_rt.idsbase;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class FileUtils {
    private static String TAG = "FileUtils";

    public static boolean compMD5(final String path, final String md5Code) {
        final File file = new File(path);
        if (file.exists()) {
            String fileMD5 = getFileMD5(file);
            Log.d(TAG, "Download success file md5: " + fileMD5 + " md5code: " + md5Code + " file path: " + path);
            return fileMD5.equals(md5Code);
        } else {
            return false;
        }
    }

    /**
     * 获取单个文件的MD5值！
     * @param file
     * @return
     */
    /**
     * 获取单个文件的MD5值！
     *
     * @param file 文件
     * @return 文件32位MD5
     */

    private static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        String strMd5 = bigInt.toString(16);
        if (strMd5.length() < 32) {
            for (int i = 0; i < 32 - strMd5.length(); i++) {
                strMd5 = "0" + strMd5;//首位是0的MD5码补全首位的0
            }
        }
        return strMd5;
    }



    public static boolean delFile(File file) {
        if (!file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
        }
        return file.delete();
    }


    //判断文件是否存在
    public static boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
