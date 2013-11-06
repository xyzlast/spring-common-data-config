package me.xyzlast.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: ykyoon
 * Date: 11/7/13
 * Time: 1:58 AM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableTransactionManagement
public class OrmHibernateConfiguration extends AbstractOrmConfiguration {
    @Override
    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setDataSource(dataSource());
        transactionManager.setSessionFactory(sessionFactoryBean().getObject());
        return transactionManager;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        Properties properties = getHibernateProperties();
        if (showSql) {
            properties.put(HIBERNATE_SHOW_SQL, "true");
        }
        sessionFactory.setHibernateProperties(properties);
        sessionFactory.setPackagesToScan(packagesToScan);
        return sessionFactory;
    }
}
