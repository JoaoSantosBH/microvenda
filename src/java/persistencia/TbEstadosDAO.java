/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.TbEstadosEntidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joaosantos
 */
public class TbEstadosDAO {

    public static ArrayList<TbEstadosEntidade> pegarEstados() throws SQLException {
        ArrayList<TbEstadosEntidade> lst = new ArrayList<TbEstadosEntidade>();
        String Query = "SELECT * FROM tb_estados order by id asc;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            TbEstadosEntidade t = new TbEstadosEntidade();
            t.setId(rs.getInt("id"));
            t.setNome(rs.getString("nome"));
            t.setUf(rs.getString("uf"));
            lst.add(t);
        }
        pst.close();
        pst = null;
        rs.close();
        rs =null;
        return lst;
    }
    
    public static String pegarNomeDoEstadoPeloSeuId(int id) throws SQLException{
        String result = "";
        String query = "SELECT nome FROM tb_estados WHERE id = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            result= rs.getString("nome");
        }
        pst.close();
        rs.close();
        pst = null;
        rs = null;
        
        return result;
    }

}
