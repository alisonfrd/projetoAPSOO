 package com.afrd.limar.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afrd.limar.Adapter.AdapterAtendimentoPendente;
import com.afrd.limar.Adapter.AdapterClienteParticular;
import com.afrd.limar.R;
import com.afrd.limar.activity.CadastroAtendimentoActivity;
import com.afrd.limar.model.Atendimento;
import com.afrd.limar.model.Cliente;
import com.afrd.limar.model.ClientePessoaFisica;
import com.afrd.limar.model.ClientePessoaJuridica;
import com.afrd.limar.model.Equipamento;
import com.afrd.limar.model.Materiais;
import com.afrd.limar.model.MaterialEmAtendimento;
import com.afrd.limar.model.Servico;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class AtendimentoPendenciaFragment extends Fragment {


    private RecyclerView recyclerView;
    private AdapterAtendimentoPendente adapter;

    private DatabaseReference reference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference().child("atendimentos");
    private ValueEventListener valueEventListener;


    private  ArrayList<ArrayList> listaGeral = new ArrayList<>();

    ArrayList<MaterialEmAtendimento> materialEmAtendimentos = new ArrayList<>();
    ArrayList<Servico> listServicos = new ArrayList<>();
    ArrayList<Equipamento> listEquipamento = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_atendimento_pendencia, container, false);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fabAtendimentos);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), CadastroAtendimentoActivity.class);
                startActivity(intent);
            }
        });

        adapter = new AdapterAtendimentoPendente(listaGeral);


        recyclerView = view.findViewById(R.id.recyclerAtendimentoPendente);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);





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
        listaGeral.clear();
        reference.removeEventListener(valueEventListener);
    }

    public void recuperarDados() {

        valueEventListener = reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot snapshot) {



                for (DataSnapshot data : snapshot.getChildren()) {
                    //ArrayList<Object> obj = (ArrayList<Object>) data.getValue();
                    ArrayList<Object> listaDeDadosDoAtendimento = new ArrayList<>();


                    Atendimento atendimento = data.child("0").getValue(Atendimento.class);
                    String  status = atendimento.getStatus();
                    listaDeDadosDoAtendimento.add(atendimento);


                    //Rupera segundo objeto
                    ArrayList<Object> listObjItens = (ArrayList<Object>) data.child("1").getValue();
                    MaterialEmAtendimento materialEmAtendimento;
                    String descricao;
                    String key ;
                    long quantidade ;
                    long valorVenda ;
                    if (listObjItens != null){
                        for(int i=0; i<listObjItens.size(); i++){
                            descricao= (String) ((HashMap)listObjItens.get(i)).get("descricao");
                            key = (String) ((HashMap)listObjItens.get(i)).get("key");
                            quantidade = (long) ((HashMap)listObjItens.get(i)).get("quantidade");
                            valorVenda = (long) ((HashMap)listObjItens.get(i)).get("valorVenda");
                            materialEmAtendimento = new MaterialEmAtendimento(descricao,key, valorVenda, (int)quantidade );

                            materialEmAtendimentos.add(materialEmAtendimento);


                        }
                    }
                    listaDeDadosDoAtendimento.add(materialEmAtendimentos);


                    ArrayList<Object> listaObjServico = (ArrayList<Object>) data.child("2").getValue();
                    Servico servico;
                    String codigoS;
                    long valorS;
                    String descricaoS;
                    String keyS;

                    if (listaObjServico != null){
                        for(int i=0; i<listaObjServico.size(); i++){
                            codigoS= (String) ((HashMap)listaObjServico.get(i)).get("codigo");
                            valorS = (long) ((HashMap)listaObjServico.get(i)).get("valor");
                            descricaoS = (String) ((HashMap)listaObjServico.get(i)).get("descricao");
                            keyS = (String) ((HashMap)listaObjServico.get(i)).get("key");


                            servico = new Servico(codigoS, descricaoS, valorS);

                            listServicos.add(servico);


                        }
                    }
                    listaDeDadosDoAtendimento.add(listServicos);

                    ArrayList<Object> listaEquipamento = (ArrayList<Object>) data.child("3").getValue();
                    Equipamento equipamento;
                     String codEquipamentoE;
                     String ambienteE;
                     String tipoEquipamentoE;
                     String modeloE;
                     String capcidadeE;
                     String fabricanteE;
                     String tensaoEmOperaçãoE;
                     String nivelDeRuidoE;
                     String condicoesGeraisE;
                     String keyE;

                    if (listaEquipamento != null){
                        for(int i=0; i<listaEquipamento.size(); i++){
                            codEquipamentoE= (String) ((HashMap)listaEquipamento.get(i)).get("codEquipamento");
                            ambienteE = (String) ((HashMap)listaEquipamento.get(i)).get("ambiente");
                            tipoEquipamentoE = (String) ((HashMap)listaEquipamento.get(i)).get("tipoEquipamento");
                            modeloE = (String) ((HashMap)listaEquipamento.get(i)).get("modelo");
                            capcidadeE = (String) ((HashMap)listaEquipamento.get(i)).get("capcidade");
                            fabricanteE = (String) ((HashMap)listaEquipamento.get(i)).get("fabricante");
                            tensaoEmOperaçãoE = (String) ((HashMap)listaEquipamento.get(i)).get("tensaoEmOperação");
                            nivelDeRuidoE = (String) ((HashMap)listaEquipamento.get(i)).get("nivelDeRuido");
                            condicoesGeraisE = (String) ((HashMap)listaEquipamento.get(i)).get("condicoesGerais");
                            keyE = (String) ((HashMap)listaEquipamento.get(i)).get("key");


                            equipamento = new Equipamento(codEquipamentoE, ambienteE, tipoEquipamentoE,modeloE,capcidadeE, fabricanteE, tensaoEmOperaçãoE, nivelDeRuidoE, condicoesGeraisE);

                            listEquipamento.add(equipamento);


                        }
                    }
                    listaDeDadosDoAtendimento.add(listEquipamento);




                    ClientePessoaFisica clientePessoaFisica =  data.child("4").getValue(ClientePessoaFisica.class);
                    listaDeDadosDoAtendimento.add(clientePessoaFisica);


                    if(status.compareToIgnoreCase("Pendente") == 0){
                        listaGeral.add(listaDeDadosDoAtendimento);

                    }














                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getContext(), "Deu ruiiiim", Toast.LENGTH_SHORT).show();
            }
        });
    }
}