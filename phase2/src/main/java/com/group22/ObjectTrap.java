package com.group22;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectTrap extends SuperObject{
    public ObjectTrap(){
        name = "Trap";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("Address for Trap"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
