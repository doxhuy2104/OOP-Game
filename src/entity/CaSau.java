package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CaSau extends Enemies{
    GamePanel gp;
    BufferedImage[] cMove,cAttack,cWake;
    BufferedImage cSleep;
    int screenX,screenY,centerX,centerY;
    int mNum=0,mCounter=0,aCounter=0,aNum=0,wCounter=0,wNum=0;
    boolean wake=false,attack=false;

    public CaSau(GamePanel gp) {
        super(gp);
        this.gp=gp;
        getCaSauImage();

        bodyArea = new Rectangle();
        saw = false;
        hp = 5;
        eSpeed=2;
        moved=false;

        eNum = 2;
        mD = "L";

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;
        attackAreaU = new Rectangle(screenX - 7 * gp.scale, screenY - 10 * gp.scale, 136, 76);
        attackAreaL = new Rectangle(screenX - 15 * gp.scale, screenY - 9 * gp.scale, 76, 136);
        attackAreaD = new Rectangle(screenX - 9 * gp.scale, screenY + 15 * gp.scale, 136, 76);
        attackAreaR = new Rectangle(screenX + 13 * gp.scale, screenY - 8 * gp.scale, 76, 136);

    }

    public void getCaSauImage(){
        try {
            BufferedImage casau= ImageIO.read(getClass().getResourceAsStream("/enemies/carnivorock.png"));

            cMove=new BufferedImage[4];
            for(int i=0;i<4;i++){
                cMove[i]=casau.getSubimage(i*22,22,22,22);
            }

            cAttack=new BufferedImage[4];
            for(int i=0;i<4;i++){
                cAttack[i]=casau.getSubimage(i*22,44,22,22);
            }

            cSleep=casau.getSubimage(0,66,22,22);
            cWake=new BufferedImage[2];
            for(int i=0;i<2;i++){
                cWake[i]=casau.getSubimage(i*22,0,22,22);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(){
        bodyArea = new Rectangle(eX + 16, eY + 40, 52, 48);//cap nhat tao do phan than quai vat
        super.update();
        if(daylui) super.daylui();
        if (saw) {
            super.saw();
            if(!move) wake=true;
            if(wake){
                wCounter++;
                if(wCounter>=10){
                    wCounter=0;
                    wNum++;
                    if(wNum>=2){
                        wNum=0;
                        wake=false;
                    }
                }
            }
        }
        if (move && alive && !daylui) {
            saw=false;
            super.move();
            mCounter++;
            if(mCounter>=10){
                mCounter=0;
                mNum++;
                if(mNum>=4)mNum=0;
            }
        }
        super.attacked();
        centerX=bodyArea.x+bodyArea.width/2;
        centerY=bodyArea.y+bodyArea.height/2;
        int distanceX=gp.screenWidth/2-centerX;
        int distanceY=gp.screenHeight/2-centerY;
        int distance=(int)Math.sqrt(distanceX*distanceX+distanceY*distanceY);
        if(distance<200) if(move) attack=true;
        if (attack) {
            double angle = Math.atan2(distanceY, distanceX);
            if(aNum==0) aCounter++;
            if(aCounter>=10&&aNum==0){
                aCounter=0;
                aNum=1;
                move=false;
            }
            if(aNum==1) aCounter++;
            if(aCounter>=60){
                aCounter=0;
                aNum=2;
            }
            if(aNum==2){

            }
        }
    }

    public void attack(){

    }

    public void draw(Graphics2D g2) {
        if (alive) {
            if (!moved) {
                if (!saw) g2.drawImage(cSleep, eX, eY, 22 * gp.scale, 22 * gp.scale, null);
                else g2.drawImage(cWake[wNum], eX, eY, 22 * gp.scale, 22 * gp.scale, null);
            }else if(!attack) g2.drawImage(cMove[mNum], eX, eY, 22 * gp.scale, 22 * gp.scale, null);
            if(attack) g2.drawImage(cAttack[aNum], eX, eY, 22 * gp.scale, 22 * gp.scale, null);
        }
        g2.drawString("move: "+move,10,110);
        g2.drawString("sawCOunter: "+sawCounter,10,120);
        g2.drawString("attack: "+attack,10,130);
    }

    public void reset(){
        super.reset();
        mNum=0;
    }
}
