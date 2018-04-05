package com.zsxneil.bing.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.CompositeTransactionAttributeSource;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

/**
 * 尝试了这几个类，只有这个有效，其他几个为什么无效没搞清楚
 */
@Configuration
public class TransactionConfig {
    @Autowired
    private DataSourceTransactionManager transactionManager;

    // 创建事务通知

    @Bean(name = "txAdvice")
    public TransactionInterceptor getAdvisor() throws Exception {

        NameMatchTransactionAttributeSource tas = new NameMatchTransactionAttributeSource();

        Properties properties = new Properties();
        properties.setProperty("get*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("select*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("list*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("query*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("find*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("insert*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("*", "PROPAGATION_REQUIRED,-Exception, readOnly");

        tas.setProperties(properties);
        //TransactionInterceptor tsi = new TransactionInterceptor(transactionManager,properties);
        System.out.println("*******************************************************");
        //配置组合式事务，包括声明式和注解式。默认先使用注解，注解没找到再使用声明式
        TransactionInterceptor tsi = new TransactionInterceptor(transactionManager,
                new CompositeTransactionAttributeSource(new TransactionAttributeSource[]{new AnnotationTransactionAttributeSource(), tas}));
        return tsi;

    }

    @Bean
    public BeanNameAutoProxyCreator txProxy() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setInterceptorNames("txAdvice");
//        creator.setBeanNames("*Service", "*ServiceImpl");
        creator.setBeanNames("*ServiceImpl");
        creator.setProxyTargetClass(true);
        return creator;
    }
}
