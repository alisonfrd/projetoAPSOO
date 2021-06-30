package com.afrd.limar.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.icu.number.NumberFormatter;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afrd.limar.Adapter.AdapterAtendimentoPendente;
import com.afrd.limar.Helper.RecyclerItemClickListener;
import com.afrd.limar.R;
import com.afrd.limar.activity.AlteraDadosClientePF;
import com.afrd.limar.activity.CadastroAtendimentoActivity;
import com.afrd.limar.model.Atendimento;
import com.afrd.limar.model.ClientePessoaFisica;
import com.afrd.limar.model.Equipamento;
import com.afrd.limar.model.MaterialEmAtendimento;
import com.afrd.limar.model.Servico;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;


public class AtendimentoRelatorioFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterAtendimentoPendente adapter;

    private DatabaseReference reference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference().child("atendimentos");
    private ValueEventListener valueEventListener;


    private  ArrayList<ArrayList> listaGeral = new ArrayList<>();

    ArrayList<MaterialEmAtendimento> materialEmAtendimentos = new ArrayList<>();
    ArrayList<Servico> listServicos = new ArrayList<>();
    ArrayList<Equipamento> listEquipamento = new ArrayList<>();

    ArrayList<Integer> quantidadeItens = new ArrayList<Integer>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_atendimento_relatorio, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbarClientes);
        toolbar.setTitle("Atendimentos Finalizados");

        adapter = new AdapterAtendimentoPendente(listaGeral);


        recyclerView = view.findViewById(R.id.recyclerAtendimentoFinalizado);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onItemClick(View view, int position) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Deseja Gerar PDF?")
                                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        criarPdf(position);

                                    }
                                })
                                .setNegativeButton("Não", null);
                        AlertDialog alert = builder.create();
                        alert.show();

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));

        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void criarPdf(int position){
        PdfDocument pdfDocument = new PdfDocument();

        PdfDocument.PageInfo details = new PdfDocument.PageInfo.Builder(
                500,600, 1
        ).create();
        PdfDocument.Page newPage = pdfDocument.startPage(details);

        Canvas canvas = newPage.getCanvas();
        Paint colorText = new Paint();
        colorText.setColor(Color.BLACK);



        //------------//
        ArrayList<MaterialEmAtendimento> materialEmAtendimentos = new ArrayList<>();
        ArrayList<MaterialEmAtendimento> materialEmAtendimentos2 = new ArrayList<>();
        ArrayList<Servico> servicos = new ArrayList<>();
        ArrayList<Servico> servicos2 = new ArrayList<>();
        ArrayList<Equipamento> equipamentos = new ArrayList<>();
        ArrayList<Equipamento> equipamentos2 = new ArrayList<>();
        Intent intent = new Intent(getActivity(), CadastroAtendimentoActivity.class);
        Atendimento atttt = (Atendimento) ((ArrayList)listaGeral.get( position )).get(0);
        materialEmAtendimentos = (ArrayList<MaterialEmAtendimento>) ((ArrayList)listaGeral.get( position )).get(1);
        servicos = (ArrayList<Servico>) ((ArrayList)listaGeral.get( position )).get(2);
        equipamentos = (ArrayList<Equipamento>) ((ArrayList)listaGeral.get( position )).get(3);
        ClientePessoaFisica clientePessoaFisicaEncaminha = (ClientePessoaFisica) ((ArrayList)listaGeral.get( position )).get(4);
        Double valorTotal = (Double) ((ArrayList)listaGeral.get( position )).get(5);

        String valorFormatado = new DecimalFormat("#,##0.00").format(valorTotal);

        canvas.drawText("Cliente: " + clientePessoaFisicaEncaminha.getNome() , 105, 100, colorText);
        canvas.drawText("Data Atendimento: " + atttt.getDataIncio(), 105, 120, colorText);
        canvas.drawText("Hora Ínicio: " + atttt.getHoraInicio(), 105, 140, colorText);
        canvas.drawText("Hora Fim: " + atttt.getHoraFim(), 105, 160, colorText);
        canvas.drawText("Descrição: " + atttt.getDescricaoAtendimento(), 105, 200, colorText);

        colorText.setColor(Color.RED);

        int limiteIten = quantidadeItens.get(position);
        int i= 0;
        String materiaisPdf = "";
        while (limiteIten > 0){
            materialEmAtendimentos2.add(materialEmAtendimentos.get(i));
            materiaisPdf ="" + materialEmAtendimentos2.get(i).getDescricao() + ", ";
            i++;
            limiteIten --;
        }


        //canvas.drawText("Materiais utilizados: " +  materiaisPdf , 105, 200, colorText);



        canvas.drawText("Valor Total: R$" + valorFormatado, 105, 230, colorText);
        pdfDocument.finishPage(newPage);


        //String targetPdf = "/sdcard/pdf/pdfModelo.pdf";
        String nomePath = "/".concat(clientePessoaFisicaEncaminha.getNome()).concat(".").concat("pdf");
        File filePath = new File(Environment.getExternalStorageDirectory(), "/pdf.pdf");
        try {
            pdfDocument.writeTo(new FileOutputStream(filePath));
            Toast.makeText(getActivity(), "Gravado..", Toast.LENGTH_SHORT).show();

        }catch (IOException ex){

            Log.i("TAG", "criarPdf: "+ ex.toString());
            Toast.makeText(getActivity(), "Falha ao gerar Pdf"+ ex.toString(), Toast.LENGTH_SHORT).show();
        }
        pdfDocument.close();

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
                            try {
                                valorVenda = (long) ((HashMap)listObjItens.get(i)).get("valorVenda");

                            }catch (Exception ex){
                                valorVenda = 0;
                            }
                            materialEmAtendimento = new MaterialEmAtendimento(descricao,key, valorVenda, (int)quantidade );

                            materialEmAtendimentos.add(materialEmAtendimento);


                        }
                        quantidadeItens.add(listObjItens.size());
                    }else{
                        quantidadeItens.add(0);
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

                    Double totalAtendimento = data.child("5").getValue(Double.class);
                    listaDeDadosDoAtendimento.add(totalAtendimento);

                    if(status.compareToIgnoreCase("Finalizado") == 0){
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