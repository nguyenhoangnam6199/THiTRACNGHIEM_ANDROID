package com.example.retrofitdemo;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofitdemo.model.Statistic;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;


public class Statistic3Fragment extends Fragment {

    BarChart barChart;
    ArrayList<BarEntry> barEntryArrayList;
    ArrayList<String> labelsName;
    public Statistic3Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.fragment_statistic3, container, false);
        barChart = itemView.findViewById(R.id.mbarChart);

        barChart = itemView.findViewById(R.id.mbarChart3);
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

        return itemView;
    }


}
