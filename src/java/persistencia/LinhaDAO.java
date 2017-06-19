/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.LinhaEntidade;
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
public class LinhaDAO {

    public static void cadastrarLinhaProdutos(LinhaEntidade linha) throws SQLException, PersistenciaException {

        if (verificaSeLinhaJaExiste(linha.getNome(), linha.getIdUsuario())) {
            throw new PersistenciaException("Esta linha ja foi cadastrada!");
        }

        String Query = "INSERT INTO linha(id_usuario, nome) values(?,?);";
        Connection Con = BaseDados.getInstancia().getConnection();
        PreparedStatement pst = Con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, linha.getIdUsuario());
        pst.setString(2, linha.getNome());

        pst.executeUpdate();

        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            linha.setIdLinha(rs.getInt(1));
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        Con.close();
        Con = null;
      
    }

    public static boolean verificaSeLinhaJaExiste(String Nome, int idUser) throws SQLException {
        String Query = "SELECT id_usuario FROM linha WHERE nome = ? AND id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, Nome);
        pst.setInt(2, idUser);
        ResultSet rs = pst.executeQuery();
        boolean retorno = rs.next();
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return retorno;
    }

    public static List<LinhaEntidade> ListarLinhasCadastradas(int id) throws SQLException {
        LinhaEntidade l = null;

        ArrayList<LinhaEntidade> lst = new ArrayList<LinhaEntidade>();
        String query = "SELECT * FROM linha where id_usuario =" + id + "; ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            l = new LinhaEntidade();
            l.setIdLinha(rs.getInt("id_linha"));
            l.setIdUsuario(rs.getInt("id_usuario"));
            l.setNome(rs.getString("nome"));
            lst.add(l);

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return lst;
    }

    public static LinhaEntidade pegarLinhaPeloId(int id) throws SQLException {

        LinhaEntidade l = null;
        String Query = "SELECT * FROM linha WHERE id_linha = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            l = new LinhaEntidade();
            l.setIdLinha(rs.getInt("id_linha"));
            l.setIdUsuario(rs.getInt("id_usuario"));
            l.setNome(rs.getString("nome"));

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return l;
    }

    public static void editarLinhaDeProdutosCadastrada(LinhaEntidade l) throws SQLException {
        String Query = "UPDATE  linha SET nome = ? where  id_linha = ?;";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, l.getNome());
        pst.setInt(2, l.getIdLinha());

        pst.executeUpdate();
        pst.close();
        pst = null;
    }

    public static void excluirLinha(String idLinha) throws SQLException {

        String Query = " DELETE FROM linha WHERE id_linha = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idLinha);
        pst.executeUpdate();
        pst.close();
        pst = null;

    }
    
    public static Boolean verificarSeListaEhDoVendedor(String idLinha, int idUsuario) throws SQLException{
        
        String query = "SELECT * FROM linha WHERE id_linha = ? AND id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setString(1, idLinha);
        pst.setInt(2, idUsuario);
        ResultSet rs = pst.executeQuery();
        Boolean eMinhaLinha = rs.next();
        
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return eMinhaLinha;
    }

    public static Boolean verificarSeUsuarioPossuiLinhasCadastradas(Integer idUsuario) throws SQLException {
        Boolean existe = false;
        String q = "SELECT * FROM linha WHERE id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idUsuario);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            existe = true;
        }
        
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return existe;
    }
    
}
