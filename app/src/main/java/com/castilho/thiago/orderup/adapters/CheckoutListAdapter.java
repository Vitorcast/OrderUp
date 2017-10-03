package com.castilho.thiago.orderup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.castilho.thiago.orderup.R;
import com.castilho.thiago.orderup.models.Food;
import com.castilho.thiago.orderup.models.ModelBase;

import java.util.List;

/**
 * Created by Thiago on 2017-09-27.
 */

public class CheckoutListAdapter extends BaseAdapter {
    private Context context;
    private List<ModelBase> arrayList;
    private LayoutInflater inflater;

    public CheckoutListAdapter(Context context, List<ModelBase> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();

            view = inflater.inflate(R.layout.checkout_list_custom_row_layout, viewGroup,false);

            viewHolder.label = view.findViewById(R.id.label);
            viewHolder.image = view.findViewById(R.id.image);
            viewHolder.labelPrice = view.findViewById(R.id.label_price);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.image.setImageDrawable(context.getDrawable((arrayList.get(i).getResourceId())));
        viewHolder.label.setText(arrayList.get(i).getName());
        if(arrayList.get(i) instanceof Food) {
            viewHolder.labelPrice.setText("$" + ((Food) arrayList.get(i)).getPrice());
        } else {
            viewHolder.labelPrice.setText(arrayList.get(i).getName());
        }

        viewHolder.label.setTag(arrayList.get(i).getId());
        viewHolder.labelPrice.setTag(arrayList.get(i).getId());
        viewHolder.image.setTag(arrayList.get(i).getId());

        return view;
    }

    private class ViewHolder {
        private TextView label;
        private TextView labelPrice;
        private ImageView image;
    }
}
