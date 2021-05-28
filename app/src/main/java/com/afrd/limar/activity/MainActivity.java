package com.afrd.limar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.afrd.limar.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {


    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Alison");

        DatabaseReference usuario = databaseReference.child("Usuario");
        usuario.child("02").setValue(pessoa);

    }


    //criar interface
    public void startActivityClient(View view){
        Intent intent = new Intent(getApplicationContext(), ClienteActivity.class);
        startActivity(intent);
    }
    public void startActivityAtendimentos(View view){
        Intent intent = new Intent(getApplicationContext(), AtendimentosActivity.class);
        startActivity(intent);
    }
    public void startActivityEstoque(View view){
        Intent intent = new Intent(getApplicationContext(), EstoqueActivity.class);
        startActivity(intent);
    }


}

