package com.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class WoodBlock extends BuildingBlock {
    public WoodBlock(Texture texturePath, float x, float y, float scale) {
        super(String.valueOf(texturePath),x,y,scale);
    }
}

