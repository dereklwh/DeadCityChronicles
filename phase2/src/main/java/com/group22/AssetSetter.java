package com.group22;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){ //here we are making 2 vaccine we should chnage that later and add other objects too
        gp.obj[0] = new ObjectVaccine();
        gp.obj[0].worldX = 23 * gp.tileSize; //change this to anywhere we want to put the object
        gp.obj[0].worldY = 7 * gp.tileSize; //change this as well

        gp.obj[1] = new ObjectVaccine();
        gp.obj[1].worldX = 23 * gp.tileSize; //change this to anywhere we want to put the object
        gp.obj[1].worldY = 7 * gp.tileSize; //change this as well
    }
}
