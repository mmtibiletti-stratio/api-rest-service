package com.santander.serenity.security.service.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ServiceCfg {

    private static final Logger logger = LoggerFactory.getLogger(ServiceCfg.class);

    /**
     * URL Path Mapping.
     */
    @Value("${cors.mapping}")
    private String corsMapping;

    /**
     * Allowed origin domains URLs.
     */
    @Value("${cors.allowedOrigins}")
    private String[] corsAllowedOrigins;

    /**
     * HTTP Methods allowed.
     */
    @Value("${cors.allowedMethods}")
    private String[] corsAllowedMethods;

    /**
     * Max age the results of a preflight request can be cached (In seconds).
     */
    @Value("${cors.maxAge}")
    private int corsMaxAge;

    /**
     * Bean to configure CORS. If it's enabled in configuration, the CORS functionality is created.
     * @return WebMvcConfigurer Bean with the CORS functionality configured.
     */
    @Bean
    @ConditionalOnProperty(name = "cors.enabled", havingValue = "true", matchIfMissing = true)
    public WebMvcConfigurer corsConfigurer() {
        logger.debug("[SERVER-CFG] Creating CORS configuration with values (mapping='{}', allowedOrigins='{}', allowedMethods='{}', maxAge='{}')",
                corsMapping, corsAllowedOrigins, corsAllowedMethods, corsMaxAge);

        WebMvcConfigurerAdapter webMvcConfigurerAdapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(corsMapping)
                        .allowedOrigins(corsAllowedOrigins)
                        .allowedMethods(corsAllowedMethods)
                        .maxAge(corsMaxAge);
            }
        };
        logger.debug("[SERVER-CFG] CORS configuration created");
        return webMvcConfigurerAdapter;
    }
}