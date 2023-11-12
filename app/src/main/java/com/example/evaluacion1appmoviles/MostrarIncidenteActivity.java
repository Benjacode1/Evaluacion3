package com.example.evaluacion1appmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MostrarIncidenteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_incidente);

        Intent intent = getIntent();
        String laboratorio = intent.getStringExtra("laboratorio");
        String nombre = intent.getStringExtra("nombre");
        String rut = intent.getStringExtra("rut");
        String descripcion = intent.getStringExtra("descripcion");

        TextView laboratorioTextView = findViewById(R.id.laboratorioTextView);
        laboratorioTextView.setText("Laboratorio: " + laboratorio);

        TextView nombreTextView = findViewById(R.id.nombreTextView);
        nombreTextView.setText("Nombre: " + nombre);

        TextView rutTextView = findViewById(R.id.rutTextView);
        rutTextView.setText("RUT: " + rut);

        TextView descripcionTextView = findViewById(R.id.descripcionTextView);
        descripcionTextView.setText("Descripción: " + descripcion);

        TextView horaTextView = findViewById(R.id.horaTextView);
        String horaActual = obtenerHoraActual();
        horaTextView.setText("Hora del incidente: " + horaActual);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMainActivity();
            }
        });
    }

    private void returnToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Elimina las actividades del stack
        startActivity(intent);
        finish(); // Finaliza esta actividad para liberar memoria
    }

    private String obtenerHoraActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

}
