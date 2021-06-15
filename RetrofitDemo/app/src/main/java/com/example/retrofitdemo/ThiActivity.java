package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdemo.adapter.BoDe1Adapter;
import com.example.retrofitdemo.adapter.ViewPageAdapter;
import com.example.retrofitdemo.api.APIService;
import com.example.retrofitdemo.model.CauHoi;
import com.example.retrofitdemo.model.EndExam;
import com.example.retrofitdemo.model.KetQuaThi;
import com.example.retrofitdemo.model.ListQuestion;
import com.example.retrofitdemo.model.Question;
import com.example.retrofitdemo.model.SignUpStatus;
import com.example.retrofitdemo.model.StartingTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThiActivity extends AppCompatActivity {

    public static ViewPageAdapter viewPagerAdapter;
    private ViewPager viewPager;
    public ArrayList<CauHoi> listCauHoi;
    Button btnLuu;
    TextView txtPos;
    TextView txtCountDown;
    private CountDownTimer mCountDownTimer;

    public static List<String> dapan=new ArrayList<>();
    public static List<ExamFragment> listFragment=new ArrayList<>();
    public static List<Fragment> listtttt=new ArrayList<>();
    public StartingTest start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thi);
        KetQua.idList=new ArrayList<>();
        KetQua.dynamicSelected=new HashMap<>();
        KetQua.listSelected=new ArrayList<String>();
        KetQua.testList=new ArrayList<>();
        KetQua.selectedIn1Page=new TreeSet<>();
        KetQua.map.put(-5,"0");
        KetQua.map.put(-10,"0");
        viewPager = findViewById(R.id.viewpager);
        btnLuu = findViewById(R.id.btnLuu);
        Bundle bundle1 = getIntent().getExtras();
        StartingTest startingTest=bundle1.getParcelable("TEST");
        //startingTest=HomeActivity.s;
        startingTest= BoDe1Adapter.s;//Sửa homeactivity thành Bode1Adapter
        final StartingTest finalStartingTest = startingTest;
        start=startingTest;
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(KetQua.check){
                    if(listCauHoi.get(KetQua.curPos).loai==10){
                        KetQua.listSelected.set(KetQua.curPos,KetQua.selectedIn1Page.toString());
                    }else{
                        KetQua.listSelected.set(KetQua.curPos, KetQua.rdSelected);}
                    KetQua.check = false;
                }
                Log.d("TEST:"+KetQua.curPos,""+KetQua.listSelected.get(KetQua.curPos));
                //ExamFragment.mediaPlayer.pause();
                for(int in=0;in<KetQua.idList.size();in++){
                    if(KetQua.listSelected.get(in).lastIndexOf("[")>=0){
                        String s=KetQua.listSelected.get(in);
                        s=s.substring(1,s.length()-1);
                        Log.d("---------",""+s);
                        s=s.replaceFirst("AT","T");
                        s=s.replaceFirst("BT","T");
                        s=s.replaceFirst("CT","T");
                        s=s.replaceFirst("DT","T");
                        s=s.replaceFirst("AF","F");
                        s=s.replaceFirst("BF","F");
                        s=s.replaceFirst("CF","F");
                        s=s.replaceFirst("DF","F");
                        s=s.replaceAll(", ","");
                        s=s.trim();
                        Log.d("---------",""+s);
                        KetQua.listSelected.set(in,s);
                    }

                }
                EndExam endExam=new EndExam();
                endExam.setId(finalStartingTest.id);
                List<ListQuestion> listQuestions=new ArrayList<>();
                for(int in=0;in<KetQua.idList.size();in++){
                    ListQuestion listQuestion= new ListQuestion();
                    long loai=finalStartingTest.listQuestion.get(in).type.id;
                    if(loai==3||loai==4||loai==6||loai==7||loai==10||loai==11){
                    for(int b=0;b<finalStartingTest.listQuestion.get(in).detail.size();b++){
                        listQuestion.setIdQuestion(finalStartingTest.listQuestion.get(in).id);
                        listQuestion.setIdQuestionDetail(finalStartingTest.listQuestion.get(in).detail.get(b).id);
                        listQuestion.setAnswer(KetQua.listSelected.get(in));
                        //ANSWER
                        }
                    }
                    else if(loai==5||loai==9){
                        for(int b=0;b<finalStartingTest.listQuestion.get(in).detail.size();b++){
                            listQuestion.setIdQuestion(finalStartingTest.listQuestion.get(in).id);
                            listQuestion.setIdQuestionDetail(finalStartingTest.listQuestion.get(in).detail.get(b).id);
                            listQuestion.setAnswer(KetQua.listSelected.get(in));
                            //ANSWER
                        }
                    }
                    else{

                        listQuestion.setIdQuestion(finalStartingTest.listQuestion.get(in).id);
                        listQuestion.setAnswer(KetQua.listSelected.get(in));
                    }
                    listQuestions.add(listQuestion);
                }
                endExam.setListQuestion(listQuestions);
                //Sửa homeactivity thành BoDe1Adapter
                APIService.apiService.endExam("Bearer "+KetQua.token,BoDe1Adapter.idChosen,endExam).enqueue(new Callback<SignUpStatus>() {
                    @Override
                    public void onResponse(Call<SignUpStatus> call, Response<SignUpStatus> response) {
                        SignUpStatus ketqua=response.body();
                        String kq="";
                        if(ketqua==null){
                            kq="Lỗi khi nộp đáp án";
                        }else {
                            kq = ketqua.message;
                        }
                        Intent i=new Intent(ThiActivity.this,KetQuaActivity.class);
                        i.putExtra("ketqua",kq);
                        mCountDownTimer.cancel();
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<SignUpStatus> call, Throwable t) {

                    }
                });


            }
        });

        viewPagerAdapter = new ViewPageAdapter(getSupportFragmentManager());
        listCauHoi=new ArrayList<>();
        if(startingTest!=null){
            Log.d("TEST: id:",""+startingTest.id);
            Log.d("TEST: time:",""+startingTest.creationDateTime);
            Log.d("TEST: size:",""+startingTest.getListQuestion().size());
        }
        int id=0;
        for(Question q:startingTest.listQuestion){
            if(q.description!=null)
            q.description=q.description.replaceAll("/n","\n");
            listCauHoi.add(new CauHoi(id,(int)q.id,(int)q.type.id,q.content,q.description));
            id++;
            KetQua.idList.add((int)q.id);
            Log.d("TEST: Cau"+q.id,""+q.level);
            Log.d("TEST: Loai",""+q.type.id);
            Log.d("TEST: Content:",""+q.content);
        }
        txtPos=findViewById(R.id.txtPos);
        for(int i=0; i<listCauHoi.size(); i++){
            TreeSet<String> ts=new TreeSet<>();
            ts.clear();
            KetQua.dynamicSelected.put(listCauHoi.get(i).id,ts);
            KetQua.listSelected.add("");
            KetQua.testList.add(ts);
            dapan.add("");
            Bundle bundle = new Bundle();
            bundle.putInt("id",listCauHoi.get(i).id);
            bundle.putInt("loai",listCauHoi.get(i).loai);
            bundle.putString("content",listCauHoi.get(i).content);
            bundle.putString("description",listCauHoi.get(i).description);
            if(startingTest.listQuestion.get(i).photo!=null||startingTest.listQuestion.get(i).photo!=""){
                bundle.putString("photo",startingTest.listQuestion.get(i).photo);
            }
            long loai=listCauHoi.get(i).loai;
            if(loai==3||loai==4||loai==5||loai==6||loai==7||loai==9||loai==10||loai==11){
                bundle.putString("description",startingTest.listQuestion.get(i).detail.get(0).description);
                if(startingTest.listQuestion.get(i).detail.get(0).content!=null||startingTest.listQuestion.get(i).detail.get(0).content!="")
                bundle.putString("detailcontent",startingTest.listQuestion.get(i).detail.get(0).content);
                bundle.putString("a",startingTest.listQuestion.get(i).detail.get(0).a);
                bundle.putString("b",startingTest.listQuestion.get(i).detail.get(0).b);
                bundle.putString("c",startingTest.listQuestion.get(i).detail.get(0).c);
                bundle.putString("d",startingTest.listQuestion.get(i).detail.get(0).d);
            }
            ExamFragment ExamFragment = new ExamFragment();
            ExamFragment.setArguments(bundle);
            onAttachFragment(ExamFragment);
            viewPagerAdapter.add(ExamFragment,""+listCauHoi.get(i).id);
        }
        viewPager.setAdapter(viewPagerAdapter);
        txtCountDown = findViewById(R.id.txt_timer);

        //Ham chay thoi gian
        StartTime();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }


            //@RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onPageSelected(int position) {
                UpdatePage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void UpdatePage(int position){
        //--------------------------------------------------------------------------------------------------
        Boolean direction=false;//vuot sang cau tiep theo

        if(KetQua.map.get(-10)!=null){
            KetQua.dynamicSelected.put(KetQua.idList.get(Integer.parseInt(KetQua.map.get(-10))),KetQua.selectedIn1Page);
            if(position<Integer.parseInt(KetQua.map.get(-10))){
                direction=true;
            }
        }
        else{
            KetQua.dynamicSelected.put(KetQua.idList.get(0),KetQua.selectedIn1Page);
        }
        if(!direction){
            //Toast.makeText(getApplicationContext(),"Huong sang phai",Toast.LENGTH_SHORT).show();
        }
        else{
            //Toast.makeText(getApplicationContext(),"Huong sang trai",Toast.LENGTH_SHORT).show();
        }
        //--------------------------------------------------------------------------------------------------
        //2 dòng
        dapan.set(KetQua.curPos,KetQua.listSelected.get(KetQua.curPos));
        txtPos.setText(String.valueOf(position));

//-----------------------------------------------------------------------------------------------------
        KetQua.map.put(-10,String.valueOf(position));//Lay pos hien tai
        if(position>Integer.parseInt(KetQua.map.get(-5))) {
            KetQua.map.put(-5, String.valueOf(position));//Lay pos lon nhat
        }
        //--------------------------------------------------------------------------------------------
        String kq;
        kq=KetQua.rdSelected.toString();
        if(KetQua.check){
            if(listCauHoi.get(KetQua.curPos).loai==10){
                KetQua.listSelected.set(KetQua.curPos,KetQua.selectedIn1Page.toString());
            }else {
                KetQua.listSelected.set(KetQua.curPos, kq);
            }
            KetQua.check = false;
        }
        Log.d("TEST:"+KetQua.curPos,""+KetQua.listSelected.get(KetQua.curPos));
        Toast.makeText(getApplicationContext(),"Câu "+KetQua.curPos+": "+KetQua.listSelected.get(KetQua.curPos),Toast.LENGTH_SHORT).show();
        //ExamFragment.mediaPlayer.pause();
        KetQua.curPos=position;
        KetQua.rdSelected="";
        KetQua.selectedIn1Page.clear();
    }
    public void StartTime(){// sửa homeactivity thành BoDe1Adapter
        long time=BoDe1Adapter.time;
        mCountDownTimer = new CountDownTimer(BoDe1Adapter.time,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                BoDe1Adapter.time=millisUntilFinished;
                int phut = (int)(BoDe1Adapter.time/1000)/60;
                int giay = (int)(BoDe1Adapter.time/1000)%60;
                String s = String.format(Locale.getDefault(),"%02d:%02d",phut,giay);
                txtCountDown.setText(s);
            }

            @Override
            public void onFinish() {
                //Code sau khi chay du thoi gian
                Toast.makeText(getApplicationContext(),"Het Gio",Toast.LENGTH_SHORT).show();
                if(KetQua.check){
                    if(listCauHoi.get(KetQua.curPos).loai==10){
                        KetQua.listSelected.set(KetQua.curPos,KetQua.selectedIn1Page.toString());
                    }else{
                        KetQua.listSelected.set(KetQua.curPos, KetQua.rdSelected);}
                    KetQua.check = false;
                }
                Log.d("TEST:"+KetQua.curPos,""+KetQua.listSelected.get(KetQua.curPos));
                //ExamFragment.mediaPlayer.pause();
                for(int in=0;in<KetQua.idList.size();in++){
                    if(KetQua.listSelected.get(in).lastIndexOf("[")>=0){
                        String s=KetQua.listSelected.get(in);
                        s=s.substring(1,s.length()-1);
                        Log.d("---------",""+s);
                        s=s.replaceFirst("AT","T");
                        s=s.replaceFirst("BT","T");
                        s=s.replaceFirst("CT","T");
                        s=s.replaceFirst("DT","T");
                        s=s.replaceFirst("AF","F");
                        s=s.replaceFirst("BF","F");
                        s=s.replaceFirst("CF","F");
                        s=s.replaceFirst("DF","F");
                        s=s.replaceAll(", ","");
                        s=s.trim();
                        Log.d("---------",""+s);
                        KetQua.listSelected.set(in,s);
                    }

                }
                EndExam endExam=new EndExam();
                endExam.setId(start.id);
                List<ListQuestion> listQuestions=new ArrayList<>();
                for(int in=0;in<KetQua.idList.size();in++){
                    ListQuestion listQuestion= new ListQuestion();
                    long loai=start.listQuestion.get(in).type.id;
                    if(loai==10){
                        for(int b=0;b<start.listQuestion.get(in).detail.size();b++){
                            listQuestion.setIdQuestion(start.listQuestion.get(in).id);
                            listQuestion.setIdQuestionDetail(start.listQuestion.get(in).detail.get(b).id);
                            listQuestion.setAnswer(KetQua.listSelected.get(in));
                            //ANSWER
                        }
                    }
                    else if(loai==3||loai==4||loai==5||loai==6||loai==7||loai==9||loai==11){
                        for(int b=0;b<start.listQuestion.get(in).detail.size();b++){
                            listQuestion.setIdQuestion(start.listQuestion.get(in).id);
                            listQuestion.setIdQuestionDetail(start.listQuestion.get(in).detail.get(b).id);
                            listQuestion.setAnswer(KetQua.listSelected.get(in));
                            //ANSWER
                        }
                    }
                    else{

                        listQuestion.setIdQuestion(start.listQuestion.get(in).id);
                        listQuestion.setAnswer(KetQua.listSelected.get(in));
                    }
                    listQuestions.add(listQuestion);
                }
                endExam.setListQuestion(listQuestions);
                //Sửa homeactivity thành BoDe1Adapter
                APIService.apiService.endExam("Bearer "+KetQua.token,BoDe1Adapter.idChosen,endExam).enqueue(new Callback<SignUpStatus>() {
                    @Override
                    public void onResponse(Call<SignUpStatus> call, Response<SignUpStatus> response) {
                        SignUpStatus ketqua=response.body();
                        String kq="";
                        if(ketqua==null){
                            kq="Lỗi khi nộp đáp án";
                        }else {
                            kq = ketqua.message;
                        }
                        Intent i=new Intent(ThiActivity.this,KetQuaActivity.class);
                        i.putExtra("ketqua",kq);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<SignUpStatus> call, Throwable t) {

                    }
                });

            }
        }.start();
    }
}
