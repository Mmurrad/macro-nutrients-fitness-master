package com.auth;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.easyfitness.R;

import java.util.List;

public class FoodAdapter extends ArrayAdapter<FoodData> {

    private Activity context;
    private List<FoodData> foodDataList;

    public FoodAdapter(Activity context, List<FoodData> foodDataList) {
        super(context, R.layout.sample_food,foodDataList);
        this.context=context;
        this.foodDataList=foodDataList;
    }

    public FoodAdapter(@NonNull Context context, int resource) {
        super(context, R.layout.sample_food);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater inflater=context.getLayoutInflater();
        View view=inflater.inflate(R.layout.sample_food,null,true);
        TextView food=(TextView)view.findViewById(R.id.sample_food_name);

        FoodData details=foodDataList.get(position);
        food.setText(details.getName());
        return view;
    }
}
