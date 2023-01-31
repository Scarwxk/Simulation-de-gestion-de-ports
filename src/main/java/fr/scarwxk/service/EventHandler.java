package fr.scarwxk.service;

public class EventHandler {

    private final GamePanel gp;
    private final EventRect[][] eventRect;

    private int previousEventX, previousEventY;
    private boolean canTouchEvent = true;

    public EventHandler(GamePanel gamePanel) {
        this.gp = gamePanel;

        eventRect = new EventRect[gp.getMaxWorldCol()][gp.getMaxWorldRow()];

        int col = 0;
        int row = 0;

        while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].setEventRectDefaultX(eventRect[col][row].x);
            eventRect[col][row].setEventRectDefaultY(eventRect[col][row].y);

            col++;
            if (col == gp.getMaxWorldCol()) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent() {
        // Check that the player is more than 1 tile away from the last event

        int xDistance = Math.abs(gp.getPlayer().getWorldX() - previousEventX);
        int yDistance = Math.abs(gp.getPlayer().getWorldY() - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.getTileSize()) {
            canTouchEvent = true;
        }


        if (canTouchEvent) {
            if (hit(45, 6, "right")) {
                damage(45, 6, gp.getDialogueState());
                gp.getPlayer().setDirection("left");
            }
        }
    }

    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;

        gp.getPlayer().getSolidArea().x = gp.getPlayer().getWorldX() + gp.getPlayer().getSolidArea().x;
        gp.getPlayer().getSolidArea().y = gp.getPlayer().getWorldY() + gp.getPlayer().getSolidArea().y;

        eventRect[col][row].x = col * gp.getTileSize() + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.getTileSize() + eventRect[col][row].y;

        if (gp.getPlayer().getSolidArea().intersects(eventRect[col][row]) && !eventRect[col][row].isEventDone()) {
            if (gp.getPlayer().getDirection().contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.getPlayer().getWorldX();
                previousEventY = gp.getPlayer().getWorldY();
            }
        }

        gp.getPlayer().getSolidArea().x = gp.getPlayer().getSolidAreaDefaultX();
        gp.getPlayer().getSolidArea().y = gp.getPlayer().getSolidAreaDefaultY();
        eventRect[col][row].x = eventRect[col][row].getEventRectDefaultX();
        eventRect[col][row].y = eventRect[col][row].getEventRectDefaultY();

        return hit;

    }

    public void damage(int col, int row, int gameState) {
        gp.setGameState(gameState);
        gp.getUi().currentDialogue = "Une tempête attaque !";
        gp.getPlayer().setLife(gp.getPlayer().getLife() - 1);
        // Si je veux un event réalisable une seule fois

        //eventRect[col][row].setEventDone(true);

        // Si je veux un event réalisable à chaque fois que je m'éloigne de 1 tile

        canTouchEvent = false;
    }

    public void healingPool(int gameState) {
        if (gp.getKeyH().enterPressed) {
            gp.setGameState(gameState);
            gp.getUi().currentDialogue = "Vous avez bu de l'eau de mer\n Votre vie se restaure.";
            gp.getPlayer().setLife(gp.getPlayer().getMaxLife());
        }
    }

}
