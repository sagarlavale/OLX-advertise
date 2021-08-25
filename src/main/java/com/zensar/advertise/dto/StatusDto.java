package com.zensar.advertise.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Contains the Information About Order Status")
public class StatusDto {
    @ApiModelProperty(value = "ID of Order Status")
    private Integer id;

    @ApiModelProperty(value = "Name of Order Status")
    private String status;
}
