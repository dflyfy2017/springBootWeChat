package cn.springboot.wechat.util;

public class LATDAndLGTDConversion {

    public static float ToString(String degree, String minute, String second) {
        float t1 = 60, t2 = 3600;
        float res = Float.valueOf(degree) + Float.valueOf(minute) / t1 + Float.valueOf(second) /t2;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(ToString("121", "31", "50"));
    }

}
