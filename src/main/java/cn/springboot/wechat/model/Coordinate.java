package cn.springboot.wechat.model;

import org.springframework.stereotype.Component;

@Component
public class Coordinate {
    private String degree;
    private String minute;
    private String second;

    public Coordinate(){

    }

    public Coordinate(String degree, String minute, String second) {
        this.degree = degree;
        this.minute = minute;
        this.second = second;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }
}
