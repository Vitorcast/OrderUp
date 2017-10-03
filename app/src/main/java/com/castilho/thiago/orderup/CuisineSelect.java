package com.castilho.thiago.orderup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.castilho.thiago.orderup.adapters.RadioListAdapter;
import com.castilho.thiago.orderup.models.Cuisine;
import com.castilho.thiago.orderup.models.ModelBase;
import com.castilho.thiago.orderup.services.CuisineService;
import com.castilho.thiago.orderup.services.OrderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CuisineSelect extends AppCompatActivity {

    ListView list_view;

    CuisineService service;
    OrderService orderService;

    RadioListAdapter adapter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine_select);

        setTitle("Select cuisine:");

        service = CuisineService.getInstance();
        orderService = OrderService.getInstance();

        loadListView();
    }

    void loadListView(){
        list_view = (ListView)findViewById(R.id.list_view);
        List<ModelBase> arrayList = new ArrayList<ModelBase>();
        for (Cuisine cuisine: service.getAll()) {
            arrayList.add(cuisine);
        }

        adapter = new RadioListAdapter(this,arrayList,false);
        list_view.setAdapter(adapter);
        
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent selectRestaurant = new Intent(CuisineSelect.this, RestaurantSelect.class);
                selectRestaurant.putExtra("CuisineId", (Integer)view.getTag());
                startActivity(selectRestaurant);
            }
        });
    }
}
