package com.my.springboot.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.*;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//@Configuration
//@Aspect
//@Component
public class TransactionConfig {

//    @Bean
    public PlatformTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

//    @Bean
    public TransactionInterceptor transactionInterceptor(PlatformTransactionManager platformTransactionManager) {

        NameMatchTransactionAttributeSource tas = new NameMatchTransactionAttributeSource();




        Properties defaultTransactionProps = new Properties();
        defaultTransactionProps.setProperty("select*", "PROPAGATION_NOT_SUPPORTED,readOnly");
        defaultTransactionProps.setProperty("query*", "PROPAGATION_NOT_SUPPORTED,readOnly");
        defaultTransactionProps.setProperty("find*", "PROPAGATION_NOT_SUPPORTED,readOnly");
        defaultTransactionProps.setProperty("get*", "PROPAGATION_NOT_SUPPORTED,readOnly");
        defaultTransactionProps.setProperty("list*", "PROPAGATION_NOT_SUPPORTED,readOnly");
        defaultTransactionProps.setProperty("executeQueryForlist*", "PROPAGATION_NOT_SUPPORTED");
        defaultTransactionProps.setProperty("*NoTx", "PROPAGATION_NOT_SUPPORTED");
        defaultTransactionProps.setProperty("*", "PROPAGATION_REQUIRED,-Exception");
        tas.setProperties(defaultTransactionProps);


        System.out.println("**************************************************************************");
        //配置组合式事务，包括声明式和注解式。默认先使用注解，注解没找到再使用声明式
        return new TransactionInterceptor(platformTransactionManager,
                new CompositeTransactionAttributeSource(new TransactionAttributeSource[]{new AnnotationTransactionAttributeSource(), tas}));

    }

//    @Bean
    public Advisor defaultPointcutAdvisor(TransactionInterceptor transactionInterceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        //这里的表达式根据业务实际情况调整
        pointcut.setExpression("execution(public * com.my.springboot.mybatis.service.impl.*.*(..))");

        return new DefaultPointcutAdvisor(pointcut, transactionInterceptor);
    }

}
