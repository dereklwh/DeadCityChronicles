package com.group22.entities;

import com.group22.GamePanel;
import com.group22.KeyHandler;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import entities.Player;
import entities.Zombie;
import junit.framework.TestCase;

/**
 * Unit test for the Zombie class.
 */
public class ZombieTest extends TestCase {
    
    private Zombie zombie;
    private GamePanel mockGamePanel;
    private Player mockPlayer;
    @Mock
    private KeyHandler mockKeyHandler;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this); // Initialize Mockito mocks
        mockGamePanel = new GamePanel(); 
        mockPlayer = new Player(mockGamePanel, mockKeyHandler);
        zombie = new Zombie(mockGamePanel, 1);
    }

    public void testZombieConstructor() {
        Zombie zombie = new Zombie(mockGamePanel, 1); // Assuming 1 is a valid zombie type
        assertNotNull("Zombie object should not be null", zombie);
    }

    public void testUpdateCalculatesCorrectDirection() {
        // Manually set the actionLockCounter to 29
        zombie.actionLockCounter = 29;
    
        // Set up player and zombie positions
        mockPlayer.worldX = 100;
        mockPlayer.worldY = 50;
        zombie.worldX = 50;
        zombie.worldY = 50;
    
        // Call update
        zombie.update();
    
        // Check if the direction is calculated correctly
        String expectedDirection = "right";
        assertEquals(expectedDirection, zombie.direction);
    }
}