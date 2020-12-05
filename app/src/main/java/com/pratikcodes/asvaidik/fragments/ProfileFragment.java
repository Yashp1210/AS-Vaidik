package com.pratikcodes.asvaidik.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pratikcodes.asvaidik.R;


public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }
    TextInputEditText edtmail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        edtmail = view.findViewById(R.id.mailet);
        SharedPreferences pref = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        String dmail = pref.getString("email","");
        edtmail.setText(dmail);

        return view;
    }
}