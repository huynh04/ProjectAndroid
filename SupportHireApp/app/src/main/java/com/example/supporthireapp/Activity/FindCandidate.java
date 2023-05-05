package com.example.supporthireapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.supporthireapp.Adapter.CandidateAdapter;
import com.example.supporthireapp.Adapter.CategoryAdapter;
import com.example.supporthireapp.Database.CandidateData;
import com.example.supporthireapp.Listener.CategoryOnClicked;
import com.example.supporthireapp.Model.Candidate;
import com.example.supporthireapp.databinding.ActivityFindCandidateBinding;

import java.util.ArrayList;
import java.util.List;

public class FindCandidate extends AppCompatActivity implements CategoryOnClicked{
    ActivityFindCandidateBinding binding;
    ArrayList<Candidate> candidateArrayList;
    CandidateData candidateData;
    CategoryAdapter categoryAdapter;
    CandidateAdapter candidateAdapter;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindCandidateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        candidateArrayList = new ArrayList<>();
        candidateData = new CandidateData(this);
        uid = getIntent().getStringExtra("uid");

        categoryCandidate();
        findCandidate();

    }
    public void findCandidate(){
        String query = "" ;
        binding.findInput.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String query1 = "%"+query+"%" ;
                candidateArrayList.clear();
                fetchData(query1,"",uid);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void categoryCandidate(){
        Cursor cursor = candidateData.Skill(uid);
        List<String> category_list = new ArrayList<>();
        if (cursor.getCount()>=1){
            while (cursor.moveToNext()){
                category_list.add(cursor.getString(0));
            }
        }
        categoryAdapter = new CategoryAdapter(category_list,FindCandidate.this, FindCandidate.this);
        binding.category.setLayoutManager(new LinearLayoutManager(FindCandidate.this, LinearLayoutManager.HORIZONTAL,false));
        binding.category.setHasFixedSize(true);
        binding.category.setAdapter(categoryAdapter);
    }

    public void fetchData(String name, String skill,String Uid){
        Cursor cursor = candidateData.Fetch(name,skill,Uid);
        if (cursor.getCount()>=1){
            while (cursor.moveToNext()){
                candidateArrayList.add(new Candidate(cursor.getString(0), cursor.getString(1), cursor.getString(3),cursor.getString(4), cursor.getString(2), cursor.getString(5),cursor.getString(6),cursor.getString(7)));
            }
            candidateAdapter = new CandidateAdapter(candidateArrayList,FindCandidate.this);
            binding.candidateItem.setLayoutManager(new LinearLayoutManager(FindCandidate.this, LinearLayoutManager.VERTICAL,false));
            binding.candidateItem.setHasFixedSize(true);
            binding.candidateItem.setAdapter(candidateAdapter);
        }
        else {
            Toast.makeText(this, "No Candidate ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnClicked(String category) {
        candidateArrayList.clear();
        fetchData("",category,uid);
    }
}