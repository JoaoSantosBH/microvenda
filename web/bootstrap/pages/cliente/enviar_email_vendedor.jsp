<%-- 
    Document   : indexc
    Created on : Sep 11, 2015, 2:20:56 PM
    Author     : joaosantos
--%>


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

                            <% if (Codmsg.equals("1")) {%>
                            <script>window.alert('Mensagem Envidada com Sucesso!')</script>
                            <%} else if (Codmsg.equals("4")) {%>
                            <script>window.alert('Ocorreu um Erro: <%= Erro%>')</script>
                            <%}%>



                            <form role="form" method="POST" name="emailform" action="EnviarEmailClienteParaVendedorEspecificoServlet">


                                <div class="form-group">
                                    <label>Titulo</label>
                                    <input class="form-control" type="text" name="subject" placeholder="Titulo do Email">
                                </div>

                                <div class="form-group">
                                    <label>Mensagem</label>
                                    <textarea class="form-control" rows="3" maxlength="500" name="msg" placeholder="Sua Mensagem"></textarea>
                                </div>                                    

                                <input type="hidden" value="<%= Logado.getIdCliente()%>" name="id_cliente">
                                
                                <div class="form-group">
                                    <input class="btn btn-lg btn-success btn-block" type="submit" value="Enviar Email" onclick="return validarEmail()">
                                </div>


                            </form>
                            <script language="javascript" type="text/javascript">
                                function validarEmail() {
                                    
                                    var msg = emailform.msg.value;
                                    var titulo = emailform.subject.value;
                                    if (titulo === "") {
                                        alert('Digite um titulo para sua mensagems');
                                        emailform.subject.focus();
                                        return false;
                                    } else if (msg === "") {
                                        alert('Digite uma mensagem')
                                        emailform.msg.focus();
                                        return false;
                                    } else {
                                        return confirm('Deseja enviar sua mensagem agora?');
                                    }

                                }
                            </script>

                            <!-- /.col-lg-6 (nested) -->
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

