package com.afrd.limar.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afrd.limar.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterServicos extends RecyclerView.Adapter<AdapterServicos.MyViewHolder> {


    private List<Servico> listaServico;

    public AdapterServicos(ArrayList<Servico> listaServico) {
        this.listaServico = listaServico;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_servicos, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(AdapterServicos.MyViewHolder holder, int position) {
        Servico servico = listaServico.get( position );
        holder.descricao.setText( servico.getDescricao());
        holder.valor.setText(String.valueOf(servico.getValor()));
    }

    @Override
    public int getItemCount() {
        return listaServico.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView descricao, valor;
        public MyViewHolder(View itemView) {
            super(itemView);

            descricao = itemView.findViewById(R.id.textViewDescricaoServico);
            valor = itemView.findViewById(R.id.textViewValorServico);
        }
    }
}
