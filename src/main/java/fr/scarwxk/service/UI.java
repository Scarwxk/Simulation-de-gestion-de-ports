package fr.scarwxk.service;

import fr.scarwxk.bean.Entity;
import fr.scarwxk.object.Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class UI {

    private final GamePanel gp;
    private Graphics2D g2;
    private final Font gameFont;
    private int commandNum = 0;

    private BufferedImage heart_full, heart_half, heart_blank;


    /*private boolean messageOn = false;
    private String message = "";
    private final int messageCounter = 0;

    private boolean gameFinished = false;
*/
    public String currentDialogue = "";

    public UI(GamePanel gp) {
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            gameFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(is));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        // CREATE HUD OBJECT
        Entity heart = new Heart(gp);
        heart_full = heart.getImage();
        heart_half = heart.getImage2();
        heart_blank = heart.getImage3();

    }

    /*public void showMessage(String text) {
        message = text;
        messageOn = true;
    }*/

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(gameFont);
        g2.setColor(Color.white);

        // TITLE STATE
        if (gp.getGameState() == gp.getTitleState()) {
            drawTitleScreen();
        }

        // PLAY STATE
        if (gp.getGameState() == gp.getPlayState()) {
            drawPlayerLife();
        }
        // PAUSE STATE
        if (gp.getGameState() == gp.getPauseState()) {
            drawPlayerLife();
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if (gp.getGameState() == gp.getDialogueState()) {
            drawPlayerLife();
            drawDialogueScreen();
        }
    }

    private void drawPlayerLife() {

        int x = gp.getTileSize() / 2;
        int y = gp.getTileSize() / 2;
        int i = 0;

        // DRAW BLANK HEARTS
        while (i < gp.getPlayer().getMaxLife() / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.getTileSize();
        }

        // RESET
        x = gp.getTileSize() / 2;
        y = gp.getTileSize() / 2;
        i = 0;

        // DRAW CURRENT LIFE
        while (i < gp.getPlayer().getLife()) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.getPlayer().getLife()) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.getTileSize();
        }

    }

    private void drawTitleScreen() {
        // BACKGROUND
        g2.setColor(new Color(86, 125, 70));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "L'aventure du potit bateau";
        int x = getXforCenteredText(text);
        int y = gp.getTileSize() * 3;

        // SHADOW
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 5, y + 5);

        // MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // PLAYER IMAGE
        x = gp.getScreenWidth() / 2 - gp.getTileSize();
        y += gp.getTileSize() * 2;
        g2.drawImage(gp.getPlayer().getRight1(), x, y, gp.getTileSize() * 2, gp.getTileSize() * 2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        g2.setColor(Color.BLACK);
        text = "NOUVELLE PARTIE";
        x = getXforCenteredText(text);
        y += gp.getTileSize() * 4;
        g2.drawString(text, x, y);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x + 3, y + 3);
        if (commandNum == 0) {
            g2.drawImage(gp.getPlayer().getRight1(), x - gp.getTileSize(), y - gp.getTileSize() / 4 * 3, gp.getTileSize(), gp.getTileSize(), null);
        }

        g2.setColor(Color.BLACK);
        text = "CHARGER UNE PARTIE";
        x = getXforCenteredText(text);
        y += gp.getTileSize();
        g2.drawString(text, x, y);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x + 3, y + 3);
        if (commandNum == 1) {
            g2.drawImage(gp.getPlayer().getRight1(), x - gp.getTileSize(), y - gp.getTileSize() / 4 * 3, gp.getTileSize(), gp.getTileSize(), null);
        }

        g2.setColor(Color.BLACK);
        text = "QUITTER";
        x = getXforCenteredText(text);
        y += gp.getTileSize();
        g2.drawString(text, x, y);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x + 3, y + 3);
        if (commandNum == 2) {
            g2.drawImage(gp.getPlayer().getRight1(), x - gp.getTileSize(), y - gp.getTileSize() / 4 * 3, gp.getTileSize(), gp.getTileSize(), null);
        }
    }

    private void drawDialogueScreen() {

        // WINDOW
        int x = gp.getTileSize() * 2;
        int y = gp.getTileSize() / 2;
        int width = gp.getScreenWidth() - (gp.getTileSize() * 4);
        int height = gp.getTileSize() * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 26F));
        x += gp.getTileSize();
        y += gp.getTileSize();

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }


    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 220);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
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

    /*public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }*/

    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }

    public void setCommandNum(int commandNum) {
        this.commandNum = commandNum;
    }

    public int getCommandNum() {
        return commandNum;
    }
}
