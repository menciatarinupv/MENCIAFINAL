package com.example.proyectofinal;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class pantalla7 extends AppCompatActivity{

    private TextView ganador;
    private TextView segundo;
    private TextView tercero;
    private TextView cuarto;
    private TextView quinto;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla7);
        /*LogicaNegocio.pedirTodasLasPalabras((resultados -> {}));
        LogicaNegocio.pedirTodosLosCodigos((resultados -> {}));
        LogicaNegocio.obtenerCodigoPalabraDelPuesto(1,(resultados -> {}));*/
        ganador = (TextView) findViewById(R.id.ganador);
        segundo = (TextView) findViewById(R.id.segundo);
        tercero = (TextView) findViewById(R.id.tercero);
        cuarto =(TextView) findViewById(R.id.cuarto);
        quinto = (TextView) findViewById(R.id.quinto);

        LogicaNegocio.obtenerCodigoPalabraDelPuesto(1, new LogicaNegocio.RespuestaPuesto() {
            @Override
            public void callback(int codigo) {
                // Aquí recibes el código de palabra
                int codigoPalabra = codigo;
                // Hacer algo con el código (llamada a pedirUsuarioConCodigoDePalabra)
                LogicaNegocio.pedirUsuarioConCodigoDePalabra(codigoPalabra, new LogicaNegocio.RespuestaUsuario() {
                    @Override
                    public void callback(String cuerpo) {
                        // Aquí recibes el usuario asociado con el código de palabra
                        // Hacer algo con el usuario
                        // Asegúrate de actualizar el TextView en el hilo principal
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ganador.setText(cuerpo);
                            }
                        });
                    }
                });
            }
        });
        LogicaNegocio.obtenerCodigoPalabraDelPuesto(2, new LogicaNegocio.RespuestaPuesto() {
            @Override
            public void callback(int codigo) {
                // Aquí recibes el código de palabra
                int codigoPalabra = codigo;
                // Hacer algo con el código (llamada a pedirUsuarioConCodigoDePalabra)
                LogicaNegocio.pedirUsuarioConCodigoDePalabra(codigoPalabra, new LogicaNegocio.RespuestaUsuario() {
                    @Override
                    public void callback(String cuerpo) {
                        // Aquí recibes el usuario asociado con el código de palabra
                        // Hacer algo con el usuario
                        // Asegúrate de actualizar el TextView en el hilo principal
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                segundo.setText(cuerpo);
                            }
                        });
                    }
                });
            }
        });
        LogicaNegocio.obtenerCodigoPalabraDelPuesto(3, new LogicaNegocio.RespuestaPuesto() {
            @Override
            public void callback(int codigo) {
                // Aquí recibes el código de palabra
                int codigoPalabra = codigo;
                // Hacer algo con el código (llamada a pedirUsuarioConCodigoDePalabra)
                LogicaNegocio.pedirUsuarioConCodigoDePalabra(codigoPalabra, new LogicaNegocio.RespuestaUsuario() {
                    @Override
                    public void callback(String cuerpo) {
                        // Aquí recibes el usuario asociado con el código de palabra
                        // Hacer algo con el usuario
                        // Asegúrate de actualizar el TextView en el hilo principal
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tercero.setText(cuerpo);
                            }
                        });
                    }
                });
            }
        });
        LogicaNegocio.obtenerCodigoPalabraDelPuesto(4, new LogicaNegocio.RespuestaPuesto() {
            @Override
            public void callback(int codigo) {
                // Aquí recibes el código de palabra
                int codigoPalabra = codigo;
                // Hacer algo con el código (llamada a pedirUsuarioConCodigoDePalabra)
                LogicaNegocio.pedirUsuarioConCodigoDePalabra(codigoPalabra, new LogicaNegocio.RespuestaUsuario() {
                    @Override
                    public void callback(String cuerpo) {
                        // Aquí recibes el usuario asociado con el código de palabra
                        // Hacer algo con el usuario
                        // Asegúrate de actualizar el TextView en el hilo principal
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cuarto.setText(cuerpo);
                            }
                        });
                    }
                });
            }
        });
       LogicaNegocio.obtenerCodigoPalabraDelPuesto(5, new LogicaNegocio.RespuestaPuesto() {
            @Override
            public void callback(int codigo) {
                // Aquí recibes el código de palabra
                int codigoPalabra = codigo;
                // Hacer algo con el código (llamada a pedirUsuarioConCodigoDePalabra)
                LogicaNegocio.pedirUsuarioConCodigoDePalabra(codigoPalabra, new LogicaNegocio.RespuestaUsuario() {
                    @Override
                    public void callback(String cuerpo) {
                        // Aquí recibes el usuario asociado con el código de palabra
                        // Hacer algo con el usuario
                        // Asegúrate de actualizar el TextView en el hilo principal
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                quinto.setText(cuerpo);
                            }
                        });
                    }
                });
            }
        });




    }

}
