package com.example.occupeye.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.occupeye.AA_RecyclerviewAdapter;
import com.example.occupeye.Bookmark;
import com.example.occupeye.CategoryCreatorModel;
import com.example.occupeye.Home;
import com.example.occupeye.Login;
import com.example.occupeye.R;
import com.example.occupeye.Register;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    View rootView;
    Button loginTester;

    Button registerTester;
    ArrayList<CategoryCreatorModel> categoryModel=new ArrayList<>();
    View hostelselbtn;
    View allselbtn;
    View collegeselbtn;
    View libselbtn;
    DatabaseReference myRef;
    Bookmark bookmark=Bookmark.getBookmark();

    HashMap<String,String> obj=new HashMap<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Location");


        //INITIALISING DOM ELEMENTS
        loginTester=rootView.findViewById(R.id.login);
        hostelselbtn=rootView.findViewById(R.id.category_sel_hostel);
        allselbtn=rootView.findViewById(R.id.category_sel_all);
        libselbtn=rootView.findViewById(R.id.category_sel_lib);
        collegeselbtn=rootView.findViewById(R.id.category_sel_college);
        registerTester=rootView.findViewById(R.id.register);
        //SETTING UP DEFAULT DISPLAY ITEMS
        setUpCategoryModel("all");
        setUpRecyclerView();

        //CATEGORY SELECTORS
        hostelselbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("HOSTELSEL", "HOSTEL ROOMS ONLY");
                setUpCategoryModel("hostel");
                setUpRecyclerView();
            }
        });
        collegeselbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("COLLEGESEL", "COLLEGE ROOMS ONLY");
                setUpCategoryModel("college");
                setUpRecyclerView();
            }
        });
        allselbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ALLSEL", "ALL ROOMS");
                setUpCategoryModel("all");
                setUpRecyclerView();
            }
        });
        libselbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LIBSEL", "LIB ROOMS ONLY");
                setUpCategoryModel("lib");
                setUpRecyclerView();
            }

        });
        return rootView;
    }
    private void setUpRecyclerView(){
        //Setting up the recycler view
        RecyclerView recyclerView=rootView.findViewById(R.id.myRecyclerView);
        AA_RecyclerviewAdapter adapter=new AA_RecyclerviewAdapter(rootView.getContext(),categoryModel);
        recyclerView.setAdapter(adapter);


        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(
                rootView.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView.setLayoutManager(HorizontalLayout);
    }

    private void setUpCategoryModel(String buttonName){


        if (buttonName=="all"){
            ArrayList<String> roomName = new ArrayList<>();
            ArrayList<Integer> imageno = new ArrayList<>();


            categoryModel.clear();

            for(int i=0;i<roomName.size();i++){
                categoryModel.add(new CategoryCreatorModel(roomName.get(i),imageno.get(i)));
            }
            System.out.println(Bookmark.getBookmarkedLocs());
        } else if (buttonName=="hostel") {
            categoryModel.clear();
            String[] roomName={"SUTD HOSTEL"};
            int[] imageno={R.drawable.hostel_img};
            for(int i=0;i<roomName.length;i++){
                categoryModel.add(new CategoryCreatorModel(roomName[i],imageno[i]));
            }

        }else if (buttonName=="lib"){
            categoryModel.clear();
            String[] roomName={"SUTD LIBRARY"};
            int[] imageno={R.drawable.lib_img};
            for(int i=0;i<roomName.length;i++){
                categoryModel.add(new CategoryCreatorModel(roomName[i],imageno[i]));
            }
        } else if (buttonName=="college") {
            categoryModel.clear();
            String[] roomName={"SUTD LIBRARY","SUTD COLLEGE"};
            int[] imageno={R.drawable.lib_img,R.drawable.college};

            for(int i=0;i<roomName.length;i++){

                categoryModel.add(new CategoryCreatorModel(roomName[i],imageno[i]));
            }

        }

    }
}