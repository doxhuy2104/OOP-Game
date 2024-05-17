package main;

import entity.Enemies;
import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeft = entity.x + entity.solidArea.x;
        int entityRight = entityLeft + entity.solidArea.width;
        int entityTop = entity.y + entity.solidArea.y;
        int entityBottom = entityTop + entity.solidArea.height;

        int entityLeftCol = entityLeft / gp.tileSize;
        int entityRightCol = entityRight / gp.tileSize;
        int entityTopRow = entityTop / gp.tileSize;
        int entityBotRow = entityBottom / gp.tileSize;

        int tileNum1, tileNum2, tileNum3;

        switch (entity.collisionCheck) {
            case "up":
                entityTopRow = (entityTop - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "upr":
                entityTopRow = (entityTop - entity.speed) / gp.tileSize;
                entityRightCol = (entityRight + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum3 = gp.tileManager.mapTileNum[entityRightCol][entityBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    if (gp.tileManager.tile[tileNum3].collision)
                        entity.collisionR = true;
                }
                if (gp.tileManager.tile[tileNum2].collision || gp.tileManager.tile[tileNum3].collision) {
                    if (gp.tileManager.tile[tileNum1].collision)
                        entity.collisionU = true;
                }
                break;

            case "upl":
                entityTopRow = (entityTop - entity.speed) / gp.tileSize;
                entityLeftCol = (entityLeft - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum3 = gp.tileManager.mapTileNum[entityLeftCol][entityBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    if (gp.tileManager.tile[tileNum3].collision)
                        entity.collisionL = true;
                }
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum3].collision) {
                    if (gp.tileManager.tile[tileNum2].collision)
                        entity.collisionU = true;
                }
                break;
            case "down":
                entityBotRow = (entityBottom + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBotRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "downr":
                entityBotRow = (entityBottom + entity.speed) / gp.tileSize;
                entityRightCol = (entityRight + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBotRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBotRow];
                tileNum3 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    if (gp.tileManager.tile[tileNum3].collision)
                        entity.collisionR = true;
                }
                if (gp.tileManager.tile[tileNum2].collision || gp.tileManager.tile[tileNum3].collision) {
                    if (gp.tileManager.tile[tileNum1].collision)
                        entity.collisionD = true;
                }
                break;
            case "downl":
                entityBotRow = (entityBottom + entity.speed) / gp.tileSize;
                entityLeftCol = (entityLeft - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBotRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBotRow];
                tileNum3 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    if (gp.tileManager.tile[tileNum3].collision)
                        entity.collisionL = true;
                }
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum3].collision) {
                    if (gp.tileManager.tile[tileNum2].collision)
                        entity.collisionD = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeft - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRight + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
    public int checkObject( Entity entity, boolean player){
        int index = 999;
        for(int i = 0; i< gp.obj.length; i++){
            if(gp.obj[i] != null){
                //vị trí vùng rắn entity
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;
                //vị trí vùng rắn object
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) index = i;
                        }
                        break;
                    case "upr":
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) index = i;
                        }
                        break;
                    case "upl":
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x -= entity.speed;
                      if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) index = i;
                      }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) index = i;
                        }
                        break;
                    case "downr":
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) index = i;
                        }
                        break;
                    case "downl":
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) index = i;
                        }
                        break;

                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public void checkTileEnemies(Entity entity) {
        int enemieLeft = entity.eSX + entity.sx + 8;
        int enemieRight = enemieLeft + entity.bodyArea.width;
        int enemieTop = entity.eSY + entity.sy + 24;
        int enemieBottom = enemieTop + entity.bodyArea.height;

        int enemieLeftCol = enemieLeft / gp.tileSize;
        int enemieRightCol = enemieRight / gp.tileSize;
        int enemieTopRow = enemieTop / gp.tileSize;
        int enemieBotRow = enemieBottom / gp.tileSize;

        int tileNum1, tileNum2, tileNum3;

        switch (entity.eD) {
            case "U":
                enemieTopRow = (enemieTop - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieTopRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieTopRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.eCollision = true;
                }
                break;
            case "RU":
                enemieTopRow = (enemieTop - entity.speed) / gp.tileSize;
                enemieRightCol = (enemieRight + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieTopRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieTopRow];
                tileNum3 = gp.tileManager.mapTileNum[enemieRightCol][enemieBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    if (gp.tileManager.tile[tileNum3].collision)
                        entity.eCollisionR = true;
                }
                if (gp.tileManager.tile[tileNum2].collision || gp.tileManager.tile[tileNum3].collision) {
                    if (gp.tileManager.tile[tileNum1].collision)
                        entity.eCollisionU = true;
                }
                break;
            case "LU":
                enemieTopRow = (enemieTop - entity.speed) / gp.tileSize;
                enemieLeftCol = (enemieLeft - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieTopRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieTopRow];
                tileNum3 = gp.tileManager.mapTileNum[enemieLeftCol][enemieBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    if (gp.tileManager.tile[tileNum3].collision)
                        entity.eCollisionL = true;
                }
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum3].collision) {
                    if (gp.tileManager.tile[tileNum2].collision)
                        entity.eCollisionU = true;
                }
                break;
            case "D":
                enemieBotRow = (enemieBottom + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieBotRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.eCollision = true;
                }
                break;
            case "RD":
                enemieBotRow = (enemieBottom + entity.speed) / gp.tileSize;
                enemieRightCol = (enemieRight + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieBotRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieBotRow];
                tileNum3 = gp.tileManager.mapTileNum[enemieRightCol][enemieTopRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    if (gp.tileManager.tile[tileNum3].collision)
                        entity.eCollisionR = true;
                }
                if (gp.tileManager.tile[tileNum2].collision || gp.tileManager.tile[tileNum3].collision) {
                    if (gp.tileManager.tile[tileNum1].collision)
                        entity.eCollisionD = true;
                }
                break;
            case "LD":
                enemieBotRow = (enemieBottom + entity.speed) / gp.tileSize;
                enemieLeftCol = (enemieLeft - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieBotRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieBotRow];
                tileNum3 = gp.tileManager.mapTileNum[enemieLeftCol][enemieTopRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    if (gp.tileManager.tile[tileNum3].collision)
                        entity.eCollisionL = true;
                }
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum3].collision) {
                    if (gp.tileManager.tile[tileNum2].collision)
                        entity.eCollisionD = true;
                }
                break;
            case "L":
                enemieLeftCol = (enemieLeft - 1) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieTopRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieLeftCol][enemieBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.eCollision = true;
                }
                break;
            case "R":
                enemieRightCol = (enemieRight + 1) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieRightCol][enemieTopRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.eCollision = true;
                }
                break;
        }
    }

    public void pToECo(Entity entity) {
        int playerTop = entity.screenY + 32;
        int playerBot = playerTop + entity.solidArea.height;
        int playerLeft = entity.screenX + 8;
        int playerRight = playerLeft + entity.solidArea.width;

        int enemieTop[] = new int[gp.slime.length];
        int enemieBot[] = new int[gp.slime.length];
        int enemieRight[] = new int[gp.slime.length];
        int enemieLeft[] = new int[gp.slime.length];

        for (int i = 0; i < gp.slime.length; i++) {
            if (gp.slime[i] != null) {
                enemieTop[i] = gp.slime[i].bodyArea.y;
                enemieBot[i] = enemieTop[i] + gp.slime[i].bodyArea.height;
                enemieLeft[i] = gp.slime[i].bodyArea.x;
                enemieRight[i] = enemieLeft[i] + gp.slime[i].bodyArea.width;
                if(gp.slime[i].alive){
                switch (entity.collisionCheck) {
                    case "up":
                        if (playerTop - entity.speed <= enemieBot[i] && playerTop >= enemieBot[i] && playerLeft <= enemieRight[i] && playerRight >= enemieLeft[i]) {
                            entity.pToECU = true;
                        }
                        break;
                    case "down":
                        if (playerBot + entity.speed >= enemieTop[i] && playerBot <= enemieBot[i] & playerLeft <= enemieRight[i] && playerRight >= enemieLeft[i]) {
                            entity.pToECD = true;
                        }
                        break;
                    case "left":
                        if (playerLeft - entity.speed <= enemieRight[i] && playerLeft >= enemieLeft[i] && playerBot >= enemieTop[i] && playerTop <= enemieBot[i]) {
                            entity.pToECL = true;
                        }
                        break;
                    case "right":
                        if (playerRight + entity.speed >= enemieLeft[i] && playerRight <= enemieRight[i] && playerBot >= enemieTop[i] && playerTop <= enemieBot[i]) {
                            entity.pToECR = true;
                        }
                        break;
                    case "upr":
                        if (playerTop - entity.speed <= enemieBot[i] && playerTop >= enemieBot[i] && playerLeft <= enemieRight[i] && playerRight >= enemieLeft[i]) {
                            entity.pToECU = true;
                        }
                        if (playerRight + entity.speed >= enemieLeft[i] && playerRight <= enemieRight[i] && playerBot >= enemieTop[i] && playerTop <= enemieBot[i]) {
                            entity.pToECR = true;
                        }
                        break;
                    case "upl":
                        if (playerLeft - entity.speed <= enemieRight[i] && playerLeft >= enemieLeft[i] && playerBot >= enemieTop[i] && playerTop <= enemieBot[i]) {
                            entity.pToECL = true;
                        }
                        if (playerTop - entity.speed <= enemieBot[i] && playerTop >= enemieBot[i] && playerLeft <= enemieRight[i] && playerRight >= enemieLeft[i]) {
                            entity.pToECU = true;
                        }
                        break;
                    case "downr":
                        if (playerRight + entity.speed >= enemieLeft[i] && playerRight <= enemieRight[i] && playerBot >= enemieTop[i] && playerTop <= enemieBot[i]) {
                            entity.pToECR = true;
                        }
                        if (playerBot + entity.speed >= enemieTop[i] && playerBot <= enemieBot[i] & playerLeft <= enemieRight[i] && playerRight >= enemieLeft[i]) {
                            entity.pToECD = true;
                        }
                        break;
                    case "downl":
                        if (playerBot + entity.speed >= enemieTop[i] && playerBot <= enemieBot[i] & playerLeft <= enemieRight[i] && playerRight >= enemieLeft[i]) {
                            entity.pToECD = true;
                        }
                        if (playerLeft - entity.speed <= enemieRight[i] && playerLeft >= enemieLeft[i] && playerBot >= enemieTop[i] && playerTop <= enemieBot[i]) {
                            entity.pToECL = true;
                        }
                        break;
                }
            }}
        }
    }

    public void eToPCo(Enemies enemies) {
        int playerTop = enemies.screenY + 32;
        int playerBot = playerTop + gp.player.solidArea.height;
        int playerLeft = enemies.screenX + 8;
        int playerRight = playerLeft + gp.player.solidArea.width;

        int enemieTop = enemies.bodyArea.y;
        int enemieBot = enemieTop + enemies.bodyArea.height;
        int enemieLeft = enemies.bodyArea.x;
        int enemieRight = enemieLeft + enemies.bodyArea.width;

        switch (enemies.eD) {
            case "U":
                if (enemieTop-enemies.dlS<=playerBot&&enemieTop>=playerTop&&enemieLeft <= playerRight && enemieRight >= playerLeft) {
                    enemies.eToPCU = true;
                }
                break;
            case "D":
                if (enemieBot+enemies.dlS>=playerTop &&enemieBot<= playerTop&& enemieLeft <= playerRight && enemieRight >= playerLeft) {
                    enemies.eToPCD = true;
                }
                break;
            case "L":
                if (enemieLeft-enemies.dlS<=playerRight&&enemieLeft>=playerLeft&&enemieBot>=playerTop&&enemieTop<=playerBot) {
                    enemies.eToPCL = true;
                }
                break;
            case "R":
                if (enemieRight+enemies.dlS>=playerLeft&&enemieRight<=playerRight&&enemieBot>=playerTop&&enemieTop<=playerBot) {
                    enemies.eToPCR = true;
                }
                break;
            case "LU":
                if (enemieLeft-enemies.dlS<=playerRight&&enemieLeft>=playerLeft&&enemieBot>=playerTop&&enemieTop<=playerBot) {
                    enemies.eToPCL = true;
                }
                if (enemieTop-enemies.dlS<=playerBot&&enemieTop>=playerTop&&enemieLeft <= playerRight && enemieRight >= playerLeft) {
                    enemies.eToPCU = true;
                }
                break;
            case "LD":
                if (enemieLeft-enemies.dlS<=playerRight&&enemieLeft>=playerLeft&&enemieBot>=playerTop&&enemieTop<=playerBot) {
                    enemies.eToPCL = true;
                }
                if (enemieBot+enemies.dlS>=playerTop &&enemieBot<= playerTop&& enemieLeft <= playerRight && enemieRight >= playerLeft) {
                    enemies.eToPCD = true;
                }
                break;
            case "RU":
                if (enemieTop-enemies.dlS<=playerBot&&enemieTop>=playerTop&&enemieLeft <= playerRight && enemieRight >= playerLeft) {
                    enemies.eToPCU = true;
                }
                if (enemieRight+enemies.dlS>=playerLeft&&enemieRight<=playerRight&&enemieBot>=playerTop&&enemieTop<=playerBot) {
                    enemies.eToPCR = true;
                }
                break;
            case "RD":
                if (enemieRight+enemies.dlS>=playerLeft&&enemieRight<=playerRight&&enemieBot>=playerTop&&enemieTop<=playerBot) {
                    enemies.eToPCR = true;
                }
                if (enemieBot+enemies.dlS>=playerTop &&enemieBot<= playerTop&& enemieLeft <= playerRight && enemieRight >= playerLeft) {
                    enemies.eToPCD = true;
                }
                break;

        }
    }
}


