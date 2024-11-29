package com.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class YellowBird extends Bird {
    public YellowBird(Texture texture, float x, float y, float scale) {
        super(texture, x, y, scale, 1.2f);
    }

    @Override
    public void useSpecial() {
        if (!isSpecial) {
            velocity.x *= 2.5f;
            super.useSpecial();
        }
    }
}
