package cn.springboot.wechat.util;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/7/20 16:15
 * 自动生成账号工具类
 */
public class GenerateAccountUtil {

    /**
     * 生成9位随机账号
     * @return
     */
    public static String GenerateUserAccount(){
        int length = 6;
        String sb = null;
        Random random = new Random();
        double o = (random.nextDouble() + 1) * Math.pow(10, length);
        String a = String.valueOf(o);
        sb = a.substring(1, length+1);
        for (int q = 0; q < 2; q++) {
            int n = (int) (Math.random() * 10);
            sb += n;
        }
        return ((int) (Math.random() * 9) + 1) + sb;
    }

    /**
     * 生成7位随机群号
     * @return
     */
    public static String GenerateGroupAccount(){
        int length = 6;
        String sb = null;
        Random random = new Random();
        double o = (random.nextDouble() + 1) * Math.pow(10, length);
        String a = String.valueOf(o);
        sb = a.substring(1, length+1);
        return ((int) (Math.random() * 9) + 1) + sb;
    }

}
