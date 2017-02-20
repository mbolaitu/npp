package com.mycompagnie.nppwebmaven.jdbc;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;


public class Connexion 
{
    private static Connection connex = null;

    public static Connection getConnection() throws Exception
    {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String user = dbUri.getUserInfo().split(":")[0];
        String pass = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath();
        connex =DriverManager.getConnection(dbUrl,user,pass);
        connex.setAutoCommit(false);
        return connex;
    }
    
    /*public static Connection getConnection() throws Exception
    {
        if(Connexion.connex==null || (Connexion.connex!=null && Connexion.connex.isClosed()))
        {
            Class.forName("org.postgresql.Driver");
            connex = DriverManager.getConnection("jdbc:postgresql://localhost:5432/npp", "postgres", "itu");
            connex.setAutoCommit(false);
        }
        return Connexion.connex;
    }*/
   
}
