/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.AcessoEntidade;
import entidade.ClienteEntidade;
import entidade.DataCriacaoEntidade;
import entidade.EnderecoEntidade;
import entidade.EstoqueEntidade;
import entidade.ProdutoEntidade;
import entidade.TelefoneEntidade;
import entidade.UsuarioEntidade;
import entidade.VendaEntidade;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.AcessoDAO;
import persistencia.ClienteDAO;
import persistencia.DataCriacaoDAO;
import persistencia.EnderecoDAO;
import persistencia.EstoqueDAO;
import persistencia.InvestimentoDAO;
import persistencia.LinhaDAO;

import persistencia.PersistenciaException;
import persistencia.ProdutoDAO;
import persistencia.TelefoneDAO;

import persistencia.UsuarioDAO;
import util.ClienteRank;
import util.ClienteRankAdicionar;
import util.EnviarEmail;
import util.PegarCoordenadasEnderecoNoGoogle;

/**
 *
 * @author joaosantos
 */
public class ControladorUsuario {

    public static void inserirVendedor(UsuarioEntidade Novo) throws SQLException, PersistenciaException, NoSuchAlgorithmException {

        UsuarioDAO.inserirVendedor(Novo);

        TelefoneEntidade tel = new TelefoneEntidade();
        EnderecoEntidade end = new EnderecoEntidade();
        AcessoEntidade acesso = new AcessoEntidade();
        DataCriacaoEntidade dtCri = new DataCriacaoEntidade();
        
        Calendar dataHoje = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String agora = s.format(dataHoje.getTime());
        
        dtCri.setIdUsuario(Novo.getIdUsuario());
        dtCri.setDataCriacao(agora);
        
        acesso.setAcessoAtual(agora);
        acesso.setUltimoAcesso(agora);
        acesso.setIdUsuario(Novo.getIdUsuario());
        
        tel.setIdUsuario(Novo.getIdUsuario());
        tel.setFixo(null);
        tel.setCelular(null);
        tel.setOutro(null);
//
        end.setLogradouro(null);
        end.setNumero("");
        end.setComplemento(null);
        end.setBairro(null);
        end.setCep(null);
        end.setCidade(0);
        end.setEstado(Short.valueOf("0"));
        end.setIdUsuario(Novo.getIdUsuario());
        ClienteEntidade cl = new ClienteEntidade();
        cl.setIdUsuario(Novo.getIdUsuario().toString());
        cl.setNome("000" + Novo.getIdUsuario() + " - Consumidor Final");
        cl.setEmail("ecl00" + Novo.getIdUsuario() + "@00microv" + Novo.getIdUsuario());
        cl.setSenha("");
        cl.setTipo(Short.valueOf("0"));
        cl.setValido(Short.valueOf("1"));
        cl.setFoto("/img/usuario_default.png");
        TelefoneDAO.inserirTelefone(tel);
        EnderecoDAO.inserirEndereco(end);
        AcessoDAO.inserirAcessoEntidade(acesso);
        DataCriacaoDAO.registrarDataCriação(dtCri);
        ControladorCliente.inserirCliente(cl, null, null);
        try {
            end = PegarCoordenadasEnderecoNoGoogle.editarEndereco(end);
        } catch (IOException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        EnviarEmail env = new EnviarEmail();
        env.enviarLinkValidacaoDeCadastro(Novo.getEmail());
    }

    public static ArrayList prepararRankingUsuariosPorOrdemDeVendasFeitas(String idUsuario) throws SQLException {

        ArrayList retornoLista = new ArrayList();
        int idUser = Integer.valueOf(idUsuario);
        List<VendaEntidade> listaVendas = ControladorVenda.pegarVendasDoUsuario(idUser);

        for (VendaEntidade v : listaVendas) {
            ClienteRank cr = new ClienteRank();
            cr.setIdCliente(v.getIdCliente());
            cr.setTotal(v.getValor().intValue());

            Boolean existe = ClienteRankAdicionar.verificarSeExisteItemNoCarrinho(cr, retornoLista);
            if (!existe) {
                retornoLista = (ArrayList) ClienteRankAdicionar.adicionarProdutoCarrinho(cr, retornoLista);
            } else {
                retornoLista = (ArrayList) ClienteRankAdicionar.editarItemNoCarrinho(cr, retornoLista);
            }
        }
        int count = 0;
        Collections.sort(retornoLista);
        Collections.reverse(retornoLista);
        List<ClienteRank> lstCr = retornoLista;

        return retornoLista;

    }

    public static void inserirProduto(ProdutoEntidade p) throws SQLException, PersistenciaException {
        ProdutoDAO.cadastrarProduto(p);
        EstoqueEntidade e = new EstoqueEntidade();
        e.setIdProduto(p.getIdProduto());
        e.setIdLinha(p.getIdLinha());
        e.setIdUsuario(p.getIdUsuario());
        e.setQuantidade(0);
        e.setLimiteInferior(0);
        e.setNome(p.getNome());
        EstoqueDAO.cadastrarItemNoEstoque(e);
    }

    public static String pegarNomeClientePeloId(int idCliente) throws SQLException {
        String nome = UsuarioDAO.pegarAtributoNomeDeUsuarioPeloIdCliente(idCliente);

        return nome;
    }

    public static UsuarioEntidade pegarUsuarioPeloSeuId(String idUsuario) throws SQLException {
        UsuarioEntidade u = new UsuarioEntidade();
        u = UsuarioDAO.pegarUsuarioPorId(idUsuario);
        return u;
    }

    public static EnderecoEntidade pegarEnderecoUsuarioPeloIdUsuario(int idUsuario) throws SQLException {
        EnderecoEntidade e = new EnderecoEntidade();
        e = EnderecoDAO.pegarEnderecoUsuarioPeloSeuIdUsuario(idUsuario);
        return e;
    }

    public static TelefoneEntidade pegarTelefoneUsuarioPeloIdUsuario(int idUsuario) throws SQLException {
        TelefoneEntidade t = new TelefoneEntidade();
        t = TelefoneDAO.pegarTelefonePeloIdCliente(idUsuario);
        return t;

    }

    public static void atualizarDadosUsuario(EnderecoEntidade e, TelefoneEntidade t, UsuarioEntidade u) throws SQLException, IOException {

        e = PegarCoordenadasEnderecoNoGoogle.editarEndereco(e);
        EnderecoDAO.atualizarEnderecoUsuario(e);
        TelefoneDAO.editarTelefones(t);
        UsuarioDAO.atualizarDadosUsuario(u);

    }

    public static List<UsuarioEntidade> pegarListaDeTodosUsuariosCadastradosNoSistema() throws SQLException {
        List<UsuarioEntidade> lst = UsuarioDAO.pegarListaDeTodosUsuariosCadastradosNoSistema();
        return lst;
    }

    public static Boolean verificarSeUsuarioPossuiLinhasCadastradas(Integer idUsuario) throws SQLException {
        Boolean existe = LinhaDAO.verificarSeUsuarioPossuiLinhasCadastradas(idUsuario);

        return existe;
    }

    public static void excluirUsuarioInativo(UsuarioEntidade u) throws SQLException {

        //escluir acesso
        AcessoDAO.excluirAcessoUsuarioInativo(u.getIdUsuario());
        //excluir tel 
        TelefoneDAO.excluirTelefoneUsuarioInativo(u.getIdUsuario());
        //excluir end
        EnderecoDAO.excluirEnderecoUsuarioInativo(u.getIdUsuario());
        //excluir usuario 
        UsuarioDAO.excluirUsuarioInativo(u.getIdUsuario());
        //excluir ckliente balcao
        ClienteDAO.excluirClienteBalcao(u.getIdUsuario());
        //excluir data_criacao
        DataCriacaoDAO.excluirRegistro(u.getIdUsuario());
        //excluir investimento
        InvestimentoDAO.excluirInvestimento(u.getIdUsuario());
                
        //notificar admin por eMail
        EnviarEmail env = new EnviarEmail();
        env.enviarEmailUsuaRioInativoExcluido(u);

    }

    public static void atualizarStatusPagamentoUsuario(UsuarioEntidade u) throws SQLException {
        UsuarioDAO.atualizarStatusPagamentoUsuario(u);

    }

    public static void VerificarVencimentoDeMensalidades() throws SQLException {
        EnviarEmail env = new EnviarEmail();
        env.enviarEmailRotinaVerificacaoMensalidade();
        List<UsuarioEntidade> lstUsuario = pegarListaDeTodosUsuariosCadastradosNoSistema();

        for (UsuarioEntidade u : lstUsuario) {
            if (u.getStatusPagamento() == 1) { // SE USUARIO NAO TA VENCIDO
                //VERIFICA SE VENCE HPOJE
                if (u.getValido() == 1) {
                    String dataIngresso = DataCriacaoDAO.pegarDataCriacao(u.getIdUsuario());
                    Calendar c = Calendar.getInstance();

                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                    String dataAtual = sf.format(c.getTime());

                    String prepara[] = dataIngresso.split("-");
                    String ano = prepara[0];
                    String mes = prepara[1];
                    String dia = prepara[2];

                    String prepara2[] = dataAtual.split("-");
                    String ano2 = prepara2[0];
                    String mes2 = prepara2[1];
                    String dia2 = prepara2[2];

                    if (dia.equals(dia2)) {

                        if (mes.equals(mes2) && ano.equals(ano2)) {
                            //FAZER NADA INSCRICAO FEITA HOJE

                        } else {
                            u.setStatusPagamento(Short.valueOf("4"));
                            atualizarStatusPagamentoUsuario(u);
                            //notificar usuario vencimento

                            env.enviarEmailNotificandoVencimentoMensalidadeUsuario(u);
//                        System.out.println("VENCIDO");
                        }

                    }
                }
            } else {//USUARIOS VENCIDOS

                //decrementar contador
                Short i = u.getStatusPagamento();
                Short um = 1;
                short atual = (short) (i - um);
                u.setStatusPagamento(atual);

                //atualizar objeto
                atualizarStatusPagamentoUsuario(u);
                //notificar email
                env.enviarEmailNotificandoVencimentoMensalidadeUsuario(u);
                if (i == 2) {
                    //desabilitar usuario
                    u.setStatusPagamento(Short.valueOf("1"));
                    u.setValido(Short.valueOf("0"));

                    //atualizar u
                    desativarUsuario(u);
                    //notificar email admin 
//                System.out.println("EMAIL CANCELDDAADO PARA " + u.getEmail());
                    env.enviarEmailUsuaRioVencidoDesativado(u);

                }
            }

        }
    }

    public static void verificarInaitividadeDaContaNegocio() throws SQLException {
        //N O T I F I C A R   A D M I N 
        EnviarEmail env = new EnviarEmail();
        env.enviarEmailRotinaVerificacaoContaInativa();
        //buscar lista de todos usuarios
        List<UsuarioEntidade> lstU = pegarListaDeTodosUsuariosCadastradosNoSistema();

        for (UsuarioEntidade u : lstU) {
            //verificar data de criacao da conta
            if (u.getValido() == 0) {
            Calendar dataAtual = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dataAtualS = sdf.format(dataAtual.getTime());
            String dataCriacao = DataCriacaoDAO.pegarDataCriacao(u.getIdUsuario());
            String dataIncrementadaNoventaDias = util.OperacoesComDatas.somarEneDiasNaDataAtual(dataCriacao, 90);// 90 dias
//            System.out.println("Criada em : " + dataCriacao);
//            System.out.println("Data Atual: " + dataAtualS);
//            System.out.println("Increment : " + dataIncrementadaNoventaDias);

                String separa[] = dataIncrementadaNoventaDias.split("-");
                int ano = Integer.parseInt(separa[0]);
                int mes = Integer.parseInt(separa[1]);
                int dia = Integer.parseInt(separa[2]);

                String separa2[] = dataAtualS.split("-");
                int ano2 = Integer.parseInt(separa2[0]);
                int mes2 = Integer.parseInt(separa2[1]);
                int dia2 = Integer.parseInt(separa2[2]);
//            
//            //verificar data de criacao da conta tem 90 dias ou mais
                if (dia <= dia2) {
                    if (mes <= mes2) {
                        if (ano <= ano2) {
                            // verificar se possui itens  cadastrados
                            Boolean temLinha = verificarSeUsuarioPossuiLinhasCadastradas(u.getIdUsuario());

                            if (!temLinha) {
//                            System.out.println("A   P   A   G   A   R ==> " + u.getNome());
                                excluirUsuarioInativo(u);
                            }

                        }
                    }

                } else if (dia >= dia2) {
                    if (mes <= mes2) {
                        if (ano <= ano2) {

                            // verificar se possui itens  cadastrados
                            Boolean temLinha = verificarSeUsuarioPossuiLinhasCadastradas(u.getIdUsuario());
//                        System.out.println("VEMNCIDO + " + temLinha);

                            if (!temLinha) {
//                            System.out.println("A   P   A   G   A   R  ==> " + u.getNome());
                                excluirUsuarioInativo(u);

                            }
                        }
                    }

                }
            }

        }
    }

//    public static void notificarMensalidadesVencidas() throws SQLException {
//        List<UsuarioEntidade> lstUs = ControladorUsuario.pegarListaUsuariosVencidos();
//        EnviarEmail env = new EnviarEmail();
//        env.enviarEmailRotinaNotificacoes();
//        for (UsuarioEntidade u : lstUs) {
//            System.out.println(u.getNome());
//
//            //decrementar contador
//            Short i = u.getStatusPagamento();
//            Short um = 1;
//            short atual = (short) (i - um);
//            u.setStatusPagamento(atual);
//
//            //atualizar objeto
//            atualizarStatusPagamentoUsuario(u);
//            //notificar email
//            env.enviarEmailNotificandoVencimentoMensalidadeUsuario(u);
//            if (i == 2) {
//                //desabilitar usuario
//                u.setStatusPagamento(Short.valueOf("1"));
//                u.setValido(Short.valueOf("0"));
//
//                //atualizar u
//                desativarUsuario(u);
//                //notificar email admin 
////                System.out.println("EMAIL CANCELDDAADO PARA " + u.getEmail());
//                env.enviarEmailUsuaRioVencidoDesativado(u);
//
//            } else {
////                System.out.println("EMAIL PARA " + u.getEmail());
//            }
//
//        }
//    }
    public static List<UsuarioEntidade> pegarListaUsuariosVencidos() throws SQLException {

        List<UsuarioEntidade> lst = UsuarioDAO.pegarListaUsuariosVencidos();

        return lst;
    }

    public static void desativarUsuario(UsuarioEntidade u) throws SQLException {
        UsuarioDAO.desativarUsuario(u);
    }

    public static int pegarTodosOsPedidosDoUsuario(int idUser) throws SQLException{
       int totalPedidos =  UsuarioDAO.pegarTotalDePedidosDoUsuario(idUser);
        return totalPedidos;
    }

    public static int pegarTodasAsVendasDoUsuario(int idUser) throws SQLException{
        int totalDeVendas = UsuarioDAO.pegarTotaldeVendasDoUsuario(idUser);
        return totalDeVendas;
    }

}
