package com.zensar.advertise.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Contains the List of Product Categories on OLX Application")
public class CategoryListDto {
	@ApiModelProperty(value = "List of Categories")
	private List<CategoryDto> categories;
}
