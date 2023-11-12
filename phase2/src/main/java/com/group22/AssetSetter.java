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

        gp.obj[3] = new ObjectTrap(gp);
        gp.obj[3].worldX = 13 * gp.tileSize;
        gp.obj[3].worldY = 25 * gp.tileSize;

        gp.obj[4] = new ObjectTrap(gp);
        gp.obj[4].worldX = 11 * gp.tileSize;
        gp.obj[4].worldY = 60 * gp.tileSize;

        gp.obj[5] = new ObjectTrap(gp);
        gp.obj[5].worldX = 13 * gp.tileSize;
        gp.obj[5].worldY = 51 * gp.tileSize;

        gp.obj[6] = new ObjectTrap(gp);
        gp.obj[6].worldX = 27 * gp.tileSize;
        gp.obj[6].worldY = 62 * gp.tileSize;

        gp.obj[7] = new ObjectTrap(gp);
        gp.obj[7].worldX = 34 * gp.tileSize;
        gp.obj[7].worldY = 40 * gp.tileSize;

        gp.obj[8] = new ObjectTrap(gp);
        gp.obj[8].worldX = 35 * gp.tileSize;
        gp.obj[8].worldY = 21 * gp.tileSize;

        gp.obj[9] = new ObjectTrap(gp);
        gp.obj[9].worldX = 34 * gp.tileSize;
        gp.obj[9].worldY = 51 * gp.tileSize;

        gp.obj[10] = new ObjectTrap(gp);
        gp.obj[10].worldX = 14 * gp.tileSize;
        gp.obj[10].worldY = 41 * gp.tileSize;

        gp.obj[11] = new ObjectTrap(gp);
        gp.obj[11].worldX = 14 * gp.tileSize;
        gp.obj[11].worldY = 21 * gp.tileSize;

        gp.obj[12] = new ObjectTrap(gp);
        gp.obj[12].worldX = 28 * gp.tileSize;
        gp.obj[12].worldY = 22 * gp.tileSize;

        gp.obj[13] = new ObjectDoor(gp);
        gp.obj[13].worldX = 18 * gp.tileSize;
        gp.obj[13].worldY = 11 * gp.tileSize;

        gp.obj[14] = new ObjectKey(gp);
        gp.obj[14].worldX = 60 * gp.tileSize;
        gp.obj[14].worldY = 10 * gp.tileSize;

        gp.obj[15] = new ObjectKey(gp);
        gp.obj[15].worldX = 49 * gp.tileSize;
        gp.obj[15].worldY = 30 * gp.tileSize;

        gp.obj[16] = new ObjectKey(gp);
        gp.obj[16].worldX = 10 * gp.tileSize;
        gp.obj[16].worldY = 28 * gp.tileSize;


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
