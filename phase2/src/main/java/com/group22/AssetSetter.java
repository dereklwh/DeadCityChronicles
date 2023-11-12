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
        gp.obj[1].worldY = 33 * gp.tileSize; //change this as well

         gp.obj[2] = new ObjectVaccine(gp);
        gp.obj[2].worldX = 12 * gp.tileSize; 
        gp.obj[2].worldY = 25 * gp.tileSize;
    }

    public void setZombie() {
        gp.zombie[0] = new Zombie(gp, 1);
        gp.zombie[0].worldX = gp.tileSize * 23;
        gp.zombie[0].worldY = gp.tileSize * 25;
        gp.zombie[0].speed = 1;

        gp.zombie[1] = new Zombie(gp, 2);
        gp.zombie[1].worldX = gp.tileSize * 20;
        gp.zombie[1].worldY = gp.tileSize * 20;
        gp.zombie[1].speed = 1;

        gp.zombie[2] = new Zombie(gp, 3);
        gp.zombie[2].worldX = gp.tileSize * 20;
        gp.zombie[2].worldY = gp.tileSize * 21;
        gp.zombie[2].speed = 5;

        gp.zombie[3] = new Zombie(gp, 2);
        gp.zombie[3].worldX = gp.tileSize * 20;
        gp.zombie[3].worldY = gp.tileSize * 17;
        gp.zombie[3].speed = 1;

        gp.zombie[4] = new Zombie(gp, 3);
        gp.zombie[4].worldX = gp.tileSize * 20;
        gp.zombie[4].worldY = gp.tileSize * 18;
        gp.zombie[4].speed = 3;

    }
}
