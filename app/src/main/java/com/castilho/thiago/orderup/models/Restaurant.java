package com.castilho.thiago.orderup.models;

/**
 * Created by Thiago on 2017-09-25.
 */

public class Restaurant extends ModelBase {

    private Integer CuisineId;

    public Integer getCuisineId() {
        return CuisineId;
    }

    public void setCuisineId(Integer cuisineId) {
        CuisineId = cuisineId;
    }

    public Restaurant() {
    }

    public Restaurant(Integer cuisineId) {
        CuisineId = cuisineId;
    }

    public Restaurant(Integer id, String name, Integer cuisineId,Integer resourceId) {
        super(id, name, resourceId);
        CuisineId = cuisineId;
    }
}
