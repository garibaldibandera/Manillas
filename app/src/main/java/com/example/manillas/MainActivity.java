package com.example.manillas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText cantidad;
    private TextView unitario, total;
    private Spinner combo_material, combo_dije, combo_tipodije, combo_tipomoneda;
    private String[] opmaterial, opdije, optipodije, optipomoneda;
    private ArrayAdapter<String> adapter;
    private Button btnLimpiar, btnCalcular;
    private int selectMaterial;
    private int selectDije;
    private int selectTipodije;
    private int selectMoneda;
    private double vlrUnitario;
    private double vlrtotal;
    private double vlrCantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cantidad = findViewById(R.id.txtCantidad);
        total = findViewById(R.id.lblValortotal);
        unitario = findViewById(R.id.lblValorunitario);
        combo_material = findViewById(R.id.cmbMaterial);
        combo_dije = findViewById(R.id.cmbDije);
        combo_tipodije = findViewById(R.id.cmbTipodije);
        combo_tipomoneda = findViewById(R.id.cmbTipomoneda);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnCalcular = findViewById(R.id.btnCalcular);
        inicializarComponentes();

        btnLimpiar.setOnClickListener(this);
        btnCalcular.setOnClickListener(this);
        opmaterial = getResources().getStringArray(R.array.opmaterial);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opmaterial);
        combo_material.setAdapter(adapter);
        ArrayAdapter<String> adap_material = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opmaterial);

        opdije = getResources().getStringArray(R.array.opdije);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opdije);
        combo_dije.setAdapter(adapter);
        ArrayAdapter<String> adap_dije = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opdije);

        optipodije = getResources().getStringArray(R.array.optipodije);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, optipodije);
        combo_tipodije.setAdapter(adapter);
        ArrayAdapter<String> adap_tipodije = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, optipodije);

        optipomoneda = getResources().getStringArray(R.array.optipomoneda);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, optipomoneda);
        combo_tipomoneda.setAdapter(adapter);
        ArrayAdapter<String> adap_tipomoneda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, optipomoneda);
    }

    private void inicializarComponentes() {
        combo_material.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectMaterial = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        combo_dije.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectDije = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        combo_tipodije.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectTipodije = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        combo_tipomoneda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectMoneda = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void limpiarbtn(View v) {
        limpiar();
    }
    public void limpiar(){
        cantidad.setText("");
        combo_material.setSelection(0);
        combo_dije.setSelection(0);
        combo_tipodije.setSelection(0);
        combo_tipomoneda.setSelection(0);
        total.setText("");
        unitario.setText("");
    }

    public boolean validar() {
        String error_cantidad, error_material, error_dije, error_tipodije, error_tipomoneda;
        int posicion_material, posicion_dije, posicion_tipodije, posicion_tipomoneda;

        error_cantidad = getResources().getString(R.string.error_cantidad);
        error_material = getResources().getString(R.string.error_material);
        error_dije = getResources().getString(R.string.error_dije);
        error_tipodije = getResources().getString(R.string.error_tipodije);
        error_tipomoneda = getResources().getString(R.string.error_tipomoneda);

        posicion_material = combo_material.getSelectedItemPosition();
        posicion_dije = combo_dije.getSelectedItemPosition();
        posicion_tipodije = combo_tipodije.getSelectedItemPosition();
        posicion_tipomoneda = combo_tipomoneda.getSelectedItemPosition();

        if (cantidad.getText().toString().isEmpty()) {

            Toast.makeText(this, error_cantidad, Toast.LENGTH_LONG).show();
//            cantidad.setError(error_cantidad);
            cantidad.requestFocus();
            return false;
        } else if (posicion_material == 0) {
            Toast.makeText(this, error_material, Toast.LENGTH_LONG).show();
            combo_material.requestFocus();
            return false;
        } else if (posicion_dije == 0) {
            Toast.makeText(this, error_dije, Toast.LENGTH_LONG).show();
            combo_dije.requestFocus();
            return false;
        } else if (posicion_tipodije == 0) {
            Toast.makeText(this, error_tipodije, Toast.LENGTH_LONG).show();
            combo_tipodije.requestFocus();
            return false;
        } else if (posicion_tipomoneda == 0) {
            Toast.makeText(this, error_tipomoneda, Toast.LENGTH_LONG).show();
            combo_tipomoneda.requestFocus();
            return false;
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLimpiar:
                limpiar();
                break;
            case R.id.btnCalcular:
                if (validar()) {
                    vlrCantidad = Integer.parseInt(cantidad.getText().toString());
                    if (selectMaterial == 1) {
                        if (selectDije == 1) {
                            if (selectTipodije == 1 || selectTipodije == 2 || selectTipodije == 3) {
                                if (selectMoneda == 1) {
                                    vlrUnitario = 100 * 3200;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_peso_colombiano) + vlrUnitario);
                                    total.setText(String.format("%1s %.1f", getResources().getString(R.string.opcion_peso_colombiano), vlrtotal));
                                } else {
                                    vlrUnitario = 100;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_Dolar) + vlrUnitario);
                                    total.setText(getResources().getString(R.string.opcion_Dolar) + vlrtotal);
                                }
                            } else if (selectTipodije == 4) {
                                if (selectMoneda == 1) {
                                    vlrUnitario = 80 * 3200;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_peso_colombiano) + vlrUnitario);
                                    total.setText(String.format("%1s %.1f", getResources().getString(R.string.opcion_peso_colombiano), vlrtotal));
                                } else {
                                    vlrUnitario = 80;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_Dolar) + vlrUnitario);
                                    total.setText(getResources().getString(R.string.opcion_Dolar) + vlrtotal);
                                }
                            } else if (selectTipodije == 5) {
                                if (selectMoneda == 1) {
                                    vlrUnitario = 70 * 3200;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_peso_colombiano) + vlrUnitario);
                                    total.setText(String.format("%1s %.1f", getResources().getString(R.string.opcion_peso_colombiano), vlrtotal));
                                } else {
                                    vlrUnitario = 70;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_Dolar) + vlrUnitario);
                                    total.setText(getResources().getString(R.string.opcion_Dolar) + vlrtotal);
                                }
                            }
                        } else if (selectDije == 2) {
                            if (selectTipodije == 1 || selectTipodije == 2 || selectTipodije == 3) {
                                if (selectMoneda == 1) {
                                    vlrUnitario = 120 * 3200;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_peso_colombiano) + vlrUnitario);
                                    total.setText(String.format("%1s %.1f", getResources().getString(R.string.opcion_peso_colombiano), vlrtotal));
                                } else {
                                    vlrUnitario = 120;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_Dolar) + vlrUnitario);
                                    total.setText(getResources().getString(R.string.opcion_Dolar) + vlrtotal);
                                }
                            } else if (selectTipodije == 4) {
                                if (selectMoneda == 1) {
                                    vlrUnitario = 100 * 3200;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_peso_colombiano) + vlrUnitario);
                                    total.setText(String.format("%1s %.1f", getResources().getString(R.string.opcion_peso_colombiano), vlrtotal));
                                } else {
                                    vlrUnitario = 100;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_Dolar) + vlrUnitario);
                                    total.setText(getResources().getString(R.string.opcion_Dolar) + vlrtotal);
                                }
                            } else if (selectTipodije == 5) {
                                if (selectMoneda == 1) {
                                    vlrUnitario = 90 * 3200;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_peso_colombiano) + vlrUnitario);
                                    total.setText(String.format("%1s %.1f", getResources().getString(R.string.opcion_peso_colombiano), vlrtotal));
                                } else {
                                    vlrUnitario = 90;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_Dolar) + vlrUnitario);
                                    total.setText(getResources().getString(R.string.opcion_Dolar) + vlrtotal);
                                }
                            }
                        }
                    } else if (selectMaterial == 2) {
                        if (selectDije == 1) {
                            if (selectTipodije == 1 || selectTipodije == 2 || selectTipodije == 3) {
                                if (selectMoneda == 1) {
                                    vlrUnitario = 90 * 3200;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_peso_colombiano) + vlrUnitario);
                                    total.setText(String.format("%1s %.1f", getResources().getString(R.string.opcion_peso_colombiano), vlrtotal));
                                } else {
                                    vlrUnitario = 90;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_Dolar) + vlrUnitario);
                                    total.setText(getResources().getString(R.string.opcion_Dolar) + vlrtotal);
                                }
                            } else if (selectTipodije == 4) {
                                if (selectMoneda == 1) {
                                    vlrUnitario = 70 * 3200;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_peso_colombiano) + vlrUnitario);
                                    total.setText(String.format("%1s %.1f", getResources().getString(R.string.opcion_peso_colombiano), vlrtotal));
                                } else {
                                    vlrUnitario = 70;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_Dolar) + vlrUnitario);
                                    total.setText(getResources().getString(R.string.opcion_Dolar) + vlrtotal);
                                }
                            } else if (selectTipodije == 5) {
                                if (selectMoneda == 1) {
                                    vlrUnitario = 50 * 3200;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_peso_colombiano) + vlrUnitario);
                                    total.setText(String.format("%1s %.1f", getResources().getString(R.string.opcion_peso_colombiano), vlrtotal));
                                } else {
                                    vlrUnitario = 50;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_Dolar) + vlrUnitario);
                                    total.setText(getResources().getString(R.string.opcion_Dolar) + vlrtotal);
                                }
                            }
                        } else if (selectDije == 2) {
                            if (selectTipodije == 1 || selectTipodije == 2 || selectTipodije == 3) {
                                if (selectMoneda == 1) {
                                    vlrUnitario = 110 * 3200;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_peso_colombiano) + vlrUnitario);
                                    total.setText(String.format("%1s %.1f", getResources().getString(R.string.opcion_peso_colombiano), vlrtotal));
                                } else {
                                    vlrUnitario = 110;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_Dolar) + vlrUnitario);
                                    total.setText(getResources().getString(R.string.opcion_Dolar) + vlrtotal);
                                }
                            } else if (selectTipodije == 4) {
                                if (selectMoneda == 1) {
                                    vlrUnitario = 90 * 3200;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_peso_colombiano) + vlrUnitario);
                                    total.setText(String.format("%1s %.1f", getResources().getString(R.string.opcion_peso_colombiano), vlrtotal));
                                } else {
                                    vlrUnitario = 90;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_Dolar) + vlrUnitario);
                                    total.setText(getResources().getString(R.string.opcion_Dolar) + vlrtotal);
                                }
                            } else if (selectTipodije == 5) {
                                if (selectMoneda == 1) {
                                    vlrUnitario = 80 * 3200;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_peso_colombiano) + vlrUnitario);
                                    total.setText(String.format("%1s %.1f", getResources().getString(R.string.opcion_peso_colombiano), vlrtotal));
                                } else {
                                    vlrUnitario = 80;
                                    vlrtotal = vlrCantidad * vlrUnitario;
                                    unitario.setText(getResources().getString(R.string.opcion_Dolar) + vlrUnitario);
                                    total.setText(getResources().getString(R.string.opcion_Dolar) + vlrtotal);
                                }
                            }
                        }
                    }
                break;
            }
        }
    }
}
