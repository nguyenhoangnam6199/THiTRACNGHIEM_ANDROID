package com.example.retrofitdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdemo.R;
import com.example.retrofitdemo.model.ThongTin;

import java.util.List;

public class ThongTinAdapter extends RecyclerView.Adapter<ThongTinAdapter.ViewHolder> {

    Context context;
    List<ThongTin> thongTinList;

    public ThongTinAdapter(Context context, List<ThongTin> thongTinList) {
        this.context = context;
        this.thongTinList = thongTinList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thong_tin,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(thongTinList!=null && thongTinList.size()>0){
            ThongTin m = thongTinList.get(position);
            holder.id_stt.setText(m.getStt());
            holder.id_ht.setText(m.getHoten());
            holder.id_msv.setText(m.getMasv());
        }
        else{
            return;
        }
    }

    @Override
    public int getItemCount() {
        return thongTinList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id_stt, id_ht, id_msv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id_stt = itemView.findViewById(R.id.id_stt);
            id_ht = itemView.findViewById(R.id.id_ht);
            id_msv = itemView.findViewById(R.id.id_msv);
        }
    }
}
