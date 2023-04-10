package com.example.occupeye;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AA_RecyclerviewAdapter extends RecyclerView.Adapter<AA_RecyclerviewAdapter.MyViewHolder> {
    Context context;
    ArrayList<CategoryCreatorModel> creatorModel;
    Bookmark bookmark=Bookmark.getBookmark();


    public AA_RecyclerviewAdapter(Context context, ArrayList<CategoryCreatorModel> creatorModel) {
        this.context = context;

        this.creatorModel=creatorModel;

    }

    @NonNull
    @Override
    public AA_RecyclerviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.text,parent,false);

        return new AA_RecyclerviewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AA_RecyclerviewAdapter.MyViewHolder holder, int position) {
        holder.background.setImageResource(creatorModel.get(position).image);
        holder.roomName.setText(creatorModel.get(position).roomName);
        CategoryCreatorModel bookmarkPot=new CategoryCreatorModel(creatorModel.get(position).roomName,creatorModel.get(position).image);
        holder.bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(holder.bookmarkButton.getText().toString()=="BOOKMARKED"){
                    holder.bookmarkButton.setText("ADD TO BOOKMARKS");
                    Bookmark.getBookmarkedLocs().remove(bookmarkPot);
                }else{
                    holder.bookmarkButton.setText("BOOKMARKED");
                    Bookmark.getBookmarkedLocs().add(bookmarkPot);

                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return creatorModel.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView background;
        TextView roomName;
        Button bookmarkButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            roomName=itemView.findViewById(R.id.roomname);
            background=itemView.findViewById(R.id.background_layout);
            bookmarkButton=itemView.findViewById(R.id.buttonbookmark);

        }
    }
}
