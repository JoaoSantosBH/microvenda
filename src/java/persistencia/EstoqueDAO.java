/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import entidade.EstoqueEntidade;
import entidade.ItemEntidade;
import entidade.LinhaEntidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.EnviarEmail;

/**
 *
 * @author joaosantos
 */
public class EstoqueDAO {

    public static void cadastrarItemNoEstoque(EstoqueEntidade n) throws SQLException, PersistenciaException {
        if (verificaSeItemJaFoiCadastrado(n.getIdProduto(), n.getIdUsuario())) {
            throw new PersistenciaException("Este Item ja se encontra em estoque");
        }

        String Query = "INSERT INTO estoque(id_produto,id_linha,id_usuario, quantidade, limite_inferior, nome) values(?,?,?,?,?,?);";
        Connection Con = BaseDados.getInstancia().getConnection();
        PreparedStatement pst = Con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, n.getIdProduto());
        pst.setInt(2, n.getIdLinha());
        pst.setInt(3, n.getIdUsuario());
        pst.setInt(4, n.getQuantidade());
        pst.setInt(5, n.getLimiteInferior());
        pst.setString(6, n.getNome());
        pst.executeUpdate();

        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            n.setIdProduto(rs.getInt(1));
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        Con.close();
        Con = null;

    }

    public static boolean verificaSeItemJaFoiCadastrado(int idP, int idU) throws SQLException {
        String Query = "SELECT id_produto FROM estoque WHERE id_produto = ? AND id_usuario =? ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, idP);
        pst.setInt(2, idU);
        ResultSet rs = pst.executeQuery();
        boolean retorno = rs.next();
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return retorno;
    }

    public static List<EstoqueEntidade> ListarEstoque(int idUser) throws SQLException {
        EstoqueEntidade e = null;

        ArrayList<EstoqueEntidade> lst = new ArrayList<EstoqueEntidade>();
        String query = "SELECT * FROM estoque where id_usuario =" + idUser + " Order by nome  ;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            e = new EstoqueEntidade();
            e.setIdProduto(rs.getInt("id_produto"));
            e.setIdLinha(rs.getInt("id_linha"));
            e.setIdUsuario(rs.getInt("id_usuario"));
            e.setQuantidade(rs.getInt("quantidade"));
            e.setLimiteInferior(rs.getInt("limite_inferior"));
            e.setNome(rs.getString("nome"));
            lst.add(e);

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return lst;
    }

    public static int pegarQuantidadeDoItemNoEstoque(EstoqueEntidade e) throws SQLException {
        int qtd = -1;
        String q = "SELECT quantidade FROM estoque where id_produto = ? AND id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, e.getIdProduto());
        pst.setInt(2, e.getIdUsuario());
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            qtd = rs.getInt("quantidade");
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return qtd;
    }

    public static int pegarQuantidadeDoItemNoEstoqueParaAtualizar(int idProd, int qtd) throws SQLException {

        String q = "SELECT quantidade FROM estoque where id_produto = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idProd);
        int result = 0;
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            result = rs.getInt("quantidade");
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return result;
    }

    public static EstoqueEntidade pegarItemNoEstoque(int id_produto, int id_usuario) throws SQLException {
        EstoqueEntidade es = null;
        String q = "SELECT * FROM estoque where id_produto = ? AND id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, id_produto);
        pst.setInt(2, id_usuario);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            es = new EstoqueEntidade();
            es.setIdProduto(rs.getInt("id_produto"));
            es.setIdLinha(rs.getInt("id_linha"));
            es.setIdUsuario(rs.getInt("id_usuario"));
            es.setQuantidade(rs.getInt("quantidade"));
            es.setLimiteInferior(rs.getInt("limite_inferior"));

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return es;
    }

    public static Boolean esteProdutoEstaEmEstoque(int idProd) throws SQLException {

        String q = "SELECT * FROM estoque where id_produto = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idProd);
        ResultSet rs = pst.executeQuery();
        Boolean retorno = rs.next();
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return retorno;
    }

    public static void editarItemEstoque(EstoqueEntidade e) throws SQLException {
        String Query = "UPDATE  estoque SET quantidade = ?, limite_inferior =? where  id_produto = ? and id_usuario =?;";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, e.getQuantidade());
        pst.setInt(2, e.getLimiteInferior());
        pst.setInt(3, e.getIdProduto());
        pst.setInt(4, e.getIdUsuario());

        pst.executeUpdate();
        pst.close();
        pst = null;

    }

    public static void decrementarItemEstoque(EstoqueEntidade e) throws SQLException {
        EnviarEmail env = new EnviarEmail();
        int qtd = pegarQuantidadeDoItemNoEstoque(e);

        if (qtd == e.getLimiteInferior()) {
            e.setQuantidade(qtd);
            env.enviarEmailLimiteInferiorAtingido(e);
        }
        if (qtd < e.getLimiteInferior() && qtd != 0) {
            e.setQuantidade(qtd);
            env.enviarEmailAbaixoDoLimiteInferiorAtingido(e);
        }
        if (qtd == 0) {
            e.setQuantidade(qtd);
            env.enviarEmailProdutoZeradoNoEstoque(e);
            qtd++;
        }
        String Query = "UPDATE  estoque SET quantidade = ?  where  id_produto = ? and id_usuario =?;";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, qtd - 1);
        pst.setInt(2, e.getIdProduto());
        pst.setInt(3, e.getIdUsuario());

        pst.executeUpdate();
        pst.close();
        pst = null;

    }

//    public static void excluirItemEstoque(EstoqueEntidade e) throws SQLException {
//
//        String Query = " DELETE FROM linha WHERE id_linha = ?;";
//        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
//        pst.setString(1, idLinha);
//        pst.executeUpdate();
//
//    }
    public static void removerItemDoEstoque(String idPro, String idU) throws SQLException {
        String Query = " DELETE FROM estoque WHERE id_produto = ? AND id_usuario =?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idPro);
        pst.setString(2, idU);
        pst.executeUpdate();
        pst.close();
        pst = null;

    }

    public static Boolean existeQuantidadeSuficiente(Integer idProduto, int qtd) throws SQLException {
        Boolean temEstoque = true;
        String query = "SELECT * FROM estoque where id_produto =? ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, idProduto);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            EstoqueEntidade e = new EstoqueEntidade();
            e.setQuantidade(rs.getInt("quantidade"));
            if (qtd > e.getQuantidade()) {
                temEstoque = false;
            }
        }

        pst.close();
        pst = null;
        rs.close();
        rs = null;

        return temEstoque;
    }

    public static void atualizarItemEstoquePorVenda(ItemEntidade item, int qtdAtual) throws SQLException {

        String Query = "UPDATE  estoque SET quantidade = ?  where  id_produto = ? and id_usuario =?;";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, qtdAtual - item.getQtde());//decrementa item
        pst.setInt(2, item.getIdProduto());
        pst.setInt(3, item.getIdUsuario());

        pst.executeUpdate();
        pst.close();
        pst = null;
    }
        public static void incrementarItemEstoquePorCancelamentoVenda(ItemEntidade item, int qtdAtual) throws SQLException {

        String Query = "UPDATE  estoque SET quantidade = ?  where  id_produto = ? and id_usuario =?;";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, qtdAtual + item.getQtde());//incrementa item
        pst.setInt(2, item.getIdProduto());
        pst.setInt(3, item.getIdUsuario());

        pst.executeUpdate();
        pst.close();
        pst = null;
    }

    public static void excluirItemDoEstoque(String idProd) throws SQLException {
        String query = "DELETE FROM estoque WHERE id_produto =?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, Integer.valueOf(idProd));
        pst.executeUpdate();
        pst.close();
        pst = null;
    }

    public static Boolean verificarSeEstoqueEhDoVendedor(String idProduto, int idUsuario) throws SQLException {
        String query = "SELECT * FROM estoque WHERE id_usuario = ? AND id_produto = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, idUsuario);
        pst.setString(2, idProduto);
        ResultSet rs = pst.executeQuery();
        Boolean eMeuEstoque = rs.next();
       
        
        rs.close();
        rs = null;
        pst.close();
        pst = null;
        return eMeuEstoque;
    }

}
