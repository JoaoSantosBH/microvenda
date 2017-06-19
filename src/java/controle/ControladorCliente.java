/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.AcessoEntidade;
import entidade.ClienteEntidade;
import entidade.EnderecoEntidade;
import entidade.LinhaEntidade;
import entidade.TelefoneEntidade;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.AcessoDAO;
import persistencia.ClienteDAO;
import persistencia.EnderecoDAO;
import persistencia.EstoqueDAO;
import persistencia.LinhaDAO;
import persistencia.PersistenciaException;
import persistencia.ProdutoDAO;
import persistencia.TelefoneDAO;
import util.PegarCoordenadasEnderecoNoGoogle;

/**
 *
 * @author joaosantos
 */
public class ControladorCliente {
    
    public static void inserirCliente(ClienteEntidade Novo, TelefoneEntidade t, EnderecoEntidade e) throws SQLException, PersistenciaException, NoSuchAlgorithmException {
        
        int id = ClienteDAO.inserirCliente(Novo);
        // BUG SINSTRO id do cliente igual id usuario
        
        TelefoneEntidade tel = new TelefoneEntidade();
        EnderecoEntidade end = new EnderecoEntidade();
        AcessoEntidade acesso = new AcessoEntidade();
        
        Calendar dataHoje = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String agora = s.format(dataHoje.getTime());
        acesso.setAcessoAtual(agora);
        acesso.setUltimoAcesso(agora);
        acesso.setIdUsuario(Novo.getIdCliente());
        
        if (t == null) {
            tel.setIdUsuario(id);
            tel.setFixo(null);
            tel.setCelular(null);
            tel.setOutro(null);
        } else {
            tel.setIdUsuario(id);
            tel.setFixo(t.getFixo());
            tel.setCelular(t.getCelular());
            tel.setOutro(t.getOutro());
        }

//
        if (e == null) {
            end.setLogradouro("");
            end.setNumero("");
            end.setComplemento("");
            end.setBairro("");
            end.setCep("");
            end.setCidade(0);
            end.setEstado(Short.valueOf("0"));
            end.setIdUsuario(id);
        } else {
            end.setLogradouro(e.getLogradouro());
            end.setNumero(e.getNumero());
            end.setComplemento(e.getComplemento());
            end.setBairro(e.getBairro());
            end.setCep(e.getCep());
            end.setCidade(e.getCidade());
            end.setEstado(e.getEstado());
            end.setIdUsuario(id);
        }
        
        try {// Buscando coordenadas no google
            end = PegarCoordenadasEnderecoNoGoogle.editarEndereco(end);
        } catch (IOException ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TelefoneDAO.inserirTelefone(tel);
        EnderecoDAO.inserirEndereco(end);
        AcessoDAO.inserirAcessoEntidade(acesso);
        
    }
    
    public static void editarDadosDoCliente(ClienteEntidade c, TelefoneEntidade t, EnderecoEntidade e) throws SQLException {
        
        ClienteDAO.editarCliente(c);
        TelefoneDAO.editarTelefones(t);
        EnderecoDAO.editarEnderecoCliente(e);
        
    }
    
    public static List<ClienteEntidade> pegarListaDeClientesDoVendedor(int id) throws SQLException {
        
        List<ClienteEntidade> lst = ClienteDAO.ListarClientesdoVendedorParaEdicao(id);
        
        return lst;
    }
    
    public static List<ClienteEntidade> pegarListaDeClientesDoVendedorEmailTodos(int id) throws SQLException {
        
        List<ClienteEntidade> lst = ClienteDAO.ListarClientesdoVendedorParaEMailTodos(id);
        
        return lst;
    }
    
    public static List<ClienteEntidade> pegarListaDeClientesDoVendedorBackup(int id) throws SQLException {
        
        List<ClienteEntidade> lst = ClienteDAO.ListarClientesdoVendedorParaBackup(id);
        
        return lst;
    }
    
    public static ClienteEntidade pegarClientePeloId(int id) throws SQLException {
        
        ClienteEntidade c = ClienteDAO.pegarClientePeloId(id);
        
        return c;
    }
    
    public static void inserirLinhaProduto(LinhaEntidade l) throws SQLException, PersistenciaException {
        
        LinhaDAO.cadastrarLinhaProdutos(l);
    }
    
    public static void excluirLinhaProduto(String id) throws SQLException {
        // aqui que devia ter uma rotina
        LinhaDAO.excluirLinha(id);
        
    }
    
    public static void excluirProdutoERemoverDoEstoque(String idProd) throws SQLException {
        EstoqueDAO.excluirItemDoEstoque(idProd);
        ProdutoDAO.excluirProduto(idProd);
        
    }
    
    public static void excluirCliente(String idC, String idU) throws SQLException {
        ClienteDAO.excluirCliente(idC, idU);
        TelefoneDAO.excluirTelefoneCliente(idC);
        EnderecoDAO.excluirEnderecoCliente(idC);
    }
    
    public static ClienteEntidade pegarClientePeloNomeDeCliente(String nomeCliente, int idU) throws SQLException {
        ClienteEntidade c = ClienteDAO.pegarClientePeloNomeDoCliente(nomeCliente, idU);
        return c;
    }
    
    public static List<ClienteEntidade> buscarListaDeClientesQueAtendemQueryLike(String idUser, String query) throws SQLException {
        List<ClienteEntidade> lst = ClienteDAO.buscarListaDeClientesQueAtendemQueryLike(idUser, query);
        return lst;
    }
    
    public static void atualizarDadosDoCliente(EnderecoEntidade e, TelefoneEntidade t, ClienteEntidade c) throws SQLException, IOException {
        
        e = PegarCoordenadasEnderecoNoGoogle.editarEndereco(e);
        EnderecoDAO.atualizarEnderecoUsuario(e);
        TelefoneDAO.editarTelefones(t);
        ClienteDAO.atualizarDadosDoCLiente(c);
        
    }
    
}
