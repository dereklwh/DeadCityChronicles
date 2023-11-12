package com.group22;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){ //here we are making 2 vaccine we should chnage that later and add other objects too
        gp.obj[0] = new ObjectVaccine(gp);
        gp.obj[0].worldX = 23 * gp.tileSize; //change this to anywhere we want to put the object
        gp.obj[0].worldY = 7 * gp.tileSize; //change this as well

        gp.obj[1] = new ObjectVaccine(gp);
        gp.obj[1].worldX = 23 * gp.tileSize; //change this to anywhere we want to put the object
        gp.obj[1].worldY = 2 * gp.tileSize; //change this as well
    }

    public void setZombie() {
        gp.zombie[0] = new Zombie(gp);
        gp.zombie[0].worldX = gp.tileSize * 23;
        gp.zombie[0].worldY = gp.tileSize * 21;

        gp.zombie[1] = new Zombie(gp);
        gp.zombie[1].worldX = gp.tileSize * 25;
        gp.zombie[1].worldY = gp.tileSize * 26;
        gp.zombie[1].speed = 1;

    }
}
