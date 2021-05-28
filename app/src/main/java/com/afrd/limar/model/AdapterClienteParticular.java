package com.afrd.limar.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afrd.limar.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.afrd.limar.R.drawable.logo_ar;

public class AdapterClienteParticular extends RecyclerView.Adapter<AdapterClienteParticular.MyViewHolder> {

    ArrayList<String> arrayDeClientes = new ArrayList<>();

    public AdapterClienteParticular() {
        arrayDeClientes.add("Abner");
        arrayDeClientes.add("Burisvaldo");
        arrayDeClientes.add("Cleudistonho");
        arrayDeClientes.add("Darbiano");
        arrayDeClientes.add("Marciano");
        arrayDeClientes.add("NElson");
        arrayDeClientes.add("Xaviado");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cliente_particular, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nome.setText(arrayDeClientes.get(position).toString());


    }

    @Override
    public int getItemCount() {

        return arrayDeClientes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nome;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textViewNome);

        }
    }

}
