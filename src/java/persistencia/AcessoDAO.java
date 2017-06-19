/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.AcessoEntidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joaosantos
 */
public class AcessoDAO {

    public static void inserirAcessoEntidade(AcessoEntidade a) throws SQLException {
        String Query = "INSERT INTO acesso(id_usuario, acesso_atual, ultimo_acesso, ip_acesso) VALUES(?,?,?,?);";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, a.getIdUsuario());
        pst.setString(2, a.getAcessoAtual());
        pst.setString(3, a.getUltimoAcesso());
        pst.setString(4, a.getIpAcesso());

        pst.executeUpdate();
        
        pst.close();
        pst = null;
    }

    public static void realizarRodizioDeDatasDeAcesso(AcessoEntidade a) throws SQLException {
        AcessoEntidade acesso = null;
        String query = "SELECT * from acesso WHERE id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, a.getIdUsuario());
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            acesso = new AcessoEntidade();
            acesso.setIdAcesso(rs.getInt("id_acesso"));
            acesso.setIdUsuario(rs.getInt("id_usuario"));
            acesso.setAcessoAtual(rs.getString("acesso_atual"));
            acesso.setUltimoAcesso(rs.getString("ultimo_acesso"));
            acesso.setIpAcesso(a.getIpAcesso());
        }
        
        acesso.setUltimoAcesso(acesso.getAcessoAtual());
        acesso.setAcessoAtual(a.getAcessoAtual());
        atualizarAcesso(acesso);
        pst.close();
        pst = null;
        rs.close();
        rs =null;
    }

    public static void atualizarAcesso(AcessoEntidade a) throws SQLException {
        String query = "UPDATE acesso SET  acesso_atual = ?, ultimo_acesso= ?, ip_acesso =? WHERE id_usuario =?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setString(1, a.getAcessoAtual());
        pst.setString(2, a.getUltimoAcesso());
        pst.setString(3, a.getIpAcesso());
        pst.setInt(4, a.getIdUsuario());
        
        pst.executeUpdate();
        pst.close();
        pst = null;
    }

    public static String pegarUltimoAcesso(int idUsuario) throws SQLException {
        String data = "";
        String query = "SELECT * FROM acesso WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, idUsuario);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            data = rs.getString("ultimo_acesso");
        }

        pst.close();
        pst = null;
        rs.close();
        rs =null;
        return data;
    }

    public static void excluirAcessoUsuarioInativo(Integer idUsuario) throws SQLException {
     String q = "DELETE FROM acesso WHERE id_usuario =?";
     PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
     pst.setInt(1, idUsuario);
     pst.executeUpdate();
     pst.close();
     pst = null;
    }

}
