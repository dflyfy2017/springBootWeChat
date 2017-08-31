package cn.springboot.wechat.serviceImpl;

import cn.springboot.wechat.dao.GroupDao;
import cn.springboot.wechat.dao.UserDao;
import cn.springboot.wechat.exception.CustomizedException;
import cn.springboot.wechat.model.Group;
import cn.springboot.wechat.model.User;
import cn.springboot.wechat.service.UserService;
import cn.springboot.wechat.util.EncodeUtil;
import cn.springboot.wechat.util.GenerateAccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/7/20 13:37
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private GroupDao groupDao;

    @Override
    public List<User> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public User findById(Integer id) throws Exception {
        return userDao.select("id", id);
    }

    @Override
    public User login(User user) throws Exception {
        User u = userDao.select("account", user.getAccount());
        if (u == null) {
            throw new CustomizedException(300, "账户不存在");
        }
        //验证密码
        if (!EncodeUtil.validatePassword(u.getPassword(), user.getPassword())) {
            throw new CustomizedException(305, "密码错误");
        }
        u.setLast_login_date(new Date());
        System.out.println("当前登录总次数：" + u.getLogin_times());
        u.setLogin_times(u.getLogin_times() + 1);
        userDao.update(u);
        return u;
    }

    @Override
    public User register(User user) throws Exception {
        User u = userDao.select("email", user.getEmail());
        if (u != null) {
            throw new CustomizedException(300, "该邮箱已注册");
        }
        user.setAccount(GenerateAccountUtil.GenerateUserAccount());
        user.setPassword(EncodeUtil.encryptPassword(user.getPassword()));
        user.setRegister_date(new Date());
        user.setPicture("0001");
        userDao.insert(user);
        return userDao.select("id", user.getId());
    }

    @Override
    public User select(String filed, Object value) throws Exception {
        return userDao.select(filed, value);
    }

    @Override
    public List<Group> findGroupByUserId(Integer user_id) throws Exception {
        List<Group> groupList = groupDao.findGroupByUserId(user_id);
        return groupList;
    }


}
