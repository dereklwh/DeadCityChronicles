package com.group22;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectVaccine extends SuperObject{
    public ObjectVaccine(){
        name = "Vaccine";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("res/object/vaccine.png")); //just a placeholder
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
