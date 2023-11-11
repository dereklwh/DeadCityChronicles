package com.group22;

import java.awt.Graphics2D;
import java.awt.Font;

public class UI {
    
    GamePanel gp;

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {

        g2.setFont(new Font("Arial", Font.PLAIN, 40)); //Find new font later
    }
}
