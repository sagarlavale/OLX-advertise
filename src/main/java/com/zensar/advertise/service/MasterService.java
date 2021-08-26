package com.zensar.advertise.service;

import com.zensar.advertise.dto.CategoryDto;
import com.zensar.advertise.dto.CategoryListDto;
import com.zensar.advertise.dto.StatusDto;
import com.zensar.advertise.dto.StatusListDto;

public interface MasterService {

    String MASTER_SERVICE_URL = "http://localhost:8000/master/";

    StatusDto getStatus(Integer id);

    CategoryDto getCategory(Integer id);

    CategoryListDto getCategories();

    StatusListDto getStatuses();
}
