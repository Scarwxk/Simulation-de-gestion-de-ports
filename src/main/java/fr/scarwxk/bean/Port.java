package fr.scarwxk.bean;

public class Port {
    private final double x;
    private final double y;
    private final Quais quai;

    public Port(double x, double y) {
        this.x = x;
        this.y = y;
        this.quai = new Quais();
    }

    public Port(double x, double y, int nb_quai) {
        this.x = x;
        this.y = y;
        this.quai = new Quais(nb_quai);
    }

    public double retourneX() {
        return this.x;
    }

    public double retourneY() {
        return this.y;
    }

    public boolean ajouterBateau() {
        return this.quai.ajouterBateau();
    }

    public void retirerBateau() {
        this.quai.retirerBateau();
    }
}