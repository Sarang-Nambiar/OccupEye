package com.example.occupeye.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.occupeye.Bookmark;
import com.example.occupeye.CategoryCreatorModel;
import com.example.occupeye.Fragments.UserFragment;
import com.example.occupeye.R;

import java.util.ArrayList;

public class myRvAdapter extends RecyclerView.Adapter<myRvAdapter.myHolder> {
    Context context;
    ArrayList<CategoryCreatorModel> creatorModel;
    Bookmark bookmark;

    public myRvAdapter(Context context, ArrayList<CategoryCreatorModel> categoryCreatorModels, Bookmark bookmark){
        this.creatorModel = categoryCreatorModels;
        this.context = context;
        this.bookmark = bookmark;
    }
    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.rv_item, parent, false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        holder.tvTitle.setText(creatorModel.get(position).getRoomName());
        holder.background.setImageResource(creatorModel.get(position).getImage());
        CategoryCreatorModel bookmarkPot=new CategoryCreatorModel(creatorModel.get(position).getRoomName(),creatorModel.get(position).getImage(),creatorModel.get(position).getColour());
        holder.bookmarkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(Bookmark.getBookmarkedLocs());
                Bookmark.getBookmarkedLocs().remove(bookmarkPot);
            }
        });
    }

    @Override
    public int getItemCount() {
        return creatorModel.size();
    }

    public class myHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView background;
        Button bookmarkbtn;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.roomnameRv);
            bookmarkbtn = itemView.findViewById(R.id.bookmarkbtnRv);
        }
    }

    public Bookmark getBookmark(){
        return bookmark;
    }

}
