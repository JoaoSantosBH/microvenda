<%-- 
    Document   : login
    Created on : Apr 1, 2015, 2:27:14 PM
    Author     : joaosantos
--%>
<%@page import="entidade.UsuarioEntidade"%>
<%@page import="persistencia.UsuarioDAO"%>
<%@page import="util.MD5"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html lang="en">

    <head>

        <meta charset="utf-8">
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

        <script language="javascript" type="text/javascript">
            function validar() {
                var email = form1.txt_email.value;
                if (email === "") {
                    alert('Digite um email válido');
                    form1.txt_email.focus();
                    return false;
                }
                var senha = form1.txt_password.value;
                if (senha === "") {
                    alert('Preencha a senha');
                    form1.txt_password.focus();
                    return false;
                }


            }
        </script>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>
    <%
        UsuarioEntidade Autenticado = null;
        String nome = "";
        String Erro = request.getParameter("e");
        if (Erro == null) {
            Erro = "";
        }
        String email = request.getParameter("e-mail");
        String hash = request.getParameter("hash");

        Integer id = UsuarioDAO.pegarIdPorEmail(email);
        MD5 md5 = new MD5();
        String hash_teste = md5.criarMD5ParaEnviarLink(id.toString());

        if (hash.equals(hash_teste)) {

            UsuarioDAO.validarUsuarioPorLinkEmail(email);
            nome = UsuarioDAO.pegarAtributoNomeDeUsuario(email);
        }

    %>



    <body>
        <% String Codmsg = request.getParameter("msg");
            if (Codmsg == null) {
                Codmsg = "";
            }
        %>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Login</h3>
                        </div>
                        <div class="panel-body">


                            <form role="form" name="form1" method="post" action="LoginServlet">
                                <fieldset>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="E-mail" name="txt_email" type="email" autofocus>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Senha" name="txt_password" type="password" value="">
                                    </div>

                                    <input class="btn btn-lg btn-success btn-block" type="submit" value="Login" onclick="return validar()">

                                </fieldset>


                                <%if (hash.equals(hash_teste)) {%>
                                <h3>Obrigado <%= nome%> <br/>agora você já pode logar em nosso sistema! Seja Bem Vindo!
                                    <%} else {%>
                                    <h3 style="color: red;">Não foi possivel validar seu cadastro! Por favor verifique se seu e-mail está correto!  

                                        <%}%> 




                                        </form>
                                        <form action="/bsgrey/index.jsp">
                                            <button type="submit" class="btn btn-lg btn-success btn-block" >voltar</button>
                                        </form>



                                        </div>
                                        </div>
                                        </div>
                                        </div>
                                        </div>

                                        <!-- jQuery -->
                                        <script src="../bower_components/jquery/dist/jquery.min.js"></script>

                                        <!-- Bootstrap Core JavaScript -->
                                        <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

                                        <!-- Metis Menu Plugin JavaScript -->
                                        <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

                                        <!-- Custom Theme JavaScript -->
                                        <script src="../dist/js/sb-admin-2.js"></script>

                                        </body>

                                        </html>
                                        <!DOCTYPE html>