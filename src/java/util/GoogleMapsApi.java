/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entidade.EnderecoEntidade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.EnderecoDAO;
import persistencia.PersistenciaException;
import persistencia.TbCidadesDAO;

/**
 *
 * @author joaosantos
 */
public class GoogleMapsApi {

    public static String pegarJSONdaQueryDigitadaGoogleWS(String query) throws MalformedURLException, IOException {

        String jsonResult = "";
        String url = query;
        URL queryToGoogle = new URL(url);
        InputStream is = queryToGoogle.openConnection().getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line = null;
        while ((line = reader.readLine()) != null) {
            jsonResult += line;
        }
        reader.close();
        return jsonResult;
    }

    public static String filtrarLtnLtgDoJSONResult(String json) {
        String latLng = "";
        String divide[] = json.split("location");
        String a = divide[1];
        String separa[] = a.split(":");

        String dois = separa[2];
        String treis = separa[3];

        String elimina[] = dois.split(",");
        String lat = elimina[0];
        String eliminar[] = treis.split("}");
        String longi = eliminar[0];
        longi = longi.replaceAll(" ", "");

        latLng = lat + ", " + longi + ",";
        return latLng;
    }

    
    
    
    
    public static String montaQueryParaConsultaGoogleMapApi(String logradouro, String numero, String cidade, String estado) {
        cidade = cidade.replaceAll(" ", "+");
        estado = estado.replaceAll(" ", "+");
        String query = logradouro;
        query = query.replaceAll(" ", "+");
        query += "+";
        query += numero;
        query += ",";
        query += cidade;
        query += ",";
        query += estado;
        String url = "http://maps.google.com/maps/api/geocode/json?address=" + query + "&sensor=true_or_false";

        return url;

    }

}
