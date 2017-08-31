package cn.springboot.wechat.serviceImpl;

import cn.springboot.wechat.model.Coordinate;
import cn.springboot.wechat.model.Shipin_tree;
import cn.springboot.wechat.service.TreeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: dfly
 * Date: 2017-08-18
 * Time: 11:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTreeServiceImpl {

    @Autowired
    private TreeService treeService;

    @Test
    public void test1() throws Exception {
        /*Shipin_tree tree = treeService.getById("500");
        System.out.println(tree.toString());*/
    }

    @Test
    public void insert() throws Exception {
        Shipin_tree tree = new Shipin_tree();
        tree.setId(602);
        tree.set_id("505");
        tree.setpId("5.02.3.3");
        tree.setName("测试8");
        tree.setCODE("3001");
        tree.setPhoneCODE("3001");
        tree.setImportant(1);
        tree.setPhoneImportant(1);
        /*tree.setLGTD(Float.valueOf("1525.3652"));
        tree.setLATD(Float.valueOf("152.3652236"));*/
        /*treeService.insert(tree);*/
    }

    @Test
    public void update() throws Exception {
        Shipin_tree tree = new Shipin_tree();
        tree.setId(600);
        tree.set_id("600");
        tree.setName("测试7-修改-2");
        tree.setCODE("3005");
        tree.setPhoneCODE("3006");
        tree.setIcon("icons/sp16.png");
        tree.setLGTD(Float.valueOf("151.25254"));
        tree.setLATD(Float.valueOf("29.362514"));
        /*treeService.update(tree);*/
    }

    @Test
    public void findAll() throws Exception {
        List<Shipin_tree> list = treeService.findAll("test");
        System.out.println(list.size());
    }

    @Test
    public void delete() throws Exception {
        /*treeService.delete("id", 501);*/
    }

    @Test
    public void testUpdate(){
        Coordinate cE = new Coordinate("121", "47", "45");
        Coordinate cN = new Coordinate("29", "10", "3");
        String _id = "541";
        treeService.updateLGTDAndLATD(cE, cN, _id);
    }
}
