package com.example.rpl.trompey;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.ViewHolder> {

    private ArrayList<GroomingAdmin> adminArrayList;
    private Context mContext;

    public AdminAdapter(ArrayList<GroomingAdmin> adminArrayList, Context mContext) {
        this.adminArrayList = adminArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AdminAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_grooming,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAdapter.ViewHolder holder, int position) {
        GroomingAdmin gd = adminArrayList.get(position);
        holder.bindTo(gd);
    }

    @Override
    public int getItemCount() {
        return adminArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,status;
        ImageView img;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            status = itemView.findViewById(R.id.tv_status);
            img = itemView.findViewById(R.id.profile_pic);
            cardView = itemView.findViewById(R.id.list_item_admin);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GroomingAdmin ga = adminArrayList.get(getAdapterPosition());
                    Intent detailGrooming = new Intent(mContext,AdminDetailsActivity.class);
                    detailGrooming.putExtra("id",ga.getId());

                    mContext.startActivity(detailGrooming);
                }
            });
        }

        public void bindTo(GroomingAdmin gd) {
            name.setText(gd.getEmail());
            status.setText(gd.getStatus());
        }
    }
}
