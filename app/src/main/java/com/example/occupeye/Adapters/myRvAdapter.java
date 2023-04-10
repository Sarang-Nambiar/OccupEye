package com.example.occupeye.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.occupeye.Fragments.UserFragment;
import com.example.occupeye.R;

import java.util.ArrayList;

public class myRvAdapter extends RecyclerView.Adapter<myRvAdapter.myHolder> {
    ArrayList<String> data;
    Context context;

    public myRvAdapter(Context context, ArrayList<String> stringArrayList){
        this.data = stringArrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.rv_item, parent, false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        holder.tvTitle.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class myHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.roomnameRv);

        }
    }

}
