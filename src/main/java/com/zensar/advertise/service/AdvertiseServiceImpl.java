package com.zensar.advertise.service;

import com.zensar.advertise.dto.*;
import com.zensar.advertise.entity.Advertise;
import com.zensar.advertise.exception.AdvertiseNotFoundException;
import com.zensar.advertise.exception.CategoryNotFoundException;
import com.zensar.advertise.exception.InvalidTokenException;
import com.zensar.advertise.exception.StatusNotFoundException;
import com.zensar.advertise.mapper.AdvertiseMapper;
import com.zensar.advertise.repository.AdvertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AdvertiseServiceImpl implements AdvertiseService{

	@Autowired
	AdvertiseRepository advertiseRepository;

	@Autowired
	AdvertiseMapper advertiseMapper;

	@Autowired
	MasterService masterService;

	@Autowired
	UserServiceImpl userService;

	@Override
	public ResponseEntity<?> post(String token, AdvertiseRequestDto requestDto) {
		UserDto userDto;
		CategoryDto categoryDto;
		StatusDto statusDto;
		try{
			userDto = userService.getUserInfo(token);
		}
		catch (HttpClientErrorException e)
		{
			throw new InvalidTokenException(token);
		}
		Advertise advertise = advertiseMapper.toAdvertise(requestDto);

		advertise.setCategory(requestDto.getCategoryId());

		advertise.setStatus(requestDto.getStatusId());

		advertise.setCreatedById(userDto.getId());
		advertise.setCreatedBy(userDto.getFirstName()+userDto.getLastName());
		advertise.setUpdatedById(userDto.getId());
		advertise.setUpdatedBy(userDto.getFirstName()+userDto.getLastName());

		AdvertiseResponseDto advertiseResponseDto = advertiseMapper.toAdvertiseResponseDto(advertiseRepository.save(advertise));

		try {
			categoryDto = masterService.getCategory(requestDto.getCategoryId());
		}
		catch (HttpClientErrorException e)
		{
			throw new CategoryNotFoundException(requestDto.getCategoryId());
		}

		try {
			statusDto = masterService.getStatus(requestDto.getStatusId());
		}
		catch (HttpClientErrorException e)
		{
			throw new StatusNotFoundException(requestDto.getStatusId());
		}
		advertiseResponseDto.setCategory(categoryDto.getCategory());
		advertiseResponseDto.setStatus(statusDto.getStatus());

		return ResponseEntity.ok(advertiseResponseDto) ;
	}

	@Override
	public ResponseEntity<?> put(String token, AdvertiseRequestDto requestDto) {

		UserDto userDto;
		CategoryDto categoryDto;
		StatusDto statusDto;
		try{
			userDto = userService.getUserInfo(token);
		}
		catch (HttpClientErrorException e)
		{
			throw new InvalidTokenException(token);
		}

		Advertise advertise = advertiseMapper.toAdvertise(requestDto);

		advertise.setUpdatedById(userDto.getId());
		advertise.setUpdatedBy(userDto.getFirstName()+userDto.getLastName());

		AdvertiseResponseDto advertiseResponseDto = advertiseMapper.toAdvertiseResponseDto(advertiseRepository.save(advertise));

		try {
			categoryDto = masterService.getCategory(requestDto.getCategoryId());
		}
		catch (HttpClientErrorException e)
		{
			throw new CategoryNotFoundException(requestDto.getCategoryId());
		}

		try {
			statusDto = masterService.getStatus(requestDto.getStatusId());
		}
		catch (HttpClientErrorException e)
		{
			throw new StatusNotFoundException(requestDto.getStatusId());
		}
		advertiseResponseDto.setCategory(categoryDto.getCategory());
		advertiseResponseDto.setStatus(statusDto.getStatus());

		return ResponseEntity.ok(advertiseResponseDto) ;
	}

	@Override
	public ResponseEntity<?> get(String token) {
		UserDto userDto;
		CategoryDto categoryDto;
		StatusDto statusDto;
		try {
			userDto = userService.getUserInfo(token);
		}
		catch (HttpClientErrorException e)
		{
			throw new InvalidTokenException(token);
		}
		List<Advertise> advertiseList = advertiseRepository.findAllByUser(userDto.getId());

		AdvertiseListDto advertiseListDto1 = new AdvertiseListDto();

		for (Advertise dto : advertiseList)
		{
			try {
				categoryDto = masterService.getCategory(dto.getCategory());
			}
			catch (HttpClientErrorException e)
			{
				throw new CategoryNotFoundException(dto.getCategory());
			}
			try {
				statusDto = masterService.getStatus(dto.getStatus());
			}catch (HttpClientErrorException e)
			{
				throw new StatusNotFoundException(dto.getStatus());
			}

			AdvertiseResponseDto responseDto = advertiseMapper.toAdvertiseResponseDto(dto);
			responseDto.setCategory(categoryDto.getCategory());
			responseDto.setStatus(statusDto.getStatus());

			advertiseListDto1.getAdvertises().add(responseDto);
		}


		return ResponseEntity.ok(advertiseListDto1);
	}

	@Override
	public ResponseEntity<?> getUserAd(String token, Integer postId) {
		UserDto userDto;
		CategoryDto categoryDto;
		StatusDto statusDto;
		try {
			userDto = userService.getUserInfo(token);
		}
		catch (HttpClientErrorException e)
		{
			throw new InvalidTokenException(token);
		}
		Optional<Advertise> optionalAdvertise = advertiseRepository.findByUserAndPostId(userDto.getId(), postId);
		if (optionalAdvertise.isPresent())
		{
			Advertise advertise = optionalAdvertise.get();

			AdvertiseResponseDto responseDto = advertiseMapper.toAdvertiseResponseDto(advertise);

			try {
				categoryDto = masterService.getCategory(advertise.getCategory());
			}
			catch (HttpClientErrorException e)
			{
				throw new CategoryNotFoundException(advertise.getCategory());
			}
			try {
				statusDto = masterService.getStatus(advertise.getStatus());
			}catch (HttpClientErrorException e)
			{
				throw new StatusNotFoundException(advertise.getStatus());
			}

			responseDto.setStatus(statusDto.getStatus());
			responseDto.setCategory(categoryDto.getCategory());

			return ResponseEntity.ok(responseDto);
		}
		else
		{
			throw new AdvertiseNotFoundException(postId);
		}

	}

	@Override
	public ResponseEntity<?> delete(String token, Integer postId) {
		UserDto userDto;

		try {
			userDto = userService.getUserInfo(token);
		}
		catch (HttpClientErrorException e)
		{
			throw new InvalidTokenException(token);
		}
		Optional<Advertise> optionalAdvertise = advertiseRepository.findByUserAndPostId(userDto.getId(), postId);
		if (optionalAdvertise.isPresent())
		{
			advertiseRepository.delete(optionalAdvertise.get());
			return ResponseEntity.ok(true);
		}
		else
		{
			throw new AdvertiseNotFoundException(postId);
		}
	}

	@Override
	public ResponseEntity<?> get(String token, Integer postId) {
		Optional<Advertise> optional = advertiseRepository.findById(postId);
		CategoryDto categoryDto;
		StatusDto statusDto;
		if (optional.isPresent())
		{
			Advertise advertise = optional.get();
			AdvertiseResponseDto responseDto = advertiseMapper.toAdvertiseResponseDto(advertise);

			try {
				categoryDto = masterService.getCategory(advertise.getCategory());
			}
			catch (HttpClientErrorException e)
			{
				throw new CategoryNotFoundException(advertise.getCategory());
			}
			try {
				statusDto = masterService.getStatus(advertise.getStatus());
			}catch (HttpClientErrorException e)
			{
				throw new StatusNotFoundException(advertise.getStatus());
			}

			responseDto.setStatus(statusDto.getStatus());
			responseDto.setCategory(categoryDto.getCategory());

			return ResponseEntity.ok(responseDto);

		}
		else {
			throw new AdvertiseNotFoundException(postId);
		}

	}

	@Override
	public ResponseEntity<?> searchOnCriteria(String searchText) {
		AdvertiseListDto list = new AdvertiseListDto();
		CategoryDto categoryDto;
		StatusDto statusDto;
		List<Advertise> advertises = advertiseRepository.findAll();

		List<AdvertiseResponseDto> advertiseResponseListDto = new ArrayList<>();

		for (Advertise advertise : advertises)
		{
			AdvertiseResponseDto responseDto = advertiseMapper.toAdvertiseResponseDto(advertise);

			try {
				categoryDto = masterService.getCategory(advertise.getCategory());
			}
			catch (HttpClientErrorException e)
			{
				throw new CategoryNotFoundException(advertise.getCategory());
			}
			try {
				statusDto = masterService.getStatus(advertise.getStatus());
			}catch (HttpClientErrorException e)
			{
				throw new StatusNotFoundException(advertise.getStatus());
			}

			responseDto.setCategory(categoryDto.getCategory());
			responseDto.setStatus(statusDto.getStatus());

			advertiseResponseListDto.add(responseDto);
		}

		Predicate<AdvertiseResponseDto> f1 = x -> x.getStatus().equalsIgnoreCase(searchText);
		list.setAdvertises(advertiseResponseListDto.stream().filter(f1).collect(Collectors.toList()));
		return ResponseEntity.ok(list);
	}

	@Override
	public ResponseEntity<?> search(String searchText) {

		AdvertiseListDto list = new AdvertiseListDto();
		CategoryDto categoryDto;
		StatusDto statusDto;
		List<Advertise> advertises = advertiseRepository.findAll();

		List<AdvertiseResponseDto> advertiseResponseListDto = new ArrayList<>();

		for (Advertise advertise : advertises)
		{
			AdvertiseResponseDto responseDto = advertiseMapper.toAdvertiseResponseDto(advertise);

			try {
				categoryDto = masterService.getCategory(advertise.getCategory());
			}
			catch (HttpClientErrorException e)
			{
				throw new CategoryNotFoundException(advertise.getCategory());
			}
			try {
				statusDto = masterService.getStatus(advertise.getStatus());
			}catch (HttpClientErrorException e)
			{
				throw new StatusNotFoundException(advertise.getStatus());
			}

			responseDto.setCategory(categoryDto.getCategory());
			responseDto.setStatus(statusDto.getStatus());

			advertiseResponseListDto.add(responseDto);
		}
		Predicate<AdvertiseResponseDto> f1 = x -> x.getTitle().contains(searchText);
		Predicate<AdvertiseResponseDto> f2 = x -> x.getDescription().contains(searchText);
		Predicate<AdvertiseResponseDto> f3 = x -> x.getCategory().contains(searchText);
		
		list.setAdvertises(advertiseResponseListDto.stream().filter(f1.or(f2).or(f3)).collect(Collectors.toList()));
		return ResponseEntity.ok(list);
	}


	

}
