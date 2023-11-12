package com.group22;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public  boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e){

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.playState){
            playState(code);
        }

        else if (gp.gameState == gp.pauseState){
            pauseState(code);
        }

        else if (gp.gameState == gp.settingState){
            settingState(code);
        }
    }

    public void playState(int code){
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = true;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.settingState;
        }
    }

    public void settingState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
    }

    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = false;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }

    }
}
