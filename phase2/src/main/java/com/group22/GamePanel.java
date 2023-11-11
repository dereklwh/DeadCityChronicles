package com.group22;

import java.awt.*;
import javax.swing.JPanel;

import com.group22.tile.TileManager;


/**
 * Main controller for the game, handles all game systems
 */
public class GamePanel extends JPanel implements Runnable{

    private final int originalTileSize = 16;
    public int scale = 3; //this is to scale 16
    public int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 16; //40
    public final int maxScreenRow = 12; //22
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
    public CollisionChecker cChecker = new CollisionChecker(this);
    public  AssetSetter aSetter = new AssetSetter(this);

    //Sound
    Sound music = new Sound();
    Sound se = new Sound();

    //Entity and Object
    public Player player = new Player(this, keyH);
    public Zombie zombie = new Zombie(this);
    public SuperObject obj[] = new SuperObject[10]; //how many objects we can show

    //Tile
    TileManager tileM = new TileManager(this);

    //default player's position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    //UI
    public UI ui = new UI(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        //this.setBackground(Color.black);
        this.setBackground(new java.awt.Color(71, 53, 44));
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
            //System.out.println("game running!");
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
                //throw new RuntimeException(e);
                e.printStackTrace();
            }
        }
    }

    public void update(){
        player.update();
        System.out.println("Player Position: " + player.worldX + ", " + player.worldY);
        zombie.update();
        System.out.println("Zombie Position: " + zombie.worldX + ", " + zombie.worldY);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //map
        tileM.draw(g2);
        //Object
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        //Player
        player.draw(g2);
        //Zombie1
        zombie.draw(g2);

        //UI
        ui.draw(g2);
        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}
