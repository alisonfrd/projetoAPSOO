package com.afrd.limar.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afrd.limar.R;
import com.afrd.limar.activity.CadastroClienteActivityPJ;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClienteEmpresaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClienteEmpresaFragment extends Fragment {


    public ClienteEmpresaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cliente_empresa, container, false);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fabClientes);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CadastroClienteActivityPJ.class);
                startActivity(intent);
            }
        });
        return view;
    }
}