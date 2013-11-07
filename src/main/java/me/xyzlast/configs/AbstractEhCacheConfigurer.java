/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package me.xyzlast.configs;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

/**
 * Created with IntelliJ IDEA.
 * User: ykyoon
 * Date: 11/7/13
 * Time: 11:08 AM
 * To change this template use File | Settings | File Templates.
 */

public abstract class AbstractEhCacheConfigurer {
    @Bean(name = "ehcache")
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() throws Exception {
        EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
        bean.setShared(Boolean.TRUE);
        bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        bean.afterPropertiesSet();

        return bean;
    }

    @Bean(name = "cacheManager")
    public EhCacheCacheManager cacheManager() throws Exception {
        EhCacheCacheManager ehCacheManager = new EhCacheCacheManager();
        ehCacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
        return ehCacheManager;
    }
}
