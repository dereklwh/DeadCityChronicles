package com.group22;

import javax.swing.JPanel;

public class GamePanel extends JPanel{

    private final int originalTileSize = 16;
    public int scale = 3;
    public int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 40;
    public final int maxScreenRow = 22;

    public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * maxScreenRow;

    
}
