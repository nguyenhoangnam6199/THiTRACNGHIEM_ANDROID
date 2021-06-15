package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.retrofitdemo.adapter.HistoryAdapter;
import com.example.retrofitdemo.adapter.ViewPageAdapter;
import com.example.retrofitdemo.model.ListQuestion;
import com.example.retrofitdemo.model.QuestionHD;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private HistoryAdapter viewPagerAdapter;
    private ViewPager viewPager;
    Button btnOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        viewPager = findViewById(R.id.viewpager);
        btnOut = findViewById(R.id.btnOut);

        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String kq=KetQua.chuoi;
                Intent i=new Intent(HistoryActivity.this,HelloActivity.class);
                //i.putExtra("chuoi",kq);
                startActivity(i);
            }
        });

        viewPagerAdapter = new HistoryAdapter(getSupportFragmentManager());
        for(int i=0; i<KetQua.historyDetail.getListQuestion().size(); i++){
            QuestionHD questionHD=KetQua.historyDetail.getListQuestion().get(i);
//            long loai=questionHD.getType().id;
//            Bundle bundle = new Bundle();
//            bundle.putLong("id",questionHD.getId());
//            bundle.putLong("loai",questionHD.getType().id);
//            bundle.putString("content",questionHD.getContent());
//            if(questionHD.getDescription()!=null/*||!questionHD.getDescription().equalsIgnoreCase("")*/){
//                bundle.putString("description",questionHD.getDescription());
//            }
//            if(questionHD.getPhoto()!=null/*||!questionHD.getPhoto().equalsIgnoreCase("")*/){
//                bundle.putString("photo",questionHD.getPhoto());
//            }
//            if(questionHD.getAnswer()!=null/*||!questionHD.getAnswer().equalsIgnoreCase("")*/){
//                bundle.putString("answer",questionHD.getAnswer());
//            }else bundle.putString("answer","");
//            if(questionHD.getUserAnswer()!=null/*||!questionHD.getUserAnswer().equalsIgnoreCase("")*/){
//                bundle.putString("useranswer",questionHD.getUserAnswer());
//            }else bundle.putString("useranswer","");
            if(!questionHD.getDetails().isEmpty()){
                for(int j=0; j<questionHD.getDetails().size(); j++) {
                    long loai=questionHD.getType().id;
                    Bundle bundle = new Bundle();
                    bundle.putLong("id",questionHD.getId());
                    bundle.putLong("loai",questionHD.getType().id);
                    bundle.putString("content",questionHD.getContent());
                    if(questionHD.getDescription()!=null/*||!questionHD.getDescription().equalsIgnoreCase("")*/){
                        bundle.putString("description",questionHD.getDescription());
                    }
                    if(questionHD.getPhoto()!=null/*||!questionHD.getPhoto().equalsIgnoreCase("")*/){
                        bundle.putString("photo",questionHD.getPhoto());
                    }
                    if(questionHD.getAnswer()!=null/*||!questionHD.getAnswer().equalsIgnoreCase("")*/){
                        bundle.putString("answer",questionHD.getAnswer());
                    }else bundle.putString("answer","");
                    if(questionHD.getUserAnswer()!=null/*||!questionHD.getUserAnswer().equalsIgnoreCase("")*/){
                        bundle.putString("useranswer",questionHD.getUserAnswer());
                    }else bundle.putString("useranswer","");
                    bundle.putString("description", questionHD.getDetails().get(j).getDescription());
                    bundle.putString("useranswer", questionHD.getDetails().get(j).getUserAnswer());
                    bundle.putString("answer", questionHD.getDetails().get(j).getAnswer());

                    bundle.putString("detailcontent", questionHD.getDetails().get(j).getContent());
                    bundle.putString("a", questionHD.getDetails().get(j).getA());
                    bundle.putString("b", questionHD.getDetails().get(j).getB());
                    bundle.putString("c", questionHD.getDetails().get(j).getC());
                    bundle.putString("d", questionHD.getDetails().get(j).getD());
                    HistoryDetailFragment hdFragment = new HistoryDetailFragment();
                    hdFragment.setArguments(bundle);
                    viewPagerAdapter.add(hdFragment,"");
                }
            }
            else{
                Bundle bundle = new Bundle();
                bundle.putLong("id",questionHD.getId());
                bundle.putLong("loai",questionHD.getType().id);
                bundle.putString("content",questionHD.getContent());
                if(questionHD.getDescription()!=null/*||!questionHD.getDescription().equalsIgnoreCase("")*/){
                    bundle.putString("description",questionHD.getDescription());
                }
                if(questionHD.getPhoto()!=null/*||!questionHD.getPhoto().equalsIgnoreCase("")*/){
                    bundle.putString("photo",questionHD.getPhoto());
                }
                if(questionHD.getAnswer()!=null/*||!questionHD.getAnswer().equalsIgnoreCase("")*/){
                    bundle.putString("answer",questionHD.getAnswer());
                }else bundle.putString("answer","");
                if(questionHD.getUserAnswer()!=null/*||!questionHD.getUserAnswer().equalsIgnoreCase("")*/){
                    bundle.putString("useranswer",questionHD.getUserAnswer());
                }else bundle.putString("useranswer","");
                HistoryDetailFragment hdFragment = new HistoryDetailFragment();
                hdFragment.setArguments(bundle);
                viewPagerAdapter.add(hdFragment,"");
            }
//            HistoryDetailFragment hdFragment = new HistoryDetailFragment();
//            hdFragment.setArguments(bundle);
//            viewPagerAdapter.add(hdFragment,"");
        }
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
