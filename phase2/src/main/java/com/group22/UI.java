package com.group22;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Font;

import com.group22.ObjectKey;
import com.group22.ObjectHeart;
import com.group22.ObjectVaccine;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80; //Find new font later
    BufferedImage keyImage;
    BufferedImage hImage;
    BufferedImage vImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.BOLD, 80);

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

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white); 

        if(gp.gameState == gp.playState){
            playTime += (double)1/60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*15, 60);

        }
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*15, 60);

        }

        if (gameFinished == true){

            g2.setFont(arial_40);
            g2.setColor(Color.white); 

            String text;
            int textLength;
            int x;
            int y; 

            text = "You won";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y= gp.screenHeight/2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            text = "Your Time is: " + dFormat.format(playTime);
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y= gp.screenHeight/2 + (gp.tileSize * 4);
            g2.drawString(text, x, y);

            g2.setFont(arial_80);
            g2.setColor(Color.yellow);
            text = "Congratulations";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y= gp.screenHeight/2 + (gp.tileSize * 2);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }

        else {
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

    public void drawPauseScreen(){
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
