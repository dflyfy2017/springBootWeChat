package cn.springboot.wechat.service;

import cn.springboot.wechat.model.Coordinate;
import cn.springboot.wechat.model.Shipin_tree;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: dfly
 * Date: 2017-08-18
 * Time: 11:02
 */
public interface TreeService {
    public Shipin_tree getById(String ds, String id) throws Exception;

    public void insert(String ds, Shipin_tree tree) throws Exception;

    public void update(String ds, Shipin_tree tree) throws Exception;

    public List<Shipin_tree> findAll(String dataSource) throws Exception;

    public void delete(String ds, String filed, Object value) throws Exception;

    public void updateLGTDAndLATD(Coordinate coordinateE, Coordinate coordinateN, String _id);

    public void modifyTreeNode(String ds, String _id, String opType, String value) throws Exception;

}
