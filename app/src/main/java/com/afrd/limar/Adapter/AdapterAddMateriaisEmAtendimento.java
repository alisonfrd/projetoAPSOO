package com.afrd.limar.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afrd.limar.R;
import com.afrd.limar.model.Materiais;
import com.afrd.limar.model.MaterialEmAtendimento;

import java.util.ArrayList;
import java.util.List;

public class AdapterAddMateriaisEmAtendimento extends RecyclerView.Adapter<AdapterAddMateriaisEmAtendimento.MyViewHolder> {

    List<MaterialEmAtendimento> lista;

    public AdapterAddMateriaisEmAtendimento(List<MaterialEmAtendimento> lista) {
        this.lista = lista;



    }







    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_atendimentos_add_materiais, parent, false);
        return new AdapterAddMateriaisEmAtendimento.MyViewHolder(itemList);

    }

    @Override
    public void onBindViewHolder(AdapterAddMateriaisEmAtendimento.MyViewHolder holder, int position) {
        MaterialEmAtendimento listaMateriais = lista.get(position );

            holder.descricao.setText(listaMateriais.getDescricao());
            holder.quantidade.setText(String.valueOf(listaMateriais.getQuantidade()));
            holder.valor.setText(String.valueOf(listaMateriais.getValorVenda()));


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView descricao, quantidade, valor;
        private ImageButton buttonMais, buttonMenos;
        public MyViewHolder(View itemView) {
            super(itemView);

            descricao = itemView.findViewById(R.id.textViewDescricaoMaterialAdapter);
            quantidade = itemView.findViewById(R.id.textViewQtdMaterialAdapter);
            valor = itemView.findViewById(R.id.textViewValorMaterialAdapter);
        }
    }

}
