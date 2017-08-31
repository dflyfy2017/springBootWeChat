package cn.springboot.wechat.controller;

import cn.springboot.wechat.model.Group;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/8/8 11:19
 */
@RestController
public class TestController {

    @PostMapping(value = "/testTableData")
    public Object tableList() {
        /*logger.info(limit + "," + offset + "," + departmentName + "," + status);*/
        List<Group> list = new ArrayList<Group>();
        for (int i = 0; i < 50; i++) {
            Group group = new Group();
            group.setId("" + (i+1));
            group.setGroup_name("Group" + i);
            group.setGroup_lord("Group_lord" + i);
            group.setGroup_notes("Group_notes" + i);
            list.add(group);
        }
        Group group = new Group();
        group.setId("00002");
        group.setGroup_name("GroupName");
        group.setGroup_lord("Group_lord");
        group.setGroup_notes("Group_notes");
        return list;
    }
}
