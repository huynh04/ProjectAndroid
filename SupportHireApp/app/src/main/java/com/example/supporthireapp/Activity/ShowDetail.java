package com.example.supporthireapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;

import com.example.supporthireapp.Database.CandidateData;
import com.example.supporthireapp.Model.Candidate;
import com.example.supporthireapp.R;
import com.example.supporthireapp.databinding.ActivityShowDetailBinding;

import java.util.ArrayList;

public class ShowDetail extends AppCompatActivity {
    String stt;
    ActivityShowDetailBinding binding;
    CandidateData candidateData;
    ArrayList<Candidate> candidates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        stt = getIntent().getStringExtra("stt");
        candidateData = new CandidateData(this);
        candidates = new ArrayList<>();

        showdetail();

    }
    public void showdetail(){
        Cursor cursor = candidateData.EachData(stt);
        while (cursor.moveToNext()){
            candidates.add(new Candidate(cursor.getString(0), cursor.getString(1), "","", cursor.getString(2), "","",""));
        }
        binding.NameDetail.setText(candidates.get(0).getFullname());
        binding.AgeDetail.setText(candidates.get(0).getDate());
        binding.SttDetail.setText(stt);
    }

}