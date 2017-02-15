/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagnie.nppwebmaven.mapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import com.mycompagnie.nppwebmaven.jdbc.Connexion;

/**
 *
 * @author itu
 */
public class Theme {
    private long id_theme;
    private String nom_theme;

    public long getId_theme() {
        return id_theme;
    }

    public final void setId_theme(long id_theme) {
        this.id_theme = id_theme;
    }

    public String getNom_theme() {
        return nom_theme;
    }

    public final void setNom_theme(String nom_theme) {
        this.nom_theme = nom_theme;
    }

    public Theme(long id_theme, String nom_theme) {
        this.setId_theme(id_theme);
        this.setNom_theme(nom_theme);
    }
    
    public boolean setTheme() throws Exception {
        int reponse = 0;
        Connection c = Connexion.getConnection();
        String sql = "insert into theme values (?,?)";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setLong(1, this.id_theme);
        ps.setString(2, this.nom_theme);
        reponse = ps.executeUpdate();
        c.commit();
        ps.clearParameters();
        c.close();
        return reponse>0;
    }
    
    public static Theme getThemeById(long id) throws Exception {
        Theme theme = null;
        Connection c = Connexion.getConnection();
        String sql = "select * from theme where id_theme=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs==null) {
            throw new Exception("aucun theme trouve");
        }
        while(rs.next()) {
            theme = new Theme(rs.getLong(1),rs.getString(2));
        }
        return theme;
    }
    
    public static long getLastIndex() throws Exception {
        Connection c = Connexion.getConnection();
        long reponse = -1;
        ResultSet rs = c.createStatement().executeQuery("select count(*) from theme");
        while(rs.next()) {
            reponse = rs.getLong(1);
        }
        return reponse+1;
    }
    
   
    
}
