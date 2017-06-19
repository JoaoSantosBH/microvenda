<%-- 
    Document   : cadastra_cliente
    Created on : Apr 7, 2015, 3:20:56 PM
    Author     : joaosantos
--%>


<%@page import="entidade.ClienteEntidade"%>
<%@page import="util.Carrinho"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.EstoqueEntidade"%>
<%@page import="persistencia.EstoqueDAO"%>
<%@page import="persistencia.ProdutoDAO"%>
<%@page import="entidade.ProdutoEntidade"%>
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
            function validarCliente() {

                var linha = formpedido.txt_id_cliente.value;
                if (linha === "0") {
                    alert('Selecione um cliente para a venda!');
                    formpedido.txt_id_cliente.focus();
                    return false;
                } else{
                    return confirm('Finalizar a Venda??');
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
                            <h1 class="page-header">Venda Direta</h1>
                        </div>
                        <!-- /.col-lg-12 -->
                        <div class="panel-body">

<% List<ClienteEntidade> lstCli = controle.ControladorCliente.pegarListaDeClientesDoVendedor(Logado.getIdUsuario()); %>

                            <!--   TABELAS    -->

                            
                            <div class="col-lg-6">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <% List<Carrinho> lst = (ArrayList) session.getAttribute("carrinho");
                                            int totalCarrinho = 0;
                                            ProdutoEntidade p = null;
                                            boolean existe = false;
                                            if (lst == null) {
                                                lst = new ArrayList<Carrinho>();

                                            }
                                            List<EstoqueEntidade> le = EstoqueDAO.ListarEstoque(Logado.getIdUsuario());
                                            int total = 0;
                                        %>
                                        <h4 style="color: #720e9e"><%= lst.size()%> itens no seu pedido</h4> 

                                        <!--  FECHAR PEDIDO  -->
                                        <form name="formpedido" method="post" action="FecharPedidoVendaDiretaServlet"> 
                                             <select class="form-control" name="txt_id_cliente">
                                                 <option value="0">selecione um cliente </option>

                                        <%  for (ClienteEntidade c : lstCli) {%>

                                        <option value="<%= c.getIdCliente()%>"><%= c.getNome()%></option>

                                        <% } %>


                                    </select>
                                            <input type="hidden" name="id_usu" value="<%= Logado.getIdUsuario()%>">
                                            <input type="submit" class="btn-outline"  value="Vender" onclick="return validarCliente()">
                                        </form >
                                        <!--  FECHAR PEDIDO  -->

                                        <% if (Codmsg.equals("1")) {%>

                                        <script>window.alert("Você não especificou a quantidade")</script>
                                        <%} else if (Codmsg.equals("5")) {%>
                                        <script>window.alert("Este Produto Ja esta no Pedido! Para alterar sua quantidade, remova e adicione novamente!")</script>

                                        <%}else if (Codmsg.equals("2")) {%>
                                        <script>window.alert("Este Pedido Não Possui nenhum item")</script>

                                        <%} %> 
                                    </div>
                                    <!-- /.panel-heading -->
                                    <div class="">
                                        <div class="table-responsive">

                                            <table class="table table-striped table-bordered table-hover">
                                                <thead>
                                                    <tr>

                                                        <th>PRODUTO</th>
                                                        <th>Qtd</th>
                                                        <!--<th>Qtd Min</th>-->
                                                        <th></th>
                                                    </tr>
                                                </thead>


                                                <tbody>

                                                    <%
                                                        //B U S C A R  LISTA ESTOQUE EM ORDEM ASLFAVETICA

                                                        for (EstoqueEntidade e : le) {

                                                            p = ProdutoDAO.pegarProdutoPeloId(e.getIdProduto());
                                                    %>


                                                    <tr>
                                                        <%             for (Carrinho car : lst) {
                                                                if (car.getNome().equals(p.getNome())) {
                                                                    System.out.println("achou ");
                                                                    existe = true;
                                                                }

                                                            }
                                                        %>




                                                        <% if (existe) {%>
                                                        <td style="color: blue"><%= p.getNome()%></td>
                                                        <%} else {%>
                                                        <td ><%=  p.getNome()%></td>
                                                        <%}%>




                                                        <% if (e.getQuantidade() < e.getLimiteInferior()) {%>
                                                        <td style="color: red; text-align:center; "><%= e.getQuantidade()%></td>
                                                        <%
                                                            total += e.getQuantidade();
                                                        %>
                                                        <%} else {%>
                                                        <td style="text-align: center; "><%= e.getQuantidade()%></td>
                                                        <%
                                                                total += e.getQuantidade();
                                                            }%>
                                                        <!--<td style="text-align: center; "><%= e.getLimiteInferior()%></td>-->
                                                        <td>
                                                            <form role="form" name="form_carrinho[]" method="post" action="AdicionarItemCarrinhoVendaDiretaServlet">
                                                                <input type="number"  name="qtd" value="" class="btn-circle" >

                                                                <input type="hidden" name="id_produto" value="<%= e.getIdProduto()%>">
                                                                <input type="hidden" name="preco" value="<%= p.getPrecoVenda()%>">
                                                                <input type="hidden" name="preco_custo" value="<%= p.getPrecoCompra() %>">
                                                                <input type="submit" class="btn-info"  value="+" onclick="return validarOrcamento()">
                                                            </form>

                                                        </td>
                                                    </tr>

                                                <%existe = false;
                                                        }%><h3 style="color: #00d6b2">Total de produtos em estoque: <%= total%></h3>
                                                <%
                                                    for (Carrinho c : lst) {
                                                        totalCarrinho += c.getValor();
                                                    }

                                                %>Valor deste Orcamento : <h3>R$ <%= totalCarrinho%> reais</h3>
                                                </tbody>

                                            </table>

                                        </div><h3 style="color: #00d6b2">Total de produtos em estoque: <%= total%></h3>
                                        <!-- /.table-responsive -->
                                    </div>
                                    <!-- /.panel-body -->
                                </div>

                                <!-- /.panel -->
                            </div>


                            <!--  T A B E  L  A S      -->
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
        if (tipo
                == 0) {%>

    <body>
        cliente

    </body>
    <!-- = = = = = = = = = = = = = = = = = = = = = =  p a g i n a      c l i e t e = = = = = = = = = = = = = = = = = = = = = = = = = = = =-->
    <%}%>










    <!-- = = = = = = = = = = = = = = = = = = = = = =  p a g i n a      L I M B O O  t e = = = = = = = = = = = = = = = = = = = = = = = = = = = =-->
    <% if (tipo
                == 3) {%>

    <body>

        <jsp:include page="login.jsp">
            <jsp:param name="opcao" value="2"/>
        </jsp:include>

    </body>
    <!-- = = = = = = = = = = = = = = = = = = = = = =  p a g i n a      L I M B O O  t e = = = = = = = = = = = = = = = = = = = = = = = = = = = =-->
    <%}%>

</html>

