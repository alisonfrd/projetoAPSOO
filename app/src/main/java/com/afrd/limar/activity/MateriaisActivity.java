package com.afrd.limar.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afrd.limar.Helper.RecyclerItemClickListener;
import com.afrd.limar.R;
import com.afrd.limar.Adapter.AdapterMateriais;
import com.afrd.limar.model.Materiais;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MateriaisActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterMateriais adapterMateriais;
    private DatabaseReference materiaisReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference().child("materiais") ;
    private ArrayList<Materiais> listaMateriais = new ArrayList<>();
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiais);


        //Configuração do Botão flutuante
        FloatingActionButton floatingActionButton = findViewById(R.id.fabEstoque);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroMateriais.class);
                startActivity(intent);
            }
        });

        //Configuração do recyclerVliew
        adapterMateriais = new AdapterMateriais(listaMateriais);
        recyclerView = findViewById(R.id.recyclerMateriais);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        recyclerView.setAdapter(adapterMateriais);


        //Recuperar dado de verificação para saber se o usuario clickou para Cadastro de material ou para adicionar o material em um atendimento
        Intent intent = getIntent();
        String value = intent.getStringExtra("chaveMaterial");

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //se o usuario clickou no botao de atendimento entao o fluxo segue normal (cadastro ou alteração de materiais)
                        if(value == null){
                            Materiais attMateriais = listaMateriais.get(position);
                            Intent i = new Intent(getApplicationContext(), AlteraDadosMateriais.class);

                            i.putExtra("id", String.valueOf(attMateriais.getId()));
                            i.putExtra("quantidade", String.valueOf(attMateriais.getQuantidade()));
                            i.putExtra("fornecedor", attMateriais.getFornecedor());
                            i.putExtra("descricao", attMateriais.getDescricao());
                            i.putExtra("unidade", attMateriais.getUnidade());
                            i.putExtra("valorCusto", String.valueOf(attMateriais.getValorCusto()));
                            i.putExtra("valorVenda", String.valueOf(attMateriais.getValorVenda()));
                            i.putExtra("key", attMateriais.getKey());
                            startActivity(i);
                        //Se ele clicou no botaão de add materiais no Campo de Atendimento então ele ira encaminhar os dados do material selecionado para serem salvos no BD
                        }else  if(value.compareTo("1") == 0){
                            Materiais returnMateriais = listaMateriais.get(position);

                            Intent intent1 = new Intent(getApplicationContext(),AdicionarQuantidadeDoItemActivity.class);

                            intent1.putExtra("id", String.valueOf(returnMateriais.getId()));
                            intent1.putExtra("fornecedor", returnMateriais.getFornecedor());
                            intent1.putExtra("unidade", returnMateriais.getUnidade());
                            intent1.putExtra("descricao", returnMateriais.getDescricao());
                            intent1.putExtra("quantidade", returnMateriais.getQuantidade());
                            intent1.putExtra("valorCusto", returnMateriais.getValorCusto());
                            intent1.putExtra("valorVenda", returnMateriais.getValorVenda());
                            intent1.putExtra("key", returnMateriais.getKey());

                            startActivityForResult(intent1, 1);

                        }

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                //recebe os dados da ActivityAdicionarQuantidadeDoItem
                String descricao, key,unidade, forncedor,id;
                int quantidade, quantidadeUsada;
                double valorVenda, valorCusto;

                descricao = data.getStringExtra("descricao");
                key = data.getStringExtra("keyRecebe");
                unidade = data.getStringExtra("unidade");
                forncedor = data.getStringExtra("fornecedor");
                id = data.getStringExtra("id");
                quantidade = data.getIntExtra("quantidade", 0);

                quantidadeUsada = data.getIntExtra("quantidadeUsada", 0);

                valorVenda = data.getDoubleExtra("valorVenda", 0);
                valorCusto = data.getDoubleExtra("valorCusto", 0);




                //Encaminha os dados dos itens para activiti de Adicionarmateriais para atualizar a lista de materiais utilizados
                Intent result = new Intent();
                result.putExtra("descricao",descricao);
                result.putExtra("valorVenda",valorVenda);
                result.putExtra("quantidadeUsada",quantidadeUsada);
                result.putExtra("keyRecebe",key);

                result.putExtra("unidade",unidade);
                result.putExtra("fornecedor",forncedor);
                result.putExtra("id",id);
                result.putExtra("quantidade",quantidade);
                result.putExtra("valorCusto",valorCusto);


                //atualização no BD
                materiaisReference.child(key).removeValue();

                Materiais materiaisAtt = new Materiais();
                materiaisAtt.setDescricao(descricao);
                materiaisAtt.setKey(key);
                materiaisAtt.setUnidade(unidade);
                materiaisAtt.setFornecedor(forncedor);
                materiaisAtt.setId(Integer.parseInt(id));
                materiaisAtt.setQuantidade(quantidade - quantidadeUsada);
                materiaisAtt.setValorVenda(valorVenda);
                materiaisAtt.setValorCusto(valorCusto);




                //DatabaseReference materialReference = materiaisReference.child("materiais");
                materiaisReference.child(key).setValue(materiaisAtt);




                setResult(RESULT_OK, result);
                finish();


            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        recuperarDados();
    }

    @Override
    public void onStop() {
        super.onStop();
        listaMateriais.clear();
        materiaisReference.removeEventListener(valueEventListener);
    }

    public void recuperarDados(){

        valueEventListener= materiaisReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for(DataSnapshot data: snapshot.getChildren()){

                    Materiais materiais = data.getValue(Materiais.class);
                    materiais.setKey(data.getKey());

                    listaMateriais.add(materiais);




                }
                adapterMateriais.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


    }
}