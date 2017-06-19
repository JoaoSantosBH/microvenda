<%-- 
    Document   : a_receber
    Created on : Jun 21, 2015, 3:56:01 PM
    Author     : joaosantos
--%>


<%@page import="persistencia.ClienteDAO"%>
<%@page import="entidade.ClienteEntidade"%>
<%@page import="entidade.ProdutoEntidade"%>
<%@page import="persistencia.ProdutoDAO"%>
<%@page import="entidade.ItemEntidade"%>
<%@page import="persistencia.ItemDAO"%>
<%@page import="entidade.PedidoEntidade"%>
<%@page import="persistencia.PedidoDAO"%>
<%@page import="persistencia.AreceberDAO"%>
<%@page import="persistencia.FormaPagamentoDAO"%>
<%@page import="entidade.VendaEntidade"%>
<%@page import="controle.ControladorAReceber"%>
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
            String dataIni = request.getParameter("di");
            if (dataIni == null) {
                dataIni = "0000-00-00";
            }
            String dataFim = request.getParameter("df");
            if (dataFim == null) {
                dataFim = "0000-00-00";
            }
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
                            <h1 class="page-header">Vendas no Periodo</h1>
                        </div>
                        <!-- /.col-lg-12 -->
                        <div class="panel-body">

                            <!-- VENDAS -->
                            <%

                                List<VendaEntidade> lstV = controle.ControladorVenda.pegarVendasDoUsuarioPorPeriodoPreDefinido(Logado.getIdUsuario(), dataIni, dataFim);
                                if (lstV.size() == 0) {%>
                           
                            <script>window.alert('Não foi feita nehuma venda neste periodo!')</script>
                            <form action="hist_vendas_periodo.jsp">
                                <button type="submit" class="btn btn-info" >voltar</button>
                            </form>
                            <%} else {
                                for (VendaEntidade venda : lstV) {
                                    String data = venda.getData();
                                    data = fronteira.FormataDataHtml.formatarDataParaDisplay(data);
                                    String formaPagamento = FormaPagamentoDAO.pegarFormaDePagamento(venda.getFormaPagamento());
                                    Integer valorParcela = AreceberDAO.pegarValorDaParcela(venda.getIdVenda());

                                    if (valorParcela == 0) {
                                        valorParcela = 0;
                                    }

                                    Integer valorAReceber = valorParcela * venda.getParcelas();
                                    ClienteEntidade cliente = ClienteDAO.pegarClientePeloId(venda.getIdCliente());
                            %>

                            <div class="panel panel-green">


                                <div class="panel-heading">
                                    <div>Venda Nº  [ <%= venda.getNumPedido()%>] Cliente : <%= cliente.getNome() %>  
                                    
                                        <%if(valorParcela!=0){%>
                                        <form method="POST" action="BuscarParcelasDeVendaEspecificaServlet">
                                            <button class="btn btn-outline" type="submit">
                                                <input type="hidden" name="id_usuario" value="<%= Logado.getIdUsuario() %>">
                                                <input type="hidden" name="id_venda" value="<%= venda.getIdVenda() %>">                                                
                                            <i class="fa fa-list-ol"> parcelas a receber</i>
                                        </button>
                                        </form>
                                        <%}%>
                                    </div>
                                </div>

                                <div class="panel-body">

                                    <div class="row show-grid">   


                                        <div class="col-md-2"><label>Data</label></br><%= data%></div>
                                        <div class="col-md-2"><label>Form Pagamento</label></br><%= formaPagamento%></div>
                                        <div class="col-md-2"><label> Entrada</label></br>R$ <%= venda.getEntrada()%></div>
                                        <div class="col-md-2"><label>Num Parcelas</label></br><%= venda.getParcelas()%></div>
                                        <div class="col-md-2"><label>Valor Parcelas</label></br>R$ <%= valorParcela%></div>
                                        <div class="col-md-2" style="color: red;"><label>Valor Parcelado</label></br>R$ <%= valorAReceber%></div>

                                    </div>


                                    <div class="table-responsive">


                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                                <%
                                                    PedidoEntidade p = PedidoDAO.pegarPedidoPeloNumPedido(venda.getNumPedido());
                                                    List<ItemEntidade> lsItens = ItemDAO.pegarItensDaVendaPeloNumPedido(venda.getNumPedido());
                                                    Float investimento = 0f;
                                                    Float LucroPercentual = 0F;
                                                %>
                                                <tr>
                                                    <th>item</th>
                                                    <th>qtde</th>
                                                    <th>Preco Custo</th>
                                                    <th>Custo Total</th>
                                                    <th>Preco Venda</th>
                                                    <th>Lucro</th>
                                                    <th>TOTAL</th>
                                                </tr>
                                            </thead>


                                            <tbody>
                                                <% for (ItemEntidade i : lsItens) {
                                                        ProdutoEntidade pro = ProdutoDAO.pegarProdutoPeloId(i.getIdProduto());
                                                        Float lucro = (i.getValorVenda() - i.getPrecoCustoItem()) * i.getQtde();

                                                        investimento += i.getPrecoCustoItem() * i.getQtde();

                                                        LucroPercentual += lucro;
                                                %>
                                                <tr>
                                                    <td style="text-align: center; "><%= i.getNome()%></td>
                                                    <td style="text-align: center; "><%= i.getQtde()%></td>
                                                    <td style="text-align: center; ">R$ <%= i.getPrecoCustoItem() %></td>
                                                    <td style="text-align: center; ">R$ <%= i.getPrecoCustoItem() * i.getQtde()%></td>
                                                    <td style="text-align: center; ">R$ <%= i.getValorVenda()%></td>
                                                    <td style="text-align: center; ">R$ <%= lucro%></td>
                                                    <td style="text-align: center; ">R$ <%= i.getValorVenda() * i.getQtde()%></td>
                                                </tr>

                                                <%}%>
                                            </tbody>
                                        </table>
                                        <div class="row show-grid" >
                                            <% Float result = ((LucroPercentual * 100) / investimento);
                                                if (result < 0) {
                                                    result += 100;
                                                }
                                            %>

                                            <div class="col-md-3"  style="color: green;"><label>Total da venda : </label></br> R$ <%= venda.getValor()%></div>
                                            <div class="col-md-3" style="color: blue;"><label>Total Investido :</label></br> R$  <%= investimento%> </div>

                                            <div class="col-md-3" style="color: green;"><label>Lucro da venda :</label></br> R$ <%= venda.getValor() - investimento%> </div>

                                            <div class="col-md-3" style="color: green;"><label>Lucro percentual :</label></br> <%= result%>   %</div>

                                        </div>
                                    </div>


                                </div>
                                <!-- VENDAS -->


                            </div>
                            <%}%><!-- F I M D    F O R -->

                            <%}%>
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


