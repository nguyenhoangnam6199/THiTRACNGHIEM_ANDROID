package com.example.retrofitdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofitdemo.adapter.BoDe1Adapter;
import com.example.retrofitdemo.adapter.BoDe1AdapterNew;
import com.example.retrofitdemo.model.BoDe;

import java.util.ArrayList;
import java.util.List;


public class StudyFragment extends Fragment {

    RecyclerView recyclerView;
    List<BoDe> dsBoDe= new ArrayList<>();


    public StudyFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dsBoDe = (ArrayList<BoDe>) KetQua.dsbode;
        //Hiện giao diện
        View itemView = inflater.inflate(R.layout.fragment_study, container, false);
        recyclerView = itemView.findViewById(R.id.dsBoDe);

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),
                2));

        //BoDe1Adapter adapter = new BoDe1Adapter(getContext(),dsBoDe);
        BoDe1AdapterNew adapter = new BoDe1AdapterNew(getContext(),dsBoDe);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return itemView;
    }


}
