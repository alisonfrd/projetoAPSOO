package com.afrd.limar.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afrd.limar.Adapter.AdapterAddEquipamentoEmAtendimento;
import com.afrd.limar.Adapter.AdapterAddMateriaisEmAtendimento;
import com.afrd.limar.Adapter.AdapterClienteParticular;
import com.afrd.limar.R;
import com.afrd.limar.activity.AdicionarEquipamentoActivity;
import com.afrd.limar.activity.ServicosActivity;
import com.afrd.limar.model.Equipamento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class AdicionarEquipamentoEmAtendimentoActivity extends AppCompatActivity {
    private ArrayList<Equipamento> listaEquipamento = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterAddEquipamentoEmAtendimento adapterAddEquipamentoEmAtendimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_equipamento_em_atendimento);

        Intent i = getIntent();
        listaEquipamento = (ArrayList<Equipamento>)i.getSerializableExtra("listaAtendimento");



        if(!listaEquipamento.isEmpty()){
            adapterAddEquipamentoEmAtendimento = new AdapterAddEquipamentoEmAtendimento(listaEquipamento);
            recyclerView = findViewById(R.id.recyclerEquipamentosAdicionarAtendimento);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
            recyclerView.setAdapter(adapterAddEquipamentoEmAtendimento);
        }



        FloatingActionButton floatingActionButton = findViewById(R.id.fabAdicionarEquipamento);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdicionarEquipamentoActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    public void adicionarEquipamentoEmAtendimento(View view){
        Intent result = new Intent();
        result.putExtra("listaRetorno", (Serializable) listaEquipamento);
        result.putExtra("valida", "equipamentos");
        setResult(RESULT_OK, result);

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){

                Equipamento equipamento = new Equipamento();
                equipamento.setCodEquipamento(data.getStringExtra("codigo"));
                equipamento.setAmbiente(data.getStringExtra("ambiente"));
                equipamento.setTipoEquipamento(data.getStringExtra("tipo"));
                equipamento.setModelo(data.getStringExtra("modelo"));
                equipamento.setCapcidade(data.getStringExtra("capacidade"));
                equipamento.setFabricante(data.getStringExtra("fabricante"));
                equipamento.setTensaoEmOperação(data.getStringExtra("tensao"));
                equipamento.setCondicoesGerais(data.getStringExtra("consideracoes"));
                equipamento.setNivelDeRuido(data.getStringExtra("valorSlider"));


                listaEquipamento.add(equipamento);

                adapterAddEquipamentoEmAtendimento = new AdapterAddEquipamentoEmAtendimento(listaEquipamento);
                recyclerView = findViewById(R.id.recyclerEquipamentosAdicionarAtendimento);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
                recyclerView.setAdapter(adapterAddEquipamentoEmAtendimento);


            }
        }
    }
}