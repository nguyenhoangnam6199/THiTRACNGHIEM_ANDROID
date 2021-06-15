package com.example.retrofitdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdemo.FavoriteQuestionActivity;
import com.example.retrofitdemo.HistoryActivity;
import com.example.retrofitdemo.KetQua;
import com.example.retrofitdemo.R;
import com.example.retrofitdemo.api.APIService;
import com.example.retrofitdemo.api.ItemClickListener;
import com.example.retrofitdemo.model.FavoriteQuestion;
import com.example.retrofitdemo.model.HistoryDetail;
import com.example.retrofitdemo.model.HistoryTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class RecyclerViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
{
    public TextView tv_tenBoDe,txt_tt_thoigian,txt_tt_thoigian2,txt_tt_soluong;

    private ItemClickListener itemClickListener;

    public RecyclerViewHolder3(View itemView) {
        super(itemView);
        //txt_description = (TextView)itemView.findViewById(R.id.txtDescription);
        tv_tenBoDe = itemView.findViewById(R.id.tvTenBoDe);
        txt_tt_thoigian=itemView.findViewById(R.id.txt_tt_thoigian);
        txt_tt_thoigian2=itemView.findViewById(R.id.txt_tt_thoigian2);
        txt_tt_soluong=itemView.findViewById(R.id.txt_tt_soluong);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}

public class BoDe3Adapter  extends RecyclerView.Adapter<RecyclerViewHolder3>{
    Context mContext;
    ArrayList<HistoryTest> mData;
    public static String token;

    public BoDe3Adapter(Context mContext, ArrayList<HistoryTest> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_lich_su,parent,false);

        //Gán animation
        Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.scale_list);
        itemView.startAnimation(animation);
        return new RecyclerViewHolder3(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder3 holder, int position) {
        holder.tv_tenBoDe.setText(""+mData.get(position).getName());
        String time=mData.get(position).getCreationDateTime();
        time=time.replaceAll("T"," ");
        time=time.replaceAll("Z","");
        holder.txt_tt_thoigian.setText("Thời gian bắt đầu: "+time);
        time=mData.get(position).getSubmitDateTime();
        time=time.replaceAll("T"," ");
        time=time.replaceAll("Z","");
        holder.txt_tt_thoigian2.setText("Thời gian nộp bài: "+time);
        holder.txt_tt_soluong.setText("Kết quả: "+mData.get(position).getResult());
        /*String level="";
        if(mData.get(position).getQuestion().getLevel()==1)
            level="Dễ";
        if(mData.get(position).getQuestion().getLevel()==2)
            level="Trung bình";
        if(mData.get(position).getQuestion().getLevel()==3)
            level="Khó";
        holder.txt_tt_soluong.setText("Độ khó: "+level);*/

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(final View view, int position, boolean isLongClick) {

                if(isLongClick)
                {
                    //Toast.makeText(mContext, "Bạn chọn : "+mData.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
                else
                {   token= KetQua.token;
                    //Toast.makeText(mContext, ""+mData.get(position).getQuestion().getId(), Toast.LENGTH_SHORT).show();
                    /*for (FavoriteQuestion fq:KetQua.dsyeuthich) {
                        if(fq.getQuestion().getId()==mData.get(position).getQuestion().getId()){
                            KetQua.favoriteQuestion=fq;
                            Intent i=new Intent(view.getContext(), FavoriteQuestionActivity.class);
                            mContext.startActivity(i);
                        }
                    }*/
                    APIService.apiService.getHistoryDetail("Bearer "+KetQua.token,mData.get(position).getId()).enqueue(new Callback<HistoryDetail>() {
                        @Override
                        public void onResponse(Call<HistoryDetail> call, Response<HistoryDetail> response) {
                            KetQua.historyDetail=response.body();
                            Intent i =new Intent(mContext, HistoryActivity.class);
                            mContext.startActivity(i);
                        }

                        @Override
                        public void onFailure(Call<HistoryDetail> call, Throwable t) {

                        }
                    });
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
