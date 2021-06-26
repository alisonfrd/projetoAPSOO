package com.afrd.limar.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afrd.limar.R;
import com.afrd.limar.model.MaterialEmAtendimento;
import com.afrd.limar.model.Servico;

import java.util.List;

public class AdapterAddServicosEmAtendimento extends RecyclerView.Adapter<AdapterAddServicosEmAtendimento.MyViewHolder> {

    List<Servico> lista;

    public AdapterAddServicosEmAtendimento(List<Servico> lista) {
        this.lista = lista;
    }

    private RecyclerView recyclerView;
    private AdapterServicos adapterServicos;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_servicos, parent, false);
        return new AdapterAddServicosEmAtendimento.MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(AdapterAddServicosEmAtendimento.MyViewHolder holder, int position) {
        Servico servico = lista.get( position );
        holder.valor.setText(""+ servico.getValor());
        holder.descricao.setText(servico.getDescricao());
    }

    @Override
    public int getItemCount() {
        return lista.size();
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
