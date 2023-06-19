package com.example.proyectofinal;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.PipedOutputStream;
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






}
