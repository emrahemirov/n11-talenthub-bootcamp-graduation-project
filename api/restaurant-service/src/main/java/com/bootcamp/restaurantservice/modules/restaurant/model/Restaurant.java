package com.bootcamp.restaurantservice.modules.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@SolrDocument(collection = "restaurants")
public class Restaurant {
    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    @Indexed(name = "name", type = "string")
    private String name;

    @Indexed(name = "geo", type = "location")
    private String geo;

    @Indexed(name = "averageRate", type = "pdouble")
    private Double averageRate;

//     <field name="geo" type="location" indexed="true" stored="true" multiValued="false"/>
//    <field name="name" type="string" indexed="true" stored="true" multiValued="false"/>
//    <field name="averageRate" type="pdouble" indexed="true" stored="true" multiValued="false"/>
//    <field name="totalReviewsCount" type="plong" indexed="true" stored="true" multiValued="false"/>

    @Indexed(name = "totalReviewsCount", type = "plong")
    private Long totalReviewsCount;

    public Restaurant() {
        this.id = UUID.randomUUID().toString();
    }


}




