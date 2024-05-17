package main;

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

    public void checkTileEnemies(Entity entity) {
        int enemieLeft = entity.bodyAreaC.x;
        int enemieRight = enemieLeft + entity.bodyAreaC.width;
        int enemieTop = entity.bodyAreaC.y;
        int enemieBottom = enemieTop + entity.bodyAreaC.height;

        int enemieLeftCol = enemieLeft / gp.tileSize;
        int enemieRightCol = enemieRight / gp.tileSize;
        int enemieTopRow = enemieTop / gp.tileSize;
        int enemieBotRow = enemieBottom / gp.tileSize;

        int up,down,left,right;

        int tileNum1, tileNum2, tileNum3;

        switch (entity.mD) {
            case "U":
                enemieTopRow = (enemieTop - ((int)entity.yMove+1)) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieTopRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieTopRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.eCollision = true;
                    up=enemieTop-enemieTopRow*gp.tileSize;
                    entity.yMove=up;
                }
                break;
            case "RU":
                enemieTopRow = (enemieTop - ((int)entity.yMove+1)) / gp.tileSize;
                enemieRightCol = (enemieRight + ((int)entity.xMove+1)) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieTopRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieTopRow];
                tileNum3 = gp.tileManager.mapTileNum[enemieRightCol][enemieBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    if (gp.tileManager.tile[tileNum3].collision)
                    {
                        entity.eCollisionR = true;
                        right=enemieRight-enemieRightCol*gp.tileSize;
                        entity.xMove=right;
                    }

                }
                if (gp.tileManager.tile[tileNum2].collision || gp.tileManager.tile[tileNum3].collision) {
                    if (gp.tileManager.tile[tileNum1].collision)
                    {
                        entity.eCollisionU = true;
                        up=enemieTop-enemieTopRow*gp.tileSize;
                        entity.yMove=up;
                    }
                }
                break;
            case "LU":
                enemieTopRow = (enemieTop - ((int)entity.yMove+1)) / gp.tileSize;
                enemieLeftCol = (enemieLeft - ((int)entity.xMove+1)) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieTopRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieTopRow];
                tileNum3 = gp.tileManager.mapTileNum[enemieLeftCol][enemieBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    if (gp.tileManager.tile[tileNum3].collision)
                    {
                        entity.eCollisionL = true;
                        left=enemieLeft-enemieLeftCol*gp.tileSize;
                        entity.xMove=left;
                    }
                }
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum3].collision) {
                    if (gp.tileManager.tile[tileNum2].collision)
                    {
                        entity.eCollisionU = true;
                        up=enemieTop-enemieTopRow*gp.tileSize;
                        entity.yMove=up;
                    }
                }
                break;
            case "D":
                enemieBotRow = (enemieBottom + ((int)entity.yMove+1)) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieBotRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.eCollision = true;
                    down=enemieBottom-enemieBotRow*gp.tileSize;
                    entity.yMove=down;
                }
                break;
            case "RD":
                enemieBotRow = (enemieBottom + ((int)entity.yMove+1)) / gp.tileSize;
                enemieRightCol = (enemieRight + ((int)entity.xMove+1)) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieBotRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieBotRow];
                tileNum3 = gp.tileManager.mapTileNum[enemieRightCol][enemieTopRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    if (gp.tileManager.tile[tileNum3].collision)
                    {
                        entity.eCollisionR = true;
                        right=enemieRight-enemieRightCol*gp.tileSize;
                        entity.xMove=right;
                    }
                }
                if (gp.tileManager.tile[tileNum2].collision || gp.tileManager.tile[tileNum3].collision) {
                    if (gp.tileManager.tile[tileNum1].collision)
                    {
                        entity.eCollisionD = true;
                        down=enemieBottom-enemieBotRow*gp.tileSize;
                        entity.yMove=down;
                    }
                }
                break;
            case "LD":
                enemieBotRow = (enemieBottom + ((int)entity.yMove+1)) / gp.tileSize;
                enemieLeftCol = (enemieLeft - ((int)entity.xMove+1)) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieBotRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieBotRow];
                tileNum3 = gp.tileManager.mapTileNum[enemieLeftCol][enemieTopRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    if (gp.tileManager.tile[tileNum3].collision)
                    {
                        entity.eCollisionL = true;
                        left=enemieLeft-enemieLeftCol*gp.tileSize;
                        entity.xMove=left;
                    }
                }
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum3].collision) {
                    if (gp.tileManager.tile[tileNum2].collision)
                    {
                        entity.eCollisionD = true;
                        down=enemieBottom-enemieBotRow*gp.tileSize;
                        entity.yMove=down;
                    }
                }
                break;
            case "L":
                enemieLeftCol = (enemieLeft - ((int)entity.xMove+1)) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieTopRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieLeftCol][enemieBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.eCollision = true;
                    left=enemieLeft-enemieLeftCol*gp.tileSize;
                    entity.xMove=left;
                }
                break;
            case "R":
                enemieRightCol = (enemieRight + ((int)entity.xMove+1)) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieRightCol][enemieTopRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.eCollision = true;
                    right=enemieRight-enemieRightCol*gp.tileSize;
                    entity.xMove=right;
                }
                break;
        }
        System.out.println(entity.yMove);

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
                enemieTop[i] = gp.slime[i].bodyAreaA.y;
                enemieBot[i] = enemieTop[i] + gp.slime[i].bodyAreaA.height;
                enemieLeft[i] = gp.slime[i].bodyAreaA.x;
                enemieRight[i] = enemieLeft[i] + gp.slime[i].bodyAreaA.width;
                if (gp.slime[i].alive) {
                    boolean up=playerTop <= enemieBot[i] && playerTop >= enemieBot[i] && playerLeft <= enemieRight[i] && playerRight >= enemieLeft[i];
                    boolean down=playerBot >= enemieTop[i] && playerBot <= enemieBot[i] & playerLeft <= enemieRight[i] && playerRight >= enemieLeft[i];
                    boolean left= playerLeft <= enemieRight[i] && playerLeft >= enemieLeft[i] && playerBot >= enemieTop[i] && playerTop <= enemieBot[i];
                    boolean right = playerRight >= enemieLeft[i] && playerRight <= enemieRight[i] && playerBot >= enemieTop[i] && playerTop <= enemieBot[i];
                    switch (entity.collisionCheck) {
                        case "up":
                            if (up) {
                                entity.pToECU = true;
                            }
                            break;
                        case "down":
                            if (down) {
                                entity.pToECD = true;
                            }
                            break;
                        case "left":
                            if (left) {
                                entity.pToECL = true;
                            }
                            break;
                        case "right":
                            if (right) {
                                entity.pToECR = true;
                            }
                            break;
                        case "upr":
                            if (up) {
                                entity.pToECU = true;
                            }
                            if (right) {
                                entity.pToECR = true;
                            }
                            break;
                        case "upl":
                            if (left) {
                                entity.pToECL = true;
                            }
                            if (up) {
                                entity.pToECU = true;
                            }
                            break;
                        case "downr":
                            if (right) {
                                entity.pToECR = true;
                            }
                            if (down) {
                                entity.pToECD = true;
                            }
                            break;
                        case "downl":
                            if (down) {
                                entity.pToECD = true;
                            }
                            if (left) {
                                entity.pToECL = true;
                            }
                            break;
                    }
                }
            }
        }
    }

    public void eToPCo(Entity enemies) {

        int playerTop = enemies.screenY + 32;
        int playerBot = playerTop + gp.player.solidArea.height;
        int playerLeft = enemies.screenX + 8;
        int playerRight = playerLeft + gp.player.solidArea.width;

        int enemieTop = enemies.bodyAreaA.y;
        int enemieBot = enemieTop + enemies.bodyAreaA.height;
        int enemieLeft = enemies.bodyAreaA.x;
        int enemieRight = enemieLeft + enemies.bodyAreaA.width;

        switch (enemies.mD) {
            case "U":
                if (enemieTop <= playerBot && enemieTop >= playerTop && enemieLeft <= playerRight && enemieRight >= playerLeft) {
                    enemies.eToPCU = true;
                }
                break;
            case "D":
                if (enemieBot >= playerTop && enemieBot <= playerTop && enemieLeft <= playerRight && enemieRight >= playerLeft) {
                    enemies.eToPCD = true;
                }
                break;
            case "L":
                if (enemieLeft <= playerRight && enemieLeft >= playerLeft && enemieBot >= playerTop && enemieTop <= playerBot) {
                    enemies.eToPCL = true;
                }
                break;
            case "R":
                if (enemieRight  >= playerLeft && enemieRight <= playerRight && enemieBot >= playerTop && enemieTop <= playerBot) {
                    enemies.eToPCR = true;
                }
                break;
            case "LU":
                if (enemieLeft  <= playerRight && enemieLeft >= playerLeft && enemieBot >= playerTop && enemieTop <= playerBot) {
                    enemies.eToPCL = true;
                }
                if (enemieTop <= playerBot && enemieTop >= playerTop && enemieLeft <= playerRight && enemieRight >= playerLeft) {
                    enemies.eToPCU = true;
                }
                break;
            case "LD":
                if (enemieLeft<= playerRight && enemieLeft >= playerLeft && enemieBot >= playerTop && enemieTop <= playerBot) {
                    enemies.eToPCL = true;
                }

                if (enemieBot >= playerTop && enemieBot <= playerTop && enemieLeft <= playerRight && enemieRight >= playerLeft) {
                    enemies.eToPCD = true;
                }
                break;
            case "RU":
                if (enemieTop  <= playerBot && enemieTop >= playerTop && enemieLeft <= playerRight && enemieRight >= playerLeft) {
                    enemies.eToPCU = true;
                }
                if (enemieRight  >= playerLeft && enemieRight <= playerRight && enemieBot >= playerTop && enemieTop <= playerBot) {
                    enemies.eToPCR = true;
                }
                break;
            case "RD":
                if (enemieRight  >= playerLeft && enemieRight <= playerRight && enemieBot >= playerTop && enemieTop <= playerBot) {
                    enemies.eToPCR = true;
                }
                if (enemieBot >= playerTop && enemieBot <= playerTop && enemieLeft <= playerRight && enemieRight >= playerLeft) {
                    enemies.eToPCD = true;
                }

                break;
        }
    }

    public void atkCollision(Entity enemies){
        int enemieLeft = enemies.bodyAreaC.x;
        int enemieRight = enemieLeft + enemies.bodyAreaC.width;
        int enemieTop = enemies.bodyAreaC.y;
        int enemieBottom = enemieTop + enemies.bodyAreaC.height;

        int enemieLeftCol = enemieLeft / gp.tileSize;
        int enemieRightCol = enemieRight / gp.tileSize;
        int enemieTopRow = enemieTop / gp.tileSize;
        int enemieBotRow = enemieBottom / gp.tileSize;

        int tileNum1, tileNum2, tileNum3;

        switch (enemies.mD) {
            case "U":
                enemieTopRow = (enemieTop - enemies.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieTopRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieTopRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    enemies.eCollision = true;
                }
                break;
            case "RU":
                enemieTopRow = (enemieTop - enemies.speed) / gp.tileSize;
                enemieRightCol = (enemieRight + enemies.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieTopRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieTopRow];
                tileNum3 = gp.tileManager.mapTileNum[enemieRightCol][enemieBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    if (gp.tileManager.tile[tileNum3].collision)
                        enemies.eCollisionR = true;
                }
                if (gp.tileManager.tile[tileNum2].collision || gp.tileManager.tile[tileNum3].collision) {
                    if (gp.tileManager.tile[tileNum1].collision)
                        enemies.eCollisionU = true;
                }
                break;
            case "LU":
                enemieTopRow = (enemieTop - enemies.speed) / gp.tileSize;
                enemieLeftCol = (enemieLeft - enemies.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieTopRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieTopRow];
                tileNum3 = gp.tileManager.mapTileNum[enemieLeftCol][enemieBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    if (gp.tileManager.tile[tileNum3].collision)
                        enemies.eCollisionL = true;
                }
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum3].collision) {
                    if (gp.tileManager.tile[tileNum2].collision)
                        enemies.eCollisionU = true;
                }
                break;
            case "D":
                enemieBotRow = (enemieBottom + enemies.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieBotRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    enemies.eCollision = true;
                }
                break;
            case "RD":
                enemieBotRow = (enemieBottom + enemies.speed) / gp.tileSize;
                enemieRightCol = (enemieRight + enemies.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieBotRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieBotRow];
                tileNum3 = gp.tileManager.mapTileNum[enemieRightCol][enemieTopRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    if (gp.tileManager.tile[tileNum3].collision)
                        enemies.eCollisionR = true;
                }
                if (gp.tileManager.tile[tileNum2].collision || gp.tileManager.tile[tileNum3].collision) {
                    if (gp.tileManager.tile[tileNum1].collision)
                        enemies.eCollisionD = true;
                }
                break;
            case "LD":
                enemieBotRow = (enemieBottom + enemies.speed) / gp.tileSize;
                enemieLeftCol = (enemieLeft - enemies.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieBotRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieBotRow];
                tileNum3 = gp.tileManager.mapTileNum[enemieLeftCol][enemieTopRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    if (gp.tileManager.tile[tileNum3].collision)
                        enemies.eCollisionL = true;
                }
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum3].collision) {
                    if (gp.tileManager.tile[tileNum2].collision)
                        enemies.eCollisionD = true;
                }
                break;
            case "L":
                enemieLeftCol = (enemieLeft - 1) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieLeftCol][enemieTopRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieLeftCol][enemieBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    enemies.eCollision = true;
                }
                break;
            case "R":
                enemieRightCol = (enemieRight + 1) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[enemieRightCol][enemieTopRow];
                tileNum2 = gp.tileManager.mapTileNum[enemieRightCol][enemieBotRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    enemies.eCollision = true;
                }
                break;
        }
    }
}


