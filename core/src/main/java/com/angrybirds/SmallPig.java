package com.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class SmallPig extends Pig{
    public SmallPig(Texture texturePath, float x, float y) {
        super(texturePath, x, y, 0.1f,PigType.SMALL);
    }
}
