package com.group22;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectMed extends SuperObject {
    GamePanel gp;
    public ObjectMed(GamePanel gp){
        name = "Med";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("res/object/med.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}