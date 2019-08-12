package com.varsitycollege.ctill.vcweather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WeatherArrayAdapter extends ArrayAdapter<Temprature>
{


    public WeatherArrayAdapter(@NonNull Context context, ArrayList<Temprature> weatherArrayList
    )
    {
        super(context,0, weatherArrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
       Temprature temprature = getItem(position);
       Context context ;

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.weather_items,parent,false);
        }


        TextView highTemp = convertView.findViewById(R.id.highvalue);
        TextView lowTemp = convertView.findViewById(R.id.lowvalue);
        TextView date = convertView.findViewById(R.id.datevalue);
        TextView link = convertView.findViewById(R.id.linkvalue);


        date.setText(temprature.getDate());
        highTemp.setText(temprature.getMaxTemp());
        lowTemp.setText(temprature.getMinTemp());
        link.setText(temprature.getLink());

        return convertView;



    }
}






