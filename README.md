# Spring & Hibernate ORM java configuration

## Introduction

I always copy and paste the bean configurations for SessionFactory, EntityManagerFactory, PlatformTransactionManager and CacheManager. Because It's the first step start to the project using ORM.
So I decided to change it.

## Usage - JPA, EntityManagerFactory

* add package name that has entities.
* add properties file included username, password, dialect, driverName, minConnection and maxConnection.
* add @EnableOrm annotation to your domain java configuration.


```properties
connect.driver=com.mysql.jdbc.Driver
connect.url=jdbc:mysql://localhost/bookstore
connect.username=root
connect.password=qwer12#$
connect.min=5
connect.max=20
hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
```


```java
@EnableOrm(enableCache = true, framework = OrmFramework.JPA, hbmToDdl = HbmToDdl.CREATE,
        packagesToScan = "me.xyzlast.test.entities", showSql = true)
@Configuration
@PropertySource(value = {"classpath:dbconnect.properties"})
@EnableTransactionManagement
@EnableCaching
public class TestConfigurationForJpa extends AbstractEhCacheConfigurer {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configHolder = new PropertySourcesPlaceholderConfigurer();
        Properties properties = new Properties();
        properties.setProperty("org.jboss.logging.provier", "slf4j");
        configHolder.setProperties(properties);
        return configHolder;
    }
}
```
## Usage - Hibernate, SessionFactory
```java
@Configuration
@EnableOrm(showSql = true, packagesToScan = "me.xyzlast.test.entities",
        hbmToDdl = HbmToDdl.CREATE_DROP, framework = OrmFramework.HIBERNATE, enableCache = false)
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
```
