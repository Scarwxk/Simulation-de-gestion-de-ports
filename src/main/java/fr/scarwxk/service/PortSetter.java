package fr.scarwxk.service;

import fr.scarwxk.bean.Port;
import fr.scarwxk.bean.Quais;
import fr.scarwxk.bean.QuaisPosition;

// import java.util.Random;

public class PortSetter {

    private final GamePanel gp;
    private final Port[] ports = new Port[4];

    public PortSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setPort() {
        ports[0] = new Port(35, 91, 6);
        ports[1] = new Port(100, 81, 3);
        ports[2] = new Port(147, 76, 3);
        ports[3] = new Port(150, 36, 6);
        setQuais();
    }

    public void setQuais() {
        /*Random random = new Random();
        for (Port port : ports) {
            int nb_quais_random = random.nextInt(3);
            port.getQuai().setQuaisOcc(nb_quais_random);
            for (int i = 0; i < nb_quais_random; i++) {
                port.getQuai().setPositionQuais();
            }
        }*/
        QuaisPosition[] liste = new QuaisPosition[6];
        liste[0] = new QuaisPosition(30, 101);
        liste[1] = new QuaisPosition(41, 101);
        liste[2] = new QuaisPosition(30, 105);
        liste[3] = new QuaisPosition(41, 105);
        liste[4] = new QuaisPosition(30, 109);
        liste[5] = new QuaisPosition(41, 109);
        ports[0].getQuai().setPositionQuais(liste);

        liste = new QuaisPosition[3];
        liste[0] = new QuaisPosition(94, 84);
        liste[1] = new QuaisPosition(100, 82);
        liste[2] = new QuaisPosition(106, 84);
        ports[1].getQuai().setPositionQuais(liste);

        liste = new QuaisPosition[3];
        liste[0] = new QuaisPosition(148, 101);
        liste[1] = new QuaisPosition(154, 73);
        liste[2] = new QuaisPosition(154, 77);
        ports[2].getQuai().setPositionQuais(liste);

        liste = new QuaisPosition[6];
        liste[0] = new QuaisPosition(158, 34);
        liste[1] = new QuaisPosition(158, 37);
        liste[2] = new QuaisPosition(150, 40);
        liste[3] = new QuaisPosition(145, 38);
        liste[4] = new QuaisPosition(145, 33);
        liste[5] = new QuaisPosition(150, 31);
        ports[3].getQuai().setPositionQuais(liste);
    }

    public Port[] getPorts() {
        return ports;
    }
}
