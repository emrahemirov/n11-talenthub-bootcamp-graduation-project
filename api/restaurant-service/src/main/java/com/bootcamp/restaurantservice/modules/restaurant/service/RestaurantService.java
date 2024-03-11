package com.bootcamp.restaurantservice.modules.restaurant.service;

import com.bootcamp.restaurantservice.advice.responseexceptionhandler.ErrorMessage;
import com.bootcamp.restaurantservice.advice.responseexceptionhandler.exceptions.ItemNotFoundException;
import com.bootcamp.restaurantservice.modules.restaurant.client.RestaurantSolrClient;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantMapper;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantResponse;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantSaveRequest;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantUpdateRequest;
import com.bootcamp.restaurantservice.modules.restaurant.dto.averagerate.AverageRateUpdateRequest;
import com.bootcamp.restaurantservice.modules.restaurant.dto.averagerate.AverageRateUpdateType;
import com.bootcamp.restaurantservice.modules.restaurant.model.Restaurant;
import com.bootcamp.restaurantservice.modules.restaurant.repository.RestaurantRepository;
import com.bootcamp.restaurantservice.shared.QueryParams;
import com.bootcamp.restaurantservice.shared.WithPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository repository;
    private final RestaurantSolrClient restaurantSolrClient;

    public RestaurantResponse save(RestaurantSaveRequest saveRequest) {
        Restaurant restaurant = RestaurantMapper.INSTANCE.toRestaurant(saveRequest);
        restaurant.setGeo(saveRequest.latitude() + "," + saveRequest.longitude());
        restaurant.setAverageRate(5D);
        restaurant.setTotalReviewsCount(50L);

        repository.save(restaurant);

        return RestaurantMapper.INSTANCE.toRestaurantResponse(restaurant);
    }

    public WithPagination<RestaurantResponse> findAll(QueryParams queryParams) {

        Page<Restaurant> restaurantPage = repository.findAll(PageRequest.of(queryParams.getPage(), queryParams.getSize()));
        List<RestaurantResponse> restaurantResponseList = RestaurantMapper.INSTANCE.toRestaurantResponseList(restaurantPage.getContent());


        return WithPagination.of(
                restaurantPage,
                restaurantResponseList
        );
    }

    public List<RestaurantResponse> getRecommendedRestaurantsWithin10Km(String latitude, String longitude) {
        List<Restaurant> restaurantList = restaurantSolrClient.getRecommendedRestaurantsWithin10Km(latitude, longitude);

        return RestaurantMapper.INSTANCE.toRestaurantResponseList(restaurantList);

    }

    public void updateAverageRate(AverageRateUpdateRequest updateRequest) {
        Restaurant restaurant = findRestaurantById(updateRequest.id());
        Double currentAverageRate = restaurant.getAverageRate();
        Long currentTotalReviewsCount = restaurant.getTotalReviewsCount();

        Double newAverageRate = currentAverageRate;
        Long newTotalReviewsCount = currentTotalReviewsCount;

        if (updateRequest.type() == AverageRateUpdateType.INCREASE) {
            newTotalReviewsCount = currentTotalReviewsCount + 1;
            newAverageRate = (currentAverageRate * currentTotalReviewsCount + updateRequest.rate().getValue()) / newTotalReviewsCount;
        }
        if (updateRequest.type() == AverageRateUpdateType.DECREASE) {
            newTotalReviewsCount = currentTotalReviewsCount - 1;
            newAverageRate = (currentAverageRate * currentTotalReviewsCount - updateRequest.rate().getValue()) / newTotalReviewsCount;
        }

        restaurant.setAverageRate(newAverageRate);
        restaurant.setTotalReviewsCount(newTotalReviewsCount);

        repository.save(restaurant);
    }

    public void delete(String id) {
        Restaurant restaurant = findRestaurantById(id);
        repository.delete(restaurant);
    }

    public RestaurantResponse update(RestaurantUpdateRequest updateRequest) {
        Restaurant restaurant = findRestaurantById(updateRequest.id());
        RestaurantMapper.INSTANCE.mutateRestaurant(restaurant, updateRequest);
        restaurant.setGeo(updateRequest.latitude() + "," + updateRequest.longitude());
        repository.save(restaurant);

        return RestaurantMapper.INSTANCE.toRestaurantResponse(restaurant);
    }


    public Restaurant findRestaurantById(String id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND));
    }

}
