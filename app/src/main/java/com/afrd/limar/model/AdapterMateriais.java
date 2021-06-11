package com.afrd.limar.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afrd.limar.R;

import java.util.List;

public class AdapterMateriais extends RecyclerView.Adapter<AdapterMateriais.MyViewHolder> {

    private List<Materiais> listaMateriais;

    public AdapterMateriais(List<Materiais> listaMateriais) {
        this.listaMateriais = listaMateriais;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_materiais, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterMateriais.MyViewHolder holder, int position) {
        Materiais materiais = listaMateriais.get(position);

        holder.descricao.setText(materiais.getDescricao());
        holder.quantidade.setText(String.valueOf(materiais.getQuantidade()));
        holder.valor.setText(String.valueOf(materiais.getValorCusto()));
    }

    @Override
    public int getItemCount() {
        return listaMateriais.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView descricao, quantidade, valor;

        public MyViewHolder(View itemView) {
            super(itemView);

            descricao = itemView.findViewById(R.id.textViewDescricaoMaterial);
            quantidade = itemView.findViewById(R.id.textViewQtdMaterial);
            valor = itemView.findViewById(R.id.textViewValorMaterial);

        }
    }
}
