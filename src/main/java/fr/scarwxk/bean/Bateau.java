package fr.scarwxk.bean;
import java.lang.Math;

public class Bateau {
    Port depart;
    Port arrivee;
    boolean enMer;

    public Bateau() {
        this.depart = null;
        this.arrivee = null;
        this.enMer = true;
    }

    public Bateau(Port position) {
        this.depart = null;
        if (position.ajouterBateau()) {
            this.arrivee = position;
            this.enMer = false;
        } else {
            this.enMer = true;
        }
    }

    public void accoster(Port a) {
        if (a.ajouterBateau()) {
            this.arrivee = a;
        }
        this.enMer = false;
    }

    public void quitter() {
        this.arrivee.retirerBateau();
        this.enMer = true;
        this.depart = this.arrivee;
        this.arrivee = null;
    }

    public float distance() {
        if (!this.enMer && this.depart != null && this.arrivee != null)
            return (float)(Math.sqrt(Math.pow(this.arrivee.retourneX() - this.depart.retourneX(), 2) + Math.pow(this.arrivee.retourneY() - this.depart.retourneY(), 2)));
        return -1;
    }
}