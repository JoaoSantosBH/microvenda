package util;

import entidade.ClienteEntidade;
import entidade.ConfigServidorEmailEntidade;
import entidade.EstoqueEntidade;
import entidade.ItemEntidade;
import entidade.PedidoEntidade;
import entidade.ProdutoEntidade;

import entidade.UsuarioEntidade;
import entidade.VendaEntidade;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import persistencia.ClienteDAO;

import persistencia.ConfigServidorEmailDAO;
import persistencia.PedidoDAO;
import persistencia.ProdutoDAO;
import persistencia.UsuarioDAO;

/**
 *
 * @author joaosantos
 */
public class EnviarEmail {

    public void enviarLinkValidacaoDeCadastro(String email) throws SQLException, NoSuchAlgorithmException {
        String id_config = "1";
        Integer ID = UsuarioDAO.pegarIdPorEmail(email);
        String nome = UsuarioDAO.pegarAtributoNomeDeUsuarioPeloId(ID.toString());

        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        MD5 md5 = new MD5();
        String hash = md5.criarMD5ParaEnviarLink(ID.toString());//cria hash para id  do usuário 

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:white; color:black; width:100%; height:100px;'>"
                + "<p>Prezado " + nome + ", por favor clique no link ao lado, para validar o seu cadastro em nosso sistema : "
                //                + "<a href='http://localhost:8084/D2D/bootstrap/pages/validar_cadastro.jsp?hash=" + hash + "&e-mail=" + email + "'>Validar meu Cadastro</a>"
                //                + "<a href='http://192.168.25.32:8084/D2D/bootstrap/pages/validar_cadastro.jsp?hash=" + hash + "&e-mail=" + email + "'>Validar meu Cadastro</a>"
                + "<a href='http://www.microvenda.com.br/bootstrap/pages/validar_cadastro.jsp?hash=" + hash + "&e-mail=" + email + "'>Validar meu Cadastro</a>"
                + "</p>"
                + "</div>"
                + "MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(email, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailLimiteInferiorAtingido(EstoqueEntidade es) throws SQLException {

        ProdutoEntidade p = ProdutoDAO.pegarProdutoPeloId(es.getIdProduto());
        UsuarioEntidade u = UsuarioDAO.pegarUsuarioPorId(es.getIdUsuario().toString());
        String nomeProd = p.getNome();

        String id_config = "2";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Olá " + u.getNome() + ", o produto : <h1>" + p.getNome() + " </h1> Alcançou seu limite inferior no estoque."
                + "<br>Favor providenciar mais com o fornecedor do Produto"
                + "<br>Existem  apenas  <h1>" + es.getQuantidade() + "</h1> em estoque!!!"
                + "</div>"
                + "MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(u.getEmail(), "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailAbaixoDoLimiteInferiorAtingido(EstoqueEntidade es) throws SQLException {

        ProdutoEntidade p = ProdutoDAO.pegarProdutoPeloId(es.getIdProduto());
        UsuarioEntidade u = UsuarioDAO.pegarUsuarioPorId(es.getIdUsuario().toString());
        String nomeProd = p.getNome();

        String id_config = "3";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Olá " + u.getNome() + ", o produto : <h1>" + p.getNome() + "</h1>  Está abaixo de seu limite inferior no estoque."
                + "<br>Favor providenciar mais com o fornecedor"
                + "<br>Existem  apenas  <h1>" + es.getQuantidade() + "</h1> em estoque!!!"
                + "</div>"
                + "MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(u.getEmail(), "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailProdutoZeradoNoEstoque(EstoqueEntidade es) throws SQLException {

        ProdutoEntidade p = ProdutoDAO.pegarProdutoPeloId(es.getIdProduto());
        UsuarioEntidade u = UsuarioDAO.pegarUsuarioPorId(es.getIdUsuario().toString());
        String nomeProd = p.getNome();

        String id_config = "4";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Olá " + u.getNome() + ", o produto : <h1>" + p.getNome() + " </h1> Está com ZERO itens no estoque."
                + "<br>Favor providenciar mais com o fornecedor !"
                + "<br>Quantidade =  <h1>" + es.getQuantidade() + "</h1> em estoque!!!"
                + "</div>"
                + "MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(u.getEmail(), "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailConfirmacaoDeVenda(VendaEntidade v) throws SQLException {

        PedidoEntidade ped = PedidoDAO.pegarPedidoPeloNumPedido(v.getNumPedido());
        String itens = "";
        UsuarioEntidade u = UsuarioDAO.pegarUsuarioPorId(v.getIdUsuario().toString());
        ClienteEntidade cl = ClienteDAO.pegarClientePeloId(v.getIdCliente());
        List<ItemEntidade> ls = controle.ControladorPedido.listarItensDoPedidoPeloNumPed(v.getNumPedido());

        for (ItemEntidade it : ls) {

            String nomeItem = it.getNome();
            int quantidade = it.getQtde();
            itens += " . " + nomeItem + "  -  valor = " + it.getValorVenda() + " - quantidade  " + quantidade + " itens </br>";
            itens += "\n";

        }
        String dataVenda = fronteira.FormataDataHtml.formatarDataParaDisplay(v.getData());
        String id_config = "20";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject() + " pedido N " + v.getNumPedido());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "<div class='panel-heading'>"
                + "<div>Olá  " + cl.getNome() + " , sua compra foi efetuada com sucesso!</div>"
                + "</div>"
                + "<div class='panel-body'>"
                + "Pedido Numero: " + v.getNumPedido()
                + "<div class='row show-grid'> "
                + "<div class='col-md-6'><label>Data</label></br>" + dataVenda + "</div>"
                + "<div class='col-md-6'><label>Valor</label></br>R$ " + v.getValor() + "</div>"
                + "</div>"
                + "<div class='table-responsive'>"
                + "<table class='table table-striped table-bordered table-hover'>"
                + "<thead>"
                + "<tr>"
                + "<th>itens</th>"
                + "</tr>"
                + " </thead>"
                + "<tbody>"
                + "<tr>"
                + "<td style='text-align: center;'>" + itens + "</td> "
                + "</tr>"
                + "</tbody>"
                + "</table>"
                + "Agradecemos pela sua preferência!"
                + " </div>"
                + "</div>"
                + "</div>"
                + "MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>"
        );

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();

        map.put(u.getEmail(), u.getNome());
        map.put(cl.getEmail(), cl.getNome());
        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void enviarEmailConfirmacaoDeVendaApagada(VendaEntidade v) throws SQLException {

        PedidoEntidade ped = PedidoDAO.pegarPedidoPeloNumPedido(v.getNumPedido());
        String itens = "";
        UsuarioEntidade u = UsuarioDAO.pegarUsuarioPorId(v.getIdUsuario().toString());
        ClienteEntidade cl = ClienteDAO.pegarClientePeloId(v.getIdCliente());
        List<ItemEntidade> ls = controle.ControladorPedido.listarItensDoPedidoPeloNumPed(v.getNumPedido());

        for (ItemEntidade it : ls) {

            String nomeItem = it.getNome();
            int quantidade = it.getQtde();
            itens += " . " + nomeItem + "  -  valor = " + it.getValorVenda() + " - quantidade  " + quantidade + " itens </br>";
            itens += "\n";

        }
        String dataVenda = fronteira.FormataDataHtml.formatarDataParaDisplay(v.getData());
        String id_config = "26";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject() + " pedido N " + v.getNumPedido());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "<div class='panel-heading'>"
                + "<div>Olá  " + cl.getNome() + " , sua compra foi efetuada com sucesso!</div>"
                + "</div>"
                + "<div class='panel-body'>"
                + "Pedido Numero: " + v.getNumPedido()
                + "<div class='row show-grid'> "
                + "<div class='col-md-6'><label>Data</label></br>" + dataVenda + "</div>"
                + "<div class='col-md-6'><label>Valor</label></br>R$ " + v.getValor() + "</div>"
                + "</div>"
                + "<div class='table-responsive'>"
                + "<table class='table table-striped table-bordered table-hover'>"
                + "<thead>"
                + "<tr>"
                + "<th>itens</th>"
                + "</tr>"
                + " </thead>"
                + "<tbody>"
                + "<tr>"
                + "<td style='text-align: center;'>" + itens + "</td> "
                + "</tr>"
                + "</tbody>"
                + "</table>"
                + "Agradecemos pela sua preferência!"
                + " </div>"
                + "</div>"
                + "</div>"
                + "MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>"
        );

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();

        map.put(u.getEmail(), u.getNome());
        map.put(cl.getEmail(), cl.getNome());
        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void enviarEmailNovoInscritoNoSistema() throws SQLException {

        String id_config = "21";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "<div>Olá  Admin microVenda , um novo usuário acaba de se cadastrar</div>"
                + "</div>"
                + "MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>"
        );

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();

        map.put("admin@seudominio.com.br", "email admin");
        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void enviarEmailParaUsuarioEspecifico(ClienteEntidade cl, UsuarioEntidade u, String subject, String mensagem) throws SQLException {

        String id_config = "22";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(subject);
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "<div class='panel-body' style='background-color:grey;'>"
                + "<h2>Olá " + cl.getNome() + "</h2> </br>"
                + " </div>"
                + mensagem
                + "</div>"
                + "<h2>Att..  " + u.getNome()
                + "</div>"
                + "microVenda</h2>     "
                + "</br>"
                + "MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>"
        );

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();

        map.put(u.getEmail(), u.getNome());
        map.put(cl.getEmail(), cl.getNome());
        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void enviarEmailParaTodosUsuarios(List<ClienteEntidade> ls, UsuarioEntidade u, String subject, String mensagem) throws SQLException {

        String id_config = "23";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(subject);
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "<div class='panel-body' style='background-color:grey;'>"
                + "<h2>Prezado Usuário microVenda </h2> </br>"
                + " </div>"
                + mensagem
                + "</div>"
                + "<h2>Att..  " + u.getNome()
                + "</div>"
                + "microVenda</h2>     "
                + "</br>"
                + "MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>"
        );

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(u.getEmail(), u.getNome());

        for (ClienteEntidade cl : ls) {
            map.put(cl.getEmail(), cl.getNome());

        }

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void enviarEmailCLienteParaVendedorEspecifico(ClienteEntidade cl, UsuarioEntidade u, String subject, String mensagem) throws SQLException {

        String id_config = "22";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(subject);
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "<div class='panel-body' style='background-color:grey;'>"
                + "<h2>Olá " + u.getNome() + "</h2> </br>"
                + " </div>"
                + mensagem
                + "</div>"
                + "<h2>Att..  " + cl.getNome()
                + "</div>"
                + "microVenda</h2>     "
                + "</br>"
                + "MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>"
        );

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();

        map.put(cl.getEmail(), cl.getNome());
        map.put(u.getEmail(), u.getNome());
        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    void enviarEmailNovoPedidoDeCliente(UsuarioEntidade u) throws SQLException {
        String id_config = "24";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "<div>Olá  " + u.getNome() + ", um novo pedido foi feito para você em nosso sitema</div>"
                + "<a href='http://www.microvenda.com.br/'>microVenda</a>"
                + "</div>"
                + "MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>"
        );

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();

        map.put(u.getEmail(), "email gmail");
        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    void enviarEmailEdicaoCadastroDeCliente(UsuarioEntidade u, ClienteEntidade cli) throws SQLException {
        String id_config = "25";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "<div>Olá  " + u.getNome() + ", o cliente " + cli.getNome() + " alterou os seus dados cadastrais em nosso sitema, acesse o sistema e confira </div>"
                + "<a href='http://www.microvenda.com.br/'>microVenda</a>"
                + "</div>"
                + "MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>"
        );

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();

        map.put(u.getEmail(), "email gmail");
        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void enviarEmailUsuaRioInativoExcluido(UsuarioEntidade u) throws SQLException {

        String id_config = "27";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "<div>Olá  Admin microVenda , um usuário inativo  acaba de ser excluido do Sistema</div>"
                + "</div>"
                + "nome:  " + u.getNome() + " - email: " + u.getEmail()
                + "</br>MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>"
        );

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();

        map.put("admin@seudominio.com.br", "email admin");
        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();

        }
    }

    public void enviarEmailNotificandoVencimentoMensalidadeUsuario(UsuarioEntidade u) throws SQLException {
        String id_config = "28";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "<div>Prezado usuário, informamos que sua mensalidade está vencida! Favor realizar o pagamento e encaminhar o recibo para <a  href=\"mailto:financeiro@microvenda.com.br\">financeiro</a></div>"
                + "</div>"
                + "nome:  " + u.getNome() + " - email: " + u.getEmail()
                + "</br>MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>"
        );

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();

        map.put(u.getEmail(), "email gmail");
        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();

        }

    }

    public void enviarEmailUsuaRioVencidoDesativado(UsuarioEntidade u) throws SQLException {

        String id_config = "29";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "<div>Olá  Admin microVenda , um usuário inativo  acaba de ser inativado do Sistema por falta de pagamento</div>"
                + "</div>"
                + "nome:  " + u.getNome() + " - email: " + u.getEmail()
                + "id : " + u.getIdUsuario()
                + "</br>MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>"
        );

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();

        map.put("financeiro@seudominio.com.br", "email financeiro");
        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();

        }
    }

    public void enviarEmailRotinaVerificacaoMensalidade() throws SQLException {

        String id_config = "30";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "<div>Olá  Admin microVenda , rotina de Verificação de Vencimento de Mensalidade sendo executada</div>"
                + "</div>"
                + "</br>MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>"
        );

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();

        map.put("admin@seudominio.com.br", "email admin");
        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();

        }
    }

    public void enviarEmailRotinaVerificacaoContaInativa() throws SQLException {

        String id_config = "31";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "<div>Olá  Admin microVenda , rotina de Inatividadde de conta sendo executada</div>"
                + "</div>"
                + "</br>MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>"
        );

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();

        map.put("admin@seudominio.com.br", "email admin");
        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();

        }
    }

    public void enviarEmailRotinaNotificacoes() throws SQLException {

        String id_config = "32";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "<div>Olá  Admin microVenda , rotina de Notificacaoes sendo executada</div>"
                + "</div>"
                + "</br>MENSAGEM GERADA AUTOMATICAMENTE - NAO RESPONDA ESTE E-MAIL!"
                + "</body>"
                + "</html>"
        );

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();

        map.put("admin@seudominio.com.br", "email admin");
        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();

        }
    }
}
