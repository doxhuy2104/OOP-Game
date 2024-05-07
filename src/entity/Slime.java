package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Slime extends Entity {
    GamePanel gp;
    public final int screenX, screenY;
    private float transparency = 1.0f;
    private final float TRANSPARENCY_STEP = 0.5f;
    private double xMove=0,yMove=0,distance,dx,dy;

    public static int abs(int x) {
        return x >= 0 ? x : -x;
    }


    public Slime(GamePanel gp) {
        this.gp = gp;

        getEnemiesImage();

        bodyArea = new Rectangle();
        saw = false;
        hp = 5;
        eSpeed=2;

        eNum = 2;
        sD = "L";

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;
        attackAreaU = new Rectangle(screenX - 7 * gp.scale, screenY - 10 * gp.scale, 136, 76);
        attackAreaL = new Rectangle(screenX - 15 * gp.scale, screenY - 9 * gp.scale, 76, 136);
        attackAreaD = new Rectangle(screenX - 9 * gp.scale, screenY + 15 * gp.scale, 136, 76);
        attackAreaR = new Rectangle(screenX + 13 * gp.scale, screenY - 8 * gp.scale, 76, 136);
    }


    public void getEnemiesImage() {
        try {
            exclamation = ImageIO.read(getClass().getResourceAsStream("/enemies/exclamation.png"));
            slimeL = new BufferedImage[4];
            slimeR = new BufferedImage[4];
            BufferedImage slimeSheet = ImageIO.read(getClass().getResourceAsStream("/enemies/slimeRed.png"));
            for (int i = 0; i < 4; i++) {
                slimeR[i] = slimeSheet.getSubimage(i * 16, 16, 16, 16);
                slimeL[i] = slimeSheet.getSubimage(48 - i * 16, 32, 16, 16);
            }
            sR = new BufferedImage[2];
            sR[0] = slimeSheet.getSubimage(0, 0, 16, 16);
            sR[1] = slimeSheet.getSubimage(16, 0, 16, 16);

            sL = new BufferedImage[2];
            sL[0] = slimeSheet.getSubimage(0, 48, 16, 16);
            sL[1] = slimeSheet.getSubimage(16, 48, 16, 16);

            shadow = ImageIO.read(getClass().getResourceAsStream("/enemies/enemySm.png"));

            spark = new BufferedImage[3];
            BufferedImage sparkSheet = ImageIO.read(getClass().getResourceAsStream("/attack/spark.png"));
            for (int i = 0; i < 3; i++) {
                spark[i] = sparkSheet.getSubimage(28 * i, 0, 28, 34);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (alive) {//cap nhat toa do quai vat
            eX = -gp.player.x + gp.player.screenX + eSX + sx;
            eY = -gp.player.y + gp.player.screenY + eSY + sy;
        }
        bodyArea = new Rectangle(eX + 8, eY + 24, 48, 36);//cap nhat tao do phan than quai vat
        attacking = false;
        gp.attackChecker.attackChecker(this);
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


        if (daylui) {
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
        }

        if (eX > screenX) sD = "L";
        else sD = "R";

        eCollision = false;
        eCollisionR = false;
        eCollisionL = false;
        eCollisionU = false;
        eCollisionD = false;
        gp.collisionChecker.checkTileEnemies(this);


        if (daylui) {
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

        eToPCU = false;
        eToPCD = false;
        eToPCL = false;
        eToPCR = false;
        if (!gp.player.invisible) gp.collisionChecker.eToPCo(this);
        if (eToPCR || eToPCL || eToPCD || eToPCU) gp.player.invisible = true;

        if ((abs(eX - screenX)) < 350 && (abs(eY - screenY)) < 250) saw = true;
        if (move && alive && !daylui) {
            distance = Math.sqrt(Math.pow(screenX - eX, 2) + Math.pow(screenY - eY, 2));
            if(distance!=0) {
                dx = (screenX - eX) / distance;
                dy = (screenY - eY) / distance;
            }
            if(dx>0)eD="R";
            else eD="L";

            xMove+=dx*eSpeed;
            yMove+=dy*eSpeed;

            eSX+=(int) xMove;
            eSY+=(int) yMove;

            xMove-=(int) xMove;
            yMove-=(int) yMove;
        }

        if (saw) {
            if (sawCounter < 60) {
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
                move = true;
                chamThan = false;
            }
        }
        if (move) {
            sawCounter = 0;
            sawW = 0;
            sawH = 0;
            cX = 0;
            cY = 0;
            eCounter++;
            if (eCounter % 8 == 0 && eNum < 3) {
                eNum++;
            }
            if (eCounter == 32) {
                eNum = 0;
                eCounter = 0;
            }
        }
//            sawCounter=0;
//            sawW =0;
        else {
            nMCounter++;
            if (nMCounter % 20 == 0 && nMNum < 1) {
                nMNum++;
            }
            if (nMCounter == 40) {
                nMNum = 0;
                nMCounter = 0;
            }
        }


    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        if (alive) {
            g2.drawImage(shadow, eX + 7, eY + 48, gp.scale * shadow.getWidth(), gp.scale * shadow.getHeight(), null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
            if (chamThan) g2.drawImage(exclamation, eX + 33 - cX, eY - cY, sawW, sawH, null);
            if (move) {
                if (eD.equals("LU") || eD.equals("LD") || eD.equals("L") || eD.equals("U")) slimeI = slimeL[eNum];
                if (eD.equals("RU") || eD.equals("RD") || eD.equals("R") || eD.equals("D")) slimeI = slimeR[eNum];
                g2.drawImage(slimeI, eX, eY, gp.scale * slimeI.getWidth(), gp.scale * slimeI.getHeight(), null);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            } else {
                switch (sD) {
                    case "L":
                        slimeI = sL[nMNum];
                        break;
                    case "R":
                        slimeI = sR[nMNum];
                        break;
                }

                g2.drawImage(slimeI, eX, eY, gp.scale * slimeI.getWidth(), gp.scale * slimeI.getHeight(), null);
            }
        }
        if (attacking && alive || sp) {
            g2.drawImage(spark[sparkNum], eX - 24, eY - 36, gp.scale * spark[sparkNum].getWidth(), gp.scale * spark[sparkNum].getHeight(), null);
        }
    }


    public void reset() {
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
