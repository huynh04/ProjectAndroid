package com.example.supporthireapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supporthireapp.Activity.ShowDetail;
import com.example.supporthireapp.Model.Candidate;
import com.example.supporthireapp.R;

import java.util.ArrayList;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.CandidateHolder> {
    ArrayList<Candidate>candidates;
    Context ctx;

    public CandidateAdapter(ArrayList<Candidate> candidates, Context ctx) {
        this.candidates = candidates;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public CandidateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.candidate_item,parent,false);
        return new CandidateHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CandidateHolder holder, int position) {
        holder.name.setText(candidates.get(position).getFullname());
        holder.skill.setText(candidates.get(position).getSkill());
        holder.stt.setText(candidates.get(position).getStt());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, ShowDetail.class);
                intent.putExtra("stt", candidates.get(position).getStt());
                intent.putExtra("uid",candidates.get(position).getUid());
                ctx.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public class CandidateHolder extends RecyclerView.ViewHolder{
        TextView name,skill,stt;

        public CandidateHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.fullname);
            skill= itemView.findViewById(R.id.skill);
            stt = itemView.findViewById(R.id.stt);
        }
    }
}
