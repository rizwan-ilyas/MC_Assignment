package com.example.quranapplication;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class myParaRVAdapter extends RecyclerView.Adapter<myParaRVAdapter.MyRVH>{
    List<Ayah> ayahList;
    public myParaRVAdapter(List<Ayah> ayahList) {
        this.ayahList = ayahList;
    }

    @NonNull
    @Override
    public myParaRVAdapter.MyRVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_full_para, parent, false);
        return new myParaRVAdapter.MyRVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myParaRVAdapter.MyRVH holder, int position) {
        Ayah a =ayahList.get(position);
        holder.ayah.setText(a.ArabicText);
        holder.translation.setText(a.Translation);
    }

    @Override
    public int getItemCount() {
        return ayahList.size();
    }

    public class MyRVH extends RecyclerView.ViewHolder {
        TextView ayah;
        TextView translation;
        public MyRVH(@NonNull View itemView) {
            super(itemView);
            ayah = itemView.findViewById(R.id.ayah);
            translation = itemView.findViewById(R.id.ayah_translation);
        }
    }
}
