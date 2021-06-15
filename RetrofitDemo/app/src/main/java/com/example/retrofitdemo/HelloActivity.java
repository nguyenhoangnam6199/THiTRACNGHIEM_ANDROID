package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdemo.api.APIService;
import com.example.retrofitdemo.model.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelloActivity extends AppCompatActivity {

    public static String token;
    Handler handler;
    Runnable runnable;
    ImageView img;
    TextView txtTenTaiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        img = findViewById(R.id.img);
        img.animate().alpha(4000).setDuration(0);
        txtTenTaiKhoan = findViewById(R.id.txtTenTaiKhoan);
        token=KetQua.token;

        //getInfo();
        KetQua.check=false;
        KetQua.curPos=0;
        txtTenTaiKhoan.setText(KetQua.txt_ten);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent dsp = new Intent(HelloActivity.this,NavigationActivity.class);
                startActivity(dsp);
                finish();
            }
        },4000);
    }

    public void getInfo()  {

        APIService.apiService.getInfo("Bearer: "+KetQua.token).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    try{
                        String ten = response.body().getName().toUpperCase();
                        txtTenTaiKhoan.setText("Xin Chào "+ten+" !");

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(HelloActivity.this,"Không thể kết nối với API",Toast.LENGTH_SHORT).show(); }
        });

    }
}
