package com.group22;

import java.awt.*;
import javax.swing.JPanel;

/**
 * Main controller for the game, handles all game systems
 */
public class GamePanel extends JPanel implements Runnable{

    private final int originalTileSize = 16;
    public int scale = 3; //this is to scale 16
    public int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 40;
    public final int maxScreenRow = 22;
    public int screenWidth = tileSize * maxScreenCol; //1920
    public int screenHeight = tileSize * maxScreenRow; //1056

    //FPS
    int FPS = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //default player's position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run(){

        double drawInterval = (double) 1000000000 /FPS; //0.016666 s
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            //system.out.println("game running!");
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        if(keyH.upPressed){
            playerY -= playerSpeed;
        }
        else if(keyH.downPressed){
            playerY += playerSpeed;
        }
        else if(keyH.leftPressed){
            playerX -= playerSpeed;
        }
        else if(keyH.rightPressed){
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }
}
