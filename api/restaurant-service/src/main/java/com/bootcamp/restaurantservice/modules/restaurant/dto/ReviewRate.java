package com.bootcamp.restaurantservice.modules.restaurant.dto;

public enum ReviewRate {
    ONE(1D),
    TWO(2D),
    THREE(3D),
    FOUR(4D),
    FIVE(5D);

    private final Double value;

    ReviewRate(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
