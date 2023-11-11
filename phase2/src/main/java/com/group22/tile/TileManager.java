package com.group22.tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.group22.GamePanel;

// under package Tile;

public class TileManager{

    GamePanel gp;
    public Tile[] tile;
    //need to create the map package and import data text file into pack.
    public int mapTileNum[][];

    public TileManager(GamePanel gp){

        this.gp = gp;
        tile = new Tile[10]; //create 10 kinds of tiles, such as water tile,wall tile

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];


        getTileImage();
        
        loadMap("../res/map/world01.txt");
    }

    // grab image from the pack
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/ground1.png"));

            //For better rendering performance if need
            /*BufferedImage scaledImage = new BufferedImage(gp.tileSize, gp.tileSize, tile[0].image.getType());
            Graphics2D g2 = scaledImage.createGraphics();
            g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
            tile[0].image = scaledImage;*/

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/wall1.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/haystack/haystack1.png"));
            tile[2].collision = true; //add this line to make solid

            /*tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("../res/player/idle_0.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("../res/player/idle_0.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("../res/player/idle_0.png"));*/

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //load the map from the text you created
    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){

            String line = br.readLine();

            while(col < gp.maxWorldCol){
                String numbers[] = line.split(" ");

                int num = Integer.parseInt(numbers[col]);

                mapTileNum[col][row] = num;
                col++;
            }

            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
        br.close();

        }catch(Exception e){
        }
    }


    
    public void draw(Graphics2D g2){
        //g2.drawImage(tile[0].image,0,0, gp.tileSize,gp.tileSze,null); // 0,0 is the coordinate, means x,y
        //g2.drawImage(tile[1].image,0,0, gp.tileSize,gp.tileSze,null);
        //g2.drawImage(tile[2].image,0,0, gp.tileSize,gp.tileSze,null);

        int worldCol = 0;
        int worldRow = 0;
       

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
            }
            
            worldCol++;
           
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
                
            }
        }
            
        //saving the map data to a text file

    }

}