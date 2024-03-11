package com.bootcamp.restaurantservice.modules.restaurant.dto;

import com.bootcamp.restaurantservice.modules.restaurant.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    RestaurantResponse toRestaurantResponse(Restaurant entity);

    List<RestaurantResponse> toRestaurantResponseList(List<Restaurant> entities);

    Restaurant toRestaurant(RestaurantSaveRequest dto);

    void mutateRestaurant(@MappingTarget Restaurant entity, RestaurantUpdateRequest dto);
}