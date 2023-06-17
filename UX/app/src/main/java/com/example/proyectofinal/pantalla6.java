package com.example.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
public class pantalla6 extends AppCompatActivity{
private String palabraRecibida;
private Intent enviar1;
private Intent enviar2;
private Intent enviar3;
private Intent enviar4;
private Intent enviar5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla6);


    }

    public void botonPalabra1_pulsado(View view){
        Log.d("segundaApp","botonPalabra1_pulsado empieza");

        enviar1 = new Intent(this, pantalla7.class);
        this.startActivity(enviar1);
        Log.d("segundaApp", "paso de pantalla a 7");

    }
    public void botonPalabra2_pulsado(View view){
        Log.d("segundaApp","botonPalabra2_pulsado empieza");

        enviar2 = new Intent(this, pantalla7.class);
        this.startActivity(enviar2);
        Log.d("segundaApp", "paso de pantalla a 7");

    }
    public void botonPalabra3_pulsado(View view){
        Log.d("segundaApp","botonPalabra3_pulsado empieza");

        enviar3 = new Intent(this, pantalla7.class);
        this.startActivity(enviar3);
        Log.d("segundaApp", "paso de pantalla a 7");

    }
    public void botonPalabra4_pulsado(View view){
        Log.d("segundaApp","botonPalabra4_pulsado empieza");

        enviar4 = new Intent(this, pantalla7.class);
        this.startActivity(enviar4);
        Log.d("segundaApp", "paso de pantalla a 7");

    }
    public void botonPalabra5_pulsado(View view){
        Log.d("segundaApp","botonPalabra5_pulsado empieza");

        enviar5 = new Intent(this, pantalla7.class);
        this.startActivity(enviar5);
        Log.d("segundaApp", "paso de pantalla a 7");

    }

}
