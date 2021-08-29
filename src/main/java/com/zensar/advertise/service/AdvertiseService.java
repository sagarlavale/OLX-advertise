package com.zensar.advertise.service;

import com.zensar.advertise.dto.AdvertiseRequestDto;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;
import java.util.Date;

public interface AdvertiseService {
	
	ResponseEntity<?> post(String token, AdvertiseRequestDto requestDto);

	ResponseEntity<?> put(String token,AdvertiseRequestDto requestDto);

	ResponseEntity<?> get(String token);

	ResponseEntity<?> getUserAd(String token, Integer postId);

	ResponseEntity<?> get(String token, Integer postId);

	ResponseEntity<?> search(String searchText);

	ResponseEntity<?> delete(String token, Integer postId);

	ResponseEntity<?> findAll(int page, int size, String title, String createdBy, Integer category, Integer status, Double price,String dateCondition, String onDate,String fromDate, String toDate,String sortBy, String order);
}
