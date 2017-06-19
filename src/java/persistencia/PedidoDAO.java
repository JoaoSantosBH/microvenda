/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.PedidoEntidade;
import entidade.ProdutoEntidade;
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
public class PedidoDAO {

    public static void cadastrarPedido(PedidoEntidade p) throws SQLException, PersistenciaException {

        String Query = "INSERT INTO pedido( id_usuario, id_cliente, data, numero_pedido, status, Total) values(?,?,?,?,?,?);";
        Connection Con = BaseDados.getInstancia().getConnection();
        PreparedStatement pst = Con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, p.getIdUsuario());
        pst.setInt(2, p.getIdCliente());
        pst.setString(3, p.getData());
        pst.setLong(4, p.getNumeroPedido());
        pst.setInt(5, p.getStatus());
        pst.setFloat(6, p.getTotal());

        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            p.setIdPedido(rs.getInt(1));
        }
        pst.close();
        pst = null;
        Con.close();
        Con = null;

    }

    public static int contarNumeroDePedidos(int id_usu) throws SQLException {
        int total = 0;
        String Query = "SELECT * FROM pedido WHERE id_usuario = ? AND status =1";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, id_usu);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            total += 1;
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;

        return total;
    }

    public static int contarNumeroDePedidosCliente(int id_Cl) throws SQLException {
        int total = 0;
        String Query = "SELECT * FROM pedido WHERE id_cliente = ? AND status =1";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, id_Cl);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            total += 1;
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;

        return total;
    }
////
//

    public static List<PedidoEntidade> ListarPedidos(int id) throws SQLException {
        PedidoEntidade p = null;

        ArrayList<PedidoEntidade> lst = new ArrayList<PedidoEntidade>();
        String query = "SELECT * FROM pedido where id_usuario =" + id + " order by id_pedido DESC; ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            p = new PedidoEntidade();
            p.setIdPedido(rs.getInt("id_pedido"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setIdCliente(rs.getInt("id_cliente"));
            p.setData(rs.getString("data"));
            p.setNumeroPedido(rs.getLong("numero_pedido"));
            p.setStatus(rs.getShort("status"));
            p.setTotal(rs.getFloat("Total"));

            lst.add(p);

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return lst;
    }

    public static List<PedidoEntidade> ListarPedidosAtivosVendedor(int id) throws SQLException {
        PedidoEntidade p = null;

        ArrayList<PedidoEntidade> lst = new ArrayList<PedidoEntidade>();
        String query = "SELECT * FROM pedido where id_usuario = " + id + " and status = 1 order by id_pedido DESC; ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            p = new PedidoEntidade();
            p.setIdPedido(rs.getInt("id_pedido"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setIdCliente(rs.getInt("id_cliente"));
            p.setData(rs.getString("data"));
            p.setNumeroPedido(rs.getLong("numero_pedido"));
            p.setStatus(rs.getShort("status"));
            p.setTotal(rs.getFloat("Total"));

            lst.add(p);

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return lst;
    }
////     
//
//    public static ProdutoEntidade pegarProdutoPeloId(int id) throws SQLException {
//
//        ProdutoEntidade p = null;
//        String Query = "SELECT * FROM produto WHERE id_produto = ?";
//        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
//        pst.setInt(1, id);
//
//        ResultSet rs = pst.executeQuery();
//
//        if (rs.next()) {
//            p = new ProdutoEntidade();
//            p.setIdProduto(rs.getInt("id_produto"));
//            p.setIdLinha(rs.getInt("id_linha"));
//            p.setIdUsuario(rs.getInt("id_usuario"));
//            p.setNome(rs.getString("nome"));
//            p.setDescricao(rs.getString("descricao"));
//            p.setPrecoCompra(rs.getFloat("preco_compra"));
//            p.setPrecoVenda(rs.getFloat("preco_venda"));
//            
//
//        }
//        return p;
//    }
//    
//        public static String pegarNomeDoProdutoPeloId(int id, int idU) throws SQLException {
//
//        String nome = "";
//        String Query = "SELECT nome FROM produto WHERE id_produto = ? AND id_usuario =?";
//        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
//        pst.setInt(1, id);
//        pst.setInt(2, idU);
//        ResultSet rs = pst.executeQuery();
//
//        if (rs.next()) {
//            nome = rs.getString(1);
//
//        }
//        return nome;
//    }
////     
//

    public static void darBaixaPedido(Long numPedido) throws SQLException {
        String Query = "UPDATE  pedido SET status = ?  where  numero_pedido = ?;";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setLong(1, 0);
        pst.setLong(2, numPedido);

        pst.executeUpdate();
        pst.close();
        pst = null;

    }
//

    public static void excluirPedido(Long numPedido) throws SQLException {

        String Query = " DELETE FROM pedido WHERE numero_pedido = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setLong(1, numPedido);
        pst.executeUpdate();
        pst.close();
        pst = null;
    }

    public static PedidoEntidade pegarPedidoPeloId(int idPedido) throws SQLException {
        PedidoEntidade p = null;
        String query = "SELECT * FROM pedido WHERE id_pedido = ? ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, idPedido);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            p = new PedidoEntidade();
            p.setIdPedido(rs.getInt("id_pedido"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setIdCliente(rs.getInt("id_cliente"));
            p.setData(rs.getString("data"));
            p.setNumeroPedido(rs.getLong("numero_pedido"));
            p.setStatus(rs.getShort("status"));
            p.setTotal(rs.getFloat("Total"));
        } else {
            p = new PedidoEntidade();
            p.setIdPedido(0);
            p.setIdUsuario(000);
            p.setIdCliente(000);
            p.setData("00000000");
            p.setNumeroPedido(Long.valueOf("00"));
            p.setStatus(Short.valueOf("0"));
            p.setTotal(Float.valueOf("0"));
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return p;
    }

    public static PedidoEntidade pegarPedidoPeloNumPedido(Long numPedido) throws SQLException {
        PedidoEntidade p = null;
        String query = "SELECT * FROM pedido WHERE numero_pedido = ? ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setLong(1, numPedido);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            p = new PedidoEntidade();
            p.setIdPedido(rs.getInt("id_pedido"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setIdCliente(rs.getInt("id_cliente"));
            p.setData(rs.getString("data"));
            p.setNumeroPedido(rs.getLong("numero_pedido"));
            p.setStatus(rs.getShort("status"));
            p.setTotal(rs.getFloat("Total"));
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return p;
    }

    public static List<PedidoEntidade> ListarPedidosInativos(int idUser) throws SQLException {

        PedidoEntidade p = null;

        ArrayList<PedidoEntidade> lst = new ArrayList<PedidoEntidade>();
        String query = "SELECT * FROM pedido where id_usuario = " + idUser + " and status = 0 order by id_pedido DESC; ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            p = new PedidoEntidade();
            p.setIdPedido(rs.getInt("id_pedido"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setIdCliente(rs.getInt("id_cliente"));
            p.setData(rs.getString("data"));
            p.setNumeroPedido(rs.getLong("numero_pedido"));
            p.setStatus(rs.getShort("status"));
            p.setTotal(rs.getFloat("Total"));

            lst.add(p);

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return lst;
    }

    public static List<PedidoEntidade> pegarTodosOsPedidosDoCliente(int idUs, int idCl) throws SQLException {
        List<PedidoEntidade> lst = new ArrayList<>();
        PedidoEntidade p = new PedidoEntidade();
        String q = "SELECT * FROM pedido WHERE id_usuario = ? AND id_cliente = ? and status = 1 ORDER BY data DESC;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idUs);
        pst.setInt(2, idCl);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            p.setIdPedido(rs.getInt("id_pedido"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setIdCliente(rs.getInt("id_cliente"));
            p.setData(rs.getString("data"));
            p.setNumeroPedido(rs.getLong("numero_pedido"));
            p.setStatus(rs.getShort("status"));
            p.setTotal(rs.getFloat("Total"));
            lst.add(p);
        }
        pst.close();
        rs.close();
        pst = null;
        rs = null;

        return lst;
    }

    public static List<PedidoEntidade> pegarTodosOsPedidosDoUsuarioDoDiaCorrente(int idU, String dataAgora) throws SQLException {
        List<PedidoEntidade> lst = new ArrayList<>();
        PedidoEntidade p = null;
        String q = "SELECT * FROM pedido WHERE data = ? AND id_usuario = ? and status = 1 ORDER BY data DESC;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setString(1, dataAgora);
        pst.setInt(2, idU);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            p = new PedidoEntidade();
            p.setIdPedido(rs.getInt("id_pedido"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setIdCliente(rs.getInt("id_cliente"));
            p.setData(rs.getString("data"));
            p.setNumeroPedido(rs.getLong("numero_pedido"));
            p.setStatus(rs.getShort("status"));
            p.setTotal(rs.getFloat("Total"));
            lst.add(p);
        }

        pst.close();
        rs.close();
        pst = null;
        rs = null;

        return lst;
    }

    //METODO GENERICO USADO PARA TODOS --- AQUI USUARIO EH O  VENDEDOR
    public static List<PedidoEntidade> pegarTodosOsPedidosDoUsuarioPorPeriodoDeterminado(int idU, String dataIni, String dataFim) throws SQLException {
        List<PedidoEntidade> lst = new ArrayList<>();
        PedidoEntidade p = null;
        String q = "SELECT * FROM pedido WHERE data BETWEEN CAST(? AS DATE) AND CAST(? AS DATE) AND id_usuario = ? and status = 1 ORDER BY data DESC;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setString(1, dataIni);
        pst.setString(2, dataFim);
        pst.setInt(3, idU);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            p = new PedidoEntidade();
            p.setIdPedido(rs.getInt("id_pedido"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setIdCliente(rs.getInt("id_cliente"));
            p.setData(rs.getString("data"));
            p.setNumeroPedido(rs.getLong("numero_pedido"));
            p.setStatus(rs.getShort("status"));
            p.setTotal(rs.getFloat("Total"));
            lst.add(p);
        }
        pst.close();
        rs.close();
        pst = null;
        rs = null;

        return lst;
    }

    public static List<PedidoEntidade> listarPedidosAtivosDoCliente(int id) throws SQLException {
        PedidoEntidade p = null;
        List<PedidoEntidade> lst = new ArrayList<>();
        String q = "SELECT * FROM pedido WHERE id_cliente = ? and status = 1 ORDER BY data DESC; ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            p = new PedidoEntidade();
            p.setIdPedido(rs.getInt("id_pedido"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setIdCliente(rs.getInt("id_cliente"));
            p.setData(rs.getString("data"));
            p.setNumeroPedido(rs.getLong("numero_pedido"));
            p.setStatus(rs.getShort("status"));
            p.setTotal(rs.getFloat("Total"));
            lst.add(p);
        }
        pst.close();
        rs.close();
        pst = null;
        rs = null;

        return lst;
    }

}
