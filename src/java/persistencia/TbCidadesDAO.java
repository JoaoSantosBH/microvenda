/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.TbCidadesEntidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaosantos
 */
public class TbCidadesDAO {

    public static ArrayList<TbCidadesEntidade> pegarTodasAsCidades() throws SQLException {

        ArrayList<TbCidadesEntidade> lst = new ArrayList<TbCidadesEntidade>();
        String Query = "SELECT * FROM tb_cidades;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            TbCidadesEntidade t = new TbCidadesEntidade();
            t.setId(rs.getInt("id"));
            t.setNome(rs.getString("nome"));
            t.setEstado(rs.getInt("estado"));
            t.setNome(rs.getString("nome"));

            lst.add(t);
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;

        return lst;
    }

    public static ArrayList<TbCidadesEntidade> pegarCidadesPelaUf(String uf) throws SQLException {
        ArrayList<TbCidadesEntidade> lst = new ArrayList<TbCidadesEntidade>();
        String Query = "SELECT * FROM tb_cidades where estado =? ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, uf);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            TbCidadesEntidade cidade = new TbCidadesEntidade();
            cidade.setId(rs.getInt("id"));
            cidade.setNome(rs.getString("nome"));
            cidade.setEstado(rs.getInt("estado"));
            cidade.setUf(rs.getString("uf"));

            lst.add(cidade);
        }

        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return lst;
    }

    public static List<TbCidadesEntidade> pegarCidadesPelaUfDWR(String uf) throws SQLException {
        List<TbCidadesEntidade> lst = new ArrayList<TbCidadesEntidade>();
        String Query = "SELECT * FROM tb_cidades where estado =? ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, uf);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            TbCidadesEntidade cidade = new TbCidadesEntidade();
            cidade.setId(rs.getInt("id"));
            cidade.setNome(rs.getString("nome"));
            cidade.setEstado(rs.getInt("estado"));
            cidade.setUf(rs.getString("uf"));

            lst.add(cidade);
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;

        return lst;
    }

    public static String pegarCidadePeloId(Integer cidade) throws SQLException {
        String result = "";
        String Query = "SELECT nome FROM tb_cidades WHERE id = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, cidade);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            result = rs.getString("nome");
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return result;
    }
}
