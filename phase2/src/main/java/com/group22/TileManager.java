package tile;

// under package Tile;

public class TileManager{

    GamePanel gp;
    Tile[] tile;
    //need to create the map package and import data text file into pack.
    int mapTileNum[][];

    public TileManager(GamePanel gp){

        this.gp = gp;
        tile = new Tile[10]; //create 10 kinds of tiles, such as water tile,wall tile

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];


        getTileImage();
        //loadMap("/map/map01.txt");
        loadMap("/map/world01.txt");
    }

    // grab image from the pack
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bush.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/xxx.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/yyy.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/zzz.png"));*/

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //load the map from the text you created
    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BudderedReader br = new BufferedReader(new InputStreamReader(is));

        int col = 0;
        int row = 0;

        while(col<gp.maxWorldCol && row < gp.maxWorldRow){

            String line = br.readLIne();

            while(col < gp.maxScreenCol){
                String numbers[] = line.split(" ");

                int num = Integer.parseInt(numbers[col]);

                mapTileNum[col][row] = num;
                col++;
            }

            if(col == gp.maxScreenCol){
                col = 0;
                row++;
            }
        }
        br.close();

            




        }catch(Exception e){
        }
    }


    //goto gamePanel class type "TileManager tileM = new TileManager(this)", then under paintComponent before line player.draw type "tileM.draw(g2)"
    public void draw(Graphics2D g2){
        //g2.drawImage(tile[0].image,0,0, gp.tileSize,gp.tileSze,null); // 0,0 is the cordinate, means x,y
        //g2.drawImage(tile[1].image,0,0, gp.tileSize,gp.tileSze,null);
        //g2.drawImage(tile[2].image,0,0, gp.tileSize,gp.tileSze,null);

        int worldCol = 0;
        int worldRow = 0;
        //int x = 0;
        //int y =0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX > gp.player.worldX - gp.player.screenX &&
                worldX < gp.player.worldX + gp.player.screenX &&
                worldY > gp.player.worldY - gp.player.screenY &&
                worldY < gp.player.worldY + gp.player.screenY){
                    
                    g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
            }
            
            worldCol++;
           

            if(worldCol == gp.maxScreenCol){
                worldCol = 0;
           
                worldRow++;
                
            }
        }

        //saving the map data to a text file

    }
}