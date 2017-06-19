/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.ItemEntidade;
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
public class ItemDAO {

    public static void cadastrarItem(ItemEntidade p) throws SQLException, PersistenciaException {

        String Query = "INSERT INTO item(numero_pedido, id_usuario,id_produto, id_cliente, nome, preco_custo_item, valor_venda, qtde) values(?,?,?,?,?,?,?,?);";
        Connection Con = BaseDados.getInstancia().getConnection();
        PreparedStatement pst = Con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setLong(1, p.getNumeroPedido());
        pst.setInt(2, p.getIdUsuario());
        pst.setInt(3, p.getIdProduto());
        pst.setInt(4, p.getIdCliente());
        pst.setString(5, p.getNome());
        pst.setFloat(6, p.getPrecoCustoItem());
        pst.setFloat(7, p.getValorVenda());
        pst.setFloat(8, p.getQtde());

        pst.executeUpdate();
        pst.close();
        pst = null;
        Con.close();
        Con = null;

    }

//    public static boolean verificaSeProdutoJaEstaCadastrado(String Nome, int id_usu) throws SQLException {
//        String Query = "SELECT id_usuario FROM produto WHERE nome = ? AND id_usuario =?";
//        PreparedStatement Pst = BaseDados.getInstancia().prepareStatement(Query);
//        Pst.setString(1, Nome);
//        Pst.setInt(2, id_usu);
//        ResultSet Rs = Pst.executeQuery();
//        return Rs.next();
//    }
////
//
    public static List<ItemEntidade> ListarProdutosCadastrados(Long idPedido) throws SQLException {
        ItemEntidade it = null;

        ArrayList<ItemEntidade> lst = new ArrayList<ItemEntidade>();
        String query = "SELECT * FROM item where numero_pedido =" + idPedido + "; ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            it = new ItemEntidade();

            it.setNumeroPedido(rs.getLong("numero_pedido"));
            it.setIdUsuario(rs.getInt("id_usuario"));
            it.setIdProduto(rs.getInt("id_produto"));
            it.setIdCliente(rs.getInt("id_cliente"));
            it.setNome(rs.getString("nome"));
            it.setValorVenda(rs.getFloat("valor_venda"));
            it.setQtde(rs.getInt("qtde"));
            lst.add(it);

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return lst;
    }

    public static List<ItemEntidade> pegarItensDaVendaPeloNumPedido(Long numPed) throws SQLException {
        List<ItemEntidade> ls = new ArrayList<>();
        ItemEntidade i = null;
        String q = "SELECT * FROM item WHERE numero_pedido = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setLong(1, numPed);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            i = new ItemEntidade();
            i.setNumeroPedido(rs.getLong("numero_pedido"));
            i.setIdUsuario(rs.getInt("id_usuario"));
            i.setIdProduto(rs.getInt("id_produto"));
            i.setIdCliente(rs.getInt("id_cliente"));
            i.setNome(rs.getString("nome"));
            i.setValorVenda(rs.getFloat("valor_venda"));
            i.setQtde(rs.getInt("qtde"));
            i.setPrecoCustoItem(rs.getFloat("preco_custo_item"));

            ls.add(i);
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;

        return ls;
    }
////     
//
//    public static ProdutoEntidade pegarProdutoPeloId(int id) throws SQLException {
//
//        ProdutoEntidade it = null;
//        String Query = "SELECT * FROM produto WHERE id_produto = ?";
//        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
//        pst.setInt(1, id);
//
//        ResultSet rs = pst.executeQuery();
//
//        if (rs.next()) {
//            it = new ProdutoEntidade();
//            it.setIdProduto(rs.getInt("id_produto"));
//            it.setIdLinha(rs.getInt("id_linha"));
//            it.setIdUsuario(rs.getInt("id_usuario"));
//            it.setNome(rs.getString("nome"));
//            it.setDescricao(rs.getString("descricao"));
//            it.setPrecoCompra(rs.getFloat("preco_compra"));
//            it.setPrecoVenda(rs.getFloat("preco_venda"));
//            
//
//        }
//        return it;
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
//    public static void editarProdutoCadastrado(ProdutoEntidade it) throws SQLException {
//        String Query = "UPDATE  produto SET  id_linha = ? , nome = ?, descricao =? , preco_compra =?, preco_venda =? where  id_produto = ?;";
//
//        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
//        pst.setInt(1, it.getIdLinha());
//        pst.setString(2, it.getNome());
//        pst.setString(3, it.getDescricao());
//        pst.setFloat(4, it.getPrecoCompra());
//        pst.setFloat(5, it.getPrecoVenda());
//        pst.setInt(6, it.getIdProduto());
//        
//
//        pst.executeUpdate();
//
//    }
//

    public static void excluirItensPedido(Long numPedido) throws SQLException {

        String Query = " DELETE FROM item WHERE numero_pedido = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setLong(1, numPedido);
        pst.executeUpdate();
        pst.close();
        pst = null;

    }

    public static void excluirItensDaVendaCancelada(Long numPedido) throws SQLException {
        String Query = " DELETE FROM item WHERE numero_pedido = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setLong(1, numPedido);
        pst.executeUpdate();
        pst.close();
        pst = null;

    }

    public static Boolean verificarSeExistePedidoComProdutoASerExcluido(String idProduto, String idUsuario) throws SQLException {
        Boolean existe = false;
        String q = "SELECT * FROM item WHERE id_produto = ? AND id_usuario =? ;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setString(1, idProduto);
        pst.setString(2, idUsuario);
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

    public static List<ItemEntidade> pegarTodosItensVendidosParaRankProdutos(int i)throws SQLException {
        List<ItemEntidade> ls = new ArrayList<>();
        ItemEntidade it = null;
        String q = "SELECT * FROM item where id_usuario = ? order by id_produto desc;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, i);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            it = new ItemEntidade();
            it.setIdCliente(rs.getInt("id_cliente"));
            it.setIdProduto(rs.getInt("id_produto"));
            it.setIdUsuario(rs.getInt("id_usuario"));
            it.setNome(rs.getString("nome"));
            it.setNumeroPedido(rs.getLong("numero_pedido"));
            it.setPrecoCustoItem(rs.getFloat("preco_custo_item"));
            it.setQtde(rs.getInt("qtde"));
            it.setValorVenda(rs.getFloat("valor_venda"));
            ls.add(it);
        }
        return ls;
    }
}
