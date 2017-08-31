package cn.springboot.wechat.service;

import cn.springboot.wechat.model.Group;
import cn.springboot.wechat.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/7/20 13:36
 */
public interface UserService {
    public List<User> findAll() throws Exception;
    public User findById(Integer id) throws Exception;
    public User login(User user) throws Exception;
    public User register(User user) throws Exception;
    public User select(String filed, Object value) throws Exception;
    public List<Group> findGroupByUserId(Integer user_id) throws Exception;
}
