package fr.scarwxk.bean;

import fr.scarwxk.service.GamePanel;
import fr.scarwxk.service.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
        this.x = 100;
        this.y = 100;
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
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        if(keyH.upPressed)
        {
            setDirection("up");
            this.y -= this.speed;
        }
        if(keyH.downPressed)
        {
            setDirection("down");
            this.y += this.speed;
        }
        if(keyH.leftPressed)
        {
            setDirection("left");
            this.x -= this.speed;
        }
        if(keyH.rightPressed)
        {
            setDirection("right");
            this.x += this.speed;
        }

        setSpriteCounter(getSpriteCounter() + 1);

        if(getSpriteCounter() > 10)
        {
            if(getSpriteNum() == 1)
            {
                setSpriteNum(2);
            }
            else if(getSpriteNum() == 2)
            {
                setSpriteNum(3);
            }
            else if(getSpriteNum() == 3)
            {
                setSpriteNum(4);
            }
            else if(getSpriteNum() == 4)
            {
                setSpriteNum(1);
            }
            setSpriteCounter(0);
        }

    }

    public void draw(Graphics2D g2)
    {
        /*g2.setColor(Color.white);

        g2.fillRect(this.x, this.y, gp.getTileSize(), gp.getTileSize());*/

        BufferedImage image = null;

        switch (getDirection()) {
            case "up" -> image = getBufferedImage(up1, up2, up3, up4);
            case "down" -> image = getBufferedImage(down1, down2, down3, down4);
            case "left" -> image = getBufferedImage(left1, left2, left3, left4);
            case "right" -> image = getBufferedImage(right1, right2, right3, right4);
        }

        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);

    }

    private BufferedImage getBufferedImage(BufferedImage up1, BufferedImage up2, BufferedImage up3, BufferedImage up4) {
        BufferedImage image = null;

        if (getSpriteNum() == 1) {
            image = up1;
        }
        if (getSpriteNum() == 2) {
            image = up2;
        }
        if (getSpriteNum() == 3) {
            image = up3;
        }
        if (getSpriteNum() == 4) {
            image = up4;
        }
        return image;
    }
}
