package cn.springboot.wechat.controller;

import cn.springboot.wechat.model.Coordinate;
import cn.springboot.wechat.model.Shipin_tree;
import cn.springboot.wechat.service.TreeService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/8/8 11:19
 */
@Controller
public class TreeController {

    private String ds;

    private static final Logger logger = LoggerFactory.getLogger(TreeController.class);

    @Autowired
    private TreeService treeService;

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public String viewTree() {
        return "tree";
    }

    @RequestMapping(value = "/treeList", method = RequestMethod.POST)
    public void treeList(HttpServletResponse response,
                         HttpServletRequest request) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String ds = request.getParameter("ds");
        this.ds = ds;
        List<Shipin_tree> list = treeService.findAll(ds);
        Gson gson = new Gson();
        String listJson = gson.toJson(list);
        out.print(listJson);
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/updateGA", method = RequestMethod.POST)
    public String updateGA(HttpServletRequest request) {
        String[] degree = request.getParameterValues("degree");
        String[] minute = request.getParameterValues("minute");
        String[] second = request.getParameterValues("second");
        String _id = request.getParameter("_id");
        System.out.println(minute[0] + "," + minute[1]);
        Coordinate cE = new Coordinate(degree[0], minute[0], second[0]);
        Coordinate cN = new Coordinate(degree[1], minute[1], second[1]);
        treeService.updateLGTDAndLATD(cE, cN, _id);
        return "update";
    }

    @RequestMapping(value = "/updateGA", method = RequestMethod.GET)
    public String viewUpdate() {
        return "update";
    }

    @RequestMapping(value = "/addNode", method = RequestMethod.POST)
    public void add(HttpServletResponse response, HttpServletRequest request,
                    Shipin_tree tree) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (tree.getLGTD() != null && tree.getLATD() != 0) {
            tree.setLGTD(Float.valueOf(tree.getLGTD()));
        }
        if (tree.getLATD() != null && tree.getLATD() != 0) {
            tree.setLATD(Float.valueOf(tree.getLATD()));
        }
        String _id = request.getParameter("_id");
        String _pId = request.getParameter("_pId");
        tree.set_id(_id);
        tree.set_pId(_pId);
        try {
            treeService.insert(this.ds, tree);
            out.write("增加成功");
        } catch (Exception e) {
            out.write(e.getMessage());
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/updateNode", method = RequestMethod.POST)
    public void update(HttpServletResponse response, HttpServletRequest request,
                       Shipin_tree tree) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String _id = request.getParameter("_id");
        String _pId = request.getParameter("_pId");
        tree.set_id(_id);
        tree.set_pId(_pId);
        if (tree.getLGTD() != null && tree.getLATD() != 0) {
            tree.setLGTD(Float.valueOf(tree.getLGTD()));
        }
        if (tree.getLATD() != null && tree.getLATD() != 0) {
            tree.setLATD(Float.valueOf(tree.getLATD()));
        }
        try {
            treeService.update(this.ds, tree);
            out.print("修改成功");
        } catch (Exception e) {
            out.print(e.getMessage());
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/deleteNode", method = RequestMethod.POST)
    public void delete(HttpServletResponse response, HttpServletRequest request)
            throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String _id = request.getParameter("_id");
        if (_id != null) {
            try {
                treeService.delete(this.ds, "_id", _id);
                out.print("删除成功！");
            } catch (Exception e) {
                out.print(e.getMessage());
            }
        } else {
            out.print("字段取值异常！请检查");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/hideNode", method = RequestMethod.POST)
    public void modifyTreeNode(HttpServletResponse response,
                               HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String opType = request.getParameter("opType");
        String _id = request.getParameter("id");
        String value = request.getParameter("value");
        if (_id != null) {
            try {
                treeService.modifyTreeNode(this.ds,  _id, opType, value);
                out.print("操作成功！刷新页面可看到效果！");
            } catch (Exception e) {
                out.print(e.getMessage());
            }
        } else {
            out.print("id字段取值异常！请检查");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/getBy_id", method = RequestMethod.POST)
    public void getNodeInfoBy_id(HttpServletResponse response,
                                 HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String _id = request.getParameter("id");
        Shipin_tree treeNode = null;
        Gson gson = new Gson();
        if (_id != null) {
            try {
                treeNode = treeService.getById(this.ds, _id);
                out.write(gson.toJson(treeNode));
            } catch (Exception e) {
                out.print(e.getMessage());
            }
        } else {
            out.write("_id字段取值错误");
        }

        out.flush();
        out.close();
    }

}
