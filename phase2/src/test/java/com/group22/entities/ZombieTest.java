package com.group22.entities;

import com.group22.GamePanel;
import com.group22.KeyHandler;

import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    public void testSpriteAnimationUpdate() {
        // Call update method 50 times
        for (int i = 0; i < 50; i++) {
            zombie.update();
        }
        // Assert that spriteNum cycles through 1 to 4
        assertTrue("Sprite number should be between 1 and 4", zombie.spriteNum >= 1 && zombie.spriteNum <= 4);
    }

    public void testZombieRemovalAfterCollisionWithVaccinatedPlayer() {
        // Set up a scenario where the player has a vaccine
        mockPlayer.hasVaccine = 1;
    
        // Position the zombie to collide with the player
        zombie.worldX = mockPlayer.worldX;
        zombie.worldY = mockPlayer.worldY;
    
        // Mock collision checker for player collision
        when(mockGamePanel.cChecker.checkPlayer(zombie, zombie.worldX, zombie.worldY)).thenReturn(true);
    
        // Call update
        zombie.update();
    
        // Assert that the zombie is marked for removal
        assertTrue("Zombie should be marked for removal after colliding with a vaccinated player", zombie.isRemoveThis());
    }
    



}