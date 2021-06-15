package com.example.retrofitdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.retrofitdemo.model.Statistic;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Statistic3Activity extends AppCompatActivity {

    BarChart barChart;
    ArrayList<BarEntry> barEntryArrayList;
    ArrayList<String> labelsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic3);
        barChart = findViewById(R.id.mbarChart3);
        barEntryArrayList = new ArrayList<>();
        labelsName = new ArrayList<>();
        List<Statistic> s=AdminActivity.listStatistic;
        for(int i=0; i<s.size(); i++){
            barEntryArrayList.add(new BarEntry(i,s.get(i).getQuantityTime()));
            labelsName.add(s.get(i).getName());
        }

        BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Tổng thời gian thi");
        barDataSet.setColors(Color.RED);
        Description description = new Description();
        description.setText("Thời gian");
        barChart.setDescription(description);
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        //Set dinh dang cho labelsName
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelsName));
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labelsName.size());
        xAxis.setLabelRotationAngle(270);

        barChart.animateY(2000);
        barChart.invalidate();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_thoat, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.thoat:
                Intent i = new Intent(Statistic3Activity.this, AdminActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
