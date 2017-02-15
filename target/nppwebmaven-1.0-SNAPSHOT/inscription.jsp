<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%  String erreur = (String)request.getAttribute("error");
    out.println(erreur);
%>
<html>
    <head>
        <title>npp</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/style.css">
        <!-- jQuery library -->
        <script src="js/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="header">
            <center><img src="img/jeux.png" alt=""></center>
        </div>
        <div class="login">
            <h1>N'OUBLIEZ PAS LES PAROLES</h1>
            <h1>Inscription</h1>
            <form method="post" action="Inscription" enctype="multipart/form-data">
                <input type="text" name="name" placeholder="Nom" />
                <input type="text" name="lastname" placeholder="Prénom"/>
                <input type="text" name="username" placeholder="Nom d'utilisateur"/>
                <input type="password" name="password" placeholder="Mot de passe" required="required" />
                <input type="password" name="confpassword" placeholder="Confirmation du mot de passe" required="required" />
                <input type="email" name="email" placeholder="Email" required="required" />
                <input type="file" name="photo" placeholder="url photo" required="required" />
                <button type="submit" class="btn btn-primary btn-block btn-large">Enregistrer</button>
                <a href="index.html" style="color: white;">Page de connexion</a>
            </form>
	</div>
        <% if(request.getParameter("error")!=null){ %>
        <div id="erreur" class="alert alert-danger" style="display: none;">
            <strong>Erreur!</strong> Veuillez verifier le(s) champ(s)
        </div>
        <% } %>
        <script src="js/index.js"></script>
        <script src="js/prefixfree.min.js"></script>
    </body>
</html>
