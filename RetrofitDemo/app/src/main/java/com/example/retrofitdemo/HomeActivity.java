package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdemo.adapter.BoDeAdapter;
import com.example.retrofitdemo.api.APIService;
import com.example.retrofitdemo.model.Account;
import com.example.retrofitdemo.model.BoDe;
import com.example.retrofitdemo.model.Question;
import com.example.retrofitdemo.model.StartingTest;
import com.example.retrofitdemo.model.StartingTest2;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
ListView lv_dsde;
public static ArrayList<BoDe> arrayList;
  public ArrayList<BoDe> arrayList1=new ArrayList<>();
TextView txt_user;
BoDeAdapter adapter;
public static String token;
public static long idChosen;
public static long time;
public static StartingTest2 s;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        lv_dsde=findViewById(R.id.lv_dsde);
        Intent intent=getIntent();
        token=KetQua.token;
        arrayList=new ArrayList<>();
        arrayList= (ArrayList<BoDe>) KetQua.dsbode;
        getInfo();
        adapter = new BoDeAdapter(this, R.layout.item_bode, arrayList);

        lv_dsde.setAdapter(adapter);
        lv_dsde.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                idChosen=arrayList.get(i).getId();
                time=arrayList.get(i).getTime()*1000*60;
                //APIService.apiService.startExam("Bearer "+token,idChosen).enqueue(new Callback<StartingTest>() {
                     APIService.apiService.startExam2("Bearer "+token,idChosen).enqueue(new Callback<StartingTest2>() {
                    @Override
                    public void onResponse(Call<StartingTest2> call, Response<StartingTest2> response) {
                        //Intent intent1=new Intent(HomeActivity.this,ThiActivity.class);
                        Intent intent1=new Intent(HomeActivity.this,ThiNewActivity.class);
                        StartingTest2 startingTest=response.body();
                        s=response.body();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("TEST",  startingTest);

                        Instant tgketthuc=Instant.parse(startingTest.expirationDateTime.toString());

                        Instant tghientai=Instant.now();
                        time = tghientai.until(tgketthuc, ChronoUnit.MINUTES)*1000*60;
                        intent1.putExtras(bundle);
                        startActivity(intent1);
                    }

                    @Override
                    public void onFailure(Call<StartingTest2> call, Throwable t) {
                        Toast.makeText(HomeActivity.this,"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
    public void getInfo()  {

        APIService.apiService.getInfo("Bearer "+token).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                txt_user=findViewById(R.id.txt_user);
                if(response.isSuccessful()){
                    try{
                        txt_user.setText("Xin Chào: "+response.body().getName().toString());

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(HomeActivity.this,"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
            }
        });

    }
    /*public void getBoDe(){
        APIService.apiService.getBoDe().enqueue(new Callback<ArrayList<BoDe>>() {
            @Override
            public void onResponse(Call<ArrayList<BoDe>> call, Response<ArrayList<BoDe>> response) {

            }

            @Override
            public void onFailure(Call<ArrayList<BoDe>> call, Throwable t) {
                Toast.makeText(HomeActivity.this,"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
