package com.afrd.limar.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.afrd.limar.Fragment.AtendimentoPendenciaFragment;
import com.afrd.limar.Fragment.AtendimentoRelatorioFragment;
import com.afrd.limar.Fragment.ClienteEmpresaFragment;
import com.afrd.limar.Fragment.ClienteParticularFragment;
import com.afrd.limar.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class AtendimentosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atendimentos);
        configuraBottomNavigationView();
    }

    //Metodo para criar o BottomNavigation
    private void configuraBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavigation_atendimentos);
        //configurações iniciais do Bottom Navigation
        bottomNavigationViewEx.setTextVisibility(false);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPagerAtendimentos, new AtendimentoPendenciaFragment()).commit();
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
                        fragmentTransaction.replace(R.id.viewPagerAtendimentos, new AtendimentoPendenciaFragment()).commit();
                        return true;
                    case R.id.ic_empresa :
                        fragmentTransaction.replace(R.id.viewPagerAtendimentos, new AtendimentoRelatorioFragment()).commit();
                        return true;
                }
                return false;
            }
        });
    }
}