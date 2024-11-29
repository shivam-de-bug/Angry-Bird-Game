package com.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class RedBird extends Bird {
    public RedBird(Texture texture, float x, float y, float scale) {
        super(texture, x, y, scale, 1.5f); // Red bird has standard power
    }

    @Override
    public void useSpecial() {
        // Red bird has no special ability
        super.useSpecial();
    }
}
