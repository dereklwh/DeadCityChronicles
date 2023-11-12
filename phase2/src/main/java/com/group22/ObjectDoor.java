package com.group22;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectDoor extends SuperObject {
    GamePanel gp;
    public ObjectDoor(GamePanel gp){
        name = "Door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("res/object/door.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
