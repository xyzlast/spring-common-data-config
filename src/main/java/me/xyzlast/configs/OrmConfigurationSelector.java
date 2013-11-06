package me.xyzlast.configs;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ykyoon
 * Date: 11/7/13
 * Time: 1:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class OrmConfigurationSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        final Map<String,Object> annotationAttributes = importingClassMetadata
                .getAnnotationAttributes(EnableOrm.class.getName());
        OrmFramework framework = (OrmFramework) annotationAttributes.get("framework");
        if(OrmFramework.Jpa == framework) {
            return new String[] { OrmJpaConfiguration.class.getName() };
        } else {
            return new String[] { OrmHibernateConfiguration.class.getName() };
        }
    }
}
