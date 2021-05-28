package com.afrd.limar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.afrd.limar.R;

public class CadastroAtendimentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_atendimento);
    }

    public void addEquipamento(View view){
        Intent intent = new Intent(getApplicationContext(), AdicionarEquipamentoActivity.class);
        startActivity(intent);

    }
    public void salvarAtendimento(View view){
        finish();
    }
}