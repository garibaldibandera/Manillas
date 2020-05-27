package com.example.manillas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
}
