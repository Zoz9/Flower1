package com.example.zoz.flower1;

import java.util.ArrayList;
import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Zoz on 29.01.2017.
 */ 

public class FAdapterList extends BaseAdapter {

    Context ctx;
    LayoutInflater lInfater;
    ArrayList<CatalogFlower> myOjctFlower;

    public FAdapterList(Context ctx,ArrayList<CatalogFlower> myOjctFlower ){
        this.ctx =ctx;
        this.myOjctFlower = myOjctFlower;
        lInfater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return myOjctFlower.size();//проверить
    }

    @Override
    public Object getItem(int position) {
        return myOjctFlower.get(position);//проверить
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
        {
            view = lInfater.inflate(R.layout.items, parent,false);
        }
        CatalogFlower FlowerItem = (CatalogFlower)getItem(position);

        ((TextView) view.findViewById(R.id.tvNameFl)).setText(FlowerItem.getName());
        ((TextView) view.findViewById(R.id.tvIdFl)).setText(String.valueOf(FlowerItem.getId()));

        return view;
    }
}
