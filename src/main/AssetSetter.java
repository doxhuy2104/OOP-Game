package main;

import entity.Enemies;
import entity.NPC;
import object.objectBoots;
import object.objectChest;
import object.objectDoor;
import object.objectKey;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    public void setObject(){

        gp.obj[0] = new objectKey();
        gp.obj[0].worldX = 3 * gp.tileSize;
        gp.obj[0].worldY = 14 * gp.tileSize;

        gp.obj[1] = new objectKey();
        gp.obj[1].worldX = 1 * gp.tileSize;
        gp.obj[1].worldY = 37 * gp.tileSize;

        gp.obj[2] = new objectKey();
        gp.obj[2].worldX = 34 * gp.tileSize;
        gp.obj[2].worldY = 26 * gp.tileSize;

        gp.obj[3] = new objectKey();
        gp.obj[3].worldX = 67 * gp.tileSize;
        gp.obj[3].worldY = 24 * gp.tileSize;

        gp.obj[4] = new objectBoots();
        gp.obj[4].worldX = 3 * gp.tileSize;
        gp.obj[4].worldY = 20 * gp.tileSize;

        gp.obj[5] = new objectChest();
        gp.obj[5].worldX = 59 * gp.tileSize;
        gp.obj[5].worldY = 49 * gp.tileSize;
    }
    public void setEnemies(){
        gp.slime[0] = new Enemies(gp);
        gp.slime[0].sx =28*gp.tileSize;
        gp.slime[0].sy =18*gp.tileSize;

        gp.slime[1] = new Enemies(gp);
        gp.slime[1].sx =43*gp.tileSize;
        gp.slime[1].sy =22*gp.tileSize;

        gp.slime[2] = new Enemies(gp);
        gp.slime[2].sx =35*gp.tileSize;
        gp.slime[2].sy =29*gp.tileSize;

        gp.slime[3] = new Enemies(gp);
        gp.slime[3].sx =20*gp.tileSize;
        gp.slime[3].sy =30*gp.tileSize;
    }
    public void setNpc(){
        gp.npc[0] = new NPC(gp);
        gp.npc[0].worldX = 3 * gp.tileSize;
        gp.npc[0].worldY = 4 * gp.tileSize;
    }
}
