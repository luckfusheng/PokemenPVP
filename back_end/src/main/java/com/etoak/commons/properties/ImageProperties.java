package com.etoak.commons.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@ConfigurationProperties(prefix = "image")
@Component
@Data
public class ImageProperties {
    private String dir;
    private String prefix;
    private List<String> types;
}
