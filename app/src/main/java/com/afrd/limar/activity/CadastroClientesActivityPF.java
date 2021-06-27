package com.afrd.limar.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.afrd.limar.Helper.ExpressoesRegulares;
import com.afrd.limar.R;
import com.afrd.limar.model.ClientePessoaFisica;
import com.afrd.limar.model.ClientePessoaJuridica;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CadastroClientesActivityPF extends AppCompatActivity {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference();
    private TextInputEditText textNome, textCpf, textDtNas, textTele, textEmail, textEndereco, textCidade;


    private  TextInputLayout inputNome, inputTelefonePF, inputCpfPF, inputDNPF;


    private ArrayList<ClientePessoaFisica> listaCLientePf = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_clientes_pf);

        textNome = findViewById(R.id.outlinedTextFieldNomePF);
        textCpf = findViewById(R.id.outlinedTextFieldCpfPF);
        textDtNas = findViewById(R.id.outlinedTextFieldDtNasPF);
        textTele = findViewById(R.id.outlinedTextFieldTelefonePF);
        textEmail = findViewById(R.id.outlinedTextFieldEmailPF);
        textEndereco = findViewById(R.id.outlinedTextFieldEnderecoPF);
        textCidade = findViewById(R.id.outlinedTextFieldCidadePF);

        inputNome = findViewById(R.id.inputNomePF);
        inputTelefonePF= findViewById(R.id.inputTelefonePF);
        inputCpfPF= findViewById(R.id.inputCpfPF);
        inputDNPF= findViewById(R.id.inputDNPF);

        maskCpf();
        maskData();
        maskTelefone();

        //recebe lista para verificação de existencia de cliente como mesmo cpf
        Intent intent = getIntent();
        listaCLientePf = (ArrayList<ClientePessoaFisica>) intent.getSerializableExtra("listaConferePf");



    }

    //aplicando mascaras nos campos
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
        for(ClientePessoaFisica lista : listaCLientePf){
            if(lista.getCpf().compareTo(textCpf.getText().toString()) == 0){
                validaCadastrado = true;
            }
        }

        String inputCpf = this.inputCpfPF.getEditText().getText().toString().trim();
        if (inputCpf.isEmpty()){
            this.inputCpfPF.setError("Digite o Cnpj");
            return false;
        }else if(!inputCpf.isEmpty() && !ExpressoesRegulares.confereCpf(textCpf.getText().toString())){
            this.inputCpfPF.setError("CPF Inválido");
            return false;

        }else if(validaCadastrado){
            Toast.makeText(this, "Cliente Já Cadastrado", Toast.LENGTH_LONG).show();
            return false;
        }else{
            this.inputCpfPF.setError(null);
            return true;
        }
    }

    public boolean validaTelefone(){
        String telefone = this.inputTelefonePF.getEditText().getText().toString().trim();
        if (!telefone.isEmpty()){
            if(ExpressoesRegulares.confereTelefone(textTele.getText().toString() )){
                return true;
            }else{
                this.inputTelefonePF.setError("Telefone Invalido");
                return false;
            }
        }else{
            return true;
        }
    }


    public boolean validaDataNascimento(){
        String data = this.inputDNPF.getEditText().getText().toString().trim();
        if(!data.isEmpty()){
            if(ExpressoesRegulares.confereDataNascimento(textDtNas.getText().toString())){
                return true;
            }else{
                this.inputDNPF.setError("Data Invalida");
                return false;
            }
        }else{
            return true;
        }


    }

    public void addCLientePF (View view){
        if(!validaNome() || !validaTelefone() || !validaCpf() || !validaDataNascimento()){
            this.inputNome.getHint();

        }else {
            ClientePessoaFisica clientePessoaFisica = new ClientePessoaFisica(textNome.getText().toString(), textEndereco.getText().toString(),
                    textTele.getText().toString(),textTele.getText().toString(), textCidade.getText().toString(), textEmail.getText().toString(),
                    textCpf.getText().toString(), textDtNas.getText().toString());

            DatabaseReference usuarios = databaseReference.child("clientePF");
            usuarios.push().setValue(clientePessoaFisica);

            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if(!textNome.getText().toString().isEmpty() || !textEndereco.getText().toString().isEmpty() || !textTele.getText().toString().isEmpty()
                || !textCidade.getText().toString().isEmpty() || !textEmail.getText().toString().isEmpty() || !textCpf.getText().toString().isEmpty()
                || !textDtNas.getText().toString().isEmpty()
        ){
            AlertDialog.Builder builder = new AlertDialog.Builder(CadastroClientesActivityPF.this);
            builder.setTitle("Deseja Voltar?")
                    .setMessage("Os dados não serão salvos")
                    .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            CadastroClientesActivityPF.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("Cancelar", null);
            AlertDialog alert = builder.create();
            alert.show();
        }else{
            CadastroClientesActivityPF.super.onBackPressed();
        }
    }
}