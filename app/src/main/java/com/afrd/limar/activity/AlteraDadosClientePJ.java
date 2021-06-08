package com.afrd.limar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.afrd.limar.R;
import com.afrd.limar.model.ClientePessoaFisica;
import com.afrd.limar.model.ClientePessoaJuridica;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlteraDadosClientePJ extends AppCompatActivity {

    private TextInputEditText textNome, textCnpj, textInsEstadual, textTele, textEmail, textEndereco, textCidade;
    private TextInputLayout inputNome;
    private TextInputLayout inputCnpj;
    private TextInputLayout inputIE;
    private String nome, cnpj, inscricaoEstadual, celular, email,endereco,cidade, key;
    private DatabaseReference clientespjReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference().child("clientePJ") ;


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


        inputNome = findViewById(R.id.inputNomepjalterar);
        inputCnpj = findViewById(R.id.inputCnpjalterar);
        inputIE = findViewById(R.id.inputCnpjIEalterar);
        maskCnpj();
        maskInscEst();
        maskTelefone();
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
    public void voltarPJ(View view){

        finish();
    }
    public void salvarPJ(View view){
        clientespjReference.child(key).removeValue();
        ClientePessoaJuridica clientePessoaJuridica = new ClientePessoaJuridica(textNome.getText().toString(), textEndereco.getText().toString(),
                textTele.getText().toString(), textTele.getText().toString(), textCidade.getText().toString(), textEmail.getText().toString(),
                textCnpj.getText().toString(),textInsEstadual.getText().toString());
        clientespjReference.push().setValue(clientePessoaJuridica);
        //codigo para atualizar client
        finish();
    }
    public void deletarPJ(View view){
        clientespjReference.child(key).removeValue();

        finish();
    }
}