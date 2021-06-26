package com.afrd.limar.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.afrd.limar.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarEquipamentoActivity extends AppCompatActivity {
    private TextInputEditText outlinedTextFieldCodEquipamento, outlinedTextFieldAmbiente, outlinedTextFieldTipoEquipamento,
            outlinedTextFieldModelo, outlinedTextFieldCapacidade, outlinedTextFieldFabricante, outlinedTextFieldTensaoemOp,
            outlinedTextFieldConsGeral;
    private Slider slider;

    private double valorSlider = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_equipamento);

        outlinedTextFieldCodEquipamento = findViewById(R.id.outlinedTextFieldCodEquipamento);
        outlinedTextFieldAmbiente = findViewById(R.id.outlinedTextFieldAmbiente);
        outlinedTextFieldTipoEquipamento = findViewById(R.id.outlinedTextFieldTipoEquipamento);
        outlinedTextFieldModelo = findViewById(R.id.outlinedTextFieldModelo);
        outlinedTextFieldCapacidade = findViewById(R.id.outlinedTextFieldCapacidade);
        outlinedTextFieldFabricante = findViewById(R.id.outlinedTextFieldFabricante);
        outlinedTextFieldTensaoemOp = findViewById(R.id.outlinedTextFieldTensaoemOp);
        outlinedTextFieldConsGeral = findViewById(R.id.outlinedTextFieldConsGeral);
        slider = findViewById(R.id.slider);

        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch( Slider slider) {
                }

            @Override
            public void onStopTrackingTouch(Slider slider) {


            }
        });

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(Slider slider, float value, boolean fromUser) {
                valorSlider = value;

            }
        });



    }

    public void salvarEquipamento  (View view){
        Intent intent = new Intent();
        intent.putExtra("codigo", outlinedTextFieldCodEquipamento.getText().toString().trim());
        intent.putExtra("ambiente", outlinedTextFieldAmbiente.getText().toString().trim());
        intent.putExtra("tipo", outlinedTextFieldTipoEquipamento.getText().toString().trim());
        intent.putExtra("modelo", outlinedTextFieldModelo.getText().toString().trim());
        intent.putExtra("capacidade", outlinedTextFieldCapacidade.getText().toString().trim());
        intent.putExtra("fabricante", outlinedTextFieldFabricante.getText().toString().trim());
        intent.putExtra("tensao", outlinedTextFieldTensaoemOp.getText().toString().trim());
        intent.putExtra("consideracoes", outlinedTextFieldConsGeral.getText().toString().trim());
        intent.putExtra("valorSlider", valorSlider);
        setResult(RESULT_OK, intent);

        finish();
    }
}