package fr.scarwxk.service;

import fr.scarwxk.bean.Player;
import fr.scarwxk.bean.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS
    final static int originalTileSize = 16; //16x16 tile
    final static int scale = 5;
    final static int tileSize = originalTileSize * scale; //80x80 tile
    private static final int maxScreenCol = 16;
    private static final int maxScreenRow = 12;
    final static int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final static int screenHeight = tileSize * maxScreenRow; // 576 pixels
    final static int FPS = 60;
    final static int INT_INTERVAL = 1000000000;
    private final TileManager tileM = new TileManager(this);
    private final KeyHandler keyH = new KeyHandler();
    private Thread gameThread;

    private final Player player = new Player(this, keyH);

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }


    // GAME LOOP
    @Override
    public void run() {

        double drawInterval = INT_INTERVAL/FPS; // 0.01666 sec
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1)
            {
                // 1 UPDATE : Update info such as player positions
                update();
                // 2 DRAW : Draw the screen with the updated info
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= INT_INTERVAL)
            {
                System.out.println("FPS :" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }


    public void update()
    {
        player.update();
    }

    public void paintComponent(Graphics g)
    {
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
}
