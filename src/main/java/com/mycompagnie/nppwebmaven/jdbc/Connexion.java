package com.mycompagnie.nppwebmaven.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;


public class Connexion 
{
    private static Connection connex = null;

    public static Connection getConnection() throws Exception
    {
        if(Connexion.connex==null || (Connexion.connex!=null && Connexion.connex.isClosed()))
        {
            Class.forName("org.postgresql.Driver");
            connex = DriverManager.getConnection("jdbc:postgres://qkdmwpqgytqbih:de925d71282b8f8800a7b0f5170d143ac5f0603bb5835bd9b7645948ec5c77bb@ec2-23-21-111-81.compute-1.amazonaws.com:5432/d3pkkno8gmsnla", "qkdmwpqgytqbih", "de925d71282b8f8800a7b0f5170d143ac5f0603bb5835bd9b7645948ec5c77bb");
        }
        return Connexion.connex;
    }
}
