package com.learning.authenticationdemo.barberbuddy.customer.model.mapper;

import com.learning.authenticationdemo.barberbuddy.customer.model.Saloon;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.request.SaloonRequestVO;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.response.SaloonResponseVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaloonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "barber", ignore = true)
    Saloon toEntity(SaloonRequestVO vo);

    @Mapping(target = "barberUsername", source = "barber.username")
    SaloonResponseVO toResponse(Saloon saloon);
}
