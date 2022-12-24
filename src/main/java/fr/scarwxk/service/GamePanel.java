package fr.scarwxk.service;

import fr.scarwxk.bean.Player;
import fr.scarwxk.bean.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    private final static int originalTileSize = 16; //16x16 tile
    private final static int scale = 4;
    private final static int tileSize = originalTileSize * scale; //64x64 tile
    private static final int maxScreenCol = 16;
    private static final int maxScreenRow = 12;
    private final static int screenWidth = tileSize * maxScreenCol; // 768 pixels
    private final static int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS

    private final static int maxWorldCol = 50;
    private final static int maxWorldRow = 50;
    private final static int worldWidth = tileSize * maxWorldCol;
    private final static int worldHeight = tileSize * maxWorldRow;

    private final static int FPS = 60;
    private final static int INT_INTERVAL = 1000000000;
    private final TileManager tileM = new TileManager(this);
    private final KeyHandler keyH = new KeyHandler();
    private Thread gameThread;

    private CollisionChecker collisionChecker = new CollisionChecker(this);
    private final Player player = new Player(this, keyH);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    // GAME LOOP
    @Override
    public void run() {

        double drawInterval = INT_INTERVAL / FPS; // 0.01666 sec
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // 1 UPDATE : Update info such as player positions
                update();
                // 2 DRAW : Draw the screen with the updated info
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= INT_INTERVAL) {
                System.out.println("FPS :" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }


    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public Player getPlayer() {
        return player;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public TileManager getTileM() {
        return tileM;
    }
}
