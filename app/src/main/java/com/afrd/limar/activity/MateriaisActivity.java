package com.afrd.limar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.afrd.limar.Helper.RecyclerItemClickListener;
import com.afrd.limar.R;
import com.afrd.limar.model.AdapterMateriais;
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

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
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