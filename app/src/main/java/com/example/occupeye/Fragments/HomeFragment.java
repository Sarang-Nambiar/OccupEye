package com.example.occupeye.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.occupeye.AA_RecyclerviewAdapter;
import com.example.occupeye.Bookmark;
import com.example.occupeye.CategoryCreatorModel;
import com.example.occupeye.R;
import com.example.occupeye.RecyclerItemClickListener;
import com.example.occupeye.RoomPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment{
    View rootView;
    Button loginTester;

    Button registerTester;
    ArrayList<CategoryCreatorModel> categoryModel=new ArrayList<>();
    View hostelselbtn;
    View allselbtn;
    View collegeselbtn;
    View libselbtn;
    RecyclerView recyclerView;
    ArrayList<String> roomName;
    Bookmark bookmark=Bookmark.getBookmark();

    FirebaseDatabase database;
    DatabaseReference myRef;

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

        Log.d("label", "msg1");
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://occupeye-dedb8-default-rtdb.asia-southeast1.firebasedatabase.app");
        myRef = database.getReference("Locations");


        //INITIALISING DOM ELEMENTS
        loginTester=rootView.findViewById(R.id.login);
        hostelselbtn=rootView.findViewById(R.id.category_sel_hostel);
        allselbtn=rootView.findViewById(R.id.category_sel_all);
        libselbtn=rootView.findViewById(R.id.category_sel_lib);
        collegeselbtn=rootView.findViewById(R.id.category_sel_college);
        recyclerView = rootView.findViewById(R.id.myRecyclerView);
        //SETTING UP DEFAULT DISPLAY ITEMS
        setUpCategoryModel("all");
        setUpRecyclerView();

        //CATEGORY SELECTORS
        hostelselbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("HOSTELSEL", "HOSTEL ROOMS ONLY");
                setUpCategoryModel("hostel");






                Log.d("label2","msg2");

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
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), RoomPage.class));
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        return rootView;
    }

    public void setUpRecyclerView(){
        //Setting up the recycler view
        recyclerView=rootView.findViewById(R.id.myRecyclerView);
        AA_RecyclerviewAdapter adapter=new AA_RecyclerviewAdapter(rootView.getContext(),categoryModel);
        recyclerView.setAdapter(adapter);


        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(
                rootView.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView.setLayoutManager(HorizontalLayout);
    }

    private void setUpCategoryModel(String buttonName){
        Log.d("settingup","settingupmodels");

        if (buttonName=="all"){
            roomName = new ArrayList<>();
            ArrayList<Integer> imageno = new ArrayList<>();


            categoryModel.clear();

            for(int i=0;i<roomName.size();i++){
                categoryModel.add(new CategoryCreatorModel(roomName.get(i),imageno.get(i)));
            }
            System.out.println(Bookmark.getBookmarkedLocs());
        } else if (buttonName=="hostel") {

            Log.d("settingup2","settingupmodels2");
            ArrayList<String> roomName = new ArrayList<>();
            int[] imageno={R.drawable.hostel_img};
            System.out.println(myRef);
            myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {Toast.makeText(getContext(),"Unable to get data",Toast.LENGTH_SHORT).show();}
                    if (task.isSuccessful()){
                        Log.d("settingup3","settingupmodels3");
                        if(task.getResult().exists()){
                            DataSnapshot dataSnapshot = task.getResult();
                            HashMap<String,String>data= (HashMap<String, String>) task.getResult().getValue();
                            for ( String key : data.keySet() ) {
                                Log.d("label3", String.valueOf(key));
                                Log.d("label4", "msg4");
                                roomName.add(String.valueOf(key));
                            }
                            for(int i=0;i<roomName.size();i++){
                                categoryModel.add(new CategoryCreatorModel(roomName.get(i),imageno[0]));
                            }
                            setUpRecyclerView();

                        }
                    }else{
                        Log.d("label6","myRef");
                    }
                }
            });



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