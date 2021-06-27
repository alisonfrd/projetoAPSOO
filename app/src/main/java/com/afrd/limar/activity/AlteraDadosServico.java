package com.afrd.limar.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afrd.limar.R;
import com.afrd.limar.activity.ServicosActivity;
import com.afrd.limar.model.Materiais;
import com.afrd.limar.model.Servico;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlteraDadosServico extends AppCompatActivity {
    private DatabaseReference servicosReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference().child("servicos");
    private TextInputEditText textCodigo, textDescricao, textValor;
    private String id, descricao,  key;
    private Double valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_dados_servico);

        textCodigo = findViewById(R.id.outlinedTextFieldCodigoServicoAltera);
        textDescricao = findViewById(R.id.outlinedTextFieldDescricaoServicoAltera);
        textValor = findViewById(R.id.outlinedTextFieldValorServicoAltera);

        atualizarView();
        deletarServico();
    }

    public void atualizarView(){
        Bundle bundle = getIntent().getExtras();

        id = bundle.getString("id");
        descricao = bundle.getString("descricao");
        valor = bundle.getDouble("valor");
        key = bundle.getString("key");

        textCodigo.setText(id);
        textValor.setText(Double.toString(valor));
        textDescricao.setText(descricao);

    }


    public void salvarServico(View view){
        servicosReference.child(key).removeValue();

        Servico servico = new Servico(textCodigo.getText().toString(), textDescricao.getText().toString(), Double.parseDouble(textValor.getText().toString()));

        //codigo para atualizar client
        servicosReference.push().setValue(servico);
        finish();
    }
    public void deletarServico(){
        Button buttonDell = findViewById(R.id.buttonDellServicos);
        buttonDell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlteraDadosServico.this);
                builder.setMessage("Deseja Excluir?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                servicosReference.child(key).removeValue();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(AlteraDadosServico.this);
        builder.setTitle("Deseja Voltar?")
                .setMessage("Os dados não serão salvos")
                .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlteraDadosServico.super.onBackPressed();
                    }
                })
                .setNegativeButton("Cancelar", null);
        AlertDialog alert = builder.create();
        alert.show();
    }
}