package com.bootcamp.restaurantservice.modules.restaurant.service;

import com.bootcamp.restaurantservice.advice.responseexceptionhandler.ErrorMessage;
import com.bootcamp.restaurantservice.advice.responseexceptionhandler.exceptions.ItemNotFoundException;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantMapper;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantResponse;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantSaveRequest;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantUpdateRequest;
import com.bootcamp.restaurantservice.modules.restaurant.model.Restaurant;
import com.bootcamp.restaurantservice.modules.restaurant.repository.RestaurantRepository;
import com.bootcamp.restaurantservice.shared.WithPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantResponse save(RestaurantSaveRequest saveRequest) {
        Restaurant restaurant = RestaurantMapper.INSTANCE.toRestaurant(saveRequest);
        repository.save(restaurant);

        return RestaurantMapper.INSTANCE.toRestaurantResponse(restaurant);
    }

    public WithPagination<RestaurantResponse> findAll(Integer page, Integer size) {
        Page<Restaurant> restaurantPage = repository.findAll(PageRequest.of(page, size));
        List<RestaurantResponse> restaurantResponseList = RestaurantMapper.INSTANCE.toRestaurantResponseList(restaurantPage.getContent());

        return WithPagination.of(
                restaurantPage,
                restaurantResponseList
        );
    }

    public void delete(String id) {
        Restaurant restaurant = findRestaurantById(id);
        repository.delete(restaurant);
    }

    public RestaurantResponse update(RestaurantUpdateRequest updateRequest) {
        Restaurant foundRestaurant = findRestaurantById(updateRequest.id());
        Restaurant restaurantToUpdate = RestaurantMapper.INSTANCE.toRestaurant(updateRequest);
        BeanUtils.copyProperties(restaurantToUpdate, foundRestaurant);

        Restaurant updatedRestaurant = repository.save(foundRestaurant);
        return RestaurantMapper.INSTANCE.toRestaurantResponse(updatedRestaurant);
    }


    public Restaurant findRestaurantById(String id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND));
    }

}
