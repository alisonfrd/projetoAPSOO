package com.afrd.limar.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afrd.limar.Helper.RecyclerItemClickListener;
import com.afrd.limar.R;
import com.afrd.limar.activity.AlteraDadosClientePF;
import com.afrd.limar.activity.CadastroClientesActivityPF;
import com.afrd.limar.model.AdapterClienteParticular;
import com.afrd.limar.model.Cliente;
import com.afrd.limar.model.ClientePessoaFisica;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ClienteParticularFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterClienteParticular adapter;
    private DatabaseReference clientespfReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference().child("clientePF") ;
    private ArrayList<ClientePessoaFisica> listaCLientePf = new ArrayList<>();
    private ValueEventListener valueEventListener;
    public ClienteParticularFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_cliente_particular2, container, false);

        //Configurando a toobar
        Toolbar toolbar = view.findViewById(R.id.toolbarClientes);
        toolbar.setTitle("Clientes");

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fabClientes);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CadastroClientesActivityPF.class);
                startActivity(intent);
            }
        });

        adapter = new AdapterClienteParticular(listaCLientePf);



        recyclerView = view.findViewById(R.id.recyclerClienteParticular);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayout.VERTICAL));

        recyclerView.setAdapter(adapter);


        //Envento de clicl//
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getActivity(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                ClientePessoaFisica attCliente= listaCLientePf.get(position);
                                Intent i = new Intent(getActivity(), AlteraDadosClientePF.class);
                                //i.putExtra("clientepf", attLista);

                                i.putExtra("nome", attCliente.getNome());
                                i.putExtra("cpf", attCliente.getCpf());
                                i.putExtra("nasc", attCliente.getDataNascimento());
                                i.putExtra("celular", attCliente.getCelular());
                                i.putExtra("email", attCliente.getEmail());
                                i.putExtra("endereco", attCliente.getEndereco());
                                i.putExtra("cidade", attCliente.getCidade());
                                i.putExtra("key", attCliente.getKey());

                                startActivity(i);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );



        // Inflate the layout for this fragment
        return view;


    }
    //criação dos menus da Toobar



    @Override
    public void onStart() {
        super.onStart();
        recuperarDados();
    }

    @Override
    public void onStop() {
        super.onStop();
        listaCLientePf.clear();
        clientespfReference.removeEventListener(valueEventListener);
    }

    public void recuperarDados(){

        valueEventListener= clientespfReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for(DataSnapshot data: snapshot.getChildren()){

                    ClientePessoaFisica clientePessoaFisica = data.getValue(ClientePessoaFisica.class);
                    clientePessoaFisica.setKey(data.getKey());

                    listaCLientePf.add(clientePessoaFisica);



                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


    }

}