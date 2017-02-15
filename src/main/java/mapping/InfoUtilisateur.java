/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

import java.sql.Connection;
import jdbc.Connexion;
import metier.Authentification;

/**
 *
 * @author itu
 */
public class InfoUtilisateur {
    private String user_name;
    private String url_photo;
    private int gain;
    private Chanson[] liste_chanson;

    public String getUser_name() {
        return user_name;
    }

    public final void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUrl_photo() {
        return url_photo;
    }

    public final void setUrl_photo(String url_photo) {
        this.url_photo = url_photo;
    }

    public int getGain() {
        return gain;
    }

    public final void setGain(int gain) {
        this.gain = gain;
    }

    public Chanson[] getListe_chanson() {
        return liste_chanson;
    }

    public final void setListe_chanson(Chanson[] liste_chanson) {
        this.liste_chanson = liste_chanson;
    }

    public InfoUtilisateur(String user_name, String url_photo, int gain, Chanson[] liste_chanson) {
        this.setUser_name(user_name);
        this.setUrl_photo(url_photo);
        this.setGain(gain);
        this.setListe_chanson(liste_chanson);
    }
    
    public static InfoUtilisateur getInfoUtilisateur(String user,String pass) throws Exception {
        Connection c = Connexion.getConnection();
        InfoUtilisateur reponse = null;
        if(Authentification.sauthentifie(c, user, pass)) {
            Login log = Login.getLogin(user, pass);
            Gain gain = Gain.getGainById(log.getId_utilisateur());
            reponse = new InfoUtilisateur(user, pass, gain.getGain(), ChansonUtilisateur.getListeChansonUtilisateur(log.getId_utilisateur()));
        }
        else {
            c.close();
        }
        return reponse;
    }
    
}
