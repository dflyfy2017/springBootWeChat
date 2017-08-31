package cn.springboot.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by fuyuan on 2017/7/17.
 * 字符串转换工具类
 * 用户密码加密和验证
 */
public class EncodeUtil {
    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    private static MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证输入的密码是否正确
     * @param password 加密后的密码（从数据库取出）
     * @param inputString（输入的字符串）
     * @return 结果：TRUE ：正确，FALSE：错误
     */
    public static boolean validatePassword(String password, String inputString){
        return password.equals(encryptByMD5(inputString));
    }

    /**
     * 把输入的字符串进行加密
     * @param inputString
     * @return
     */
    public static String encryptPassword(String inputString) {
        return encryptByMD5(inputString);
    }

    /**
     * 对字符串进行MD5加密
     *
     * @param inputString
     * @return
     */
    private static String encryptByMD5(String inputString) {
        if (inputString != null && !"".equals(inputString)) {
            try {
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = messageDigest.digest(inputString.getBytes());
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将字符数组转换成16进制字符串
     *
     * @param results 字节数组
     * @return 16进制的字符串
     */
    private static String byteArrayToHexString(byte[] results) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < results.length; i++) {
            sb.append(byteToHexString(results[i]));
        }
        return sb.toString();
    }

    /**
     * 将一个字节转化成16进制形式的字符串
     *
     * @param b 一个字节
     * @return 16进制形式的字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
