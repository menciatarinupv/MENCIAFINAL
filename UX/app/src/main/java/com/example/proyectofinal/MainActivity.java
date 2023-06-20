package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;





public class MainActivity extends AppCompatActivity {
    private TextView tematica;
    private EditText textoPalabra;
    private Intent enviar;
    private String stringPalabra;
    private EditText textoNickname;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "estoy en onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tematica = (TextView) findViewById(R.id.tematica);
        textoPalabra = (EditText) findViewById(R.id.palabra);
        textoNickname = (EditText) findViewById(R.id.nickname);


       /* LogicaNegocio.borrarTodoAntesDeJugar((new LogicaNegocio.RespuestaBorrar() {
            @Override
            public void callback() {
               Log.d("primeraApp", "todo se ha borrado");
            }
        }));*/
    }

   public void botonEnviar_pulsado (View view){
       Log.d( "primeraApp", "MainActivity.botonEnviar_pulsado(): empieza");
       //pasar la palabra del EditText a String
       //String palabraString = textoPalabra.getText().toString();
       Log.d("primeraApp", textoPalabra.getText().toString());
        LogicaNegocio.mandarPalabraAlServidorRest(textoPalabra.getText().toString(), () -> {
            enviar = new Intent(this, pantalla6.class);
            //empieza la actividad
            this.startActivity(enviar);
            Log.d("primeraApp", "paso de pantalla");
        });

   }

   public void botonEnviarTodo_pulsado (View view){
       Log.d( "primeraApp", "MainActivity.botonEnviarTodo_pulsado(): empieza");
       Log.d("primeraApp", textoPalabra.getText().toString());
       Log.d("primeraApp", textoNickname.getText().toString());
       LogicaNegocio.mandarPalabraUserAlServidorRest(textoPalabra.getText().toString(), textoNickname.getText().toString(), () -> {
           enviar = new Intent(this, pantalla6.class);
           //empieza la actividad
           this.startActivity(enviar);
           Log.d("primeraApp", "paso de pantalla");
       });
   }

}