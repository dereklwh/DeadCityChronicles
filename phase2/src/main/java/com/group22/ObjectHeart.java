package com.group22;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectHeart extends SuperObject {
    GamePanel gp;
    public ObjectHeart(GamePanel gp){
        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("res/object/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("res/object/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("res/object/heart_blank.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
