<%-- 
    Document   : indexc
    Created on : Sep 11, 2015, 2:20:56 PM
    Author     : joaosantos
--%>


<%@page import="controle.ControladorCliente"%>
<%@page import="entidade.TelefoneEntidade"%>
<%@page import="entidade.EnderecoEntidade"%>
<%@page import="controle.ControladorUsuario"%>
<%@page import="entidade.ItemEntidade"%>
<%@page import="persistencia.ClienteDAO"%>
<%@page import="controle.ControladorPedido"%>
<%@page import="entidade.PedidoEntidade"%>
<%@page import="entidade.ClienteEntidade"%>
<%@page import="persistencia.PedidoDAO"%>
<%@page import="persistencia.AcessoDAO"%>
<%@page import="entidade.LinhaEntidade"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="entidade.UsuarioEntidade"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>microVenda</title>

        <!-- Bootstrap Core CSS -->
        <link href="../../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="../../dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="../../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">


        <script language="javascript" type="text/javascript">
            function validarMeusDados() {
                var nome = formcriacliente.txt_nome.value;
                if (nome === "") {
                    alert('Digite o seu nome!');
                    formcriacliente.txt_nome.focus();
                    return false;
                }

                var fixo = formcriacliente.txt_tel_fixo.value;
                if (fixo === "") {
                    alert('Digite um telefone fixo');
                    formcriacliente.txt_tel_fixo.focus();
                    return false;
                }
                var celular = formcriacliente.txt_cel.value;
                if (celular === "") {
                    alert('Digite o Celular');
                    formcriacliente.txt_cel.focus();
                    return false;
                }
                var logradouro = formcriacliente.txt_logradouro.value;
                if (logradouro === "") {
                    alert('Digite o Endereço do Cliente');
                    formcriacliente.txt_logradouro.focus();
                    return false;
                }
                var num = formcriacliente.txt_numero.value;
                if (num === "") {
                    alert('Digite o Numero do Endereco');
                    formcriacliente.txt_numero.focus();
                    return false;
                }
                var bairro = formcriacliente.txt_bairro.value;
                if (bairro === "") {
                    alert('Digite o Bairroo');
                    formcriacliente.txt_bairro.focus();
                    return false;
                }
                var cep = formcriacliente.txt_cep.value;
                if (cep === "") {
                    alert('Digite o CEP');
                    formcriacliente.txt_cep.focus();
                    return false;
                }
                var estado = formcriacliente.estados.value;
                if (estado === "0") {
                    alert('Selecione um estado');
                    formcriacliente.estados.focus();
                    return false;
                }
                var cidade = formcriacliente.cidades.value;
                if (cidade === "0") {
                    alert('Selecione uma cidade');
                    formcriacliente.cidades.focus();
                    return false;
                }
                var email = formcriacliente.txt_email.value;
                if (email === "") {
                    alert('Digite um email válido');
                    formcriacliente.txt_email.focus();
                    return false;
                }
                var email2 = formcriacliente.txt_email_conf.value;
                if (email2 === "") {
                    alert('Confirme seu e-mail');
                    formcriacliente.txt_email_conf.focus();
                    return false;
                }
                if (email2 !== email) {
                    alert('Os e-mails digitados são diferentes');
                    formcriacliente.txt_email_conf.focus();
                    return false;
                }
                var senha = formcriacliente.txt_password.value;
                if (senha === "") {
                    alert('Preencha a senha');
                    formcriacliente.txt_password.focus();
                    return false;
                }

                var senha2 = formcriacliente.txt_password2.value;
                if (senha !== senha2){
                    alert('as senhas estão diferentes');
                    formcriacliente.txt_password2.focus();
                    return false;
                }

            }
        </script>
        <script language="javascript" type="text/javascript">
            //adiciona mascara ao telefone
            function MascaraTelefone(tel) {
                if (mascaraInteiro(tel) == false) {
                    event.returnValue = false;
                }
                return formataCampo(tel, '0000-0000', event);
            }
            function MascaraCep(cep) {
                if (mascaraInteiro(cep) == false) {
                    event.returnValue = false;
                }
                return formataCampo(cep, '00000-000', event);
            }
            //valida telefone
            function ValidaTelefone(tel) {
                exp = /(\d{4})-(\d{4})/g
                if (!exp.test(tel.value))
                    alert('Numero de Telefone Invalido!');

            }
            function ValidaDDD(ddd) {
                exp = /(\d{4})/g
                if (!exp.test(tel.value))
                    alert('Numero de DDD Invalido!');

            }
            function MascaraDDD(ddd) {
                if (mascaraInteiro(ddd) == false) {
                    event.returnValue = false;
                }
                return formataCampo(ddd, '000', event);
            }
            //valida numero inteiro com mascara
            function mascaraInteiro() {
                if (event.keyCode < 48 || event.keyCode > 57) {
                    event.returnValue = false;
                    return false;
                }
                return true;
            }

            //formata de forma generica os campos
            function formataCampo(campo, Mascara, evento) {
                var boleanoMascara;

                var Digitato = evento.keyCode;
                exp = /\-|\.|\/|\(|\)| /g
                campoSoNumeros = campo.value.toString().replace(exp, "");

                var posicaoCampo = 0;
                var NovoValorCampo = "";
                var TamanhoMascara = campoSoNumeros.length;
                ;

                if (Digitato !== 8) { // backspace 
                    for (i = 0; i <= TamanhoMascara; i++) {

                        boleanoMascara = ((Mascara.charAt(i) === "-"))

                        boleanoMascara = boleanoMascara

                        if (boleanoMascara) {
                            NovoValorCampo += Mascara.charAt(i);
                            TamanhoMascara++;
                        } else {
                            NovoValorCampo += campoSoNumeros.charAt(posicaoCampo);
                            posicaoCampo++;
                        }
                    }
                    campo.value = NovoValorCampo;
                    return true;
                } else {
                    return true;
                }
            }


        </script>

        <script type='text/javascript'>
            var loadstart = function (data) {

                dwr.util.removeAllOptions('estados');
                dwr.util.addOptions('estados', data, 'id', 'nome');

            }
        </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/MetodosParaODWR.js'></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>

        <script type='text/javascript'> //carregar estados e cidades
    var callback = function (c) {
    }
    function carregaCidades() {

        var estado = dwr.util.getValue('estados');
        MetodosParaODWR.pegarCidadesDWR(estado, scallback);
    }
    var scallback = function (data) {
        var size = data.length;
        if (size === 0) {
            alert("Nothing found");

        } else {
            dwr.util.removeAllOptions('cidades');

            dwr.util.addOptions('cidades', data, 'id', 'nome');
        }
    }
        </script>

        <!-- T E S T E -->


        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

        <script type="text/javascript" >
            $(document).ready(function ($) {
                var availableTags = [
                    "ActionScript",
                    "AppleScript",
                    "Asp",
                    "BASIC",
                    "C",
                    "C++",
                    "Clojure",
                    "COBOL",
                    "ColdFusion",
                    "Erlang",
                    "Fortran",
                    "Groovy",
                    "Haskell",
                    "Java",
                    "JavaScript",
                    "Lisp",
                    "Perl",
                    "PHP",
                    "Python",
                    "Ruby",
                    "Scala",
                    "Scheme"
                ];
                $("#tags").autocomplete({
                    source: availableTags
                });
            });
        </script>
        <!-- T E S T E -->






    </head>

    <body onload="MetodosParaODWR.pegarTodosEstadosDWR(loadstart);"> 
        <%
            ClienteEntidade Logado = (ClienteEntidade) session.getAttribute("CLienteLogado");
            String foto = "";
            String Erro = request.getParameter("e");
            Short tipo;
            String Codmsg = request.getParameter("msg");
            if (Codmsg == null) {
                Codmsg = "";
            }
        %>
        <% if (Logado == null) {
                tipo = 3;
            } else {
                tipo = Logado.getTipo();
            }
            if (tipo == 0) {%>
        <div id="wrapper">

            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="indexc.jsp">microVenda</a>

                </div>

                <ul class="nav navbar-top-links navbar-right">


                    <!-- /.dropdown -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-messages">

                            <li><a href="enviar_email_vendedor.jsp"><i class="fa fa-sign-out fa-fw"></i>Enviar Email p/ Vendedor</a></li> 


                        </ul>
                        <!-- /.dropdown-messages -->
                    </li>
                    <li class="dropdown">

                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">



                            <li><a href="meus_dados.jsp"><i class="fa fa-sign-out fa-fw"></i>Meus Dados</a></li> 
                            <li class="divider"></li>
                            <li><a href="LogoutClienteServlet"><i class="fa fa-sign-out fa-fw"></i> Sair</a></li>

                        </ul>
                        <!-- /.dropdown-user -->
                    </li>
                    <!-- /.dropdown -->
                </ul>
                <!-- /.navbar-top-links -->

            </nav>

            <!-- Page Content -->
            <div id="" style="background-color: #fffff1;">
                <div class="container-fluid">
                    <div class="row">
                        <!-- /.navbar-header -->


                        <div class="col-lg-12">

                        </div>
                        <!-- /.col-lg-12 -->
                        <div class="panel-body">


                            <script>
                                function consultacep(cep) {
                                    cep = cep.replace(/\D/g, "")
                                    url = "http://cep.correiocontrol.com.br/" + cep + ".js"
                                    s = document.createElement('script')
                                    s.setAttribute('charset', 'utf-8')
                                    s.src = url
                                    document.querySelector('head').appendChild(s)
                                }

                                function correiocontrolcep(valor) {
                                    if (valor.erro) {
                                        alert('CEP nao encontrado');

                                        return;
                                    }
                                    ;


                                    document.getElementById('logradouro').value = valor.logradouro
                                    document.getElementById('bairro').value = valor.bairro

                                }
                            </script>
                            <% 
                                int idUser = Logado.getIdCliente();
                                    ClienteEntidade u = ControladorCliente.pegarClientePeloId(Integer.valueOf(idUser));
                                EnderecoEntidade e = ControladorUsuario.pegarEnderecoUsuarioPeloIdUsuario(idUser);
                                TelefoneEntidade t = ControladorUsuario.pegarTelefoneUsuarioPeloIdUsuario(idUser);
                            %>
                            <form role="form" name="formcriacliente" method="post" action="AtualizarDadosClienteServlet">
                                <!--<fieldset>-->
                                    <div class="form-group"><label>Nome</label>
                                        <input class="form-control" maxlength="100" placeholder="Nome Completo" name="txt_nome" type="text" autofocus value="<%= u.getNome()%>">
                                    </div>
                                    <div class="form-group"><label>Fixo</label>
                                        <input class="form-control" id="01" maxlength="9"  placeholder="Telefone Fixo" name="txt_tel_fixo" type="tel" value="<%= t.getFixo()%>"  onKeyPress="MascaraTelefone(formcriacliente.txt_tel_fixo);" >
                                    </div>
                                    <div class="form-group"><label>Celular</label>
                                        <input class="form-control" id="02"  maxlength="9"  placeholder="Celular" name="txt_cel" type="tel" value="<%= t.getCelular()%>"  onKeyPress="MascaraTelefone(formcriacliente.txt_cel);" >
                                    </div>
                                    <div class="form-group"><label>Outro Tel</label>
                                        <input class="form-control" id="03"  maxlength="9"  placeholder="Outro Telefone" name="txt_tel_outro" type="tel" value="<%= t.getOutro()%>" onKeyPress="MascaraTelefone(formcriacliente.txt_tel_outro);" >
                                    </div>
                                    <div class="form-group"><label>Cep</label>
                                        <input class="form-control" maxlength="9"  placeholder="CEP" name="txt_cep" type="text" value="<%= e.getCep()%>" onKeyPress="MascaraCep(formcriacliente.txt_cep);" onblur="consultacep(this.value)">
                                    </div>
                                    <div class="form-group"><label>Logradouro</label>
                                        <input class="form-control" maxlength="150" id="logradouro" placeholder="Logradouro" name="txt_logradouro" type="text" value="<%= e.getLogradouro()%>" >
                                    </div>

                                    <div class="form-group"><label>Numero</label>
                                        <input class="form-control" maxlength="5"  placeholder="Numero" name="txt_numero" type="tel" value="<%= e.getNumero()%>">
                                    </div>
                                    <div class="form-group"><label>Complemento</label>
                                        <input class="form-control" maxlength="45"  placeholder="Complemento" name="txt_complemento" type="text" value="<%= e.getComplemento()%>">
                                    </div>
                                    <div class="form-group"><label>Bairro</label>
                                        <input class="form-control" maxlength="45" id="bairro" placeholder="Bairro" name="txt_bairro" type="text" value="<%= e.getBairro()%>">
                                    </div>


                                    <select name="estados" id="estados" onchange="carregaCidades()"></select>
                                    <select name="cidades" id="cidades"></select>

                                    <div class="form-group"><label>Email</label>
                                        <input class="form-control" placeholder="Email" name="txt_email" type="email"  value="<%= u.getEmail()%>">
                                    </div>


                                    <div class="form-group"><label>Confirme Email</label>
                                        <input class="form-control" placeholder="Confirme E-mail" name="txt_email_conf" type="email" value="<%= u.getEmail()%>">
                                    </div>
                                    <div class="form-group">
                                        <label>Senha</label>
                                        <input class="form-control" placeholder="Senha" name="txt_password" type="text" value="<%= u.getSenha()%>">
                                    </div>
                                    <div class="form-group">
                                        <label>Confirme a Senha</label>
                                        <input class="form-control" placeholder="Senha" name="txt_password2" type="text" value="<%= u.getSenha()%>">
                                    </div>
                                    <input class="btn btn-lg btn-success btn-block" type="submit" value="Salvar" onclick="return validarMeusDados()">
                                    <input type="hidden" name="id_cliente" value="<%= Logado.getIdCliente() %>">
                                    <input type="hidden" name="id_usuario" value="<%= Logado.getIdUsuario() %>">
                                <!--</fieldset>-->


                                <% if (Codmsg.equals("")) {%>


                                <% } else if (Codmsg.equals("1")) {%>
                                <script>window.alert('Dados Alterados com cesso!')</script>

                                <% } else if (Codmsg.equals("2")) {%>

                                <script>window.alert('<%= request.getParameter("excep")%>')</script>

                                <% } else if (Codmsg.equals("3")) {%>

                                <script>window.alert('Erro no banco de dados.')</script>

                                <% } else if (Codmsg.equals("4")) {%>

                                <script>window.alert('Erro inexperado.')</script>

                                <% }%>



                            </form>



                        </div>

                    </div>
                    <!-- /.row -->
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="../../bower_components/jquery/dist/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="../../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="../../bower_components/metisMenu/dist/metisMenu.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="../../dist/js/sb-admin-2.js"></script>

    </body>







    <!-- = = = = = = = = = = = = = = = = = = = = = =  p a g i n a      c l i e t e = = = = = = = = = = = = = = = = = = = = = = = = = = = =-->
    <% }
        if (tipo == 1) {%>

    <body>
        cliente

    </body>
    <!-- = = = = = = = = = = = = = = = = = = = = = =  p a g i n a      c l i e t e = = = = = = = = = = = = = = = = = = = = = = = = = = = =-->
    <%}%>










    <!-- = = = = = = = = = = = = = = = = = = = = = =  p a g i n a      L I M B O O  t e = = = = = = = = = = = = = = = = = = = = = = = = = = = =-->
    <% if (tipo == 3) {%>

    <body>

        <jsp:include page="login.jsp">
            <jsp:param name="opcao" value="2"/>
        </jsp:include>

    </body>
    <!-- = = = = = = = = = = = = = = = = = = = = = =  p a g i n a      L I M B O O  t e = = = = = = = = = = = = = = = = = = = = = = = = = = = =-->
    <%}%>

</html>

