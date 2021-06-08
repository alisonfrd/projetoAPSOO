package com.afrd.limar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.afrd.limar.R;
import com.afrd.limar.model.ClientePessoaFisica;
import com.afrd.limar.model.ClientePessoaJuridica;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroClienteActivityPJ extends AppCompatActivity {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference();
    private TextInputEditText textNome, textCnpj, textInsEstadual, textTele, textEmail, textEndereco, textCidade;
    private TextInputLayout inputNome;
    private TextInputLayout inputCnpj;
    private TextInputLayout inputIE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente_p_j);

        //Find components in layout
        textNome=  findViewById(R.id.outlinedTextFieldNomePj);
        textCnpj = findViewById(R.id.outlinedTextFieldCnpj);
        textInsEstadual = findViewById(R.id.outlinedTextFieldInscEstadual);
        textTele = findViewById(R.id.outlinedTextFieldTelefonePJ);
        textEmail = findViewById(R.id.outlinedTextFieldEmailPJ);
        textEndereco = findViewById(R.id.outlinedTextFieldEnderecoPJ);
        textCidade = findViewById(R.id.outlinedTextFieldCidadePJ);




        inputNome = findViewById(R.id.inputNomepj);
        inputCnpj = findViewById(R.id.inputCnpj);
        inputIE = findViewById(R.id.inputCnpjIE);
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
    public boolean validaCnpj(){
        String inputNome = this.inputCnpj.getEditText().getText().toString().trim();
        if (inputNome.isEmpty()){
            this.inputCnpj.setError("Digite o Cnpj");
            return false;
        }else{
            this.inputCnpj.setError(null);
            return true;
        }
    }

    public boolean validaIE(){
        String inputNome = this.inputIE.getEditText().getText().toString().trim();
        if (inputNome.isEmpty()){
            this.inputIE.setError("Digite a Inscrição Estadual");
            return false;
        }else{
            this.inputIE.setError(null);
            return true;
        }
    }




    public void addCLientePJ (View view){

        if(validaNome() == false | validaCnpj()==false | validaIE()==false){
            this.inputNome.getHint();

        }else {
            ClientePessoaJuridica clientePessoaJuridica = new ClientePessoaJuridica(textNome.getText().toString(),textEndereco.getText().toString(), textTele.getText().toString(),
                    textTele.getText().toString(), textCidade.getText().toString(), textEmail.getText().toString(), textCnpj.getText().toString(), textInsEstadual.getText().toString() );

            DatabaseReference usuarios = databaseReference.child("clientePJ");
            usuarios.push().setValue(clientePessoaJuridica);
            finish();
        }


    }



}