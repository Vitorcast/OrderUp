package com.castilho.thiago.orderup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.castilho.thiago.orderup.adapters.CheckoutListAdapter;
import com.castilho.thiago.orderup.models.Food;
import com.castilho.thiago.orderup.models.ModelBase;
import com.castilho.thiago.orderup.models.Order;
import com.castilho.thiago.orderup.services.OrderService;

import java.util.ArrayList;
import java.util.List;

public class Checkout extends AppCompatActivity {

    TextView tv_customer, tv_total;
    ListView lv_order_items;
    OrderService service;
    CheckoutListAdapter adapter;
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        setTitle("Order summary:");

        // init service
        service = OrderService.getInstance();

        order = service.getOrder();

        //set components
        setComponents();
    }


    protected void setComponents(){
        tv_customer = (TextView) findViewById(R.id.tv_customer);
        tv_total = (TextView) findViewById(R.id.tv_total);
        tv_customer.setText(order.getCustomer().getName());
        tv_total.setText(order.getTotal().toString());


        lv_order_items = (ListView)findViewById(R.id.lv_order_items);

        List<ModelBase> list = new ArrayList<>();
        for (Food food:service.getOrder().getFoods()) {
            list.add(food);
        }

        adapter = new CheckoutListAdapter(this,list);

        lv_order_items.setAdapter(adapter);
    }
}
