package com.example.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
public class pantalla6 extends AppCompatActivity{
private String palabraRecibida;
private Intent enviar1;
private Intent enviar2;
private Intent enviar3;
private Intent enviar4;
private Intent enviar5;
private Button palabra1;
private Button palabra2;
private Button palabra3;
private Button palabra4;
private Button palabra5;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla6);
        palabra1 = (Button) findViewById(R.id.palabra1);
        palabra2 = (Button) findViewById(R.id.palabra2);
        palabra3 = (Button) findViewById(R.id.palabra3);
        palabra4 = (Button) findViewById(R.id.palabra4);
        palabra5 = (Button) findViewById(R.id.palabra5);
        LogicaNegocio.pedirPalabraAlServidorRest(0, (respuesta)->{
            palabra1.setText(respuesta.getString("palabra"));
        });
        LogicaNegocio.pedirPalabraAlServidorRest(1, (respuesta)->{
            palabra2.setText(respuesta.getString("palabra"));
        });
        LogicaNegocio.pedirPalabraAlServidorRest(2, (respuesta)->{
            palabra3.setText(respuesta.getString("palabra"));
        });
        LogicaNegocio.pedirPalabraAlServidorRest(3, (respuesta)->{
            palabra4.setText(respuesta.getString("palabra"));
        });
        LogicaNegocio.pedirPalabraAlServidorRest(4, (respuesta)->{
            palabra5.setText(respuesta.getString("palabra"));
        });

    }

    public void botonPalabra1_pulsado(View view){
        Log.d("segundaApp","botonPalabra1_pulsado empieza");

        LogicaNegocio.mandarPuntuacionAlServidorRest(palabra1.getText().toString(),()->{
            enviar1 = new Intent(this, pantalla7.class);
            this.startActivity(enviar1);
            Log.d("segundaApp", "paso de pantalla a 7");

        });


    }
    public void botonPalabra2_pulsado(View view){
        Log.d("segundaApp","botonPalabra2_pulsado empieza");
        LogicaNegocio.mandarPuntuacionAlServidorRest(palabra2.getText().toString(),()->{
            enviar2 = new Intent(this, pantalla7.class);
            this.startActivity(enviar2);
            Log.d("segundaApp", "paso de pantalla a 7");
        });



    }
    public void botonPalabra3_pulsado(View view){
        Log.d("segundaApp","botonPalabra3_pulsado empieza");
        LogicaNegocio.mandarPuntuacionAlServidorRest(palabra3.getText().toString(),()->{
            enviar3 = new Intent(this, pantalla7.class);
            this.startActivity(enviar3);
            Log.d("segundaApp", "paso de pantalla a 7");
        });
    }
    public void botonPalabra4_pulsado(View view){
        Log.d("segundaApp","botonPalabra4_pulsado empieza");
        LogicaNegocio.mandarPuntuacionAlServidorRest(palabra4.getText().toString(),()->{
            enviar4 = new Intent(this, pantalla7.class);
            this.startActivity(enviar4);
            Log.d("segundaApp", "paso de pantalla a 7");
        });

    }
    public void botonPalabra5_pulsado(View view){
        Log.d("segundaApp","botonPalabra5_pulsado empieza");
        LogicaNegocio.mandarPuntuacionAlServidorRest(palabra5.getText().toString(),()->{
            enviar5 = new Intent(this, pantalla7.class);
            this.startActivity(enviar5);
            Log.d("segundaApp", "paso de pantalla a 7");
        });
    }

}
