package com.example.proyectofinal;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.Arrays;
import java.util.Optional;

// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
@RequiresApi(api = Build.VERSION_CODES.N)

public class LogicaNegocio {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    public interface Respuesta {
        void callback ( Bundle resultados );
    }
    public interface RespuestaVacia{
        void callback();
    }
    public interface RespuestaPuntuacion{
        void callback();
    }
    public interface Respuesta2 {
        void callback ( Bundle resultado);
    }
    public interface RespuestaBorrar {
        void callback();
    }
    public interface RespuestaPalabras{
        void callback(String resultados );
    }
    public interface RespuestaCodigos{
        void callback(String resultados);
    }
    public interface RespuestaPuesto{

        void callback(int codigoquequiero);
    }


    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    private static Optional<String> url_servidor = Optional.empty();
    private static String servidor_por_defecto = "http://10.0.2.2:8080";
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    public static void ponerUrlServidor( String url ) {
        Log.d( "primeraApp", "LogicaNegocio.ponerUrlServidor(): empieza");

        //El concepto de Java Optional hace referencia a una variable
        // que puede tener un valor asignado o que puede contener un valor null.
        url_servidor = Optional.of( url );
        Log.d( "primeraApp", "LogicaNegocio.ponerUrlServidor(): termina");
    } // ()

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    public static void borrarTodoAntesDeJugar (RespuestaBorrar respuesta ){
        Log.d("primeraApp", "empiezaBorrarTodo");
        PeticionarioREST elPeticionario = new PeticionarioREST();
        Log.d("primeraApp", "creoPeticionarioBorrar");

        elPeticionario.hacerPeticionREST(
                "GET",
                url_servidor.orElse(servidor_por_defecto) + "/borrar",
                null,
                new PeticionarioREST.RespuestaREST() {
                    @Override
                    public void callback(int codigo, String cuerpo) {
                        respuesta.callback();
                    }
                }
        );
    }
   public static void mandarPalabraAlServidorRest(String palabraString, RespuestaVacia respuestaUI) {
       Log.d("primeraApp", "empiezaMadarPalabra");
       PeticionarioREST elPeticionario = new PeticionarioREST();
       Log.d("primeraApp", "creoPeticionario");
       // Crear un objeto JSON a partir de la palabraString
       JSONObject json = new JSONObject();
       try {
           json.put("palabra", palabraString);
           Log.d("primeraApp", "paso a json el string " + json);
       } catch (JSONException e) {
           e.printStackTrace();
       }

       // Convierte el objeto JSON a una cadena
       //String jsonStr = json.toString();

       elPeticionario.hacerPeticionREST(
               "POST",
               url_servidor.orElse(servidor_por_defecto)+"/palabra",
               json.toString(),
               new PeticionarioREST.RespuestaREST() {
                   @Override
                   public void callback(int codigo, String cuerpo) {
                       Log.d("Palabra", "el cuerpo es" + json.toString());
                       respuestaUI.callback();

                   }
               });
   }

   public static void mandarPalabraUserAlServidorRest(String palabraString, String nicknameString, RespuestaVacia respuestaUI) {
       Log.d("primeraApp", "empiezaMadarPalabraUser");
       PeticionarioREST elPeticionario = new PeticionarioREST();
       Log.d("primeraApp", "creoPeticionario");

       // Crear un objeto JSON a partir del palabraString
       JSONObject json1 = new JSONObject();

       try {
           json1.put("palabra", palabraString);
           json1.put("nombre", nicknameString);
           Log.d("primeraApp", "paso a json el string de palabra y el string de nickname " + json1);
       } catch (JSONException e) {
           e.printStackTrace();
       }

       elPeticionario.hacerPeticionREST(
               "POST",
               url_servidor.orElse(servidor_por_defecto)+"/palabraUser",
               json1.toString(),
               new PeticionarioREST.RespuestaREST() {
                   @Override
                   public void callback(int codigo, String cuerpo) {
                       Log.d("primeraApp", "el cuerpo es" + json1.toString());
                       respuestaUI.callback();

                   }
               });
    }

   public static void pedirPalabraAlServidorRest(int palabracual,Respuesta2 respuesta22){
        Log.d("primeraApp", "empiezaMadarPalabraUser");
        PeticionarioREST elPeticionario2 = new PeticionarioREST();
        Log.d("primeraApp", "creoPeticionario2");
        elPeticionario2.hacerPeticionREST(
                "GET",
                url_servidor.orElse(servidor_por_defecto)+"/palabras",
                null,
                new PeticionarioREST.RespuestaREST() {
                    @Override
                    public void callback(int codigo, String cuerpo) {
                        Log.d("segundaApp", "el cuerpo es" + codigo);


                        /* aquí deberíamos analizar la respuesta REST+HTTP. Si es correcta, parsear el resultado
                         que estará en JSON (pero como un único texto) para sacar las partes que nos interesen.

                         Luego, ponemos las respuestas ya pasadas al tipo correcto en bundle (almacén clave-valor)
                         y llamamos al callback que está esperando en la parte de UX
                         */
                        Bundle res = new Bundle();
                        res.putInt( "codigo", codigo );
                        res.putString( "resultadoSinParsear", cuerpo );

                        //JSONArray jsonArrayPalabras = JSONParser.parseString(cuerpo).getAsJsonArray();

                        try {
                            JSONArray jsonArrayPalabras = new JSONArray(cuerpo);
                            JSONObject jObject = jsonArrayPalabras.getJSONObject(palabracual);
                            res.putString("palabra", jObject.getString("palabra")); // Actualizado
                            respuesta22.callback(res);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        respuesta22.callback(res);

                        Log.d( "segundaApp", "LogicaNegocio.pedirAlgoAlServidorRest().callback: recibo: " + cuerpo );

                    }
                });
   }

   public static void mandarPuntuacionAlServidorRest(String palabraVotada, RespuestaPuntuacion respuestaPuntuacion){
       Log.d("segundaApp", "empiezaMadarPalabraUser");
       PeticionarioREST elPeticionario3 = new PeticionarioREST();
       Log.d("segundaApp", "creoPeticionario3");

       JSONObject json2 = new JSONObject();
       try {
           json2.put("palabra", palabraVotada);
           Log.d("primeraApp", "paso a json el string de palabra votada" + json2);
       } catch (JSONException e) {
           e.printStackTrace();
       }
       elPeticionario3.hacerPeticionREST(
               "POST",
               url_servidor.orElse(servidor_por_defecto) + "/ModificarPuntuacion",
               json2.toString(),
               new PeticionarioREST.RespuestaREST() {
                   @Override
                   public void callback(int codigo, String cuerpo) {
                       respuestaPuntuacion.callback();
                   }
               }

       );

   }

//creo metodo pedirTodasLasPuntuaciones(string palabras todas, respuesta) --> devuelve array con todas las puntuaciones
//creo el método obtenerCódigoPalabraDelPuesto(Int quepuestoquiero) --> devuelve el código de la palabra con el puesto "quepuestoquiero"
//creo el metodo pedirUsuarioConCódigoPalabra(Int codigo, respuesta ) --> devuelve el usuario y hago set text

    public static void pedirTodasLasPalabras(RespuestaPalabras respuestapal){
        Log.d("terceraApp", "empiezaPedirTodasLasPalabras");
        PeticionarioREST elPeticionario4 = new PeticionarioREST();
        Log.d("terceraApp", "creoPeticionario4");

        elPeticionario4.hacerPeticionREST(
                "GET",
                url_servidor.orElse(servidor_por_defecto)+"/palabras",
                null,
                new PeticionarioREST.RespuestaREST() {
                    @Override
                    public void callback(int codigo, String cuerpo) {
                        Log.d("terceraApp", "el cuerpo es" + codigo);

                        Bundle res = new Bundle();
                        res.putInt( "codigo", codigo );
                        res.putString( "resultadoSinParsear", cuerpo );


                        respuestapal.callback(cuerpo);
                        Log.d("terceraApp","tengo" + cuerpo);
                        Log.d( "terceraApp", "LogicaNegocio.pedirAlgoAlServidorRest().callback: recibo: " + cuerpo );

                    }
                });
    }

    public static void pedirTodosLosCodigos(RespuestaCodigos respuestacod) {
        pedirTodasLasPalabras(new RespuestaPalabras() {
            @Override
            public void callback(String todasLasPalabras) {
                PeticionarioREST elPeticionario5 = new PeticionarioREST();

                try {
                    // Convertir el string de palabras a JSON
                    JSONArray palabrasJsonArray = new JSONArray(todasLasPalabras);

                    // Pasar este JSONArray como cuerpo de la solicitud POST
                    elPeticionario5.hacerPeticionREST(
                            "POST",
                            url_servidor.orElse(servidor_por_defecto)+"/puntuacionPalabras",
                            palabrasJsonArray.toString(),
                            new PeticionarioREST.RespuestaREST() {
                                @Override
                                public void callback(int codigo, String cuerpo) {
                                    Log.d("terceraApp", "el cuerpo es" + cuerpo);

                                    Bundle res = new Bundle();
                                    res.putInt( "codigo", codigo );
                                    res.putString( "resultadoSinParsear", cuerpo );

                                    respuestacod.callback(cuerpo);
                                    Log.d("terceraApp","tengo" + cuerpo);
                                    Log.d( "terceraApp", "LogicaNegocio.pedirTodosLosCodigos().callback: recibo: " + cuerpo );
                                }
                            }
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static void obtenerCodigoPalabraDelPuesto(int quepuestoquiero, RespuestaPuesto respuestapuesto) {
        pedirTodosLosCodigos(new RespuestaCodigos() {
            @Override
            public void callback(String todosLosCodigos) {
                try {
                    // Convertir el string de códigos a un array de int
                    JSONArray codigosJsonArray = new JSONArray(todosLosCodigos);
                    int[] codigos = new int[codigosJsonArray.length()];
                    for (int i = 0; i < codigosJsonArray.length(); i++) {
                        codigos[i] = codigosJsonArray.getInt(i);
                    }

                    // Encontrar el quepuestoquiero-ésimo número más grande y su posición
                    int[] copiaCodigos = codigos.clone();
                    Arrays.sort(copiaCodigos);
                    int target = copiaCodigos[copiaCodigos.length - quepuestoquiero];
                    int posicion = -1;
                    for (int i = 0; i < codigos.length; i++) {
                        if (codigos[i] == target) {
                            posicion = i;
                            break;
                        }
                    }

                    // Obtener todas las palabras y encontrar el código de la palabra en la posición encontrada
                    int finalPosicion = posicion;
                    Log.d("terceraApp","la posicion que quiero es"+finalPosicion);
                    pedirTodasLasPalabras(new RespuestaPalabras() {
                        @Override
                        public void callback(String todasLasPalabras) {
                            try {
                                JSONArray palabrasJsonArray = new JSONArray(todasLasPalabras);
                                JSONObject palabraObj = palabrasJsonArray.getJSONObject(finalPosicion);
                                int codigoPalabra = palabraObj.getInt("codigo");
                                respuestapuesto.callback(codigoPalabra);
                                Log.d("terceraApp","llego al final de obtener codigo con puesto");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }




}
