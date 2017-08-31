package cn.springboot.wechat.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 动态数据源注册
 * User: dfly
 * Date: 2017-08-17
 * Time: 17:04
 */
public class DynamicDataSourceRegister implements
        ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    //数据源配置信息
    private PropertyValues dataSourcePropertyValues;

    //默认数据源
    private DataSource defaultDataSource;

    //动态数据源
    private Map<String, DataSource> dynamicDataSources = new HashMap<>();

    /**
     * 加载多数据源配置
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {

        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver
                (environment, "spring.datasource.");
        String dsPrefixs = propertyResolver.getProperty("dataSources");
        for (String dsPrefix : dsPrefixs.split(",")) {//多个数据源
            Map<String, Object> map = propertyResolver.getSubProperties(dsPrefix + ".");
            DataSource dataSource = initDataSource(map);
            //设置默认数据源
            if ("test".equals(dsPrefix)) {
                defaultDataSource = dataSource;
            } else {
                dynamicDataSources.put(dsPrefix, dataSource);
            }
            dynamicDataSources.put(dsPrefix, dataSource);
            dataBinder(dataSource, environment);
        }

    }



    /**
     * 初始化数据源
     * @param map
     * @return
     */
    private DataSource initDataSource(Map<String, Object> map) {
        logger.info("=====initDataSource(初始化数据源)=====");
        String driverClassName = map.get("driverClassName").toString();
        String url = map.get("url").toString();
        String username = map.get("username").toString();
        String password = map.get("password").toString();
        String Type = map.get("Type").toString();
        /*DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);*/
        Class<DataSource> dataSourceType;
        DataSource dataSource = null;
        try {
            dataSourceType = (Class<DataSource>) Class.forName(Type);
            //根据连接信息创建数据源
            dataSource = DataSourceBuilder.create().
                    driverClassName(driverClassName).url(url)
                    .username(username).password(password).type(dataSourceType).build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     * 加载数据源配置信息
     * @param dataSource
     * @param environment
     */
    private void dataBinder(DataSource dataSource, Environment environment) {
        logger.info("=====dataBinder(加载数据源配置信息)=====");
        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
        dataBinder.setIgnoreNestedProperties(false);// false
        dataBinder.setIgnoreInvalidFields(false);// false
        dataBinder.setIgnoreUnknownFields(true);// true
        if (dataSourcePropertyValues == null) {
            Map<String, Object> values = new RelaxedPropertyResolver
                    (environment, "datasource").getSubProperties(".");
            dataSourcePropertyValues = new MutablePropertyValues(values);
        }
        dataBinder.bind(dataSourcePropertyValues);
    }

    /**
     * 注册数据源 bean
     * @param importingClassMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        // 将主数据源添加到更多数据源中
        targetDataSources.put("dataSource", defaultDataSource);
        // 添加更多数据源
        targetDataSources.putAll(dynamicDataSources);

        // 创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("dataSource", beanDefinition);

        logger.info("=====多数据源注册成功=====");
    }
}
