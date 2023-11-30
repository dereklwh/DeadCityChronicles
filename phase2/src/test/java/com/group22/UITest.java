package com.group22;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UITest {

    @Mock
    private GamePanel mockGamePanel;
    private UI uiTest;
    private Graphics2D mockGraphics;

    @BeforeEach
    public void setUp() {
        mockGamePanel = Mockito.mock(GamePanel.class);
        mockGamePanel.tileSize = 32;
        uiTest = new UI(mockGamePanel);
    }

    @Test
    public void testShowMessage() {
        assertFalse(uiTest.messageOn);

        uiTest.showMessage("Test Message");

        assertTrue(uiTest.messageOn);
        assertEquals("Test Message", uiTest.message);
    }

    @Test
    public void testDrawTitleScreen() {
        mockGraphics = Mockito.mock(Graphics2D.class);
        uiTest.drawTitleScreen();
        Mockito.verify(mockGraphics).drawImage(Mockito.any(BufferedImage.class), Mockito.anyInt(), Mockito.anyInt(), Mockito.any());

    }

    /*@Test
    public void testDrawPlayState() {
        // Set the game state to playState
        when(mockGamePanel.getGameState()).thenReturn(GamePanel.playState);

        // Your code to call the draw method and assert the expected behavior for the play state
        uiTest.draw(mockGraphics);

        // Add assertions based on the expected behavior for the play state
    }*/

}
