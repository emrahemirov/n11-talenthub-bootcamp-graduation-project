package com.bootcamp.reviewservice.modules.user.dto;

import com.bootcamp.reviewservice.modules.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toUserResponse(User entity);

    List<UserResponse> toUserResponseList(List<User> entityList);

    User toUser(UserSaveRequest dto);

    User toUser(UserUpdateRequest dto);

    void mutateUser(@MappingTarget User entity, UserUpdateRequest dto);
}