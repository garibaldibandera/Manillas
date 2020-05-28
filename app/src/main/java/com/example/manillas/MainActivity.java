package com.example.manillas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText cantidad;
    private TextView unitario, total;
    private Spinner combo_material, combo_dije, combo_tipodije, combo_tipomoneda;
    private String[] opmaterial, opdije, optipodije, optipomoneda;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cantidad=findViewById(R.id.txtCantidad);
        combo_material=findViewById(R.id.cmbMaterial);
        combo_dije=findViewById(R.id.cmbDije);
        combo_tipodije=findViewById(R.id.cmbTipodije);
        combo_tipomoneda=findViewById(R.id.cmbTipomoneda);

        opmaterial=getResources().getStringArray(R.array.opmaterial);
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,opmaterial);
        combo_material.setAdapter(adapter);
        ArrayAdapter<String> adap_material = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opmaterial);

        opdije=getResources().getStringArray(R.array.opdije);
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,opdije);
        combo_dije.setAdapter(adapter);
        ArrayAdapter<String> adap_dije = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opdije);

        optipodije=getResources().getStringArray(R.array.optipodije);
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,optipodije);
        combo_tipodije.setAdapter(adapter);
        ArrayAdapter<String> adap_tipodije = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, optipodije);

        optipomoneda=getResources().getStringArray(R.array.optipomoneda);
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,optipomoneda);
        combo_tipomoneda.setAdapter(adapter);
        ArrayAdapter<String> adap_tipomoneda = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, optipomoneda);
    }

    public void aux_limpiar(View v){
        cantidad.setText("");
        combo_material.setSelection(0);
        combo_dije.setSelection(0);
        combo_tipodije.setSelection(0);
        combo_tipomoneda.setSelection(0);
    }
    public boolean validar(){
        String error_cantidad, error_material, error_dije, error_tipodije, error_tipomoneda;
        int posicion_mateiral, posicion_dije, posicion_tipodije, posicion_tipomoneda;

        error_cantidad=getResources().getString(R.string.error_cantidad);
        error_material=getResources().getString(R.string.error_material);
        error_dije=getResources().getString(R.string.error_dije);
        error_tipodije=getResources().getString(R.string.error_tipodije);
        error_tipomoneda=getResources().getString(R.string.error_tipomoneda);

        posicion_mateiral = combo_material.getSelectedItemPosition();
        posicion_dije=combo_dije.getSelectedItemPosition();
        posicion_tipodije=combo_tipodije.getSelectedItemPosition();
        posicion_tipomoneda=combo_tipomoneda.getSelectedItemPosition();

        if (cantidad.getText().toString().isEmpty()){
            cantidad.setError(error_cantidad);
            cantidad.requestFocus();
            return false;
        }else if(posicion_mateiral==0){
            Toast.makeText(this, error_material, Toast.LENGTH_LONG).show();
            combo_material.requestFocus();
            return false;
        }else if(posicion_dije==0){
            Toast.makeText(this, error_dije, Toast.LENGTH_LONG).show();
            combo_dije.requestFocus();
            return false;
        }else if(posicion_tipodije==0){
            Toast.makeText(this, error_tipodije, Toast.LENGTH_LONG).show();
            combo_tipodije.requestFocus();
            return false;
        }else if(posicion_tipomoneda==0){
            Toast.makeText(this, error_tipomoneda, Toast.LENGTH_LONG).show();
            combo_tipomoneda.requestFocus();
            return false;
        }
        return true;



    }


}
