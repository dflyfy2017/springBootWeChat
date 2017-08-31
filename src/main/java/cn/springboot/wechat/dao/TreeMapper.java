package cn.springboot.wechat.dao;

import cn.springboot.wechat.common.CommonAbstractDao;
import cn.springboot.wechat.model.Shipin_tree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: dfly
 * Date: 2017-08-18
 * Time: 10:59
 */
@Mapper
public interface TreeMapper extends CommonAbstractDao<Shipin_tree>{
    public void updateLGTDAndLATD(Shipin_tree tree);
}
