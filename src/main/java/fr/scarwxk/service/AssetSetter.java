package fr.scarwxk.service;

import fr.scarwxk.bean.NPC_OldMan;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }


    /**
     * Function set object on map
     */
    public void setObject() {
    }

    public void setNpc() {
        gp.getNpc()[0] = new NPC_OldMan(gp);
        gp.getNpc()[0].setWorldX(48 * gp.getTileSize());
        gp.getNpc()[0].setWorldY(2 * gp.getTileSize());
    }
}
