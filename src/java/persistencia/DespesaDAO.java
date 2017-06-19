/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.DespesaEntidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaosantos
 */
public class DespesaDAO {

    public static void registrarDespesa(DespesaEntidade d) throws SQLException {
        String q = "INSERT INTO despesa(id_usuario, descricao, data, valor) VALUES(?,?,?,?); ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, d.getIdUsuario());
        pst.setString(2, d.getDescricao());
        pst.setString(3, d.getData());
        pst.setFloat(4, d.getValor());
        pst.executeUpdate();
        pst.close();
        pst = null;
    }

    public static List<DespesaEntidade> listarTodasAsDespesas(int idUsuario) throws SQLException {
        DespesaEntidade d = null;
        List<DespesaEntidade> lst = new ArrayList<>();
        String q = "SELECT * FROM despesa WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idUsuario);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            d = new DespesaEntidade();
            d.setIdDespesa(rs.getInt("id_despesa"));
            d.setIdUsuario(rs.getInt("id_usuario"));
            d.setDescricao(rs.getString("descricao"));
            d.setData(rs.getString("data"));
            d.setValor(rs.getFloat("valor"));
            lst.add(d);
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;

        return lst;
    }

    public static List<DespesaEntidade> pegarDespesaPorPeriodoPreDefinido(int idUsuario, String dataIni, String dataFim) throws SQLException {
        List<DespesaEntidade> lst = new ArrayList<>();
        DespesaEntidade d = null;
        String query = "SELECT * FROM despesa Where data between CAST(? AS DATE) AND CAST(? AS DATE) AND id_usuario = ? ORDER BY id_despesa DESC;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setString(1, dataIni);
        pst.setString(2, dataFim);
        pst.setInt(3, idUsuario);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            d = new DespesaEntidade();
            d.setIdDespesa(rs.getInt("id_despesa"));
            d.setIdUsuario(rs.getInt("id_usuario"));
            d.setDescricao(rs.getString("descricao"));
            d.setData(rs.getString("data"));
            d.setValor(rs.getFloat("valor"));
            lst.add(d);
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;

        return lst;
    }

    public static Float pegarSomaDepsesaUsuario(int idUsuario) throws SQLException {
        Float total = 0f;
        String q = "select SUM(valor) FROM despesa WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idUsuario);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            total = rs.getFloat(1);
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return total;
    }
}
