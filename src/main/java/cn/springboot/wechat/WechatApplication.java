package cn.springboot.wechat;

import cn.springboot.wechat.config.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@MapperScan("cn.springboot.wechat.dao")
@Import(DynamicDataSourceRegister.class)
@EnableTransactionManagement
@SpringBootApplication
public class WechatApplication {

    private final static Logger logger = LoggerFactory.getLogger(WechatApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class, args);
        logger.info("=========Spring Boot Start Success=========");
    }
}
