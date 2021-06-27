package com.afrd.limar.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afrd.limar.Helper.ExpressoesRegulares;
import com.afrd.limar.R;
import com.afrd.limar.model.ClientePessoaFisica;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AlteraDadosClientePF extends AppCompatActivity {
    private TextInputEditText textNome, textCpf, textDtNas, textTele, textEmail, textEndereco, textCidade;
    private TextInputLayout inputNome, inputTelePFalterar, inputCpfPFalterar, inputDNPFalterar;
    private String nome, cpf, nasc, celular, email,endereco,cidade, key;

    private ArrayList<ClientePessoaFisica> listaCLientePf = new ArrayList<>();

    private DatabaseReference clientespfReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference().child("clientePF") ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_dados_cliente_pf);

        textNome = findViewById(R.id.outlinedTextFieldNomePFAlterar);
        textCpf = findViewById(R.id.outlinedTextFieldCpfPFAlterar);
        textDtNas = findViewById(R.id.outlinedTextFieldDtNasPFAlterar);
        textTele = findViewById(R.id.outlinedTextFieldTelefonePFAlterar);
        textEmail = findViewById(R.id.outlinedTextFieldEmailPFAlterar);
        textEndereco = findViewById(R.id.outlinedTextFieldEnderecoPFAlterar);
        textCidade = findViewById(R.id.outlinedTextFieldCidadePFAlterar);

        //Recuperar dados do clientePF
        atualizarView();

        //Aplicando mask
        inputNome = findViewById(R.id.inputNomePFalterar);
        inputTelePFalterar = findViewById(R.id.inputTelePFalterar);
        inputCpfPFalterar = findViewById(R.id.inputCpfPFalterar);
        inputDNPFalterar = findViewById(R.id.inputDNPFalterar);
        maskCpf();
        maskData();
        maskTelefone();


        Intent intent = getIntent();
        listaCLientePf = (ArrayList<ClientePessoaFisica>) intent.getSerializableExtra("listaAlteraPF");



        deletarPF();

    }


    public void maskCpf(){
        SimpleMaskFormatter smf =  new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(textCpf, smf);
        textCpf.addTextChangedListener(mtw);
    }
    public void maskData(){
        SimpleMaskFormatter smf =  new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(textDtNas, smf);
        textDtNas.addTextChangedListener(mtw);
    }
    public void maskTelefone(){
        SimpleMaskFormatter smf =  new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(textTele, smf);
        textTele.addTextChangedListener(mtw);
    }

    //func para exibir os dados no layout de att
    public void atualizarView(){
        Bundle bundle = getIntent().getExtras();
        nome = bundle.getString("nome");
        cpf = bundle.getString("cpf");
        nasc = bundle.getString("nasc");
        celular = bundle.getString("celular");
        email = bundle.getString("email");
        endereco = bundle.getString("endereco");
        cidade = bundle.getString("cidade");
        key = bundle.getString("key");

        textNome.setText(nome);
        textCpf.setText(cpf);
        textDtNas.setText(nasc);
        textTele.setText(celular);
        textEmail.setText(email);
        textEndereco.setText(endereco);
        textCidade.setText(cidade);
    }
    public boolean validaNome(){
        String inputNome = this.inputNome.getEditText().getText().toString().trim();
        if (inputNome.isEmpty()){
            this.inputNome.setError("Digite o nome");
            return false;
        }else{
            this.inputNome.setError(null);
            return true;
        }
    }

    public boolean validaCpf(){
        boolean validaCadastrado = false;

        if(cpf.compareTo(textCpf.getText().toString()) != 0){
            for(ClientePessoaFisica lista : listaCLientePf){
                if(lista.getCpf().compareTo(textCpf.getText().toString()) == 0 ){

                        validaCadastrado = true;


                }

            }
        }


        String inputCpf = this.inputCpfPFalterar.getEditText().getText().toString().trim();
        if (inputCpf.isEmpty()){
            this.inputCpfPFalterar.setError("Digite o Cnpj");
            return false;
        }else if(!inputCpf.isEmpty() && !ExpressoesRegulares.confereCpf(textCpf.getText().toString())){
            this.inputCpfPFalterar.setError("CPF Inválido");
            return false;

        }else if(validaCadastrado){
            Toast.makeText(this, "Cliente Já Cadastrado", Toast.LENGTH_LONG).show();
            return false;
        }else{
            this.inputCpfPFalterar.setError(null);
            return true;
        }
    }

    public boolean validaTelefone(){
        String telefone = this.inputTelePFalterar.getEditText().getText().toString().trim();
        if (!telefone.isEmpty()){
            if(ExpressoesRegulares.confereTelefone(textTele.getText().toString() )){
                return true;
            }else{
                this.inputTelePFalterar.setError("Telefone Invalido");
                return false;
            }
        }else{
            return true;
        }
    }
    public boolean validaDataNascimento(){
        String data = this.inputDNPFalterar.getEditText().getText().toString().trim();
        if(!data.isEmpty()){
            if(ExpressoesRegulares.confereDataNascimento(textDtNas.getText().toString())){
                return true;
            }else{
                this.inputDNPFalterar.setError("Data Invalida");
                return false;
            }
        }else{
            return true;
        }


    }

    public void salvar(View view){
        if(!validaNome() || !validaCpf() || !validaTelefone() || !validaDataNascimento()){
            this.inputNome.getHint();
        }else{
            clientespfReference.child(key).removeValue();
            ClientePessoaFisica clientePessoaFisica = new ClientePessoaFisica(textNome.getText().toString(),textEndereco.getText().toString(),textTele.getText().toString(),
                    textTele.getText().toString(),textCidade.getText().toString(),textEmail.getText().toString(),textCpf.getText().toString(),textDtNas.getText().toString());
            clientespfReference.push().setValue(clientePessoaFisica);
            //codigo para atualizar client
            finish();
        }


    };
    public void deletarPF(){
        Button buttonDell = findViewById(R.id.buttonDellPF);
        buttonDell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlteraDadosClientePF.this);
                builder.setMessage("Deseja Realmente Excluir?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                clientespfReference.child(key).removeValue();
                                finish();
                            }
                        })
                        .setNegativeButton("Cancelar", null);
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(AlteraDadosClientePF.this);
        builder.setTitle("Deseja Voltar?")
                .setMessage("Os dados não serão salvos")
                .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlteraDadosClientePF.super.onBackPressed();
                    }
                })
                .setNegativeButton("Cancelar", null);
        AlertDialog alert = builder.create();
        alert.show();
    }
}