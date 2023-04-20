package com.example.my_weatherapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_weatherapp.Model.Current;
import com.example.my_weatherapp.Model.ForeCast.ForeCast;
import com.example.my_weatherapp.Model.ForeCast.ForeCastDay;
import com.example.my_weatherapp.Model.ForeCast.Hour;
import com.example.my_weatherapp.Model.Location;
import com.example.my_weatherapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ForeCastAdapter extends RecyclerView.Adapter<ForeCastAdapter.ForeCastHolder> {
    Context ctx;
    List<Hour>hours;

    public ForeCastAdapter(Context ctx, List<Hour> hours) {
        this.ctx = ctx;
        this.hours = hours;
    }

    @NonNull
    @Override
    public ForeCastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.forecastholder,parent,false);
        return new ForeCastHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForeCastHolder holder, int position) {
        holder.temp_forecast.setText(hours.get(position).getTemp_c());
        Picasso.get().load("https:"+hours.get(position).getCondition().getIcon()).into(holder.icon_forecast);
        holder.Time.setText(hours.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return hours.size();
    }

    public class ForeCastHolder extends RecyclerView.ViewHolder{
        TextView temp_forecast,Time;
        ImageView icon_forecast;

        public ForeCastHolder(@NonNull View itemView) {
            super(itemView);

            temp_forecast = itemView.findViewById(R.id.temp_forecast);
            icon_forecast = itemView.findViewById(R.id.icon_forecast);
            Time = itemView.findViewById(R.id.timeDay);
        }
    }
}
