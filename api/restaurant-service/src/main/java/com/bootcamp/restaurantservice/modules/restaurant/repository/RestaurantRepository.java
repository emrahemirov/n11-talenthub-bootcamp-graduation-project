package com.bootcamp.restaurantservice.modules.restaurant.repository;

import com.bootcamp.restaurantservice.modules.restaurant.model.Restaurant;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface RestaurantRepository extends SolrCrudRepository<Restaurant, String> {

}