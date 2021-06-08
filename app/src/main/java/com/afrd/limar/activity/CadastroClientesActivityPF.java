package com.afrd.limar.activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class CadastroClientesActivityPF extends AppCompatActivity {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference();
    private TextInputEditText textNome, textCpf, textDtNas, textTele, textEmail, textEndereco, textCidade;


    private  TextInputLayout inputNome;
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
        maskCpf();
        maskData();
        maskTelefone();

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


    public void addCLientePF (View view){
        if(validaNome() == false){
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
}