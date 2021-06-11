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

public class AdapterClienteParticular extends RecyclerView.Adapter<AdapterClienteParticular.MyViewHolder> {

    private List<ClientePessoaFisica> listaClientes;
    public AdapterClienteParticular(ArrayList<ClientePessoaFisica> lista) {
        this.listaClientes = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_nome_descricao, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Collections.sort(listaClientes);

        ClientePessoaFisica cliente = listaClientes.get( position );
        holder.nome.setText( cliente.getNome());



    }

    @Override
    public int getItemCount() {

        return listaClientes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nome;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textViewNome);


        }
    }

}
