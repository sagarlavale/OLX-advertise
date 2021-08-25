package com.zensar.advertise.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(value = "List of Advertisements on OLX Application")
@AllArgsConstructor
@NoArgsConstructor
public class AdvertiseListDto {
	
	private List<AdvertiseResponseDto> advertises;

}
