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

public class DokterAdapter extends RecyclerView.Adapter<DokterAdapter.ViewHolder> {
    private ArrayList<Dokter> dokterlist;
    private Context mContext;

    public DokterAdapter(Context context, ArrayList<Dokter> dokter) {
        this.dokterlist = dokter;
        this.mContext = context;
    }

    @NonNull
    @Override
    public DokterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_dokter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Dokter dr =dokterlist.get(position);
        holder.bindTo(dr);
    }


    @Override
    public int getItemCount() {
        return dokterlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nama,harga;
        ImageView gambar;
        Button detail;

        public ViewHolder(View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.gambar);
            nama = itemView.findViewById(R.id.nama);
            harga = itemView.findViewById(R.id.harga);
            detail = itemView.findViewById(R.id.btn_detail);
            detail.setOnClickListener(this);
        }

        public void bindTo (Dokter dr){

            nama.setText(dr.getNama());
            harga.setText(dr.getHarga());
            Glide.with(mContext).load(
                    dr.getGambar()).override(100).into(gambar);
        }

        @Override
        public void onClick(View view) {
            Dokter currentDokter = dokterlist.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailDokter.class);
            detailIntent.putExtra("nama", currentDokter.getNama());
            detailIntent.putExtra("harga", currentDokter.getHarga());
            detailIntent.putExtra("jadwal", currentDokter.getJadwal());
            mContext.startActivity(detailIntent);
        }
    }

}
