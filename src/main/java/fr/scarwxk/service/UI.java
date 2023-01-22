package fr.scarwxk.service;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class UI {

    private final GamePanel gp;
    private Graphics2D g2;
    private final Font gameFont;


    private boolean messageOn = false;
    private String message = "";
    private final int messageCounter = 0;

    private boolean gameFinished = false;

    public String currentDialogue = "";

    public UI(GamePanel gp) {
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            gameFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(is));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(gameFont);
        g2.setColor(Color.white);

        // PLAY STATE
        if (gp.getGameState() == gp.getPlayState()) {

        }
        // PAUSE STATE
        if (gp.getGameState() == gp.getPauseState()) {
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if(gp.getGameState() == gp.getDialogueState())
        {
            drawDialogueScreen();
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

        for(String line : currentDialogue.split("\n"))
        {
            g2.drawString(line, x, y);
            y += 40;
        }


    }

    public void drawSubWindow(int x, int y, int width, int height)
    {
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

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }
}
