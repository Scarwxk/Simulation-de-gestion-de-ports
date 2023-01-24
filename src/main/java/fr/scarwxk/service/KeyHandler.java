package fr.scarwxk.service;

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

        // PLAY STATE
        if (gp.getGameState() == gp.getPlayState()) {

            switch (code) {
                case KeyEvent.VK_Z, KeyEvent.VK_UP -> upPressed = true;
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPressed = true;
                case KeyEvent.VK_Q, KeyEvent.VK_LEFT -> leftPressed = true;
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPressed = true;
                case KeyEvent.VK_P -> gp.setGameState(gp.getPauseState());
                case KeyEvent.VK_ENTER -> enterPressed = true;
            }
        }

        // PAUSE STATE
        if (gp.getGameState() == gp.getPauseState()) {
            if (code == KeyEvent.VK_P) {
                gp.setGameState(gp.getPlayState());
            }
        }
        // DIALOGUE STATE
        if (gp.getGameState() == gp.getDialogueState()) {
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
