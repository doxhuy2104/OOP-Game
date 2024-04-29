package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    //player
    public int shadowX,shadowY;//bóng
    public String direction;//hươớng nhân vật
    public String atkDirection;//hướng tấn công
    public int x, y;//toạ độ nhân vật
    public boolean pAlive=true;//trạng thái sống
    public int speed, cspeed;
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


    public int spriteCounter=0,nMCounter=0;
    public int spriteNum=0,nMNum=0;
    public int attackCounter=0;
    public int attackNum=0;

    public boolean isMoving = false;
    public boolean isAttack = false;
    public boolean isThink=false;
    public int sliceCounter =0;
    public int sliceNum =0;
    public Rectangle solidArea;
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
}
