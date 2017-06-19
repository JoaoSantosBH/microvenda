/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import controle.ControladorVenda;
import entidade.VendaEntidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaosantos
 */
public class VendaDAO {

    public static int criarNovaVenda(VendaEntidade venda) throws SQLException {
        int id = 0;
        String query = "INSERT INTO venda (num_pedido, id_usuario, id_cliente, data, forma_pagamento, valor, parcelas, entrada) values (?,?,?,?,?,?,?,?);";
        Connection Con = BaseDados.getInstancia().getConnection();
        PreparedStatement pst = Con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setLong(1, venda.getNumPedido());
        pst.setInt(2, venda.getIdUsuario());
        pst.setInt(3, venda.getIdCliente());
        pst.setString(4, venda.getData());
        pst.setShort(5, venda.getFormaPagamento());
        pst.setFloat(6, venda.getValor());
        pst.setInt(7, venda.getParcelas());
        pst.setFloat(8, venda.getEntrada());
        pst.executeUpdate();

        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            venda.setIdVenda(rs.getInt(1));
            id = venda.getIdVenda();

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        Con.close();
        Con = null;

        return id;
    }

    public static VendaEntidade pegarVendaPeloId(int idVenda) throws SQLException {
        VendaEntidade v = null;
        String query = "SELECT * FROM venda Where id_venda = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, idVenda);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            v = new VendaEntidade();
            v.setIdVenda(rs.getInt("id_venda"));
            v.setNumPedido(rs.getLong("num_pedido"));
            v.setIdUsuario(rs.getInt("id_usuario"));
            v.setIdCliente(rs.getInt("id_cliente"));
            v.setData(rs.getString("data"));
            v.setFormaPagamento(rs.getShort("forma_pagamento"));
            v.setValor(rs.getFloat("valor"));
            v.setParcelas(rs.getInt("parcelas"));
            v.setEntrada(rs.getFloat("entrada"));

        }

        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return v;
    }

    public static List<VendaEntidade> pegarTodasAsVendasDoUsuario(int idUser) throws SQLException {
        List<VendaEntidade> ls = new ArrayList<VendaEntidade>();
        VendaEntidade v = null;
        String query = "SELECT * FROM venda WHERE id_usuario = ? ORDER BY data DESC;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, idUser);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            v = new VendaEntidade();
            v.setIdVenda(rs.getInt("id_venda"));
            v.setNumPedido(rs.getLong("num_pedido"));
            v.setIdUsuario(rs.getInt("id_usuario"));
            v.setIdCliente(rs.getInt("id_cliente"));
            v.setData(rs.getString("data"));
            v.setFormaPagamento(rs.getShort("forma_pagamento"));
            v.setValor(rs.getFloat("valor"));
            v.setParcelas(rs.getInt("parcelas"));
            v.setEntrada(rs.getFloat("entrada"));
            ls.add(v);
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return ls;
    }

    public static Boolean verificarSeJaExisteVendaComNumPedidoAtual(String numPedido) throws SQLException {
        Boolean existe = false;
        String query = "SELECT * FROM venda WHERE num_pedido = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setString(1, numPedido);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            existe = true;
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return existe;

    }

    public static List<VendaEntidade> pegarVendasDoUsuarioProPeriodoPreDefinido(int idUser, String dataIni, String dataFim) throws SQLException {
        List<VendaEntidade> ls = new ArrayList<VendaEntidade>();
        VendaEntidade v = null;
        String query = "SELECT * FROM venda Where data between CAST(? AS DATE) AND CAST(? AS DATE) AND id_usuario = ? ORDER BY id_venda DESC;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setString(1, dataIni);
        pst.setString(2, dataFim);
        pst.setInt(3, idUser);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            v = new VendaEntidade();
            v.setIdVenda(rs.getInt("id_venda"));
            v.setNumPedido(rs.getLong("num_pedido"));
            v.setIdUsuario(rs.getInt("id_usuario"));
            v.setIdCliente(rs.getInt("id_cliente"));
            v.setData(rs.getString("data"));
            v.setFormaPagamento(rs.getShort("forma_pagamento"));
            v.setValor(rs.getFloat("valor"));
            v.setParcelas(rs.getInt("parcelas"));
            v.setEntrada(rs.getFloat("entrada"));
            ls.add(v);
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return ls;
    }

    public static List<VendaEntidade> pegarTodasAsVendasPorClienteDoVendedor(int idUsu, int idCliente) throws SQLException {
        List<VendaEntidade> lst = new ArrayList<>();
        VendaEntidade v = null;
        String query = "SELECT * FROM venda Where id_usuario =? AND id_cliente =? ORDER BY data DESC;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, idUsu);
        pst.setInt(2, idCliente);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            v = new VendaEntidade();
            v.setIdVenda(rs.getInt("id_venda"));
            v.setNumPedido(rs.getLong("num_pedido"));
            v.setIdUsuario(rs.getInt("id_usuario"));
            v.setIdCliente(rs.getInt("id_cliente"));
            v.setData(rs.getString("data"));
            v.setFormaPagamento(rs.getShort("forma_pagamento"));
            v.setValor(rs.getFloat("valor"));
            v.setParcelas(rs.getInt("parcelas"));
            v.setEntrada(rs.getFloat("entrada"));
            lst.add(v);
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return lst;
    }

    public static VendaEntidade pegarVendaPeloNumPedido(Long numPedido, int idU) throws SQLException {
        VendaEntidade v = new VendaEntidade();
        String q = "SELECT * FROM venda WHERE num_pedido like '%" + numPedido + "%' AND id_usuario = ? ;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);

        pst.setInt(1, idU);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            v.setIdVenda(rs.getInt("id_venda"));
            v.setNumPedido(rs.getLong("num_pedido"));
            v.setIdUsuario(rs.getInt("id_usuario"));
            v.setIdCliente(rs.getInt("id_cliente"));
            v.setData(rs.getString("data"));
            v.setFormaPagamento(rs.getShort("forma_pagamento"));
            v.setValor(rs.getFloat("valor"));
            v.setParcelas(rs.getInt("parcelas"));
            v.setEntrada(rs.getFloat("entrada"));
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;

        return v;
    }

    public static List<VendaEntidade> pegarListaDeVendasQueAtendemQueryLike(String idUser, String query) throws SQLException {
        VendaEntidade v = null;
        List<VendaEntidade> lst = new ArrayList<>();
        String q = "SELECT * FROM venda WHERE num_pedido like '%" + query + "%' AND id_usuario = ?  ORDER BY data DESC;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setString(1, idUser);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            v = new VendaEntidade();
            v.setIdVenda(rs.getInt("id_venda"));
            v.setNumPedido(rs.getLong("num_pedido"));
            v.setIdUsuario(rs.getInt("id_usuario"));
            v.setIdCliente(rs.getInt("id_cliente"));
            v.setData(rs.getString("data"));
            v.setFormaPagamento(rs.getShort("forma_pagamento"));
            v.setValor(rs.getFloat("valor"));
            v.setParcelas(rs.getInt("parcelas"));
            v.setEntrada(rs.getFloat("entrada"));
            lst.add(v);
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return lst;
    }

    public static List<VendaEntidade> pegarListaDeComprasDoCLiente(String idVendedor, int idCliente) throws SQLException {
        List<VendaEntidade> lst = new ArrayList<>();
        VendaEntidade v = null;
        String q = "SELECT * FROM venda WHERE id_usuario = ? AND id_cliente = ? ORDER BY data DESC;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setString(1, idVendedor);
        pst.setInt(2, idCliente);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            v = new VendaEntidade();
            v.setIdVenda(rs.getInt("id_venda"));
            v.setNumPedido(rs.getLong("num_pedido"));
            v.setIdUsuario(rs.getInt("id_usuario"));
            v.setIdCliente(rs.getInt("id_cliente"));
            v.setData(rs.getString("data"));
            v.setFormaPagamento(rs.getShort("forma_pagamento"));
            v.setValor(rs.getFloat("valor"));
            v.setParcelas(rs.getInt("parcelas"));
            v.setEntrada(rs.getFloat("entrada"));
            lst.add(v);
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return lst;
    }

    public static void apagarVenda(Integer idVenda) throws SQLException{
        String q = "DELETE FROM venda WHERE id_venda = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idVenda);
        pst.executeUpdate();
        pst.close();
        pst = null;
        
        
    }

}
