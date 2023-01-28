package fr.scarwxk.service;

import fr.scarwxk.object.Key;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // TITLE STATE
        if (gp.getGameState() == gp.getTitleState()) {
            switch (code) {
                case KeyEvent.VK_Z, KeyEvent.VK_UP -> {
                    gp.getUi().setCommandNum(gp.getUi().getCommandNum() - 1);
                    if (gp.getUi().getCommandNum() < 0) {
                        gp.getUi().setCommandNum(2);
                    }
                }
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                    gp.getUi().setCommandNum(gp.getUi().getCommandNum() + 1);
                    if (gp.getUi().getCommandNum() > 2) {
                        gp.getUi().setCommandNum(0);
                    }
                }
                case KeyEvent.VK_ENTER -> {
                    if (gp.getUi().getCommandNum() == 0) {
                        gp.setGameState(gp.getPlayState());
                        gp.playMusic(0);
                    }
                    if (gp.getUi().getCommandNum() == 1) {
                        // Add later
                    }
                    if (gp.getUi().getCommandNum() == 2) {
                        System.exit(0);
                    }
                }
            }
        }


        // PLAY STATE
        if (gp.getGameState() == gp.getPlayState()) {
            if(code == KeyEvent.VK_Z || code == KeyEvent.VK_UP)
            {
                upPressed = true;
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            {
                downPressed = true;
            }
            if(code == KeyEvent.VK_Q || code == KeyEvent.VK_LEFT)
            {
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
            {
                rightPressed = true;
            }
            if(code == KeyEvent.VK_P)
            {
                gp.setGameState(gp.getPauseState());
            }
            if(code == KeyEvent.VK_ENTER)
            {
                enterPressed = true;
            }
        }

        // PAUSE STATE
        else if (gp.getGameState() == gp.getPauseState()) {
            if (code == KeyEvent.VK_P) {
                gp.setGameState(gp.getPlayState());
            }
        }
        // DIALOGUE STATE
        else if (gp.getGameState() == gp.getDialogueState()) {
            if (code == KeyEvent.VK_ENTER) {
                gp.setGameState(gp.getPlayState());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_Z, KeyEvent.VK_UP -> upPressed = false;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPressed = false;
            case KeyEvent.VK_Q, KeyEvent.VK_LEFT -> leftPressed = false;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPressed = false;

        }
    }
}
