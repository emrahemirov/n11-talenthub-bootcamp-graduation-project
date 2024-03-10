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
    @Indexed(name = "geo", type = "string", required = true)
    private String geo;
    @Indexed(name = "address_line", type = "string", required = true)
    private String addressLine;
    @Indexed(name = "building_number", type = "string", required = true)
    private String buildingNumber;
    @Indexed(name = "floor", type = "string")
    private String floor;
    @Indexed(name = "apartment_number", type = "string")
    private String apartmentNumber;
    @Indexed(name = "average_rate", type = "double", required = true)
    private Double averageRate;
    @Indexed(name = "total_review_count", type = "long", required = true)
    private Long totalReviewCount;


    public Restaurant() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", geo='" + geo + '\'' +
                ", addressLine='" + addressLine + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", floor='" + floor + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                '}';
    }


}
