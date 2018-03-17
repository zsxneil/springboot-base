package com.my.springboot.config;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

//@Component
public class TransactionConfig3 {


//    @Bean
    public PlatformTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

//    @Autowired
    private PlatformTransactionManager transactionManager;

//    @Bean
    public TransactionInterceptor transactionInterceptor() {
        Properties attributes = new Properties();
        attributes.setProperty("get*", "PROPAGATION_REQUIRED");
        attributes.setProperty("add*", "PROPAGATION_REQUIRED");
        attributes.setProperty("update*", "PROPAGATION_REQUIRED");
        attributes.setProperty("delete*", "PROPAGATION_REQUIRED");
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, attributes);
        System.out.println("/***************************************");
        return txAdvice;
    }


//    @Bean
    public AspectJExpressionPointcut aspectJExpressionPointcut(){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        String transactionExecution = "execution(public * com.my.springboot.mybatis.service.impl.*.*(..))";
        pointcut.setExpression(transactionExecution);
        return pointcut;
    }

//    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(){
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(aspectJExpressionPointcut());
        advisor.setAdvice(transactionInterceptor());
        return advisor;
    }
}
