package com.zensar.advertise.service;

import com.zensar.advertise.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;

//@Service
public class UserServiceImpl implements UserService{

    @Autowired
    RestTemplate restTemplate;


    @Override
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
}
