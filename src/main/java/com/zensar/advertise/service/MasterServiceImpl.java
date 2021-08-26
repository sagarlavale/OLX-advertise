package com.zensar.advertise.service;

import com.zensar.advertise.dto.CategoryDto;
import com.zensar.advertise.dto.CategoryListDto;
import com.zensar.advertise.dto.StatusDto;
import com.zensar.advertise.dto.StatusListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MasterServiceImpl implements MasterService{

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

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

    public CategoryDto getCategory(Integer id) {
        String categoryUrl = "category/";

        RestTemplate restTemplate = new RestTemplate();

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

    @Override
    public CategoryListDto getCategories() {
        String categoryUrl = "category/";

        RestTemplate restTemplate = new RestTemplate();

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
    public StatusListDto getStatuses() {
        String statusUrl = "status/";

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
}
