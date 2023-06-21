package org.docban.infraestructure.config;

import org.docban.infraestructure.config.yaml.YamlPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(
        value = { "classpath:application.yaml" },
        factory = YamlPropertySourceFactory.class
)
public class AppConfig {}

