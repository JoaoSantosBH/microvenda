/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.EnderecoEntidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaosantos
 */
public class EnderecoDAO {

    public static void inserirEndereco(EnderecoEntidade e) throws SQLException {
        String Query = "INSERT INTO endereco(id_usuario, logradouro, numero, complemento, bairro, cep, cidade, estado,  coordenada) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, e.getIdUsuario());
        pst.setString(2, e.getLogradouro());
        pst.setString(3, e.getNumero());
        pst.setString(4, e.getComplemento());
        pst.setString(5, e.getBairro());
        pst.setString(6, e.getCep());
        pst.setInt(7, e.getCidade());
        pst.setInt(8, e.getEstado());
        pst.setString(9, e.getCoordenada());
        pst.executeUpdate();
        pst.close();
        pst = null;

    }

    public static EnderecoEntidade pegarEnderecoClientePeloId(String id) throws SQLException {
        EnderecoEntidade e = null;
        String Query = "SELECT * FROM endereco WHERE id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            e = new EnderecoEntidade();

            e.setLogradouro(rs.getString("logradouro"));
            e.setNumero(rs.getString("numero"));
            e.setComplemento(rs.getString("complemento"));
            e.setBairro(rs.getString("bairro"));
            e.setCep(rs.getString("cep"));
            e.setCidade(rs.getInt("cidade"));
            e.setEstado(rs.getShort("estado"));
            e.setCoordenada(rs.getString("coordenada"));

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return e;
    }

//    public static void editarEndereco(EnderecoEntidade end) throws SQLException {
//        String Query = "UPDATE  endereco SET logradouro = ?, numero=?, complemento=?, bairro=?, cep=?,  cidade=?,estado=?, coordenada=?  where id_usuario = ?;";
//
//        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
//        pst.setString(1, end.getLogradouro());
//        pst.setString(2, end.getNumero());
//        pst.setString(3, end.getComplemento());
//        pst.setString(4, end.getBairro());
//        pst.setString(5, end.getCep());
//        pst.setInt(6, end.getCidade());
//        pst.setInt(7, end.getEstado());
//        pst.setString(8, end.getCoordenada());
//        pst.setInt(9, end.getIdUsuario());
//       
//
//        pst.executeUpdate();
//
//    }
    public static void editarEnderecoCliente(EnderecoEntidade end) throws SQLException {
        String Query = "UPDATE  endereco SET logradouro = ?, numero=?, complemento=?, bairro=?, cep=?,  coordenada=?  where id_usuario = ?;";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, end.getLogradouro());
        pst.setString(2, end.getNumero());
        pst.setString(3, end.getComplemento());
        pst.setString(4, end.getBairro());
        pst.setString(5, end.getCep());

        pst.setString(6, end.getCoordenada());
        pst.setInt(7, end.getIdUsuario());
        pst.executeUpdate();
        pst.close();
        pst = null;
    }

//    public static Integer pegarCidadeUsuario(String idUser) throws SQLException {
//        Integer cidade = null;
//        String Query = "SELECT cidade FROM endereco WHERE id_usuario = ?;";
//        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
//        pst.setString(1, idUser);
//        ResultSet rs = pst.executeQuery();
//        if (rs.next()) {
//            cidade = rs.getInt("cidade");
//        }
//
//        return cidade;
//    }
//    public static List<EnderecoEntidade> pegarListaEnderecosPorCidadeBairro(Integer cidadeUser, String bairro) throws SQLException {
//        List<EnderecoEntidade> lst = new ArrayList<EnderecoEntidade>();
//        EnderecoEntidade e = null;
//        String Query = "SELECT * FROM endereco WHERE cidade = ? and bairro = ?;";
//        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
//        pst.setInt(1, cidadeUser);
//        pst.setString(2, bairro);
//        ResultSet rs = pst.executeQuery();
//        while (rs.next()) {
//            e = new EnderecoEntidade();
//
//            e.setIdLogradouro(rs.getInt("id_logradouro"));
//            e.setIdUsuario(rs.getInt("id_usuario"));
//            e.setLogradouro(rs.getString("logradouro"));
//            e.setNumero(rs.getInt("numero"));
//            e.setComplemento(rs.getString("complemento"));
//            e.setBairro(rs.getString("bairro"));
//            e.setCep(rs.getString("cep"));
//            e.setUf(rs.getInt("uf"));
//            e.setCidade(rs.getInt("cidade"));
//
//            lst.add(e);
//        }
//
//        return lst;
//    }
//    public static String pegarBairroUsuario(String idUser) throws SQLException {
//        String bairro = "";
//        String Query = "SELECT bairro FROM endereco WHERE id_usuario = ?";
//        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
//        pst.setString(1, idUser);
//        ResultSet rs = pst.executeQuery();
//        if (rs.next()) {
//            bairro = rs.getString("bairro");
//        }
//
//        return bairro;
//    }
//
//    public static String pegarCoordenadaUsuario(String idUsu) throws SQLException {
//        String cord = "";
//        String Query = "SELECT coordenada FROM endereco WHERE id_usuario = ?;";
//        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
//        pst.setString(1, idUsu);
//        ResultSet rs = pst.executeQuery();
//        if (rs.next()) {
//            cord = rs.getString("coordenada");
//        }
//
//        return cord;
//
//    }
//
//    public static String pegarNomeCidadePeloId(Integer cidade) throws SQLException{
//        String result ="";
//        String Query = "SELECT nome FROM tb_cidades WHERE id =?;";
//        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
//        pst.setInt(1, cidade);
//        ResultSet rs = pst.executeQuery();
//        if(rs.next()){
//            
//            result  = rs.getString("nome");
//            
//        }
//        
//        
//        return result;
//    }
//
//    public static String pegarNomeEstadoPeloId(Integer uf) throws SQLException{
//        String estado = "";
//        String Query = "SELECT nome FROM tb_estados WHERE id = ?;";
//        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
//        pst.setInt(1, uf);
//        ResultSet rs = pst.executeQuery();
//        if(rs.next()){
//            estado = rs.getString("nome");
//        }
//        
//        return estado;
//        
//    }
    public static String pegarNomeCidadePeloId(Integer cidade) throws SQLException {
        String result = "";
        String Query = "SELECT nome FROM tb_cidades WHERE id =?;";
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

    public static String pegarNomeEstadoPeloId(Integer uf) throws SQLException {
        String estado = "";
        String Query = "SELECT nome FROM tb_estados WHERE id = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, uf);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            estado = rs.getString("nome");
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return estado;

    }

    public static void excluirEnderecoCliente(String idC) throws SQLException {
        String Query = " DELETE FROM endereco WHERE id_usuario = ? ;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idC);

        pst.executeUpdate();
        pst.close();
        pst = null;

    }

    public static EnderecoEntidade pegarEnderecoUsuarioPeloSeuIdUsuario(int idUsuario) throws SQLException{
        EnderecoEntidade e = new EnderecoEntidade();
        String q = "SELECT * FROM endereco WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idUsuario);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            e.setIdEndereco(rs.getInt("id_endereco"));
            e.setIdUsuario(rs.getInt("id_usuario"));
            e.setLogradouro(rs.getString("logradouro"));
            e.setNumero(rs.getString("numero"));
            e.setComplemento(rs.getString("complemento"));
            e.setBairro(rs.getString("bairro"));
            e.setCep(rs.getString("cep"));
            e.setCidade(rs.getInt("cidade"));
            e.setEstado(rs.getShort("estado"));
            e.setCoordenada(rs.getString("coordenada"));
        }
        
        
        pst.close();
        rs.close();
        pst =null;
        rs = null;
        return e;
    }

    public static void atualizarEnderecoUsuario(EnderecoEntidade e) throws SQLException{
        
        String q = "UPDATE endereco SET logradouro =?, numero = ?, complemento = ?, bairro = ?, cep = ?, cidade = ?, estado = ?, coordenada = ? WHERE id_usuario = ? ;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setString(1, e.getLogradouro());
        pst.setString(2, e.getNumero());
        pst.setString(3, e.getComplemento());
        pst.setString(4, e.getBairro());
        pst.setString(5, e.getCep());
        pst.setInt(6, e.getCidade());
        pst.setShort(7, e.getEstado());
        pst.setString(8, e.getCoordenada());
        pst.setInt(9, e.getIdUsuario());
        pst.executeUpdate();
        pst.close();
        pst = null;
        
    }

    public static void excluirEnderecoUsuarioInativo(Integer idUsuario) throws SQLException{
        String q = "DELETE FROM endereco WHERE id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idUsuario);
        pst.executeUpdate();
        pst.close();
        pst = null;
        
    }

}
