<%-- 
    Document   : a_receber
    Created on : Jun 21, 2015, 3:56:01 PM
    Author     : joaosantos
--%>


<%@page import="entidade.ClienteEntidade"%>
<%@page import="controle.ControladorCliente"%>
<%@page import="entidade.VendaEntidade"%>
<%@page import="persistencia.VendaDAO"%>
<%@page import="controle.ControladorUsuario"%>
<%@page import="entidade.AreceberEntidade"%>
<%@page import="controle.ControladorAReceber"%>
<%@page import="entidade.LinhaEntidade"%>
<%@page import="fronteira.FormataDataHtml"%>
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
            function validarCriaLinha() {
                var nome = formcriacliente.txt_nome_linha.value;
                if (nome === "") {
                    alert('Digite uma linha para ser inserida no Banco de Dados');
                    formcriacliente.txt_nome_linha.focus();
                    return false;
                }
            }
        </script>





    </head>

    <body> 
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
                                        <a href="listar_clientes.jsp">Editar</a>
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
                            <h1 class="page-header">Pagamentos A Receber</h1>
                        </div>
                        <!-- /.col-lg-12 -->
                        <div class="panel-body">
 <% if(Codmsg.equals("1")){%>
                                 <script>window.alert("Parcela excluida com sucesso!")</script>
                            <%}%>
                            <% if(Codmsg.equals("2")){%>
                            <script>window.alert("O seguinte erro ocorreu : <%= Erro %>");</script>
                            <%}%>
                            <% if(Codmsg.equals("3")){%>
                                <script>window.alert("O seguinte erro ocorreu : <%= Erro %>");</script>
                            <%}%>                          

                            <!-- A   R E C E B E R  -->

                            <%
                                List<AreceberEntidade> ls = ControladorAReceber.pegarListaDeParcelasAReceber(Logado.getIdUsuario());
                                
                                if(ls.size() == 0){%>
                                <script>
window.alert('Nenhum pagamento a receber!')
window.location.href ="/bootstrap/pages/index.jsp"
                                
                                </script>
                                <%} else {
                                    
                                    for (AreceberEntidade ar : ls) {
                                    VendaEntidade venda = VendaDAO.pegarVendaPeloId(ar.getIdVenda());
                                
                                    String nomeCliente = ControladorUsuario.pegarNomeClientePeloId(ar.getIdCliente());
                                    ClienteEntidade  cl = ControladorCliente.pegarClientePeloId(ar.getIdCliente());
                                    String data = ar.getData().toString();
                                    data = fronteira.FormataDataHtml.formatarDataParaDisplay(data);
                                    Long numPedido = venda.getNumPedido();
                                    
                                    
                            %>
                            
                             
                            <div class="panel panel-red">

                                <div class="panel-heading">
                                    <div><%= nomeCliente%></div>
                                </div>

                                <div class="panel-body">

                                    <div class="row show-grid">   
                                        <% Boolean vencida = util.OperacoesComDatas.verificarSeParcelaAReceberEstaVencida(ar.getData().toString()); %>
                                        <% if(vencida){%>
                                        <div class="col-md-3"><label style="color: red">Venda </br><%= numPedido %> </label></br></div>
                                        <div class="col-md-3"><label style="color: red">Vencida N� </br><%= ar.getNumeroParcela()%></label></div>
                                        <div class="col-md-3"><label style="color: red">Data </br><%= data%></label></div>
                                        <div class="col-md-3"><label style="color: red">Valor a Receber</br>R$ <%= ar.getValor()%></label></div>
                                        
                                        <%} else {%>
                                        
                                        <div class="col-md-3"><label>Venda </br><%= numPedido %> </label></br></div>
                                        <div class="col-md-3"><label>Parcela N� </label></br><%= ar.getNumeroParcela()%></div>
                                        <div class="col-md-3"><label>Data </label></br><%= data%></div>
                                        <div class="col-md-3"><label>Valor a Receber</label></br>R$ <%= ar.getValor()%></div>
                                        
                                        <%}%>
                                        
                                        
                                        
                                        
                                        <a href="mailto:<%= cl.getEmail() %>?subject=Compra N <%= numPedido %>&body=Parcela numero <%=ar.getNumeroParcela()  %>">enviar e-mail para este Usuario</a> 
                                    </div>



                                </div>

                                    <form method="POST" action="ExcluirRecebimentoParcelaServlet">
                                        <input type="hidden" name="id_receber_txt" value="<%= ar.getIdAreceber() %>"/>
                                        <input class="btn-danger" type="submit" value="Dar Baixa Recebido" onclick="return confirm('Tem certesa que esta parcela foi recebida?')"/>
                                        
                                    </form>
                            </div>
                            <% } %>
                            <!-- A   R E C E B E R  -->
                                    
                                    
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


