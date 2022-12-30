package fr.scarwxk.service;

import fr.scarwxk.object.Boots;
import fr.scarwxk.object.Chest;
import fr.scarwxk.object.Door;
import fr.scarwxk.object.Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }


    /**
     * Function set object on map
     */
    public void setObject() {
        gp.getObj()[0] = new Key();
        gp.getObj()[0].setWorldX(30 * gp.getTileSize());
        gp.getObj()[0].setWorldY(3 * gp.getTileSize());

        gp.getObj()[1] = new Door();
        gp.getObj()[1].setWorldX(31 * gp.getTileSize());
        gp.getObj()[1].setWorldY(3 * gp.getTileSize());

        gp.getObj()[2] = new Chest();
        gp.getObj()[2].setWorldX(32 * gp.getTileSize());
        gp.getObj()[2].setWorldY(3 * gp.getTileSize());

        gp.getObj()[3] = new Boots();
        gp.getObj()[3].setWorldX(25 * gp.getTileSize());
        gp.getObj()[3].setWorldY(5 * gp.getTileSize());
    }
}
