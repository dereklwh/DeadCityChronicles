package com.group22;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Zombie extends Entity{
    GamePanel gp;
    int worldX, worldY;
    int speed;
    String direction;

    BufferedImage up1, up2, up3,up4, down1,down2,down3, down4,left1,left2,left3,left4,right1, right2,right3,right4;

    public final int screenX;
    public final int screenY;

    public Zombie(GamePanel gp) {
        this.gp = gp;

        screenX = gp.screenWidth/2 - (gp.tileSize);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        getZombieImage();
    }

    public void setDefaultValues(){
        // Set the default values for the zombie here
        // We will just place the zombie at a random position within the world bounds
        worldX = gp.tileSize * 10;
        worldY = gp.tileSize * 5;
        speed = 3; // Zombies are slower than the player
        direction = "down"; // Initial direction
    }

    // Load images for zombie
    public void getZombieImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("res/zombie1/zombie1_run_right0.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("res/zombie1/zombie1_run_right1.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("res/zombie1/zombie1_run_right2.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("res/zombie1/zombie1_run_right3.png"));
         
            down1 = ImageIO.read(getClass().getResourceAsStream("res/zombie1/zombie1_run_left0.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("res/zombie1/zombie1_run_left1.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("res/zombie1/zombie1_run_left2.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("res/zombie1/zombie1_run_left3.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("res/zombie1/zombie1_run_left0.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("res/zombie1/zombie1_run_left1.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("res/zombie1/zombie1_run_left2.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("res/zombie1/zombie1_run_left3.png"));
            
            right1 = ImageIO.read(getClass().getResourceAsStream("res/zombie1/zombie1_run_right0.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("res/zombie1/zombie1_run_right1.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("res/zombie1/zombie1_run_right2.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("res/zombie1/zombie1_run_right3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        int xDistance = gp.player.worldX - worldX;
        int yDistance = gp.player.worldY - worldY;
        String xDirection = xDistance > 0 ? "right" : "left";
        String yDirection = yDistance > 0 ? "down" : "up";

        // Simple AI for zombie: move in the direction with the greatest distance from the player
        if (Math.abs(xDistance) > Math.abs(yDistance)) {
            direction = xDirection;
        } else {
            direction = yDirection;
        }

        // Move zombie in the chosen direction
        switch (direction) {
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
        }

        spriteCounter++;
        if(spriteCounter > 12){ //zombie image changes every 12 frames
            if(spriteNum == 1) {
                spriteNum = 2;
            }else if (spriteNum == 2){
                spriteNum = 3;
            }
            else if (spriteNum == 3){
                spriteNum = 4;
            }
            else if (spriteNum == 4){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        // Get the player's position on the screen
        int playerScreenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        int playerScreenY = gp.screenHeight / 2 - (gp.tileSize / 2);
    
        // Calculate the zombie's position on the screen based on the player's position
        // This assumes the player is always centered on the screen
        int zombieScreenX = worldX - gp.player.worldX + playerScreenX;
        int zombieScreenY = worldY - gp.player.worldY + playerScreenY;
    
        //draw the zombie at its screen position
        BufferedImage image = null;
        switch (direction) {
        	case "up":
        		if(spriteNum == 1){
        			image = up1;
        		}else if(spriteNum == 2){
        			image = up2;
        		}
        		else if(spriteNum == 3) {
        			image = up3; }
        		else if(spriteNum == 4) {
        			image = up4; }
        		break;
        	case "down":
        		if(spriteNum == 1){
        			image = down1;
        		}else if(spriteNum == 2){
        			image = down2;
        		}else if(spriteNum == 3) {
        			image = down3;}
        		else if(spriteNum == 4) {
        			image = down4; }
        		
        		break;
        	case "left":
        		if(spriteNum == 1){
        			image = left1;
        		}else if(spriteNum == 2){
        			image = left2;
        		}
        		else if(spriteNum == 3) {
        			image = left3;}
        		else if(spriteNum == 4) {
        			image = left4; }
        		break;
        	case "right":
        		if(spriteNum == 1){
        			image = right1;
        		}else if(spriteNum == 2){
        			image = right2;
        		}
        		else if(spriteNum == 3) {
        			image = right3;}
        		else if(spriteNum == 4) {
        			image = right4; }
            
        		break;
        }
    
        g2.drawImage(image, zombieScreenX, zombieScreenY, gp.tileSize, gp.tileSize, null);
    }
    

}
