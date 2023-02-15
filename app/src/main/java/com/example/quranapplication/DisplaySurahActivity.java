package com.example.quranapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DisplaySurahActivity extends AppCompatActivity {
    List<Ayah> surahList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    JsonHelper jsonHelper;
    JSONArray arr;
    Spinner trans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_surah);
        Intent intent = getIntent();
        String surahName = intent.getStringExtra("surah_name");
        String [] parts = surahName.split(" -- ");
        Integer surahId = Integer.parseInt(parts[0]);
        trans = findViewById(R.id.s_translation_sp);
        String[] arraySpinner = new String[] {
                "UrduTranslation", "EnglishTranslation", "SindhiTranslation", "HindiTranslation", "PushtoTransation",
        };
        ArrayAdapter translationAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, arraySpinner);
        translationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        trans.setAdapter(translationAdapter);
        trans.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                surahList.clear();
                for (int j = 0; j < arr.length(); j++)
                {
                    try {
                        if (surahId == arr.getJSONObject(j).getInt("surah_number"))
                        {
                            Ayah ayat = new Ayah(arr.getJSONObject(j).getString("text"),arr.getJSONObject(j).getString(trans.getSelectedItem().toString()));
                            surahList.add(ayat);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("except", "getJsonFromAssets: " + e.getMessage());
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        jsonHelper = new JsonHelper("QuranMetaData.json", this);
        try {
            arr = new JSONArray(jsonHelper.getJsonFromAssets());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("except", "getJsonFromAssets: " + e.getMessage());
        }
        for (int i = 0; i < arr.length(); i++)
        {
            try {
                if (arr.getJSONObject(i).getInt("surah_number")==surahId)
                {
                    Ayah ayat = new Ayah(arr.getJSONObject(i).getString("text"),arr.getJSONObject(i).getString(trans.getSelectedItem().toString()));
                    surahList.add(ayat);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("except", "getJsonFromAssets: " + e.getMessage());
            }
        }
        recyclerView = findViewById(R.id.parah_rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(DisplaySurahActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new myParaRVAdapter(surahList);
        recyclerView.setAdapter(adapter);
    }
}