package cn.springboot.wechat.serviceImpl;

import cn.springboot.wechat.model.Group;
import cn.springboot.wechat.model.User;
import cn.springboot.wechat.service.UserService;
import cn.springboot.wechat.util.EncodeUtil;
import cn.springboot.wechat.util.GenerateAccountUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void findById() throws Exception {
        List<User> list = userService.findAll();
        for (User u : list) {
            System.out.println(u.toString());
        }
    }

    @Test
    public void register() throws Exception {
        User user = new User();
        user.setName("邓福如");
        user.setAccount(GenerateAccountUtil.GenerateUserAccount());
        user.setPassword(EncodeUtil.encryptPassword("000000"));
        user.setEmail("147258@wc.com");
        user.setRegister_date(new Date());
        user.setPicture("0004");
        this.userService.register(user);
    }

    @Test
    public void testG2() {
        try {
            List<Group> groupList = userService.findGroupByUserId(13);
            for (Group g: groupList) {
                System.out.println(g.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}