package cn.springboot.wechat.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * Created with IntelliJ IDEA.
 * Description: 切换数据源
 * User: dfly
 * Date: 2017-08-18
 * Time: 9:12
 */


@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {


    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Pointcut("execution(public * cn.springboot.wechat.serviceImpl.TreeServiceImpl.*(..))")
    public void log() { }

    /**
     * 根据@TargetDataSource的属性值设置不同的dataSourceKey,以供DynamicDataSource
     *
     * @param point
     * @throws Throwable
     */

    @Before("log()")
    public void changeDataSource(JoinPoint point) throws Throwable {
        Object[] ds = point.getArgs();
        DynamicDataSource.setDataSourceType(String.valueOf(ds[0]));
    }


    /**
     * 方法执行完毕后清楚数据源
     *
     * @param point
     */

    @After("log()")
    public void restoreDataSource(JoinPoint point) {
        DynamicDataSource.clearDataSourceType();
    }
}


