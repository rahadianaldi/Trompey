package com.example.rpl.trompey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ObatHistoryAdapter extends RecyclerView.Adapter<ObatHistoryAdapter.ViewHolder> {
    private ArrayList<ObatHistory> history;
    private Context mContext;

    public ObatHistoryAdapter(Context context, ArrayList<ObatHistory> obat) {
        this.history = obat;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ObatHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.obat_hewan_history,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ObatHistory ob = history.get(position);
        holder.bindTo(ob);
    }


    @Override
    public int getItemCount() {
        return history.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nama,harga;

        public ViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama_obat);
            harga = itemView.findViewById(R.id.harga_obat);
        }

        public void bindTo (ObatHistory obat){

            nama.setText(obat.getNama());
            harga.setText(obat.getHarga());
        }

        @Override
        public void onClick(View view) {

        }
    }

}
