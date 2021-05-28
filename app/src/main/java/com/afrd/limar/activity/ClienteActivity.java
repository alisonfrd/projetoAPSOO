package com.afrd.limar.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afrd.limar.Fragment.ClienteEmpresaFragment;
import com.afrd.limar.Fragment.ClienteParticularFragment;
import com.afrd.limar.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ClienteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        //configurando o BottomNavigation
        configuraBottomNavigationView();

        /*FloatingActionButton floatingActionButton = findViewById(R.id.fabClientes);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroClientesActivity.class);
                startActivity(intent);
            }
        });*/


    }
    //Metodo para criar o BottomNavigation
    private void configuraBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavigation_clientes);
        //configurações iniciais do Bottom Navigation
        bottomNavigationViewEx.enableItemShiftingMode(true);
        bottomNavigationViewEx.setTextVisibility(false);
        bottomNavigationViewEx.enableAnimation(true);
        bottomNavigationViewEx.enableShiftingMode(false);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPager_cliente, new ClienteParticularFragment()).commit();
        //Criando os fragments
        habilitaNavegacao(bottomNavigationViewEx);
    }

    private void habilitaNavegacao(BottomNavigationViewEx viewEx){


        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (item.getItemId()){
                    case R.id.ic_particular :
                        fragmentTransaction.replace(R.id.viewPager_cliente, new ClienteParticularFragment()).commit();
                        return true;
                    case R.id.ic_empresa :
                        fragmentTransaction.replace(R.id.viewPager_cliente, new ClienteEmpresaFragment()).commit();
                        return true;
                }
                return false;
            }
        });
    }
}