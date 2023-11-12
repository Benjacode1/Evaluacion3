package com.example.evaluacion1appmoviles;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private EditText rutEditText;
    private Spinner labSpinner;
    private EditText nameEditText;
    private EditText incidentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rutEditText = findViewById(R.id.rutEditText);
        labSpinner = findViewById(R.id.labSpinner);
        nameEditText = findViewById(R.id.nameEditText);
        incidentEditText = findViewById(R.id.incidentEditText);

        Button enviarButton = findViewById(R.id.enviarButton);

        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (camposVacios()) {
                    Toast.makeText(getApplicationContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    String rut = rutEditText.getText().toString();
                    if (validarRut(rut)) {
                        enviarInformacion();
                    } else {
                        Toast.makeText(getApplicationContext(), "Rut no válido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean validarRut(String rut) {
        rut = rut.trim().toUpperCase();
        if (rut.matches("\\d{7,8}-[Kk0-9]")) {
            String[] rutParts = rut.split("-");
            String rutNumeros = rutParts[0];
            char rutVerificador = rutParts[1].charAt(0);

            char digitoVerificadorEsperado = calcularDigitoVerificador(rutNumeros);

            return rutVerificador == digitoVerificadorEsperado;
        }
        return false;
    }

    private String obtenerFechaHoraActual() {
        TimeZone timeZoneChile = TimeZone.getTimeZone("America/Santiago");
        Calendar calendarioChile = Calendar.getInstance(timeZoneChile);
        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        Date fechaHora = calendarioChile.getTime();

        return formatoFechaHora.format(fechaHora);
    }

    private char calcularDigitoVerificador(String rutNumeros) {
        int suma = 0;
        int multiplicador = 2;

        for (int i = rutNumeros.length() - 1; i >= 0; i--) {
            char digito = rutNumeros.charAt(i);
            int valorDigito = Character.getNumericValue(digito);
            suma += multiplicador * valorDigito;
            multiplicador = multiplicador == 7 ? 2 : multiplicador + 1;
        }

        int residuo = suma % 11;
        return residuo == 1 ? 'K' : residuo == 0 ? '0' : (char) ('0' + (11 - residuo));
    }

    private void enviarInformacion() {
        String laboratorio = labSpinner.getSelectedItem().toString();
        String nombre = nameEditText.getText().toString();
        String rut = rutEditText.getText().toString();
        String descripcion = incidentEditText.getText().toString();

        Intent intent = new Intent(MainActivity.this, MostrarIncidenteActivity.class);

        intent.putExtra("laboratorio", laboratorio);
        intent.putExtra("nombre", nombre);
        intent.putExtra("rut", rut);
        intent.putExtra("descripcion", descripcion);

        startActivity(intent);
    }

    private boolean camposVacios() {
        String laboratorio = labSpinner.getSelectedItem().toString();
        String nombre = nameEditText.getText().toString().trim();
        String rut = rutEditText.getText().toString().trim();
        String descripcion = incidentEditText.getText().toString().trim();

        return laboratorio.isEmpty() || nombre.isEmpty() || rut.isEmpty() || descripcion.isEmpty();
    }
}
