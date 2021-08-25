package com.zensar.advertise.service;

import com.zensar.advertise.dto.CategoryDto;
import com.zensar.advertise.dto.StatusDto;

public interface MasterService {

    String MASTER_SERVICE_URL = "http://localhost:8000/master/";

    StatusDto getStatus(Integer id);

    CategoryDto getCategory(Integer id);
}
