/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entidade.EnderecoEntidade;
import java.io.IOException;
import java.sql.SQLException;
import persistencia.EnderecoDAO;
import util.GoogleMapsApi;

/**
 *
 * @author joaosantos
 */
public class PegarCoordenadasEnderecoNoGoogle {

    public static EnderecoEntidade editarEndereco(EnderecoEntidade e) throws SQLException, IOException {

        if (e.getCidade() == 0 && e.getEstado() == 0) {
            return e;
        } else {
            //pegar cidade
            String cidade = EnderecoDAO.pegarNomeCidadePeloId(e.getCidade());
            String estado = EnderecoDAO.pegarNomeEstadoPeloId(Integer.valueOf(e.getEstado()));
            //monta query
            String queryPesquisa = GoogleMapsApi.montaQueryParaConsultaGoogleMapApi(e.getLogradouro(), e.getNumero(), cidade, estado);
            //pega retorno json com google
            String JsonResult = GoogleMapsApi.pegarJSONdaQueryDigitadaGoogleWS(queryPesquisa);
            //filtra a corrdenada para persistir
            String coordenadas = GoogleMapsApi.filtrarLtnLtgDoJSONResult(JsonResult);
            e.setCoordenada(coordenadas);
        }

        return e;

    }

//    public static String pegarCoordenadaUsuarioAtual(String idUser) throws SQLException {
//        String result = "";
//        
//        result = EnderecoDAO.pegarCoordenadaUsuario(idUser);
//        //remove a virgula fina
//        return result.substring(0,result.length()-1);
//
//    }
}
