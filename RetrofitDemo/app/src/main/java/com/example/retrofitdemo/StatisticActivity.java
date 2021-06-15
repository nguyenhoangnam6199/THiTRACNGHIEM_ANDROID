package com.example.retrofitdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class StatisticActivity extends AppCompatActivity {


    BarChart barChart;
    List<String> dsbode=new ArrayList<>();
    List<Float> sodenop=new ArrayList<>();
    List<Float> sodekonop=new ArrayList<>();

    BarDataSet barDataSet1, barDataSet2, barDataSet3;

    ArrayList barEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        for(int i=0;i<AdminActivity.listStatistic.size();i++){
            dsbode.add(AdminActivity.listStatistic.get(i).getName());
            sodenop.add((float)AdminActivity.listStatistic.get(i).getQuantityTesting());
            sodekonop.add((float)AdminActivity.listStatistic.get(i).getQuantityNotSubmit());
        }
        barChart = findViewById(R.id.mbarChart);

        barDataSet1 = new BarDataSet(getBarEntriesOne(), "Tổng Số Lần Thi");
        barDataSet1.setColor(getApplicationContext().getResources().getColor(R.color.colorPrimaryDark));
        barDataSet2 = new BarDataSet(getBarEntriesTwo(), "Tổng Số Lần Thi Không Nộp");
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

    }

    //mảng số đề nộp
    private ArrayList<BarEntry> getBarEntriesOne() {

        barEntries = new ArrayList<>();
        for(int i=1; i<=sodenop.size(); i++){
            barEntries.add(new BarEntry((float) i,sodenop.get(i-1)));
        }
        return barEntries;
    }

    // mảng số đề chưa nộp
    private ArrayList<BarEntry> getBarEntriesTwo() {
        barEntries = new ArrayList<>();
        for(int i=1; i<=sodekonop.size(); i++){
            barEntries.add(new BarEntry((float)i,sodekonop.get(i-1)));
        }
        return barEntries;
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
                Intent i = new Intent(StatisticActivity.this, AdminActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
