/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.UsuarioEntidade;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaomarcelo.ms@gmail.com
 */
public class UsuarioDAO {

    public static void inserirVendedor(UsuarioEntidade novo) throws SQLException, PersistenciaException {

        if (verificaEmailCadastro(novo.getEmail())) {
            throw new PersistenciaException("email ja cadastrado!");
        }
        if (verificaNomeCadastro(novo.getNome())) {
            throw new PersistenciaException("nome ja cadastrado!");
        }

        String Query = "INSERT INTO usuario(nome, id_telefone, id_endereco, email, senha,tipo, valido, status_pagamento,foto) values(?,0,0,?,?,1,0,1,'/img');";
        Connection Con = BaseDados.getInstancia().getConnection();
        PreparedStatement pst = Con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setString(1, novo.getNome());
        pst.setString(2, novo.getEmail());
        pst.setString(3, novo.getSenha());
        pst.executeUpdate();

        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            novo.setIdUsuario(rs.getInt(1));
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        Con.close();
        Con = null;

    }

    public static boolean verificaEmailCadastro(String Email) throws SQLException {
        String Query = "SELECT id_usuario FROM usuario WHERE email = ? ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, Email);
        ResultSet rs = pst.executeQuery();
        Boolean result = rs.next();
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return result;

    }

    public static boolean verificaNomeCadastro(String Nome) throws SQLException {
        String Query = "SELECT id_usuario FROM usuario WHERE nome = ? ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, Nome);
        ResultSet rs = pst.executeQuery();
        boolean esiste = rs.next();
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return esiste;
    }

    public static boolean verificaEmailCadastroCliente(String Email) throws SQLException {
        String Query = "SELECT id_usuario FROM cliente WHERE email = ? ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, Email);
        ResultSet rs = pst.executeQuery();
        boolean esiste = rs.next();
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return esiste;

    }

    public static boolean verificaNomeCadastroCliente(String Nome) throws SQLException {
        String Query = "SELECT id_usuario FROM cliente WHERE nome = ? ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, Nome);
        ResultSet rs = pst.executeQuery();
        boolean esiste = rs.next();
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return esiste;
    }
//    public static boolean verificaSePossuiReserva(String id) throws SQLException {
//        String Query = "SELECT * FROM usuario WHERE id_usuario = ? and reserva = 1";
//        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
//        pst.setString(1, id);
//        ResultSet rs = pst.executeQuery();
//        return rs.next();
//    }

    public static UsuarioEntidade logar(String login, String senha) throws SQLException {

        UsuarioEntidade U = null;
        String Query = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, login);
        pst.setString(2, senha);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            U = new UsuarioEntidade();
            U.setIdUsuario(rs.getInt("id_usuario"));
            U.setNome(rs.getString("nome"));
            U.setIdTelefone(rs.getInt("id_telefone"));
            U.setIdEndereco(rs.getInt("id_endereco"));
            U.setEmail(rs.getString("email"));
            U.setSenha(""); //Não guardar a senha por questão de segurança.
            U.setValido(rs.getShort("valido"));
            U.setStatusPagamento(rs.getShort("status_pagamento"));
            U.setTipo(rs.getShort("tipo"));
            U.setFoto(rs.getString("foto"));

            U.setUltimoAcesso(rs.getDate("ultimo_acesso"));

        }

        pst.close();
        pst = null;
        rs.close();
        rs = null;

        return U;
    }

    public static void validarUsuarioPorLinkEmail(String login) throws SQLException {

        String Query = "UPDATE usuario SET valido = 1 where email = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, login);

        pst.executeUpdate();
        pst.close();
        pst = null;

    }

    public static int pegarAtributoValidadoDeUsuario(String Email) throws SQLException {
        String Query = "SELECT valido FROM usuario WHERE email = ? ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, Email);
        ResultSet rs = pst.executeQuery();
        int saida = 0;
        if (rs.next()) {
            saida = rs.getInt("valido");
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return saida;

    }

    public static String pegarAtributoNomeDeUsuario(String Email) throws SQLException {
        String Query = "SELECT nome FROM usuario WHERE email = ? ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, Email);
        ResultSet rs = pst.executeQuery();
        String saida = "";
        if (rs.next()) {
            saida = rs.getString("nome");
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return saida;

    }

    public static String pegarAtributoNomeDeUsuarioPeloId(String id) throws SQLException {
        String Query = "SELECT nome FROM usuario WHERE id_usuario = ? ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();
        String saida = "";
        if (rs.next()) {
            saida = rs.getString("nome");
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return saida;

    }

    public static String pegarAtributoFotoDeUsuario(String id) throws SQLException {
        String Query = "SELECT foto FROM usuario WHERE id_usuario = ? ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();
        String saida = "";
        if (rs.next()) {
            saida = rs.getString("foto");
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return saida;

    }

    public static int pegarIdPorEmail(String Email) throws SQLException {
        String Query = "SELECT id_usuario FROM usuario WHERE email = ? ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, Email);
        ResultSet rs = pst.executeQuery();
        int saida = 0;
        if (rs.next()) {
            saida = rs.getInt("id_usuario");
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return saida;

    }

    public static UsuarioEntidade pegarUsuarioPorId(String id) throws SQLException {
        UsuarioEntidade u = new UsuarioEntidade();
        String Query = "SELECT * FROM usuario WHERE id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            u.setIdUsuario(rs.getInt("id_usuario"));
            u.setIdTelefone(rs.getInt("id_telefone"));
            u.setIdEndereco(rs.getInt("id_endereco"));
            u.setNome(rs.getString("nome"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return u;
    }

//    public static void marcarReservaParaUsuario(String id) throws SQLException {
//
//        String Query = "UPDATE usuario SET reserva = 1 where id_usuario = ?";
//        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
//        pst.setString(1, id);
//        pst.executeUpdate();
//
//    }
//
//    public static void desmarcarReservaParaUsuario(String id) throws SQLException {
//        String Query = "UPDATE usuario SET reserva = 0 where id_usuario = ?";
//        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
//        pst.setString(1, id);
//        pst.executeUpdate();
//
//    }
    public static String pegarAtributoEmailUsuarioPeloId(String idUsuarioTem) throws SQLException {
        String email = "";
        String Query = "SELECT email FROM usuario where id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idUsuarioTem);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            email = rs.getString("email");
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return email;

    }

    public static void cadastrarFotoUsuario(int idUsuario, String foto, String nomeAdmin, String emailAdmin) throws SQLException {
        String Query = "UPDATE usuario set foto = ? where id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, foto);
        pst.setInt(2, idUsuario);
        pst.executeUpdate();
        pst.close();
        pst = null;
    }

    public static void redefinirSenhaUsuario(String email, String senha) throws SQLException {
        String Query = "UPDATE usuario set senha=? where email = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, senha);
        pst.setString(2, email);
        pst.executeUpdate();
        pst.close();
        pst = null;
    }

    public static String pegarPatrimoniosPeloIdUsuario(int idUsuario) throws SQLException {

        String titulo = "";
        String Query = "SELECT titulo FROM patrimonio WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, idUsuario);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            titulo = rs.getString("titulo");
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return titulo;
    }

    private static String pegarSenhaUsuarioPorEmail(String facebookEmail) throws SQLException {
        String senha = "";
        String Query = "SELECT senha FROM usuario WHERE email = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, facebookEmail);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            senha = rs.getString("senha");
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return senha;
    }

    public static void desativarPerfilUsuario(String idUsu) throws SQLException {
        String Query = "UPDATE usuario SET valido =0 WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idUsu);
        pst.executeUpdate();
        pst.close();
        pst = null;
    }

    public static String pegarAtributoSenhaUsuario(String idUsuario) throws SQLException {
        String retorno = "";
        String Query = "SELECT senha FROM usuario WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idUsuario);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            retorno = rs.getString(1);
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return retorno;
    }

    public static String pegarAtributoNomeDeUsuarioPeloIdCliente(int idCliente) throws SQLException {
        String nome = "";
        String query = "SELECT * FROM cliente WHERE id_cliente = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, idCliente);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            nome = rs.getString("nome");
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return nome;
    }

    public static void atualizarDadosUsuario(UsuarioEntidade u) throws SQLException {
        String q = "UPDATE usuario SET nome=?, email =?, senha = ? WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setString(1, u.getNome());
        pst.setString(2, u.getEmail());
        pst.setString(3, u.getSenha());
        pst.setInt(4, u.getIdUsuario());
        pst.executeUpdate();
        pst.close();
        pst = null;

    }

    public static List<UsuarioEntidade> pegarListaDeTodosUsuariosCadastradosNoSistema() throws SQLException {
        List<UsuarioEntidade> lst = new ArrayList<>();
        UsuarioEntidade u = null;
        String q = "SELECT * FROM usuario ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            u = new UsuarioEntidade();
            u.setIdUsuario(rs.getInt("id_usuario"));
            u.setNome(rs.getString("nome"));
            u.setIdTelefone(rs.getInt("id_telefone"));
            u.setIdEndereco(rs.getInt("id_endereco"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            u.setTipo(rs.getShort("tipo"));
            u.setValido(rs.getShort("valido"));
            u.setStatusPagamento(rs.getShort("status_pagamento"));
            u.setFoto(rs.getString("foto"));
            u.setUltimoAcesso(rs.getDate("ultimo_acesso"));
            lst.add(u);
        }
        rs.close();
        rs = null;
        pst.close();
        pst = null;
        return lst;
    }

    public static void excluirUsuarioInativo(Integer idUsuario) throws SQLException {
        String q = "DELETE FROM usuario WHERE id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idUsuario);
        pst.executeUpdate();
        pst.close();
        pst = null;
    }

    public static void atualizarStatusPagamentoUsuario(UsuarioEntidade u) throws SQLException {
        String q = "UPDATE usuario SET status_pagamento = ?  WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setShort(1, u.getStatusPagamento());
        pst.setInt(2, u.getIdUsuario());
        pst.executeUpdate();
        pst.close();
        pst = null;

    }

    public static List<UsuarioEntidade> pegarListaUsuariosVencidos() throws SQLException {
        List<UsuarioEntidade> lst = new ArrayList<>();
        UsuarioEntidade u = null;
        String q = "SELECT * FROM usuario WHERE status_pagamento != 1;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            u = new UsuarioEntidade();
            u.setIdUsuario(rs.getInt("id_usuario"));
            u.setNome(rs.getString("nome"));
            u.setIdTelefone(rs.getInt("id_telefone"));
            u.setIdEndereco(rs.getInt("id_endereco"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            u.setTipo(rs.getShort("tipo"));
            u.setValido(rs.getShort("valido"));
            u.setStatusPagamento(rs.getShort("status_pagamento"));
            u.setFoto(rs.getString("foto"));
            u.setUltimoAcesso(rs.getDate("ultimo_acesso"));
            lst.add(u);
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;

        return lst;
    }

    public static void desativarUsuario(UsuarioEntidade u) throws SQLException {
        String q = "UPDATE usuario SET valido = ?, status_pagamento =? WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setShort(1, u.getValido());
        pst.setShort(2, u.getStatusPagamento());
        pst.setInt(3, u.getIdUsuario());
        pst.executeUpdate();
        pst.close();
        pst = null;

    }

    public static int pegarTotalDePedidosDoUsuario(int idUser) throws SQLException {
        int totalP_edidos = 0;
        String q = "SELECT COUNT(*) FROM pedido WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idUser);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            totalP_edidos = rs.getInt(1);
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return totalP_edidos;
    }

    public static int pegarTotaldeVendasDoUsuario(int idUser) throws SQLException {
        int totalVendas = 0;
        String q = "SELECT COUNT(*) FROM venda WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idUser);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            totalVendas = rs.getInt(1);
        }
        
        rs.close();
        rs = null;
        pst.close();
        pst = null;
        return totalVendas;
    }

}
