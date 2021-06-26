package com.afrd.limar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afrd.limar.R;
import com.google.android.material.textfield.TextInputLayout;

public class AdicionarQuantidadeDoItemActivity extends AppCompatActivity {
    private TextInputLayout quantidadeInsirida;
    private Button confirmar;
    private TextView descricao, qtdEstoque, valor;


    private String desRecebe, keyRecebe, unidade, fornecedor, id;
    private double valorVenda, valorCusto;
    private int quantidade, posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_quantidade_do_item);

        quantidadeInsirida = findViewById(R.id.textInputLayoutQuantidadeItem);
        confirmar = findViewById(R.id.buttonConfirmaAddItem);
        descricao = findViewById(R.id.textViewDescricaoAddQuantidade);
        qtdEstoque = findViewById(R.id.textViewQdtEstoqueAddQuantidade);
        valor = findViewById(R.id.textViewValorAddQuantidade);


        Intent intent = getIntent();
        unidade = intent.getStringExtra("unidade");
        fornecedor = intent.getStringExtra("fornecedor");
        id = intent.getStringExtra("id");
        desRecebe = intent.getStringExtra("descricao");
        valorVenda = intent.getDoubleExtra("valorVenda", 0);
        valorCusto = intent.getDoubleExtra("valorCusto", 0);
        quantidade = intent.getIntExtra("quantidade", 0);
        keyRecebe = intent.getStringExtra("key");

        this.descricao.setText("Descrição: " + desRecebe);
        this.qtdEstoque.setText("Quantidade: " + quantidade);
        this.valor.setText("Valor: " + valorVenda);


    }

    public void confirmar(View view){
        String valor = quantidadeInsirida.getEditText().getText().toString().trim();

        if( valor.isEmpty()){
            Toast.makeText(this, "Inserir uma quantidade", Toast.LENGTH_SHORT).show();
        }else{
            int value = Integer.parseInt(valor);
            if(value <= 0){
                Toast.makeText(this, "Inserir uma quantidade maior que 0", Toast.LENGTH_SHORT).show();
            }else if(value > quantidade  ){
                Toast.makeText(this, "Estoque insuficiente", Toast.LENGTH_SHORT).show();
            }else{
                Intent result = new Intent();
                result.putExtra("descricao",desRecebe);
                result.putExtra("valorVenda",valorVenda);
                result.putExtra("valorCusto", valorCusto);
                result.putExtra("quantidadeUsada",value);
                result.putExtra("quantidade",quantidade);
                result.putExtra("keyRecebe",keyRecebe);
                result.putExtra("unidade", unidade);
                result.putExtra("fornecedor", fornecedor);
                result.putExtra("id", id);

                setResult(RESULT_OK, result);
                finish();
            }
        }

    }
}