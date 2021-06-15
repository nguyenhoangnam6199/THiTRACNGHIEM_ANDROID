package com.example.retrofitdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
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
import android.widget.CheckBox;
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

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExamFragment extends Fragment {
    private TextView textCurrentTime, textTotalDuration;
    private SeekBar playerSeekBar;
    public  MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private String content;
    private ImageView anhDe;
    private ImageView saveFav;

    public static String choose="";
    public ExamFragment() {
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
        //Get giá trị
        int loai = getArguments().getInt("loai");
        final int id = getArguments().getInt("id");
        KetQua.idCauHoi = id;
        content = getArguments().getString("content");
        String description = getArguments().getString("description");
        String detailcontent="";
        String a="";
        String b="";
        String c="";
        String d="";
        if(loai==3||loai==4||loai==5||loai==6||loai==7||loai==9||loai==10||loai==11) {
            if(getArguments().getString("detailcontent")!=null||getArguments().getString("detailcontent")!="")
            detailcontent = getArguments().getString("detailcontent");
            a = getArguments().getString("a");
            b = getArguments().getString("b");
            c = getArguments().getString("c");
            d = getArguments().getString("d");
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
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////
        tv_des.setText(description);
        Log.d("Des::",""+description);
        Log.d("ID:: - :",""+KetQua.idList.get(id));
        tv_des.setVisibility(View.GONE);
        if(!KetQua.isStudy){
            btn_des.setVisibility(View.GONE);
        }
        txtContent.setText(content);
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
            subContent.setText(detailcontent);
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
            subContent.setText(detailcontent);
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
            subContent.setText(detailcontent);
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
            subContent.setText(detailcontent);
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
            subContent.setText(detailcontent);
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
            subContent.setVisibility(View.VISIBLE);
            subContent.setText(detailcontent);
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
        }
        btn_des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_des.getVisibility()==View.GONE){
                    tv_des.setVisibility(View.VISIBLE);
                }
                else tv_des.setVisibility(View.GONE);
            }
        });
        rd1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(id==KetQua.curPos){
                        KetQua.check = true;
                    }
                    KetQua.rdSelected=rd1.getText().toString();
                    choose=rd1.getText().toString();
                    KetQua.rdSelected="A";
                }
            }
        });
        rd2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(id==KetQua.curPos){
                        KetQua.check = true;
                    }
                    KetQua.rdSelected=rd2.getText().toString();
                    choose=rd2.getText().toString();
                    KetQua.rdSelected="B";
                }
            }
        });
        rd3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(id==KetQua.curPos){
                        KetQua.check = true;
                    }
                    KetQua.rdSelected=rd3.getText().toString();
                    choose=rd3.getText().toString();
                    KetQua.rdSelected="C";
                }

            }
        });
        rd4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(id==KetQua.curPos){
                        KetQua.check = true;
                    }
                    KetQua.rdSelected=rd4.getText().toString();
                    choose=rd4.getText().toString();
                    KetQua.rdSelected="D";
                }

            }
        });
        //---------------------------------------------------------------------------------
        rdt1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(id==KetQua.curPos){
                        KetQua.check = true;
                    }
                    KetQua.rdSelected="AT";
                    KetQua.selectedIn1Page.add("AT");
                    KetQua.selectedIn1Page.remove("AF");
                    choose="T";
                }
            }
        });
        rdt3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(id==KetQua.curPos){
                        KetQua.check = true;
                    }
                    KetQua.rdSelected="BT";
                    KetQua.selectedIn1Page.add("BT");
                    KetQua.selectedIn1Page.remove("BF");
                    choose="T";
                }
            }
        });
        rdt5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(id==KetQua.curPos){
                        KetQua.check = true;
                    }
                    KetQua.rdSelected="CT";
                    KetQua.selectedIn1Page.add("CT");
                    KetQua.selectedIn1Page.remove("CF");
                    choose="T";
                }
            }
        });
        rdt7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(id==KetQua.curPos){
                        KetQua.check = true;
                    }
                    KetQua.rdSelected="DT";
                    KetQua.selectedIn1Page.add("DT");
                    KetQua.selectedIn1Page.remove("DF");
                    choose="T";
                }
            }
        });
        rdt2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(id==KetQua.curPos){
                        KetQua.check = true;
                    }
                    KetQua.rdSelected="AF";
                    KetQua.selectedIn1Page.add("AF");
                    KetQua.selectedIn1Page.remove("AT");
                    choose="T";
                }
            }
        });
        rdt4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(id==KetQua.curPos){
                        KetQua.check = true;
                    }
                    KetQua.rdSelected="BF";
                    KetQua.selectedIn1Page.add("BF");
                    KetQua.selectedIn1Page.remove("BT");
                    choose="T";
                }
            }
        });
        rdt6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(id==KetQua.curPos){
                        KetQua.check = true;
                    }
                    KetQua.rdSelected="CF";
                    KetQua.selectedIn1Page.add("CF");
                    KetQua.selectedIn1Page.remove("CT");
                    choose="T";
                }
            }
        });
        rdt8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(id==KetQua.curPos){
                        KetQua.check = true;
                    }
                    KetQua.rdSelected="DF";
                    KetQua.selectedIn1Page.add("DF");
                    KetQua.selectedIn1Page.remove("DT");
                    choose="T";
                }
            }
        });
        edt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(id==KetQua.curPos){
                    KetQua.check = true;
                }
                KetQua.rdSelected=edt1.getText().toString().trim();
            }
        });
        //---------------Thêm câu yêu thích--------------------------------
        saveFav=itemView.findViewById(R.id.imgFav);
        saveFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIService.apiService.saveFavorite("Bearer "+KetQua.token,KetQua.idList.get(id)).enqueue(new Callback<SignUpStatus>() {
                    @Override
                    public void onResponse(Call<SignUpStatus> call, Response<SignUpStatus> response) {
                        Toast.makeText(getActivity(),"Đã thêm vào danh sách yêu thích",Toast.LENGTH_SHORT).show();
                        Notification notification = new NotificationCompat.Builder(getActivity(), com.example.retrofitdemo.model.Notification.CHANNEL_ID)
                                .setContentTitle("Thông báo !")
                                .setContentText("Đã thêm vào danh sách yêu thích câu hỏi "+KetQua.idList.get(id))
                                .setSmallIcon(R.drawable.ic_notifications_none_black_24dp)
                                // .setLargeIcon(bitmap)
                                .build();

                        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                        if(notificationManager!=null){
                            notificationManager.notify(getID(),notification);
                        }
                    }

                    @Override
                    public void onFailure(Call<SignUpStatus> call, Throwable t) {
                        Toast.makeText(getActivity(),"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return itemView;
    }


    private void prepareMediaPlayer(){
        try {
            //mediaPlayer.setDataSource("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3");
            //mediaPlayer.setDataSource("https://apisertracnghiem.s3-ap-southeast-1.amazonaws.com/1619163358843-S_U2_P1_1.mp3");
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
