package com.group22;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    BufferedImage heart_full, heart_half, heart_blank;
    BufferedImage keyImage;
    BufferedImage hImage;
    BufferedImage vImage;

    BufferedImage medImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public int commandNum =0;
    int subState = 0;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        
        
        try {
        	InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			maruMonica = new Font("Arial", Font.PLAIN, 20);
		}

        //HUD

        ObjectKey key = new ObjectKey(gp);
        keyImage = key.image;


        ObjectVaccine vaccine = new ObjectVaccine(gp);
        vImage = vaccine.image;

        ObjectMed med = new ObjectMed(gp);
        medImage = med.image;

        ObjectHeart heart = new ObjectHeart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white); 

        if(gp.gameState == gp.playState){
            playTime += (double)1/60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*15, 60);

            drawPlayerLife();
        }
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*15, 60);

            drawPlayerLife();

        }
        if (gp.gameState == gp.settingState){
            drawSettingScreen();
        }

        if (gameFinished == true){

            g2.setFont(maruMonica);
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

            g2.setFont(maruMonica);
            g2.setColor(Color.yellow);
            text = "Congratulations";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y= gp.screenHeight/2 + (gp.tileSize * 2);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }

        else {
            g2.setFont(maruMonica);
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

    public void drawPlayerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        //draw max
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        //reset values
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //draw current life
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawPauseScreen(){
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawSettingScreen(){
        g2.setColor(Color.white);
        g2.setFont(maruMonica);

        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0:
                setting_top(frameX, frameY);
                break;
            case 1:
                fullScreenNoti(frameX, frameY);
                break;
            case 2:
                setting_control(frameX, frameY);
                break;
            case 3:
                endGame(frameX, frameY);
                break;
        }

        gp.keyH.enterPressed = false;

    }

    public void setting_top(int frameX, int frameY){
        int textX;
        int textY;

        String text = "Settings";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        //Fullscreen on/off
        textX = frameX +gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Fullscreen", textX, textY);
        if(commandNum ==0){
            g2.drawString(">", textX -25, textY);
            if(gp.keyH.enterPressed == true){
                if(gp.fullScreenOn ==false){
                    gp.fullScreenOn = true;
                }
                else if  (gp.fullScreenOn ==true){
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }

        //Sound
        textY += gp.tileSize;
        g2.drawString("Sound", textX, textY);
        if(commandNum ==1){
            g2.drawString(">", textX -25, textY);
        }

        //Control
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if(commandNum ==2){
            g2.drawString(">", textX -25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 2;
                commandNum =0;
            }
        }       

        //Quit
        textY += gp.tileSize;
        g2.drawString("Quit Game", textX, textY);
        if(commandNum ==3){
            g2.drawString(">", textX -25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 3;
                commandNum = 0;    
            }
        }

        //Resume
        textY += gp.tileSize*2;
        g2.drawString("Resume", textX, textY);
        if(commandNum ==4){
            g2.drawString(">", textX -25, textY);
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        //Fullscreen checkbox 
        textX = frameX + (int)gp.tileSize*5;
        textY = frameY + gp.tileSize*2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn == true){
            g2.fillRect(textX, textY, 24, 24);
        }

        //Volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24*gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);
    
    }
    

    public void fullScreenNoti(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        String text = "The change will take \neffect after restarting \nthe game.";
        for(String line: text.split("\n")){
            g2.drawString(line, textX-35, textY);
            textY += 40;
        }

        //back
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if (commandNum ==0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed==true){
                subState =0;
            }
        }
    }

    public void setting_control(int frameX, int frameY){
        int textX;
        int textY;

        String text = "Control";
        textX = getXforCenteredText(text);
        textY = frameY +gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY+=gp.tileSize;
        g2.drawString("Confirm", textX, textY); textY+=gp.tileSize;
        g2.drawString("Pause", textX, textY); textY+=gp.tileSize;
        g2.drawString("Settings", textX, textY); textY+=gp.tileSize;

        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WASD", textX -80, textY); textY+=gp.tileSize;
        g2.drawString("ENTER", textX - 80, textY); textY+=gp.tileSize;
        g2.drawString("P", textX - 80, textY); textY+=gp.tileSize;
        g2.drawString("ESCAPE", textX - 80, textY); textY+=gp.tileSize;

        //Back
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if (commandNum ==0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed==true){
                subState =0;
            }
        }

    }

    public void endGame(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize;

        
        String text = "Quit the game and \nreturn to the \ntitle screen?";
        for(String line: text.split("\n")){
            g2.drawString(line, textX-35, textY);
            textY += 40;
        }

        //YES
        String text2 = "Yes";
        textX = getXforCenteredText(text2);
        textY += gp.tileSize*3;
        g2.drawString(text2, textX, textY);
        if (commandNum ==0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed==true){
                subState =0;
                //change game state
            }
        }


        //NO
        String text3 = "No";
        textX = getXforCenteredText(text3);
        textY += gp.tileSize;
        g2.drawString(text3, textX, textY);
        if (commandNum ==1){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed==true){
                subState =0;
                commandNum = 3;
            }
        }
    }

    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(71, 53, 44, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

    }
}
