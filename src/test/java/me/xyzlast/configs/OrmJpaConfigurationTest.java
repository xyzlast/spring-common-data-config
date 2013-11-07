/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package me.xyzlast.configs;

/**
 * Created with IntelliJ IDEA.
 * User: ykyoon
 * Date: 11/7/13
 * Time: 2:12 AM
 * To change this template use File | Settings | File Templates.
 */


import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

import me.xyzlast.test.entities.Book;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfigurationForJpa.class })
public class OrmJpaConfigurationTest {
    @Autowired
    private ApplicationContext context;

    @Test
    public void getDataSource() {
        DataSource dataSource = context.getBean(DataSource.class);
        assertThat(dataSource, is(not(nullValue())));
    }

    @Test
    public void getEntityManagerFactory() {
        EntityManagerFactory entityManagerFactory = context.getBean(EntityManagerFactory.class);
        assertThat(entityManagerFactory, is(not(nullValue())));
    }

    @Test
    public void getTransactionManager() {
        PlatformTransactionManager transactionManager = context.getBean(PlatformTransactionManager.class);
        assertThat(transactionManager, is(not(nullValue())));
    }

    @Test
    public void getBooksData() {
        EntityManagerFactory entityManagerFactory = context.getBean(EntityManagerFactory.class);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        assertThat(entityManager, is(not(nullValue())));
        Book book = entityManager.find(Book.class, 0L);
        entityManager.close();
    }

    @Test
    public void getCacheManager() {
        CacheManager cacheManager = context.getBean(CacheManager.class);
        assertThat(cacheManager, is(not(nullValue())));
    }
}
