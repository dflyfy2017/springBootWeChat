package cn.springboot.wechat.common;

import cn.springboot.wechat.config.TargetDataSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/7/20 13:00
 */
public interface CommonAbstractDao<T> {
    T select(@Param("key") String uniqueField, @Param("value") Object value) throws Exception;
    List<T> findAll() throws Exception;
    void insert(T t) throws Exception;
    void update(T t) throws Exception;
    void delete(@Param("key") String uniqueField, @Param("value") Object value) throws Exception;
}
