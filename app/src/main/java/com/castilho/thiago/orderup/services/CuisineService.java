package com.castilho.thiago.orderup.services;

import com.castilho.thiago.orderup.R;
import com.castilho.thiago.orderup.models.Cuisine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thiago on 2017-09-25.
 */

public class CuisineService {
    private static CuisineService instance = null;
    public static CuisineService getInstance(){
        if (instance == null){
            instance = new CuisineService();
        }
        return instance;
    }

    private List<Cuisine> list;

    public CuisineService() {
        list = new ArrayList<>();
        list.add(new Cuisine(0,"Brazilian", R.drawable.brazilian));
        list.add(new Cuisine(1,"Greek", R.drawable.greek));
        list.add(new Cuisine(2,"Japanese", R.drawable.japanese));
    }

    public List<Cuisine> getAll(){
        return this.list;
    }
}
