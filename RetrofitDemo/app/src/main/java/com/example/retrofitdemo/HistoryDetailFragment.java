package com.example.retrofitdemo;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdemo.api.APIService;
import com.example.retrofitdemo.model.SignUpStatus;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.ContextCompat.getColor;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryDetailFragment extends Fragment {


    private TextView textCurrentTime, textTotalDuration;
    private SeekBar playerSeekBar;
    public MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private String content;
    private ImageView anhDe;
    private ImageView saveFav;

    public static String choose="";
    public HistoryDetailFragment() {
    }

    public String getChoose() {
        return choose;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        KetQua.check=false;
        View itemView = inflater.inflate(R.layout.fragment_exam, container, false);
        TextView txtContent = itemView.findViewById(R.id.txtContent);
        //Ánh xạ
        final RadioButton rd1 = itemView.findViewById(R.id.rd1);
        final RadioButton rd2 = itemView.findViewById(R.id.rd2);
        final RadioButton rd3 = itemView.findViewById(R.id.rd3);
        final RadioButton rd4 = itemView.findViewById(R.id.rd4);
        RadioButton rdt1 = itemView.findViewById(R.id.rdt1);
        RadioButton rdt2 = itemView.findViewById(R.id.rdt2);
        RadioButton rdt3 = itemView.findViewById(R.id.rdt3);
        RadioButton rdt4 = itemView.findViewById(R.id.rdt4);
        RadioButton rdt5 = itemView.findViewById(R.id.rdt5);
        RadioButton rdt6 = itemView.findViewById(R.id.rdt6);
        RadioButton rdt7 = itemView.findViewById(R.id.rdt7);
        RadioButton rdt8 = itemView.findViewById(R.id.rdt8);
        TextView tv_tf = itemView.findViewById(R.id.tv_tf);
        TextView tv_a = itemView.findViewById(R.id.tv_a);
        TextView tv_b = itemView.findViewById(R.id.tv_b);
        TextView tv_c = itemView.findViewById(R.id.tv_c);
        TextView tv_d = itemView.findViewById(R.id.tv_d);
        TextView subContent=itemView.findViewById(R.id.txtSubContent);
        final TextView tv_des = itemView.findViewById(R.id.txt_desciption);
        Button btn_des = itemView.findViewById(R.id.btn_desciption);
        final EditText edt1 = itemView.findViewById(R.id.edt1);


        //Anh xa Audio
        final ImageView imagePlayPause;


        imagePlayPause = itemView.findViewById(R.id.imagePlayPause);
        textCurrentTime = itemView.findViewById(R.id.textCurrentTime);
        textTotalDuration = itemView.findViewById(R.id.textTotalDuration);
        playerSeekBar = itemView.findViewById(R.id.playSeekBar);
        anhDe = itemView.findViewById(R.id.imgAnhDe);
        mediaPlayer = new MediaPlayer();

        playerSeekBar.setMax(100);

        long loai = getArguments().getLong("loai");
//        txtLoai.setText(loai);{
        final long id = getArguments().getLong("id");
        KetQua.idCauHoi = (int)id;
        content = getArguments().getString("content");
        String description = getArguments().getString("description");
        String answer="";
        String useranswer="";
        String ct="";
        String a="";
        String b="";
        String c="";
        String d="";
        if(loai==3||loai==4||loai==5||loai==6||loai==7||loai==9||loai==10||loai==11) {
            if(getArguments().getString("detailcontent")!=null||getArguments().getString("detailcontent")!="")
                ct = getArguments().getString("detailcontent");
            a = getArguments().getString("a");
            b = getArguments().getString("b");
            c = getArguments().getString("c");
            d = getArguments().getString("d");
            answer=getArguments().getString("answer");
            useranswer=getArguments().getString("useranswer");
        }
        else{
            answer=getArguments().getString("answer");
            useranswer=getArguments().getString("useranswer");
        }
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
            String photo=getArguments().getString("photo");
            Picasso.with(getContext())
                    .load(photo)
                    .into(anhDe);
            if(loai==11||loai==7){
                Picasso.with(getContext())
                        .load(content)
                        .into(anhDe);
            }}
        ///////////////////////////////////////////////////////////////////////////////////
        tv_des.setText(description);
        tv_des.setVisibility(View.GONE);
        if(!KetQua.isStudy){
            btn_des.setVisibility(View.GONE);
        }
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
            if(useranswer!=null){
                if(useranswer.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                    rd1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                    rd2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                    rd3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                    rd4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }}
            if(answer!=null){
                if(answer.equalsIgnoreCase("A")){
                    //rd1.setChecked(true);
                    rd1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("B")){
                    //rd2.setChecked(true);
                    rd2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("C")){
                    //rd3.setChecked(true);
                    rd3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("D")){
                    //rd4.setChecked(true);
                    rd4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
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
            if(useranswer!=null){
                if(useranswer.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                    rd1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                    rd2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                    rd3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                    rd4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }}
            if(answer!=null){
                if(answer.equalsIgnoreCase("A")){
                    //rd1.setChecked(true);
                    rd1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("B")){
                    //rd2.setChecked(true);
                    rd2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("C")){
                   // rd3.setChecked(true);
                    rd3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("D")){
                   // rd4.setChecked(true);
                    rd4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
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
            if(useranswer!=null){
                if(useranswer.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                    rd1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                    rd2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                    rd3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                    rd4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }}
            if(answer!=null){
                if(answer.equalsIgnoreCase("A")){
                    //rd1.setChecked(true);
                    rd1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("B")){
                    //rd2.setChecked(true);
                    rd2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("C")){
                    //rd3.setChecked(true);
                    rd3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("D")){
                   // rd4.setChecked(true);
                    rd4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }}
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
            if(useranswer!=null){
                if(useranswer.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                    rd1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                    rd2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                    rd3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                    rd4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }}
            if(answer!=null){
                if(answer.equalsIgnoreCase("A")){
                   // rd1.setChecked(true);
                    rd1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("B")){
                   // rd2.setChecked(true);
                    rd2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("C")){
                   // rd3.setChecked(true);
                    rd3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("D")){
                   // rd4.setChecked(true);
                    rd4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }}
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
            if(useranswer!=null){
                if(useranswer.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                    rd1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                    rd2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                    rd3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                    rd4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }}
            if(answer!=null){
                if(answer.equalsIgnoreCase("A")){
                    //rd1.setChecked(true);
                    rd1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("B")){
                    //rd2.setChecked(true);
                    rd2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("C")){
                   // rd3.setChecked(true);
                    rd3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("D")){
                    //rd4.setChecked(true);
                    rd4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }}
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
            if(useranswer!=null){
                if(useranswer.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                    rd1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                    rd2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                    rd3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                    rd4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }}
            if(answer!=null){
                if(answer.equalsIgnoreCase("A")){
                    //rd1.setChecked(true);
                    rd1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("B")){
                    //rd2.setChecked(true);
                    rd2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("C")){
                   // rd3.setChecked(true);
                    rd3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("D")){
                   // rd4.setChecked(true);
                    rd4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }}
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
            if(useranswer!=null){
                if(useranswer.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                    rd1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                    rd2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                    rd3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                    rd4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }}
            if(answer!=null){
                if(answer.equalsIgnoreCase("A")){
                    //rd1.setChecked(true);
                    rd1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("B")){
                    //rd2.setChecked(true);
                    rd2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("C")){
                   // rd3.setChecked(true);
                    rd3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("D")){
                   // rd4.setChecked(true);
                    rd4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }}
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
            if(useranswer!=null){


                if(useranswer.length()==4&&!String.valueOf(useranswer.charAt(0)).equalsIgnoreCase("A")){
                    if(String.valueOf(useranswer.charAt(0)).equalsIgnoreCase("T")){
                        rdt1.setChecked(true);
                        rdt1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                    }
                    if(String.valueOf(useranswer.charAt(0)).equalsIgnoreCase("F")){
                        rdt2.setChecked(true);
                        rdt2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                    }
                    if(String.valueOf(useranswer.charAt(1)).equalsIgnoreCase("T")){
                        rdt3.setChecked(true);
                        rdt3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                    }
                    if(String.valueOf(useranswer.charAt(1)).equalsIgnoreCase("F")){
                        rdt4.setChecked(true);
                        rdt4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                    }
                    if(String.valueOf(useranswer.charAt(2)).equalsIgnoreCase("T")){
                        rdt5.setChecked(true);
                        rdt5.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                    }
                    if(String.valueOf(useranswer.charAt(2)).equalsIgnoreCase("F")){
                        rdt6.setChecked(true);
                        rdt6.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                    }
                    if(String.valueOf(useranswer.charAt(3)).equalsIgnoreCase("T")){
                        rdt7.setChecked(true);
                        rdt7.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                    }
                    if(String.valueOf(useranswer.charAt(3)).equalsIgnoreCase("F")){
                        rdt8.setChecked(true);
                        rdt8.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                    }}
                else{
                    String[] listC=useranswer.split("");
                    List<String> ListB = new ArrayList<>(Arrays.asList(listC));
                    if(ListB.contains("A")){
                        rdt1.setChecked(true);
                        rdt1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                    }
                    else {rdt2.setChecked(true);
                        rdt2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));}
                    if(ListB.contains("B")){
                        rdt3.setChecked(true);
                        rdt3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                    }
                    else {rdt4.setChecked(true);
                        rdt4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));}
                    if(ListB.contains("C")){
                        rdt5.setChecked(true);
                        rdt5.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                    }
                    else {rdt6.setChecked(true);
                        rdt6.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));}
                    if(ListB.contains("D")){
                        rdt7.setChecked(true);
                        rdt7.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                    }
                    else {rdt8.setChecked(true);
                        rdt8.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));}
                }}

            if(answer.length()==4&&!String.valueOf(answer.charAt(0)).equalsIgnoreCase("A")){
                if(String.valueOf(answer.charAt(0)).equalsIgnoreCase("T")){
                    // rdt1.setChecked(true);
                    rdt1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(String.valueOf(answer.charAt(0)).equalsIgnoreCase("F")){
                    //rdt2.setChecked(true);
                    rdt2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(String.valueOf(answer.charAt(1)).equalsIgnoreCase("T")){
                    //rdt3.setChecked(true);
                    rdt3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(String.valueOf(answer.charAt(1)).equalsIgnoreCase("F")){
                    // rdt4.setChecked(true);
                    rdt4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(String.valueOf(answer.charAt(2)).equalsIgnoreCase("T")){
                    // rdt5.setChecked(true);
                    rdt5.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(String.valueOf(answer.charAt(2)).equalsIgnoreCase("F")){
                    //rdt6.setChecked(true);
                    rdt6.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(String.valueOf(answer.charAt(3)).equalsIgnoreCase("T")){
                    // rdt7.setChecked(true);
                    rdt7.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(String.valueOf(answer.charAt(3)).equalsIgnoreCase("F")){
                    // rdt8.setChecked(true);
                    rdt8.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }}
            else{
                String[] listA=answer.split("");
                List<String> ListB = new ArrayList<>(Arrays.asList(listA));
                if(ListB.contains("A")){
                    // rdt1.setChecked(true);
                    rdt1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                else {//rdt2.setChecked(true);
                    rdt2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));}
                if(ListB.contains("B")){
                    // rdt3.setChecked(true);
                    rdt3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                else {//rdt4.setChecked(true);
                    rdt4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));}
                if(ListB.contains("C")){
                    // rdt5.setChecked(true);
                    rdt5.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                else {//rdt6.setChecked(true);
                    rdt6.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));}
                if(ListB.contains("D")){
                    //rdt7.setChecked(true);
                    rdt7.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                else {//rdt8.setChecked(true);
                    rdt8.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));}
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
            if(useranswer!=null)
            edt1.setText(useranswer);
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
            if(useranswer!=null){
                if(useranswer.equalsIgnoreCase("A")){
                    rd1.setChecked(true);
                    rd1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("B")){
                    rd2.setChecked(true);
                    rd2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("C")){
                    rd3.setChecked(true);
                    rd3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }
                if(useranswer.equalsIgnoreCase("D")){
                    rd4.setChecked(true);
                    rd4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.red)));
                }}
            if(answer!=null){
                if(answer.equalsIgnoreCase("A")){
                    //rd1.setChecked(true);
                    rd1.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("B")){
                    //rd2.setChecked(true);
                    rd2.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("C")){
                    //rd3.setChecked(true);
                    rd3.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }
                if(answer.equalsIgnoreCase("D")){
                    //rd4.setChecked(true);
                    rd4.setButtonTintList(ColorStateList.valueOf(getColor(getContext(),R.color.green)));
                }}
        }

        btn_des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Visi::",""+tv_des.getVisibility());
                Log.d("Visi::",""+View.GONE);
                if(tv_des.getVisibility()==View.GONE){
                    tv_des.setVisibility(View.VISIBLE);
                }
                else tv_des.setVisibility(View.GONE);
            }
        });

        saveFav=itemView.findViewById(R.id.imgFav);
        saveFav.setVisibility(View.GONE);
        return itemView;
    }


    private void prepareMediaPlayer(){
        try {
            mediaPlayer.setDataSource(content);
            mediaPlayer.prepare();
            textTotalDuration.setText(milliSecondsToTimer(mediaPlayer.getDuration()));
        }
        catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
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
    private int getID(){
        Random rand = new Random();
        int time = rand.nextInt(100)+1;
        return time;
    }

}
