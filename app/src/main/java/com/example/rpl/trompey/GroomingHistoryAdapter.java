package com.example.rpl.trompey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroomingHistoryAdapter extends RecyclerView.Adapter<GroomingHistoryAdapter.ViewHolder> {
    private ArrayList<GroomingHistory> history;
    private Context mContext;

    public GroomingHistoryAdapter(Context context, ArrayList<GroomingHistory> grooming) {
        this.history = grooming;
        this.mContext = context;
    }

    @NonNull
    @Override
    public GroomingHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_history_grooming,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroomingHistory ob = history.get(position);
        holder.bindTo(ob);
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView paket, isi, harga, metode, status;

        public ViewHolder(View itemView) {
            super(itemView);
            paket = itemView.findViewById(R.id.paket_grooming);
            isi = itemView.findViewById(R.id.isi_grooming);
            harga = itemView.findViewById(R.id.harga_grooming);
            metode = itemView.findViewById(R.id.metodepembayaran_grooming);
            status = itemView.findViewById(R.id.status_grooming);

        }
        public void bindTo (GroomingHistory grooming){

            paket.setText(grooming.getPaket());
            isi.setText(grooming.getIsi());
            harga.setText(grooming.getHarga());
            metode.setText(grooming.getMetode());
            status.setText(grooming.getStatus());

        }

        @Override
        public void onClick(View v) {


        }
    }
}
