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
import android.widget.Toast;

import com.afrd.limar.AlteraDadosServico;
import com.afrd.limar.Helper.RecyclerItemClickListener;
import com.afrd.limar.R;
import com.afrd.limar.model.AdapterMateriais;
import com.afrd.limar.model.AdapterServicos;
import com.afrd.limar.model.Materiais;
import com.afrd.limar.model.Servico;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServicosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterServicos adapterServicos;
    private DatabaseReference servicosReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference().child("servicos") ;
    private ArrayList<Servico> listaServico = new ArrayList<>();
    private ValueEventListener valueEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos);


        //Configuração do Botão flutuante
        FloatingActionButton floatingActionButton = findViewById(R.id.fabServicos);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroServicosActivity.class);
                startActivity(intent);
            }
        });

        //Configuração do recyclerVliew

        adapterServicos = new AdapterServicos(listaServico);
        recyclerView = findViewById(R.id.recyclerServicos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        recyclerView.setAdapter(adapterServicos);


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Servico attservico = listaServico.get( position );
                        Intent i = new Intent(getApplicationContext(), AlteraDadosServico.class);

                        i.putExtra("id", attservico.getCodigo());
                        i.putExtra("descricao", attservico.getDescricao());
                        i.putExtra("valor", attservico.getValor());
                        i.putExtra("key", attservico.getKey());
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
        listaServico.clear();
        servicosReference.removeEventListener(valueEventListener);
    }

    public void recuperarDados(){

        valueEventListener= servicosReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for(DataSnapshot data: snapshot.getChildren()){

                    Servico servico = data.getValue(Servico.class);
                    servico.setKey(data.getKey());

                    listaServico.add(servico);



                }
                adapterServicos.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


    }
}