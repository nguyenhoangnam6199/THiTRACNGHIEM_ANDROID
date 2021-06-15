package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdemo.api.APIService;
import com.example.retrofitdemo.model.SignUpStatus;
import com.example.retrofitdemo.model.TestCreation;
import com.example.retrofitdemo.model.TestElement;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemBoDeActivity extends AppCompatActivity {
TestCreation testCreationList;
List<TestElement> testElements= new ArrayList<>();
EditText txt_ten_bo_de,txt_thoi_gian;
Button btn_luu_bo_de,btn_thoat_bo_de;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_bo_de);
        testElements.clear();
        testCreationList =new TestCreation();
        //---------------------------------------------------------
        Button btn_loai_1=findViewById(R.id.btn_loai_1);
        btn_loai_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(ThemBoDeActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_loai_de);
                TextView tv_ten_loai_de=dialog.findViewById(R.id.tv_ten_loai);
                tv_ten_loai_de.setText("TOEIC PART 1");

                TextView tv_dd=dialog.findViewById(R.id.txt_dd1d);
                tv_dd.setText("1");
                TextView tv_dtb=dialog.findViewById(R.id.txt_dd1tb);
                tv_dtb.setText("1");
                TextView tv_dk=dialog.findViewById(R.id.txt_dd1k);
                tv_dk.setText("1");
                Button btn_luu = dialog.findViewById(R.id.btn_luu_loai);
                btn_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText sld=dialog.findViewById(R.id.txt_sl1d);
                        EditText sltb=dialog.findViewById(R.id.txt_sl1tb);
                        EditText slk=dialog.findViewById(R.id.txt_sl1k);
                        int q1=Integer.parseInt(sld.getText().toString().trim());
                        int q2=Integer.parseInt(sltb.getText().toString().trim());
                        int q3=Integer.parseInt(slk.getText().toString().trim());
                        if(q1>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==1&&te.getLevel()==1){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(1,1,q1);
                            testElements.add(te);
                        }
                        if(q2>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==1&&te.getLevel()==2){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(1,2,q2);
                            testElements.add(te);
                        }
                        if(q3>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==1&&te.getLevel()==3){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(1,3,q3);
                            testElements.add(te);
                        }
                        dialog.dismiss();
                    }
                });
                Button btn_thoat= dialog.findViewById(R.id.btn_thoat_loai);
                btn_thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        //----------------------------------------------
        Button btn_loai_2=findViewById(R.id.btn_loai_2);
        btn_loai_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(ThemBoDeActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_loai_de);
                TextView tv_ten_loai_de=dialog.findViewById(R.id.tv_ten_loai);
                tv_ten_loai_de.setText("TOEIC PART 2");
                TextView tv_dd=dialog.findViewById(R.id.txt_dd1d);
                tv_dd.setText("2");
                TextView tv_dtb=dialog.findViewById(R.id.txt_dd1tb);
                tv_dtb.setText("2");
                TextView tv_dk=dialog.findViewById(R.id.txt_dd1k);
                tv_dk.setText("2");
                Button btn_luu = dialog.findViewById(R.id.btn_luu_loai);
                btn_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText sld=dialog.findViewById(R.id.txt_sl1d);
                        EditText sltb=dialog.findViewById(R.id.txt_sl1tb);
                        EditText slk=dialog.findViewById(R.id.txt_sl1k);
                        int q1=Integer.parseInt(sld.getText().toString().trim());
                        int q2=Integer.parseInt(sltb.getText().toString().trim());
                        int q3=Integer.parseInt(slk.getText().toString().trim());
                        if(q1>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==2&&te.getLevel()==1){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(2,1,q1);
                            testElements.add(te);
                        }
                        if(q2>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==2&&te.getLevel()==2){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(2,2,q2);
                            testElements.add(te);
                        }
                        if(q3>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==2&&te.getLevel()==3){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(2,3,q3);
                            testElements.add(te);
                        }
                        dialog.dismiss();
                    }
                });
                Button btn_thoat= dialog.findViewById(R.id.btn_thoat_loai);
                btn_thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        //----------------------------------------------
        Button btn_loai_3=findViewById(R.id.btn_loai_3);
        btn_loai_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(ThemBoDeActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_loai_de);
                TextView tv_ten_loai_de=dialog.findViewById(R.id.tv_ten_loai);
                tv_ten_loai_de.setText("TOEIC PART 3");
                TextView tv_dd=dialog.findViewById(R.id.txt_dd1d);
                tv_dd.setText("3");
                TextView tv_dtb=dialog.findViewById(R.id.txt_dd1tb);
                tv_dtb.setText("3");
                TextView tv_dk=dialog.findViewById(R.id.txt_dd1k);
                tv_dk.setText("3");
                Button btn_luu = dialog.findViewById(R.id.btn_luu_loai);
                btn_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText sld=dialog.findViewById(R.id.txt_sl1d);
                        EditText sltb=dialog.findViewById(R.id.txt_sl1tb);
                        EditText slk=dialog.findViewById(R.id.txt_sl1k);
                        int q1=Integer.parseInt(sld.getText().toString().trim());
                        int q2=Integer.parseInt(sltb.getText().toString().trim());
                        int q3=Integer.parseInt(slk.getText().toString().trim());
                        if(q1>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==3&&te.getLevel()==1){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(3,1,q1);
                            testElements.add(te);
                        }
                        if(q2>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==3&&te.getLevel()==2){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(3,2,q2);
                            testElements.add(te);
                        }
                        if(q3>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==3&&te.getLevel()==3){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(3,3,q3);
                            testElements.add(te);
                        }
                        dialog.dismiss();
                    }
                });
                Button btn_thoat= dialog.findViewById(R.id.btn_thoat_loai);
                btn_thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        //----------------------------------------------
        Button btn_loai_4=findViewById(R.id.btn_loai_4);
        btn_loai_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(ThemBoDeActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_loai_de);
                TextView tv_ten_loai_de=dialog.findViewById(R.id.tv_ten_loai);
                tv_ten_loai_de.setText("TOEIC PART 4");
                TextView tv_dd=dialog.findViewById(R.id.txt_dd1d);
                tv_dd.setText("4");
                TextView tv_dtb=dialog.findViewById(R.id.txt_dd1tb);
                tv_dtb.setText("4");
                TextView tv_dk=dialog.findViewById(R.id.txt_dd1k);
                tv_dk.setText("4");
                Button btn_luu = dialog.findViewById(R.id.btn_luu_loai);
                btn_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText sld=dialog.findViewById(R.id.txt_sl1d);
                        EditText sltb=dialog.findViewById(R.id.txt_sl1tb);
                        EditText slk=dialog.findViewById(R.id.txt_sl1k);
                        int q1=Integer.parseInt(sld.getText().toString().trim());
                        int q2=Integer.parseInt(sltb.getText().toString().trim());
                        int q3=Integer.parseInt(slk.getText().toString().trim());
                        if(q1>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==4&&te.getLevel()==1){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(4,1,q1);
                            testElements.add(te);
                        }
                        if(q2>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==4&&te.getLevel()==2){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(4,2,q2);
                            testElements.add(te);
                        }
                        if(q3>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==4&&te.getLevel()==3){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(4,3,q3);
                            testElements.add(te);
                        }
                        dialog.dismiss();
                    }
                });
                Button btn_thoat= dialog.findViewById(R.id.btn_thoat_loai);
                btn_thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        //----------------------------------------------
        Button btn_loai_5=findViewById(R.id.btn_loai_5);
        btn_loai_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(ThemBoDeActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_loai_de);
                TextView tv_ten_loai_de=dialog.findViewById(R.id.tv_ten_loai);
                tv_ten_loai_de.setText("TOEIC PART 5");
                TextView tv_dd=dialog.findViewById(R.id.txt_dd1d);
                tv_dd.setText("5");
                TextView tv_dtb=dialog.findViewById(R.id.txt_dd1tb);
                tv_dtb.setText("5");
                TextView tv_dk=dialog.findViewById(R.id.txt_dd1k);
                tv_dk.setText("5");
                Button btn_luu = dialog.findViewById(R.id.btn_luu_loai);
                btn_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText sld=dialog.findViewById(R.id.txt_sl1d);
                        EditText sltb=dialog.findViewById(R.id.txt_sl1tb);
                        EditText slk=dialog.findViewById(R.id.txt_sl1k);
                        int q1=Integer.parseInt(sld.getText().toString().trim());
                        int q2=Integer.parseInt(sltb.getText().toString().trim());
                        int q3=Integer.parseInt(slk.getText().toString().trim());
                        if(q1>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==5&&te.getLevel()==1){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(5,1,q1);
                            testElements.add(te);
                        }
                        if(q2>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==5&&te.getLevel()==2){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(5,2,q2);
                            testElements.add(te);
                        }
                        if(q3>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==5&&te.getLevel()==3){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(5,3,q3);
                            testElements.add(te);
                        }
                        dialog.dismiss();
                    }
                });
                Button btn_thoat= dialog.findViewById(R.id.btn_thoat_loai);
                btn_thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        //----------------------------------------------
        Button btn_loai_6=findViewById(R.id.btn_loai_6);
        btn_loai_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(ThemBoDeActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_loai_de);
                TextView tv_ten_loai_de=dialog.findViewById(R.id.tv_ten_loai);
                tv_ten_loai_de.setText("TOEIC PART 6");
                TextView tv_dd=dialog.findViewById(R.id.txt_dd1d);
                tv_dd.setText("6");
                TextView tv_dtb=dialog.findViewById(R.id.txt_dd1tb);
                tv_dtb.setText("6");
                TextView tv_dk=dialog.findViewById(R.id.txt_dd1k);
                tv_dk.setText("6");
                Button btn_luu = dialog.findViewById(R.id.btn_luu_loai);
                btn_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText sld=dialog.findViewById(R.id.txt_sl1d);
                        EditText sltb=dialog.findViewById(R.id.txt_sl1tb);
                        EditText slk=dialog.findViewById(R.id.txt_sl1k);
                        int q1=Integer.parseInt(sld.getText().toString().trim());
                        int q2=Integer.parseInt(sltb.getText().toString().trim());
                        int q3=Integer.parseInt(slk.getText().toString().trim());
                        if(q1>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==6&&te.getLevel()==1){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(6,1,q1);
                            testElements.add(te);
                        }
                        if(q2>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==6&&te.getLevel()==2){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(6,2,q2);
                            testElements.add(te);
                        }
                        if(q3>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==6&&te.getLevel()==3){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(6,3,q3);
                            testElements.add(te);
                        }
                        dialog.dismiss();
                    }
                });
                Button btn_thoat= dialog.findViewById(R.id.btn_thoat_loai);
                btn_thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        //----------------------------------------------
        Button btn_loai_7=findViewById(R.id.btn_loai_7);
        btn_loai_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(ThemBoDeActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_loai_de);
                TextView tv_ten_loai_de=dialog.findViewById(R.id.tv_ten_loai);
                tv_ten_loai_de.setText("TOEIC PART 7");
                TextView tv_dd=dialog.findViewById(R.id.txt_dd1d);
                tv_dd.setText("7");
                TextView tv_dtb=dialog.findViewById(R.id.txt_dd1tb);
                tv_dtb.setText("7");
                TextView tv_dk=dialog.findViewById(R.id.txt_dd1k);
                tv_dk.setText("7");
                Button btn_luu = dialog.findViewById(R.id.btn_luu_loai);
                btn_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText sld=dialog.findViewById(R.id.txt_sl1d);
                        EditText sltb=dialog.findViewById(R.id.txt_sl1tb);
                        EditText slk=dialog.findViewById(R.id.txt_sl1k);
                        int q1=Integer.parseInt(sld.getText().toString().trim());
                        int q2=Integer.parseInt(sltb.getText().toString().trim());
                        int q3=Integer.parseInt(slk.getText().toString().trim());
                        if(q1>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==7&&te.getLevel()==1){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(7,1,q1);
                            testElements.add(te);
                        }
                        if(q2>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==7&&te.getLevel()==2){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(7,2,q2);
                            testElements.add(te);
                        }
                        if(q3>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==7&&te.getLevel()==3){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(7,3,q3);
                            testElements.add(te);
                        }
                        dialog.dismiss();
                    }
                });
                Button btn_thoat= dialog.findViewById(R.id.btn_thoat_loai);
                btn_thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        //----------------------------------------------
        Button btn_loai_8=findViewById(R.id.btn_loai_8);
        btn_loai_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(ThemBoDeActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_loai_de);
                TextView tv_ten_loai_de=dialog.findViewById(R.id.tv_ten_loai);
                tv_ten_loai_de.setText("Điền từ");
                TextView tv_dd=dialog.findViewById(R.id.txt_dd1d);
                tv_dd.setText("8");
                TextView tv_dtb=dialog.findViewById(R.id.txt_dd1tb);
                tv_dtb.setText("8");
                TextView tv_dk=dialog.findViewById(R.id.txt_dd1k);
                tv_dk.setText("8");
                Button btn_luu = dialog.findViewById(R.id.btn_luu_loai);
                btn_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText sld=dialog.findViewById(R.id.txt_sl1d);
                        EditText sltb=dialog.findViewById(R.id.txt_sl1tb);
                        EditText slk=dialog.findViewById(R.id.txt_sl1k);
                        int q1=Integer.parseInt(sld.getText().toString().trim());
                        int q2=Integer.parseInt(sltb.getText().toString().trim());
                        int q3=Integer.parseInt(slk.getText().toString().trim());
                        if(q1>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==8&&te.getLevel()==1){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(8,1,q1);
                            testElements.add(te);
                        }
                        if(q2>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==8&&te.getLevel()==2){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(8,2,q2);
                            testElements.add(te);
                        }
                        if(q3>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==8&&te.getLevel()==3){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(8,3,q3);
                            testElements.add(te);
                        }
                        dialog.dismiss();
                    }
                });
                Button btn_thoat= dialog.findViewById(R.id.btn_thoat_loai);
                btn_thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        //----------------------------------------------
        Button btn_loai_9=findViewById(R.id.btn_loai_9);
        btn_loai_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(ThemBoDeActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_loai_de);
                TextView tv_ten_loai_de=dialog.findViewById(R.id.tv_ten_loai);
                tv_ten_loai_de.setText("Sắp thứ tự");
                TextView tv_dd=dialog.findViewById(R.id.txt_dd1d);
                tv_dd.setText("9");
                TextView tv_dtb=dialog.findViewById(R.id.txt_dd1tb);
                tv_dtb.setText("9");
                TextView tv_dk=dialog.findViewById(R.id.txt_dd1k);
                tv_dk.setText("9");
                Button btn_luu = dialog.findViewById(R.id.btn_luu_loai);
                btn_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText sld=dialog.findViewById(R.id.txt_sl1d);
                        EditText sltb=dialog.findViewById(R.id.txt_sl1tb);
                        EditText slk=dialog.findViewById(R.id.txt_sl1k);
                        int q1=Integer.parseInt(sld.getText().toString().trim());
                        int q2=Integer.parseInt(sltb.getText().toString().trim());
                        int q3=Integer.parseInt(slk.getText().toString().trim());
                        if(q1>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==9&&te.getLevel()==1){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(9,1,q1);
                            testElements.add(te);
                        }
                        if(q2>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==9&&te.getLevel()==2){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(9,2,q2);
                            testElements.add(te);
                        }
                        if(q3>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==9&&te.getLevel()==3){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(9,3,q3);
                            testElements.add(te);
                        }
                        dialog.dismiss();
                    }
                });
                Button btn_thoat= dialog.findViewById(R.id.btn_thoat_loai);
                btn_thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        //------------------------------------------------------------
        Button btn_loai_10=findViewById(R.id.btn_loai_10);
        btn_loai_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(ThemBoDeActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_loai_de);
                TextView tv_ten_loai_de=dialog.findViewById(R.id.tv_ten_loai);
                tv_ten_loai_de.setText("Đúng / Sai");
                TextView tv_dd=dialog.findViewById(R.id.txt_dd1d);
                tv_dd.setText("10");
                TextView tv_dtb=dialog.findViewById(R.id.txt_dd1tb);
                tv_dtb.setText("10");
                TextView tv_dk=dialog.findViewById(R.id.txt_dd1k);
                tv_dk.setText("10");
                Button btn_luu = dialog.findViewById(R.id.btn_luu_loai);
                btn_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText sld=dialog.findViewById(R.id.txt_sl1d);
                        EditText sltb=dialog.findViewById(R.id.txt_sl1tb);
                        EditText slk=dialog.findViewById(R.id.txt_sl1k);
                        int q1=Integer.parseInt(sld.getText().toString().trim());
                        int q2=Integer.parseInt(sltb.getText().toString().trim());
                        int q3=Integer.parseInt(slk.getText().toString().trim());
                        if(q1>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==10&&te.getLevel()==1){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(10,1,q1);
                            testElements.add(te);
                        }
                        if(q2>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==10&&te.getLevel()==2){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(10,2,q2);
                            testElements.add(te);
                        }
                        if(q3>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==10&&te.getLevel()==3){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(10,3,q3);
                            testElements.add(te);
                        }
                        dialog.dismiss();
                    }
                });
                Button btn_thoat= dialog.findViewById(R.id.btn_thoat_loai);
                btn_thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        //------------------------------------------------------------
        Button btn_loai_11=findViewById(R.id.btn_loai_11);
        btn_loai_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(ThemBoDeActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_loai_de);
                TextView tv_ten_loai_de=dialog.findViewById(R.id.tv_ten_loai);
                tv_ten_loai_de.setText("Hình ảnh");
                TextView tv_dd=dialog.findViewById(R.id.txt_dd1d);
                tv_dd.setText("11");
                TextView tv_dtb=dialog.findViewById(R.id.txt_dd1tb);
                tv_dtb.setText("11");
                TextView tv_dk=dialog.findViewById(R.id.txt_dd1k);
                tv_dk.setText("11");
                Button btn_luu = dialog.findViewById(R.id.btn_luu_loai);
                btn_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText sld=dialog.findViewById(R.id.txt_sl1d);
                        EditText sltb=dialog.findViewById(R.id.txt_sl1tb);
                        EditText slk=dialog.findViewById(R.id.txt_sl1k);
                        int q1=Integer.parseInt(sld.getText().toString().trim());
                        int q2=Integer.parseInt(sltb.getText().toString().trim());
                        int q3=Integer.parseInt(slk.getText().toString().trim());
                        if(q1>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==11&&te.getLevel()==1){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(11,1,q1);
                            testElements.add(te);
                        }
                        if(q2>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==11&&te.getLevel()==2){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(11,2,q2);
                            testElements.add(te);
                        }
                        if(q3>0){
                            if(!testElements.isEmpty()){
                                for(TestElement te:testElements){
                                    if(te.getTypeid()==11&&te.getLevel()==3){
                                        testElements.remove(te);
                                    }
                                }
                            }
                            TestElement te=new TestElement(11,3,q3);
                            testElements.add(te);
                        }
                        dialog.dismiss();
                    }
                });
                Button btn_thoat= dialog.findViewById(R.id.btn_thoat_loai);
                btn_thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        //------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------------
        txt_ten_bo_de=findViewById(R.id.txt_ten_bo_de);
        txt_thoi_gian=findViewById(R.id.txt_thoi_gian);
        btn_luu_bo_de=findViewById(R.id.btn_luu_bo_de);
        btn_luu_bo_de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean check=true;
                if(testElements.isEmpty()){
                    Toast.makeText(ThemBoDeActivity.this,"Chưa chọn loại đề !",Toast.LENGTH_SHORT).show();
                    check=false;
                }
                if(txt_ten_bo_de.getText().toString().trim().equalsIgnoreCase("")){
                    txt_ten_bo_de.setError("Chưa nhập tên bộ đề !");
                    check=false;
                }
                if(txt_thoi_gian.getText().toString().trim().equalsIgnoreCase("")){
                    txt_thoi_gian.setError("Chưa nhập thời gian thi !");
                    check=false;
                }else
                if(Integer.parseInt(txt_thoi_gian.getText().toString())<=0){
                    txt_thoi_gian.setError("Thời gian thi phải lớn hơn 0 phút");
                    check= false;
                }
                if(check){
                    testCreationList.setTime(Integer.parseInt(txt_thoi_gian.getText().toString()));
                    testCreationList.setName(txt_ten_bo_de.getText().toString().trim());
                    testCreationList.setDetails(testElements);
                    createTest(testCreationList);
                }
            }
        });
        btn_thoat_bo_de=findViewById(R.id.btn_thoat_bo_de);
        btn_thoat_bo_de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testElements.clear();
                testCreationList =new TestCreation();
                Intent i =new Intent(ThemBoDeActivity.this,AdminActivity.class);
                startActivity(i);
            }
        });//---------------------------------------------------------------------------------------------
    }//--------------------------------------------------------------------------------------------------
    private void createTest(TestCreation testCreation) {
        APIService.apiService.createTest("Bearer "+KetQua.token,testCreation).enqueue(new Callback<SignUpStatus>() {
            @Override
            public void onResponse(Call<SignUpStatus> call, Response<SignUpStatus> response) {
                Toast.makeText(ThemBoDeActivity.this, "Tạo bộ đề thành công", Toast.LENGTH_SHORT).show();
                Notification notification = new NotificationCompat.Builder(ThemBoDeActivity.this, com.example.retrofitdemo.model.Notification.CHANNEL_ID)
                        .setContentTitle("Thông báo !")
                        .setContentText("Đã thêm bộ đề "+txt_ten_bo_de.getText().toString().trim())
                        .setSmallIcon(R.drawable.ic_notifications_none_black_24dp)
                // .setLargeIcon(bitmap)
                        .build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if(notificationManager!=null){
                    notificationManager.notify(getID(),notification);
                }
                Intent i =new Intent(ThemBoDeActivity.this,AdminActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<SignUpStatus> call, Throwable t) {
                Toast.makeText(ThemBoDeActivity.this,"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private int getID(){
        Random rand = new Random();
        int time = rand.nextInt(100)+1;
        return time;
    }
}
