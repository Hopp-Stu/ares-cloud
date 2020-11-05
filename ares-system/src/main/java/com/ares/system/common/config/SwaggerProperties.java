package com.ares.system.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: yy
 * @date: 2020/09/15
 * @see: com.ares.core.common.config SwaggerProperties.java
 **/
@Data
@Component
@ConfigurationProperties(prefix = "ares.swagger")
public class SwaggerProperties {

    private boolean enable;

    @Value("${ares.swagger.base.package}")
    private String basePackage;

    @Value("${ares.swagger.contact.name}")
    private String contactName;

    @Value("${ares.swagger.contact.email}")
    private String contactEmail;

    @Value("${ares.swagger.contact.url}")
    private String contactUrl;

    private String description;

    private String title;

    private String url;

    private String version;

    @NestedConfigurationProperty
    private List<ParameterConfig> parameterConfig;

    @Data
    public static class ParameterConfig{
        private String name;
        private String description;
        private String type = "header";
        private String dateType = "String";
        private boolean required;
        private String defaultValue;
    }
}
