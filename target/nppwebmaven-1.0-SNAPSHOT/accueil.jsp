<%-- 
    Document   : accueil
    Created on : 9 févr. 2017, 22:31:07
    Author     : mbola
--%>

<%@page import="com.mycompagnie.nppwebmaven.mapping.Chanson"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompagnie.nppwebmaven.mapping.Utilisateur" %>
<% 
    System.out.println(request.getSession().getAttribute("id_user"));
    if(request.getSession().getAttribute("id_user")==null) {
        response.sendRedirect("index.html");
        //out.println(a);
    }else{ 
%>
<!DOCTYPE html>

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
                        <li><a href="deconnexion" style="color: white;"><span class="glyphicon glyphicon-log-out"></span> Se déconnecter</a></li>
                    </ul>
                </div>
            </nav>
            <div class="row">
                <div class="clo-md-12" style="margin-left: 15px;">
                    <h1 style="color: white;">N'OUBLIEZ PAS LES PAROLES</h1>
                    <ul class="nav nav-pills">
                      <li class="active"><a data-toggle="pill" href="#profil">Profil</a></li>
                      <li><a data-toggle="pill" href="#menu1">Classemment</a></li>
                      <li><a data-toggle="pill" href="#menu2">Chanson</a></li>
                    </ul>

                    <div class="tab-content" style="margin-top: 20px;">
                        <div id="profil" class="tab-pane fade in active">
                            <div class="panel panel-primary">
                                <div class="panel-heading">Information personelle</div>
                                <div class="panel-body">
                                    <div class="row">
                                        <% 
                                        long id = (long)request.getSession().getAttribute("id_user");
                                        String[] rep = Utilisateur.getInfo(id); %>
                                        <div class="col-md-4">
                                            <img src="<%= "img/"+ rep[0] %>" class="img-thumbnail" alt="" width="204" height="136">
                                        </div>
                                        <div class="col-md-8">
                                            
                                            <label>Nom utilisateur : </label><%= rep[1] %> <br><br>
                                            <label>Prénom utilisateur : </label><%= rep[2] %> <br><br>
                                            <label>Adresse email : </label><%= rep[3] %><br><br>
                                            <label>Gain : </label><span class="badge"><%= rep[4]+" Ar" %></span> <br><br>
                                            <label>Classemment : </label><span class="badge"><%= Utilisateur.getClassemment(id) %></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="menu1" class="tab-pane fade">
                            <div class="panel panel-primary">
                                <div class="panel-heading">Classement membre</div>
                                <div class="panel-body">
                                    <div class="row">
                                        <% ArrayList<String[]> classemment = Utilisateur.getClassemmentMembre(); %>
                                        <% for(int i=0;i<classemment.size();i++) { %>
                                        <a href="<%= "profil.jsp?id="+classemment.get(i)[0] %>"><span class="badge"><%= i+1 %></span><label><%= (classemment.get(i)[1])+" "+(classemment.get(i)[2]) %></label></a><br>
                                        <% } %>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="menu2" class="tab-pane fade">
                            <div class="panel panel-primary">
                                <div class="panel-heading">Liste Chansons</div>
                                <%  ArrayList<Chanson> liste_chanson = Chanson.getAllChanson(); %>
                                <div class="panel-body">
                                    <div class="row">
                                        <% for(int j=0;j<liste_chanson.size();j++) { %>
                                        <a href="<%= "profil_musique.jsp?id="+liste_chanson.get(j).getId_chanson() %>"><span class="badge"><%= liste_chanson.get(j).getPrix_chanson()+" Ar" %></span><label><%= liste_chanson.get(j).getTitre() %></label></a><br>
                                        <% } %>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
<% } %>
