package com.angrybirds;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;

public abstract class BaseGameScreen implements Screen {
    public AngryBirdsGame game;
    public Array<Bird> birds;
    public Array<BuildingBlock> blocks;
    public Array<Pig> pigs;

    public Bird currentBird;
    public int remainingBirds;
    public int score;

    public BaseGameScreen(AngryBirdsGame game) {
        this.game = game;
        this.birds = new Array<>();
        this.blocks = new Array<>();
        this.pigs = new Array<>();
    }

    // Abstract methods to ensure consistent implementation
    public abstract void initializeLevel();
    public abstract void saveGameState();
    public abstract void loadGameState();
}
