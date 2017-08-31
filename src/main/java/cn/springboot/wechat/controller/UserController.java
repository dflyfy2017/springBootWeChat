package cn.springboot.wechat.controller;

import cn.springboot.wechat.model.Group;
import cn.springboot.wechat.model.PageResult;
import cn.springboot.wechat.model.UserForm;
import cn.springboot.wechat.service.UserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/7/20 14:47
 */
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public Object findAll() throws Exception {
        return userService.findAll();
    }


    @GetMapping("/find/{id}")
    public Object findById(@PathVariable("id") Integer id) throws Exception {
        return userService.findById(id);
    }

    @RequestMapping(value = "/getAllData", method = RequestMethod.GET)
    public void getData(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

    }

    @RequestMapping(value = "/testTable", method = RequestMethod.GET)
    public String tableListView() {
        return "table";
    }

    @ResponseBody
    @RequestMapping(value = "/testTable", method = RequestMethod.POST)
    public Object tableList(UserForm form, String group_notes) {
        logger.info(form.toString());
        logger.info("Edit value :" + group_notes);
        PageResult result = new PageResult();
        List<Group> list = new ArrayList<Group>();
        for (int i = 0; i < 20; i++) {
            Group group = new Group();
            group.setId("" + (i + 1));
            group.setGroup_name("Group" + i);
            group.setGroup_lord("Group_lord" + i);
            group.setGroup_notes("Group_notes" + i);
            group.setCreateDate(new Date());
            list.add(group);
        }
        result.setCount(20);
        result.setData(list);
        return result;
    }

}
