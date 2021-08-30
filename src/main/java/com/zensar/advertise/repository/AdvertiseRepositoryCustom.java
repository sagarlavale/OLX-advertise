package com.zensar.advertise.repository;

import com.zensar.advertise.entity.Advertise;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AdvertiseRepositoryCustom {

    List<Advertise> filter(int page,int size,String title, String createdBy, Integer category, Integer status, Double price, String dateCondition, String onDate, String fromDate, String toDate);

    List<Advertise> search(String searchText);
}
