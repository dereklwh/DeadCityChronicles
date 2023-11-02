package com.group22;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GamePanel extends JPanel{

    private final int originalTileSize = 16;
    public int scale = 3;
    public int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 40;
    public final int maxScreenRow = 22;
    public int screenWidth = tileSize * maxScreenCol; //1920
    public int screenHeight = tileSize * maxScreenRow; //1056

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        
    }
}
