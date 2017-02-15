/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagnie.nppwebmaven.mapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import com.mycompagnie.nppwebmaven.jdbc.Connexion;

/**
 *
 * @author mbola
 */
public class ThemePoint extends Theme {

    private int point;
    private Chanson[] liste_chanson;

    public Chanson[] getListe_chanson() {
        return liste_chanson;
    }

    public void setListe_chanson(ArrayList<Chanson> exclude) throws Exception {
        this.liste_chanson = Chanson.getRandomChansonsTheme(this, exclude);
    }
    
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
    
    public ThemePoint(Theme theme, int point,ArrayList<Chanson> exclude) {
       super(theme.getId_theme(), theme.getNom_theme());
       this.setPoint(point);
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
    
    public static ThemePoint[] getRandomThemes(ArrayList<Chanson> chanson_exclude) throws SQLException, Exception{
        ArrayList<Theme> listTheme = new ArrayList();
        
        Connection c = Connexion.getConnection();
        String query = "select * from theme";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Theme temp = new Theme(rs.getInt(1),rs.getString(2));
            listTheme.add(temp);
        }
        ThemePoint[] randomThemes = new ThemePoint[5];
        Random r = new Random();
        int nombreThemes = listTheme.size();
        
        ArrayList<Integer> exclude = new ArrayList();
        int point = 50;
        for(int i=0; i < randomThemes.length ; i++){
            int rand = getRandomWithExclusion(r, 0, nombreThemes, exclude);
            randomThemes[i] = new ThemePoint(listTheme.get(rand),point,chanson_exclude);
            exclude.add(rand);
            point = point-10;
        }
        return randomThemes;
    }
    
}
