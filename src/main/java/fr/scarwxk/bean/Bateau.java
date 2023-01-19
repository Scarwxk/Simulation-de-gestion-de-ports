package fr.scarwxk.bean;

import fr.scarwxk.service.GamePanel;

public class Bateau extends Entity {
    private Port depart;
    private Port arrivee;
    private boolean enMer;

    public Bateau(GamePanel gp) {
        super(gp);
        this.depart = null;
        this.arrivee = null;
        this.enMer = true;
    }

    public Bateau(Port position, GamePanel gp) {
        super(gp);
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
            this.enMer = false;
        }
    }

    public void quitter() {
        if (this.arrivee != null) {
            this.arrivee.retirerBateau();
            this.enMer = true;
            this.depart = this.arrivee;
            this.arrivee = null;
        }

    }

    public float distance() {
        if (!this.enMer && this.depart != null && this.arrivee != null)
            return (float) (Math.sqrt(Math.pow(this.arrivee.retourneX() - this.depart.retourneX(), 2) +
                    Math.pow(this.arrivee.retourneY() - this.depart.retourneY(), 2)));

        return -1;
    }


}