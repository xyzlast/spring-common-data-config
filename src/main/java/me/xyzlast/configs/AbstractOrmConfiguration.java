package me.xyzlast.configs;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: ykyoon
 * Date: 11/7/13
 * Time: 1:42 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractOrmConfiguration implements ImportAware {

    public static final String HIBERNATE_DIALECT = "hibernate.dialect";
    public static final String CONNECT_USERNAME = "connect.username";
    public static final String CONNECT_PASSWORD = "connect.password";
    public static final String CONNECT_DRIVER = "connect.driver";
    public static final String CONNECT_URL = "connect.url";
    public static final String CONNECT_MAX = "connect.max";
    public static final String CONNECT_MIN = "connect.min";

    public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    public static final String EH_CACHE_REGION_FACTORY = "org.hibernate.cache.ehcache.EhCacheRegionFactory";
    public static final String HIBERNATE_CACHE_USE_QUERY_CACHE = "hibernate.cache.use_query_cache";
    public static final String HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
    public static final String HIBERNATE_CACHE_REGION_FACTORY_CLASS = "hibernate.cache.region.factory_class";
    public static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    public static final String CREATE_DROP = "create-drop";
    public static final String CREATE = "create";
    private static final String HIBERNATE_EHCACHE_MANAGER_NAME = "net.sf.ehcache.cacheManagerName";

    private static final String HIBERNATE_CACHE_NAME = "me.xyzlast.configs.enableORM.cacheManager";

    protected boolean showSql;
    private boolean enableCache;
    protected String[] packagesToScan;
    private HbmToDdl hbmToDdl;

    @Autowired
    private Environment env;

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        final Map<String,Object> annotationAttributes =
                importMetadata.getAnnotationAttributes(EnableOrm.class.getName());
        packagesToScan = (String[]) annotationAttributes.get("packagesToScan");
        enableCache = (boolean) annotationAttributes.get("enableCache");
        packagesToScan = (String[]) annotationAttributes.get("packagesToScan");
        showSql = (boolean) annotationAttributes.get("showSql");
        hbmToDdl = (HbmToDdl) annotationAttributes.get("hbmToDdl");
    }

    @Bean
    public javax.sql.DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setUsername(env.getProperty(CONNECT_USERNAME));
        dataSource.setPassword(env.getProperty(CONNECT_PASSWORD));
        dataSource.setDriverClass(env.getProperty(CONNECT_DRIVER));
        dataSource.setJdbcUrl(env.getProperty(CONNECT_URL));

        int minConnection = Integer.parseInt(env.getProperty(CONNECT_MIN));
        int maxConnection = Integer.parseInt(env.getProperty(CONNECT_MAX));
        dataSource.setMaxConnectionsPerPartition(minConnection);
        dataSource.setMinConnectionsPerPartition(maxConnection);
        return dataSource;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public abstract PlatformTransactionManager transactionManager();

    protected Properties getHibernateProperties() {
        final Properties properties = new Properties();
        properties.put(HIBERNATE_DIALECT, env.getProperty(HIBERNATE_DIALECT));
        if (enableCache) {
            properties.put(HIBERNATE_CACHE_REGION_FACTORY_CLASS, EH_CACHE_REGION_FACTORY);
            properties.put(HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE, true);
            properties.put(HIBERNATE_CACHE_USE_QUERY_CACHE, true);
            properties.put(HIBERNATE_EHCACHE_MANAGER_NAME, HIBERNATE_CACHE_NAME);
        }
        switch (hbmToDdl) {
            case CREATE:
                properties.put(HIBERNATE_HBM2DDL_AUTO, CREATE);
                break;
            case CREATE_DROP:
                properties.put(HIBERNATE_HBM2DDL_AUTO, CREATE_DROP);
                break;
            case NO_ACTION:
            default:
                break;
        }
        return properties;
    }

}
