// En MostrarDatosActivity
package com.example.evaluacion1appmoviles;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MostrarDatosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);

        // Obtener los datos pasados como extras
        Intent intent = getIntent();
        String rut = intent.getStringExtra("rut");
        String fechaHora = intent.getStringExtra("fechaHora");

        // Mostrar los datos en los TextView
        TextView rutTextView = findViewById(R.id.rutTextView);
        TextView fechaHoraTextView = findViewById(R.id.fechaHoraTextView);

        rutTextView.setText("Rut: " + rut);
        fechaHoraTextView.setText("Fecha y Hora actual: " + fechaHora);
    }
}

