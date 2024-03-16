package com.bootcamp.reviewservice.modules.useraddress.dto;

import com.bootcamp.reviewservice.modules.useraddress.model.UserAddress;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserAddressMapper {

    UserAddressMapper INSTANCE = Mappers.getMapper(UserAddressMapper.class);

    UserAddressResponse toUserAddressResponse(UserAddress entity);

    List<UserAddressResponse> toUserAddressResponseList(List<UserAddress> entityList);

    UserAddress toUserAddress(UserAddressSaveRequest dto);

    UserAddress toUserAddress(UserAddressUpdateRequest dto);

    void mutateUserAddress(@MappingTarget UserAddress entity, UserAddressUpdateRequest dto);

}