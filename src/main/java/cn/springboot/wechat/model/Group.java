package cn.springboot.wechat.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/8/7 9:06
 */
@Component
@Entity
public class Group {

    //
    @Id
    private String id;
    //
    private String group_name;
    //
    private String group_lord;
    //
    private String group_notes;
    //
    private String pic;

    private Date createDate;

    public Group() {

    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_lord() {
        return group_lord;
    }

    public void setGroup_lord(String group_lord) {
        this.group_lord = group_lord;
    }

    public String getGroup_notes() {
        return group_notes;
    }

    public void setGroup_notes(String group_notes) {
        this.group_notes = group_notes;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", group_name='" + group_name + '\'' +
                ", group_lord='" + group_lord + '\'' +
                ", group_notes='" + group_notes + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
