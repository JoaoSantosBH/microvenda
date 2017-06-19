<%-- 
    Document   : login
    Created on : Apr 1, 2015, 2:27:14 PM
    Author     : joaosantos
--%>

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
    <% String Erro = request.getParameter("e");
        if (Erro == null) {
            Erro = "";
        }

    %>

    <body>

        <div class="container"  >
            <div class="row">

                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel panel-default"> <!--   -->

                        <div class="panel-body" >

<!--                            <h5 style="color:red;">Sua Sessão Expirou! Faça o Login!</h5>-->
                            <form role="form" name="form1" method="post" action="LoginServlet">
                                <fieldset>
                                    
                                    <div class="form-group">
                                        <input class="form-control" placeholder="E-mail" name="txt_email" type="email" autofocus>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Senha" name="txt_password" type="password" value="">
                                    </div>

                                    <input class="btn btn-lg btn-success btn-block" type="submit" value="Entrar" onclick="return validar()">

                                </fieldset>

                                <% if (Erro.equals("1")) {%>
                                <h3 style="color:red; font-family:Gill, Helvetica, sans-serif"> Nome ou Senha Incorretos. </h3> 

                                <% }
                                if (Erro.equals("2")) {%>
                                <h3 style="color: red; font-family:Gill, Helvetica, sans-serif "> Erro no Banco de Dados. Tente mais tarde</h3> 

                                <% } else if (Erro.equals("3")) {%>
                                <h3 style="color: red; font-family:Gill, Helvetica, sans-serif "> Usuario nao confirmado, cheque seu email </h3>
                                <% }%>

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