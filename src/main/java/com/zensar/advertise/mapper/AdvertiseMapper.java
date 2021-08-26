package com.zensar.advertise.mapper;

import com.zensar.advertise.dto.AdvertiseRequestDto;
import com.zensar.advertise.dto.AdvertiseResponseDto;
import com.zensar.advertise.entity.Advertise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface AdvertiseMapper {

    Advertise toAdvertise(AdvertiseRequestDto advertiseRequestDto);

    @Mapping(target = "userName", source = "createdBy")
    @Mapping(target = "modifiedDate", source = "updatedAt")
    @Mapping(target = "createdDate", source = "createdAt")
    AdvertiseResponseDto toAdvertiseResponseDto(Advertise advertise);

    List<AdvertiseResponseDto> toAdvertiseListDto(List<Advertise> advertises);
}
