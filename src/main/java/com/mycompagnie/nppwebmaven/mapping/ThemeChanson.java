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
public class ThemeChanson {
    private long id_theme;
    private long id_chanson;

    public long getId_theme() {
        return id_theme;
    }

    public final void setId_theme(long id_theme) {
        this.id_theme = id_theme;
    }

    public long getId_chanson() {
        return id_chanson;
    }

    public final void setId_chanson(long id_chanson) {
        this.id_chanson = id_chanson;
    }

    public ThemeChanson(long id_theme, long id_chanson) {
        this.setId_theme(id_theme);
        this.setId_chanson(id_chanson);
    }
    
    public boolean setThemeChanson() throws Exception {
        int reponse = 0;
        Connection c = Connexion.getConnection();
        String sql = "insert into theme_chanson";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setLong(1, this.id_theme);
        ps.setLong(2, id_chanson);
        reponse = ps.executeUpdate();
        c.commit();
        ps.clearParameters();
        c.close();
        return reponse>0;
    }
    
    public static long getLastIndex() throws Exception {
        Connection c = Connexion.getConnection();
        long reponse = -1;
        ResultSet rs = c.createStatement().executeQuery("select count(*) from theme_chanson");
        while(rs.next()) {
            reponse = rs.getLong(1);
        }
        return reponse+1;
    }
    
}
