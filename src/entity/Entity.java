package entity;

import main.GamePanel;
import javax.imageio.ImageIO;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    //player
    public int shadowX,shadowY;//bóng
    public String direction;//hươớng nhân vật
    public String atkDirection;//hướng tấn công
    public int x, y;//toạ độ nhân vật
    public boolean pAlive=true;//trạng thái sống
    public int speed, cspeed, currentSpeed;
    public double acceleration;
    public String uD,lR;//huong de xac dinh anh trc khi dung

    public String collisionCheck;//kiểm tra va chạm

    //ảnh
    public BufferedImage[] up,upl,left,right;
    public BufferedImage standr,standl,standUR,standUL;
    public BufferedImage shadow;
    public BufferedImage[] attackR,attackL,attackUR,attackUL;
    public BufferedImage[] swordU,swordD,swordL,swordR,swordUL;
    public BufferedImage[] sliceU,sliceD,sliceL,sliceR,sliceUL;
    BufferedImage[] thinkR,thinkL;
    BufferedImage[] rollUpR,rollUpL,rollR,rollL;
    BufferedImage heart,heartE,heartH;
    GamePanel gp;

    public int spriteCounter=0,nMCounter=0;
    //public int sCounter;
    public int spriteNum=0,nMNum=0;
    public int attackCounter=0;
    public int attackNum=0;

    public boolean isMoving = false;
    public boolean isAttack = false;
    public boolean isThink=false;
    public int sliceCounter =0;
    public int sliceNum =0;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public int screenX,screenY;
    public Rectangle attackAreaU,attackAreaD,attackAreaR,attackAreaL;
    public boolean collisionOn=false,collisionL=false,collisionR=false,collisionU=false,collisionD=false;

    public int thinkCounter=0;
    public int thinkNum=0;
    public boolean isRolling=false;
    public int rollingCounter=0;
    public int rollingNum=0;

    public boolean pToECU,pToECD,pToECR,pToECL;
    public int  maxHP, currentHP;

    public int invisibleTime=0,iT=0;
    public boolean invisible,canAttack;
    //player

    //ênmies
    public Rectangle bodyArea;
    public int sx, sy;
    public int eX,eY,eSX=0,eSY=0;
    public boolean attacking=true,alive=true,daylui=false;
    public int eCounter=0,eNum=0,dlNum=0,dlS=6,sawCounter=0, sawW =0,sawH=0,cX=0,cY=0;
    public int hp;
    public int atkCounter;
    BufferedImage[] spark;
    public int sparkCounter=0,sparkNum=0;
    public String eD="L",sD;
    BufferedImage slimeI,exclamation;
    public boolean sp=false;
    public boolean eCollision=false,eCollisionR=false,eCollisionL=false,eCollisionU=false,eCollisionD=false;
    public boolean saw=false,move=false,chamThan=false;
    public boolean eToPCU,eToPCD,eToPCL,eToPCR;
    public BufferedImage[] slimeR,slimeL,sL,sR;
    //enemies



    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public int worldX, worldY;
    public int defaultX = worldX, defaultY = worldY;
    public int acTionCounter = 0;
    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            g2d.drawImage(scaledImage, 0, 0, width, height,  null);
            g2d.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void setAction(){};
    public void update(){
        setAction();
        collisionOn = false;
        if(collisionOn==false){
            switch (direction){
                case "up":
                    worldY-=speed;
                    if(defaultY - worldY > speed * 2) collisionOn = true;
                    break;
                case "down":
                    worldY+=speed;
                    if(worldY - defaultY > speed * 2) collisionOn = true;
                    break;
                case "left":
                    worldX-=speed;
                    if(defaultX - worldX > speed * 2) collisionOn = true;
                    break;
                case "right":
                    worldX+=speed;
                    if(worldX - defaultX > speed * 2) collisionOn = true;
                    break;
            }
            System.out.println(collisionOn);
        }
        System.out.println(collisionOn);
        spriteCounter++;
        if(spriteCounter==12){
            if(spriteNum==0){
                spriteNum += 1;
            }
            else if(spriteNum==1){
                spriteNum -= 1;
            }
            spriteCounter=0;
        }

    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.x + gp.player.screenX;
        int screenY = worldY - gp.player.y + gp.player.screenY;

        //if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "up":
                    if (spriteNum == 0) {
                        image = up1;
                    }
                    if (spriteNum == 1) {
                        image = up2;
                    }
                    break;
                case "down": // Trường hợp mới cho NPC đứng yên
                    if (spriteNum == 0) {
                        image = down1;
                    }
                    if (spriteNum == 1) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 0) {
                        image = left1;
                    }
                    if (spriteNum == 1) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 0) {
                        image = right1;
                    }
                    if (spriteNum == 1) {
                        image = right2;
                    }
                    break;
            }

            if (image != null) {
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        //}
    }

}
