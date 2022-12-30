package fr.scarwxk.service;

import fr.scarwxk.object.Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    private final GamePanel gp;
    private final Font arial_40, arial_80B;
    private final BufferedImage keyImage;

    private boolean messageOn = false;
    private String message = "";
    private int messageCounter = 0;

    private boolean gameFinished = false;

    private double playTime;
    private DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        Key key = new Key();
        keyImage = key.getImage();
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished) {
            g2.setFont(this.arial_40);
            g2.setColor(Color.white);

            String text = "Tu as fini le jeu !";
            int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            int x = gp.getScreenWidth() / 2 - textLength / 2;
            int y = gp.getScreenHeight() / 2 - gp.getTileSize() * 3;

            g2.drawString(text, x, y);

            g2.setFont(this.arial_80B);
            g2.setColor(Color.yellow);

            text = "Félicitations !";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.getScreenWidth() / 2 - textLength / 2;
            y = gp.getScreenHeight() / 2 + (gp.getTileSize() * 2);

            g2.drawString(text, x, y);

            g2.setFont(this.arial_40);
            g2.setColor(Color.white);

            text = "Ton temps est de : " + dFormat.format(playTime) + " secondes !";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.getScreenWidth() / 2 - textLength / 2;
            y = gp.getScreenHeight() / 2 + (gp.getTileSize() * 5);

            g2.drawString(text, x, y);

            gp.setGameThread(null);

        } else {
            g2.setFont(this.arial_40);
            g2.setColor(Color.white);
            g2.drawImage(this.keyImage, gp.getTileSize() / 2, gp.getTileSize() / 2, gp.getTileSize(), gp.getTileSize(), null);
            g2.drawString("x " + gp.getPlayer().getHasKey(), 100, 75);

            // TIME
            this.playTime += (double) 1 / 60;
            g2.drawString("Temps écoulé : " + dFormat.format(this.playTime), gp.getTileSize() * 10, 65);

            // MESSAGE

            if (this.messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(this.message, gp.getTileSize() / 2, gp.getTileSize() * 5);

                this.messageCounter++;

                // 2 secs because game runnnig 60 frames/sec
                if (messageCounter > 120) {
                    this.messageCounter = 0;
                    this.messageOn = false;
                }
            }
        }

    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
}
