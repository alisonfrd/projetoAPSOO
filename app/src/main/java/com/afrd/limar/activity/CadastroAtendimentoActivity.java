package com.afrd.limar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afrd.limar.R;
import com.afrd.limar.model.Atendimento;
import com.afrd.limar.model.ClientePessoaFisica;
import com.afrd.limar.model.ClientePessoaJuridica;
import com.afrd.limar.model.Equipamento;
import com.afrd.limar.model.MaterialEmAtendimento;
import com.afrd.limar.model.Servico;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class CadastroAtendimentoActivity extends AppCompatActivity  {
    //ArrayList para salvar os dados do atendimento e depois salvar no BD
    private ArrayList<Object> listaDeDadosDoAtendimento = new ArrayList<>();
    private ArrayList<MaterialEmAtendimento> listaMateriais = new ArrayList<>();
    private ArrayList<Servico> listaServicos = new ArrayList<>();
    private ArrayList<Equipamento> listaEquipamento = new ArrayList<>();

    //dados do atendimento
    private TextInputEditText textDataAtendimento, textHoraInicio, textHoraFim, textDescricaoAtendimento;
    private RadioGroup radioGroup;
    private String statusAtendimento = "Pendente";



    private TextView textViewNomeClienteAtendimento, textViewEndereco, textViewCidade;
    private boolean verificacao = false;
    private String nome, celular, endereco, cidade,cnpj,inscricaoEstadual,cpf,nasc, email;

    //configuraçõa firebase
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_atendimento);

        textViewNomeClienteAtendimento = findViewById(R.id.textViewNomeClienteAtendimento);
        textViewEndereco = findViewById(R.id.textEnderecoExibicaoAtendimento);
        textViewCidade = findViewById(R.id.textCidadeExibicaoAtendimento);

        textDataAtendimento = findViewById(R.id.textDataAtendimento);
        textHoraInicio = findViewById(R.id.textHoraInicio);
        textDescricaoAtendimento = findViewById(R.id.textDescricaoAtendimento);
        textHoraFim = findViewById(R.id.textHoraFim);
        radioGroup = findViewById(R.id.radioGroup);


    }
    public Atendimento criaDadosAtendimento(){
        Toast.makeText(getApplicationContext(), statusAtendimento, Toast.LENGTH_SHORT).show();
        Atendimento atendimento = new Atendimento(1,textDescricaoAtendimento.getText().toString().trim(),textDataAtendimento.getText().toString().trim(),textHoraInicio.getText().toString().trim(),
                textHoraFim.getText().toString().trim(), statusAtendimento);
        return atendimento;
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonPendente:
                if (checked)
                    statusAtendimento = "Pendente";
                    // Pirates are the best
                    break;
            case R.id.radioButtonFinalizado:
                if (checked)
                    statusAtendimento = "Finalizado";

                // Ninjas rule
                    break;
        }
    }


    public void addEquipamento(View view){
        Intent intent = new Intent(getApplicationContext(), AdicionarEquipamentoEmAtendimentoActivity.class);
        intent.putExtra("listaAtendimento", (Serializable) listaEquipamento);
        startActivityForResult(intent, 1);
    }

    public void addMaterial(View view){
        Intent intent = new Intent(getApplicationContext(), AdicionarMaterialActivity.class);
        intent.putExtra("listaMateriais", (Serializable) listaMateriais);
        startActivityForResult(intent, 1);

    }
    public void addServico(View view){
        Intent intent = new Intent(getApplicationContext(), AdicionarServicoActivity.class);
        intent.putExtra("listaServicos", (Serializable) listaServicos);
        startActivityForResult(intent, 1);
    }
    public void salvarAtendimento(View view){
        listaDeDadosDoAtendimento.add(criaDadosAtendimento());

        listaDeDadosDoAtendimento.add(listaMateriais);
        listaDeDadosDoAtendimento.add(listaServicos);
        listaDeDadosDoAtendimento.add(listaEquipamento);
        if(verificacao){
            ClientePessoaFisica clientePessoaFisica = new ClientePessoaFisica(this.nome, this.endereco, this.celular,this.celular, this.cidade, this.email,
                    this.cpf, this.nasc);
            listaDeDadosDoAtendimento.add(clientePessoaFisica);
            Toast.makeText(this, clientePessoaFisica.getNome(), Toast.LENGTH_SHORT).show();
        }else{
            ClientePessoaJuridica clientePessoaJuridica = new ClientePessoaJuridica(this.nome,this.endereco, this.celular, this.celular, this.cidade
            ,this.email, this.cnpj, this.inscricaoEstadual);
            listaDeDadosDoAtendimento.add(clientePessoaJuridica);
            Toast.makeText(this, clientePessoaJuridica.getNome(), Toast.LENGTH_SHORT).show();

        }

        DatabaseReference atendimentos  = databaseReference.child("atendimentos");
        atendimentos.push().setValue(listaDeDadosDoAtendimento);

        finish();
    }

    public void addClienteParaAtendimento(View view){
        Intent intent = new Intent(getApplicationContext(), ClienteActivity.class);
        intent.putExtra("chaveCadastro", "1");
        startActivityForResult(intent, 1);
    }



    //Recuperação dos dados para salvar no array para push em BD
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if(resultCode == RESULT_OK){

                //verificar qual objeto esta sendo retornado para salvar na lista
                if(data.getStringExtra("valida").compareTo("clienteCNPJ")==0){

                    this.nome = data.getStringExtra("nome");
                    this.cnpj = data.getStringExtra("cnpj");
                    this.inscricaoEstadual = data.getStringExtra("inscricaoEstadual");
                    this.celular = data.getStringExtra("celular");
                    this.endereco = data.getStringExtra("endereco");
                    this.cidade = data.getStringExtra("cidade");
                    this.email = data.getStringExtra("email");

                    textViewNomeClienteAtendimento.setText(this.nome);

                    textViewEndereco.setText("End: " + this.endereco);
                    textViewCidade.setText("Cidade: " + this.cidade);


                }else if(data.getStringExtra("valida").compareTo("clienteCPF")==0){

                    verificacao = true; //se o usuario selecionar um cliente pf verficação é true se não é false
                    this.nome = data.getStringExtra("nome");
                    this.cpf = data.getStringExtra("cpf");
                    this.nasc = data.getStringExtra("nasc");
                    this.celular = data.getStringExtra("celular");
                    this.email = data.getStringExtra("email");
                    this.endereco= data.getStringExtra("endereco");
                    this.cidade = data.getStringExtra("cidade");
                    textViewNomeClienteAtendimento.setText(this.nome);
                    textViewEndereco.setText("End: " + this.endereco);
                    textViewCidade.setText("Cidade: " + this.cidade);
                }else if(data.getStringExtra("valida").compareTo("materiais")==0){
                    listaMateriais = (ArrayList<MaterialEmAtendimento>) data.getSerializableExtra("listaRetorno");
                }else if(data.getStringExtra("valida").compareTo("servicos")==0){
                    listaServicos = (ArrayList<Servico>) data.getSerializableExtra("listaRetorno");
                }else if(data.getStringExtra("valida").compareTo("equipamentos")==0){
                    listaEquipamento = (ArrayList<Equipamento>) data.getSerializableExtra("listaRetorno");
                }
            }
        }
    }
}