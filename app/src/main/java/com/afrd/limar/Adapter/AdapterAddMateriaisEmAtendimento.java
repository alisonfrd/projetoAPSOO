package com.afrd.limar.Adapter;

import android.util.Log;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdapterAddMateriaisEmAtendimento extends RecyclerView.Adapter<AdapterAddMateriaisEmAtendimento.MyViewHolder> {

    private static boolean excluir;
    List<MaterialEmAtendimento> lista;
    List<Materiais> listaBackup;
    private DatabaseReference materiaisReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference().child("materiais") ;


    public AdapterAddMateriaisEmAtendimento(List<MaterialEmAtendimento> lista,List<Materiais> listaBackup ) {
        this.lista = lista;
        this.listaBackup  = listaBackup;

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

            holder.imageButtonRemAddMaterial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removerItem(position);

                }
            });
    }

    public void removerItem(int position){
        lista.remove(position);


        //Materiais materiaisup = listaBackup.get( position );
        //materiaisReference.child(materiaisup.getKey()).removeValue();
       // materiaisReference.push().setValue(materiaisup);
        //listaBackup.remove(position);

        notifyItemRemoved(position);
        notifyItemRangeChanged(position, lista.size());



    }


    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView descricao, quantidade, valor;
        private ImageButton imageButtonRemAddMaterial;
        public MyViewHolder(View itemView) {
            super(itemView);

            descricao = itemView.findViewById(R.id.textViewDescricaoMaterialAdapter);
            quantidade = itemView.findViewById(R.id.textViewQtdMaterialAdapter);
            valor = itemView.findViewById(R.id.textViewValorMaterialAdapter);
            imageButtonRemAddMaterial = itemView.findViewById(R.id.imageButtonRemAddMaterial);
        }
    }

}
