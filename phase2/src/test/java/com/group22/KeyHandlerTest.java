package com.group22;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class KeyHandlerTest {

    KeyHandler keyHandler;
    GamePanel mockGamePanel;
    GamePanel realGamePanel;

    @BeforeEach
    void setUp() {
        mockGamePanel = Mockito.mock(GamePanel.class);
        realGamePanel = new GamePanel(); // Create a real instance to get the playState value
        // Set the gameState of the mock GamePanel to the playState value from the real instance.
        mockGamePanel.gameState = realGamePanel.playState;
        keyHandler = new KeyHandler(mockGamePanel);
    }

    @Test
    void testKeyPressUp() {
        KeyEvent keyEvent = new KeyEvent(mockGamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'U');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.upPressed, "Up key should be pressed");
    }

    @Test
    void testKeyPressDown() {
        KeyEvent keyEvent = new KeyEvent(mockGamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'D');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.downPressed, "Down key should be pressed");
    }

    @Test
    void testKeyPressLeft() {
        KeyEvent keyEvent = new KeyEvent(mockGamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.leftPressed, "Left key should be pressed");
    }

    @Test
    void testKeyPressRight() {
        KeyEvent keyEvent = new KeyEvent(mockGamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'R');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.rightPressed, "Right key should be pressed");
    }

    @Test
    void testKeyReleaseDown() {
        KeyEvent keyEvent = new KeyEvent(mockGamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'D');
        keyHandler.keyReleased(keyEvent);
        assertFalse(keyHandler.downPressed, "Down key should be released");
    }

    @Test
    void testKeyReleaseRight() {
        KeyEvent keyEvent = new KeyEvent(mockGamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'R');
        keyHandler.keyReleased(keyEvent);
        assertFalse(keyHandler.rightPressed, "Right key should be released");
    }

    @Test
    void testKeyReleasedUp() {
        KeyEvent keyEvent = new KeyEvent(mockGamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'U');
        keyHandler.keyReleased(keyEvent);
        assertFalse(keyHandler.upPressed, "Up key should be released");
    }

    @Test
    void testKeyReleasedLeft() {
        KeyEvent keyEvent = new KeyEvent(mockGamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L');
        keyHandler.keyReleased(keyEvent);
        assertFalse(keyHandler.leftPressed, "Left key should be released");
    }

    @Test
    void testTogglePausePlay() {
        // Set initial game state to play
        mockGamePanel.gameState = realGamePanel.playState;

        // Simulate pressing the 'P' key to pause
        KeyEvent keyEventPause = new KeyEvent(mockGamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_P, 'P');
        keyHandler.keyPressed(keyEventPause);
        assertEquals(realGamePanel.pauseState, mockGamePanel.gameState, "Game state should be pauseState after pressing 'P'");

        // Simulate pressing the 'P' key again to resume play
        KeyEvent keyEventPlay = new KeyEvent(mockGamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_P, 'P');
        keyHandler.keyPressed(keyEventPlay);
        assertEquals(realGamePanel.playState, mockGamePanel.gameState, "Game state should be playState after pressing 'P' again");
    }

    @Test
    void testEscapeKeyPressInPlayState() {
        mockGamePanel.gameState = realGamePanel.playState;

        KeyEvent keyEvent = new KeyEvent(mockGamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, 'E');
        keyHandler.keyPressed(keyEvent);
        assertEquals(realGamePanel.settingState, mockGamePanel.gameState, "Game state should be settingState after pressing Escape in playState");
    }


    // Additional test cases can be added for other keys and game states
}
