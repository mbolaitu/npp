/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import jdbc.Connexion;

/**
 *
 * @author itu
 */
public class Chanson {
    private long id_chanson;
    private String titre;
    private String chanteur;
    private int annee;
    private String url_chanson;
    private String url_photo_chanteur;
    private int prix_chanson;

    public long getId_chanson() {
        return id_chanson;
    }

    public final void setId_chanson(long id_chanson) {
        this.id_chanson = id_chanson;
    }

    public String getTitre() {
        return titre;
    }

    public final void setTitre(String titre) {
        this.titre = titre;
    }

    public String getChanteur() {
        return chanteur;
    }

    public final void setChanteur(String chanteur) {
        this.chanteur = chanteur;
    }

    public int getAnnee() {
        return annee;
    }

    public final void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getUrl_chanson() {
        return url_chanson;
    }

    public final void setUrl_chanson(String url_chanson) {
        this.url_chanson = url_chanson;
    }

    public String getUrl_photo_chanteur() {
        return url_photo_chanteur;
    }

    public final void setUrl_photo_chanteur(String url_photo_chanteur) {
        this.url_photo_chanteur = url_photo_chanteur;
    }

    public int getPrix_chanson() {
        return prix_chanson;
    }

    public final void setPrix_chanson(int prix_chanson) {
        this.prix_chanson = prix_chanson;
    }

    public Chanson(long id_chanson, String titre, String chanteur, int annee, String url_chanson, String url_photo_chanteur,int prix_chanson) {
        this.setId_chanson(id_chanson);
        this.setTitre(titre);
        this.setChanteur(chanteur);
        this.setAnnee(annee);
        this.setUrl_chanson(url_chanson);
        this.setUrl_photo_chanteur(url_photo_chanteur);
        this.setPrix_chanson(prix_chanson);
    }
    
    public boolean setChanson() throws Exception {
        int reponse = 0;
        Connection c = Connexion.getConnection();
        String sql = "insert into chanson values (?,?,?,?,?,?,?)";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setLong(1, this.id_chanson);
        ps.setString(2, this.titre);
        ps.setString(3, this.chanteur);
        ps.setInt(4, this.annee);
        ps.setString(5, this.url_chanson);
        ps.setString(6, this.url_photo_chanteur);
        ps.setInt(7, this.prix_chanson);
        reponse = ps.executeUpdate();
        c.commit();
        ps.clearParameters();
        c.close();
        return reponse>0;
    }
    
    public static Chanson getChansonById (long id) throws Exception {
        Chanson chanson = null;
        Connection c = Connexion.getConnection();
        String sql = "select * from chanson where id_chanson=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs==null) {
            throw new Exception("aucun chanson trouve");
        }
        while(rs.next()) {
            chanson = new Chanson(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(9));
        }
        return chanson;
    }
    
    public static long getLastIndex() throws Exception {
        Connection c = Connexion.getConnection();
        long reponse = -1;
        ResultSet rs = c.createStatement().executeQuery("select count(*) from chanson");
        while(rs.next()) {
            reponse = rs.getLong(1);
        }
        return reponse+1;
    }
    
    public static int getRandomWithExclusion(Random rnd, int start, int end, ArrayList<Integer> exclude) {
    int random = start + rnd.nextInt(end - start + 1 - exclude.size());
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }
    
    public static Chanson[] getRandomChansonsTheme(ThemePoint theme,ArrayList<Chanson> list) throws SQLException, Exception{
        ArrayList<Chanson> listChanson = new ArrayList();
        ArrayList<Integer> exclude = new ArrayList();
        Connection c = Connexion.getConnection();
        String query = "select * from chanson join theme_chanson where idTheme = "+theme.getId_theme();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            boolean test_chanson = false;
            for(int i =0;i<list.size();i++) {
                if(rs.getLong(1)==list.get(i).getId_chanson()) {
                    test_chanson = true;
                    break;
                }
            }
            if(!test_chanson) {
                Chanson temp = new Chanson(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(9));
                listChanson.add(temp);
            }
        }
        Chanson[] randomChansons = new Chanson[2];
        Random r = new Random();
        int nombreChansons = listChanson.size();
        for(int i=0; i < randomChansons.length ; i++){
            int rand = getRandomWithExclusion(r, 0, nombreChansons, exclude);
            randomChansons[i] = listChanson.get(rand);
            exclude.add(rand);
        }
        return randomChansons;
    }
    
    public static Chanson getRandomChansons(ArrayList<Chanson> list) throws SQLException, Exception{
        ArrayList<Chanson> listChanson = new ArrayList();
        ArrayList<Integer> exclude = new ArrayList();
        Connection c = Connexion.getConnection();
        String query = "select * from chanson join theme_chanson";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            boolean test_chanson = false;
            for(int i =0;i<list.size();i++) {
                if(rs.getLong(1)==list.get(i).getId_chanson()) {
                    test_chanson = true;
                    break;
                }
            }
            if(!test_chanson) {
                Chanson temp = new Chanson(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(9));
                listChanson.add(temp);
            }
        }
        int nombreChansons = listChanson.size();
        Chanson randomChansons = null;
        Random r = new Random();
        int rand = getRandomWithExclusion(r, 0, nombreChansons, exclude);
        randomChansons = listChanson.get(rand);
        return randomChansons;
    }
    
    public static ArrayList<Chanson> getAllChanson() throws Exception {
        ArrayList<Chanson> rep = new ArrayList<>();
        Connection c = Connexion.getConnection();
        String query = "select * from chanson";
        ResultSet rs = c.createStatement().executeQuery(query);
        while(rs.next()) {
            rep.add(new Chanson(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(7)));
        }
        return rep;
    }
}
