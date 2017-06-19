/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.DataCriacaoEntidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joaosantos
 */
public class DataCriacaoDAO {

    public static void registrarDataCriação(DataCriacaoEntidade dtCri) throws SQLException{
        String q = "INSERT INTO data_criacao(id_usuario, data_criacao) VALUES(?,?);";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, dtCri.getIdUsuario());
        pst.setString(2, dtCri.getDataCriacao());
        pst.executeUpdate();
        pst.close();
        pst = null;
        
    }

    public static void excluirRegistro(Integer idUsuario) throws SQLException {
        String q = "DELETE FROM data_criacao WHERE id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idUsuario);
        pst.executeUpdate();
        pst.close();
        pst = null;
    }

    public static String pegarDataCriacao(Integer idUsuario) throws SQLException{
        String d = "";
        String q = "SELECT * FROM data_criacao WHERE id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idUsuario);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            d = rs.getString("data_criacao");
        }
        pst.close();
        rs.close();
        pst =null;
        rs = null;
        
        return d;
    }
    
}
