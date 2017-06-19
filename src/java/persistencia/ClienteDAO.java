/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import controle.ControladorCliente;
import entidade.ClienteEntidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static persistencia.UsuarioDAO.verificaEmailCadastroCliente;
import static persistencia.UsuarioDAO.verificaNomeCadastroCliente;

/**
 *
 * @author joaosantos
 */
public class ClienteDAO {

    public static int inserirCliente(ClienteEntidade novo) throws SQLException, PersistenciaException {

        if (verificaEmailCadastroCliente(novo.getEmail())) {
            throw new PersistenciaException("email ja cadastrado!");
        }
        if (verificaNomeCadastroCliente(novo.getNome())) {
            throw new PersistenciaException("nome ja cadastrado!");
        }

        String Query = "INSERT INTO cliente(id_usuario, nome, email, senha, tipo, valido,foto) values(?,?,?,?,0,1,?);";
        Connection Con = BaseDados.getInstancia().getConnection();
        PreparedStatement pst = Con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setString(1, novo.getIdUsuario());
        pst.setString(2, novo.getNome());
        pst.setString(3, novo.getEmail());
        pst.setString(4, novo.getSenha());
        pst.setString(5, novo.getFoto());
        pst.executeUpdate();

        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            novo.setIdCliente(rs.getInt(1));
        }
        int id_usuario = novo.getIdCliente();
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        Con.close();
        Con = null;

        return id_usuario;
    }

    public static Boolean verificarSeOClientePertenceAoVendedor(String idCl, int idUser) throws SQLException {

        String query = "SELECT * FROM cliente WHERE id_cliente = ? and id_usuario = ? ;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setString(1, idCl);
        pst.setInt(2, idUser);
        ResultSet rs = pst.executeQuery();
        Boolean eMeuCliente = rs.next();
        rs.close();
        rs = null;
        pst.close();
        pst = null;
        return eMeuCliente;
    }

    public static List<ClienteEntidade> ListarClientesdoVendedorParaEdicao(int id) throws SQLException {
        ClienteEntidade c = null;

        ArrayList<ClienteEntidade> lst = new ArrayList<ClienteEntidade>();
        String query = "SELECT * FROM cliente where id_usuario =" + id + " order by nome; ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            c = new ClienteEntidade();
            c.setIdCliente(rs.getInt("id_cliente"));
            c.setIdUsuario(rs.getString("id_usuario"));
            c.setNome(rs.getString("nome"));
            lst.add(c);

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return lst;
    }

    public static List<ClienteEntidade> ListarClientesdoVendedorParaBackup(int id) throws SQLException {
        ClienteEntidade c = null;

        ArrayList<ClienteEntidade> lst = new ArrayList<ClienteEntidade>();
        String query = "SELECT * FROM cliente where id_usuario =" + id + " order by nome; ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            c = new ClienteEntidade();
            c.setIdCliente(rs.getInt("id_cliente"));
            c.setIdUsuario(rs.getString("id_usuario"));
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
            c.setSenha(rs.getString("senha"));
            lst.add(c);

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return lst;
    }

    public static List<ClienteEntidade> ListarClientesdoVendedorParaEMailTodos(int id) throws SQLException {
        ClienteEntidade c = null;

        ArrayList<ClienteEntidade> lst = new ArrayList<ClienteEntidade>();
        String query = "SELECT * FROM cliente where id_usuario =" + id + " order by nome; ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            c = new ClienteEntidade();
            c.setIdCliente(rs.getInt("id_cliente"));
            c.setIdUsuario(rs.getString("id_usuario"));
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
            c.setSenha(rs.getString("senha"));
            lst.add(c);

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return lst;
    }

    public static void editarCliente(ClienteEntidade c) throws SQLException {
        String Query = "UPDATE  cliente SET nome = ?, email=?, senha=? where id_cliente = ?;";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, c.getNome());
        pst.setString(2, c.getEmail());
        pst.setString(3, c.getSenha());
        pst.setInt(4, c.getIdCliente());
        pst.executeUpdate();
        pst.close();
        pst = null;

    }

    public static ClienteEntidade pegarClientePeloId(int id) throws SQLException {

        ClienteEntidade c = null;
        String Query = "SELECT * FROM cliente WHERE id_cliente = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            c = new ClienteEntidade();
            c.setIdCliente(rs.getInt("id_cliente"));
            c.setIdUsuario(rs.getString("id_usuario"));
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
            c.setSenha(rs.getString("senha"));

        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;
        return c;
    }

    public static void excluirCliente(String idCliente, String idUsuario) throws SQLException {

        String Query = " DELETE FROM cliente WHERE id_cliente = ? AND id_usuario =?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idCliente);
        pst.setString(2, idUsuario);
        pst.executeUpdate();
        pst.close();
        pst = null;

    }

    public static ClienteEntidade pegarClientePeloNomeDoCliente(String nomeCliente, int idU) throws SQLException {
        ClienteEntidade c = new ClienteEntidade();
        String q = "SELECT * FROM cliente WHERE nome like '%" + nomeCliente + "%' AND id_usuario = ? ;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
//        pst.setString(1, nomeCliente);
        pst.setInt(1, idU);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            c.setIdCliente(rs.getInt("id_cliente"));
            c.setIdUsuario(rs.getString("id_usuario"));
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
            c.setSenha(rs.getString("senha"));
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;

        return c;
    }

    public static List<ClienteEntidade> buscarListaDeClientesQueAtendemQueryLike(String idUser, String query) throws SQLException {
        ClienteEntidade c = null;
        List<ClienteEntidade> lst = new ArrayList<>();
        String q = "SELECT * FROM cliente WHERE nome LIKE '%" + query + "%' AND id_usuario = ? ORDER BY nome; ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setString(1, idUser);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            c = new ClienteEntidade();
            c.setIdCliente(rs.getInt("id_cliente"));
            c.setIdUsuario(rs.getString("id_usuario"));
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
            c.setSenha(rs.getString("senha"));
            lst.add(c);
        }
        pst.close();
        pst = null;
        rs.close();
        rs = null;

        return lst;
    }

    public static ClienteEntidade logar(String login, String senha) throws SQLException {

        ClienteEntidade cliente = null;
        String Query = "SELECT * FROM cliente WHERE email = ? AND senha = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, login);
        pst.setString(2, senha);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            cliente = new ClienteEntidade();
            cliente.setIdCliente(rs.getInt("id_cliente"));
            cliente.setIdUsuario(rs.getString("id_usuario"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setSenha(rs.getString("senha"));
            cliente.setValido(rs.getShort("valido"));
            cliente.setTipo(rs.getShort("tipo"));

        }

        pst.close();
        pst = null;
        rs.close();
        rs = null;

        return cliente;
    }

    public static void atualizarDadosDoCLiente(ClienteEntidade c) throws SQLException {
        String q = "UPDATE cliente SET nome=?, email =?, senha = ? WHERE id_cliente = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setString(1, c.getNome());
        pst.setString(2, c.getEmail());
        pst.setString(3, c.getSenha());
        pst.setInt(4, c.getIdCliente());
        pst.executeUpdate();
        pst.close();
        pst = null;
    }

    public static void excluirClienteBalcao(Integer idUsuario) throws SQLException{
        String q = "DELETE FROM cliente Where id_usuario = ? ;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idUsuario);
        pst.executeUpdate();
        pst.close();
        pst = null;
    }
}
