package com.group22.entities;

import com.group22.GamePanel;
import com.group22.KeyHandler;
import com.group22.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * The Player class represents the player-controlled character in the game. It extends the Entity class
 * with player-specific functionality such as handling input, updating player status, and drawing the player sprite.
 */
public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public String name = "Carl";
    public int hasKey = 0;
    public int hasVaccine = 0;
    public  int screenX;
    public  int screenY;

    BufferedImage damageImage1, damageImage2, damageImage3;
    boolean isDamaged = false;
    int damageAnimationDuration = 9; // Duration of damage animation in frames
    int damageAnimationFrame = 0; // Current frame of the damage animation

    public Map<String, BufferedImage> spriteMap = new HashMap<>();
    
    
    /**
     * Constructor that initializes a new Player object with a reference to the GamePanel and KeyHandler.
     * @param gp The game panel that the player exists within.
     * @param keyH The key handler for managing player input.
     */
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
        initializeSpriteMap();
    }
    
    private void initializeSpriteMap() {
    	spriteMap.put("up1", up1);
    	spriteMap.put("up2", up2);
    	spriteMap.put("up3", up3);
    	spriteMap.put("down1", down1);
    	spriteMap.put("down2", down2);
    	spriteMap.put("down3", down3);
    	spriteMap.put("left1", left1);
    	spriteMap.put("left2", left2);
    	spriteMap.put("left3", left3);
    	spriteMap.put("right1", right1);
    	spriteMap.put("right2", right2);
    	spriteMap.put("right3", right3);
    }

    /**
     * Sets default values for player properties like world position, speed, and life.
     */
    public void setDefaultValues(){
        worldX = gp.tileSize * 39;
        worldY = gp.tileSize * 21;
        speed = 5;
        direction = "down";
        maxLife = 6; //2 lives = 1 heart
        life = maxLife;
    }

    /**
     * Restores the player's position and status to default values.
     */
    public void restorePos(){
        worldX = gp.tileSize * 39;
        worldY = gp.tileSize * 21;
        direction = "down";
        life = maxLife;
        invincible = false;
        hasKey = 0;
        hasVaccine = 0;
    }

    /**
     * Loads and assigns player images for various states and directions.
     */
    public void getPlayerImage(){
        damageImage1 = setup("damage");
        damageImage2 = setup("damage2");
        damageImage3 = setup("damage3");
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

    /**
     * Helper method to load an image and scale it to the appropriate size.
     * @param imageName The name of the image file to load.
     * @return A BufferedImage object representing the loaded and scaled image.
     */
    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
    
    /**
     * Retrieves the appropriate damage image based on the damage animation frame.
     * @return A bufferedImage object representing damaged player sprite.
     */
    public BufferedImage getDamageImage() {
        if (damageAnimationFrame <= damageAnimationDuration / 4) {
            return damageImage1;
        } else if (damageAnimationFrame <= (damageAnimationDuration * 2) / 3) {
            return damageImage2;
        } else {
            return damageImage3;
        }
    }

    /**
     * updates the player's state each frame, handling movement, collision, and interactions.
     */
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

                if (isDamaged) {
                    damageAnimationFrame++;
                    if (damageAnimationFrame > damageAnimationDuration) {
                        isDamaged = false;
                        damageAnimationFrame = 0;
                    }
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
                gp.playSE(6);
            }
       
        //Check tile collision
        collisionOn = false;
        gp.cChecker.checkTile(this);

        // Check object collision
        int objectIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objectIndex);

        int zombieIndex = gp.cChecker.checkEntity(this, gp.zombie);
        interactZombie(zombieIndex);

        if(collisionOn == false){
            worldX += deltaX;
            worldY += deltaY;
        }

        if (invincible ==true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter =0;
            }
        }
    }

    /**
     * Handles interactions when the player picks up an object.
     * @param i The index of the object that the player interacts with.
     */
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

    /**
     * Draws the player's current sprite to the screen based on the player's state and direction.
     * @param g2 The Graphics2D context to draw on.
     */
    public void draw(Graphics2D g2){
        BufferedImage image = null;

        if (isDamaged){
        	image = getDamageImage();
        	damageAnimationFrame++;
            if (damageAnimationFrame > damageAnimationDuration) {
                isDamaged = false;
                damageAnimationFrame = 0;
            }
        } else{
        	String key = direction + spriteNum;
        	image = spriteMap.getOrDefault(key, stop);
        }

        g2.drawImage(image, screenX, screenY, null);
    }

    /**
     * Handles interaction when player collides with zombie
     * @param i The index of the zombie that is interacting with player
     */
    public void interactZombie(int i){
        if(i != 999) {
            if(hasVaccine > 0) {
                // Player has a vaccine, so they do not take damage, and the zombie is cured
                hasVaccine--; 
                gp.zombie[i].setRemoveThis(true); // Mark the zombie for removal
                gp.ui.showMessage("Zombie cured!");
            } else if(!invincible) {
                // Player does not have a vaccine and is not invincible, so they take damage
                life--;
                isDamaged = true;
                invincible = true;
                gp.playSE(3); // Play damage sound effect
            }
        }
    }
    
    }
