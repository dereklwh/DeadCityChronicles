package com.group22;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * Main controller for the game, handles all game systems
 */
public class dGamePanel extends JPanel implements Runnable{

    private final int originalTileSize = 16;
    public int scale = 3;
    public int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 40;
    public final int maxScreenRow = 22;
    public int screenWidth = tileSize * maxScreenCol; //1920
    public int screenHeight = tileSize * maxScreenRow; //1056

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){

    }
}
