package com.krinal.lloyds.interview_coding_solution.configuration;

import com.krinal.lloyds.interview_coding_solution.configuration.properties.ApiDetailProperties;
import com.krinal.lloyds.interview_coding_solution.configuration.properties.LloydsProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfig {
    @Bean
    @ConfigurationProperties(prefix = "lloyds")
    public LloydsProperties lloydsProperties() {
        return new LloydsProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "lloyds.apis.open-banking.all-atms")
    public ApiDetailProperties allAtmsApiProperties() {
        return new ApiDetailProperties();
    }
}
