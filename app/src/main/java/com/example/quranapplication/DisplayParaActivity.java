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

public class DisplayParaActivity extends AppCompatActivity {
    List<Ayah> paraList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    JsonHelper jsonHelper;
    JSONArray arr;
    Spinner translation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_para_display);
        Intent intent = getIntent();
        Integer paraId = Integer.parseInt(intent.getStringExtra("id"));
        translation = findViewById(R.id.s_translation_sp);
        String[] arraySpinner = new String[] {
                "UrduTranslation", "EnglishTranslation", "SindhiTranslation", "HindiTranslation", "PushtoTransation",
        };
        ArrayAdapter translationAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, arraySpinner);
        translationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        translation.setAdapter(translationAdapter);
        translation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                paraList.clear();
                for (int j = 0; j < arr.length(); j++)
                {
                    try {
                        if (arr.getJSONObject(j).getInt("juz") == paraId)
                        {
                            Ayah ayat = new Ayah(arr.getJSONObject(j).getString("text"),arr.getJSONObject(j).getString(translation.getSelectedItem().toString()));
                            paraList.add(ayat);
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
                if (arr.getJSONObject(i).getInt("juz") == paraId)
                {
                    Ayah ayat = new Ayah(arr.getJSONObject(i).getString("text"),arr.getJSONObject(i).getString(translation.getSelectedItem().toString()));
                    paraList.add(ayat);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("except", "getJsonFromAssets: " + e.getMessage());
            }
        }
        recyclerView = findViewById(R.id.surah_rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(DisplayParaActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new myParaRVAdapter(paraList);
        recyclerView.setAdapter(adapter);
    }
}