package com.afrd.limar.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afrd.limar.R;

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
        return inflater.inflate(R.layout.fragment_cliente_empresa, container, false);
    }
}