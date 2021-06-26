package com.afrd.limar.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afrd.limar.R;
import com.afrd.limar.model.Equipamento;

import java.util.ArrayList;

public class AdapterAddEquipamentoEmAtendimento extends RecyclerView.Adapter<AdapterAddEquipamentoEmAtendimento.MyViewHolder> {
    ArrayList<Equipamento> listEquipamento;

    public AdapterAddEquipamentoEmAtendimento(ArrayList<Equipamento> listEquipamento) {
        this.listEquipamento = listEquipamento;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_materiais, parent, false);
        return new AdapterAddEquipamentoEmAtendimento.MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(AdapterAddEquipamentoEmAtendimento.MyViewHolder holder, int position) {
        Equipamento equipamento = listEquipamento.get( position );
        holder.modelo.setText(equipamento.getModelo());
        holder.tipo.setText(equipamento.getTipoEquipamento());
        holder.ambiente.setText(equipamento.getModelo());
    }

    @Override
    public int getItemCount() {
        return listEquipamento.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView modelo, tipo, ambiente;
        public MyViewHolder(View itemView) {
            super(itemView);

            modelo = itemView.findViewById(R.id.textViewDescricaoMaterial);
            tipo = itemView.findViewById(R.id.textViewQtdMaterial);
            ambiente = itemView.findViewById(R.id.textViewValorMaterial);
        }
    }

}
