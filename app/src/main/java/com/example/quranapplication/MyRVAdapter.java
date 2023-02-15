package com.example.quranapplication;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class myRecyclerViewAdapter extends RecyclerView.Adapter<myRecyclerViewAdapter.MyVH> {

    List<Integer> paraList;
    public myRecyclerViewAdapter(List<Integer> paraList) {
        this.paraList = paraList;
    }

    @NonNull
    @Override
    public myRecyclerViewAdapter.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_para, parent, false);
        return new MyVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myRecyclerViewAdapter.MyVH holder, int position) {
        holder.data=paraList.get(position);
        holder.para_no.setText(holder.data.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DisplayParaActivity.class);
                intent.putExtra("id",holder.para_no.getText() );
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return paraList.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        TextView para_no;
        Integer data;
        public MyVH(@NonNull View itemView) {
            super(itemView);
            para_no = itemView.findViewById(R.id.surahname);
        }
    }
}