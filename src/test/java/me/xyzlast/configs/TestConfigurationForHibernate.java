package me.xyzlast.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: ykyoon
 * Date: 11/7/13
 * Time: 9:46 AM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableOrm(showSql = true, packagesToScan = "me.xyzlast.test.entities",
        hbmToDdl = HbmToDdl.CREATE_DROP, framework = OrmFramework.HIBERNATE, enableCache = true)
@PropertySource(value = {"classpath:dbconnect.properties"})
@EnableTransactionManagement
public class TestConfigurationForHibernate {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configHolder = new PropertySourcesPlaceholderConfigurer();
        Properties properties = new Properties();
        properties.setProperty("org.jboss.logging.provier", "slf4j");
        configHolder.setProperties(properties);
        return configHolder;
    }
}
