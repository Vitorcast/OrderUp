package com.castilho.thiago.orderup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.castilho.thiago.orderup.R;
import com.castilho.thiago.orderup.models.ModelBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Thiago on 2017-09-25.
 */

public class RadioListAdapter extends BaseAdapter {
    private Context context;
    private List<ModelBase> arrayList;
    private LayoutInflater inflater;
    private boolean isListView;
    private int selectedPosition = -1;

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public RadioListAdapter(Context context, List<ModelBase> arrayList, boolean isListView) {
        this.context = context;
        this.arrayList = arrayList;
        this.isListView = isListView;
        inflater = LayoutInflater.from(context);
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

            if(isListView){
                view = inflater.inflate(R.layout.list_custom_row_layout, viewGroup,false);
            } else {
                view = inflater.inflate(R.layout.grid_custom_row_layout, viewGroup, false);
            }

            viewHolder.label = view.findViewById(R.id.label);
            viewHolder.image = view.findViewById(R.id.image);
            viewHolder.radioButton = view.findViewById(R.id.radio_button);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.label.setText(arrayList.get(i).getName());
        viewHolder.radioButton.setChecked(i==selectedPosition);
        viewHolder.image.setImageDrawable(context.getDrawable((arrayList.get(i).getResourceId())));

        viewHolder.label.setTag(arrayList.get(i).getId());
        viewHolder.radioButton.setTag(arrayList.get(i).getId());
        viewHolder.image.setTag(arrayList.get(i).getId());

        viewHolder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCheckChanged(view);
            }
        });

        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCheckChanged(view);
            }
        });

        viewHolder.label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCheckChanged(view);
            }
        });

        return view;
    }

    private class ViewHolder {
        private TextView label;
        private RadioButton radioButton;
        private ImageView image;
    }

    private void itemCheckChanged(View view){
        selectedPosition = (Integer)view.getTag();
        notifyDataSetChanged();
       ((ListView)view.getParent().getParent()).performItemClick(view, selectedPosition,0);
    }

    public ModelBase getSelectedItem() {
        if (selectedPosition != -1) {
            Toast.makeText(context, "Selected Item : " + arrayList.get(selectedPosition), Toast.LENGTH_SHORT).show();
            return arrayList.get(selectedPosition);
        }
        return null;
    }

}
