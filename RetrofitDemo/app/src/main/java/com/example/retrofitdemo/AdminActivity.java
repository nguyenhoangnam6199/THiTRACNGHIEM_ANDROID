package com.example.retrofitdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdemo.api.APIService;
import com.example.retrofitdemo.model.SignUpStatus;
import com.example.retrofitdemo.model.Statistic;
import com.example.retrofitdemo.model.TestCreation;
import com.example.retrofitdemo.model.TestElement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {
Button btn_them;
public static List<Statistic> listStatistic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        final List<TestElement> listChiTiet=new ArrayList<>();
        btn_them=findViewById(R.id.btnTaoBoDe);

        Button btn_thong_ke=findViewById(R.id.btnThongKe);
        btn_thong_ke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIService.apiService.statistic("Bearer "+KetQua.token).enqueue(new Callback<List<Statistic>>() {
                    @Override
                    public void onResponse(Call<List<Statistic>> call, Response<List<Statistic>> response) {

                        listStatistic=response.body();
                        Intent i=new Intent(AdminActivity.this, StatisticActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<List<Statistic>> call, Throwable t) {

                    }
                });

            }
        });
        //------------------------------------------------------------------
        Button btn_thong_ke2=findViewById(R.id.btnThongKe2);
        btn_thong_ke2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIService.apiService.statistic("Bearer "+KetQua.token).enqueue(new Callback<List<Statistic>>() {
                    @Override
                    public void onResponse(Call<List<Statistic>> call, Response<List<Statistic>> response) {

                        listStatistic=response.body();
                        Intent i=new Intent(AdminActivity.this, Statistic2Actitvity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<List<Statistic>> call, Throwable t) {

                    }
                });

            }
        });
        //------------------------------------------------------------------
        Button btn_thong_ke3=findViewById(R.id.btnThongKe3);
        btn_thong_ke3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIService.apiService.statistic("Bearer "+KetQua.token).enqueue(new Callback<List<Statistic>>() {
                    @Override
                    public void onResponse(Call<List<Statistic>> call, Response<List<Statistic>> response) {

                        listStatistic=response.body();
                        Intent i=new Intent(AdminActivity.this, Statistic3Activity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<List<Statistic>> call, Throwable t) {

                    }
                });

            }
        });
        //-------------------------------------------------------------------------
        Button btn_thong_ke4=findViewById(R.id.btnThongKe4);
        btn_thong_ke4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIService.apiService.statistic("Bearer "+KetQua.token).enqueue(new Callback<List<Statistic>>() {
                    @Override
                    public void onResponse(Call<List<Statistic>> call, Response<List<Statistic>> response) {

                        listStatistic=response.body();
                        Intent i=new Intent(AdminActivity.this, ThongKeActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<List<Statistic>> call, Throwable t) {

                    }
                });

            }
        });
        //----------------------------------------------------------------------------
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listChiTiet.clear();
                Intent i= new Intent(AdminActivity.this,ThemBoDeActivity.class);
                startActivity(i);
            }
        });
        Button btn_Thoat_Admin = findViewById(R.id.btnThoatAdmin);
        btn_Thoat_Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
    private int checkLevel(String s){
        if(s.equalsIgnoreCase("Dễ")){
            return 1;
        }
        else if(s.equalsIgnoreCase("Trung Bình")){
            return 2;
        }
        else
            return 3;
    }
    private void createTest(TestCreation testCreation) {
        APIService.apiService.createTest("Bearer "+KetQua.token,testCreation).enqueue(new Callback<SignUpStatus>() {
            @Override
            public void onResponse(Call<SignUpStatus> call, Response<SignUpStatus> response) {
                Toast.makeText(AdminActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SignUpStatus> call, Throwable t) {
                Toast.makeText(AdminActivity.this,"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
