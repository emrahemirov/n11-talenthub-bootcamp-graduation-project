package com.bootcamp.restaurantservice.modules.restaurant.client;

import com.bootcamp.restaurantservice.modules.restaurant.model.Restaurant;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;


@Service
public class RestaurantSolrClient {

    @Value("${spring.data.solr.host}")
    private String SOLR_HOST;


    private MapSolrParams getQueryParams(String latitude, String longitude) {
        String searchedGeo = latitude + "," + longitude;

        final Map<String, String> queryParamMap = new HashMap<>();

        queryParamMap.put("q", "*:*");
        queryParamMap.put("fq", "{!geofilt pt=" + searchedGeo + " sfield=geo d=10}");
        queryParamMap.put("sort", "mul(mul(div(averageRate,5),7),mul(div(sub(10,geodist(" + searchedGeo + ",geo)),10),3)) desc");
        queryParamMap.put("rows", "3");

        return new MapSolrParams(queryParamMap);
    }

    public List<Restaurant> getRecommendedRestaurantsWithin10Km(String latitude, String longitude) {
        HttpSolrClient solrClient = new HttpSolrClient.Builder(this.SOLR_HOST + "/restaurants").build();

        try {
            QueryResponse queryResponse = solrClient.query(getQueryParams(latitude, longitude));
            solrClient.close();

            List<Restaurant> restaurantList = new ArrayList<>();
            queryResponse.getResults().forEach(result -> {
                Restaurant restaurant = new Restaurant();

                restaurant.setId(result.get("id").toString());
                restaurant.setName(result.get("name").toString());
                restaurant.setGeo(result.get("geo").toString());
                restaurant.setAverageRate((Double) result.get("averageRate"));
                restaurant.setTotalReviewsCount((Long) result.get("totalReviewsCount"));

                restaurantList.add(restaurant);
            });

            return restaurantList;

        } catch (SolrServerException | IOException e) {
            return Collections.emptyList();
        }
    }

}