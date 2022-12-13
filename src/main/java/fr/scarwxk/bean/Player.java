package fr.scarwxk.bean;

import fr.scarwxk.service.GamePanel;
import fr.scarwxk.service.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
        this.x = 100;
        this.y = 100;
        this.speed = 4;
        direction = "right";
    }

    public void getPlayerImage()
    {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Player/up01.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Player/up02.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/Player/up03.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/Player/up04.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Player/down01.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Player/down02.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/Player/down03.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/Player/down04.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Player/left01.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Player/left02.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/Player/left03.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/Player/left04.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Player/right01.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Player/right02.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/Player/right03.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/Player/right04.png"));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        if(keyH.upPressed)
        {
            this.direction = "up";
            this.y -= this.speed;
        }
        if(keyH.downPressed)
        {
            this.direction = "down";
            this.y += this.speed;
        }
        if(keyH.leftPressed)
        {
            this.direction = "left";
            this.x -= this.speed;
        }
        if(keyH.rightPressed)
        {
            this.direction = "right";
            this.x += this.speed;
        }
    }

    public void draw(Graphics2D g2)
    {
        //g2.setColor(Color.white);

        //g2.fillRect(this.x, this.y, gp.getTileSize(), gp.getTileSize());

        BufferedImage image = null;


    }
}
