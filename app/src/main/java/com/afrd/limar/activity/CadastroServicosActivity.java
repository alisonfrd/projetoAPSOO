package com.afrd.limar.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.afrd.limar.R;
import com.afrd.limar.model.Materiais;
import com.afrd.limar.model.Servico;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroServicosActivity extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference();
    private TextInputEditText textCodigo, textDescricao, textValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_servicos);

        textCodigo = findViewById(R.id.outlinedTextFieldCodigoServico);
        textDescricao = findViewById(R.id.outlinedTextFieldDescricaoServico);
        textValor = findViewById(R.id.outlinedTextFieldValorServico);



    }


    public void salvarDadosServicos(View view){
        Servico servico = new Servico(textCodigo.getText().toString(), textDescricao.getText().toString(), Double.parseDouble(textValor.getText().toString()));



        DatabaseReference materialReference = databaseReference.child("servicos");
        materialReference.push().setValue(servico);

        finish();

    }

    @Override
    public void onBackPressed() {
        if(!textCodigo.getText().toString().isEmpty() || !textDescricao.getText().toString().isEmpty() || !textValor.getText().toString().isEmpty()
        ){
            AlertDialog.Builder builder = new AlertDialog.Builder(CadastroServicosActivity.this);
            builder.setTitle("Deseja Voltar?")
                    .setMessage("Os dados não serão salvos")
                    .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            CadastroServicosActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("Cancelar", null);
            AlertDialog alert = builder.create();
            alert.show();
        }else{
            CadastroServicosActivity.super.onBackPressed();
        }
    }
}