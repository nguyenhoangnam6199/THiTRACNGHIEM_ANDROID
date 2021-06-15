package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.retrofitdemo.adapter.ThongTinAdapter;
import com.example.retrofitdemo.model.ThongTin;

import java.util.ArrayList;
import java.util.List;

public class ThongTinActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ThongTinAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);

        recyclerView = findViewById(R.id.recycleView);

        setData();
    }

    private void setData() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ThongTinAdapter(this,getDS());
        recyclerView.setAdapter(adapter);
    }

    private List<ThongTin> getDS() {
        List<ThongTin> ds = new ArrayList<>();
        ds.add(new ThongTin("1","Nguyễn Hoàng Nam","N17DCCN102"));
        ds.add(new ThongTin("2","Dương Tấn Phát","N17DCCN121"));
        ds.add(new ThongTin("3","Trần Hoài Nam","N17DCCN106"));
        ds.add(new ThongTin("4","Võ Trọng Minh","N17DCCN097"));
        return ds;
    }
}
