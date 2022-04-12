package se.kimler.demo.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ConfigurableHttpMethods;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Component
public class SpringDataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.getExposureConfiguration()
                .withAssociationExposure(SpringDataRestConfig::configureHttpMethods)
                .withCollectionExposure(SpringDataRestConfig::configureHttpMethods)
                .withItemExposure(SpringDataRestConfig::configureHttpMethods);
    }

    private static ConfigurableHttpMethods configureHttpMethods(Object metdata, ConfigurableHttpMethods httpMethods) {
        httpMethods.disable(HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE);
        return httpMethods;
    }
}
