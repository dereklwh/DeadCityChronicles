package com.group22;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public String name = "Carl";
    public int hasKey = 0;
    public int hasVaccine = 0;
    public  int screenX;
    public  int screenY;

    BufferedImage damageImage;
    boolean isDamaged = false;

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
        worldX = gp.tileSize * 39;
        worldY = gp.tileSize * 21;
        speed = 5;
        direction = "down";

        //player status
        maxLife = 6; //2 lives = 1 heart
        life = maxLife;
    }

    public void restorePos(){
        worldX = gp.tileSize * 39;
        worldY = gp.tileSize * 21;
        direction = "down";
        life = maxLife;
        invincible = false;
        hasKey = 0;
        hasVaccine = 0;

    }


    public void getPlayerImage(){
        /*try{
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
        }*/
        damageImage = setup("damage2");
        up1 = setup("run_right0");
        up2 = setup("run_right1");
        up3 = setup("run_right2");
        down1 = setup("run_left0");
        down2 = setup("run_left1");
        down3 = setup("run_left2");
        left1 = setup("run_left0");
        left2 = setup("run_left1");
        left3 = setup("run_left2");
        right1 = setup("run_right0");
        right2 = setup("run_right1");
        right3 = setup("run_right2");
        stop = setup("no_anim_0");
    }

    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("res/player/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public void update(){
        int deltaX = 0;
        int deltaY = 0;
        if (keyH.upPressed == true || keyH.downPressed == true || 
            keyH.leftPressed == true || keyH.rightPressed == true ){
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

        if (life <=0){
                gp.gameState = gp.gameOverState;
                //play gameover sound effect: I put the sound effect play in the draw method (Sina) if it didn't work move it here
            }
       
        //Check tile collision
        collisionOn = false;
        gp.cChecker.checkTile(this);

        // Check object collision
        int objectIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objectIndex);

        int zombieIndex = gp.cChecker.checkEntity(this, gp.zombie);
        interactZombie(zombieIndex);

        //Check event
        //gp.eHandler.checkEvent();

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

        if (invincible ==true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter =0;
            }
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
                case "Door":
                    if (hasKey != 3){
                        gp.ui.showMessage("You need 3 keys to open the door");
                    }
                    else if (hasKey == 3){
                        gp.ui.gameFinished = true;
                        gp.stopMusic();

                    }
                break;
                case "Trap":
                 if(invincible == false){
                    life -=1;
                    gp.playSE(3);
                    isDamaged = true;
                    invincible = true;
                }
                break;
           }
        }
    }

    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(worldX, worldY, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        if (isDamaged){
            image = damageImage;
        } else{
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
        }

        
        //g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        g2.drawImage(image, screenX, screenY, null);
        isDamaged = false;
    }
        public void interactZombie(int i){
            if(i != 999) {
                if(invincible == false){
                    life -=1;
                    invincible = true;
                }
                //System.out.println("hitting npc" + i);
            }
        }
    
    }
