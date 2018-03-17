package com.my.springboot.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.*;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class TransactionConfig {

    /*@Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/

    @Bean
    public TransactionInterceptor transactionInterceptor(DataSourceTransactionManager transactionManager) {

        NameMatchTransactionAttributeSource tas = new NameMatchTransactionAttributeSource();

        /*只读事务，不做更新操作*/
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED );
        /*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED,
                Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setTimeout(60);


        /*Properties defaultTransactionProps = new Properties();
        defaultTransactionProps.setProperty("select*", "PROPAGATION_NOT_SUPPORTED,readOnly");
        defaultTransactionProps.setProperty("query*", "PROPAGATION_NOT_SUPPORTED,readOnly");
        defaultTransactionProps.setProperty("find*", "PROPAGATION_NOT_SUPPORTED,readOnly");
        defaultTransactionProps.setProperty("get*", "PROPAGATION_NOT_SUPPORTED,readOnly");
        defaultTransactionProps.setProperty("list*", "PROPAGATION_NOT_SUPPORTED,readOnly");
        defaultTransactionProps.setProperty("executeQueryForlist*", "PROPAGATION_NOT_SUPPORTED");
        defaultTransactionProps.setProperty("*NoTx", "PROPAGATION_NOT_SUPPORTED");
        defaultTransactionProps.setProperty("*", "PROPAGATION_REQUIRED,-Exception");
        tas.setProperties(defaultTransactionProps);*/

        Map<String, TransactionAttribute> txMap = new HashMap<>();
        txMap.put("add*", requiredTx);
        txMap.put("save*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("get*", readOnlyTx);
        txMap.put("query*", readOnlyTx);
        txMap.put("select*", readOnlyTx);
        txMap.put("list*", readOnlyTx);
        txMap.put("*", readOnlyTx);
        tas.setNameMap( txMap );
        System.out.println("**************************************************************************");
        //配置组合式事务，包括声明式和注解式。默认先使用注解，注解没找到再使用声明式
        return new TransactionInterceptor(transactionManager,
                new CompositeTransactionAttributeSource(new TransactionAttributeSource[]{new AnnotationTransactionAttributeSource(), tas}));

    }

    @Bean
    public Advisor defaultPointcutAdvisor(TransactionInterceptor transactionInterceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        //这里的表达式根据业务实际情况调整
        pointcut.setExpression("execution(public * com.my.springboot.mybatis.service.impl.*.*(..))");
        return new DefaultPointcutAdvisor(pointcut, transactionInterceptor);
    }

}
