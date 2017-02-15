/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagnie.nppwebmaven.mapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mycompagnie.nppwebmaven.jdbc.Connexion;

/**
 *
 * @author itu
 */
public class Gain {
    private long id_gain;
    private long id_utilisateur;
    private int gain;

    public long getId_gain() {
        return id_gain;
    }

    public final void setId_gain(long id_gain) {
        this.id_gain = id_gain;
    }

    public long getId_utilisateur() {
        return id_utilisateur;
    }

    public final void setId_utilisateur(long id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getGain() {
        return gain;
    }

    public final void setGain(int gain) {
        this.gain = gain;
    }

    public Gain(long id_gain, long id_utilisateur, int gain) {
        this.setId_gain(id_gain);
        this.setId_utilisateur(id_utilisateur);
        this.setGain(gain);
    }
    
    public boolean setGain() throws Exception {
        int reponse = 0;
        Connection c = Connexion.getConnection();
        String sql = "insert into gain values (?,?,?)";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setLong(1, this.id_gain);
        ps.setLong(2, this.id_utilisateur);
        ps.setInt(3, this.gain);
        reponse = ps.executeUpdate();
        c.commit();
        ps.clearParameters();
        c.close();
        return reponse>0;
    }
    public boolean setGain(Connection c) throws Exception {
        int reponse = 0;
        String sql = "insert into gain values (?,?,?)";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setLong(1, this.id_gain);
        ps.setLong(2, this.id_utilisateur);
        ps.setInt(3, this.gain);
        reponse = ps.executeUpdate();
        ps.clearParameters();
        return reponse>0;
    }
    
    public static Gain getGainById(long id) throws Exception {
        Gain gain = null;
        Connection c = Connexion.getConnection();
        String sql = "select * from gain where id_gain=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs==null) {
            throw new Exception("aucun gain trouve");
        }
        while(rs.next()) {
            gain = new Gain(rs.getLong(1),rs.getLong(2),rs.getInt(3));
        }
        return gain;
    }
    public static long getLastIndex() throws Exception {
        Connection c = Connexion.getConnection();
        long reponse = -1;
        ResultSet rs = c.createStatement().executeQuery("select count(*) from gain");
        while(rs.next()) {
            reponse = rs.getLong(1);
        }
        return reponse+1;
    }
    
}
