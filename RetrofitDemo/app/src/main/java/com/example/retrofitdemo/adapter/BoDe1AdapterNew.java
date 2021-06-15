package com.example.retrofitdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdemo.HomeActivity;
import com.example.retrofitdemo.KetQua;
import com.example.retrofitdemo.NavigationActivity;
import com.example.retrofitdemo.R;
import com.example.retrofitdemo.ThiActivity;
import com.example.retrofitdemo.ThiNewActivity;
import com.example.retrofitdemo.api.APIService;
import com.example.retrofitdemo.api.ItemClickListener;
import com.example.retrofitdemo.model.BoDe;
import com.example.retrofitdemo.model.FavoriteQuestion;
import com.example.retrofitdemo.model.StartingTest;
import com.example.retrofitdemo.model.StartingTest2;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class RecyclerViewHolderNew extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
{
    public TextView tv_tenBoDe,txt_tt_thoigian,txt_tt_soluong;

    private ItemClickListener itemClickListener;

    public RecyclerViewHolderNew(View itemView) {
        super(itemView);
        //txt_description = (TextView)itemView.findViewById(R.id.txtDescription);
        tv_tenBoDe = itemView.findViewById(R.id.tvTenBoDe);
        txt_tt_thoigian=itemView.findViewById(R.id.txt_tt_thoigian);
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


public class BoDe1AdapterNew extends RecyclerView.Adapter<RecyclerViewHolder>{
    Context mContext;
    List<BoDe> mData;
    public static String token;
    public static long idChosen;
    public static long time;
    public static StartingTest2 s;

    public BoDe1AdapterNew(Context mContext, List<BoDe> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_bo_de,parent,false);

        //Gán animation
        Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.scale_list);
        itemView.startAnimation(animation);

        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.tv_tenBoDe.setText(mData.get(position).getName());
        holder.txt_tt_thoigian.setText("Thời gian: "+mData.get(position).getTime()+" phút");
        holder.txt_tt_soluong.setText("Số lượng: "+mData.get(position).getQuantity()+" câu");

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(final View view, int position, boolean isLongClick) {
                idChosen= KetQua.dsbode.get(position).getId();
                time = KetQua.dsbode.get(position).getTime() * 1000 * 60;
                if(isLongClick)
                {
                    Toast.makeText(mContext, "Bạn chọn : "+mData.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
                else
                {   token=KetQua.token;
                    Toast.makeText(mContext, mData.get(position).getName(), Toast.LENGTH_SHORT).show();
                    APIService.apiService.startExam2("Bearer "+KetQua.token,idChosen).enqueue(new Callback<StartingTest2>() {
                        @Override
                        public void onResponse(Call<StartingTest2> call, Response<StartingTest2> response) {
                            Context context=view.getContext();
                            Intent intent= new Intent(context, ThiNewActivity.class);


                            StartingTest2 startingTest=response.body();
                            s=response.body();
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("TEST",  startingTest);

                            Instant tgketthuc=Instant.parse(startingTest.expirationDateTime.toString());

                            Instant tghientai=Instant.now();
                            //Instant value = tghientai.minus(7, ChronoUnit.HOURS);
                            time = tghientai.until(tgketthuc, ChronoUnit.MINUTES)*1000*60;
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<StartingTest2> call, Throwable t) {
                            Toast.makeText(view.getContext(),"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
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

