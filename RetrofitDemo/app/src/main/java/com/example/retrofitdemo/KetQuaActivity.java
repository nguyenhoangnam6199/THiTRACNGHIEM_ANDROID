package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdemo.api.APIService;
import com.example.retrofitdemo.model.FavoriteQuestion;
import com.example.retrofitdemo.model.HistoryTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KetQuaActivity extends AppCompatActivity {

    TextView tv,tv1;
    Button btnThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);
        Intent i=getIntent();

        String kq=i.getStringExtra("ketqua");

        String dachon="";
        for(int in=0;in<KetQua.idList.size();in++){
            dachon+="Câu "+(in+1)+": "+KetQua.listSelected.get(in)+"\n";
        }

        tv=findViewById(R.id.txt_KQ);
        tv1=findViewById(R.id.txt_DACHON);
        tv1.setText(dachon);
        tv.setText(kq);
        btnThoat = findViewById(R.id.btnThoat);

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIService.apiService.getFavorite("Bearer "+KetQua.token).enqueue(new Callback<ArrayList<FavoriteQuestion>>() {
                    @Override
                    public void onResponse(Call<ArrayList<FavoriteQuestion>> call, Response<ArrayList<FavoriteQuestion>> response) {
                        ArrayList<FavoriteQuestion>list=new ArrayList<>();
                        for (FavoriteQuestion b:response.body()) {
                            list.add(b);
                        }
                        KetQua.dsyeuthich=list;

                        APIService.apiService.getHistoryTests("Bearer "+KetQua.token).enqueue(new Callback<ArrayList<HistoryTest>>() {
                            @Override
                            public void onResponse(Call<ArrayList<HistoryTest>> call, Response<ArrayList<HistoryTest>> response) {
                                ArrayList<HistoryTest>list=new ArrayList<>();
                                for (HistoryTest b:response.body()) {
                                    Instant bd= Instant.parse(b.getCreationDateTime().toString());
                                    Instant kt= Instant.parse(b.getSubmitDateTime().toString());
                                    bd=bd.plus(7, ChronoUnit.HOURS);
                                    kt=kt.plus(7,ChronoUnit.HOURS);
                                    String t1=bd.toString();
                                    String t2=kt.toString();
                                    b.setCreationDateTime(t1);
                                    b.setSubmitDateTime(t2);
                                    list.add(b);
                                }
                                KetQua.dslichsu=list;
                                Intent intent = new Intent(KetQuaActivity.this,HelloActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<ArrayList<HistoryTest>> call, Throwable t) {
                                Toast.makeText(KetQuaActivity.this,"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<ArrayList<FavoriteQuestion>> call, Throwable t) {
                        Toast.makeText(KetQuaActivity.this,"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
