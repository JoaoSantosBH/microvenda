<%-- 
    Document   : indexc
    Created on : Sep 11, 2015, 2:20:56 PM
    Author     : joaosantos
--%>


<%@page import="persistencia.ProdutoDAO"%>
<%@page import="entidade.EstoqueEntidade"%>
<%@page import="persistencia.EstoqueDAO"%>
<%@page import="entidade.ProdutoEntidade"%>
<%@page import="util.Carrinho"%>
<%@page import="java.util.ArrayList"%>
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
                                            int idU = Integer.valueOf(Logado.getIdUsuario());
                                            List<EstoqueEntidade> le = EstoqueDAO.ListarEstoque(idU);
                                            int total = 0;
                                        %>
                                        <h4 style="color: #720e9e"><%= lst.size()%> itens no seu pedido</h4> 

                                        <!--  FECHAR PEDIDO  -->
                                        <form name="formpedido" method="post" action="FecharPedidoClienteServlet"> 


                                            <input type="hidden" name="id_usu" value="<%= Logado.getIdUsuario()%>">
                                            <input type="hidden" name="txt_id_cliente" value="<%= Logado.getIdCliente()%>">
                                            <input type="submit" class="btn btn-success"  value="Gerar Pedido" onclick="return confirm('Tem Certeza que deseja fechar o pedido?')">
                                        </form >
                                        <!--  FECHAR PEDIDO  -->
                                        <form action="indexc.jsp">
                                            <button type="submit" class="btn btn-info" >voltar</button>
                                        </form>

                                        <% if (Codmsg.equals("1")) {%>

                                        <script>window.alert("Você não especificou a quantidade")</script>
                                        <%} else if (Codmsg.equals("5")) {%>
                                        <script>window.alert("Este Produto Ja esta no Pedido! Para alterar sua quantidade, remova e adicione novamente!")</script>

                                        <%} else if (Codmsg.equals("2")) {%>
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





                                                  <!--<td style="text-align: center; "><%= e.getLimiteInferior()%></td>-->
                                                        <td>
                                                            <form role="form" name="form_carrinho[]" method="post" action="AdicionarItemCarrinhoClienteServlet">
                                                                <input type="number"  name="qtd" value="" class="btn-circle" >
                                                                <input type="hidden" name="preco_custo" value="<%= p.getPrecoCompra()%>">
                                                                <input type="hidden" name="id_produto" value="<%= e.getIdProduto()%>">
                                                                <input type="hidden" name="preco" value="<%= p.getPrecoVenda()%>">

                                                                <input type="submit" class="btn-info"  value="+" onclick="return validarOrcamento()">
                                                            </form>

                                                        </td>
                                                    </tr>

                                                    <%existe = false;
                                                    }%>
                                                    <%
                                                        for (Carrinho c : lst) {
                                                            totalCarrinho += c.getValor();
                                                        }

                                                    %>Valor deste Orcamento : <h3>R$ <%= totalCarrinho%> reais</h3>
                                                </tbody>

                                            </table>

                                        </div>
                                        <!-- /.table-responsive -->
                                    </div>
                                    <!-- /.panel-body -->
                                </div>

                                <!-- /.panel -->
                            </div>


                            <!--  T A B E  L  A S      -->


                        </div> <!-- F I M -->

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

