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

    BufferedImage up, down, left, right;

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
        speed = 10; // Zombies are slower than the player
        direction = "down"; // Initial direction
    }

    // Load images for zombie
    public void getZombieImage() {
        try {
            up = ImageIO.read(getClass().getResourceAsStream("res/zombie1/idle_0.png"));
            down = ImageIO.read(getClass().getResourceAsStream("res/zombie1/idle_0.png"));
            left = ImageIO.read(getClass().getResourceAsStream("res/zombie1/idle_0.png"));
            right = ImageIO.read(getClass().getResourceAsStream("res/zombie1/idle_0.png"));
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
                image = up;
                break;
            case "down":
                image = down;
                break;
            case "left":
                image = left;
                break;
            case "right":
                image = right;
                break;
        }
    
        g2.drawImage(image, zombieScreenX, zombieScreenY, gp.tileSize, gp.tileSize, null);
    }
    

}
