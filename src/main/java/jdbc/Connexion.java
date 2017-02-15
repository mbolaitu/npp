package jdbc;
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
            connex = DriverManager.getConnection("jdbc:postgresql://localhost:5432/npp", "postgres", "itu");
        }
        return Connexion.connex;
    }
}
