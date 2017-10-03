package com.castilho.thiago.orderup.models;

/**
 * Created by Thiago on 2017-09-25.
 */

public class Food extends ModelBase{

    private Double Price;
    private Integer RestaurantId;

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Integer getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        RestaurantId = restaurantId;
    }

    public Food() {
    }

    public Food(Double price, Integer restaurantId) {
        Price = price;
        RestaurantId = restaurantId;
    }

    public Food(Integer id, String name, Integer restaurantId,Double price, Integer resourceId) {
        super(id, name, resourceId);
        Price = price;
        RestaurantId = restaurantId;
    }
}
