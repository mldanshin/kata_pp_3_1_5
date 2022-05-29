package com.example.kata_pp_3_1_5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserDao {
    private RestTemplate restTemplate;
    private String sessionId;
    private String code = "";

    @Autowired
    public UserDao(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getList() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<User[]> entity = new HttpEntity<User[]>(headers);
        ResponseEntity<User[]>  responseEntity = restTemplate.exchange(
                "http://94.198.50.185:7081/api/users",
                HttpMethod.GET,
                entity,
                User[].class);
        sessionId = responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        return Arrays.asList(responseEntity.getBody());
    }

    public void store(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionId);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> requestBody = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "http://94.198.50.185:7081/api/users",
                requestBody,
                String.class
        );
        code += responseEntity.getBody();
    }

    public void update(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionId);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> requestBody = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://94.198.50.185:7081/api/users",
                HttpMethod.PUT,
                requestBody,
                String.class
        );
        code += responseEntity.getBody();
    }

    public void delete(long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionId);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> requestBody = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://94.198.50.185:7081/api/users/" + id,
                HttpMethod.DELETE,
                requestBody,
                String.class
        );
        code += responseEntity.getBody();
    }

    public String getCode() {
        return code;
    }
}
