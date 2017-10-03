package com.castilho.thiago.orderup.models;

import java.util.List;

/**
 * Created by Thiago on 2017-09-25.
 */

public class Order {
    private Customer Customer;
    private List<Food> Foods;
    private Double Total;

    public com.castilho.thiago.orderup.models.Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(com.castilho.thiago.orderup.models.Customer customer) {
        Customer = customer;
    }

    public List<Food> getFoods() {
        return Foods;
    }

    public void setFoods(List<Food> foods) {
        Foods = foods;
    }

    public Double getTotal() {
        Double Total = 0.0;
        for (Food food:this.Foods) Total += food.getPrice();
        return Total;
    }

    public Order() {
    }

    public Order(com.castilho.thiago.orderup.models.Customer customer, List<Food> foods) {
        Customer = customer;
        Foods = foods;
    }
}
