/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.LinhaEntidade;
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
public class ProdutoDAO {

    public static void cadastrarProduto(ProdutoEntidade p) throws SQLException, PersistenciaException {

        if (verificaSeProdutoJaEstaCadastrado(p.getNome(), p.getIdUsuario())) {
            throw new PersistenciaException("Este Produto ja foi cadastrado!");
        }

        String Query = "INSERT INTO produto(id_linha, id_usuario, nome, descricao, preco_compra, preco_venda, ativo) values(?,?,?,?,?,?,1);";
        Connection Con = BaseDados.getInstancia().getConnection();
        PreparedStatement pst = Con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, p.getIdLinha());
        pst.setInt(2, p.getIdUsuario());
        pst.setString(3, p.getNome());
        pst.setString(4, p.getDescricao());
        pst.setFloat(5, p.getPrecoCompra());
        pst.setFloat(6, p.getPrecoVenda());

        pst.executeUpdate();

        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            p.setIdProduto(rs.getInt(1));
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        Con.close();
        Con = null;

    }

    public static boolean verificaSeProdutoJaEstaCadastrado(String Nome, int id_usu) throws SQLException {
        String Query = "SELECT id_usuario FROM produto WHERE nome = ? AND id_usuario =?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, Nome);
        pst.setInt(2, id_usu);
        ResultSet rs = pst.executeQuery();
        boolean retorno = rs.next();
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return retorno;
    }
//

    public static List<ProdutoEntidade> ListarProdutosCadastrados(int id) throws SQLException {
        ProdutoEntidade p = null;

        ArrayList<ProdutoEntidade> lst = new ArrayList<ProdutoEntidade>();
        String query = "SELECT * FROM produto where id_usuario =" + id + " AND ativo =1 order by nome ; ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            p = new ProdutoEntidade();
            p.setIdProduto(rs.getInt("id_produto"));
            p.setIdLinha(rs.getInt("id_linha"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setNome(rs.getString("nome"));
            p.setDescricao(rs.getString("descricao"));
            p.setPrecoCompra(rs.getFloat("preco_compra"));
            p.setPrecoVenda(rs.getFloat("preco_venda"));
            lst.add(p);

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return lst;
    }
//     

    public static ProdutoEntidade pegarProdutoPeloId(int id) throws SQLException {

        ProdutoEntidade p = null;
        String Query = "SELECT * FROM produto WHERE id_produto = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            p = new ProdutoEntidade();
            p.setIdProduto(rs.getInt("id_produto"));
            p.setIdLinha(rs.getInt("id_linha"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setNome(rs.getString("nome"));
            p.setDescricao(rs.getString("descricao"));
            p.setPrecoCompra(rs.getFloat("preco_compra"));
            p.setPrecoVenda(rs.getFloat("preco_venda"));

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return p;
    }

    public static String pegarNomeDoProdutoPeloId(int id, int idU) throws SQLException {

        String nome = "";
        String Query = "SELECT nome FROM produto WHERE id_produto = ? AND id_usuario =?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, id);
        pst.setInt(2, idU);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            nome = rs.getString(1);

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return nome;
    }
//     

    public static void editarProdutoCadastrado(ProdutoEntidade p) throws SQLException {
        String Query = "UPDATE  produto SET  id_linha = ? , nome = ?, descricao =? , preco_compra =?, preco_venda =? where  id_produto = ?;";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, p.getIdLinha());
        pst.setString(2, p.getNome());
        pst.setString(3, p.getDescricao());
        pst.setFloat(4, p.getPrecoCompra());
        pst.setFloat(5, p.getPrecoVenda());
        pst.setInt(6, p.getIdProduto());

        pst.executeUpdate();
        pst.close();
        pst = null;

    }

    public static void excluirProduto(String idProduto) throws SQLException {

        String Query = "UPDATE produto SET ativo = 0 WHERE id_produto = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idProduto);
        pst.executeUpdate();
        pst.close();
        pst = null;

    }

    public static Boolean verificarSeProdutoEstaAtivo(Integer idProduto) throws SQLException {
        Boolean ativo = false;
        String query = "SELECT ativo FROM produto WHERE id_produto = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, idProduto);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int result = rs.getInt("ativo");
            if (result == 1) {
                ativo = true;
            }
        }

        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return ativo;
    }

    public static Boolean verificarSeProdutoEDoVendedor(String idP, int idUser) throws SQLException {

        String query = "SELECT * FROM produto where id_produto = ? and id_usuario =? ;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setString(1, idP);
        pst.setInt(2, idUser);
        ResultSet rs = pst.executeQuery();
        Boolean eMeu = rs.next();
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return eMeu;
    }

    public static Boolean verificarSeExisteProdutoCadastradoNaLinhaASerExcluida(String idLinha, String idUsuario) throws SQLException {
        Boolean existe = false;
        String q = "SELECT * FROM produto WHERE id_linha = ? AND id_usuario = ? AND ativo =1 ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setString(1, idLinha);
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
}
