package main;

import entity.Enemies;
import entity.Entity;
import entity.Player;
import tile.TileManager;
import ui.GameOver;
import ui.Menu;
import ui.Pause;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    public final int originalTileSize = 16;
    public final int scale = 4;
    public final int tileSize= originalTileSize*scale;
    public final int maxScreenRow=9;
    public final int maxScreenCol=16;
    public final int screenWidth=tileSize*maxScreenCol;
    public final int screenHeight=tileSize*maxScreenRow;

    public final int maxWorldCol=60;
    public final int maxWorldRow=50;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;

    //System
    public KeyHandler keyH=new KeyHandler();
    public MouseClickListener mouseClick=new MouseClickListener(this,this);
    public AssetSetter assetSetter =new AssetSetter(this);
    public Sound sound=new Sound();
    public Music music=new Music();

    Thread gameThread;

    //In game
    public Player player=new Player(this,keyH,mouseClick);
    TileManager tileManager =new TileManager(this);
    public Menu uiManager=new Menu(this,keyH,mouseClick);
    public Enemies slime[]=new Enemies[20];
    Enemies enemies=new Enemies(this);
    Pause pauseS=new Pause(this,mouseClick);
    GameOver gameOver=new GameOver(this,mouseClick);

    public CollisionChecker collisionChecker=new CollisionChecker(this);
    public AttackChecker attackChecker=new AttackChecker(this);

    public int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.addMouseListener(mouseClick);

        player.getPlayerImage();
        enemies.getEnemiesImage();
    }

    public void setUpGame(){
        assetSetter.setEnemies();
        playMusic(0);
    }

    public void startGameThread(){
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

    public void update(){
        if(uiManager.gameO){
            FPS=60;
            gameOver.update();
            for(int i=0;i<slime.length;i++){
                if(slime[i]!=null){
                    slime[i].reset();//reset trang thai quai vat khi game over
                }
            }
        }
        if(uiManager.inGame){
            FPS=60;
            if(uiManager.play){
            if(player.pAlive)player.update();
            //tileManager.updateCampFire();
                for(int i=0;i<slime.length;i++){
                if(slime[i]!=null){
                    slime[i].update();
                }
            }
            }
            if(uiManager.play){
                if (uiManager.pC<20){
                    uiManager.pC++;
                }
                if(uiManager.pC==20&&keyH.escape){
                    uiManager.pC=0;
                uiManager.pause=true;
                uiManager.play=false;}
            }
            if(uiManager.pause){
                pauseS.update();
                if (uiManager.pC<20){
                uiManager.pC++;
            }
                if(keyH.escape&&uiManager.pC==20){
                    uiManager.pause=false;
                    uiManager.play=true;uiManager.pC=0;}
            }
        }else if(!uiManager.gameO){//neu khong phai trong game va khong phai game over thi moi cap nhat menu
            FPS=240;
            uiManager.updateUI();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;
        long drawStart=System.nanoTime();
        if(uiManager.inGame) {
            //tileManager.drawGlass(g2);
            //tileManager.drawWater(g2);
            tileManager.draw1(g2);
            //tileManager.drawCampFire(g2);
            if(player.pAlive)player.draw(g2);
            //tileManager.drawTree(g2);
            if(player.pAlive)for(int i=0;i<slime.length;i++){
                if(slime[i]!=null){
                    slime[i].draw(g2);
                }
            }
            if(uiManager.pause){
                pauseS.draw(g2);
            }
            //toa do nhan vat
            g2.drawString("Col: "+(player.x+player.solidArea.x)/tileSize,10,300);
            g2.drawString("Row: "+(player.y+player.solidArea.y)/tileSize,10,310);
        } else if(!uiManager.gameO){
            //main menu
            uiManager.draw(g2);
        }

        if(uiManager.gameO){
            gameOver.draw(g2);//Game Over
        }


        long drawEnd=System.nanoTime();
        long passed=drawEnd-drawStart;
        g2.setColor(Color.WHITE);
        g2.drawString("Draw Time: "+passed,10,400);
        System.out.println("Draw Time: "+passed);
        g2.dispose();
    }

    //sound
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSoundEffect(int i){
        sound.setFile(i);
        sound.play();
    }
}
