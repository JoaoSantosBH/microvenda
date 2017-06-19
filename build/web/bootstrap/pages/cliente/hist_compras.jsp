<%-- 
    Document   : indexc
    Created on : Sep 11, 2015, 2:20:56 PM
    Author     : joaosantos
--%>


<%@page import="persistencia.AreceberDAO"%>
<%@page import="persistencia.FormaPagamentoDAO"%>
<%@page import="entidade.VendaEntidade"%>
<%@page import="persistencia.ItemDAO"%>
<%@page import="persistencia.ProdutoDAO"%>
<%@page import="entidade.ProdutoEntidade"%>
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

    <body> 
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



                            <li><a href="meus_dadosc.jsp"><i class="fa fa-sign-out fa-fw"></i>Meus Dados</a></li> 
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
                            <%
                                String idVendedor = Logado.getIdUsuario();
                                int idCliente = Logado.getIdCliente();
                                List<VendaEntidade> lstV = controle.ControladorVenda.pegarComprasDoCLiente(idVendedor, idCliente);
                                if (lstV.size() == 0) {%>
                            <script>window.alert('Não há foi realizada nenhuma Compra!')</script>
<!--                            <form action="indexc.jsp">
                                <button type="submit" class="btn btn-info" >voltar</button>
                            </form>-->
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
                                    <div>Compra Nº  [ <%= venda.getNumPedido()%>]  

                                        <%if (valorParcela != 0) {%>
                                        <form method="POST" action="BuscarParcelasDeVendaEspecificaClienteServlet">
                                            <button class="btn btn-outline" type="submit">
                                                <input type="hidden" name="id_usuario" value="<%= Logado.getIdUsuario()%>">
                                                <input type="hidden" name="id_venda" value="<%= venda.getIdVenda()%>">                                                
                                                <i class="fa fa-list-ol"> parcelas a pagar</i>
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

                                                %>
                                                <tr>
                                                    <th>item</th>
                                                    <th>qtde</th>
                                                    <th>Valor Unitario</th>
                                                    <th>TOTAL</th>
                                                </tr>
                                            </thead>


                                            <tbody>
                                                <% for (ItemEntidade i : lsItens) {
                                                        ProdutoEntidade pro = ProdutoDAO.pegarProdutoPeloId(i.getIdProduto());

                                                %>
                                                <tr>
                                                    <td style="text-align: center; "><%= i.getNome()%></td>
                                                    <td style="text-align: center; "><%= i.getQtde()%></td>                                                   
                                                    <td style="text-align: center; ">R$ <%= i.getValorVenda()%></td>                                                   
                                                    <td style="text-align: center; ">R$ <%= i.getValorVenda() * i.getQtde()%></td>
                                                </tr>

                                                <%}%>
                                            </tbody>
                                        </table>
                                        <div class="row show-grid" >                                                                                        
                                            <div class="col-md-3"  style="color: green;"><label>Total da compra : </label></br> R$ <%= venda.getValor()%></div>                                           
                                        </div>
                                    </div>


                                </div>
                                <!-- VENDAS -->


                            </div>
                            <%}%><!-- F I M D    F O R -->

                            <%}%>

                            <form action="indexc.jsp">
                                <button type="submit" class="btn btn-info" >voltar</button>
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

