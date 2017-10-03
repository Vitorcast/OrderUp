package com.castilho.thiago.orderup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.castilho.thiago.orderup.adapters.ImageListAdapter;
import com.castilho.thiago.orderup.models.Cuisine;
import com.castilho.thiago.orderup.models.ModelBase;
import com.castilho.thiago.orderup.models.Order;
import com.castilho.thiago.orderup.models.Restaurant;
import com.castilho.thiago.orderup.services.OrderService;
import com.castilho.thiago.orderup.services.RestaurantService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantSelect extends AppCompatActivity {
    private ListView list_view;
    private RestaurantService service;
    private OrderService orderService;
    private ImageListAdapter adapter;
    private Integer CuisineId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_select);

        setTitle("Select your restaurant:");

        service = RestaurantService.getInstance();
        orderService = OrderService.getInstance();

        if(getIntent()!= null){
            CuisineId = getIntent().getIntExtra("CuisineId",-1);
        }

        if(CuisineId != -1){
            loadListView();
        }
    }

    protected void loadListView(){
        list_view = (ListView)findViewById(R.id.list_view);
        List<ModelBase> arrayList = new ArrayList<ModelBase>();

        for (Restaurant restaurant: service.getAllByCuisineId(CuisineId)) {
            arrayList.add(restaurant);
        }

        adapter = new ImageListAdapter(this,arrayList);
        list_view.setAdapter(adapter);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent selectRestaurant = new Intent(RestaurantSelect.this, FoodSelect.class);
                selectRestaurant.putExtra("RestaurantId", (Integer)view.getTag());
                startActivity(selectRestaurant);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (orderService.getOrder().getCustomer() != null) {
            getMenuInflater().inflate(R.menu.menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case(R.id.menu_order_summary):
                Intent orderSummary = new Intent(this,Checkout.class);
                startActivity(orderSummary);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
