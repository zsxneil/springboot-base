package com.my.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

//@EnableTransactionManagement
@SpringBootApplication
@EnableAutoConfiguration(exclude={
		 //DataSourceAutoConfiguration.class,
		// HibernateJpaAutoConfiguration.class, //（如果使用Hibernate时，需要加）
		// DataSourceTransactionManagerAutoConfiguration.class,
//		TransactionInterceptor.class,
//		AspectJExpressionPointcutAdvisor.class,
//		TransactionAttributeSource.class
		})

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
