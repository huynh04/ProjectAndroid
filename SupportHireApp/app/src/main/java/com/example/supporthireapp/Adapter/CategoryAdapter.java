package com.example.supporthireapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supporthireapp.*;
import com.example.supporthireapp.Listener.CategoryOnClicked;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    List<String> category;
    Context ctx;
    CategoryOnClicked listener;

    public CategoryAdapter(List<String> category, Context ctx, CategoryOnClicked listener) {
        this.category = category;
        this.ctx = ctx;
        this.listener = listener;
    }


    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.category_item,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.category_text.setText(category.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClicked(holder.category_text.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder{
        TextView category_text;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            category_text = itemView.findViewById(R.id.category_text);
        }
    }
}
