package com.afrd.limar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.afrd.limar.R;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    //criar interface
    public void startActivityCliente(View view){
        Intent intent = new Intent(getApplicationContext(), ClienteActivity.class);
        startActivity(intent);
    }
    public void startActivityAtendimentos(View view){
        Intent intent = new Intent(getApplicationContext(), AtendimentosActivity.class);
        startActivity(intent);
    }
    public void startActivityMateriais(View view){
        Intent intent = new Intent(getApplicationContext(), MateriaisActivity.class);
        startActivity(intent);
    }


}

