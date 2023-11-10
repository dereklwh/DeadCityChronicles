package com.group22;

import java.awt.*;
import javax.swing.JPanel;

import tile.TileManager;

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

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int FPS = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public  AssetSetter aSetter = new AssetSetter(this);

    //Sound
    Sound sound = new Sound();
    //Entity and Object
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; //how many objects we can show

    //Tile
    TileManager tileM = new TileManager(this);

    //default player's position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    //

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        playMusic(0);
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
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //Object
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        //Player
        player.draw(g2);
        //map
        tileM.draw(g2);
        g2.dispose();
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
