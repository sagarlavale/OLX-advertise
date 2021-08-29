package com.zensar.advertise.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @GetMapping(value = "/read-property")
    public String getDbUrl(){
        return this.dbUrl;
    }
}
