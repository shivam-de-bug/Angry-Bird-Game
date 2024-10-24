
package com.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bird {
    private Texture birdTexture;
    private Vector2 position;
    private float scale;

    public Bird(Texture texturePath, float x, float y, float scale) {
        birdTexture = new Texture(String.valueOf(texturePath));
        position = new Vector2(x, y);
        this.scale = scale;
    }

    public Texture getBirdTexture() {
        return birdTexture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getScale() {
        return scale;
    }

    public void dispose() {
        birdTexture.dispose();
    }
}
