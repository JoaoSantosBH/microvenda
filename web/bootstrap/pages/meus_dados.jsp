<%-- 
    Document   : cadastra_cliente
    Created on : Apr 7, 2015, 3:20:56 PM
    Author     : joaosantos
--%>


<%@page import="entidade.TelefoneEntidade"%>
<%@page import="entidade.EnderecoEntidade"%>
<%@page import="controle.ControladorUsuario"%>
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
        <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

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



    </head>

    <body onload="MetodosParaODWR.pegarTodosEstadosDWR(loadstart);"> 
        <%
            UsuarioEntidade Logado = (UsuarioEntidade) session.getAttribute("UsuarioLogado");
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
            if (tipo == 1) {%>
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
                    <a class="navbar-brand" href="index.jsp">microVenda</a>
                </div>
                <!-- /.navbar-header -->

                <ul class="nav navbar-top-links navbar-right">
                    <!-- /.dropdown -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-messages">
                            
                            <li><a href="selec_cliente_env_email.jsp"><i class="fa fa-sign-out fa-fw"></i>Enviar Email</a></li> 
                            <li class="divider"></li>
                            <li><a href="env_email_todos_users.jsp"><i class="fa fa-sign-out fa-fw"></i>Enviar Email Todos Usuários</a></li>


                        </ul>
                        <!-- /.dropdown-messages -->
                    </li>

                    <!-- /.dropdown -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">


                            <li class="divider"></li>
                            <li><a href="meus_dados.jsp"><i class="fa fa-sign-out fa-fw"></i>Meus Dados</a></li> 
                            <li><a href="LogoutServlet"><i class="fa fa-sign-out fa-fw"></i> Sair</a></li>

                        </ul>
                        <!-- /.dropdown-user -->
                    </li>
                    <!-- /.dropdown -->
                </ul>
                <!-- /.navbar-top-links -->

                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">

                            
                            <li class="sidebar-search">
                               
                                <form method="POST" action="BuscaGenericaMenuPrincipalServlet">
                                    <div class="input-group custom-search-form">
                                        <input id="tags" autocomplete="on" name="tags" type="text" class="form-control" placeholder="Buscar...">
                                        <input type="hidden" name="id_usu" value="<%= Logado.getIdUsuario() %>">
                                        <span class="input-group-btn">
                                            <button class="btn btn-default" type="submit">
                                                <i class="fa fa-search"></i>
                                            </button>
                                        </span>
                                    </div>
                                    <!-- /input-group -->
                                </form>
                            </li>



                            <li>
                                <a href="index.jsp"><i class="fa fa-home fa-fw"></i> Inicio</a>
                            </li>


                            <!--    C  L  I  E  N  T  E  S   --> 
                            <li>
                                <a href="#"><i class="fa fa-user"></i> Cliente<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="cadastra_cliente.jsp">Cadastrar</a>
                                    </li>
                                    <li>
                                        <a href="listar_clientes.jsp">Manter</a>
                                    </li>
                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                            <!--    P   R   O   D   U   T   O   S   -->                          
                            <li>
                                <a href="#"><i class="fa fa-truck fa-fw"></i> Produtos<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="cadastra_linha.jsp">Manter Linha</a>
                                    </li>
                                    <li>
                                        <a href="cadastra_produto.jsp">Manter Produtos</a>
                                    </li>


                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                            <!--    P   R   O   D   U   T   O   S   -->  
                            <!-- E  S  T  O  Q  U  E -->
                            <li>
                                <a href="#"><i class="fa fa-database fa-fw"></i> Estoque<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="manter_estoque.jsp">Manter Estoque</a>
                                    </li>                             

                                    <li>
                                        <a href="listar_estoque.jsp">Listar Estoque</a>
                                    </li> 


                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                            <!-- E  S  T  O  Q  U  E -->



                            <!--    P   E  D   I   D    O   S   -->
                            <li>
                                <a href="#"><i class="fa fa-shopping-cart fa-fw"></i> Pedidos<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="lista_orcamento.jsp">Novo Pedido</a>
                                    </li>                             

                                    <li>
                                        <a href="pedidos.jsp">Ativos </a>
                                    </li> 

                                    <li>
                                        <a href="hist_pedidos_dia.jsp">Dia </a>
                                    </li>  
                                    <li>
                                        <a href="hist_pedidos_semana.jsp">Semana </a>
                                    </li>
                                    <li>
                                        <a href="hist_pedidos_mes.jsp">Mes </a>
                                    </li>
                                    <li>
                                        <a href="selecionar_pedido_cliente.jsp">Cliente</a>
                                    </li>                                   
                                    <li>
                                        <a href="historico.jsp"> Historico</a>
                                    </li>

                                </ul>
                                <!-- /.nav-second-level -->
                            </li>

                            <!--    P   E  D   I   D    O   S   -->



                            <!--    V  E  N  D  A  S   -->
                            <li>
                                <a href="#"><i class="fa fa-money fa-fw"></i> Vendas<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="lista_orcamento_venda_direta.jsp">Nova Venda</a>
                                    </li>
                                    <li>
                                        <a href="a_receber.jsp">A Receber</a>
                                    </li>
                                    <li>
                                        <a href="hist_vendas_dia.jsp">Dia</a>
                                    </li>
                                    <li>
                                        <a href="hist_vendas_semana.jsp">Semana</a>
                                    </li>
                                    <li>
                                        <a href="hist_vendas_mes.jsp">Mês</a>
                                    </li>
                                    <li>
                                        <a href="hist_vendas_periodo.jsp">Periodo</a>
                                    </li>
                                    <li>
                                        <a href="hist_vendas_cliente.jsp">Cliente</a>
                                    </li>                                    
                                    <li>
                                        <a href="hist_vendas.jsp">Historico </a>
                                    </li>


                                </ul>
                                <!-- /.nav-second-level -->
                            </li>

                            <!--    V  E  N  D  A  S   -->
                            <!--    D E S P E S A S    -->
                            <li>
                                <a href="#"><i class="fa fa-credit-card fa-fw"></i> Despesas<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                         <a href="declarar_despesa.jsp">Declarar</a>
                                    </li>
                                    <li>
                                         <a href="despesas_dia.jsp">Dia </a>
                                    </li>                                    
                                    <li>
                                        <a href="despesas_semana.jsp">Semana</a>
                                    </li>
                                    <li>
                                       <a href="despesas_mes.jsp">Mes</a>
                                    </li>
                                    <li>
                                       <a href="despesas_global.jsp">Histórico</a>
                                    </li>                                    
                                    
                                    
                                    
                                </ul>
                                <!-- /.nav-second-level -->
                            </li>                            
                            <!--    D E S P E S A S    -->
                            <!--    F I N A N C A S   -->
                            <li>
                                <a href="#"><i class="fa fa-dashboard fa-fw"></i> Financas<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="financas.jsp">Hist Global</a>
                                    </li>   
                                                                        <li>
                                        <a href="performance.jsp">Performance</a>
                                    </li>   

                                </ul>
                                <!-- /.nav-second-level -->
                            </li>

                            <!--    F I N A N C A S   -->                             

                        </ul><!--    M E N U    L A T E R A  L   -->
                    </div>
                    <!-- /.sidebar-collapse -->
                </div>
                <!-- /.navbar-static-side -->
            </nav>

            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Meus Dados</h1>
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
                            <% UsuarioEntidade u = ControladorUsuario.pegarUsuarioPeloSeuId(Logado.getIdUsuario().toString());
                                EnderecoEntidade e = ControladorUsuario.pegarEnderecoUsuarioPeloIdUsuario(Logado.getIdUsuario());
                                TelefoneEntidade t = ControladorUsuario.pegarTelefoneUsuarioPeloIdUsuario(Logado.getIdUsuario());
                            %>
                            <form role="form" name="formcriacliente" method="post" action="AtualizarDadosUsuarioServlet">
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
                                    <input type="hidden" name="id_usuario" value="<%= Logado.getIdUsuario()%>">
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


                        </div></div>
                    <!-- /.row -->
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="../bower_components/jquery/dist/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="../dist/js/sb-admin-2.js"></script>

    </body>







    <!-- = = = = = = = = = = = = = = = = = = = = = =  p a g i n a      c l i e t e = = = = = = = = = = = = = = = = = = = = = = = = = = = =-->
    <% }
        if (tipo == 0) {%>

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

