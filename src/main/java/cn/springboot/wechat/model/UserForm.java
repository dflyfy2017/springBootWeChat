package cn.springboot.wechat.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/8/8 13:24
 */
@Component
@Entity
public class UserForm {
    @Id
    private Integer id;
    private int limit;
    private int offset;
    private int pageIndex;
    private int pageSize;
    private String userName;

    public UserForm() {
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "limit=" + limit +
                ", offset=" + offset +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", userName=" + userName +
                '}';
    }
}
