package com.bootcamp.restaurantservice.modules.restaurant.service;

import com.bootcamp.restaurantservice.advice.responseexceptionhandler.ErrorMessage;
import com.bootcamp.restaurantservice.advice.responseexceptionhandler.exceptions.ItemNotFoundException;
import com.bootcamp.restaurantservice.modules.restaurant.client.RestaurantSolrClient;
import com.bootcamp.restaurantservice.modules.restaurant.client.UserAddressFeignClient;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantMapper;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantResponse;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantSaveRequest;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantUpdateRequest;
import com.bootcamp.restaurantservice.modules.restaurant.dto.averagerate.AverageRateUpdateRequest;
import com.bootcamp.restaurantservice.modules.restaurant.dto.averagerate.ReviewRate;
import com.bootcamp.restaurantservice.modules.restaurant.dto.useraddress.UserAddressResponse;
import com.bootcamp.restaurantservice.modules.restaurant.model.Restaurant;
import com.bootcamp.restaurantservice.modules.restaurant.repository.RestaurantRepository;
import com.bootcamp.restaurantservice.shared.QueryParams;
import com.bootcamp.restaurantservice.shared.WithPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository repository;
    private final RestaurantSolrClient restaurantSolrClient;
    private final UserAddressFeignClient feignClient;

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

    public List<RestaurantResponse> getRecommendedRestaurantsWithin10Km(Long userId) {
        UserAddressResponse userAddress = Objects.requireNonNull(feignClient.getPreferredUserAddress(userId).getBody()).getData();

        List<Restaurant> restaurantList = restaurantSolrClient.getRecommendedRestaurantsWithin10Km(userAddress.latitude(), userAddress.longitude());

        return RestaurantMapper.INSTANCE.toRestaurantResponseList(restaurantList);
    }

    public void updateAverageRate(AverageRateUpdateRequest updateRequest) {
        Restaurant restaurant = findRestaurantById(updateRequest.restaurantId());
        ReviewRate newRate = updateRequest.newRate();
        ReviewRate oldRate = updateRequest.oldRate();

        // newRate == null -> delete
        // oldRate == null -> add
        // else -> update

        Double currentAverageRate = restaurant.getAverageRate();
        Long currentTotalReviewsCount = restaurant.getTotalReviewsCount();

        Double newAverageRate = null;
        Long newTotalReviewsCount = currentTotalReviewsCount;

        if (oldRate == null) {
            newTotalReviewsCount = currentTotalReviewsCount + 1;
            newAverageRate = (currentAverageRate * currentTotalReviewsCount + newRate.getValue()) / newTotalReviewsCount;
        } else if (newRate == null) {
            newTotalReviewsCount = currentTotalReviewsCount - 1;
            newAverageRate = (currentAverageRate * currentTotalReviewsCount + oldRate.getValue()) / newTotalReviewsCount;
        } else {
            double updateRate = oldRate.getValue() - newRate.getValue();
            newAverageRate = (currentAverageRate * currentTotalReviewsCount + updateRate) / newTotalReviewsCount;
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
