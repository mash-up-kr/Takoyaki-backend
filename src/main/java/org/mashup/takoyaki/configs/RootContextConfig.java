package org.mashup.takoyaki.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "org.mashup.takoyaki",
        excludeFilters =  {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
            value = { HibernateConfig.class, WebMvcConfig.class, MultipartConfig.class })
        })
@Import(value = { HibernateConfig.class, WebMvcConfig.class, MultipartConfig.class })
public class RootContextConfig {

}
