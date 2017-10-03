package com.castilho.thiago.orderup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.castilho.thiago.orderup.R;
import com.castilho.thiago.orderup.models.ModelBase;

import java.util.List;
import java.util.Map;

/**
 * Created by Thiago on 2017-09-26.
 */

public class ImageListAdapter extends BaseAdapter {
    private Context context;
    private List<ModelBase> arrayList;
    private LayoutInflater inflater;
    private int selectedPosition = -1;

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public ImageListAdapter(Context context,  List<ModelBase>  arrayList) {
        this.context = context;
        this.arrayList = arrayList;
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
            view = inflater.inflate(R.layout.image_list_custom_row_layout, viewGroup,false);

            viewHolder.label = view.findViewById(R.id.label);
            viewHolder.image = view.findViewById(R.id.image);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.label.setText(arrayList.get(i).getName());
        viewHolder.image.setImageDrawable(context.getDrawable((arrayList.get(i).getResourceId())));

        viewHolder.label.setTag(arrayList.get(i).getId());
        viewHolder.image.setTag(arrayList.get(i).getId());

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
