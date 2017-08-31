package cn.springboot.wechat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: dfly
 * Date: 2017-08-17
 * Time: 16:35
 */
public class GlobalDataConfiguration {

    private static Logger logger = LoggerFactory.getLogger(GlobalDataConfiguration.class);

    @Bean(name = "primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        logger.info("===== primaryDataSource Init =====");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        logger.info("===== secondaryDataSource Init =====");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "shanhongDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.shanhong")
    public DataSource shanhongDataSource() {
        logger.info("===== shanhongDataSource Init =====");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "zhslDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.zhsl")
    public DataSource zhslDataSource() {
        logger.info("===== zhslDataSource Init =====");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "testDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSource testDataSource() {
        logger.info("===== testDataSource Init =====");
        return DataSourceBuilder.create().build();
    }
}
