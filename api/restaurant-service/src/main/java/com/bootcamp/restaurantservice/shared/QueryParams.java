package com.bootcamp.restaurantservice.shared;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryParams {

    private Integer page = 0;
    private Integer size = 20;
}