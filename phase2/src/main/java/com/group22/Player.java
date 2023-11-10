package com.group22;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Key;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    int hasKey = 0;
    int hasVaccine = 0;
    int hasHeart = 0;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        //solidAreaDefaultX = solidArea.x;
        //getSolidAreaDefaultY = solidArea.y;

        solidArea = new Rectangle(8,16,32,32); //x y width height

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp . tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("Adress of png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("Adress of png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("Adress of png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("Adress of png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("Adress of png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("Adress of png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("Adress of png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("Adress of png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed){
            direction = "up";
            worldY -= speed;
        }
        else if(keyH.downPressed){
            direction = "down";
            worldY += speed;
        }
        else if(keyH.leftPressed){
            direction = "left";
            worldX -= speed;
        }
        else if(keyH.rightPressed){
            direction = "right";
            worldX += speed;
        }

        // Check object collision
        int objectIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objectIndex);


        spriteCounter++;
        if(spriteCounter > 12){ //player image changes every 12 frames
            if(spriteNum == 1) {
                spriteNum = 2;
            }else if (spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void pickUpObject(int i){
        if(i != 999){
           String objectName = gp.obj[i].name;
           switch(objectName){
               case "Key":
                   gp.playSE(1);
                   hasKey++;
                   gp.obj[i] = null;
                   break;
               case "Vaccine":
                   gp.playSE(1);
                   hasVaccine++;
                   gp.obj[i] = null;
                   break;
               case"Heart":
                   gp.playSE(1);
                   hasHeart++;
                   gp.obj[i] = null;
                   break;
               case "Trap":
                   //implement later
                   gp.playSE(3);
                   hasHeart--;
                   break;
           }
        }
    }

    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }else if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }else if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }else if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }else if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
