package com.afrd.limar.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afrd.limar.R;
import com.afrd.limar.model.ClientePessoaFisica;
import com.afrd.limar.model.Materiais;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlteraDadosMateriais extends AppCompatActivity {
    private TextInputEditText textCodigo, textDescricao, TextUnidade, TextValorDeCusto, TextValorDeVenda, TextQuantidade, TextFornecedor;
    private String id, quantidade, fornecedor, descricao, unidade, valorCusto, valorVenda, key;
    private DatabaseReference materiaisReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference().child("materiais") ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_dados_materiais);



        textCodigo = findViewById(R.id.outlinedTextFieldCodigoAltera);
        textDescricao = findViewById(R.id.outlinedTextFieldDescricaoAltera);
        TextUnidade = findViewById(R.id.outlinedTextFieldUnidadeAltera);
        TextValorDeCusto = findViewById(R.id.outlinedTextFieldValorCustoAltera);
        TextValorDeVenda = findViewById(R.id.outlinedTextFieldValorVendaAltera);
        TextQuantidade = findViewById(R.id.outlinedTextFieldQuantidadeAltera);
        TextFornecedor = findViewById(R.id.outlinedTextFieldFornecedorAltera);

        atualizarView();
        deletarMaterial();
    }

    public void atualizarView(){
        Bundle bundle = getIntent().getExtras();

        id = bundle.getString("id");
        quantidade = bundle.getString("quantidade");
        fornecedor = bundle.getString("fornecedor");
        descricao = bundle.getString("descricao");
        unidade = bundle.getString("unidade");
        valorCusto = bundle.getString("valorCusto");
        valorVenda = bundle.getString("valorVenda");
        key = bundle.getString("key");

        textCodigo.setText(id);
        TextQuantidade.setText(String.valueOf(quantidade));
        TextFornecedor.setText(fornecedor);
        textDescricao.setText(descricao);
        TextUnidade.setText(unidade);
        TextValorDeCusto.setText(valorCusto);
        TextValorDeVenda.setText(valorVenda);
    }

    public void salvar(View view){
        materiaisReference.child(key).removeValue();

        Materiais materiais = new Materiais(Integer.parseInt(textCodigo.getText().toString()), Integer.parseInt(TextQuantidade.getText().toString()), TextFornecedor.getText().toString(),
                textDescricao.getText().toString(), TextUnidade.getText().toString(),Double.parseDouble(TextValorDeCusto.getText().toString()),Double.parseDouble(TextValorDeVenda.getText().toString()));

        //codigo para atualizar client
        materiaisReference.push().setValue(materiais);
        finish();
    }
    public void deletarMaterial(){
        Button buttonDell = findViewById(R.id.buttonDellMat);
        buttonDell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlteraDadosMateriais.this);
                builder.setMessage("Deseja Excluir?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                materiaisReference.child(key).removeValue();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(AlteraDadosMateriais.this);
        builder.setTitle("Deseja Voltar?")
                .setMessage("Os dados não serão salvos")
                .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlteraDadosMateriais.super.onBackPressed();
                    }
                })
                .setNegativeButton("Cancelar", null);
        AlertDialog alert = builder.create();
        alert.show();
    }
}