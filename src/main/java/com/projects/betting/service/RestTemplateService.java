package com.projects.betting.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RestTemplateService {
        private final String apiUrl = "https://jsonplaceholder.typicode.com";

        RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> getUserInfo() {
        return restTemplate.getForEntity(apiUrl + "posts/1", String.class);
    }
}
