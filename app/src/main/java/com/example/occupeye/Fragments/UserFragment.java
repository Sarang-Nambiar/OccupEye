package com.example.occupeye.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.occupeye.EditPage;
import com.example.occupeye.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button editprofilebtn;
    private TextView username;
    private TextView term;
    private TextView pillar;
    private TextView hostelBlock;
    private TextView hostelResident;

    private View rootView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView pfp;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
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
//        Intent i = new Intent();
//        String name = i.getStringExtra("username");
//        String spillar = i.getStringExtra("pillar");
//        String sterm = i.getStringExtra("term");
//        String shostelBlock = i.getStringExtra("hostelBlock");
//        String shostelResident = i.getStringExtra("hostelResident");
        pfp = rootView.findViewById(R.id.profile_image);
//        username = rootView.findViewById(R.id.nametxt);
//        pillar = rootView.findViewById(R.id.pillartxt);
//        term = rootView.findViewById(R.id.termtxt);
//        hostelBlock = rootView.findViewById(R.id.blocktxt);
//        hostelResident = rootView.findViewById(R.id.hosteltext);

//        username.setText(name);
//        pillar.setText(spillar);
//        term.setText(sterm);
//        hostelBlock.setText(shostelBlock);
//        hostelResident.setText(shostelResident);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         rootView = inflater.inflate(R.layout.fragment_user, container, false);
         editprofilebtn = rootView.findViewById(R.id.editprofilebtn);
         editprofilebtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i = new Intent(getActivity(), EditPage.class);
                 startActivity(i);
                 FragmentManager fm = getActivity().getSupportFragmentManager();
                 FragmentTransaction ft = fm.beginTransaction();
                 ft.addToBackStack(UserFragment.class.getName()).commit();
                 fm.executePendingTransactions();
             }
         });
         return rootView;
    }
}