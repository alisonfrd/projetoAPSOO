package com.afrd.limar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.afrd.limar.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EstoqueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        FloatingActionButton floatingActionButton = findViewById(R.id.fabEstoque);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroItemEstoque.class);
                startActivity(intent);
            }
        });


    }
}