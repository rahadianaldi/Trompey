package com.example.rpl.trompey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MakananHistoryAdapter extends RecyclerView.Adapter<MakananHistoryAdapter.ViewHolder> {
    private ArrayList<MakananHistory> history;
    private Context mContext;

    public MakananHistoryAdapter(Context context, ArrayList<MakananHistory> makanan) {
        this.history = makanan;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MakananHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_historymakan,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MakananHistory ob = history.get(position);
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
            nama = itemView.findViewById(R.id.namamakanan);
            harga = itemView.findViewById(R.id.hargamakanan);
        }

        public void bindTo (MakananHistory makanan){

            nama.setText(makanan.getNama());
            harga.setText(makanan.getHarga());
        }

        @Override
        public void onClick(View view) {

        }
    }

}
