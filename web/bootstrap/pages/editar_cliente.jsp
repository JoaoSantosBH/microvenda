<%-- 
    Document   : cadastra_cliente
    Created on : Apr 7, 2015, 3:20:56 PM
    Author     : joaosantos
--%>


<%@page import="persistencia.ClienteDAO"%>
<%@page import="persistencia.TelefoneDAO"%>
<%@page import="entidade.TelefoneEntidade"%>
<%@page import="entidade.EnderecoEntidade"%>
<%@page import="entidade.ClienteEntidade"%>
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
            function validarCriaCliente() {
                var nome = formcriacliente.txt_nome.value;
                if (nome === "") {
                    alert('Digite seu nome');
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
                    alert('Digite o Endere�o do Cliente');
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
                    alert('Digite um email v�lido');
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
                    alert('Os e-mails digitados s�o diferentes');
                    formcriacliente.txt_email_conf.focus();
                    return false;
                }
                var senha = formcriacliente.txt_password.value;
                if (senha === "") {
                    alert('Preencha a senha');
                    formcriacliente.txt_password.focus();
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



    </head>

    <body> 
        <%
            UsuarioEntidade Logado = (UsuarioEntidade) session.getAttribute("UsuarioLogado");

            String foto = "";
            String idCl = request.getParameter("idCl");

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
            if (tipo == 1) { %>
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
                            <li><a href="env_email_todos_users.jsp"><i class="fa fa-sign-out fa-fw"></i>Enviar Email Todos Usu�rios</a></li>


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
                                        <a href="hist_vendas_mes.jsp">M�s</a>
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
                                       <a href="despesas_global.jsp">Hist�rico</a>
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

                        </ul><!-- m e n u l a t e r a l-->
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
                            <h1 class="page-header">Alterar Cadastro do Cliente</h1>
                        </div>
                        <!-- /.col-lg-12 -->

                        <div class="panel-body">

                            <% ClienteEntidade c = controle.ControladorCliente.pegarClientePeloId(Integer.parseInt(idCl));
                                TelefoneEntidade t = TelefoneDAO.pegarTelefonePeloIdCliente(Integer.parseInt(idCl));
                                EnderecoEntidade e = persistencia.EnderecoDAO.pegarEnderecoClientePeloId(idCl);
                                Boolean eMeuCliente = ClienteDAO.verificarSeOClientePertenceAoVendedor(idCl, Logado.getIdUsuario());

                                if (t == null) {
                                    t = new TelefoneEntidade();
                                    t.setIdUsuario(Logado.getIdUsuario());
                                }
                                if (e == null) {
                                    e = new EnderecoEntidade();
                                    e.setIdUsuario(Logado.getIdUsuario());
                                }
                            %>
                            <% if (eMeuCliente) {%>
                            <form role="form" name="formcriacliente" method="post" action="EditarDadosClienteServlet">
<!--                                <fieldset>-->
                                    <div class="form-group">
                                        Nome Completo<input class="form-control" maxlength="100" placeholder="Nome Completo" name="txt_nome" value="<%=c.getNome()%>" type="text" autofocus>
                                    </div>
                                    <div class="form-group">
                                        Fixo <input class="form-control" id="01" maxlength="9"  placeholder="Telefone Fixo" name="txt_tel_fixo" value="<%= t.getFixo()%>"  type="tel"  onKeyPress="MascaraTelefone(formcriacliente.txt_tel_fixo);" >
                                    </div>
                                    <div class="form-group">
                                        Celular  <input class="form-control" id="02"  maxlength="9"  placeholder="Celular" name="txt_cel" value="<%= t.getCelular()%>"   type="tel"  onKeyPress="MascaraTelefone(formcriacliente.txt_cel);" >
                                    </div>
                                    <div class="form-group">
                                        Outro tel  <input class="form-control" id="03"  maxlength="9"  placeholder="Outro Telefone" name="txt_tel_outro" value="<%= t.getOutro()%>"   type="tel"  onKeyPress="MascaraTelefone(formcriacliente.txt_tel_outro);" >
                                    </div>
                                    <div class="form-group">
                                        Logradouro  <input class="form-control" maxlength="150"  placeholder="Logradouro" name="txt_logradouro" value="<%= e.getLogradouro()%>"   type="text" >
                                    </div>

                                    <div class="form-group">
                                        N�  <input class="form-control" maxlength="5"  placeholder="N�" name="txt_numero"  value="<%= e.getNumero()%>"  type="tel" >
                                    </div>
                                    <div class="form-group">
                                        Complemento <input class="form-control" maxlength="45"  placeholder="Complemento" name="txt_complemento" value="<%= e.getComplemento()%>"  type="text" >
                                    </div>
                                    <div class="form-group">
                                        Bairro<input class="form-control" maxlength="45"  placeholder="Bairro" name="txt_bairro" value="<%= e.getBairro()%>"  type="text" >
                                    </div>
                                    <div class="form-group">
                                        CEP<input class="form-control" maxlength="9"  placeholder="CEP" name="txt_cep" value="<%= e.getCep()%>"  type="text"  onKeyPress="MascaraCep(formcriacliente.txt_cep);">
                                    </div>


                                    <div class="form-group">
                                        email <input class="form-control" placeholder="E-mail" name="txt_email" value="<%=c.getEmail()%>"  type="email" >
                                    </div>

                                    <div class="form-group">
                                        senha <input class="form-control" placeholder="Senha" name="txt_password" value="<%=c.getSenha()%>"  type="text">
                                    </div>

                                    <input class="btn btn-lg btn-success btn-block" type="submit" value="Salvar Alteracoes" onclick="return validarCriaCliente()">
                                    <input type="hidden" name="id_usuario" value="<%= idCl%>">
                                <!--</fieldset>-->


                                <% if (Codmsg.equals("")) {%>




                                <% } else if (Codmsg.equals("1")) {%>
                                <script>window.alert('Altera��o de Cadastro Efetuada Com Sucesso!')</script>

                                <% } else if (Codmsg.equals("2")) {%>

                                <script>window.alert('<%= request.getParameter("excep")%>')</script>


                                <% } else if (Codmsg.equals("3")) {%>

                                <script>window.alert('Erro no banco de dados!')</script>




                                <% } else if (Codmsg.equals("4")) {%>

                                <script>window.alert('Erro inexperado!')</script>




                                <% }%>



                            </form>
                            <%} else {%>
                            <script>window.alert('Nada por aqui!')</script>
                            <form action="listar_clientes.jsp">
                                <button type="submit" class="btn btn-info" >voltar</button>
                            </form>
                            <%}%>

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

