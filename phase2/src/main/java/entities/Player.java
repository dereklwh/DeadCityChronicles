package entities;

import com.group22.GamePanel;
import com.group22.KeyHandler;
import com.group22.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


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

    /**
     * Draws the player's current sprite to the screen based on the player's state and direction.
     * @param g2 The Graphics2D context to draw on.
     */
    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(worldX, worldY, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        if (isDamaged){
            if(damageAnimationFrame <= damageAnimationDuration / 4){//shorter frame
                image = damageImage1;
            }else if (damageAnimationFrame <= (damageAnimationDuration*2)/3){
                image = damageImage2;
            }else{
                image = damageImage3;
            }
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
    }

        public void interactZombie(int i){
            if(i != 999) {
                if(hasVaccine > 0) {
                    // Player has a vaccine, so they do not take damage, and the zombie is cured
                    hasVaccine--; // Use up a vaccine
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
