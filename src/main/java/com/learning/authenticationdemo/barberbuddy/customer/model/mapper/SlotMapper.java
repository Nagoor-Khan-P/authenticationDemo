package com.learning.authenticationdemo.barberbuddy.customer.model.mapper;

import com.learning.authenticationdemo.barberbuddy.customer.model.Slot;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.request.SlotRequestVO;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.response.SlotResponseVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SlotMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "saloon", ignore = true)
    @Mapping(target = "booked", constant = "false")
    Slot toEntity(SlotRequestVO vo);

    @Mapping(target = "saloonId", source = "saloon.id")
    SlotResponseVO toResponse(Slot slot);
}
