package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdemo.model.FavoriteQuestion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavoriteQuestionActivity extends AppCompatActivity {
    private TextView textCurrentTime, textTotalDuration;
    private SeekBar playerSeekBar;
    public  MediaPlayer mediaPlayer;
    private ImageView anhDe;
    private Handler handler = new Handler();
    private String content;//Link
    private String photo;//Link
    private String description;
    private String a,b,c,d,as,ct,dc,answer;
    private FavoriteQuestion fq=KetQua.favoriteQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_question);
        //Ánh Xạ
        TextView txtContent = findViewById(R.id.txtContent);
        RadioButton rd1 = findViewById(R.id.rd1);
        RadioButton rd2 = findViewById(R.id.rd2);
        RadioButton rd3 = findViewById(R.id.rd3);
        RadioButton rd4 = findViewById(R.id.rd4);
        RadioButton rdt1 = findViewById(R.id.rdt1);
        RadioButton rdt2 = findViewById(R.id.rdt2);
        RadioButton rdt3 = findViewById(R.id.rdt3);
        RadioButton rdt4 = findViewById(R.id.rdt4);
        RadioButton rdt5 = findViewById(R.id.rdt5);
        RadioButton rdt6 = findViewById(R.id.rdt6);
        RadioButton rdt7 = findViewById(R.id.rdt7);
        RadioButton rdt8 = findViewById(R.id.rdt8);
        TextView tv_tf = findViewById(R.id.tv_tf);
        TextView tv_a = findViewById(R.id.tv_a);
        TextView tv_b = findViewById(R.id.tv_b);
        TextView tv_c = findViewById(R.id.tv_c);
        TextView tv_d = findViewById(R.id.tv_d);
        TextView subContent=findViewById(R.id.txtSubContent);
        final TextView tv_des = findViewById(R.id.txt_desciption);
        Button btn_des = findViewById(R.id.btn_desciption);
        EditText edt1 = findViewById(R.id.edt1);
        final ImageView imagePlayPause= findViewById(R.id.imagePlayPause);
        textCurrentTime = findViewById(R.id.textCurrentTime);
        textTotalDuration = findViewById(R.id.textTotalDuration);
        playerSeekBar = findViewById(R.id.playSeekBar);
        anhDe = findViewById(R.id.imgAnhDe);
        mediaPlayer = new MediaPlayer();

        playerSeekBar.setMax(100);
        long loai=fq.getQuestion().getType().getId();
        content=fq.getQuestion().getContent();
        photo=fq.getQuestion().getPhoto();
        description=fq.getQuestion().getDescription();
        if(!fq.getQuestion().getDetails().isEmpty()){
        a=fq.getQuestion().getDetails().get(0).getA();
        b=fq.getQuestion().getDetails().get(0).getB();
        c=fq.getQuestion().getDetails().get(0).getC();
        d=fq.getQuestion().getDetails().get(0).getD();
        as=fq.getQuestion().getDetails().get(0).getAnswer();
        ct=fq.getQuestion().getDetails().get(0).getContent();
        dc=fq.getQuestion().getDetails().get(0).getDescription();}
        answer=fq.getQuestion().getAnswer();
        //Giải thích đáp án
        if(loai==5||loai==9){
            tv_des.setText(dc);
        }else
        tv_des.setText(description);
        tv_des.setVisibility(View.GONE);
        btn_des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_des.getVisibility()==View.GONE){
                    tv_des.setVisibility(View.VISIBLE);
                }
                else tv_des.setVisibility(View.GONE);
            }
        });
        //
        //Audio
        if (loai == 1 || loai == 2 ||loai ==3||loai==4) {
            imagePlayPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mediaPlayer.isPlaying()) {
                        handler.removeCallbacks(updater);
                        mediaPlayer.pause();
                        imagePlayPause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
                    } else {
                        mediaPlayer.start();
                        imagePlayPause.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
                        updateSeekBar();
                    }
                }
            });

            prepareMediaPlayer();

            playerSeekBar.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    SeekBar seekBar = (SeekBar) v;
                    int position = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
                    mediaPlayer.seekTo(position);
                    textCurrentTime.setText(milliSecondsToTimer(mediaPlayer.getCurrentPosition()));
                    return false;
                }
            });

            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    playerSeekBar.setSecondaryProgress(percent);
                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    playerSeekBar.setProgress(0);
                    imagePlayPause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
                    textCurrentTime.setText("0:00");
                    mediaPlayer.reset();
                    prepareMediaPlayer();
                }
            });
        }
        //Image
        if(loai==1||loai==7||loai==11){

            String photo=fq.getQuestion().getPhoto();
            Picasso.with(FavoriteQuestionActivity.this)
                    .load(photo)
                    .into(anhDe);
            if(loai==11||loai==7){
                Picasso.with(FavoriteQuestionActivity.this)
                        .load(content)
                        .into(anhDe);
            }}
        txtContent.setText(content);
        //---------------New-----------------
        if(loai==1){
            anhDe.setVisibility(View.VISIBLE);
            rd1.setVisibility(View.VISIBLE);
            rd2.setVisibility(View.VISIBLE);
            rd3.setVisibility(View.VISIBLE);
            rd4.setVisibility(View.VISIBLE);
            rdt1.setVisibility(View.GONE);
            rdt1.setVisibility(View.GONE);
            rdt2.setVisibility(View.GONE);
            rdt3.setVisibility(View.GONE);
            rdt4.setVisibility(View.GONE);
            rdt5.setVisibility(View.GONE);
            rdt6.setVisibility(View.GONE);
            rdt7.setVisibility(View.GONE);
            rdt8.setVisibility(View.GONE);
            edt1.setVisibility(View.GONE);
            tv_tf.setVisibility(View.GONE);
            tv_a.setVisibility(View.GONE);
            tv_b.setVisibility(View.GONE);
            tv_c.setVisibility(View.GONE);
            tv_d.setVisibility(View.GONE);
            rd1.setText("A: ");
            rd2.setText("B: ");
            rd3.setText("C: ");
            rd4.setText("D: ");
            rd1.setEnabled(false);
            rd2.setEnabled(false);
            rd3.setEnabled(false);
            rd4.setEnabled(false);
            if(answer!=null){
                if(answer.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                }
                if(answer.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                }
                if(answer.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                }
                if(answer.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                }}
        }
        if(loai==2){
            anhDe.setVisibility(View.GONE);


            rd1.setVisibility(View.VISIBLE);
            rd2.setVisibility(View.VISIBLE);
            rd3.setVisibility(View.VISIBLE);
            rd4.setVisibility(View.VISIBLE);
            rdt1.setVisibility(View.GONE);
            rdt1.setVisibility(View.GONE);
            rdt2.setVisibility(View.GONE);
            rdt3.setVisibility(View.GONE);
            rdt4.setVisibility(View.GONE);
            rdt5.setVisibility(View.GONE);
            rdt6.setVisibility(View.GONE);
            rdt7.setVisibility(View.GONE);
            rdt8.setVisibility(View.GONE);
            edt1.setVisibility(View.GONE);
            tv_tf.setVisibility(View.GONE);
            tv_a.setVisibility(View.GONE);
            tv_b.setVisibility(View.GONE);
            tv_c.setVisibility(View.GONE);
            tv_d.setVisibility(View.GONE);
            rd1.setText(a);
            rd2.setText(b);
            rd3.setText(c);
            rd4.setText(d);
            rd1.setEnabled(false);
            rd2.setEnabled(false);
            rd3.setEnabled(false);
            rd4.setEnabled(false);
            if(answer!=null){
                if(answer.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                }
                if(answer.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                }
                if(answer.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                }
                if(answer.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                }}
        }
        if(loai==3||loai==4){
            anhDe.setVisibility(View.GONE);
            txtContent.setVisibility(View.GONE);
            imagePlayPause.setVisibility(View.VISIBLE);
            textCurrentTime.setVisibility(View.VISIBLE);
            textTotalDuration.setVisibility(View.VISIBLE);
            playerSeekBar.setVisibility(View.VISIBLE);
            subContent.setVisibility(View.VISIBLE);
            rd1.setVisibility(View.VISIBLE);
            rd2.setVisibility(View.VISIBLE);
            rd3.setVisibility(View.VISIBLE);
            rd4.setVisibility(View.VISIBLE);
            rdt1.setVisibility(View.GONE);
            rdt1.setVisibility(View.GONE);
            rdt2.setVisibility(View.GONE);
            rdt3.setVisibility(View.GONE);
            rdt4.setVisibility(View.GONE);
            rdt5.setVisibility(View.GONE);
            rdt6.setVisibility(View.GONE);
            rdt7.setVisibility(View.GONE);
            rdt8.setVisibility(View.GONE);
            edt1.setVisibility(View.GONE);
            tv_tf.setVisibility(View.GONE);
            tv_a.setVisibility(View.GONE);
            tv_b.setVisibility(View.GONE);
            tv_c.setVisibility(View.GONE);
            tv_d.setVisibility(View.GONE);
            rd1.setText(a);
            rd2.setText(b);
            rd3.setText(c);
            rd4.setText(d);
            subContent.setText(ct);
            rd1.setEnabled(false);
            rd2.setEnabled(false);
            rd3.setEnabled(false);
            rd4.setEnabled(false);
            if(answer!=null){
                if(answer.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                }
                if(answer.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                }
                if(answer.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                }
                if(answer.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                }}
            if(as!=null){
                if(as.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                }
                if(as.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                }
                if(as.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                }
                if(as.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                }
            }
        }
        if(loai==5){
            anhDe.setVisibility(View.GONE);
            txtContent.setVisibility(View.GONE);
            imagePlayPause.setVisibility(View.GONE);
            textCurrentTime.setVisibility(View.GONE);
            textTotalDuration.setVisibility(View.GONE);
            playerSeekBar.setVisibility(View.GONE);
            subContent.setVisibility(View.VISIBLE);
            rd1.setVisibility(View.VISIBLE);
            rd2.setVisibility(View.VISIBLE);
            rd3.setVisibility(View.VISIBLE);
            rd4.setVisibility(View.VISIBLE);
            rdt1.setVisibility(View.GONE);
            rdt1.setVisibility(View.GONE);
            rdt2.setVisibility(View.GONE);
            rdt3.setVisibility(View.GONE);
            rdt4.setVisibility(View.GONE);
            rdt5.setVisibility(View.GONE);
            rdt6.setVisibility(View.GONE);
            rdt7.setVisibility(View.GONE);
            rdt8.setVisibility(View.GONE);
            edt1.setVisibility(View.GONE);
            tv_tf.setVisibility(View.GONE);
            tv_a.setVisibility(View.GONE);
            tv_b.setVisibility(View.GONE);
            tv_c.setVisibility(View.GONE);
            tv_d.setVisibility(View.GONE);
            rd1.setText(a);
            rd2.setText(b);
            rd3.setText(c);
            rd4.setText(d);
            subContent.setText(ct);
            rd1.setEnabled(false);
            rd2.setEnabled(false);
            rd3.setEnabled(false);
            rd4.setEnabled(false);
            if(answer!=null){
                if(answer.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                }
                if(answer.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                }
                if(answer.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                }
                if(answer.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                }}
            if(as!=null){
                if(as.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                }
                if(as.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                }
                if(as.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                }
                if(as.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                }
            }
        }
        if(loai==6){//không có subContent
            anhDe.setVisibility(View.GONE);
            txtContent.setVisibility(View.VISIBLE);
            imagePlayPause.setVisibility(View.GONE);
            textCurrentTime.setVisibility(View.GONE);
            textTotalDuration.setVisibility(View.GONE);
            playerSeekBar.setVisibility(View.GONE);
            subContent.setVisibility(View.VISIBLE);
            rd1.setVisibility(View.VISIBLE);
            rd2.setVisibility(View.VISIBLE);
            rd3.setVisibility(View.VISIBLE);
            rd4.setVisibility(View.VISIBLE);
            rdt1.setVisibility(View.GONE);
            rdt1.setVisibility(View.GONE);
            rdt2.setVisibility(View.GONE);
            rdt3.setVisibility(View.GONE);
            rdt4.setVisibility(View.GONE);
            rdt5.setVisibility(View.GONE);
            rdt6.setVisibility(View.GONE);
            rdt7.setVisibility(View.GONE);
            rdt8.setVisibility(View.GONE);
            edt1.setVisibility(View.GONE);
            tv_tf.setVisibility(View.GONE);
            tv_a.setVisibility(View.GONE);
            tv_b.setVisibility(View.GONE);
            tv_c.setVisibility(View.GONE);
            tv_d.setVisibility(View.GONE);
            rd1.setText(a);
            rd2.setText(b);
            rd3.setText(c);
            rd4.setText(d);
            subContent.setText(ct);
            rd1.setEnabled(false);
            rd2.setEnabled(false);
            rd3.setEnabled(false);
            rd4.setEnabled(false);
            if(answer!=null){
                if(answer.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                }
                if(answer.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                }
                if(answer.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                }
                if(answer.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                }}
            if(as!=null){
                if(as.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                }
                if(as.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                }
                if(as.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                }
                if(as.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                }
            }
        }
        if(loai==7){
            anhDe.setVisibility(View.VISIBLE);
            txtContent.setVisibility(View.GONE);
            imagePlayPause.setVisibility(View.GONE);
            textCurrentTime.setVisibility(View.GONE);
            textTotalDuration.setVisibility(View.GONE);
            playerSeekBar.setVisibility(View.GONE);
            subContent.setVisibility(View.VISIBLE);
            rd1.setVisibility(View.VISIBLE);
            rd2.setVisibility(View.VISIBLE);
            rd3.setVisibility(View.VISIBLE);
            rd4.setVisibility(View.VISIBLE);
            rdt1.setVisibility(View.GONE);
            rdt1.setVisibility(View.GONE);
            rdt2.setVisibility(View.GONE);
            rdt3.setVisibility(View.GONE);
            rdt4.setVisibility(View.GONE);
            rdt5.setVisibility(View.GONE);
            rdt6.setVisibility(View.GONE);
            rdt7.setVisibility(View.GONE);
            rdt8.setVisibility(View.GONE);
            edt1.setVisibility(View.GONE);
            tv_tf.setVisibility(View.GONE);
            tv_a.setVisibility(View.GONE);
            tv_b.setVisibility(View.GONE);
            tv_c.setVisibility(View.GONE);
            tv_d.setVisibility(View.GONE);
            rd1.setText(a);
            rd2.setText(b);
            rd3.setText(c);
            rd4.setText(d);
            subContent.setText(ct);
            rd1.setEnabled(false);
            rd2.setEnabled(false);
            rd3.setEnabled(false);
            rd4.setEnabled(false);
            if(answer!=null){
                if(answer.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                }
                if(answer.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                }
                if(answer.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                }
                if(answer.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                }}
            if(as!=null){
                if(as.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                }
                if(as.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                }
                if(as.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                }
                if(as.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                }
            }
        }
        if(loai==9){
            anhDe.setVisibility(View.GONE);
            txtContent.setVisibility(View.GONE);
            imagePlayPause.setVisibility(View.GONE);
            textCurrentTime.setVisibility(View.GONE);
            textTotalDuration.setVisibility(View.GONE);
            playerSeekBar.setVisibility(View.GONE);
            subContent.setVisibility(View.VISIBLE);
            rd1.setVisibility(View.VISIBLE);
            rd2.setVisibility(View.VISIBLE);
            rd3.setVisibility(View.VISIBLE);
            rd4.setVisibility(View.VISIBLE);
            rdt1.setVisibility(View.GONE);
            rdt1.setVisibility(View.GONE);
            rdt2.setVisibility(View.GONE);
            rdt3.setVisibility(View.GONE);
            rdt4.setVisibility(View.GONE);
            rdt5.setVisibility(View.GONE);
            rdt6.setVisibility(View.GONE);
            rdt7.setVisibility(View.GONE);
            rdt8.setVisibility(View.GONE);
            edt1.setVisibility(View.GONE);
            tv_tf.setVisibility(View.GONE);
            tv_a.setVisibility(View.GONE);
            tv_b.setVisibility(View.GONE);
            tv_c.setVisibility(View.GONE);
            tv_d.setVisibility(View.GONE);
            rd1.setText(a);
            rd2.setText(b);
            rd3.setText(c);
            rd4.setText(d);
            subContent.setText(ct);
            rd1.setEnabled(false);
            rd2.setEnabled(false);
            rd3.setEnabled(false);
            rd4.setEnabled(false);
            if(answer!=null){
                if(answer.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                }
                if(answer.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                }
                if(answer.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                }
                if(answer.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                }}
            if(as!=null){
                if(as.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                }
                if(as.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                }
                if(as.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                }
                if(as.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                }
            }
        }
        if(loai==10){
            anhDe.setVisibility(View.GONE);
            txtContent.setVisibility(View.VISIBLE);
            imagePlayPause.setVisibility(View.GONE);
            textCurrentTime.setVisibility(View.GONE);
            textTotalDuration.setVisibility(View.GONE);
            playerSeekBar.setVisibility(View.GONE);

            rd1.setVisibility(View.GONE);
            rd2.setVisibility(View.GONE);
            rd3.setVisibility(View.GONE);
            rd4.setVisibility(View.GONE);
            rdt1.setVisibility(View.VISIBLE);
            rdt1.setVisibility(View.VISIBLE);
            rdt2.setVisibility(View.VISIBLE);
            rdt3.setVisibility(View.VISIBLE);
            rdt4.setVisibility(View.VISIBLE);
            rdt5.setVisibility(View.VISIBLE);
            rdt6.setVisibility(View.VISIBLE);
            rdt7.setVisibility(View.VISIBLE);
            rdt8.setVisibility(View.VISIBLE);
            edt1.setVisibility(View.GONE);
            tv_tf.setVisibility(View.VISIBLE);
            tv_a.setVisibility(View.VISIBLE);
            tv_b.setVisibility(View.VISIBLE);
            tv_c.setVisibility(View.VISIBLE);
            tv_d.setVisibility(View.VISIBLE);
            tv_a.setText(a);
            tv_b.setText(b);
            tv_c.setText(c);
            tv_d.setText(d);

            //txtContent.setVisibility(View.VISIBLE);
            txtContent.setText(content+"\nQuestion: "+ct+" ?");
            rdt1.setEnabled(false);
            rdt2.setEnabled(false);
            rdt3.setEnabled(false);
            rdt4.setEnabled(false);
            rdt5.setEnabled(false);
            rdt6.setEnabled(false);
            rdt7.setEnabled(false);
            rdt8.setEnabled(false);

            if(as.length()==4&&!String.valueOf(as.charAt(0)).equalsIgnoreCase("A")){
            if(String.valueOf(as.charAt(0)).equalsIgnoreCase("T")){
                rdt1.setChecked(true);
            }
            if(String.valueOf(as.charAt(0)).equalsIgnoreCase("F")){
                rdt2.setChecked(true);
            }
            if(String.valueOf(as.charAt(1)).equalsIgnoreCase("T")){
                rdt3.setChecked(true);
            }
            if(String.valueOf(as.charAt(1)).equalsIgnoreCase("F")){
                rdt4.setChecked(true);
            }
            if(String.valueOf(as.charAt(2)).equalsIgnoreCase("T")){
                rdt5.setChecked(true);
            }
            if(String.valueOf(as.charAt(2)).equalsIgnoreCase("F")){
                rdt6.setChecked(true);
            }
            if(String.valueOf(as.charAt(3)).equalsIgnoreCase("T")){
                rdt7.setChecked(true);
            }
            if(String.valueOf(as.charAt(3)).equalsIgnoreCase("F")){
                rdt8.setChecked(true);
            }}
            else{
                String[] listA=as.split("");
                List<String> ListB = new ArrayList<>(Arrays.asList(listA));
                if(ListB.contains("A")){
                    rdt1.setChecked(true);
                }
                else rdt2.setChecked(true);
                if(ListB.contains("B")){
                    rdt3.setChecked(true);
                }
                else rdt4.setChecked(true);
                if(ListB.contains("C")){
                    rdt5.setChecked(true);
                }
                else rdt6.setChecked(true);
                if(ListB.contains("D")){
                    rdt7.setChecked(true);
                }
                else rdt8.setChecked(true);
            }
        }
        if(loai==8){
            anhDe.setVisibility(View.GONE);
            txtContent.setVisibility(View.VISIBLE);
            imagePlayPause.setVisibility(View.GONE);
            textCurrentTime.setVisibility(View.GONE);
            textTotalDuration.setVisibility(View.GONE);
            playerSeekBar.setVisibility(View.GONE);

            rd1.setVisibility(View.GONE);
            rd2.setVisibility(View.GONE);
            rd3.setVisibility(View.GONE);
            rd4.setVisibility(View.GONE);
            rdt1.setVisibility(View.GONE);
            rdt1.setVisibility(View.GONE);
            rdt2.setVisibility(View.GONE);
            rdt3.setVisibility(View.GONE);
            rdt4.setVisibility(View.GONE);
            rdt5.setVisibility(View.GONE);
            rdt6.setVisibility(View.GONE);
            rdt7.setVisibility(View.GONE);
            rdt8.setVisibility(View.GONE);
            edt1.setVisibility(View.VISIBLE);
            tv_tf.setVisibility(View.GONE);
            tv_a.setVisibility(View.GONE);
            tv_b.setVisibility(View.GONE);
            tv_c.setVisibility(View.GONE);
            tv_d.setVisibility(View.GONE);
            if(answer!=null)
            edt1.setText(answer);
            if(as!=null)
                edt1.setText(as);
            edt1.setFocusable(false);
        }
        if(loai==11){
            anhDe.setVisibility(View.VISIBLE);
            txtContent.setVisibility(View.GONE);
            imagePlayPause.setVisibility(View.GONE);
            textCurrentTime.setVisibility(View.GONE);
            textTotalDuration.setVisibility(View.GONE);
            playerSeekBar.setVisibility(View.GONE);
            subContent.setVisibility(View.GONE);
            rd1.setVisibility(View.VISIBLE);
            rd2.setVisibility(View.VISIBLE);
            rd3.setVisibility(View.VISIBLE);
            rd4.setVisibility(View.VISIBLE);
            rdt1.setVisibility(View.GONE);
            rdt1.setVisibility(View.GONE);
            rdt2.setVisibility(View.GONE);
            rdt3.setVisibility(View.GONE);
            rdt4.setVisibility(View.GONE);
            rdt5.setVisibility(View.GONE);
            rdt6.setVisibility(View.GONE);
            rdt7.setVisibility(View.GONE);
            rdt8.setVisibility(View.GONE);
            edt1.setVisibility(View.GONE);
            tv_tf.setVisibility(View.GONE);
            tv_a.setVisibility(View.GONE);
            tv_b.setVisibility(View.GONE);
            tv_c.setVisibility(View.GONE);
            tv_d.setVisibility(View.GONE);
            rd1.setText(a);
            rd2.setText(b);
            rd3.setText(c);
            rd4.setText(d);
            rd1.setEnabled(false);
            rd2.setEnabled(false);
            rd3.setEnabled(false);
            rd4.setEnabled(false);
            if(answer!=null){
                if(answer.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                }
                if(answer.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                }
                if(answer.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                }
                if(answer.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                }}
            if(as!=null){
                if(as.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                }
                if(as.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                }
                if(as.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                }
                if(as.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                }
            }
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void prepareMediaPlayer(){
        try {
            //mediaPlayer.setDataSource("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3");
            //mediaPlayer.setDataSource("https://apisertracnghiem.s3-ap-southeast-1.amazonaws.com/1619163358843-S_U2_P1_1.mp3");
            mediaPlayer.setDataSource(content);
            mediaPlayer.prepare();
            textTotalDuration.setText(milliSecondsToTimer(mediaPlayer.getDuration()));
        }
        catch (Exception e){
            Toast.makeText(FavoriteQuestionActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = mediaPlayer.getCurrentPosition();
            textCurrentTime.setText(milliSecondsToTimer(currentDuration));
        }
    };
    private void updateSeekBar(){
        if(mediaPlayer.isPlaying()){
            playerSeekBar.setProgress((int)(((float)mediaPlayer.getCurrentPosition()/mediaPlayer.getDuration())*100));
            handler.postDelayed(updater,1000);
        }
    }
    private String milliSecondsToTimer(long milliSeconds){
        String timerString = "";
        String secondString;

        int hours = (int)(milliSeconds/(1000*60*60));
        int minutes = (int) (milliSeconds%(1000*60*60))/(1000*60);
        int second = (int) ((milliSeconds%(1000*60*60))%(1000*60)/1000);

        if(hours>0){
            timerString = hours+":";
        }
        if(second<10){
            secondString = "0"+second;
        }
        else{
            secondString=""+second;
        }
        timerString = timerString+minutes+":"+secondString;
        return timerString;
    }
}
