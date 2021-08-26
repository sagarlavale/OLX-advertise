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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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

		Advertise advertise = advertiseMapper.toAdvertise(requestDto);

		advertise.setCategory(requestDto.getCategoryId());

		advertise.setStatus(requestDto.getStatusId());

		advertise.setCreatedById(userDto.getId());
		advertise.setCreatedBy(userDto.getFirstName()+userDto.getLastName());
		advertise.setUpdatedById(userDto.getId());
		advertise.setUpdatedBy(userDto.getFirstName()+userDto.getLastName());

		AdvertiseResponseDto advertiseResponseDto = advertiseMapper.toAdvertiseResponseDto(advertiseRepository.save(advertise));

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


		Advertise advertise = advertiseMapper.toAdvertise(requestDto);

		advertise.setUpdatedById(userDto.getId());
		advertise.setUpdatedBy(userDto.getFirstName()+userDto.getLastName());

		AdvertiseResponseDto advertiseResponseDto = advertiseMapper.toAdvertiseResponseDto(advertiseRepository.save(advertise));

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
	public ResponseEntity<?> search(String searchText) {

		AdvertiseListDto list = new AdvertiseListDto();
		List<Advertise> advertises = advertiseRepository.findAll();

		List<AdvertiseResponseDto> advertiseResponseListDto = new ArrayList<>();

		set(advertises, advertiseResponseListDto);
		Predicate<AdvertiseResponseDto> f1 = x -> x.getTitle().contains(searchText);
		Predicate<AdvertiseResponseDto> f2 = x -> x.getDescription().contains(searchText);
		Predicate<AdvertiseResponseDto> f3 = x -> x.getCategory().contains(searchText);
		Predicate<AdvertiseResponseDto> f4 = x -> x.getStatus().contains(searchText);
		Predicate<AdvertiseResponseDto> f5 = x -> x.getUserName().contains(searchText);

		
		list.setAdvertises(advertiseResponseListDto.stream().filter(f1.or(f2).or(f3).or(f4).or(f5)).collect(Collectors.toList()));
		return ResponseEntity.ok(list);
	}

	private void set(List<Advertise> advertises, List<AdvertiseResponseDto> advertiseResponseListDto) {
		CategoryListDto categories = masterService.getCategories();

		StatusListDto statuses = masterService.getStatuses();

		for (Advertise advertise : advertises)
		{
			AdvertiseResponseDto responseDto = advertiseMapper.toAdvertiseResponseDto(advertise);

			CategoryDto categoryDto1 = categories.getCategories().stream().filter(categoryDto -> categoryDto.getId().equals(advertise.getCategory())).findAny().orElse(null);
			StatusDto statusDto1 = statuses.getStatusList().stream().filter(statusDto -> statusDto.getId().equals(advertise.getStatus())).findAny().orElse(null);

			responseDto.setCategory(categoryDto1.getCategory());
			responseDto.setStatus(statusDto1.getStatus());

			advertiseResponseListDto.add(responseDto);
		}
	}

	@Override
	public ResponseEntity<?> findAll(int page, int size, String title, String createdBy, Integer category, Integer status, Double price, String createdDate,String sortBy, String order) {

		List<Advertise> advertiseList;
		Pageable paging = PageRequest.of(page,size);

		Advertise filter = new Advertise();

		filter.setCategory(category);
		filter.setStatus(status);
		filter.setTitle(title);
		filter.setPrice(price);
		filter.setCreatedBy(createdBy);
		try {
			filter.setCreatedAt(getDate(createdDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Specification<Advertise> advertiseSpecification = new AdvertiseSpecification(filter);

		Page<Advertise> advertisePage = advertiseRepository.findAll(advertiseSpecification,paging);

		advertiseList = advertisePage.toList();

		if (sortBy !=null)
			advertiseList = sort(advertiseList,sortBy);
		if (order !=null && order.equals("des"))
			Collections.reverse(advertiseList);

		List<AdvertiseResponseDto> advertiseResponseListDto = new ArrayList<>();

		set(advertiseList, advertiseResponseListDto);


		return ResponseEntity.ok(advertiseResponseListDto);
	}

	public Date getDate(String string) throws ParseException {
		if (string == null)
		{
			return new Date();
		}
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.parse(string);
	}
	
	public List<Advertise> sort(List<Advertise> list, String sortBy){
		switch (sortBy){
			case "category" :
				list = list.stream().sorted(Comparator.comparingInt(Advertise::getCategory)).collect(Collectors.toList());
				break;
			case "postedBy" :
				list = list.stream().sorted(Comparator.comparing(Advertise::getCreatedBy)).collect(Collectors.toList());
				break;
			case "title" :
				list = list.stream().sorted(Comparator.comparing(Advertise::getTitle)).collect(Collectors.toList());
				break;
			case "status" :
				list = list.stream().sorted(Comparator.comparingInt(Advertise::getStatus)).collect(Collectors.toList());
				break;
			case "price" :
				list = list.stream().sorted(Comparator.comparingDouble(Advertise::getPrice)).collect(Collectors.toList());
				break;
			case "createdDate" :
				list = list.stream().sorted(Comparator.comparing(Advertise::getCreatedAt)).collect(Collectors.toList());
				break;
			default:
		}
		return list;
	}
}
