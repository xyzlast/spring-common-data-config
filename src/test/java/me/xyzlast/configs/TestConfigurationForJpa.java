package me.xyzlast.configs;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: ykyoon
 * Date: 11/7/13
 * Time: 2:12 AM
 * To change this template use File | Settings | File Templates.
 */
@EnableOrm(enableCache = true, framework = OrmFramework.JPA, hbmToDdl = HbmToDdl.CREATE,
        packagesToScan = "me.xyzlast.test.entities", showSql = true)
@Configuration
@PropertySource(value = {"classpath:dbconnect.properties"})
@EnableTransactionManagement
@EnableCaching()
@Import(EhCacheConfigurer.class)
public class TestConfigurationForJpa {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configHolder = new PropertySourcesPlaceholderConfigurer();
        Properties properties = new Properties();
        properties.setProperty("org.jboss.logging.provier", "slf4j");
        configHolder.setProperties(properties);
        return configHolder;
    }
}
