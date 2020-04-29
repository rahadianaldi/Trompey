package com.example.rpl.trompey;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ObatAdapter extends RecyclerView.Adapter<ObatAdapter.ViewHolder> {
    private ArrayList<Obat> obatlist;
    private Context mContext;

    public ObatAdapter(Context context, ArrayList<Obat> obat) {
        this.obatlist = obat;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ObatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.obat_hewan,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Obat ob =obatlist.get(position);
        holder.bindTo(ob);
    }


    @Override
    public int getItemCount() {
        return obatlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nama,harga;
        ImageView gambar;
        Button beli;

        public ViewHolder(View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.gambar);
            nama = itemView.findViewById(R.id.nama);
            harga = itemView.findViewById(R.id.harga);
            beli = itemView.findViewById(R.id.btn_beli);
            beli.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Obat currentObat = obatlist.get(getAdapterPosition());
                    Intent detailIntent = new Intent(mContext, ObatHewanBayar.class);
                    detailIntent.putExtra("nama", currentObat.getNama());
                    detailIntent.putExtra("harga", currentObat.getHarga());
                    mContext.startActivity(detailIntent);
                }
            });
            itemView.setOnClickListener(this);
        }

        public void bindTo (Obat obat){

            nama.setText(obat.getNama());
            harga.setText(obat.getHarga());
            Glide.with(mContext).load(
                    obat.getGambar()).override(100).into(gambar);
        }

        @Override
        public void onClick(View view) {
            Obat currentObat = obatlist.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, ObatHewanDetail.class);
            detailIntent.putExtra("nama", currentObat.getNama());
            detailIntent.putExtra("harga", currentObat.getHarga());
            detailIntent.putExtra("gambar", currentObat.getGambar());
            detailIntent.putExtra("deskripsi", currentObat.getDeskripsi());
            mContext.startActivity(detailIntent);
        }


    }

}
