package fr.scarwxk.bean;

public class Quais {
    private final int nbQuais;
    private int quaisOcc;
    private QuaisPosition[] positionQuais;

    public Quais() {
        this.nbQuais = 3;
        this.quaisOcc = 0;
    }

    public Quais(int nb) {
        this.nbQuais = nb;
        this.quaisOcc = 0;
    }

    public boolean ajouterBateau() {
        if (this.nbQuais - this.quaisOcc != 0) {
            (this.quaisOcc)++;
            return true;
        }
        return false;
    }

    public void retirerBateau() {
        if (this.quaisOcc != 0) {
            (this.quaisOcc)--;
        }
    }

    public int getQuaisOcc() {
        return quaisOcc;
    }

    public void setQuaisOcc(int quaisOcc) {
        this.quaisOcc = quaisOcc;
    }

    public QuaisPosition[] getPositionQuais() {
        return positionQuais;
    }

    public void setPositionQuais(QuaisPosition[] positionQuais) {
        this.positionQuais = positionQuais;
    }
}