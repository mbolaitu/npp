/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagnie.nppwebmaven.mapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.mycompagnie.nppwebmaven.jdbc.Connexion;

/**
 *
 * @author itu
 */
public class Login {
    private long id_login;
    private long id_utilisateur;
    private String username;
    private String password;
    private String email;
    private String url_photo;

    public long getId_login() {
        return id_login;
    }

    public final void setId_login(long id_login) {
        this.id_login = id_login;
    }

    public long getId_utilisateur() {
        return id_utilisateur;
    }

    public final void setId_utilisateur(long id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getUsername() {
        return username;
    }

    public final void setUsername(String username) throws Exception {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    /*public final void setPassword(String password) throws Exception {
        if(testmdp(this.password)) {
            this.password = password;
        }
        else {
            throw new Exception("le mot de passe doit contenir au moins 1(chiffre,majcule,caractere speciale) et au moins 8 caracteres");
        }
    }*/
    public final void setPassword(String password) throws Exception {
            this.password = password;
    }

    public String getEmail() {
        return email;
    }

    /*public final void setEmail(String email) throws Exception {
        if(testemail(email)) {
            this.email = email;
        }
        else {
            throw new Exception("mail invalide");
        }
    }*/
    public final void setEmail(String email) throws Exception {
            this.email = email;
    }

    public String getUrl_photo() {
        return url_photo;
    }

    public final void setUrl_photo(String url_photo) {
        this.url_photo = url_photo;
    }

    public Login(long id_login, long id_utilisateur, String username, String password, String email, String url_photo) throws Exception {
        this.setId_login(id_login);
        this.setId_utilisateur(id_utilisateur);
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setUrl_photo(url_photo);
    }
    
    public boolean setLogin() throws Exception {
        int reponse = 0;
        Connection c = Connexion.getConnection();
        String sql = "insert into login values (?,?,?,?,?,?)";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setLong(1, this.id_login);
        ps.setLong(2, this.id_utilisateur);
        ps.setString(3, this.username);
        ps.setString(4, this.password);
        ps.setString(5, this.email);
        ps.setString(6, this.url_photo);
        reponse = ps.executeUpdate();
        c.commit();
        ps.clearParameters();
        c.close();
        return reponse>0;
    }
    public boolean setLogin(Connection c) throws Exception {
        int reponse = 0;
        String sql = "insert into login values (?,?,?,?,?,?)";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setLong(1, this.id_login);
        ps.setLong(2, this.id_utilisateur);
        ps.setString(3, this.username);
        ps.setString(4, this.password);
        ps.setString(5, this.email);
        ps.setString(6, this.url_photo);
        reponse = ps.executeUpdate();
        ps.clearParameters();
        return reponse>0;
    }
    
    public static Login getLoginById(long id) throws Exception {
        Login login = null;
        Connection c = Connexion.getConnection();
        String sql = "select * from login where id_login=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs==null) {
            throw new Exception("aucun login trouve");
        }
        while(rs.next()) {
            login = new Login(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
        }
        return login;
    }
    
    public static long getLastIndex() throws Exception {
        Connection c = Connexion.getConnection();
        long reponse = -1;
        ResultSet rs = c.createStatement().executeQuery("select count(*) from login");
        while(rs.next()) {
            reponse = rs.getLong(1);
        }
        return reponse+1;
    }
    public static boolean testmdp(String mdp) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$");
        Matcher m = pattern.matcher(mdp);
        return m.find();
    }
    public static boolean testemail(String email) {
        Pattern pattern = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$");
        Matcher m = pattern.matcher(email);
        return m.find();
    }
    public static boolean testusername(String username) throws Exception {
        String sql = "select count(*) from login where username=?";
        Connection c = Connexion.getConnection();
        int reponse = -1;
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if(rs==null) {
            throw new Exception("Aucun login trouve");
        }
        while(rs.next()) {
            reponse = rs.getInt(1);
        }
        return reponse==0;
    }
    public static Login getLogin(String username,String password) throws Exception {
        Login reponse = null;
        Connection c = Connexion.getConnection();
        String sql = "select * from login where username='"+username+"' and password='"+password+"'";
        PreparedStatement ps = c.prepareStatement(sql);
       //ps.setString(1, username);
        //ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            reponse = new Login(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
        }
        return reponse;
    }
    
}
