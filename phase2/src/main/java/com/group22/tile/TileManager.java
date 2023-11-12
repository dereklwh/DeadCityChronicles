package com.group22.tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.group22.GamePanel;
import com.group22.UtilityTool;

// under package Tile;

public class TileManager{

    GamePanel gp;
    public Tile[] tile;
    //need to create the map package and import data text file into pack.
    public int mapTileNum[][];

    public TileManager(GamePanel gp){

        this.gp = gp;
        tile = new Tile[200]; //create 10 kinds of tiles, such as water tile,wall tile

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];


        getTileImage();
        
        loadMap("../res/map/world02.txt");
    }

    // grab image from the pack
    public void getTileImage(){
        //tile[0] = new Tile();
        //tile[0].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/ground1.png"));
        setup(0, "ground1", false);

            /*tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/wall1.png"));
            tile[1].collision = true;*/
        setup(1, "wall1", true);


            /*tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/bush.png"));
            tile[2].collision = true; //add this line to make solid*/
        setup(2, "bush", true);


            /*tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/road/road_cross.png"));**/
        setup(3, "road_cross", false);

            /*tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/road/road_horizontal.png"));**/
        setup(4, "road_horizontal", false);

            /*tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/road/road_ld_corner.png"));**/
        setup(5, "road_ld_corner", false);

           /* tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/road/road_lu_corner.png"));**/
        setup(6, "road_lu_corner", false);

            /*tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/road/road_rd_corner.png"));**/
        setup(7, "road_rd_corner", false);

            /*tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/road/road_ru_corner.png"));**/
        setup(8, "ru_corner", false);

            /*tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/road/road_T_down.png"));**/
        setup(9, "road_T_down", false);

            /*tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/road/road_T_left.png"));**/
        setup(10, "road_T_left", false);

            /*tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/road/road_T_right.png"));**/
        setup(11, "road_T_right", false);

            /*tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/road/road_T_up.png"));**/
        setup(12, "road_T_up", false);

            /*tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/road/road_vertical.png"));**/
        setup(13, "road_vertical", false);

            /*tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/fence/road_fence_down.png"));
            tile[14].collision = true;**/
        setup(14, "fence_down", true);

            /*tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/fence/fence_ld_corner.png"));
            tile[15].collision = true;**/
        setup(15, "fence_ld_corner", true);

            /*tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/fence/fence_left.png"));
            tile[16].collision = true;**/
        setup(16, "fence_left", true);

            /*tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/fence/fence_lu_corner.png"));
            tile[17].collision = true;**/
        setup(17, "fence_lu_corner", true);

            /*tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/fence/fence_rd_corner.png"));
            tile[18].collision = true;**/
        setup(18, "fence_rd_corner", true);


            /*tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/fence/fence_right.png"));
            tile[19].collision = true;**/
        setup(19, "fence_right", true);

            /*tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/fence/fence_ru_corner.png"));
            tile[20].collision = true;**/
        setup(20, "fence_ru_corner", true);

            /*tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/fence/fence_up.png"));
            tile[21].collision = true;**/
        setup(21, "fence_up", true);


            /*tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/haystack/haystack1.png"));
            tile[22].collision = true;*/
        setup(22, "haystack1", true);

            /*tile[23] = new Tile();
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/haystack/haystack2.png"));
            tile[23].collision = true;*/
        setup(23, "haystack2", true);

            /*tile[24] = new Tile();
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/haystack/haystack3.png"));
            tile[24].collision = true;*/
        setup(24, "haystack3", true);

            /*tile[25] = new Tile();
            tile[25].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/haystack/haystack4.png"));
            tile[25].collision = true;*/
        setup(25, "haystack4", true);

            /*tile[26] = new Tile();
            tile[26].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/haystack/haystack5.png"));
            tile[26].collision = true;*/
        setup(26, "haystack5", true);

            /*tile[27] = new Tile();
            tile[27].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/haystack/haystack6.png"));
            tile[27].collision = true;*/
        setup(27, "haystack6", true);

            /*tile[28] = new Tile();
            tile[28].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_1.png"));
            tile[28].collision = true;*/
        setup(28, "house_1", true);

           /* tile[29] = new Tile();
            tile[29].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_2.png"));
            tile[29].collision = true;*/
        setup(29, "house_2", true);

            /*tile[30] = new Tile();
            tile[30].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_3.png"));
            tile[30].collision = true;*/
        setup(30, "house_3", true);

            /*tile[31] = new Tile();
            tile[31].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_4.png"));
            tile[31].collision = true;*/
        setup(31, "house_4", true);

            /*tile[32] = new Tile();
            tile[32].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_5.png"));
            tile[32].collision = true;*/
        setup(32, "house_5", true);

            /*tile[33] = new Tile();
            tile[33].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_6.png"));
            tile[33].collision = true;*/
        setup(33, "house_6", true);

            /*tile[34] = new Tile();
            tile[34].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_7.png"));
            tile[34].collision = true;*/
        setup(34, "house_7", true);

            /*tile[35] = new Tile();
            tile[35].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_8.png"));
            tile[35].collision = true;*/
        setup(35, "house_8", true);

          /*  tile[36] = new Tile();
            tile[36].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_9.png"));
            tile[36].collision = true;*/
        setup(36, "house_9", true);

            /*tile[37] = new Tile();
            tile[37].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_10.png"));
            tile[37].collision = true;*/
        setup(37, "house_10", true);

            /*tile[38] = new Tile();
            tile[38].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_11.png"));
            tile[38].collision = true;*/
        setup(38, "house_11", true);

            /*tile[39] = new Tile();
            tile[39].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_12.png"));
            tile[39].collision = true;*/
        setup(39, "house_12", true);

            /*tile[40] = new Tile();
            tile[40].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_13.png"));
            tile[40].collision = true;*/
        setup(40, "house_13", true);

            /*tile[41] = new Tile();
            tile[41].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_14.png"));
            tile[41].collision = true;*/
        setup(41, "house_14", true);

            /*tile[42] = new Tile();
            tile[42].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_15.png"));
            tile[42].collision = true;*/
        setup(42, "house_15", true);

            /*tile[43] = new Tile();
            tile[43].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_16.png"));
            tile[43].collision = true;*/
        setup(43, "house_16", true);

            /*tile[44] = new Tile();
            tile[44].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_17.png"));
            tile[44].collision = true;*/
        setup(44, "house_17", true);

            /*tile[45] = new Tile();
            tile[45].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_18.png"));
            tile[45].collision = true;*/
        setup(45, "house_18", true);

            /*tile[46] = new Tile();
            tile[46].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_19.png"));
            tile[46].collision = true;*/
        setup(46, "house_19", true);

            /*tile[47] = new Tile();
            tile[47].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_20.png"));
            tile[47].collision = true;*/
        setup(47, "house_20", true);

            /*tile[48] = new Tile();
            tile[48].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_21.png"));
            tile[48].collision = true;*/
        setup(48, "house_21", true);

            /*tile[49] = new Tile();
            tile[49].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_22.png"));
            tile[49].collision = true;*/
        setup(49, "house_22", true);

            /*tile[50] = new Tile();
            tile[50].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_23.png"));
            tile[50].collision = true;*/
        setup(50, "house_23", true);

            /*tile[51] = new Tile();
            tile[51].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_24.png"));
            tile[51].collision = true;*/
        setup(51, "house_24", true);

            /*tile[52] = new Tile();
            tile[52].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_25.png"));
            tile[52].collision = true;*/
        setup(52, "house_25", true);

            /*tile[53] = new Tile();
            tile[53].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_26.png"));
            tile[53].collision = true;*/
        setup(53, "house_26", true);

            /*tile[54] = new Tile();
            tile[54].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_27.png"));
            tile[54].collision = true;*/
        setup(54, "house_27", true);

            /*tile[55] = new Tile();
            tile[55].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_28.png"));
            tile[55].collision = true;*/
        setup(55, "house_28", true);

            /*tile[56] = new Tile();
            tile[56].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house_29.png"));
            tile[56].collision = true;*/
        setup(56, "house_29", true);

            /*tile[57] = new Tile();
            tile[57].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_1.png"));
            tile[57].collision = true;*/
        setup(57, "house2_1", true);

            /*tile[58] = new Tile();
            tile[58].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_2.png"));
            tile[58].collision = true;*/
        setup(58, "house2_2", true);

            /*tile[59] = new Tile();
            tile[59].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_3.png"));
            tile[59].collision = true;*/
        setup(59, "house2_3", true);

            /*tile[60] = new Tile();
            tile[60].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_4.png"));
            tile[60].collision = true;*/
        setup(60, "house2_4", true);

            /*tile[61] = new Tile();
            tile[61].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_5.png"));
            tile[61].collision = true;*/
        setup(61, "house2_5", true);

            /*tile[62] = new Tile();
            tile[62].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_6.png"));
            tile[62].collision = true;*/
        setup(62, "house2_6", true);

            /*tile[63] = new Tile();
            tile[63].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_7.png"));
            tile[63].collision = true;*/
        setup(63, "house2_7", true);

            /*tile[64] = new Tile();
            tile[64].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_8.png"));
            tile[64].collision = true;*/
        setup(64, "house2_8", true);

            /*tile[65] = new Tile();
            tile[65].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_9.png"));
            tile[65].collision = true;*/
        setup(65, "house2_9", true);

            /*tile[66] = new Tile();
            tile[66].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_10.png"));
            tile[66].collision = true;*/
        setup(66, "house2_10", true);

            /*tile[67] = new Tile();
            tile[67].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_11.png"));
            tile[67].collision = true;*/
        setup(67, "house2_11", true);

            /*tile[68] = new Tile();
            tile[68].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_12.png"));
            tile[68].collision = true;*/
        setup(68, "house2_12", true);

            /*tile[69] = new Tile();
            tile[69].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_13.png"));
            tile[69].collision = true;*/
        setup(69, "house2_13", true);

            /*tile[70] = new Tile();
            tile[70].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_14.png"));
            tile[70].collision = true;*/
        setup(70, "house2_14", true);

            /*tile[71] = new Tile();
            tile[71].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_15.png"));
            tile[71].collision = true;*/
        setup(71, "house2_15", true);

            /*tile[72] = new Tile();
            tile[72].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_16.png"));
            tile[72].collision = true;*/
        setup(72, "house2_16", true);

            /*tile[73] = new Tile();
            tile[73].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_17.png"));
            tile[73].collision = true;*/
        setup(73, "house2_17", true);

            /*tile[74] = new Tile();
            tile[74].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_18.png"));
            tile[74].collision = true;*/
        setup(74, "house2_18", true);

            /*tile[75] = new Tile();
            tile[75].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_19.png"));
            tile[75].collision = true;*/
        setup(75, "house2_19", true);

            /*tile[76] = new Tile();
            tile[76].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_20.png"));
            tile[76].collision = true;*/
        setup(76, "house2_20", true);

            /*tile[77] = new Tile();
            tile[77].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_21.png"));
            tile[77].collision = true;*/
        setup(77, "house2_21", true);

            /*tile[78] = new Tile();
            tile[78].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_22.png"));
            tile[78].collision = true;*/
        setup(78, "house2_22", true);

            /*tile[79] = new Tile();
            tile[79].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_23.png"));
            tile[79].collision = true;*/
        setup(79, "house2_23", true);

            /*tile[80] = new Tile();
            tile[80].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_24.png"));
            tile[80].collision = true;*/
        setup(80, "house2_24", true);

            /*tile[81] = new Tile();
            tile[81].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_25.png"));
            tile[81].collision = true;*/
        setup(81, "house2_25", true);

            /*tile[82] = new Tile();
            tile[82].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_26.png"));
            tile[82].collision = true;*/
        setup(82, "house2_26", true);

            /*tile[83] = new Tile();
            tile[83].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_27.png"));
            tile[83].collision = true;*/
        setup(83, "house2_27", true);

            /*tile[84] = new Tile();
            tile[84].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_28.png"));
            tile[84].collision = true;*/
        setup(84, "house2_28", true);

            /*tile[85] = new Tile();
            tile[85].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/house2_29.png"));
            tile[85].collision = true;*/
        setup(85, "house2_29", true);

            /*tile[86] = new Tile();
            tile[86].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/car/car_1.png"));
            tile[86].collision = true;*/
        setup(86, "car_1", true);

            /*tile[87] = new Tile();
            tile[87].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/car/car_2.png"));
            tile[87].collision = true;*/
        setup(87, "car_2", true);

            /*tile[88] = new Tile();
            tile[88].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/car/car2_1.png"));
            tile[88].collision = true;*/
        setup(88, "car2_1", true);

            /*tile[89] = new Tile();
            tile[89].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/car/car2_2.png"));
            tile[89].collision = true;*/
        setup(89, "car2_2", true);

    }

    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try{
           tile[index]  = new Tile();
           if(imageName.contains("house")) {
               tile[index].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/house/" + imageName + ".png"));
           } else if (imageName.contains("car")) {
               tile[index].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/car/" + imageName + ".png"));
           } else if(imageName.contains("haystack")) {
               tile[index].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/haystack/" + imageName + ".png"));
           } else if(imageName.contains("road")) {
               tile[index].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/road/" + imageName + ".png"));
           } else if(imageName.contains("fence")){
               tile[index].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/fence/" + imageName + ".png"));
           } else{
               tile[index].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/" + imageName + ".png"));
           }
           tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
           tile[index].collision = collision;
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

                //g2.drawImage(tile[tileNum].image,screenX,screenY/*gp.tileSize,gp.tileSize*/,null);
                g2.drawImage(tile[tileNum].image,screenX,screenY ,null);

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