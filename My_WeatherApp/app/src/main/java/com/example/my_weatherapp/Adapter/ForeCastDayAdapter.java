package com.example.my_weatherapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_weatherapp.Model.ForeCast.Day;
import com.example.my_weatherapp.Model.ForeCast.ForeCastDay;
import com.example.my_weatherapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ForeCastDayAdapter extends RecyclerView.Adapter<ForeCastDayAdapter.ForeCastDayHolder> {
    List<ForeCastDay>foreCastDays;
    Context ctx;

    public ForeCastDayAdapter(List<ForeCastDay> foreCastDays, Context ctx) {
        this.foreCastDays = foreCastDays;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ForeCastDayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.forecastdayholder,parent,false);
        return new ForeCastDayHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForeCastDayHolder holder, int position) {
        holder.day.setText(foreCastDays.get(position).getDate());
        holder.temp_min.setText(foreCastDays.get(position).getDay().getMaxtemp_c());
        holder.temp_max.setText(foreCastDays.get(position).getDay().getMintemp_c());
        Picasso.get().load("https:"+foreCastDays.get(position).getDay().getCondition().getIcon()).into(holder.icon);


    }

    @Override
    public int getItemCount() {
        return foreCastDays.size();
    }

    public class ForeCastDayHolder extends RecyclerView.ViewHolder{
        TextView date,day,temp_max,temp_min;
        ImageView icon;

        public ForeCastDayHolder(@NonNull View itemView) {
            super(itemView);

            day = itemView.findViewById(R.id.day);
            temp_max = itemView.findViewById(R.id.max_temp);
            temp_min = itemView.findViewById(R.id.min_temp);
            icon = itemView.findViewById(R.id.icon_forecast_day);
        }
    }

}
