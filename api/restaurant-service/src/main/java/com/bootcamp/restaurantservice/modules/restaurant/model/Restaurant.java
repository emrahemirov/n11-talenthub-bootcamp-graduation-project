package com.bootcamp.restaurantservice.modules.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@SolrDocument(collection = "restaurants")
public class Restaurant {
    @Id
    @Field
    private String id;
    @Field
    private String name;
    @Field
    private String geo;
    @Field
    private Double averageRate;
    @Field
    private Long totalReviewsCount;

    public Restaurant() {
        this.id = UUID.randomUUID().toString();
    }


}




