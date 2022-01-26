package com.abim.pc_05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<DetailVaksin> lists;
    Context ctx;
    Session s = new Session(ctx);

    public Adapter(List<DetailVaksin> lists, Context ctx) {
        this.lists = lists;
        this.ctx = ctx;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.card_data, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder( Adapter.ViewHolder holder, int position) {
        holder.tv_jenis.setText(lists.get(position).getNama_vaksin());
        holder.tv_nama.setText(lists.get(position).getNama_vaksin());
        holder.tv_periode.setText("Periode "+String.valueOf(lists.get(position).getNama_vaksin()));
        holder.tv_nik.setText(s.getNik());
        holder.tv_tgl.setText(lists.get(position).getTgl_vaksin());
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView tv_periode, tv_tgl, tv_nik, tv_nama, tv_jenis;
        LinearLayout layout;
        public ViewHolder( View v) {
            super(v);

            tv_jenis = v.findViewById(R.id.tv_jenis);
            tv_nama = v.findViewById(R.id.tv_nama);
            tv_tgl = v.findViewById(R.id.tv_tgl);
            tv_periode = v.findViewById(R.id.tv_periode);
            tv_nik = v.findViewById(R.id.tv_nik);
            layout = v.findViewById(R.id.linearl);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
