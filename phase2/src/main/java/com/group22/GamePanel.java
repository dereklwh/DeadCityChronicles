package com.group22;

import javax.swing.JPanel;

public class GamePanel extends JPanel{

    private final int originalTileSize = 16;
    public int scale = 3;
    public int tileSize = originalTileSize * scale;
}
