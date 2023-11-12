package com.group22;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectKey extends SuperObject {
    GamePanel gp;
    public ObjectKey(GamePanel gp){
        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("res/object/key.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
