package com.castilho.thiago.orderup.services;

import com.castilho.thiago.orderup.R;
import com.castilho.thiago.orderup.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thiago on 2017-09-25.
 */

public class RestaurantService {
    private static RestaurantService instance = null;
    public static RestaurantService getInstance(){
        if (instance == null){
            instance = new RestaurantService();
        }
        return instance;
    }

    private List<Restaurant> list;

    public RestaurantService() {
        list = new ArrayList<>();

        list.add(new Restaurant(0,"Copacabana",0, R.drawable.brazil_copacabana));
        list.add(new Restaurant(1,"Rio 40 Graus",0,R.drawable.brazil_rio40));
        list.add(new Restaurant(2,"Blowfish",2,R.drawable.japan_blowfish));
        list.add(new Restaurant(3,"Kinka",2,R.drawable.japan_kinka));
        list.add(new Restaurant(4,"Messini",1,R.drawable.greek_messini));
        list.add(new Restaurant(5,"Mamakas",1,R.drawable.greek_mamakas));
    }

    public List<Restaurant> getAllByCuisineId(Integer id){
        List<Restaurant> restaurants = new ArrayList<>();

        for (Restaurant restaurant:this.list) {
            if(restaurant.getCuisineId() == id){
                restaurants.add(restaurant);
            }
        }
        return restaurants;
    }

    public Restaurant getByName(String restaurantName){
        for(Restaurant restaurant:this.list){
            if(restaurant.getName() == restaurantName)
                return restaurant;
        }
        return null;
    }
}
