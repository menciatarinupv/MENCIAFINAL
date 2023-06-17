package com.example.proyectofinal;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

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
    public interface RespuestaRESTUI {
        void callbackUI(int codigo, String cuerpo);
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

    /*public static void mandarPalabraAlServidorRest(String palabraString) {
        PeticionarioREST elPeticionario = new PeticionarioREST();

        //uso el método POST en vez de el GET
        elPeticionario.hacerPeticionREST(
                "POST",
                url_servidor.orElse(servidor_por_defecto)+"/palabra",
                palabraString,
                new PeticionarioREST.RespuestaREST() {
                    @Override
                    public void callback(int codigo, String cuerpo) {
                        if (codigo == 200) {
                            // La palabra se envió y se almacenó correctamente en el servidor
                            // Puedes realizar alguna acción adicional aquí si es necesario
                            Log.d("LogicaNegocio", "La palabra se envió y se almacenó correctamente en el servidor.");
                        } else {
                            // Ocurrió un error al enviar la palabra al servidor
                            Log.e("LogicaNegocio", "Error al enviar la palabra al servidor. Código de respuesta: " + codigo);
                        }
                    }
                });
    }*/

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
       /*try {
           json.put("palabra", palabraString);
           Log.d("primeraApp", "paso a json el string de palabra " + json);
       } catch (JSONException e) {
           e.printStackTrace();
       }
       // Crear un objeto JSON a partir del nicknameString
       JSONObject jsonnickname = new JSONObject();
       try {
           jsonnickname.put("nombre", nicknameString);
           Log.d("primeraApp", "paso a json el string de nickname" + jsonnickname);
       } catch (JSONException e) {
           e.printStackTrace();
       }*/
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

}
