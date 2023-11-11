package com.group22;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectHeart extends SuperObject {
    public ObjectHeart(){
        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("res/object/heart.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
