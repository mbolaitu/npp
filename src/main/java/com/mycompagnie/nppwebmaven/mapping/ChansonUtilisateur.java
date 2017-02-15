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
public class ChansonUtilisateur {
    private long id_chanson;
    private long id_utilisateur;

    public long getId_chanson() {
        return id_chanson;
    }

    public final void setId_chanson(long id_chanson) {
        this.id_chanson = id_chanson;
    }

    public long getId_utilisateur() {
        return id_utilisateur;
    }

    public final void setId_utilisateur(long id_user) {
        this.id_utilisateur = id_user;
    }

    public ChansonUtilisateur(long id_chanson, long id_user) {
        this.setId_chanson(id_chanson);
        this.setId_utilisateur(id_user);
    }
    
    public boolean setChansonUtilisateur() throws Exception {
        int reponse = 0;
        Connection c = Connexion.getConnection();
        String sql = "insert into chanson_utilisateur values (?,?)";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setLong(1, this.id_chanson);
        ps.setLong(2, this.id_utilisateur);
        reponse = ps.executeUpdate();
        c.commit();
        ps.clearParameters();
        c.close();
        return reponse>0;
    }
    
    public static long getLastIndex() throws Exception {
        Connection c = Connexion.getConnection();
        long reponse = -1;
        ResultSet rs = c.createStatement().executeQuery("select count(*) from chanson_utilisateur");
        while(rs.next()) {
            reponse = rs.getLong(1);
        }
        return reponse+1;
    }
    
    public static Chanson[] getListeChansonUtilisateur(long id) throws Exception {
        ArrayList<Chanson> arr_chanson = new ArrayList();
        String sql = "select id_chanson from chanson_utilisateur where id_utilisateur=?";
        Connection c = Connexion.getConnection();
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs==null) {
            throw new Exception("aucun chanson trouve");
        }
        while(rs.next()) {
            arr_chanson.add(Chanson.getChansonById(rs.getLong(1)));
        }
        if(arr_chanson.size()==0) {
            throw new Exception("erreur copie chanson");
        }
        return (Chanson[])arr_chanson.toArray();
    }
    
}
