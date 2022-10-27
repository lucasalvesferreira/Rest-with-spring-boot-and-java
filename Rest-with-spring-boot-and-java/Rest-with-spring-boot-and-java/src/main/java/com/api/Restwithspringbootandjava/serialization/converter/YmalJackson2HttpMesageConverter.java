package com.api.Restwithspringbootandjava.serialization.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

public class YmalJackson2HttpMesageConverter extends AbstractJackson2HttpMessageConverter {

    public YmalJackson2HttpMesageConverter() {
        super(
                new YAMLMapper()
                    .setSerializationInclusion(
                            JsonInclude.Include.NON_NULL),
                            MediaType.parseMediaType("application/x-yaml")
                );
    }
}
