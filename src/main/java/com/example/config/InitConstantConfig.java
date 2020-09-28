package com.example.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class InitConstantConfig {

    @Value("${security.anonymousURL}")
    private String anonymousURL;

    public String[] getStringArray(){
        return anonymousURL.substring(1, anonymousURL.length()).split(",");
     }
}
