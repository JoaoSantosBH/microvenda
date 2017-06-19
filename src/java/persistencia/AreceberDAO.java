/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.AreceberEntidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaosantos
 */
public class AreceberDAO {

    public static void criarNovoAreceber(AreceberEntidade ar) throws SQLException {

        String query = "INSERT INTO areceber(id_venda, id_usuario, id_cliente, data, valor, numero_parcela) values(?,?,?,?,?,?)";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, ar.getIdVenda());
        pst.setInt(2, ar.getIdUsuario());
        pst.setInt(3, ar.getIdCliente());
        pst.setString(4, ar.getData());
        pst.setFloat(5, ar.getValor());
        pst.setInt(6, ar.getNumeroParcela());

        pst.executeUpdate();
        pst.close();
        pst = null;

    }

    public static List<AreceberEntidade> pegarListaDeParcelasAReceber(int idUser) throws SQLException {
        List<AreceberEntidade> lst = new ArrayList<>();
        AreceberEntidade ar = null;
        String query = "SELECT * FROM areceber WHERE id_usuario = ? order by data;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, idUser);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            ar = new AreceberEntidade();

            ar.setIdAreceber(rs.getInt("id_areceber"));
            ar.setIdVenda(rs.getInt("id_venda"));
            ar.setIdUsuario(rs.getInt("id_usuario"));
            ar.setIdCliente(rs.getInt("id_cliente"));
            ar.setData(rs.getString("data"));
            ar.setValor(rs.getFloat("valor"));
            ar.setNumeroParcela(rs.getInt("numero_parcela"));
            lst.add(ar);
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return lst;
    }

    public static int pegarValorDaParcela(int idVenda) throws SQLException {
        int retorno = 0;
        String query = "SELECT valor FROM areceber WHERE id_venda = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, idVenda);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            retorno = rs.getInt("valor");
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return retorno;
    }
    public static void excluirParcelaAReceber(int idAReceber) throws SQLException{
        
        String query ="DELETE FROM areceber WHERE id_areceber = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, idAReceber);
        pst.executeUpdate();
        pst.close();
        pst =null;
        
    }

    public static List<AreceberEntidade> pegarListaDeAReceberDeUmaVendaEspecifica(int idVenda, int idUsuario) throws SQLException{
        List<AreceberEntidade> lst = new ArrayList<>();
        AreceberEntidade ar = null;
        String query = "SELECT * FROM areceber WHERE id_venda =? AND id_usuario = ? order by id_areceber";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, idVenda);
        pst.setInt(2, idUsuario);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            ar = new AreceberEntidade();
            ar.setIdAreceber(rs.getInt("id_areceber"));
            ar.setIdVenda(rs.getInt("id_venda"));
            ar.setIdUsuario(rs.getInt("id_usuario"));
            ar.setIdCliente(rs.getInt("id_cliente"));
            ar.setData(rs.getString("data"));
            ar.setValor(rs.getFloat("valor"));
            ar.setNumeroParcela(rs.getInt("numero_parcela"));
            lst.add(ar);
        }
        pst.close();
        rs.close();
        pst = null;
        rs = null;
        return lst;
    }

    public static List<AreceberEntidade> pegarListaDeParcelasAPagar(String idVend, int idCliente) throws SQLException{
        List<AreceberEntidade> lst = new ArrayList<>();
        AreceberEntidade ar = null;
        String q = "SELECT * FROM areceber WHERE id_usuario = ? AND id_cliente=? ORDER BY DATA;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setString(1, idVend);
        pst.setInt(2, idCliente);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            ar = new AreceberEntidade();
            ar.setIdAreceber(rs.getInt("id_areceber"));
            ar.setIdVenda(rs.getInt("id_venda"));
            ar.setIdUsuario(rs.getInt("id_usuario"));
            ar.setIdCliente(rs.getInt("id_cliente"));
            ar.setData(rs.getString("data"));
            ar.setValor(rs.getFloat("valor"));
            ar.setNumeroParcela(rs.getInt("numero_parcela"));
            lst.add(ar);            
        }
        
        pst.close();
        rs.close();
        pst = null;
        rs = null;
        return lst;
    }

    public static void apagarParcelasAreceberVendaCancelada(Integer idVenda) throws SQLException{
        String q = "DELETE FROM areceber WHERE id_venda = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idVenda);
        pst.executeUpdate();
        pst.close();
        pst = null;
        
    }

}
