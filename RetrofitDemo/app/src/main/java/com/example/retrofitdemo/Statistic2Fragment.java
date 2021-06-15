package com.example.retrofitdemo;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;


public class Statistic2Fragment extends Fragment {
    BarChart barChart;
    List<String> dsbode=new ArrayList<>();
    List<Float> socaudung=new ArrayList<>();
    List<Float> socausai=new ArrayList<>();

    BarDataSet barDataSet1, barDataSet2, barDataSet3;

    ArrayList barEntries;
    public Statistic2Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.fragment_statistic2, container, false);
        barChart = itemView.findViewById(R.id.mbarChart2);

        for(int i=0;i<AdminActivity.listStatistic.size();i++){
            dsbode.add(AdminActivity.listStatistic.get(i).getName());
            socaudung.add((float)AdminActivity.listStatistic.get(i).getQuantityCorrect());
            socausai.add((float)AdminActivity.listStatistic.get(i).getQuantityIncorrect());
        }
        barChart = itemView.findViewById(R.id.mbarChart2);

        barDataSet1 = new BarDataSet(getBarEntriesOne(), "Tổng Số Câu Đúng");
        barDataSet1.setColor(Color.GREEN);
        barDataSet2 = new BarDataSet(getBarEntriesTwo(), "Tổng Số Câu Sai");
        barDataSet2.setColor(Color.BLUE);

        // Thiết lập dữ liệu và đồ họa cho biểu đồ
        BarData data = new BarData(barDataSet1, barDataSet2);
        barChart.setData(data);
        barChart.getDescription().setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(dsbode));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(3);
        float barSpace = 0.1f;
        float groupSpace = 0.5f;
        data.setBarWidth(0.15f);
        barChart.getXAxis().setAxisMinimum(0);
        barChart.animate();
        barChart.groupBars(0, groupSpace, barSpace);
        barChart.animateY(2000);
        barChart.invalidate();

        return itemView;
    }

    //mảng số đề nộp
    private ArrayList<BarEntry> getBarEntriesOne() {

        barEntries = new ArrayList<>();
        for(int i=1; i<=socaudung.size(); i++){
            barEntries.add(new BarEntry((float) i,socaudung.get(i-1)));
        }
        return barEntries;
    }

    // mảng số đề chưa nộp
    private ArrayList<BarEntry> getBarEntriesTwo() {
        barEntries = new ArrayList<>();
        for(int i=1; i<=socausai.size(); i++){
            barEntries.add(new BarEntry((float)i,socausai.get(i-1)));
        }
        return barEntries;
    }
}
