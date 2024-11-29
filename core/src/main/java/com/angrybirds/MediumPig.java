package com.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class MediumPig extends Pig{
    public MediumPig(Texture texturePath, float x, float y) {
        super(texturePath, x, y, 0.15f,PigType.MEDIUM);
    }
}
