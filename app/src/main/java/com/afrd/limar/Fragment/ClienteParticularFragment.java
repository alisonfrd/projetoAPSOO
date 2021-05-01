package com.afrd.limar.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.afrd.limar.R;
import com.afrd.limar.activity.MainActivity;
import com.afrd.limar.model.AdapterClienteParticular;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ClienteParticularFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterClienteParticular adapter;
    public ClienteParticularFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cliente_particular2, container, false);



        AdapterClienteParticular adapterClienteParticular = new AdapterClienteParticular();

        recyclerView = view.findViewById(R.id.recyclerClienteParticular);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayout.VERTICAL));

        recyclerView.setAdapter(adapterClienteParticular);

        // Inflate the layout for this fragment
        return view;


    }
}