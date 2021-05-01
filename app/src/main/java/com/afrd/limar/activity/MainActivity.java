package com.afrd.limar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.afrd.limar.R;
import com.afrd.limar.activity.AtendimentosActivity;
import com.afrd.limar.activity.ClienteActivity;
import com.afrd.limar.activity.EstoqueActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

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

