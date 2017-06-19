/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.AreceberEntidade;
import java.sql.SQLException;
import java.util.List;
import persistencia.AreceberDAO;

/**
 *
 * @author joaosantos
 */
public class ControladorAReceber {
    
    
    public static List<AreceberEntidade> pegarListaDeParcelasAReceber(int idUser) throws SQLException{
        
        List<AreceberEntidade> ls = AreceberDAO.pegarListaDeParcelasAReceber(idUser);
        
        return ls;
    }
    
    public static List<AreceberEntidade> pegarListaDeAReceberDeUmaVendaEspecifica(int idVenda, int idUser) throws SQLException{
        List<AreceberEntidade> ls = AreceberDAO.pegarListaDeAReceberDeUmaVendaEspecifica(idVenda,idUser);
        return ls;
        
    }

    public static List<AreceberEntidade> pegarListaDeParcelasAPagar(String idVend, int idCliente) throws SQLException {
        List<AreceberEntidade> lst = AreceberDAO.pegarListaDeParcelasAPagar(idVend, idCliente);
        return lst;
    }
            
            
         
}
