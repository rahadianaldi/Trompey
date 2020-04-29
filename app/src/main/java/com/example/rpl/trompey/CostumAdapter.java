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

public class CostumAdapter extends RecyclerView.Adapter<CostumAdapter.ViewHolder> {
    Context context;
    ArrayList<RowItem>rowItems;

    public CostumAdapter(Context context,  ArrayList<RowItem>rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }


    @NonNull
    @Override
    public CostumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RowItem ri = rowItems.get(position);
        holder.bindTo(ri);
    }

    @Override
    public int getItemCount() {
        return rowItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_pic;
        TextView member_name;
        TextView status;
        TextView contactType;

        public ViewHolder(View inflate) {
            super(inflate);
            member_name = inflate.findViewById(R.id.food_name);
            status = inflate.findViewById(R.id.food_harga);
            profile_pic = inflate.findViewById(R.id.profile_pic);
        }

        public void bindTo(RowItem ri) {

            member_name.setText(ri.getMember_name());
            status.setText(ri.getStatus());
            Glide.with(context).load(ri.getProfile_pic_id()).override(50).into(profile_pic);
        }
    }
}
