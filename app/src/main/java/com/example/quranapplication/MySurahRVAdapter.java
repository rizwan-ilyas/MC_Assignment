package com.example.quranapplication;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class mySurahRecyclerViewAdapter extends RecyclerView.Adapter<mySurahRecyclerViewAdapter.MySVH> {
    List<String> surahList;
    public mySurahRecyclerViewAdapter(List<String> surahList) {
        this.surahList = surahList;
    }

    @Override
    public mySurahRecyclerViewAdapter.MySVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_surah, parent, false);
        return new mySurahRecyclerViewAdapter.MySVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull mySurahRecyclerViewAdapter.MySVH holder, int position) {
        holder.data=surahList.get(position);
        holder.surah_name.setText(holder.data.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),DisplaySurahActivity.class);
                intent.putExtra("surah_name", holder.data);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return surahList.size();
    }

    public class MySVH extends RecyclerView.ViewHolder {
        TextView surah_name;
        String data;
        public MySVH(@NonNull View itemView) {
            super(itemView);
            surah_name = itemView.findViewById(R.id.surahname);
        }
    }
}
