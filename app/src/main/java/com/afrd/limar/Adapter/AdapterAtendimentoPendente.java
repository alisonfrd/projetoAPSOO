package com.afrd.limar.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afrd.limar.R;
import com.afrd.limar.model.Atendimento;
import com.afrd.limar.model.Cliente;
import com.afrd.limar.model.ClientePessoaFisica;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterAtendimentoPendente extends RecyclerView.Adapter<AdapterAtendimentoPendente.MyViewHolder> {
    private ArrayList<ArrayList> listaDeDadosDoAtendimento ;

    ArrayList<Object> listObjItens;
    ArrayList<Object> listaObjServico;
    ArrayList<Object> listaEquipamento;
    ClientePessoaFisica clientePessoaFisica;

    public AdapterAtendimentoPendente(ArrayList<ArrayList> listaDeDadosDoAtendimento) {
        this.listaDeDadosDoAtendimento = listaDeDadosDoAtendimento;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_atendimento_pendente, parent, false);
        return new AdapterAtendimentoPendente.MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(AdapterAtendimentoPendente.MyViewHolder holder, int position) {
        Atendimento atttt = (Atendimento) ((ArrayList)listaDeDadosDoAtendimento.get( position )).get(0);
        ClientePessoaFisica cliente = (ClientePessoaFisica) ((ArrayList)listaDeDadosDoAtendimento.get( position )).get(4);

        holder.textViewNomeCliente.setText(cliente.getNome());
        holder.textViewDataAtendimentoAdapter.setText(atttt.getDataIncio());
        holder.textViewHoraAtendimentoAdapter.setText(atttt.getHoraInicio());
        holder.textViewEnderecoAdapter.setText(cliente.getEndereco());
        holder.textViewCidadeAdapter.setText(cliente.getCidade());

    }

    @Override
    public int getItemCount() {
        return listaDeDadosDoAtendimento.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewNomeCliente,textViewDataAtendimentoAdapter,textViewHoraAtendimentoAdapter,textViewEnderecoAdapter,textViewCidadeAdapter;
        public MyViewHolder( View itemView) {
            super(itemView);

            textViewNomeCliente = itemView.findViewById(R.id.textViewNomeCliente);
            textViewDataAtendimentoAdapter = itemView.findViewById(R.id.textViewDataAtendimentoAdapter);
            textViewHoraAtendimentoAdapter = itemView.findViewById(R.id.textViewHoraAtendimentoAdapter);
            textViewEnderecoAdapter = itemView.findViewById(R.id.textViewEnderecoAdapter);
            textViewCidadeAdapter = itemView.findViewById(R.id.textViewCidadeAdapter);
        }
    }

}
