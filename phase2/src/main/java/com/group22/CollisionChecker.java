package com.group22;


import java.awt.Rectangle;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }


    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                //Get entity's solid area pos
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Get obj's solid area pos
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up": //print for test remove later
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        //System.out.println("up collision");
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }

                        //System.out.println("down collision");
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {

                            if (gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }

                        //System.out.println("left collision");
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                            //System.out.println("right collision");
                            break;
                        }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkCollision(Entity entity, int nextX, int nextY) {
        boolean isCollisionDetected = false;
        int entityLeftCol = (nextX + entity.solidArea.x) / gp.tileSize;
        int entityRightCol = (nextX + entity.solidArea.x + entity.solidArea.width) / gp.tileSize;
        int entityTopRow = (nextY + entity.solidArea.y) / gp.tileSize;
        int entityBottomRow = (nextY + entity.solidArea.y + entity.solidArea.height) / gp.tileSize;
    
        if (isSolidTile(entityLeftCol, entityTopRow) || isSolidTile(entityRightCol, entityTopRow) ||
            isSolidTile(entityLeftCol, entityBottomRow) || isSolidTile(entityRightCol, entityBottomRow)) {
            isCollisionDetected = true;
        } else {
            isCollisionDetected = false;
        }
        return isCollisionDetected;
    }
    
    private boolean isSolidTile(int col, int row) {
        int tileNum = gp.tileM.mapTileNum[col][row];
        return gp.tileM.tile[tileNum].collision;
    }

    //Check zombie collission
    public int checkEntity(Entity entity, Zombie[] target) {
      int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                //Get entiti's solid area pos
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Get obj's solid area pos
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up": //print for test remove later
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                            break;
                }
                if (entity.solidArea.intersects(target[i].solidArea)) {
                    if(target[i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    // public void checkPlayer(Entity entity){
    //             entity.solidArea.x = entity.worldX + entity.solidArea.x;
    //             entity.solidArea.y = entity.worldY + entity.solidArea.y;
    //             //Get obj's solid area pos
    //             gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
    //             gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

    //             switch (entity.direction) {
    //                 case "up": //print for test remove later
    //                     entity.solidArea.y -= entity.speed;
    //                     if (entity.solidArea.intersects(gp.player.solidArea)) {
    //                             entity.collisionOn = true;
    //                         }
    //                     break;
    //                 case "down":
    //                     entity.solidArea.y += entity.speed;
    //                     if (entity.solidArea.intersects(gp.player.solidArea)) {
    //                             entity.collisionOn = true;
    //                     }
    //                     break;
    //                 case "left":
    //                     entity.solidArea.x -= entity.speed;
    //                     if (entity.solidArea.intersects(gp.player.solidArea)) {
    //                             entity.collisionOn = true;
    //                     }
    //                     break;
    //                 case "right":
    //                     entity.solidArea.x += entity.speed;
    //                     if (entity.solidArea.intersects(gp.player.solidArea)) {
    //                             entity.collisionOn = true;
    //                         }
    //                         //System.out.println("right collision");
    //                         break;
    //             }
    //             entity.solidArea.x = entity.solidAreaDefaultX;
    //             entity.solidArea.y = entity.solidAreaDefaultY;
    //             gp.player.solidArea.x = gp.player.solidAreaDefaultX;
    //             gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    // }

    public boolean checkPlayer(Entity entity, int nextX, int nextY) {
        // Calculate the entity's next solid area position
        Rectangle nextSolidArea = new Rectangle(nextX + entity.solidArea.x,
                                                nextY + entity.solidArea.y,
                                                entity.solidArea.width,
                                                entity.solidArea.height);
    
        // Get the player's solid area position
        Rectangle playerSolidArea = new Rectangle(gp.player.worldX + gp.player.solidArea.x,
                                                  gp.player.worldY + gp.player.solidArea.y,
                                                  gp.player.solidArea.width,
                                                  gp.player.solidArea.height);
    
        // Check if the entity's next solid area intersects with the player's solid area
        return nextSolidArea.intersects(playerSolidArea);
    }

    //zombie to zombie
    public void checkEntity(Zombie zombie, Zombie[] target) {
    //   int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                //Get entiti's solid area pos
                zombie.solidArea.x = zombie.worldX + zombie.solidArea.x;
                zombie.solidArea.y = zombie.worldY + zombie.solidArea.y;
                //Get obj's solid area pos
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (zombie.direction) {
                    case "up": //print for test remove later
                        zombie.solidArea.y -= zombie.speed;
                        break;
                    case "down":
                        zombie.solidArea.y += zombie.speed;
                        break;
                    case "left":
                        zombie.solidArea.x -= zombie.speed;
                        break;
                    case "right":
                        zombie.solidArea.x += zombie.speed;
                            break;
                }
                if (zombie.solidArea.intersects(target[i].solidArea)) {
                    if(target[i] != zombie) {
                        zombie.collisionOn = true;
                        //index = i;
                    }
                }
                zombie.solidArea.x = zombie.solidAreaDefaultX;
                zombie.solidArea.y = zombie.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        // return index;
    }
    
}