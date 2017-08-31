package cn.springboot.wechat.serviceImpl;

import cn.springboot.wechat.dao.TreeMapper;
import cn.springboot.wechat.model.Coordinate;
import cn.springboot.wechat.model.Shipin_tree;
import cn.springboot.wechat.service.TreeService;
import cn.springboot.wechat.util.LATDAndLGTDConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: dfly
 * Date: 2017-08-18
 * Time: 11:02
 */
@Service
@Transactional
public class TreeServiceImpl implements TreeService {

    private static final Logger logger = LoggerFactory.getLogger(TreeServiceImpl.class);

    @Autowired
    private TreeMapper treeDao;

    @Override
    public Shipin_tree getById(String ds, String id) throws Exception {
        return treeDao.select("_id", id);
    }

    @Override
    public void insert(String ds, Shipin_tree tree) throws Exception {
        treeDao.insert(tree);
    }

    @Override
    public void update(String ds, Shipin_tree tree) throws Exception {
        treeDao.update(tree);
    }

    @Override
    public List<Shipin_tree> findAll(String dataSource) throws Exception {
        List<Shipin_tree> list = treeDao.findAll();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Shipin_tree tree = (Shipin_tree) iterator.next();
            tree.setIcon("zTree/" + tree.getIcon());
        }
        return list;
    }

    @Override
    public void delete(String ds, String filed, Object value) throws Exception {
        treeDao.delete(filed, value);
    }

    @Override
    public void updateLGTDAndLATD(Coordinate cE, Coordinate cN, String _id) {
        Shipin_tree tree = new Shipin_tree();
        float e = LATDAndLGTDConversion.ToString(cE.getDegree(), cE.getMinute(), cE.getSecond());
        float n = LATDAndLGTDConversion.ToString(cN.getDegree(), cN.getMinute(), cN.getSecond());
        tree.setLGTD(e);
        tree.setLATD(n);
        /*tree.set_id(_id);*/
        treeDao.updateLGTDAndLATD(tree);
    }

    @Override
    public void modifyTreeNode(String ds, String _id, String opType, String value) throws Exception {
        Shipin_tree tree = new Shipin_tree();
        tree.set_id(_id);
        if (opType != null && opType.equals("status")) {
            tree.setImportant(Integer.parseInt(value));
            tree.setPhoneImportant(Integer.parseInt(value));
        }
        if (opType != null && opType.equals("fontColor")) {
            tree.setIsgray(value);
        }
        treeDao.update(tree);
    }

}
