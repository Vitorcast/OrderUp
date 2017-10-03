package com.castilho.thiago.orderup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.castilho.thiago.orderup.R;
import com.castilho.thiago.orderup.models.Food;
import com.castilho.thiago.orderup.models.ModelBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thiago on 2017-09-26.
 */

public class CheckBoxListAdapter extends BaseAdapter {

    private Context context;
    private List<ModelBase> arrayList;
    private LayoutInflater inflater;
    private int selectedId = -1;
    private List<Integer> selectedIds;
    private List<ModelBase> selectedItems;

    public int getSelectedId() {
        return selectedId;
    }

    public List<Integer> getSelectedIds(){
        return selectedIds;
    }

    public List<ModelBase> getSelectedItems(){
        return selectedItems;
    }

    public CheckBoxListAdapter(Context context,  List<ModelBase>  arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(context);

        selectedIds = new ArrayList<>();
        selectedItems = new ArrayList<>();
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
        CheckBoxListAdapter.ViewHolder viewHolder;
        if (view == null){
            viewHolder = new CheckBoxListAdapter.ViewHolder();
            view = inflater.inflate(R.layout.check_box_list_custom_row_layout, viewGroup,false);

            viewHolder.label = view.findViewById(R.id.label);
            viewHolder.image = view.findViewById(R.id.image);
            viewHolder.checkbox = view.findViewById(R.id.check_box);
            viewHolder.labelPrice = view.findViewById(R.id.label_price);

            view.setTag(viewHolder);
        }else {
            viewHolder = (CheckBoxListAdapter.ViewHolder)view.getTag();
        }

        viewHolder.label.setText(arrayList.get(i).getName());
        viewHolder.image.setImageDrawable(context.getDrawable((arrayList.get(i).getResourceId())));
        viewHolder.checkbox.setChecked(viewHolder.checkbox.isChecked());
        if(arrayList.get(i) instanceof  Food) {
            viewHolder.labelPrice.setText("$" + ((Food) arrayList.get(i)).getPrice());
        } else {
            viewHolder.labelPrice.setText(arrayList.get(i).getName());
        }

        viewHolder.label.setTag(arrayList.get(i).getId());
        viewHolder.image.setTag(arrayList.get(i).getId());
        viewHolder.checkbox.setTag(arrayList.get(i).getId());
        viewHolder.labelPrice.setTag(arrayList.get(i).getId());


        viewHolder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCheckChanged(view);
            }
        });

        return view;
    }

    private class ViewHolder {
        private TextView label;
        private ImageView image;
        private CheckBox checkbox;
        private TextView labelPrice;
    }

    private void itemCheckChanged(View view){

        selectedId = (Integer)view.getTag();

        if (((CheckBox) view).isChecked()){
            selectedIds.add(selectedId);
            selectedItems.add(getSelectedItem());
        } else {
            selectedIds.remove(view.getTag());
            selectedItems.remove(getSelectedItem());
        }

        notifyDataSetChanged();
        //((ListView)view.getParent().getParent()).performItemClick(view, selectedPosition,0);
    }

    public ModelBase getSelectedItem() {
        if (selectedId != -1) {
            for (ModelBase food:arrayList){
                if (food.getId() == selectedId) return food;
            }
        }
        return null;
    }
}
