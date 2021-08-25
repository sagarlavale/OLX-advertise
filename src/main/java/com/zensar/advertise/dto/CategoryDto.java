package com.zensar.advertise.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Contains the Information About Product Category")
public class CategoryDto {
    @ApiModelProperty(value = "ID of the Product Category")
    private Integer id;

    @ApiModelProperty(value = "Name of the Product Category")
    private String category;
}
