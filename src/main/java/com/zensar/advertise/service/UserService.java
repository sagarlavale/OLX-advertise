package com.zensar.advertise.service;

import com.zensar.advertise.dto.UserDto;

public interface UserService {

    String USER_SERVICE_URL = "http://login-service/login";

    UserDto getUserInfo(String token);


}
