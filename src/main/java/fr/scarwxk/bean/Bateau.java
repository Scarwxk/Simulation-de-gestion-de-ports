package fr.scarwxk.bean;
import java.awt.image.BufferedImage;
import java.lang.Math;

public class Bateau {
    private Port depart;
    private Port arrivee;
    private boolean enMer;

    protected int worldX, worldY;
    protected int speed;

    protected BufferedImage
            up1, up2, up3, up4,
            down1, down2, down3, down4,
            left1, left2, left3, left4,
            right1, right2, right3, right4;
    private String direction;

    private int spriteCounter = 0;
    private int spriteNum = 1;

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
            return (float)(Math.sqrt(Math.pow(this.arrivee.retourneX() - this.depart.retourneX(), 2) +
                    Math.pow(this.arrivee.retourneY() - this.depart.retourneY(), 2)));

        return -1;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }
}