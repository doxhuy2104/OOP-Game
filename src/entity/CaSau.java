package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.security.PublicKey;

public class CaSau extends Enemies{
    GamePanel gp;
    BufferedImage[] cMove,cAttack,cWake;
    BufferedImage cSleep;
    public CaSau(GamePanel gp){
        this.gp=gp;
        getCaSauImage();
    }

    public void getCaSauImage(){
        try {
            BufferedImage casau= ImageIO.read(getClass().getResourceAsStream("enemies/carnivorock.png"));

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

    }
}
