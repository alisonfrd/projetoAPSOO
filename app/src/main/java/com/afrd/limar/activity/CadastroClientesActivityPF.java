package com.afrd.limar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.afrd.limar.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroClientesActivityPF extends AppCompatActivity {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private TextInputEditText textNome, textCpf, textDtNas, textTele, textEmail, textEndereco, textCidade;
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


    }

    public void addCLiente (View view){
        /*ClientePessoaFisica clientePessoaFisica = new ClientePessoaFisica(textNome.getText().toString(), textEndereco.getText().toString(),
                textTele.getText().toString(),textTele.getText().toString(), textCidade.getText().toString(), textEmail.getText().toString(),
                textCpf.getText().toString(), textDtNas.getText().toString());

        DatabaseReference usuarios = databaseReference.child("usuario");
        usuarios.child("001").setValue(clientePessoaFisica);
        */

        Toast.makeText(getApplicationContext(), "Dados salvos", Toast.LENGTH_SHORT).show();
        finish();
    }
}