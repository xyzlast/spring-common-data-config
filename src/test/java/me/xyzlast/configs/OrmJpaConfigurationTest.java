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
}
