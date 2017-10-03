package com.castilho.thiago.orderup;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.castilho.thiago.orderup.adapters.CheckBoxListAdapter;
import com.castilho.thiago.orderup.adapters.ImageListAdapter;
import com.castilho.thiago.orderup.models.Food;
import com.castilho.thiago.orderup.models.ModelBase;
import com.castilho.thiago.orderup.services.FoodService;
import com.castilho.thiago.orderup.services.OrderService;

import java.util.ArrayList;
import java.util.List;

public class FoodSelect extends AppCompatActivity {
    private ListView list_view;
    private FoodService service;
    OrderService orderService;
    FloatingActionButton btn_confirm_food;
    private CheckBoxListAdapter adapter;
    private Integer RestaurantId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_select);

        setTitle("Select your food:");

        service = FoodService.getInstance();
        orderService = OrderService.getInstance();

        //init button
        btn_confirm_food = (FloatingActionButton) findViewById(R.id.btn_confirm_food);
        btn_confirm_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (ModelBase food:adapter.getSelectedItems()
                     ) {
                    orderService.addFood((Food)food);
                }

                Intent customerForm = new Intent(FoodSelect.this,CustomerForm.class);
                startActivity(customerForm);
            }
        });

        if(getIntent()!= null){
            RestaurantId = getIntent().getIntExtra("RestaurantId",-1);
        }

        if(RestaurantId != -1){
            loadListView();
        }
    }

    protected void loadListView(){
        list_view = (ListView)findViewById(R.id.list_view);
        List<ModelBase> arrayList = new ArrayList<ModelBase>();

        for (Food food: service.getAllByRestaurantId(RestaurantId)) {
            arrayList.add(food);
        }

        adapter = new CheckBoxListAdapter(this,arrayList);

        list_view.setAdapter(adapter);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent selectRestaurant = new Intent(FoodSelect.this, CustomerForm.class);
//                selectRestaurant.putExtra("Food", (Integer)view.getTag());
//                startActivity(selectRestaurant);
            }
        });
    }
}
