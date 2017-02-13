<%-- 
    Document   : accueil
    Created on : 9 févr. 2017, 22:31:07
    Author     : mbola
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="mapping.Utilisateur" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/style.css">
        <!-- jQuery library -->
        <script src="js/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="js/bootstrap.min.js"></script>
        <title>npp</title>
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-inverse" style="margin-top: 20px;background-color: #265CFF">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="accueil.jsp" style="color: white;">N'OUBLIEZ PAS LES PAROLES</a>
                    </div>
                    <ul class="nav navbar-nav" >
                        <li class="active"><a href="#">Utilisateur</a></li>
                        <li><a href="#" style="color: white;">Razafindramiandra Mbola</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="inscription.jsp" style="color: white;"><span class="glyphicon glyphicon-user"></span> Créer un compte</a></li>
                        <li><a href="index.html" style="color: white;"><span class="glyphicon glyphicon-log-out"></span> Se déconnecter</a></li>
                    </ul>
                </div>
            </nav>
            <div class="row">
                <div class="panel panel-primary" style="margin-left: 15px;">
                    <div class="panel-heading">Information personelle</div>
                    <div class="panel-body">
                        <div class="row">
                            <% String[] rep = Utilisateur.getInfo(Long.parseLong(request.getParameter("id"))); %>
                            <div class="col-md-4">
                                <img src="<%= "img/"+ rep[0] %>" class="img-thumbnail" alt="" width="204" height="136">
                            </div>
                            <div class="col-md-8">

                                <label>Nom utilisateur : </label><%= rep[1] %> <br><br>
                                <label>Prénom utilisateur : </label><%= rep[2] %> <br><br>
                                <label>Adresse email : </label><%= rep[3] %><br><br>
                                <label>Gain : </label><span class="badge"><%= rep[4]+" Ar" %></span> <br><br>
                                <label>Classemment : </label><span class="badge"><%= Utilisateur.getClassemment(Long.parseLong(request.getParameter("id"))) %></span>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </body>
</html>
