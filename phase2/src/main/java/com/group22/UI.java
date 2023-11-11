package com.group22;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;

import com.group22.ObjectKey;
import com.group22.ObjectHeart;
import com.group22.ObjectVaccine;

public class UI {
    
    GamePanel gp;
    Font arial_40; //Find new font later
    BufferedImage keyImage;
    BufferedImage hImage;
    BufferedImage vImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;


    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);

        ObjectKey key = new ObjectKey();
        keyImage = key.image;

        ObjectHeart heart = new ObjectHeart();
        hImage = heart.image;

        ObjectVaccine vaccine = new ObjectVaccine();
        vImage = vaccine.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        g2.setFont(arial_40);
        g2.setColor(Color.white); 

        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasKey, 74, 60);

        g2.drawImage(vImage, gp.tileSize/2 + 150, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasVaccine, 235, 60);

        if(messageOn == true){

            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

            messageCounter++;

            if(messageCounter > 120){
                messageCounter =0;
                messageOn = false;
            }
        }
    }
}
