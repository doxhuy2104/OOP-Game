package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemies {
    //ênmies
    public final int screenX, screenY;
    private float transparency = 1.0f;
    private final float TRANSPARENCY_STEP = 0.5f;
    private double xMove=0,yMove=0,distance,dx,dy;
    public int eSpeed;
    public BufferedImage shadow;
    public Rectangle bodyArea;
    public int sx, sy;
    public int eX,eY,eSX=0,eSY=0;
    public boolean attacking=true,alive=true,daylui=false;
    public int eCounter=0,eNum=0,dlNum=0,dlS=6,sawCounter=0, sawW =0,sawH=0,cX=0,cY=0;
    public int hp;
    public int atkCounter;
    BufferedImage[] spark;
    public int sparkCounter=0,sparkNum=0;
    public String eD="L", mD ="L";
    BufferedImage slimeI,exclamation;
    public boolean sp=false;
    public boolean eCollision=false,eCollisionR=false,eCollisionL=false,eCollisionU=false,eCollisionD=false;
    public boolean saw,move=false,chamThan=false;
    public boolean eToPCU,eToPCD,eToPCL,eToPCR;
    public BufferedImage[] slimeR,slimeL,sL,sR;
    public Rectangle attackAreaU,attackAreaD,attackAreaR,attackAreaL;
    public int nMCounter=0,nMNum=0;//no move
    public int speed;
    public boolean moved;

    //enemies
    GamePanel gp;
    public static int abs(int x) {
        return x >= 0 ? x : -x;
    }

    public Enemies(GamePanel gp){
        this.gp=gp;
        bodyArea = new Rectangle();
        saw = false;
        hp = 5;
        eSpeed=2;

        eNum = 2;
        //mD = "L";

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;
    }

    public void update() {
        if (alive) {//cap nhat toa do quai vat
            eX = -gp.player.x + gp.player.screenX + eSX + sx;
            eY = -gp.player.y + gp.player.screenY + eSY + sy;
        }
        attacking = false;
        gp.attackChecker.attackChecker(this);
        if (eX > screenX) mD = "L";
        else mD = "R";
        eCollision = false;
        eCollisionR = false;
        eCollisionL = false;
        eCollisionU = false;
        eCollisionD = false;
        gp.collisionChecker.checkTileEnemies(this);

        eToPCU = false;
        eToPCD = false;
        eToPCL = false;
        eToPCR = false;
        if (!gp.player.invisible) gp.collisionChecker.eToPCo(this);
        if (eToPCR || eToPCL || eToPCD || eToPCU) gp.player.invisible = true;

        if ((abs(eX - screenX)) < 350 && (abs(eY - screenY)) < 250) saw = true;
    }

    public void daylui(){
        switch (gp.player.atkDirection) {
            case "attackUp":
                eD = "U";
                break;
            case "attackDown":
                eD = "D";
                break;
            case "attackR":
                eD = "R";
                break;
            case "attackL":
                eD = "L";
                break;
        }
        dlNum++;
        switch (gp.player.atkDirection) {
            case "attackUp":
                if (!eCollision) eSY -= dlS;
                break;
            case "attackDown":
                if (!eCollision) eSY += dlS;
                break;
            case "attackR":
                if (!eCollision) eSX += dlS;
                break;
            case "attackL":
                if (!eCollision) eSX -= dlS;
                break;
        }
        if (dlNum % 5 == 0) {
            dlS--;
            if (dlNum == 30) {
                daylui = false;
                dlNum = 0;
                dlS = 6;
            }
        }
    }

    public void saw(){
        if (!moved&&sawCounter < 60) {
            sawCounter++;
            chamThan = true;
            if (sawW < 20) {
                sawW += 2;
                sawH += 4;
                cX++;
                cY += 2;
                //else if(sawW ==20) {}
            }
        } else if (sawCounter == 60) {
            sawCounter=0;
            move = true;
            moved = true;
            chamThan = false;
        }
    }

    public void move(){
        distance = Math.sqrt(Math.pow(screenX - eX, 2) + Math.pow(screenY - eY, 2));
        if(distance!=0) {
            dx = (screenX - eX) / distance;
            dy = (screenY - eY) / distance;
        }
        if(dx>0)eD="R";
        else eD="L";

        xMove+=dx*eSpeed;
        yMove+=dy*eSpeed;

        if(!eCollisionL||!eCollisionR)eSX+=(int) xMove;
        if(!eCollisionD||!eCollisionU)eSY+=(int) yMove;

        xMove-=(int) xMove;
        yMove-=(int) yMove;

        sawW = 0;
        sawH = 0;
        cX = 0;
        cY = 0;

    }

    public void attacked(){
        if (attacking && hp != 0) {//quai vat bi tan cong
            daylui = true;
            if (atkCounter == 0) {
                if (hp > 1)
                    gp.playSoundEffect(0);
                hp--;
                atkCounter = 1;
                transparency -= TRANSPARENCY_STEP;
                if (transparency < 0.0f) {
                    transparency = 0.0f;
                }
            }
            sparkCounter++;
            if (sparkCounter % 7 == 0) {
                sparkNum++;
            }
            if (hp == 0) {
                sp = true;
                gp.playSoundEffect(4);
            }
        } else {
            transparency += TRANSPARENCY_STEP;
            if (transparency > 1.0f) {
                transparency = 1.0f;
            }
        }

        if (!attacking) {
            sparkCounter = 0;
            sparkNum = 0;
            atkCounter = 0;
        }
        if (hp == 0) {
            alive = false;
        }
        if (sp) {
            sparkCounter++;
            if (sparkCounter % 7 == 0) {
                sparkNum++;
            }
            if (sparkNum == 2) {
                sparkCounter = 0;
                sparkNum = 0;
                sp = false;
            }
        }
    }

    public void direction(){
        if(eX>screenX) mD ="L";
        else mD ="R";
    }

    public void reset(){
        eSX = 0;
        eSY = 0;
        alive = true;
        saw = false;
        move = false;
        sawCounter = 0;
        sawW = 0;
        sawH = 0;
        cX = 0;
        cY = 0;
        chamThan = false;
    }
}
