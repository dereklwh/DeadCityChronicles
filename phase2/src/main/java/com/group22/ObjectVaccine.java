package com.group22;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectVaccine extends SuperObject{
    public ObjectVaccine(){
        name = "Vaccine";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("Address for vaccine"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
