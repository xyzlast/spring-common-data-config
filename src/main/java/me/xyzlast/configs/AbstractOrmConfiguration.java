package me.xyzlast.configs;

import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created with IntelliJ IDEA.
 * User: ykyoon
 * Date: 11/7/13
 * Time: 1:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractOrmConfiguration implements ImportAware {

    public static final String HIBERNATE_DIALECT = "hibernate.dialect";
    public static final String CONNECT_USERNAME = "connect.username";
    public static final String CONNECT_PASSWORD = "connect.password";
    public static final String CONNECT_DRIVER = "connect.driver";
    public static final String CONNECT_URL = "connect.url";

    public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    public static final String EH_CACHE_REGION_FACTORY = "org.hibernate.cache.ehcache.EhCacheRegionFactory";
    public static final String HIBERNATE_CACHE_USE_QUERY_CACHE = "hibernate.cache.use_query_cache";
    public static final String HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
    public static final String HIBERNATE_CACHE_REGION_FACTORY_CLASS = "hibernate.cache.region.factory_class";
    public static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    public static final String CREATE_DROP = "create-drop";
    public static final String CREATE = "create";

    // public static final int MAX_CONNECTION = 20;
    public static final int MAX_CONNECTION = 200;
    public static final int MIN_CONNECTION = 3;

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
