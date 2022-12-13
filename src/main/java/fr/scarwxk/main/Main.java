package fr.scarwxk.main;

import fr.scarwxk.service.GamePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Aventure navale 2D");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(250, 250);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}