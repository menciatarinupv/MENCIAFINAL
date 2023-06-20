package com.example.proyectofinal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class pantalla7 extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla7);
        LogicaNegocio.pedirTodasLasPalabras((resultados -> {}));
        LogicaNegocio.pedirTodosLosCodigos((resultados -> {}));
        LogicaNegocio.obtenerCodigoPalabraDelPuesto(1,(resultados -> {}));
    }

}
