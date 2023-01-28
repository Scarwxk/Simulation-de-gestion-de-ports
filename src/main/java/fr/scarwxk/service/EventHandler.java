package fr.scarwxk.service;

import java.awt.*;

public class EventHandler {

    private GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gamePanel) {
        this.gp = gamePanel;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent()
    {
        if(hit(45,6,"right"))
        {
            damage(gp.getDialogueState());
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection)
    {
        boolean hit = false;

        gp.getPlayer().getSolidArea().x = gp.getPlayer().getWorldX() + gp.getPlayer().getSolidArea().x;
        gp.getPlayer().getSolidArea().y = gp.getPlayer().getWorldY() + gp.getPlayer().getSolidArea().y;
        eventRect.x = eventCol * gp.getTileSize() + eventRect.x;
        eventRect.y = eventRow * gp.getTileSize() + eventRect.y;

        if(gp.getPlayer().getSolidArea().intersects(eventRect))
        {
            if(gp.getPlayer().getDirection().contentEquals(reqDirection) || reqDirection.contentEquals("any"))
            {
                hit = true;
            }
        }

        gp.getPlayer().getSolidArea().x = gp.getPlayer().getSolidAreaDefaultX();
        gp.getPlayer().getSolidArea().y = gp.getPlayer().getSolidAreaDefaultY();
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;

    }

    public void damage(int gameState)
    {
        gp.setGameState(gameState);
        gp.getUi().currentDialogue = "Une tempête attaque !";
        gp.getPlayer().setLife(gp.getPlayer().getLife() - 1);
    }

    public void healingPool(int gameState)
    {
        if(gp.getKeyH().enterPressed)
        {
            gp.setGameState(gameState);
            gp.getUi().currentDialogue = "Vous avez bu de l'eau de mer\n Votre vie se restaure.";
            gp.getPlayer().setLife(gp.getPlayer().getMaxLife());
        }
    }

}
