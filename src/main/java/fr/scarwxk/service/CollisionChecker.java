package fr.scarwxk.service;

import fr.scarwxk.bean.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }


    /**
     * Check all moves(next tile)
     * @param entity the entity that moves
     */
    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / this.gp.getTileSize();
        int entityRightCol = entityRightWorldX / this.gp.getTileSize();
        int entityTopRow = entityTopWorldY / this.gp.getTileSize();
        int entityBottomRow = entityBottomWorldY / this.gp.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityTopRow];
                if (gp.getTileM().getTile()[tileNum1].isCollision() || gp.getTileM().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gp.getTileM().getTile()[tileNum1].isCollision() || gp.getTileM().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityLeftCol][entityBottomRow];
                if (gp.getTileM().getTile()[tileNum1].isCollision() || gp.getTileM().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gp.getTileM().getTile()[tileNum1].isCollision() || gp.getTileM().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
            }
        }
    }


    /**
     * Check the collision between entities/objects
     * @param entity The entity that moves
     * @param player Boolean indicates if player or not
     * @return index of the object
     */
    public int checkObject(Entity entity, boolean player)
    {
        int index = 999;

        for(int i = 0; i < gp.getObj().length; i++)
        {
            if(gp.getObj()[i] != null)
            {
                // Get entity solid position
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;

                // Get the object solid position
                gp.getObj()[i].getSolidArea().x = gp.getObj()[i].getWorldX() + gp.getObj()[i].getSolidArea().x;
                gp.getObj()[i].getSolidArea().y = gp.getObj()[i].getWorldY() + gp.getObj()[i].getSolidArea().y;

                switch (entity.getDirection()) {
                    case "up" -> {
                        entity.getSolidArea().y -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.getObj()[i].getSolidArea())) {
                            if(gp.getObj()[i].isCollision())
                            {
                                entity.setCollisionOn(true);
                            }
                            if(player)
                            {
                                index = i;
                            }
                        }
                    }
                    case "down" -> {
                        entity.getSolidArea().y += entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.getObj()[i].getSolidArea())) {
                            if(gp.getObj()[i].isCollision())
                            {
                                entity.setCollisionOn(true);
                            }
                            if(player)
                            {
                                index = i;
                            }
                        }
                    }
                    case "left" -> {
                        entity.getSolidArea().x -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.getObj()[i].getSolidArea())) {
                            if(gp.getObj()[i].isCollision())
                            {
                                entity.setCollisionOn(true);
                            }
                            if(player)
                            {
                                index = i;
                            }
                        }
                    }
                    case "right" -> {
                        entity.getSolidArea().x += entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.getObj()[i].getSolidArea())) {
                            if(gp.getObj()[i].isCollision())
                            {
                                entity.setCollisionOn(true);
                            }
                            if(player)
                            {
                                index = i;
                            }
                        }
                    }
                }

                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                gp.getObj()[i].getSolidArea().x = gp.getObj()[i].getSolidAreaDefaultX();
                gp.getObj()[i].getSolidArea().y = gp.getObj()[i].getSolidAreaDefaultY();

            }
        }

        return index;
    }

}
