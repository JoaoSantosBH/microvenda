<%-- 
    Document   : index.jsp
    Created on : Apr 3, 2015, 6:16:23 PM
    Author     : joaosantos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/grayscale.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top" style="background-color: #e8d03d;">

        <!-- Navigation -->
        <nav class="navbar navbar-custom navbar-fixed-top" style="background-color: transparent;" role="navigation">
            <div class="container" >
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                        <i class="fa fa-bars"></i>
                    </button>
                    <a class="navbar-brand page-scroll" href="#page-top">
                        <i class="fa fa-play-circle"></i>  <span class="light">Inicio</span> 
                    </a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
                    <ul class="nav navbar-nav">
                        <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                        <li class="hidden">
                            <a href="#page-top"></a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#about">O que é</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#download">Missão</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="mailto:contato@microvenda.com.br">Contato</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

        <!-- Intro Header -->
        <header class="intro" >

            <div class="intro-body" >
                <div class="container" >

                    <div class="row">

                        <div class="col-md-8 col-md-offset-2">

                            <h1>microVENDA</h1>


                            <!-- L O G I N E C A D A S T R  O  -->
                            <form method="POST" action="/bootstrap/pages/login.jsp">
                                <input class="btn btn-lg btn-success btn-block" type="submit" value="sou_Vendedor">
                            </form>
                            <form method="POST" action="/bootstrap/pages/cliente/login.jsp">
                                <input class="btn btn-lg btn-success btn-block" type="submit" value="sou_Cliente" >
                            </form>
                            <form method="POST" action="/bootstrap/pages/cadastro.jsp">
                                <input class="btn btn-lg btn-success btn-block" type="submit" value="Criar Meu negógio">
                            </form>


                            <a href="#about" class="btn btn-circle page-scroll">
                                <i class="fa fa-angle-double-down animated"></i>
                            </a>





                        </div>
                    </div>
                </div>
            </div>
        </header>

        <!-- About Section -->
        <section id="about" class="container content-section text-center" style="background-color: #e8d03d; color: green">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2" style="background-color: #e8d03d;">
                    <h2>microVenda</h2><p>
                  MicroVenda é um sistema de gestão financeira para vendedores autônomos, representantes comerciais, micro empresas de varejo ou pequenos negócios. Funciona como o velho "bloquinho de anotações", só que é claro, com muita tecnologia aliada! 
MicroVenda pode ser acessado de smartphones, tablets e computadores, independente de sistemas operacionais, basta apenas uma conexão com a internet.
A grande vantagem de um sistema de gestão na nuvem é poder usar de qualquer dispositivo conectado à internet.
No mundo real se você perde seu “bloco de anotações’ ou caderno ou seja lá o que for, você perde toda sua informação, mas com o MicroVenda, isto é impossível, se perder seu dispositivo, você não perde nada, basta realizar o login de outro dispositivo, e tudo estara lá como antes! Nada se perde! 
MicroVenda é o sistema que faltava para você. 
Ah e o melhor, TUDO isso por uma mensalidade de 39,90 por mês. </p>


                </div>
            </div>
        </section>

        <!-- Download Section -->
        <section id="download" class="content-section text-center" style="background-color: #e8d03d;">
            <div class="download-section" style="background-color: #e8d03d;">
                <div class="container" >
                    <div class="col-lg-8 col-lg-offset-2">
                        <h2>Sistema que Roda na Nuvem</h2>
                        <p>Fornecer um sistema de gestão de vendas na nuvem para o vendedor autônomo, compatível com qualquer dispositivo que acesse a internet. </p>

                    </div>
                </div>
            </div>
        </section>



        <!-- Footer -->
        <footer>
            <div class="container text-center" style="background-color: #e8d03d;">
                <p>Copyright &copy; microVENDA 2015</p>
            </div>
        </footer>

        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <!-- Plugin JavaScript -->
        <script src="js/jquery.easing.min.js"></script>

        <!-- Google Maps API Key - Use your own API key to enable the map feature. More information on the Google Maps API can be found at https://developers.google.com/maps/ -->
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCRngKslUGJTlibkQ3FkfTxj3Xss1UlZDA&sensor=false"></script>

        <!-- Custom Theme JavaScript -->
        <script src="js/grayscale.js"></script>

    </body>

</html>
