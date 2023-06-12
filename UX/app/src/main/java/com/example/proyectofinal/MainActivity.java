package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {
    private TextView tematica;
    private EditText textoPalabra;
    private Intent enviar;
    private String stringPalabra;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "estoy en onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tematica = (TextView) findViewById(R.id.tematica);
        textoPalabra = (EditText) findViewById(R.id.palabra);


    }

    public void botonEnviar_pulsado (View quien){
        Log.d("botón", "Botón pulsado");

        //intent `para empezar la otra actividad
        enviar = new Intent(this, pantalla6.class);

        //empieza la actividad
        this.startActivity(enviar);


    }
}