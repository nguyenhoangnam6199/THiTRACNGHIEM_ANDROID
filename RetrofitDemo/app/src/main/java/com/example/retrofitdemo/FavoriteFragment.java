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
import com.example.retrofitdemo.adapter.BoDe2Adapter;
import com.example.retrofitdemo.model.FavoriteQuestion;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {

    RecyclerView recyclerView;
    List<FavoriteQuestion> dsYeuThich= new ArrayList<>();
    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dsYeuThich=(List<FavoriteQuestion>) KetQua.dsyeuthich;
        //Hiện giao diện
        View itemView = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = itemView.findViewById(R.id.dsBoDe);

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),
                2));

        BoDe2Adapter adapter = new BoDe2Adapter(getContext(),dsYeuThich);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return itemView;
    }


}
