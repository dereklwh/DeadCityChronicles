package com.group22;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectVaccine extends SuperObject{
    GamePanel gp;
    public ObjectVaccine(GamePanel gp){
        name = "Vaccine";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("res/object/vaccine.png")); //just a placeholder
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
