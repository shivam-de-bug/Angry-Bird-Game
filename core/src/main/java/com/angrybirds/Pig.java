
package com.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Pig {
    private Texture pigTexture;
    private Vector2 position;
    private float scale;

    public Pig(Texture texturePath, float x, float y, float scale) {
        pigTexture = new Texture(String.valueOf(texturePath));
        position = new Vector2(x, y);
        this.scale = scale;
    }

    public Texture getPigTexture() {
        return pigTexture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getScale() {
        return scale;
    }

    public void dispose() {
        pigTexture.dispose();
    }
}
