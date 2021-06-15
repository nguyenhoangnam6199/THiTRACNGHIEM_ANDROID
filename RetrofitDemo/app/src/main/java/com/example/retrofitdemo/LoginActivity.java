package com.example.retrofitdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdemo.api.APIService;
import com.example.retrofitdemo.model.Account;
import com.example.retrofitdemo.model.AccountLogIn;
import com.example.retrofitdemo.model.AccountSignUp;
import com.example.retrofitdemo.model.BoDe;
import com.example.retrofitdemo.model.FavoriteQuestion;
import com.example.retrofitdemo.model.HistoryTest;
import com.example.retrofitdemo.model.SignUpStatus;
import com.example.retrofitdemo.model.Token;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnDangKy;
    EditText edtTK, edtMK;
    Button btnDn;
    String tk,mk;
    TextView fp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtTK = findViewById(R.id.edtTK);
        edtMK = findViewById(R.id.edtMK);
        fp=findViewById(R.id.tv_fp);
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(LoginActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_forget_password);
                final EditText edtemail=dialog.findViewById(R.id.etEmail);
                edtemail.setHint("Nhập email");
                Button btnSave=dialog.findViewById(R.id.btnGui);
                Button btnExit=dialog.findViewById(R.id.btnHuy);
                btnExit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Boolean check=true;

                        if(edtemail.getText().toString().trim().equalsIgnoreCase("")){
                            edtemail.setError("Vui lòng không bỏ trống");
                            check=false;}

                        if(edtemail.getText().toString().trim().lastIndexOf("@")<0){
                            edtemail.setError("Vui lòng nhập đúng định dạng email");
                            check=false;}
                        //
                        if(check==true){
                            resetPassword(edtemail.getText().toString().trim());
                            dialog.dismiss();}
                        //
                    }
                });
                dialog.show();
            }
        });
        KetQua.token="";
        btnDn = findViewById(R.id.btnDN);
        btnDn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tk=edtTK.getText().toString().trim();
                mk=edtMK.getText().toString().trim();
                signIn();
            }
        });
        btnDangKy = findViewById(R.id.btnDangKy);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(LoginActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_dang_ky_2);
                final EditText edtname=dialog.findViewById(R.id.etName);
                final EditText edtusername=dialog.findViewById(R.id.etID);
                final EditText edtemail=dialog.findViewById(R.id.etEmail);
                final EditText edtpassword=dialog.findViewById(R.id.etpass);
                edtname.setHint("Nhập tên");
                edtusername.setHint("Nhập tài khoản");
                edtpassword.setHint("Nhập mật khẩu");
                edtemail.setHint("Nhập email");
                Button btnSave=dialog.findViewById(R.id.btnLuu);
                Button btnExit=dialog.findViewById(R.id.btnHuy);
                btnExit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Boolean check=true;
                        if(edtname.getText().toString().trim().equalsIgnoreCase("")){
                            edtname.setError("Vui lòng không bỏ trống");
                            check=false;}
                        if(edtusername.getText().toString().trim().equalsIgnoreCase("")){
                            edtusername.setError("Vui lòng không bỏ trống");
                            check=false;}
                        if(edtpassword.getText().toString().trim().equalsIgnoreCase("")){
                            edtpassword.setError("Vui lòng không bỏ trống");
                            check=false;}
                        if(edtemail.getText().toString().trim().equalsIgnoreCase("")){
                            edtemail.setError("Vui lòng không bỏ trống");
                            check=false;}
                        if(edtpassword.getText().toString().trim().length()<6){
                            edtpassword.setError("Mật khẩu ít nhất phải có 6 kí tự");
                            check=false;}
                        if(edtusername.getText().toString().trim().length()<3){
                            edtusername.setError("Tài khoản ít nhất phải có 3 kí tự");
                            check=false;}
                        if(edtname.getText().toString().trim().length()<4){
                            edtname.setError("Tên ít nhất phải có 4 kí tự");
                            check=false;}
                        if(edtemail.getText().toString().trim().lastIndexOf("@")<0){
                            edtemail.setError("Vui lòng nhập đúng định dạng email");
                            check=false;}
                        //
                        if(check==true){
                            signUp(edtname.getText().toString().trim()
                                    ,edtusername.getText().toString().trim()
                                    ,edtemail.getText().toString().trim()
                                    ,edtpassword.getText().toString().trim() );
                            dialog.dismiss();}
                        //
                    }
                });
                dialog.show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_info, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.infor:
                Intent i = new Intent(LoginActivity.this, ThongTinActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void signUp(String name,String username,String email,String password) {
        AccountSignUp accountSignUp= new AccountSignUp(name,username,email,password);
        APIService.apiService.signUp(accountSignUp).enqueue(new Callback<SignUpStatus>() {
            @Override
            public void onResponse(Call<SignUpStatus> call, Response<SignUpStatus> response) {
                Toast.makeText(LoginActivity.this, "Đăng ký thành cộng", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SignUpStatus> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signIn(){
        AccountLogIn accountLogIn= new AccountLogIn(tk,mk);
        APIService.apiService.signIn(accountLogIn).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                try {
                    Toast.makeText(LoginActivity.this, response.body().getAccessToken().toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, response.body().getTokenType().toString(), Toast.LENGTH_SHORT).show();
                    final String token=response.body().getAccessToken().toString();
                    APIService.apiService.getInfo("Bearer "+response.body().getAccessToken().toString()).enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(Call<Account> call, Response<Account> response) {
                            String ten = response.body().getName();
                            KetQua.txt_ten="Xin Chào "+ten+" !";
                            if(response.body().getAdmin()){
                                Intent intent=new Intent(LoginActivity.this,AdminActivity.class);
                                KetQua.token=token;
                                intent.putExtra("token",token);
                                startActivity(intent);
                            }
                            else{
                                APIService.apiService.getTest().enqueue(new Callback<ArrayList<BoDe>>() {
                                    @Override
                                    public void onResponse(Call<ArrayList<BoDe>> call, Response<ArrayList<BoDe>> response) {
                                        ArrayList<BoDe>list=new ArrayList<>();
                                        for (BoDe b:response.body()) {
                                            list.add(b);
                                        }
                                        KetQua.token=token;
                                        //Log.d("bundle",""+bundle);
                                        KetQua.dsbode=list;

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
                                                        Intent intent = new Intent(LoginActivity.this,HelloActivity.class);
                                                        startActivity(intent);
                                                    }

                                                    @Override
                                                    public void onFailure(Call<ArrayList<HistoryTest>> call, Throwable t) {
                                                        Toast.makeText(LoginActivity.this,"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onFailure(Call<ArrayList<FavoriteQuestion>> call, Throwable t) {
                                                Toast.makeText(LoginActivity.this,"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                    @Override
                                    public void onFailure(Call<ArrayList<BoDe>> call, Throwable t) {
                                        Toast.makeText(LoginActivity.this,"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }

                        @Override
                        public void onFailure(Call<Account> call, Throwable t) {
                            Toast.makeText(LoginActivity.this,"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
                        }
                    });
                }catch(Exception e){
                    Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void resetPassword (String email){
        APIService.apiService.resetPassword(email).enqueue(new Callback<SignUpStatus>() {
            @Override
            public void onResponse(Call<SignUpStatus> call, Response<SignUpStatus> response) {
                Toast.makeText(LoginActivity.this,"Vui lòng check mail",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SignUpStatus> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
