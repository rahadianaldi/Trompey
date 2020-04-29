package com.example.rpl.trompey;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.ViewHolder> {
    Context context;
    ArrayList<Makanan> makanan;
    Button beli1;

    public MakananAdapter(Context context, ArrayList<Makanan> makanan) {
        this.context = context;
        this.makanan = makanan;
    }


    @NonNull
    @Override
    public MakananAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Makanan ri = makanan.get(position);
        holder.bindTo(ri);
    }

    @Override
    public int getItemCount() {
        return makanan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView gambarmakan;
        TextView judulmakan;
        TextView hargamakan;
        Button beli;



        public ViewHolder(View inflate) {
            super(inflate);
            judulmakan = inflate.findViewById(R.id.food_name);
            hargamakan = inflate.findViewById(R.id.food_harga);
            gambarmakan = inflate.findViewById(R.id.profile_pic);
            beli = inflate.findViewById(R.id.beli);
            beli.setOnClickListener(this);
            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Makanan currentMakan = makanan.get(getAdapterPosition());
                    Intent detailIntent = new Intent(context, MakananHewanDetail.class);
                    detailIntent.putExtra("nama", currentMakan.getJudulmakan());
                    detailIntent.putExtra("harga", currentMakan.getHargamakan());
                    detailIntent.putExtra("gambar", currentMakan.getGambarmakan());
                    detailIntent.putExtra("deskripsi", currentMakan.getDeskripsimakanan());
                    context.startActivity(detailIntent);

                }
            });
        }

        public void bindTo(Makanan ri) {
            judulmakan.setText(ri.getJudulmakan());
            hargamakan.setText(ri.getHargamakan());
            Glide.with(context).load(ri.getGambarmakan()).override(50).into(gambarmakan);
        }


        @Override
        public void onClick(View v) {
            Intent beli1 = new Intent(context, MakananBayar.class);
            Makanan currentObat = makanan.get(getAdapterPosition());
            beli1.putExtra("judulmakan", currentObat.getJudulmakan());
            beli1.putExtra("hargamakan", currentObat.getHargamakan());
            context.startActivity(beli1);
        }
    }
}
