package com.afrd.limar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.afrd.limar.R;
import com.afrd.limar.model.Materiais;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroMateriais extends AppCompatActivity {

    private TextInputEditText codigo, descricao, unidade, valorDeCusto, valorDeVenda, quantidade, fornecedor;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://projetolimar-53f6e-default-rtdb.firebaseio.com/").getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_materiais);


        codigo = findViewById(R.id.outlinedTextFieldCodigo);
        descricao = findViewById(R.id.outlinedTextFieldDescricao);
        unidade = findViewById(R.id.outlinedTextFieldUnidade);
        valorDeCusto = findViewById(R.id.outlinedTextFieldValorCusto);
        valorDeVenda = findViewById(R.id.outlinedTextFieldValorVenda);
        quantidade = findViewById(R.id.outlinedTextFieldQuantidade);
        fornecedor = findViewById(R.id.outlinedTextFieldFornecedor);
    }


    public void salvarDados(View view){
        Materiais materiais = new Materiais(Integer.parseInt(codigo.getText().toString()), Integer.parseInt(quantidade.getText().toString()), fornecedor.getText().toString(),
                descricao.getText().toString(), unidade.getText().toString(),Double.parseDouble(valorDeCusto.getText().toString()),Double.parseDouble(valorDeVenda.getText().toString()));



        DatabaseReference materialReference = databaseReference.child("materiais");
        materialReference.push().setValue(materiais);

        finish();

    }
}