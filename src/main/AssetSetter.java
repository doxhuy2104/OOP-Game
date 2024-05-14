package main;

import entity.CaSau;
import entity.Slime;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setEnemies() {
        gp.slime[0] = new Slime(gp);
        gp.slime[0].sx = 66 * gp.tileSize;
        gp.slime[0].sy = 26 * gp.tileSize;

//        gp.slime[1] = new Slime(gp);
//        gp.slime[1].sx =43*gp.tileSize;
//        gp.slime[1].sy =22*gp.tileSize;
//
//        gp.slime[2] = new Slime(gp);
//        gp.slime[2].sx =35*gp.tileSize;
//        gp.slime[2].sy =29*gp.tileSize;
//
//        gp.slime[3] = new Slime(gp);
//        gp.slime[3].sx =20*gp.tileSize;
//        gp.slime[3].sy =30*gp.tileSize;
//
//        gp.slime[4] = new Slime(gp);
//        gp.slime[4].sx =11*gp.tileSize;
//        gp.slime[4].sy =6*gp.tileSize;

        gp.caSau[0] = new CaSau(gp);
        gp.caSau[0].sx = 54 * gp.tileSize;
        gp.caSau[0].sy = 22 * gp.tileSize;
    }
}
