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

    @Indexed(name = "imageSrc", type = "string")
    private String imageSrc;

    @Indexed(name = "geo", type = "location")
    private String geo;

    @Indexed(name = "averageRate", type = "pdouble")
    private Double averageRate;

    @Indexed(name = "totalReviewsCount", type = "plong")
    private Long totalReviewsCount;


    //38.439022, 27.142078 - sol ust
    //38.378086, 27.124647 - sol alt
    //38.436425, 27.211260 - sag ust
    //38.378976, 27.216450 - sag alt

    public Restaurant() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", imageSrc='" + imageSrc + '\'' +
                ", geo='" + geo + '\'' +
                ", averageRate=" + averageRate +
                ", totalReviewsCount=" + totalReviewsCount +
                '}';
    }
}




