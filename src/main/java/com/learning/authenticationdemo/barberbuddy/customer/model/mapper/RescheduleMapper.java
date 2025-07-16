package com.learning.authenticationdemo.barberbuddy.customer.model.mapper;

import com.learning.authenticationdemo.barberbuddy.customer.model.Booking;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.request.RescheduleRequestVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RescheduleMapper {

    @Mapping(target = "id", source = "bookingId")
    @Mapping(target = "date", source = "newDate")
    @Mapping(target = "time", source = "newTime")
    @Mapping(target = "slotId", source = "newSlotId")
    Booking toEntity(RescheduleRequestVO rescheduleRequestVO);
}
