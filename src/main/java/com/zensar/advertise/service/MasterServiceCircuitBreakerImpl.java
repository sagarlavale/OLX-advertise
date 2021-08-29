package com.zensar.advertise.service;

import com.zensar.advertise.dto.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MasterServiceCircuitBreakerImpl implements MasterService{
    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    @CircuitBreaker(name = "STATUS" , fallbackMethod = "fallbackForGetStatus")
    public StatusDto getStatus(Integer id) {

        String statusUrl = "status/";

        HttpHeaders httpHeaders = new HttpHeaders();

        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<StatusDto> responseEntity = restTemplate.exchange(
                MASTER_SERVICE_URL + statusUrl +id,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<StatusDto>() {
                }
        );

        return responseEntity.getBody();
    }

    @Override
    @CircuitBreaker(name = "CATEGORY" , fallbackMethod = "fallbackForGetCategory")
    public CategoryDto getCategory(Integer id) {
        String categoryUrl = "category/";

        HttpHeaders httpHeaders = new HttpHeaders();

        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<CategoryDto> responseEntity = restTemplate.exchange(
                MASTER_SERVICE_URL + categoryUrl +id,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<CategoryDto>() {
                }
        );

        return responseEntity.getBody();
    }

    @CircuitBreaker(name = "ALL_CATEGORIES" , fallbackMethod = "fallbackForGetCategories")
    @Override
    public CategoryListDto getCategories() {
        String categoryUrl = "category";

        HttpHeaders httpHeaders = new HttpHeaders();

        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<CategoryListDto> responseEntity = restTemplate.exchange(
                MASTER_SERVICE_URL + categoryUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<CategoryListDto>() {
                }
        );

        return responseEntity.getBody();
    }

    @Override
    @CircuitBreaker(name = "ALL_STATUSES" , fallbackMethod = "fallbackForGetStatuses")
    public StatusListDto getStatuses() {
        String statusUrl = "status";

        HttpHeaders httpHeaders = new HttpHeaders();

        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<StatusListDto> responseEntity = restTemplate.exchange(
                MASTER_SERVICE_URL + statusUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<StatusListDto>() {
                }
        );

        return responseEntity.getBody();
    }

    public StatusDto fallbackForGetStatus(Integer id, Throwable throwable){
        System.out.println("Master Service Failed : "+ throwable);
        return null;
    }

    public CategoryDto fallbackForGetCategory(Integer id,Throwable throwable){
        System.out.println("Master Service Failed : "+ throwable);
        return null;
    }

    public CategoryListDto fallbackForGetCategories(Throwable throwable){
        System.out.println("Master Service Failed : "+ throwable);
        return null;
    }

    public StatusListDto fallbackForGetStatuses(Throwable throwable){
        System.out.println("Master Service Failed : "+ throwable);
        return null;
    }
}
