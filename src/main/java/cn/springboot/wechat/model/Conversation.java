package cn.springboot.wechat.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/8/7 10:20
 */
@Component
@Entity
public class Conversation {

    @Id
    @GeneratedValue
    private Integer id;


}
