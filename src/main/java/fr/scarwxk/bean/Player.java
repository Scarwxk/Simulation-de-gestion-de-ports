package fr.scarwxk.bean;

import fr.scarwxk.service.GamePanel;
import fr.scarwxk.service.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Objects;

public class Player extends Bateau{
    GamePanel gp;
    KeyHandler keyH;
    
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues()
    {
        this.worldX = 100;
        this.worldY = 100;
        this.speed = 4;
        setDirection("up");
    }
    public void getPlayerImage()
    {
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
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Function update frame
     */
    public void update()
    {
        if(keyH.upPressed)
        {
            setDirection("up");
            this.worldY -= this.speed;
        }
        if(keyH.downPressed)
        {
            setDirection("down");
            this.worldY += this.speed;
        }
        if(keyH.leftPressed)
        {
            setDirection("left");
            this.worldX -= this.speed;
        }
        if(keyH.rightPressed)
        {
            setDirection("right");
            this.worldX += this.speed;
        }

        setSpriteCounter(getSpriteCounter() + 1);

        if(getSpriteCounter() > 10)
        {
            setSpriteNum((getSpriteNum() + 1) % 4);
            setSpriteCounter(0);
        }

    }

    /**
     * Dessine le sprite en fonction de la direction
     * @param g2 Element graphique en 2D
     */

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

        switch (getDirection()) {
            case "up" -> image = getBufferedImage(up1, up2, up3, up4);
            case "down" -> image = getBufferedImage(down1, down2, down3, down4);
            case "left" -> image = getBufferedImage(left1, left2, left3, left4);
            case "right" -> image = getBufferedImage(right1, right2, right3, right4);
        }

        g2.drawImage(image, worldX, worldY, gp.getTileSize(), gp.getTileSize(), null);

    }

    /**
     * Renvoie l'image à afficher pour le sprite
     * @param image1
     * @param image2
     * @param image3
     * @param image4
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
}
