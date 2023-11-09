package com.group22;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectKey extends SuperObject {
    public ObjectKey(){
        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("Address for key"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
