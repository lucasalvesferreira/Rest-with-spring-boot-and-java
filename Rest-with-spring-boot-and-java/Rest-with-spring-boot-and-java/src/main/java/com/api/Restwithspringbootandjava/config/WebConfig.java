package com.api.Restwithspringbootandjava.config;

import com.api.Restwithspringbootandjava.serialization.converter.YmalJackson2HttpMesageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");

    @Value("${cors.originPatterns:default}")
    private String corsOriginPatterns = "";

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YmalJackson2HttpMesageConverter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowedOrigins = corsOriginPatterns.split(",");
        registry.addMapping("/**")
//                .allowedMethods("GET","POST","PUT")
                .allowedMethods("*")
                .allowedOrigins(allowedOrigins)
                .allowCredentials(true);
    }
//    Transforma via Header

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("jason",MediaType.APPLICATION_JSON)
                .mediaType("xml",MediaType.APPLICATION_XML)
                .mediaType("x-yaml",MEDIA_TYPE_APPLICATION_YML);
    }



    //    Transforma via url

//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.favorParameter(true)
//                .parameterName("mediaType").ignoreAcceptHeader(true)
//                .useRegisteredExtensionsOnly(false)
//                .defaultContentType(MediaType.APPLICATION_JSON)
//                    .mediaType("jason",MediaType.APPLICATION_JSON)
//                    .mediaType("xml",MediaType.APPLICATION_XML);
//}

}
