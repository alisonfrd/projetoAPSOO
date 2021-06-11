package com.afrd.limar.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afrd.limar.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdapterClienteEmpresa extends RecyclerView.Adapter<AdapterClienteEmpresa.MyViewHolder> {

    private List<ClientePessoaJuridica> listaClientes;
    public AdapterClienteEmpresa(ArrayList<ClientePessoaJuridica> lista) {
        this.listaClientes = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_nome_descricao, parent, false);
        return new AdapterClienteEmpresa.MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(AdapterClienteEmpresa.MyViewHolder holder, int position) {
        Collections.sort(listaClientes);

        ClientePessoaJuridica cliente = listaClientes.get( position );
        holder.nome.setText( cliente.getNome());

    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nome;
        public MyViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textViewNome);
        }
    }
}
