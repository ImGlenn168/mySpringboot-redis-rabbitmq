package com.java.myspringbootdemo02.App.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringUtils implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(SpringUtils.class);

    public static String applicationName = null;
    public static String applicationAbbr = null;

    private static ApplicationContext ctx = null;

    public static Object getBean(String beanName) {
        if (ctx != null) {
            try {
                return ctx.getBean(beanName);
            } catch (Exception e) {
                logger.warn("获取Bean失败！ beanName: " + beanName, e);
                return null;
            }
        }

        return null;
    }

    public static <T> T getBean(String beanName, Class<T> requiredType) {
        T result = null;
        if (ctx != null) {
            try {
                result = ctx.getBean(beanName, requiredType);
            } catch (Exception e) {
                logger.warn("获取Bean失败！ beanName: " + beanName, e);
            }
        }

        return result;
    }

    public static <T> T getBean(Class<T> requiredType) {
        T result = null;
        if (ctx != null) {
            try {
                result = ctx.getBean(requiredType);
            } catch (Exception e) {
                logger.warn("获取Bean失败！", e);
            }
        }

        return result;
    }

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationName = applicationContext.getId();
        ctx = applicationContext;
    }
}
