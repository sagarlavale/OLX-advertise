package com.zensar.advertise.mapper;

import com.zensar.advertise.dto.AdvertiseRequestDto;
import com.zensar.advertise.dto.AdvertiseResponseDto;
import com.zensar.advertise.entity.Advertise;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface AdvertiseMapper {

    Advertise toAdvertise(AdvertiseRequestDto advertiseRequestDto);

    AdvertiseResponseDto toAdvertiseResponseDto(Advertise advertise);

    List<AdvertiseResponseDto> toAdvertiseListDto(List<Advertise> advertises);
}
