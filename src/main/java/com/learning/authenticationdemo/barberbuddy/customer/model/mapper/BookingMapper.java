package com.learning.authenticationdemo.barberbuddy.customer.model.mapper;

import com.learning.authenticationdemo.barberbuddy.customer.model.Booking;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.request.BookingRequestVO;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.response.BookingResponseVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookedAt", ignore = true)
    Booking toEntity(BookingRequestVO request);

    BookingResponseVO toResponseVO(Booking booking);
}
