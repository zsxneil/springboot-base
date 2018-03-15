package com.my.springboot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DemoListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("demo listener 启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
