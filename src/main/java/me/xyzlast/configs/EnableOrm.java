package me.xyzlast.configs;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: ykyoon
 * Date: 11/7/13
 * Time: 1:28 AM
 * To change this template use File | Settings | File Templates.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(OrmConfigurationSelector.class)
public @interface EnableOrm {
    String[] packagesToScan() default "";
    boolean enableCache() default false;
    boolean showSql() default false;
    OrmFramework framework() default OrmFramework.Jpa;
    HbmToDdl hbmToDdl() default HbmToDdl.NO_ACTION;
}
