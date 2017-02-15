/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagnie.nppwebmaven.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mycompagnie.nppwebmaven.jdbc.Connexion;
import com.mycompagnie.nppwebmaven.mapping.Gain;
import com.mycompagnie.nppwebmaven.mapping.Login;
import com.mycompagnie.nppwebmaven.mapping.Utilisateur;

/**
 *
 * @author itu
 */
public class Authentification {
    public static boolean sinscrire(String nm,String prenm,String username,String password,String email,String url_photo) throws Exception {
        boolean reponse = false; 
        Utilisateur user = new Utilisateur(Utilisateur.getLastIndex(),nm,prenm);
        Connection c = Connexion.getConnection();
        if(user.setUtilisateur(c)) {
            Login login = new Login(Login.getLastIndex(),user.getId_utilisateur(),username,password,email,url_photo);
            if(login.setLogin(c)) {
                Gain gain = new Gain(Gain.getLastIndex(),user.getId_utilisateur(),0);
                if(gain.setGain(c)) {
                    reponse = true;
                    c.commit();
                    c.close();
                }
                else {
                    c.rollback();
                    c.close();
                }
            }
            else {
                c.rollback();
                c.close();
            }
        }
        else {
            c.rollback();
            c.close();
        }
        return reponse;
    }
    public static boolean sauthentifie(Connection c,String username,String password) throws Exception {
        int reponse = 0;
        String sql = "select count(id_login) from login where username=? and password=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            reponse = rs.getInt(1);
        }
        return reponse==1;
    }
}
