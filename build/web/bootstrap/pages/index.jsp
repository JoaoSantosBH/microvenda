<%-- 
    Document   : cadastra_cliente
    Created on : Apr 7, 2015, 3:20:56 PM
    Author     : joaosantos
--%>


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
                    <li class="dropdown">

                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">



                            <li><a href="meus_dados.jsp"><i class="fa fa-sign-out fa-fw"></i>Meus Dados</a></li> 
                            <li class="divider"></li>
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
                            <%
                                String data = AcessoDAO.pegarUltimoAcesso(Logado.getIdUsuario());
                                data = fronteira.FormataDataHtml.formatarDataParaDisplay(data);

                            %>
                            <h1 class="page-header"> Bem Vindo <%=Logado.getNome()%>!</h1> Seu ultimo acesso foi em : <%= data%>
                        </div>
                        <!-- /.col-lg-12 -->
                        <div class="panel-body">

                            <% if (Codmsg.equals("13")) {%>
                            <script>window.alert('Digite um NUMERO de PEDIDO ou um NOME de Cliente!!')</script>
                            <%}%>
                            <% if (Codmsg.equals("14")) {%>
                            <script>window.alert('Ocorreu algum Erro temporario! Favor tentar novamente mais tarde!')</script>
                            <%}%>                            
                            <!-- |||||||||||||||||||||||||||||||||||||| V E N D A|||||||||||||||||||||||||||||||||||||||||||||||||||||||| -->
                            <div class="col-lg-3 col-md-6">
                                <div class="panel panel-success">
                                    <div class="panel-heading">
                                        <div class="row">
                                            <div class="col-xs-3">
                                                <i class="fa fa-hand-o-right fa-5x"></i>
                                            </div>
                                            <div class="col-xs-9 text-right">
                                                <div class="huge">NV</div>
                                                <div>Nova Venda</div>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="lista_orcamento_venda_direta.jsp">
                                        <div class="panel-footer">
                                            <span class="pull-left">Nova Venda</span>
                                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </a>
                                </div>
                            </div>

                            <!-- |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| -->

                            <!-- ||||||||||||||||||||||||||||||||||||||P E D I D O |||||||||||||||||||||||||||||||||||||||||||||||||||||||| -->
                            <div class="col-lg-3 col-md-6">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <div class="row">
                                            <div class="col-xs-3">
                                                <i class="fa fa-hand-o-right fa-5x"></i>
                                            </div>
                                            <div class="col-xs-9 text-right">
                                                <div class="huge">NP</div>
                                                <div>Novo Pedido</div>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="lista_orcamento.jsp">
                                        <div class="panel-footer">
                                            <span class="pull-left">Novo</span>
                                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </a>
                                </div>
                            </div>

                            <!-- |||||||||||||||||||||||||||||||||||||||||I T E N S    R E C E B E R ||||||||||||||||||||||||||||||||||||||||||||||||||||| -->

                            <div class="col-lg-3 col-md-6">
                                <div class="panel panel-red">
                                    <div class="panel-heading">
                                        <div class="row">
                                            <div class="col-xs-3">
                                                <i class="fa fa-tasks fa-5x"></i>
                                            </div>
                                            <div class="col-xs-9 text-right">
                                                <div class="huge">AR</div>
                                                <div>A Receber</div>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="a_receber.jsp">
                                        <div class="panel-footer">
                                            <span class="pull-left">Ver</span>
                                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </a>
                                </div>
                            </div>


                            <!-- ||||||||||||||||||||||||||||||||||||||||||C A R R I N H O |||||||||||||||||||||||||||||||||||||||||||||||||||| -->
                            <%
                                int pedidos = PedidoDAO.contarNumeroDePedidos(Logado.getIdUsuario());
                            %>
                            <div class="col-lg-3 col-md-6">
                                <div class="panel panel-yellow">
                                    <div class="panel-heading">
                                        <div class="row">
                                            <div class="col-xs-3">
                                                <i class="fa fa-shopping-cart fa-5x"></i>
                                            </div>
                                            <div class="col-xs-9 text-right" >
                                                <div class="huge"><%= pedidos%></div>
                                                <div>Pedidos Ativos</div>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="pedidos.jsp">
                                        <div class="panel-footer">
                                            <span class="pull-left">Ver</span>
                                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </a>
                                </div>
                            </div>

                            <!-- |||||||||||||||||||||||||||||||||||||||||H I S T O R I C O V E N D A S||||||||||||||||||||||||||||||||||||||||||||||||||||| -->


                            <div class="col-lg-3 col-md-6">
                                <div class="panel panel-green">
                                    <div class="panel-heading">
                                        <div class="row">
                                            <div class="col-xs-3">
                                                <i class="fa fa-money fa-5x"></i>
                                            </div>
                                            <div class="col-xs-9 text-right">
                                                <div class="huge">HV</div>
                                                <div>Histórico Vendas</div>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="hist_vendas.jsp">
                                        <div class="panel-footer">
                                            <span class="pull-left">Ver</span>
                                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </a>
                                </div>
                            </div>

                        
                            <!-- ||||||||||||||||||||||||||||||||||||||||||||||| R A N K  C L I E N T E ||||||||||||||||||||||||||||||||||||||||||||||| -->

                            <div class="col-lg-3 col-md-6">
                                <div class="panel panel-warning">
                                    <div class="panel-heading">
                                        <div class="row">
                                            <div class="col-xs-3">
                                                <i class="fa fa-star-o fa-5x"></i>
                                            </div>
                                            <div class="col-xs-9 text-right">
                                                <div class="huge">RC</div>
                                                <div>Rank Cliente</div>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="client_rank.jsp">
                                        <div class="panel-footer">
                                            <span class="pull-left">Ver</span>
                                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </a>
                                </div>
                            </div>


                            <!-- |||||||||||||||||||||||||||||||||||||||||||||p e r f o r m a||||||||||||||||||||||||||||||||||||||||||||||||| -->  
                            
                            
                            <div class="col-lg-3 col-md-6">
                                <div class="panel panel-collapse">
                                    <div class="panel-heading">
                                        <div class="row">
                                            <div class="col-xs-3">
                                                <i class="fa fa-eye fa-5x"></i>
                                            </div>
                                            <div class="col-xs-9 text-right">
                                                <div class="huge">P</div>
                                                <div>Performance</div>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="performance.jsp">
                                        <div class="panel-footer">
                                            <span class="pull-left">Ver</span>
                                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </a>
                                </div>
                            </div>


                            <!-- |||||||||||||||||||||||||||||||||||||||||  D E S P E S A ||||||||||||||||||||||||||||||||||||||||||||||||||||| -->  
                            
                            
                            <div class="col-lg-3 col-md-6">
                                <div class="panel panel-red">
                                    <div class="panel-heading">
                                        <div class="row">
                                            <div class="col-xs-3">
                                                <i class="fa fa-credit-card fa-5x"></i>
                                            </div>
                                            <div class="col-xs-9 text-right">
                                                <div class="huge">D</div>
                                                <div>Despesa</div>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="declarar_despesa.jsp">
                                        <div class="panel-footer">
                                            <span class="pull-left">Declarar</span>
                                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </a>
                                </div>
                            </div>


                            <!-- ||||||||||||||||||||||||||||||||||||||  I N V E S T I M E N T O S |||||||||||||||||||||||||||||||||||||||||||||||||||||||| -->                           
                            
                            <div class="col-lg-3 col-md-6">
                                <div class="panel panel-danger">
                                    <div class="panel-heading">
                                        <div class="row">
                                            <div class="col-xs-3">
                                                <i class="fa fa-xing fa-5x"></i>
                                            </div>
                                            <div class="col-xs-9 text-right">
                                                <div class="huge">I</div>
                                                <div>Investimentos</div>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="investimentos_declara.jsp">
                                        <div class="panel-footer">
                                            <span class="pull-left">Declarar</span>
                                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </a>
                                </div>
                            </div>


                            <!-- |||||||||||||||||||||||||||||||||||||||||F I N A N C A S ||||||||||||||||||||||||||||||||||||||||||||||||||||| -->  
                                

                            <div class="col-lg-3 col-md-6">
                                <div class="panel panel-info">
                                    <div class="panel-heading">
                                        <div class="row">
                                            <div class="col-xs-3">
                                                <i class="fa fa-bar-chart-o fa-5x"></i>
                                            </div>
                                            <div class="col-xs-9 text-right">
                                                <div class="huge">F$</div>
                                                <div>Finanças</div>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="financas.jsp">
                                        <div class="panel-footer">
                                            <span class="pull-left">Ver</span>
                                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </a>
                                </div>
                            </div>

                            <!-- |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| -->
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

