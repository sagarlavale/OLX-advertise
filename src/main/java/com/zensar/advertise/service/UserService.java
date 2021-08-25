package com.zensar.advertise.service;

import com.zensar.advertise.dto.UserDto;

public interface UserService {

    final String USER_SERVICE_URL = "http://localhost:7000/login";

    UserDto getUserInfo(String token);


}
