package me.xyzlast.configs;

/**
 * Created with IntelliJ IDEA.
 * User: ykyoon
 * Date: 11/7/13
 * Time: 9:46 AM
 * To change this template use File | Settings | File Templates.
 */
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfigurationForHibernate.class })
public class OrmHibernateConfigurationTest {
    @Autowired
    private ApplicationContext context;

    @Test
    public void getSessionFactory() {
        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        assertThat(sessionFactory, is(not(nullValue())));
    }

    @Test
    public void getTransactionManager() {
        PlatformTransactionManager transactionManager = context.getBean(PlatformTransactionManager.class);
        assertThat(transactionManager, is(not(nullValue())));
    }
}
