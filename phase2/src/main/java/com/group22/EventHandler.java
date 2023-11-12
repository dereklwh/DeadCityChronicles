package com.group22;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    EventRect eventRect[][];
    public EventHandler(GamePanel gp){
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }

    }

    public void checkEvent(){
        //check if the player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDisance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDisance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }

        if(canTouchEvent == true){
            //add stuff that hit the player here and also heal player
            //eg: if(hit(27, 16) == true) damage();
        }
    }

    public boolean hit(int col, int row){
        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row])){
            hit = true;

            previousEventX = gp.player.worldX;
            previousEventY = gp.player.worldY;
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void damage(){
        gp.player.life -= 1;
        gp.ui.showMessage("You're getting hurt");
        gp.playSE(3);
        canTouchEvent = false;
    }

    public void heal(){
        gp.player.life += 1;
        gp.ui.showMessage("boosted health");
        gp.playSE(1);
    }

}
