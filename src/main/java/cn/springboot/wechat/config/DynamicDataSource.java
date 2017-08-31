package cn.springboot.wechat.config;

import cn.springboot.wechat.common.DatabaseType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created with IntelliJ IDEA.
 * Description: 动态数据源切换
 * User: dfly
 * Date: 2017-08-17
 * Time: 16:57
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSourceType();
    }

    public static void setDataSourceType(String type){
        contextHolder.set(type);
    }

    public static String getDataSourceType() {
        return contextHolder.get();
    }

    public static void clearDataSourceType(){
        contextHolder.remove();
    }
}
