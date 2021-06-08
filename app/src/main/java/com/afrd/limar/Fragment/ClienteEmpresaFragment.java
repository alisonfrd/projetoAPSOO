package com.afrd.limar.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afrd.limar.activity.AlteraDadosClientePJ;
import com.afrd.limar.Helper.RecyclerItemClickListener;
import com.afrd.limar.R;
import com.afrd.limar.activity.CadastroClienteActivityPJ;
import com.afrd.limar.model.AdapterClienteEmpresa;
import com.afrd.limar.model.ClientePessoaJuridica;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ClienteEmpresaFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterClienteEmpresa adapter;
    private DatabaseReference clientespjReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference().child("clientePJ") ;
    private ArrayList<ClientePessoaJuridica> listaCLientePJ = new ArrayList<>();
    private ValueEventListener valueEventListener;


    public ClienteEmpresaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cliente_empresa, container, false);

        //Configurando a toobar
        Toolbar toolbar = view.findViewById(R.id.toolbarClientes);
        toolbar.setTitle("Clientes");


        FloatingActionButton floatingActionButton = view.findViewById(R.id.fabClientes);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CadastroClienteActivityPJ.class);
                startActivity(intent);
            }
        });


        adapter = new AdapterClienteEmpresa(listaCLientePJ);

        //config recyclerView
        recyclerView = view.findViewById(R.id.recyclerClienteEmpresa);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayout.VERTICAL));

        recyclerView.setAdapter(adapter);

        //Add event click
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getActivity(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                ClientePessoaJuridica attCliente= listaCLientePJ.get(position);
                                Intent i = new Intent(getActivity(), AlteraDadosClientePJ.class);
                                //i.putExtra("clientepf", attLista);

                                i.putExtra("nome", attCliente.getNome());
                                i.putExtra("cnpj", attCliente.getCnpj());
                                i.putExtra("inscricaoEstadual", attCliente.getInscricaoEstadual());
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

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        recuperarDados();
    }

    @Override
    public void onStop() {
        super.onStop();
        listaCLientePJ.clear();
        clientespjReference.removeEventListener(valueEventListener);
    }

    public void recuperarDados(){

        valueEventListener= clientespjReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for(DataSnapshot data: snapshot.getChildren()){

                    ClientePessoaJuridica clientePessoaJuridica = data.getValue(ClientePessoaJuridica.class);
                    clientePessoaJuridica.setKey(data.getKey());

                    listaCLientePJ.add(clientePessoaJuridica);



                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


    }
}