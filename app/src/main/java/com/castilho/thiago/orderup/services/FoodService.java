package com.castilho.thiago.orderup.services;

import com.castilho.thiago.orderup.R;
import com.castilho.thiago.orderup.models.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thiago on 2017-09-25.
 */

public class FoodService {
    private static FoodService instance = null;
    public static FoodService getInstance(){
        if (instance == null){
            instance = new FoodService();
        }
        return instance;
    }

    private List<Food> list;

    public FoodService() {
        list = new ArrayList<>();
        list.add(new Food(0, "Cheese Bread", 0, 100.00, R.drawable.brazilian_cheesebread));
        list.add(new Food(1, "Coxinha", 0, 100.00, R.drawable.brazilian_coxinha));
        list.add(new Food(2, "Fried Manioc", 1, 100.00, R.drawable.brazilian_friedmanioc));
        list.add(new Food(3, "Torresmo", 1, 100.00, R.drawable.brazilian_torresmo));
        list.add(new Food(4, "Koukouvagia", 4,100.00, R.drawable.greek_koukouvagia));
        list.add(new Food(5, "Greek Salad", 4, 100.00, R.drawable.greek_salad_choriatiki));
        list.add(new Food(6, "Taramosalata", 5,  100.00, R.drawable.greek_taramosalata));
        list.add(new Food(7, "Tiroafteri", 5, 100.00, R.drawable.greek_tirokafteri));
        list.add(new Food(8, "Chahan",2 , 100.00, R.drawable.japanese_chahan));
        list.add(new Food(9, "Fried Rice", 2, 100.00, R.drawable.japanese_friedrice));
        list.add(new Food(10, "Kunpau", 3,  100.00, R.drawable.japanese_kunpao));
        list.add(new Food(11, "Miso Soup", 3, 100.00, R.drawable.japanese_misosoup));
    }

    public List<Food> getAllByRestaurantId(Integer id){
        List<Food> foods = new ArrayList<>();

        for (Food food:this.list) {
            if(food.getRestaurantId() == id){
                foods.add(food);
            }
        }
        return foods;
    }

}
