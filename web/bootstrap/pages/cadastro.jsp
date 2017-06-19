<%-- 
    Document   : login
    Created on : Apr 1, 2015, 2:27:14 PM
    Author     : joaosantos
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
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

        <script language="javascript" type="text/javascript">
            function validar2() {
                var nome = form2.txt_nome.value;
                if (nome === "") {
                    alert('Preencha o campo nome');
                    form2.txt_nome.focus();
                    return false;
                }
                var email = form2.txt_email.value;
                if (email === "") {
                    alert('Digite um email válido');
                    form2.txt_email.focus();
                    return false;
                }
                var email2 = form2.txt_email_conf.value;
                if (email2 === "") {
                    alert('Confirme o e-mail');
                    form2.txt_email_conf.focus();
                    return false;
                }
                if (email2 !== email) {
                    alert('Os e-mails digitados são diferentes');
                    form2.txt_email_conf.focus();
                    return false;
                }
                var senha = form2.txt_password.value;
                if (senha === "") {
                    alert('Preencha a senha');
                    form2.txt_password.focus();
                    return false;
                }
                form = document.forms[0]
                checkbox = form.checkbox
                if (concordo.checked === false) {
                    alert('Voce tem que concordar com os termos');
                    form2.li_concordo.focus();
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
        <% String Codmsg = request.getParameter("msg");
            if (Codmsg == null) {
                Codmsg = "";
            }
        %>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel panel-default">

                        <div class="panel-body">


                            <form role="form" name="form2" method="post" action="CadastroServlet">
                                <fieldset>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Seu Nome OU Nome de Sua Empresa" name="txt_nome" type="text" autofocus>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="E-mail" name="txt_email" type="email" >
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Confirme E-mail" name="txt_email_conf" type="email" value="">
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Senha" name="txt_password" type="password" value="">
                                    </div>
                                    
                                    <input class="btn btn-lg btn-success btn-block" type="submit" value="Cadastrar" onclick="return validar2()">

                                </fieldset>
                                
                                
                                <% if (Codmsg.equals("")) {%>
                                    <input id="concordo" type="checkbox" name="li_concordo" />  <a href="contrato_utilizacao.jsp" target="_blank">Eu li e concordo com os termos de utilização do serviço</a> 
                                     


                                <% } else if (Codmsg.equals("1")) {%>

                                    <h3 style="color: #669900; font-family:Gill, Helvetica, sans-serif "> Obrigado por se cadastrar em nosso sistema! Voce recebera um link para ativação de sua conta em seu e-mail 

                                <% } else if (Codmsg.equals("2")) {%>
                                
                                    <input id="concordo" type="checkbox" name="li_concordo" />  <a href="contrato_utilizacao.jsp" target="_blank">Eu li e concordo com os termos de utilização do serviço</a> </h3>
                                    <h2 style="color: #ff9966; font-family:Gill, Helvetica, sans-serif "> <%= request.getParameter("excep")%></h2>
                                    


                                <% } else if (Codmsg.equals("3")) {%>
                                
                                    <input id="concordo" type="checkbox" name="li_concordo" />  <a href="contrato_utilizacao.jsp" target="_blank">Eu li e concordo com os termos de utilização do serviço</a> </h3>
                                    <h2  style="color: #ff9966; font-family:Gill, Helvetica, sans-serif ">Erro no banco de dados. </h2>
                                   


                                <% } else if (Codmsg.equals("4")) {%>
                                           
                                    <input id="concordo" type="checkbox" name="li_concordo" />  <a href="contrato_utilizacao.jsp" target="_blank">Eu li e concordo com os termos de utilização do serviço</a> </h3>
                                    <h2 style="color: #ff9966; font-family:Gill, Helvetica, sans-serif ">Erro inexperado. </h2>
                                    


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