package com.example.retrofitdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.retrofitdemo.HomeActivity;
import com.example.retrofitdemo.R;
import com.example.retrofitdemo.model.BoDe;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class BoDeAdapter extends BaseAdapter {
    private HomeActivity context;
    private int layout;
    private List<BoDe> list;

    public BoDeAdapter(HomeActivity context, int layout, List<BoDe> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);
            holder.txt_de=convertView.findViewById(R.id.txt_de);
            convertView.setTag(holder);

        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }
        BoDe bode= list.get(position);
        holder.txt_de.setText("Tên Đề: "+bode.getName().toString());
        return convertView;
    }
    private class ViewHolder{
        TextView txt_de;

    }

}
