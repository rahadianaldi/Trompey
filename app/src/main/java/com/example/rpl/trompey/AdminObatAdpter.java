package com.example.rpl.trompey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class AdminObatAdpter extends RecyclerView.Adapter<AdminObatAdpter.ViewHolder> {
    private ArrayList<Obat> adminArrayList;
    private Context mContext;

    public AdminObatAdpter(ArrayList<Obat> adminArrayList, Context mContext) {
        this.adminArrayList = adminArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_admin,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Obat ob = adminArrayList.get(position);
            holder.bindTo(ob);
    }

    @Override
    public int getItemCount() {
        return adminArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            img = itemView.findViewById(R.id.img_obat);
        }

        public void bindTo(Obat ob) {
            name.setText(ob.getNama());
            Glide.with(mContext).load(ob.getGambar()).into(img);
        }
    }
}
