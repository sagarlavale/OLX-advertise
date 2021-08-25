package com.zensar.advertise.dto;


import java.util.Date;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Information of the Advertisement which is Created/Updated on OLX Application")
public class AdvertiseResponseDto {
	@ApiModelProperty(value = "ID of Advertisement")
	private Integer id;
	
	@ApiModelProperty(value = "Title of Advertisement")
	private String title;
	
	@ApiModelProperty(value = "Price of Product")
	private Integer price;
	
	@ApiModelProperty(value = "Category under which the Product is Listed")
	private String category;
	
	@ApiModelProperty(value = "Description of Advertisement")
	private String description;
	
	@ApiModelProperty(value = "Name of the User who created the Ad")
	private String userName;
	
	@ApiModelProperty(value = "Date and Time at which Advertisement is Created")
	private Date createdDate;
	
	@ApiModelProperty(value = "Date and Time at which Advertisement is Modified")
	private Date modifiedDate;
	
	@ApiModelProperty(value = "Current Status of the Advertisement")
	private String status;



}
