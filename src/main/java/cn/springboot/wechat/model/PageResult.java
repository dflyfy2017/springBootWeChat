package cn.springboot.wechat.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/8/8 10:32
 */
public class PageResult {

    private int count;
    private Object data;

    public PageResult() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
