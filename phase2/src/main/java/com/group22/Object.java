package com.group22;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Object {

    protected Vector2 position; // Represents the position of the object on the screen.
    protected Sprite sprite;    // Represents the visual representation of the object.

    public Object(float x, float y) {
        this.position = new Vector2(x, y);
        // For now, we'll leave the sprite uninitialized
    }

    // Abstract methods that will be implemented in derived classes.
    public abstract void setSprite(Sprite sprite); // To set the sprite for the object.
    public abstract Sprite getSprite();            // To get the sprite of the object.

    // Getter and Setter for position.
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }
}

