package com.zensar.advertise.controller;

import com.zensar.advertise.dto.AdvertiseRequestDto;
import com.zensar.advertise.service.AdvertiseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/ad")
@CrossOrigin("*")
public class AdvertiseController {
	
	@Autowired
	AdvertiseService advertiseService;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Add New Ad", notes =  "This Service Creates New Ad on OLX Application")
	@ResponseBody
	public ResponseEntity<?> post(@RequestHeader("Authorization") String token, @RequestBody AdvertiseRequestDto requestDto) {
		return advertiseService.post(token,requestDto);
	}
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Update Ad", notes =  "This Service Updates Already Existing Ad on OLX Application")
	@ResponseBody
	public ResponseEntity<?> put(@RequestHeader("Authorization") String token,@RequestBody AdvertiseRequestDto requestDto) {
		return advertiseService.put(token,requestDto);
	}
	
	@GetMapping(value = "/user" , produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Get All Ads for a LoggedIn User", notes =  "This Service Returns List of all Ads Created By a User")
	@ResponseBody
	public ResponseEntity<?> get(@RequestHeader("Authorization") String token) {
		return advertiseService.get(token);
		
	}
	
	@GetMapping(value = "/user/{postId}" , produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Get Ad By ID", notes =  "This Service Returns a Particular Ad by Id Created by a User on OLX Application")
	@ResponseBody
	public ResponseEntity<?> getUserAd(@RequestHeader("Authorization") String token,@ApiParam(value = "Post Id" ,required = true) @PathVariable Integer postId) {
		return advertiseService.getUserAd(token,postId);
	}
	
	@GetMapping(value = "/{postId}" , produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Get Ad By ID", notes =  "This Service Returns a Particular Ad by Id from OLX Application")
	@ResponseBody
	public ResponseEntity<?> get(@RequestHeader("Authorization") String token,@ApiParam(value = "Post Id" ,required = true) @PathVariable Integer postId) {
		return advertiseService.get(token,postId);
	}
	
	@GetMapping(value = "/search/{searchText}" , produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Search", notes =  "This Service Returns List of Ads based on Search Text from OLX Application")
	@ResponseBody
	public ResponseEntity<?> search(@ApiParam(value = "Search Criteria", required = true) @PathVariable String searchText) {
		return advertiseService.search(searchText);
	}
	
	@DeleteMapping(value = "/user/{postId}" , produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Delete Ad", notes =  "This Service Deletes the Ad based on ID from OLX Application")
	@ResponseBody
	public ResponseEntity<?> delete(@RequestHeader("Authorization") String token,@ApiParam(value = "Post ID", required = true)@PathVariable Integer postId) {
		return advertiseService.delete(token,postId);
	}

	@GetMapping(value = "/search/filter" ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Search By Criteria", notes =  "This Service Returns List of Ads based on Particular Search Criteria from OLX Application")
	@ResponseBody
	public ResponseEntity<?> findAll( @RequestParam(required = false) String title,
									  @RequestParam(required = false) String postedBy,
									  @RequestParam(required = false) Integer category,
									  @RequestParam(required = false) Integer status,
									  @RequestParam(required = false) Double price,
									  @RequestParam(required = false) String dateCondition,
									  @RequestParam(required = false) String onDate,
									  @RequestParam(required = false) String fromDate,
									  @RequestParam(required = false) String toDate,
									  @RequestParam(required = false) String sortBy,
									  @RequestParam(required = false) String order,
									  @RequestParam(defaultValue = "0") int page,
									  @RequestParam(defaultValue = "3") int size) {
		return advertiseService.findAll(page,size,title,postedBy,category,status,price,dateCondition,onDate,fromDate,toDate,sortBy,order);
	}

}
