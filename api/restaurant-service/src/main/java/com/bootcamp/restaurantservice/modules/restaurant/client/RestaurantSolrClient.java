package com.bootcamp.restaurantservice.modules.restaurant.client;

import com.bootcamp.restaurantservice.modules.restaurant.model.Restaurant;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@SuppressWarnings("deprecation")
public class RestaurantSolrClient {

    private final String solrUrl = "http://localhost:8983/solr/restaurants";

    public List<Restaurant> getRecommendedRestaurantsWithin10Km(String latitude, String longitude) {
        HttpSolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();

        try {
            String searchedGeo = latitude + "," + longitude;

            final Map<String, String> queryParamMap = new HashMap<>();

            queryParamMap.put("q", "*:*");
            queryParamMap.put("fq", "fq={!geofilt pt=" + searchedGeo + " sfield=geo d=10}");
            queryParamMap.put("start", "0");
            queryParamMap.put("rows", "3");
            queryParamMap.put("sort", "sum(mul(averageScore,14),mul(sub(10,geodist(" + searchedGeo + ",geo)),3)) desc");
//            queryParamMap.put("sort", "sum(mul(div(averageRate,5),7),mul(div(sub(10,geodist(" + searchedGeo + ",geo)),10),3)) desc");

            MapSolrParams queryParams = new MapSolrParams(queryParamMap);
            QueryResponse queryResponse = solrClient.query(queryParams);
            SolrDocumentList results = queryResponse.getResults();

            solrClient.close();

            List<Restaurant> restaurantList = new ArrayList<>();

            queryResponse.getResults().forEach(result -> {
                Restaurant restaurant = new Restaurant();

                restaurant.setId(result.get("id").toString());
                restaurant.setName(result.get("name").toString());
                restaurant.setGeo(result.get("geo").toString());
                restaurant.setAverageRate((Double) result.get("averageScore"));
                restaurant.setTotalReviewsCount((Long) result.get("totalReviewsCount"));

                restaurantList.add(restaurant);
            });

            return restaurantList;

        } catch (SolrServerException | java.io.IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}