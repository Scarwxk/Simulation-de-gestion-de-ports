package fr.scarwxk.bean;

public class Port {
    private final double x;
    private final double y;
    private final Quais quai;
    private final String name;

    public Port(double x, double y) {
        this.x = x;
        this.y = y;
        this.name = "";
        this.quai = new Quais();
    }

    public Port(double x, double y, int nb_quai, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.quai = new Quais(nb_quai);
    }

    public Port(double x, double y, Quais quai) {
        this.x = x;
        this.y = y;
        this.quai = quai;
        this.name = "";
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

    public Quais getQuai() {
        return quai;
    }

    public String getName() {
        return name;
    }
}