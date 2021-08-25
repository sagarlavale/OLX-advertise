package com.zensar.advertise.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Contains the User Information to create a New User for OLX Application")
public class UserDto {
    @ApiModelProperty(value = "ID of User")
    private Integer id;

    @ApiModelProperty(value = "First Name of User")
    private String firstName;

    @ApiModelProperty(value = "Last Name of User")
    private String lastName;

    @ApiModelProperty(value = "UserName of User")
    private String userName;

    @ApiModelProperty(value = "Phone No of User")
    private String phone;

    @ApiModelProperty(value = "Email of User")
    private String email;

    @ApiModelProperty(value = "Password of User")
    private String password;

}

