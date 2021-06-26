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

import com.afrd.limar.Adapter.AdapterAddMateriaisEmAtendimento;
import com.afrd.limar.Adapter.AdapterAddServicosEmAtendimento;
import com.afrd.limar.Adapter.AdapterServicos;
import com.afrd.limar.R;
import com.afrd.limar.activity.MateriaisActivity;
import com.afrd.limar.activity.ServicosActivity;
import com.afrd.limar.model.MaterialEmAtendimento;
import com.afrd.limar.model.Servico;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class AdicionarServicoActivity extends AppCompatActivity {
    private ArrayList<Servico> listaServico = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterAddServicosEmAtendimento adapteraddServicosEmAtendimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_servico);

        Intent intent = getIntent();
        listaServico =(ArrayList<Servico>) intent.getSerializableExtra("listaServicos");

        adapteraddServicosEmAtendimento = new AdapterAddServicosEmAtendimento(listaServico);
        recyclerView = findViewById(R.id.recyclerServicosAdicionarAtendimento);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        recyclerView.setAdapter(adapteraddServicosEmAtendimento);

        FloatingActionButton floatingActionButton = findViewById(R.id.fabAdicionarServicos);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ServicosActivity.class);
                intent.putExtra("chaveServico", "1");
                startActivityForResult(intent, 1);
            }
        });


    }

    public void buttonFinalizarAddServico(View view){
        Intent result = new Intent();
        result.putExtra("valida","servicos");
        result.putExtra("listaRetorno",(Serializable) listaServico);



        setResult(RESULT_OK, result);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if(resultCode == RESULT_OK){
                try {
                    if(!listaServico.isEmpty()){
                        for(Servico lista : listaServico){
                            if(lista.getDescricao().compareToIgnoreCase(data.getStringExtra("descricao"))==0){

                                listaServico.remove(lista);
                            }
                        }
                    }
                }catch (ConcurrentModificationException ex ){

                }


                String id = data.getStringExtra("id");
                String descricao = data.getStringExtra("descricao");
                double valor = data.getDoubleExtra("valor", 0);
                String key = data.getStringExtra("key");


                Servico returnServico = new Servico(id, descricao, valor);
                returnServico.setKey(key);

                listaServico.add(returnServico);



                adapteraddServicosEmAtendimento = new AdapterAddServicosEmAtendimento(listaServico);
                recyclerView = findViewById(R.id.recyclerServicosAdicionarAtendimento);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

                recyclerView.setAdapter(adapteraddServicosEmAtendimento);

            }
        }
    }
}