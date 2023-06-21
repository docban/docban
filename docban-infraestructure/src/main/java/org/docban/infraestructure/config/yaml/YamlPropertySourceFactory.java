package org.docban.infraestructure.config.yaml;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.util.Properties;

public class YamlPropertySourceFactory extends DefaultPropertySourceFactory {

    public PropertySource<?> createPropertySource(final String name, final EncodedResource resource) {
        final YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources( new Resource[]{ resource.getResource() });
        final Properties properties = factoryBean.getObject();
        return new PropertiesPropertySource( resource.getResource().getFilename(), properties );
    }
}