package com.pratikcodes.asvaidik.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pratikcodes.asvaidik.R;
import com.pratikcodes.asvaidik.adapters.HomeAdapter;
import com.pratikcodes.asvaidik.models.HomeModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    RecyclerView rv;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rv = view.findViewById(R.id.list);
        ArrayList<HomeModel> arrayList = new ArrayList<>();

        arrayList.add(new HomeModel("Requirements",R.drawable.req));
        arrayList.add(new HomeModel("Our Team",R.drawable.team));
        arrayList.add(new HomeModel("Contact Us",R.drawable.contact));
        arrayList.add(new HomeModel("Testimonials",R.drawable.testimonials));

        HomeAdapter adapter = new HomeAdapter(arrayList,getContext());
        rv.setAdapter(adapter);

        GridLayoutManager layout = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layout);

        return view;
    }
}