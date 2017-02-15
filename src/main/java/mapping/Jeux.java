/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mbola
 */
public class Jeux {
    private UtilisateurScore j1;
    private UtilisateurScore j2;
    private int tour ; // vita
    private ArrayList<Chanson> exclude; // vita
    private ArrayList<ThemePoint> theme_exclude;
    private ThemePoint [] liste_theme;
    private InfoUtilisateur info_j1; // vita
    private InfoUtilisateur info_j2; // vita
    private Chanson chanson_final; // vita

    public int getTour() {
        return tour;
    }

    public void setTour() {
        Random r = new Random();
        this.tour = 1 + r.nextInt(2);
    }
    
    public void updateTour() {
        if(getTour()==1) {
            this.tour = 2;
        }
        else {
            this.tour = 1;
        }
    }
    
    public ArrayList<ThemePoint> getTheme_exclude() {
        return theme_exclude;
    }

    public void setTheme_exclude(ArrayList<ThemePoint> theme_exclude) {
        this.theme_exclude = theme_exclude;
    }

    public UtilisateurScore getJ1() {
        return j1;
    }

    public void setJ1(UtilisateurScore j1) {
        this.j1 = j1;
    }

    public UtilisateurScore getJ2() {
        return j2;
    }

    public void setJ2(UtilisateurScore j2) {
        this.j2 = j2;
    }

    public ArrayList<Chanson> getExclude() {
        return exclude;
    }

    public void setExclude(ArrayList<Chanson> exclude) {
        this.exclude = exclude;
    }

    public ThemePoint[] getListe_theme() {
        return liste_theme;
    }

    public void setListe_theme(ThemePoint[] liste_theme) {
        this.liste_theme = liste_theme;
    }

    public InfoUtilisateur getInfo_j1() {
        return info_j1;
    }

    public void setInfo_j1(String user, String pass) throws Exception {
        this.info_j1 = InfoUtilisateur.getInfoUtilisateur(user,pass);
    }

    public InfoUtilisateur getInfo_j2() {
        return info_j2;
    }

    public void setInfo_j2(String user, String pass) throws Exception {
        this.info_j2 = InfoUtilisateur.getInfoUtilisateur(user,pass);
    }

    public Chanson getChanson_final() {
        return chanson_final;
    }

    public void setChanson_final() throws Exception{
        this.chanson_final = Chanson.getRandomChansons(this.exclude);
    }
    
    
}
