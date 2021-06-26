package com.afrd.limar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afrd.limar.Adapter.AdapterAddMateriaisEmAtendimento;
import com.afrd.limar.R;
import com.afrd.limar.Adapter.AdapterMateriais;
import com.afrd.limar.model.Materiais;
import com.afrd.limar.model.MaterialEmAtendimento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdicionarMaterialActivity extends AppCompatActivity {
    private ArrayList<MaterialEmAtendimento> listMateriais = new ArrayList<>();
    private ArrayList<Materiais> attVoltar = new ArrayList<>();
    private AdapterAddMateriaisEmAtendimento adapterAddMateriaisEmAtendimento;
    private RecyclerView recyclerView;
    private DatabaseReference materiaisReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference().child("materiais") ;
    Materiais materiaisAtt; ;
    int contador = 0;
    private String descricao, key,unidade, forncedor,id;
    private int quantidadeEmEstoque =0 , quantidadeUsada = 0;
    private double valorVenda, valorCusto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_material);


        //Carregar os itens na tela de lista de Materiais
        Intent i = getIntent();
        listMateriais = (ArrayList<MaterialEmAtendimento>) i.getSerializableExtra("listaMateriais");

        //setting recyclerView
        adapterAddMateriaisEmAtendimento = new AdapterAddMateriaisEmAtendimento(listMateriais);
        recyclerView = findViewById(R.id.recyclerMateriaisAdicionarAtendimento);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapterAddMateriaisEmAtendimento);

        FloatingActionButton floatingActionButton = findViewById(R.id.fabAdicionarMateriais);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MateriaisActivity.class);
                intent.putExtra("chaveMaterial", "1");


                startActivityForResult(intent, 1);
            }
        });




    }

    public void buttonFinalizarAddMaterial(View view){
        Intent result = new Intent();
        result.putExtra("valida","materiais");
        result.putExtra("listaRetorno",(Serializable) listMateriais);



        setResult(RESULT_OK, result);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                if(!listMateriais.isEmpty()){
                    for(MaterialEmAtendimento lista : listMateriais){
                        if(lista.getDescricao().compareToIgnoreCase(data.getStringExtra("descricao"))==0){
                            contador = lista.getQuantidade();
                            listMateriais.remove(lista);
                        }
                    }
                }




                this.descricao = data.getStringExtra("descricao");
                this.key = data.getStringExtra("keyRecebe");
                this.unidade = data.getStringExtra("unidade");
                this.forncedor = data.getStringExtra("fornecedor");
                this.id = data.getStringExtra("id");
                this.quantidadeUsada = data.getIntExtra("quantidadeUsada", 0);
                this.quantidadeEmEstoque = data.getIntExtra("quantidade", 0);
                this.valorCusto = data.getDoubleExtra("valorCusto", 0 );
                this.valorVenda = data.getDoubleExtra("valorVenda", 0);
                //Lista para atualizar o Bd em caso de usuario voltar
                materiaisAtt = new Materiais();
                materiaisAtt.setDescricao(descricao);
                materiaisAtt.setKey(key);
                materiaisAtt.setUnidade(unidade);
                materiaisAtt.setFornecedor(forncedor);
                materiaisAtt.setId(Integer.parseInt(id));
                materiaisAtt.setQuantidade(quantidadeEmEstoque + contador);
                materiaisAtt.setValorVenda(valorVenda);
                materiaisAtt.setValorCusto(valorCusto);
                attVoltar.add(materiaisAtt);


                //Objeto para salvar os dados apenas em atendimentos
                MaterialEmAtendimento materiais = new MaterialEmAtendimento();
                materiais.setDescricao(descricao);
                materiais.setKey(key);
                materiais.setQuantidade(quantidadeUsada + contador);
                materiais.setValorVenda(valorVenda * quantidadeUsada);






                listMateriais.add(materiais);



                adapterAddMateriaisEmAtendimento = new AdapterAddMateriaisEmAtendimento(listMateriais);
                recyclerView = findViewById(R.id.recyclerMateriaisAdicionarAtendimento);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

                recyclerView.setAdapter(adapterAddMateriaisEmAtendimento);


            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        for(Materiais lista : attVoltar){
            materiaisReference.child(lista.getKey()).removeValue();
            //Toast.makeText(this, lista.getDescricao(), Toast.LENGTH_LONG).show();

            materiaisReference.push().setValue(lista);
        }









        //DatabaseReference materialReference = materiaisReference.child("materiais");

    }
}