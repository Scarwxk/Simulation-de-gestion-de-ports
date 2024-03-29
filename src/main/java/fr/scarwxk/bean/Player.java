package fr.scarwxk.bean;

import fr.scarwxk.service.GamePanel;
import fr.scarwxk.service.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Objects;

public class Player extends Bateau {
    private final int screenX, screenY;
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.gp = gp;
        this.keyH = keyH;

        this.screenX = gp.getScreenWidth() / 2 - gp.getTileSize() / 2;
        this.screenY = gp.getScreenHeight() / 2 - gp.getTileSize() / 2;

        this.setSolidArea(new Rectangle(0, 0, gp.getTileSize() - 5, gp.getTileSize() - 5));
        this.setSolidAreaDefaultX(this.getSolidArea().x);
        this.setSolidAreaDefaultY(this.getSolidArea().y);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        this.worldX = gp.getTileSize() * 100;
        this.worldY = gp.getTileSize() * 85;
        this.speed = 7;
        this.setMaxLife(6);
        this.setLife(getMaxLife());
        setDirection("right");
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/up01.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/up02.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/up03.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/up04.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/down01.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/down02.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/down03.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/down04.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/left01.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/left02.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/left03.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/left04.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/right01.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/right02.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/right03.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/right04.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function update frame
     */
    public void update() {
        if (keyH.upPressed) {
            setDirection("up");
        }
        if (keyH.downPressed) {
            setDirection("down");
        }
        if (keyH.leftPressed) {
            setDirection("left");
        }
        if (keyH.rightPressed) {
            setDirection("right");
        }

        // CHECK TILE COLLISION
        this.setCollisionOn(false);
        gp.getCollisionChecker().checkTile(this);

        // CHECK OBJECT COLLISION
        int objIndex = gp.getCollisionChecker().checkObject(this, true);
        pickUpObject(objIndex);

        // CHECK NPC COLLISION
        int npcIndex = gp.getCollisionChecker().checkEntity(this, gp.getNpc());
        interactNPC(npcIndex);

        // CHECK EVENT
        gp.geteHandler().checkEvent();

        gp.getKeyH().enterPressed = false;


        // IF !COLLISION, CAN MOVE

        if (!isCollisionOn() && (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)) {
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

    private void pickUpObject(int i) {
        if (i != 999) {

        }
    }

    public void interactNPC(int i) {
        if (i != 999) {

            if (gp.getKeyH().enterPressed) {
                gp.setGameState(gp.getDialogueState());
                gp.getNpc()[i].speak();
            }
        }

    }

    /**
     * Dessine le sprite en fonction de la direction
     *
     * @param g2 Element graphique en 2D
     */

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (getDirection()) {
            case "up" -> image = getBufferedImage(up1, up2, up3, up4);
            case "down" -> image = getBufferedImage(down1, down2, down3, down4);
            case "left" -> image = getBufferedImage(left1, left2, left3, left4);
            case "right" -> image = getBufferedImage(right1, right2, right3, right4);
        }

        g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);

    }

    /**
     * Renvoie l'image à afficher pour le sprite
     *
     * @param image1 Image n*1 du sprite
     * @param image2 Image n*2 du sprite
     * @param image3 Image n*3 du sprite
     * @param image4 Image n*4 du sprite
     * @return BufferedImage Sprite image
     */
    private BufferedImage getBufferedImage(BufferedImage image1, BufferedImage image2, BufferedImage image3, BufferedImage image4) {

        var spriteDict = new Hashtable<Integer, BufferedImage>();
        spriteDict.put(0, image1);
        spriteDict.put(1, image2);
        spriteDict.put(2, image3);
        spriteDict.put(3, image4);

        return spriteDict.get(getSpriteNum());
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }
}
