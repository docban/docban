package org.docban.app.config;

import org.docban.app.config.yaml.YamlPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(
        value = { "classpath:application.yaml" },
        factory = YamlPropertySourceFactory.class
)
public class AppConfig {}

