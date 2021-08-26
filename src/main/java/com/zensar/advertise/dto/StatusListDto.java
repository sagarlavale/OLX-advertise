package com.zensar.advertise.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Contains the List of Order Statuses on OLX Application")
public class StatusListDto {
	@ApiModelProperty(value = "List of Status")
	private List<StatusDto> statusList;

}
