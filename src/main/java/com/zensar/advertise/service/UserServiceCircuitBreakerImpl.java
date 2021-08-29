package com.zensar.advertise.service;

import com.zensar.advertise.dto.UserDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceCircuitBreakerImpl implements UserService{

    /*@Autowired
    CircuitBreakerFactory circuitBreakerFactory;*/

    @Autowired
    RestTemplate restTemplate;

    @Override
    @CircuitBreaker(name = "USER_INFO" , fallbackMethod = "fallbackForGetUserInfo")
    public UserDto getUserInfo(String token) {

        String statusUrl = "/user/info";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);

        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<UserDto> responseEntity = restTemplate.exchange(
                USER_SERVICE_URL+ statusUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<UserDto>() {
                }
        );

        return responseEntity.getBody();
    }

   /* @Override
    public UserDto getUserInfo(String token) {
        String statusUrl = "/user/info";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);

        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("USER_INFO");
        ResponseEntity<UserDto> responseEntity = circuitBreaker.run(()
                ->this.restTemplate.exchange(
                USER_SERVICE_URL+ statusUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<UserDto>() {
                }),
                throwable -> fallbackForGetUserInfo(token,throwable));
        return responseEntity.getBody();
    }*/

    public UserDto fallbackForGetUserInfo(String token,Throwable throwable){
        System.out.println("Login Service Failed : "+ throwable);
        return null;
    }
}
