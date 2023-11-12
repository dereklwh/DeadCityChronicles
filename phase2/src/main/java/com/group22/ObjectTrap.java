package com.group22;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectTrap extends SuperObject{
    GamePanel gp;
    public ObjectTrap(GamePanel gp){
        name = "Trap";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("Address for Trap"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
