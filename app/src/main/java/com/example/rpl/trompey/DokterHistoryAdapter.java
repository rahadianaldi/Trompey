package com.example.rpl.trompey;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DokterHistoryAdapter extends RecyclerView.Adapter<DokterHistoryAdapter.ViewHolder> {
    private ArrayList<DokterHistory> history;
    private Context mContext;

    public DokterHistoryAdapter(Context context, ArrayList<DokterHistory> dktr){
        this.history = dktr;
        this.mContext = context;
    }

    @NonNull
    @Override
    public DokterHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.dokter_history,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DokterHistory dh = history.get(position);
        holder.bindTo(dh);
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView nama,harga,jadwal,waktu;

        public ViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama_dokter);
            harga = itemView.findViewById(R.id.harga_dokter);
            jadwal = itemView.findViewById(R.id.jadwal_dokter);
            waktu = itemView.findViewById(R.id.jam_janjian);

        }

        public void bindTo (DokterHistory Dokter){
            nama.setText(Dokter.getNama());
            harga.setText(Dokter.getHarga());
            jadwal.setText(Dokter.getJadwal());
            waktu.setText(Dokter.getWaktu());
        }
        @Override
        public void onClick(View view) {

        }
 }
}

