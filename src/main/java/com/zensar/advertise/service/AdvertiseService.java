package com.zensar.advertise.service;

import com.zensar.advertise.dto.AdvertiseRequestDto;
import org.springframework.http.ResponseEntity;

public interface AdvertiseService {
	
	ResponseEntity<?> post(String token, AdvertiseRequestDto requestDto);

	ResponseEntity<?> put(String token,AdvertiseRequestDto requestDto);

	ResponseEntity<?> get(String token);

	ResponseEntity<?> getUserAd(String token, Integer postId);

	ResponseEntity<?> get(String token, Integer postId);

	ResponseEntity<?> searchOnCriteria(String searchText);

	ResponseEntity<?> search(String searchText);

	ResponseEntity<?> delete(String token, Integer postId);
}
