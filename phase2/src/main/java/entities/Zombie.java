package entities;

import com.group22.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The Zombie class extends the Entity class with properties and methods specific to zombies in the game.
 * It manages zombie animations, movement, AI behavior, and interaction with the player and environment.
 */
public class Zombie extends Entity{
    GamePanel gp;
    public int worldX;
    public int worldY;
    public int speed;
    public String direction;
    private int zombieType;
    public int solidAreaDefaultX, solidAreaDefaultY;
    private boolean removeThis = false;

    BufferedImage up1, up2, up3,up4, down1,down2,down3, down4,left1,left2,left3,left4,right1, right2,right3,right4;

    public final int screenX;
    public final int screenY;
    public int actionLockCounter = 0;

    /**
     * Constructor for the Zombie class.
     * @param gp The GamePanel object that the zombie will interact with.
     * @param zombieType The type identifier for the zombie.
     */
    public Zombie(GamePanel gp, int zombieType) {
        this.gp = gp;
        this.zombieType = zombieType;


        screenX = gp.screenWidth/2 - (gp.tileSize);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        getZombieImage();
    }

    public void setDefaultValues(){
        speed = 3; // Zombies are slower than the player
        direction = "down"; // Initial direction
    }

    // Load images for zombie
    public void getZombieImage() {
        try {
            String basePath = "/zombie" + zombieType + "/zombie" + zombieType + "_";
    
            up1 = ImageIO.read(getClass().getResourceAsStream(basePath + "run_right0.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream(basePath + "run_right1.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream(basePath + "run_right2.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream(basePath + "run_right3.png"));
            
            down1 = ImageIO.read(getClass().getResourceAsStream(basePath + "run_left0.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream(basePath + "run_left1.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream(basePath + "run_left2.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream(basePath + "run_left3.png"));
    
            left1 = ImageIO.read(getClass().getResourceAsStream(basePath + "run_left0.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream(basePath + "run_left1.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream(basePath + "run_left2.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream(basePath + "run_left3.png"));
            
            right1 = ImageIO.read(getClass().getResourceAsStream(basePath + "run_right0.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream(basePath + "run_right1.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream(basePath + "run_right2.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream(basePath + "run_right3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public void update() {
            int xDistance = gp.player.worldX - worldX;
            int yDistance = gp.player.worldY - worldY;
            String xDirection = xDistance > 0 ? "right" : "left";
            String yDirection = yDistance > 0 ? "down" : "up";

        //update the direction every .5 seconds
        actionLockCounter ++;
        if (actionLockCounter == 30){
            // Simple AI for zombie: move in the direction with the greatest distance from the player
            if (Math.abs(xDistance) > Math.abs(yDistance)) {
                direction = xDirection;
            } else {
                direction = yDirection;
            }
            actionLockCounter = 0;
        }

        int nextX = worldX;
        int nextY = worldY;

        switch (direction) {
            case "up": nextY -= speed; break;
            case "down": nextY += speed; break;
            case "left": nextX -= speed; break;
            case "right": nextX += speed; break;
        }

        // New collision check
        collisionOn = gp.cChecker.checkCollision(this, nextX, nextY);
        boolean playerCollision = gp.cChecker.checkPlayer(this, nextX, nextY);
        gp.cChecker.checkEntity(this, gp.zombie);

        if (playerCollision){
            collisionOn = true;
            if (gp.player.hasVaccine == 0 && gp.player.invincible == false){
                gp.player.isDamaged = true;
                gp.player.life -=1;
                gp.player.invincible = true;
                gp.playSE(3);
            }else if (gp.player.hasVaccine > 0){
                gp.player.hasVaccine--; // Use up a vaccine
                this.setRemoveThis(true); // Mark the zombie for removal
                gp.ui.showMessage("Zombie cured!");
            }
        }


        if (collisionOn) {
            // Check for viable alternative paths
            boolean canMoveVertically = !gp.cChecker.checkCollision(this, worldX, worldY + (yDistance > 0 ? speed : -speed));
            boolean canMoveHorizontally = !gp.cChecker.checkCollision(this, worldX + (xDistance > 0 ? speed : -speed), worldY);
            
            if ((direction.equals("left") || direction.equals("right")) && canMoveVertically) {
                direction = (yDistance > 0) ? "down" : "up";
            } else if ((direction.equals("up") || direction.equals("down")) && canMoveHorizontally) {
                direction = (xDistance > 0) ? "right" : "left";
            } else {
                //???
            }
        } else {
            worldX = nextX;
            worldY = nextY;
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
        // System.out.printf("the collision is %b\n", collisionOn);
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

    public void setRemoveThis(boolean status) {
        removeThis = status;
    }

    public boolean isRemoveThis() {
        return removeThis;
    }

}