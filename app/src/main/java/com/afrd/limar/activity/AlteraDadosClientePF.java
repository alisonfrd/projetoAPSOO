package com.afrd.limar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.afrd.limar.R;
import com.afrd.limar.model.ClientePessoaFisica;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class AlteraDadosClientePF extends AppCompatActivity {
    private TextInputEditText textNome, textCpf, textDtNas, textTele, textEmail, textEndereco, textCidade;
    private TextInputLayout inputNome;
    private String nome, cpf, nasc, celular, email,endereco,cidade, key;


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
        inputNome = findViewById(R.id.inputNomePF);
        maskCpf();
        maskData();
        maskTelefone();



    }
    public void adicionar(View view){

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
    public void voltar(View view){

        finish();
    }
    public void salvar(View view){
        clientespfReference.child(key).removeValue();
        ClientePessoaFisica clientePessoaFisica = new ClientePessoaFisica(textNome.getText().toString(),textEndereco.getText().toString(),textTele.getText().toString(),
                textTele.getText().toString(),textCidade.getText().toString(),textEmail.getText().toString(),textCpf.getText().toString(),textDtNas.getText().toString());
        clientespfReference.push().setValue(clientePessoaFisica);
        //codigo para atualizar client
        finish();
    }
    public void deletarPF(View view){
        clientespfReference.child(key).removeValue();

        finish();
    }


}