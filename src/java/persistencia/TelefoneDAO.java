/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.TelefoneEntidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joaosantos
 */
public class TelefoneDAO {

    public static void inserirTelefone(TelefoneEntidade t) throws SQLException {
        String Query = "INSERT INTO telefone(id_usuario, fixo, celular, outro) VALUES(?,?,?,?);";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, t.getIdUsuario());
        pst.setString(2, t.getFixo());
        pst.setString(3, t.getCelular());
        pst.setString(4, t.getOutro());
        pst.executeUpdate();

        pst.close();
        pst = null;
    }

    public static void editarTelefones(TelefoneEntidade tel) throws SQLException {
        String Query = "UPDATE telefone SET fixo=?, celular=?, outro=? where id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);

        pst.setString(1, tel.getFixo());
        pst.setString(2, tel.getCelular());
        pst.setString(3, tel.getOutro());
        pst.setInt(4, tel.getIdUsuario());
        pst.executeUpdate();
        pst.close();
        pst = null;

    }
 
    public static TelefoneEntidade pegarTelefonePeloIdCliente(int id) throws SQLException {

        TelefoneEntidade t = new TelefoneEntidade();
        String Query = "SELECT * FROM telefone WHERE id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            
            t.setIdUsuario(rs.getInt("id_usuario"));
            t.setFixo(rs.getString("fixo"));
            t.setCelular(rs.getString("celular"));
            t.setOutro(rs.getString("outro"));

        } else{
            t.setIdUsuario(0);
            t.setFixo("0");
            t.setCelular("0");
            t.setOutro("0");
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return t;
    }

    public static void excluirTelefoneCliente(String idC) throws SQLException {
        String Query = " DELETE FROM telefone WHERE id_usuario = ? ;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idC);
        pst.executeUpdate();
        pst.close();
        pst = null;
    }

    public static void excluirTelefoneUsuarioInativo(Integer idUser) throws SQLException {
        String q = "DELETE FROM telefone WHERE id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idUser);
        pst.executeUpdate();
        pst.close();
        pst =null;
    }

}
