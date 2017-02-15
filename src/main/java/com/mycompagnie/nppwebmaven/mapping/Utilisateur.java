/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagnie.nppwebmaven.mapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.mycompagnie.nppwebmaven.jdbc.Connexion;

/**
 *
 * @author itu
 */
public class Utilisateur {
    private long id_utilisateur;
    private String nom_utilisateur;
    private String prenom_utilisateur;

    public long getId_utilisateur() {
        return id_utilisateur;
    }

    public final void setId_utilisateur(long id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public final void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public String getPrenom_utilisateur() {
        return prenom_utilisateur;
    }

    public final void setPrenom_utilisateur(String prenom_utilisateur) {
        this.prenom_utilisateur = prenom_utilisateur;
    }

    public Utilisateur(long id_utilisateur, String nom_utilisateur, String prenom_utilisateur) {
        this.setId_utilisateur(id_utilisateur);
        this.setNom_utilisateur(nom_utilisateur);
        this.setPrenom_utilisateur(prenom_utilisateur);
    }
    
    public boolean setUtilisateur() throws Exception {
        Connection c = Connexion.getConnection();
        int reponse = 0;
        String sql = "insert into utilisateur values (?,?,?)";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setLong(1, this.id_utilisateur);
        ps.setString(2, this.nom_utilisateur);
        ps.setString(3, this.prenom_utilisateur);
        reponse = ps.executeUpdate();
        c.commit();
        ps.clearParameters();
        c.close();
        return reponse>0;
    }
    public boolean setUtilisateur(Connection c) throws Exception {
        int reponse = 0;
        String sql = "insert into utilisateur values (?,?,?)";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setLong(1, this.id_utilisateur);
        ps.setString(2, this.nom_utilisateur);
        ps.setString(3, this.prenom_utilisateur);
        reponse = ps.executeUpdate();
        ps.clearParameters();
        return reponse>0;
    }
    
    public static Utilisateur getUtilisateurById(long id) throws Exception {
        Utilisateur user = null;
        Connection c = Connexion.getConnection();
        String sql = "select * from utilisateur where id_user=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs==null) {
            throw new Exception("aucun utilisateur trouve");
        }
        while(rs.next()) {
            user = new Utilisateur(rs.getLong(1),rs.getString(2),rs.getString(3));
        }
        return user;
    }
    
    public static long getLastIndex() throws Exception {
        Connection c = Connexion.getConnection();
        long reponse = -1;
        ResultSet rs = c.createStatement().executeQuery("select count(*) from utilisateur");
        while(rs.next()) {
            reponse = rs.getLong(1);
        }
        return reponse+1;
    }
    
    public static String[] getInfo(long id) throws Exception {
        Connection c = Connexion.getConnection();
        String sql = "select l.url_photo,u.nom,u.prenom,l.email,g.gain from  utilisateur as u join gain as g on u.id_user=g.id_user join login as l on u.id_user=l.id_user where u.id_user=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        String[] rep = new String[5];
        while(rs.next()) {
            rep[0] = rs.getString(1);
            rep[1] = rs.getString(2);
            rep[2] = rs.getString(3);
            rep[3] = rs.getString(4);
            rep[4] = String.valueOf(rs.getDouble(5));
        }
        return rep;
    }
    public static ArrayList<String[]> getClassemmentMembre() throws Exception {
        Connection c = Connexion.getConnection();
        String sql = "select u.id_user,u.nom,u.prenom from  utilisateur as u join gain as g on u.id_user=g.id_user order by g.gain desc";
        ResultSet rs = c.createStatement().executeQuery(sql);
        ArrayList<String[]> rep = new ArrayList<String[]>();
        while(rs.next()) {
            String[] temp = new String[3];
            temp[0] = String.valueOf(rs.getLong(1));
            temp[1] = rs.getString(2);
            temp[2] = rs.getString(3);
            rep.add(temp);
        }
        return rep;
    }
    public static String getClassemment(long id) throws Exception {
        ArrayList<String[]> arr = getClassemmentMembre();
        String rep = null;
        for(int i=0;i<arr.size();i++) {
            if(Double.parseDouble(arr.get(i)[0])==id) {
                rep = String.valueOf(i+1);
            }
        }
        return rep;
    }
    
}
