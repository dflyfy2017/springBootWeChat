package cn.springboot.wechat.dao;

import cn.springboot.wechat.common.CommonAbstractDao;
import cn.springboot.wechat.model.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/8/7 9:26
 */
public interface GroupDao extends CommonAbstractDao<Group> {

    List<Group> findGroupByUserId(@Param("value") Integer user_id) throws Exception;
}
