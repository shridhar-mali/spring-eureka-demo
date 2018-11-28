package com.eureka.demo.eurekaclientb.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;

@RestController
public class EurekaClientController {

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Autowired
    RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/greeting")
    public String greeting() {
        return String.format("Hello from '%s'!", eurekaClient.getApplication(appName).getName());
    }

    @GetMapping("/client/info")
    public String info() {
        String response = restTemplate.exchange("http://CLIENTA/greeting",
                GET, null, String .class).getBody();

        return ("Response Received as " + response);
    }


}