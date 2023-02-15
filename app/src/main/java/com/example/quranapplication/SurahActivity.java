package com.example.quranapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class SurahActivity extends AppCompatActivity {
    List<String> surahList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    QuranDataHelper qdh = new QuranDataHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah);
        for(int i=1;i<=114;i++)
        {
            surahList.add(Integer.toString(i) + " -- " + qdh.englishSurahNames[i-1]);
        }
        recyclerView = findViewById(R.id.surah_recycle_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(com.example.quranapplication.SurahActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new mySurahRecyclerViewAdapter(surahList) ;
        recyclerView.setAdapter(adapter);
    }
}