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
public class PegarEnderecoPeloCep {

    public static String pegarJSONdaQueryDigitada(String query) throws MalformedURLException, IOException {

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

    public static String filtrarRuaDoJSONResult(String json) {

        String divide[] = json.split("endereco");
        String a = divide[1];
        String separa[] = a.split(":");

        String dois = separa[1];
        String b[] = dois.split("\"");
        String endereco = b[1];

        return endereco;
    }

//    //S EM  U S O 
//    public static List<String> criarListaDeUrlsParaConsulta(String idUser) throws SQLException {
//
//        List<EnderecoEntidade> lst = null;
//        List<String> urlS = new ArrayList<String>();
//
//        //01 - pegar cidade usuario
//        Integer cidadeUser = EnderecoDAO.pegarCidadeUsuario(idUser);
//        String bairro = EnderecoDAO.pegarBairroUsuario(idUser);
//
//        //02- criar lista de todos usuarios proximos
//        lst = EnderecoDAO.pegarListaEnderecosPorCidadeBairro(cidadeUser, bairro);
//
//        for (EnderecoEntidade e : lst) {
//            String query = e.getLogradouro();
//            query += "+";
//            query += e.getNumero().toString();
//            query += "+";
//            String cidade = TbCidadesDAO.pegarCidadePeloId(e.getCidade());
//            String url = "http://maps.google.com/maps/api/geocode/json?address=" + query + "," + cidade + "&sensor=true_or_false";
////            System.out.println(url);
//            urlS.add(url);
//
//        }
//        return urlS;
////S EM  U S O 
//    }
    public static String montaQueryParaConsultaGoogleMapApi(String cep) {

//        String url = "http://maps.google.com/maps/api/geocode/json?components=postal_code:" + cep + "/";
//        String url = "http://clareslab.com.br/ws/cep/json/" + cep + "/";
        String url = "http://viacep.com.br/ws/" + cep + "/json";
        return url;

    }

//    public static String montarVetorJS(String idUser) throws SQLException, MalformedURLException, IOException, PersistenciaException {
//
//        List<EnderecoEntidade> lst = null;
//        List<String> vetorGoogle = new ArrayList<String>();
//        //01 - pegar cidade usuario
//        Integer cidadeUser = EnderecoDAO.pegarCidadeUsuario(idUser);
//        String bairro = EnderecoDAO.pegarBairroUsuario(idUser);
//
//        //02- criar lista de todos usuarios proximos
//        lst = EnderecoDAO.pegarListaEnderecosPorCidadeBairro(cidadeUser, bairro);
//        int i = 1;
//        for (EnderecoEntidade e : lst) {
//
//            //pega um produto qualkquer do usuario
//            String produto = PatrimonioDAO.pegarPatrimonioPorIdUsuario(e.getIdUsuario().toString());
//            String idProduto = PatrimonioDAO.pegarIdPatrimonioPeloNomePatrimonio(produto);
//            String img = PatrimonioDAO.pegarFotoPatrimonioPeloIdPatrimonio(idProduto);
//            String link = "produto.jsp?idProd=" + idProduto;
//            String url = "<a href=\"" + link + "\">'+\n"
//                    + "      '" 
//                    + "<img width=\"80px\" height=\"80px\" style=\" background:#d8d7d2; display: block; margin-left: auto; margin-right: auto\" src=\"" 
//                    + img + "\"/></br>" + produto + "</a>";
//
//            if (!produto.equals("")) {//se usuario tiver produtos 
//
//                //pega coordenadas
//                String coordenada = EnderecoDAO.pegarCoordenadaUsuario(e.getIdUsuario().toString());
//
//                //monta string
//                String linha = "['" + produto + "', " + coordenada + " " + i + ", '" + url + "'], ";
//
//                //adiciona ao vetor
//                vetorGoogle.add(linha);
//                i++;
//            }
//
//        }
//
//        //monta vetor js
//        String vetorJs = "";
//        for (String s : vetorGoogle) {
//            vetorJs += s;
//        }
//        return vetorJs;
//    }
}
