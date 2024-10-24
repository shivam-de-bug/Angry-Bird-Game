
package com.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class BuildingBlock {
    private Texture blockTexture;
    private Vector2 position;
    private float scale;

    public BuildingBlock(String texturePath, float x, float y, float scale) {
        blockTexture = new Texture(texturePath);
        position = new Vector2(x, y);
        this.scale = scale;
    }

    public Texture getBlockTexture() {
        return blockTexture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getScale() {
        return scale;
    }

    public void dispose() {
        blockTexture.dispose();
    }
}

