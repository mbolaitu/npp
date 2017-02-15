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
        return DriverManager.getConnection(dbUrl,user,pass);
    }
}
