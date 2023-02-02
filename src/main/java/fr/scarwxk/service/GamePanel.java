package fr.scarwxk.service;

import fr.scarwxk.bean.Entity;
import fr.scarwxk.bean.Player;
import fr.scarwxk.bean.Port;
import fr.scarwxk.bean.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

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
    private final static int maxWorldCol = 250;
    private final static int maxWorldRow = 250;

    private final static int FPS = 60;
    private final static double INT_INTERVAL = 1000000000;

    // SYSTEM
    private final TileManager tileM = new TileManager(this);
    private final KeyHandler keyH = new KeyHandler(this);
    private final Sound music = new Sound();
    private final Sound soundEffect = new Sound();
    private final CollisionChecker collisionChecker = new CollisionChecker(this);
    private final AssetSetter aSetter = new AssetSetter(this);
    private final UI ui = new UI(this);

    private final EventHandler eHandler = new EventHandler(this);

    private Thread gameThread;
    // ENTITY AND OBJECT
    private final Player player = new Player(this, keyH);
    private final Entity[] obj = new Entity[10];
    private final Entity[] npc = new Entity[5];
    private final ArrayList<Entity> entityList = new ArrayList<>();

    // PORTS / QUAIS
    private final PortSetter portSetter = new PortSetter(this);

    // GAME STATE
    private int gameState;
    private final int titleState = 0;
    private final int playState = 1;
    private final int pauseState = 2;
    private final int dialogueState = 3;
    private final int choiceState = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNpc();
        portSetter.setPort();
        // Play the game music (index 0)
        // playMusic(0);
        gameState = titleState;
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
        if (gameState == playState) {
            // PLAYER
            player.update();
            // NPC
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }

            }
        }
        if (gameState == pauseState) {
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Title Screen
        if (gameState == titleState) {
            ui.draw(g2);
        }
        // Others
        else {
            // Tile
            tileM.draw(g2);

            // ADD entities to list
            entityList.add(player);

            for (Entity entity : npc) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            for (Entity entity : obj) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            // SORT par rapport à leur position en Y

            entityList.sort(Comparator.comparingInt(Entity::getWorldY));

            // DRAW ENTITIES
            for (Entity en : entityList) {
                en.draw(g2);
            }

            for (int i = 0; i < entityList.size(); i++) {
                entityList.remove(i);
            }


            // UI
            ui.draw(g2);
        }


        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }

    public int getTileSize() {
        return tileSize;
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


    public Entity[] getNpc() {
        return npc;
    }

    public UI getUi() {
        return ui;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getPlayState() {
        return playState;
    }

    public int getPauseState() {
        return pauseState;
    }

    public int getDialogueState() {
        return dialogueState;
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public int getTitleState() {
        return titleState;
    }

    public EventHandler geteHandler() {
        return eHandler;
    }

    public Entity[] getObj() {
        return obj;
    }

    public PortSetter getPortSetter() {
        return portSetter;
    }

    public int getChoiceState() {
        return choiceState;
    }
}
