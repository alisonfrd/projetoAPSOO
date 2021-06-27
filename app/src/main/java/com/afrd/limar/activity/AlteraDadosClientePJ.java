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
import com.afrd.limar.model.ClientePessoaJuridica;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AlteraDadosClientePJ extends AppCompatActivity {

    private TextInputEditText textNome, textCnpj, textInsEstadual, textTele, textEmail, textEndereco, textCidade;
    private TextInputLayout inputNome;
    private TextInputLayout inputCnpj;
    private TextInputLayout inputIE;
    private TextInputLayout inputTele;

    private String nome, cnpj, inscricaoEstadual, celular, email,endereco,cidade, key;
    private DatabaseReference clientespjReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference().child("clientePJ") ;

    private ArrayList<ClientePessoaJuridica> listaCLientePJ = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_dados_cliente_pj);

        //Find components in layout
        textNome=  findViewById(R.id.outlinedTextFieldNomePjalterar);
        textCnpj = findViewById(R.id.outlinedTextFieldCnpjalterar);
        textInsEstadual = findViewById(R.id.outlinedTextFieldInscEstadualalterar);
        textTele = findViewById(R.id.outlinedTextFieldTelefonePJalterar);
        textEmail = findViewById(R.id.outlinedTextFieldEmailPJalterar);
        textEndereco = findViewById(R.id.outlinedTextFieldEnderecoPJalterar);
        textCidade = findViewById(R.id.outlinedTextFieldCidadePJalterar);

        atualizarView();
        deletarPJ();


        inputNome = findViewById(R.id.inputNomepjalterar);
        inputCnpj = findViewById(R.id.inputCnpjalterar);
        inputIE = findViewById(R.id.inputCnpjIEalterar);
        inputTele = findViewById(R.id.inputCnpjTeleAlterar);

        maskCnpj();
        maskInscEst();
        maskTelefone();


        //carregar lista para verificar se um cliente já esta inserido no sistema
        Intent intent = getIntent();
        listaCLientePJ = (ArrayList<ClientePessoaJuridica>) intent.getSerializableExtra("listaClienteVerifica");


    }

    //aplicando mascaras nos campos
    public void maskCnpj(){
        SimpleMaskFormatter smf =  new SimpleMaskFormatter("NN.NNN.NNN/NNNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(textCnpj, smf);
        textCnpj.addTextChangedListener(mtw);
    }
    public void maskInscEst(){

        SimpleMaskFormatter smf =  new SimpleMaskFormatter("NN/NNN.NNN-N");
        MaskTextWatcher mtw = new MaskTextWatcher(textInsEstadual, smf);
        textInsEstadual.addTextChangedListener(mtw);
    }
    public void maskTelefone(){
        SimpleMaskFormatter smf =  new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(textTele, smf);
        textTele.addTextChangedListener(mtw);
    }

    public void atualizarView(){
        //recebe dados para mostrar oa usuario
        Bundle bundle = getIntent().getExtras();
        nome = bundle.getString("nome");
        cnpj = bundle.getString("cnpj");
        inscricaoEstadual = bundle.getString("inscricaoEstadual");
        celular = bundle.getString("celular");
        email = bundle.getString("email");
        endereco = bundle.getString("endereco");
        cidade = bundle.getString("cidade");
        key = bundle.getString("key");

        textNome.setText(nome);
        textCnpj.setText(cnpj);
        textInsEstadual.setText(inscricaoEstadual);
        textTele.setText(celular);
        textEmail.setText(email);
        textEndereco.setText(endereco);
        textCidade.setText(cidade);
    }

    public boolean validaCnpj(){
        boolean validaCadastrado = false;
        if(cnpj.compareTo(textCnpj.getText().toString()) != 0){
            for(ClientePessoaJuridica lista : listaCLientePJ){
                if(lista.getCnpj().compareTo(textCnpj.getText().toString()) == 0){
                    validaCadastrado = true;
                }
            }
        }


        String inputNome = this.inputCnpj.getEditText().getText().toString().trim();
        if (inputNome.isEmpty()){
            this.inputCnpj.setError("Digite o Cnpj");
            return false;
        }else if(!inputNome.isEmpty() && !ExpressoesRegulares.confereCnpj(textCnpj.getText().toString())){
            this.inputCnpj.setError("Cnpj Inválido");
            return false;

        }else if(validaCadastrado){
            Toast.makeText(this, "Cliente Cadastrado", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            this.inputCnpj.setError(null);
            return true;
        }
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

    public boolean validaIE(){
        String inputNome = this.inputIE.getEditText().getText().toString().trim();
        if (inputNome.isEmpty()){
            this.inputIE.setError("Digite a Inscrição Estadual");
            return false;
        }else if(!inputNome.isEmpty() && !ExpressoesRegulares.confereIE(textInsEstadual.getText().toString())){
            this.inputIE.setError("IE Inválido");
            return false;

        }else{

            this.inputIE.setError(null);
            return true;
        }
    }
    public boolean validaTelefone(){
        String telefone = this.inputTele.getEditText().getText().toString().trim();
        if (!telefone.isEmpty()){
            if(ExpressoesRegulares.confereTelefone(textTele.getText().toString())){
                return true;
            }else{
                this.inputTele.setError("Telefone Invalido");
                return false;
            }
        }else{
            return true;
        }
    }

    public void salvarPJ(View view){
        if(!validaNome() ){
            this.inputNome.getHint();
        }else if(!validaCnpj()){
            this.inputCnpj.getHint();
        }else if(!validaIE()){
            this.inputIE.getHint();
        }else if(!validaTelefone()){
            this.inputTele.getHint();
        }else{
            //codigo para atualizar client
            clientespjReference.child(key).removeValue();
            ClientePessoaJuridica clientePessoaJuridica = new ClientePessoaJuridica(textNome.getText().toString(), textEndereco.getText().toString(),
                    textTele.getText().toString(), textTele.getText().toString(), textCidade.getText().toString(), textEmail.getText().toString(),
                    textCnpj.getText().toString(),textInsEstadual.getText().toString());
            clientespjReference.push().setValue(clientePessoaJuridica);

            finish();
        }

    }
    public void deletarPJ(){
        Button buttonDell = findViewById(R.id.buttonDellPj);
        buttonDell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlteraDadosClientePJ.this);
                builder.setMessage("Deseja Excluir?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                clientespjReference.child(key).removeValue();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(AlteraDadosClientePJ.this);
        builder.setTitle("Deseja Voltar?")
                .setMessage("Os dados não serão salvos")
                .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlteraDadosClientePJ.super.onBackPressed();
                    }
                })
                .setNegativeButton("Cancelar", null);
        AlertDialog alert = builder.create();
        alert.show();
    }
}