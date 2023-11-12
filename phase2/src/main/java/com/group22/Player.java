package com.group22;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    
    public int hasKey = 0;
    public int hasVaccine = 0;
    public int hasHeart = 0;

    public  int screenX;
    public  int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

    
        solidArea = new Rectangle(8,16,32,32); //x y width height

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 5;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("res/player/run_right0.png")); //need sprite for player facing up
            up2 = ImageIO.read(getClass().getResourceAsStream("res/player/run_right1.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("res/player/run_right2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("res/player/run_left0.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("res/player/run_left1.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("res/player/run_left2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("res/player/run_left0.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("res/player/run_left1.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("res/player/run_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("res/player/run_right0.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("res/player/run_right1.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("res/player/run_right2.png"));
            stop = ImageIO.read(getClass().getResourceAsStream("res/player/no_anim_0.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        int deltaX = 0;
        int deltaY = 0;
        if(keyH.upPressed){
            deltaY = -speed;
            direction = "up";
        }
        else if(keyH.downPressed){
            deltaY = speed;
            direction = "down";
        }
        else if(keyH.leftPressed){
            deltaX = -speed;
            direction = "left";
        }
        else if(keyH.rightPressed){
            deltaX = speed;
            direction = "right";
        }
        else {
        	direction = "stop";
        }

        //Check tile collision
        collisionOn = false;
        gp.cChecker.checkTile(this);

        // Check object collision
        int objectIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objectIndex);

        int zombieIndex = gp.cChecker.checkEntity(this, gp.zombie);
        interactNPC(zombieIndex);


        if(collisionOn == false){
            worldX += deltaX;
            worldY += deltaY;
            /*switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }*/
        }


        spriteCounter++;
        if(spriteCounter > 12){ //player image changes every 12 frames
            if(spriteNum == 1) {
                spriteNum = 2;
            }else if (spriteNum == 2){
                spriteNum = 3;
            }
            else if (spriteNum == 3){
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

                    gp.ui.showMessage("You got a key");

                   break;
               case "Vaccine":
                   gp.playSE(1);
                   hasVaccine++;
                   gp.obj[i] = null;
                    
                    gp.ui.showMessage("You got a vaccine");

                    /*if (hasVaccine == 2){
                        gp.ui.gameFinished = true;
                    }*/

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

                   gp.ui.showMessage("You fell into a trap");

                   break;
           }
        }
    }

    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(worldX, worldY, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }else if(spriteNum == 2){
                    image = up2;
                }
                else if(spriteNum == 3) {
                	image = up3; }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }else if(spriteNum == 2){
                    image = down2;
                }else if(spriteNum == 3) {
                	image = down3;}
               
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }else if(spriteNum == 2){
                    image = left2;
                }
                else if(spriteNum == 3) {
                	image = left3;}
              
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }else if(spriteNum == 2){
                    image = right2;
                }
                else if(spriteNum == 3) {
                	image = right3;}
                
                break;
            case "stop":
            	image = stop;
        }
            	
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

        public void interactNPC(int i){
            if(i != 999) {
                System.out.println("hitting npc" + i);
            }
        }
    
    }