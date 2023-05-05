package com.example.supporthireapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.supporthireapp.Adapter.CandidateAdapter;
import com.example.supporthireapp.Database.CandidateData;
import com.example.supporthireapp.Model.Candidate;
import com.example.supporthireapp.R;
import com.example.supporthireapp.databinding.ActivityListCandidateBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ListCandidate extends AppCompatActivity {
    ActivityListCandidateBinding binding;
    ActionBarDrawerToggle drawerToggle;
    CandidateAdapter candidateAdapter;
    ArrayList<Candidate> candidates;
    CandidateData candidateData;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListCandidateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        candidates = new ArrayList<>();
        candidateAdapter = new CandidateAdapter(candidates,this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.recyclerView.setAdapter(candidateAdapter);

        uid = getIntent().getStringExtra("uid");
        candidateData = new CandidateData(this);
        list_item_menu();
        Candidate();
    }
    public void list_item_menu(){
        drawerToggle = new ActionBarDrawerToggle(this, binding.drawerlayout, R.string.open,R.string.close);
        binding.drawerlayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();
        binding.navItem.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.Home){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class)
                            .putExtra("username", uid));
                }
                if (id == R.id.contact) {
                    Toast.makeText(ListCandidate.this, "Contact", Toast.LENGTH_SHORT).show();
                }
                if(id == R.id.list_candidate){
                    Toast.makeText(ListCandidate.this, "List", Toast.LENGTH_SHORT).show();
                }
                if (id == R.id.find_candidate) {
                    startActivity(new Intent(getApplicationContext(), FindCandidate.class)
                            .putExtra("uid", uid));;
                }
                if (id == R.id.personal_data) {
                    Toast.makeText(ListCandidate.this, "Personal", Toast.LENGTH_SHORT).show();
                }
                if (id == R.id.logout){
                    startActivity(new Intent(getApplicationContext(), LoginAndSignUp.class));
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerlayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerlayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    public void Candidate(){
        Cursor cursor = candidateData.DataDetail(uid);
        if (cursor.getCount()<1){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                candidates.add(new Candidate(cursor.getString(0), cursor.getString(1), cursor.getString(3),cursor.getString(4), cursor.getString(2), cursor.getString(5),cursor.getString(6),cursor.getString(7)));
            }
            candidateAdapter.notifyDataSetChanged();
        }
    }

}