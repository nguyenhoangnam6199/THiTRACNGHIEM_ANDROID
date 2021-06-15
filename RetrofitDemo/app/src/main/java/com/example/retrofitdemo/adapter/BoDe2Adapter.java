package com.example.retrofitdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdemo.FavoriteQuestionActivity;
import com.example.retrofitdemo.KetQua;
import com.example.retrofitdemo.R;
import com.example.retrofitdemo.api.APIService;
import com.example.retrofitdemo.api.ItemClickListener;
import com.example.retrofitdemo.model.FavoriteQuestion;
import com.example.retrofitdemo.model.SignUpStatus;
import com.example.retrofitdemo.model.StartingTest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class RecyclerViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
{
    public TextView tv_tenBoDe,txt_tt_thoigian,txt_tt_soluong;
    public Button btn_del_fq;

    private ItemClickListener itemClickListener;

    public RecyclerViewHolder2(View itemView) {
        super(itemView);
        //txt_description = (TextView)itemView.findViewById(R.id.txtDescription);
        tv_tenBoDe = itemView.findViewById(R.id.tvTenBoDe);
        txt_tt_thoigian=itemView.findViewById(R.id.txt_tt_thoigian);
        txt_tt_soluong=itemView.findViewById(R.id.txt_tt_soluong);
        btn_del_fq=itemView.findViewById(R.id.btn_del_fq);
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

public class BoDe2Adapter extends RecyclerView.Adapter<RecyclerViewHolder2>{
    Context mContext;
    List<FavoriteQuestion> mData;
    public static String token;

    public BoDe2Adapter(Context mContext, List<FavoriteQuestion> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_yeu_thich,parent,false);

        //Gán animation
        Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.scale_list);
        itemView.startAnimation(animation);
        return new RecyclerViewHolder2(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder2 holder, final int position) {
        holder.tv_tenBoDe.setText(""+mData.get(position).getQuestion().getId());
        holder.txt_tt_thoigian.setText("Loại đề: "+mData.get(position).getQuestion().getType().name);
        String level="";
        if(mData.get(position).getQuestion().getLevel()==1)
            level="Dễ";
        if(mData.get(position).getQuestion().getLevel()==2)
            level="Trung bình";
        if(mData.get(position).getQuestion().getLevel()==3)
            level="Khó";
        holder.txt_tt_soluong.setText("Độ khó: "+level);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(final View view, int position, boolean isLongClick) {

                if(isLongClick)
                {
                    //Toast.makeText(mContext, "Bạn chọn : "+mData.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
                else
                {   token=KetQua.token;
                    Toast.makeText(mContext, ""+mData.get(position).getQuestion().getId(), Toast.LENGTH_SHORT).show();
                    for (FavoriteQuestion fq:KetQua.dsyeuthich) {
                        if(fq.getQuestion().getId()==mData.get(position).getQuestion().getId()){
                            KetQua.favoriteQuestion=fq;
                            Intent i=new Intent(view.getContext(), FavoriteQuestionActivity.class);
                            mContext.startActivity(i);
                        }
                    }

                }

            }
        });
        holder.btn_del_fq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIService.apiService.delFavoriteQuestion("Bearer "+KetQua.token,mData.get(position).getQuestion().getId()).enqueue(new Callback<SignUpStatus>() {
                    @Override
                    public void onResponse(Call<SignUpStatus> call, Response<SignUpStatus> response) {
                        if(response.isSuccessful()){
                            KetQua.dsyeuthich.remove(position);
                            notifyDataSetChanged();
                            APIService.apiService.getFavorite("Bearer "+KetQua.token).enqueue(new Callback<ArrayList<FavoriteQuestion>>() {
                                @Override
                                public void onResponse(Call<ArrayList<FavoriteQuestion>> call, Response<ArrayList<FavoriteQuestion>> response) {
                                    //ArrayList<FavoriteQuestion>list=new ArrayList<>();
                                    KetQua.dsyeuthich.clear();
                                    for (FavoriteQuestion b:response.body()) {
                                        //list.add(b);
                                        KetQua.dsyeuthich.add(b);
                                    }
                                    //KetQua.dsyeuthich=list;
                                    Toast.makeText(mContext,"Xóa thành công",Toast.LENGTH_SHORT).show();
//                                    Handler handler = new Handler();
//                                    handler.postDelayed(new Runnable() {
//                                        public void run() {
//                                            notifyDataSetChanged();
//                                        }
//                                    }, 3000);

                                }

                                @Override
                                public void onFailure(Call<ArrayList<FavoriteQuestion>> call, Throwable t) {
                                    Toast.makeText(mContext,"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
                                }
                            });
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    notifyDataSetChanged();
                                }
                            }, 1000);
                        }
                       /* Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                notifyDataSetChanged();
                            }
                        }, 3000);*/
                    }

                    @Override
                    public void onFailure(Call<SignUpStatus> call, Throwable t) {
                        Toast.makeText(mContext,"Không thể kết nối với API",Toast.LENGTH_SHORT).show();
                    }
                });
                //KetQua.dsyeuthich.remove(KetQua.dsyeuthich.size()-1);
                //notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}