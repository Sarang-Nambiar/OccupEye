package com.example.occupeye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.occupeye.Adapters.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomPage extends AppCompatActivity {
    SliderView sliderView;
    int[] images = new int[3];
    private Map<String, List<Integer>> mContent = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_explore);

        List<Integer> contentBlock57 = new ArrayList<>();
        contentBlock57.add(R.drawable.studyroom);
        contentBlock57.add(R.string.studyrooms);
        contentBlock57.add(R.drawable.studyroom1);
        contentBlock57.add(R.drawable.studyroom2);
        contentBlock57.add(R.drawable.studyroom3);
        mContent.put("Block 57", contentBlock57);

        List<Integer> contentBlock59 = new ArrayList<>();
        contentBlock59.add(R.drawable.studyroom1);
        contentBlock59.add(R.string.studyrooms);
        contentBlock59.add(R.drawable.studyroom);
        contentBlock59.add(R.drawable.studyroom2);
        contentBlock59.add(R.drawable.studyroom3);
        mContent.put("Block 59", contentBlock59);

        List<Integer> contentBlock55 = new ArrayList<>();
        contentBlock55.add(R.drawable.studyroom2);
        contentBlock55.add(R.string.studyrooms);
        contentBlock55.add(R.drawable.studyroom1);
        contentBlock55.add(R.drawable.studyroom);
        contentBlock55.add(R.drawable.studyroom3);
        mContent.put("Block 55", contentBlock55);

        List<Integer> contentHostel = new ArrayList<>();
        contentHostel.add(R.drawable.hostelroom);
        contentHostel.add(R.string.hostel);
        contentHostel.add(R.drawable.hostel1);
        contentHostel.add(R.drawable.hostel2);
        contentHostel.add(R.drawable.hostel3);
        mContent.put("SUTD HOSTEL", contentHostel);

        List<Integer> contentLibrary = new ArrayList<>();
        contentLibrary.add(R.drawable.library);
        contentLibrary.add(R.string.library);
        contentLibrary.add(R.drawable.library1);
        contentLibrary.add(R.drawable.library2);
        contentLibrary.add(R.drawable.library3);
        mContent.put("SUTD LIBRARY", contentLibrary);


        Intent intent = getIntent();
        String title = intent.getStringExtra("roomName");

        List<Integer> contentList = mContent.get(title);

        TextView mainText = findViewById(R.id.maintext);
        mainText.setText(title);
        System.out.println(title);
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();

        ImageView mainImage = findViewById(R.id.topimage);
        mainImage.setImageResource(contentList.get(0));

        TextView descText = findViewById(R.id.desc1);
        descText.setText(contentList.get(1));

        images[0] = contentList.get(2);
        images[1] = contentList.get(3);
        images[2] = contentList.get(4);

        Button bookmark = findViewById(R.id.buttonbookmark);
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bookmark.getText().toString().equals("BOOKMARKED")) {
                    bookmark.setText("ADD TO BOOKMARKS");
                } else {
                    bookmark.setText("BOOKMARKED");
                }
            }
        });
    }
}



