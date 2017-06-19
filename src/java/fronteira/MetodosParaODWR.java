/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;


import entidade.TbCidadesEntidade;
import entidade.TbEstadosEntidade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.TbCidadesDAO;
import persistencia.TbEstadosDAO;


/**
 *
 * @author joaosantos
 */
public class MetodosParaODWR {

    public List<TbEstadosEntidade> pegarTodosEstadosDWR() {
        List<TbEstadosEntidade> lst = new ArrayList<TbEstadosEntidade>();
        try {
            lst = TbEstadosDAO.pegarEstados();
        } catch (SQLException ex) {
            Logger.getLogger(MetodosParaODWR.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    public List<TbCidadesEntidade> pegarCidadesDWR(String idEstado) {

        List<TbCidadesEntidade> lst = new ArrayList<TbCidadesEntidade>();
        try {
            lst = TbCidadesDAO.pegarCidadesPelaUfDWR(idEstado);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosParaODWR.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }



}
