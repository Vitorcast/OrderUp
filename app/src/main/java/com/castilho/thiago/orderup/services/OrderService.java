package com.castilho.thiago.orderup.services;

import com.castilho.thiago.orderup.models.Customer;
import com.castilho.thiago.orderup.models.Food;
import com.castilho.thiago.orderup.models.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thiago on 2017-09-25.
 */

public class OrderService {
    private static OrderService instance = null;
    public static OrderService getInstance(){
        if (instance == null){
            instance = new OrderService();
        }
        return instance;
    }

    private Order order;

    public OrderService() {
        order = new Order();
    }



    public void addFood(Food food){
        if (this.order.getFoods() == null){
            this.order.setFoods(new ArrayList<Food>());
        }
        this.order.getFoods().add(food);
    }

    public void removeFood(Food food){
        this.order.getFoods().remove(food);
    }

    public void addCustomer(Customer customer){
        this.order.setCustomer(customer);
    }

    public Order getOrder(){
        return this.order;
    }
}
