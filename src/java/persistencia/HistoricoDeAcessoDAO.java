/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.HistoricoDeAcessoEntidade;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author joaosantos
 */
public class HistoricoDeAcessoDAO {

    public static void registrarLoginAcesso(HistoricoDeAcessoEntidade hist) throws SQLException {
        
        String q = "INSERT into historico_de_acesso(id_usuario,nome_usuario,ip_acesso,data_acesso) values(?,?,?,?);";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, hist.getIdUsuario());
        pst.setString(2, hist.getNomeUsuario());
        pst.setString(3, hist.getIpAcesso());
        pst.setString(4, hist.getDataAcesso());
        pst.executeUpdate();
        pst.close();
        pst = null;
        
    }
    
    
    
}
