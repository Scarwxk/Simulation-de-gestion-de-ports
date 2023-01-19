package fr.scarwxk.service;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {

    private final GamePanel gp;
    private Graphics2D g2;
    private final Font arial_40, arial_80B;
    //private final BufferedImage keyImage;

    private boolean messageOn = false;
    private String message = "";
    private final int messageCounter = 0;

    private boolean gameFinished = false;

    private double playTime;
    private DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        //Key key = new Key();
        //keyImage = key.getImage();
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if (gp.getGameState() == gp.getPlayState()) {

        }
        if (gp.getGameState() == gp.getPauseState()) {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSE";
        int x = getXforCenteredText(text);
        int y = gp.getScreenHeight() / 2;

        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.getScreenWidth() / 2 - length / 2;
        return x;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
}
