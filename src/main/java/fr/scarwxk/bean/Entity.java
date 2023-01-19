package fr.scarwxk.bean;

import fr.scarwxk.service.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

public class Entity {

    protected GamePanel gp;
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

    private Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    private int solidAreaDefaultX, solidAreaDefaultY;

    private boolean collisionOn = false;

    protected int actionLockCounter = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {

    }

    public void update() {
        setAction();

        setCollisionOn(false);
        gp.getCollisionChecker().checkTile(this);
        gp.getCollisionChecker().checkObject(this, false);
        gp.getCollisionChecker().checkPlayer(this);

        if (!isCollisionOn()) {
            switch (getDirection()) {
                case "up" -> this.worldY -= this.speed;
                case "down" -> this.worldY += this.speed;
                case "left" -> this.worldX -= this.speed;
                case "right" -> this.worldX += this.speed;
            }
        }


        setSpriteCounter(getSpriteCounter() + 1);

        if (getSpriteCounter() > 10) {
            setSpriteNum((getSpriteNum() + 1) % 4);
            setSpriteCounter(0);
        }
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

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        // Draw only tiles around the player, not the entire map

        if (worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()
        ) {
            BufferedImage image = null;

            switch (getDirection()) {
                case "up" -> image = getBufferedImage(up1, up2, up3, up4);
                case "down" -> image = getBufferedImage(down1, down2, down3, down4);
                case "left" -> image = getBufferedImage(left1, left2, left3, left4);
                case "right" -> image = getBufferedImage(right1, right2, right3, right4);
            }

            g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }

    private BufferedImage getBufferedImage(BufferedImage image1, BufferedImage image2, BufferedImage image3, BufferedImage image4) {

        var spriteDict = new Hashtable<Integer, BufferedImage>();
        spriteDict.put(0, image1);
        spriteDict.put(1, image2);
        spriteDict.put(2, image3);
        spriteDict.put(3, image4);

        return spriteDict.get(getSpriteNum());
    }


}
