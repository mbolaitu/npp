/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

/**
 *
 * @author mbola
 */
public class UtilisateurScore extends Utilisateur {
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public UtilisateurScore(Utilisateur user, int score) {
        super(user.getId_utilisateur(), user.getNom_utilisateur(), user.getPrenom_utilisateur());
        this.setScore(score);
    }
    
    public void update_score(int point) {
        this.score = this.score+point;
    }
    
    
}
