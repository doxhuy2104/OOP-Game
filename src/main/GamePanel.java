package main;

import entity.*;
import tile.TileManager;
import ui.GameOver;
import ui.Hud;
import ui.Menu;
import ui.Pause;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    public final int originalTileSize = 16;
    public final int scale = 4;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenRow = 9;
    public final int maxScreenCol = 16;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //System
    public KeyHandler keyH = new KeyHandler(this);
    public MouseClickListener mouseClick = new MouseClickListener(this, this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Sound sound = new Sound();
    public Music music = new Music();
    private long firstTime, lastTime;

    Thread gameThread;

    //In game
    public Player player = new Player(this, keyH, mouseClick);
    TileManager tileManager = new TileManager(this);
    public Menu uiManager = new Menu(this, keyH, mouseClick);
    public Slime[] slime = new Slime[20];
    public CaSau[] caSau = new CaSau[20];
    Pause pauseS = new Pause(this, mouseClick);
    GameOver gameOver = new GameOver(this, mouseClick);
    Hud hud = new Hud(this);
    ArrayList<Entity> entities = new ArrayList<>();

    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AttackChecker attackChecker = new AttackChecker(this);

    public int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.addMouseListener(mouseClick);

        player.getPlayerImage();
    }

    public void setUpGame() {
        assetSetter.setEnemies();
        playMusic(0);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        while (gameThread != null) {
            long currentTime = System.nanoTime();
            long elapsedTime = currentTime - lastTime;
            if (elapsedTime >= 1000000000 / FPS) {
                repaint();
                update();
                lastTime = currentTime;
            }
        }
    }

    public void update() {
        firstTime = System.nanoTime();
        if (uiManager.gameO) {
            FPS = 60;
            gameOver.update();
        }
        if (uiManager.inGame) {
            FPS = 60;
            if (uiManager.play) {
                hud.update();
                if (player.pAlive) player.updateP();
                for (int i = 0; i < slime.length; i++) {
                    if (slime[i] != null) {
                        slime[i].update();
                    }
                    if (caSau[i] != null) {
                        caSau[i].update();
                    }
                }
            }

            if (uiManager.pause) {
                pauseS.update();
            }
        } else if (!uiManager.gameO) {
            FPS = 240;
            uiManager.updateUI();
        }
        lastTime = System.nanoTime();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        long drawStart = System.nanoTime();
        if (uiManager.inGame) {
            tileManager.draw(g2);

                //player.draw(g2);

            entities.add(player);
            for (int i = 0; i < slime.length; i++) {
                if (slime[i] != null) {
                    entities.add(slime[i]);
                }
                if (caSau[i] != null) {
                    entities.add(caSau[i]);
                }
            }

            entities.sort(Comparator.comparingInt(e -> e.y));

            if (player.pAlive) for (int i=0;i<entities.size();i++) {
                entities.get(i).draw(g2);
            }

            entities.clear();

            hud.draw(g2);
            if (uiManager.pause) {
                pauseS.draw(g2);
            }
            //toa do nhan vat
            g2.drawString("Col: " + (player.x + player.solidArea.x) / tileSize, 10, 300);
            g2.drawString("Row: " + (player.y + player.solidArea.y) / tileSize, 10, 310);
        } else if (!uiManager.gameO) {
            //main menu
            uiManager.draw(g2);
        }

        if (uiManager.gameO) {
            gameOver.draw(g2);//Game Over
        }


        long drawEnd = System.nanoTime();
        //long passed = drawEnd - drawStart;
        long passed = drawEnd - firstTime;
        g2.setColor(Color.WHITE);
        //g2.drawString("Draw Time: "+passed,10,400);
        //System.out.println("Draw Time: "+passed);
        g2.dispose();
    }

    //sound
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {
        sound.setFile(i);
        sound.play();
    }
}
