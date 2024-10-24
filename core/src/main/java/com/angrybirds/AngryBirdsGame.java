package com.angrybirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AngryBirdsGame extends Game {
    public SpriteBatch batch;
    public Texture startScreenTexture;
    public Texture homeScreenTexture;
    public Texture levelScreenTexture;
    public Texture gameScreenTexture;
    public Texture redbirdTexture;
    public Texture bluebirdTexture;
    public Texture yellowbirdTexture;
    public Texture woodblockTexture;
    public Texture pigTexture;
    public Texture pausescreenTexture;
    public Texture savescreenTexture;
    public Texture winscreenTexture;
    public Texture losescreenTexture;
    public Texture loadinggameTexture;

    @Override
    public void create() {
        batch = new SpriteBatch();
        startScreenTexture = new Texture("start_screen.jpeg");
        homeScreenTexture = new Texture("home_screen.png");
        levelScreenTexture= new Texture("level_screen.jpeg");
        gameScreenTexture=new Texture("game_screen.png");
        redbirdTexture= new Texture("red_bird.png");
        bluebirdTexture=new Texture("blue_bird.png");
        yellowbirdTexture=new Texture("yellow_bird.png");
        woodblockTexture=new Texture("wood.jpeg");
        pigTexture=new Texture("pig.png");
        pausescreenTexture=new Texture("pause_screen.jpg");
        savescreenTexture=new Texture("game_saved.jpeg");
        winscreenTexture =new Texture("win.jpeg");
        losescreenTexture=new Texture("lose.jpg");
        loadinggameTexture=new Texture("loadinggame.jpeg");
        this.setScreen(new StartScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        startScreenTexture.dispose();
        homeScreenTexture.dispose();
        gameScreenTexture.dispose();
        redbirdTexture.dispose();
        bluebirdTexture.dispose();
        yellowbirdTexture.dispose();
        woodblockTexture.dispose();
        pigTexture.dispose();
        pausescreenTexture.dispose();
        savescreenTexture.dispose();
        winscreenTexture.dispose();
        losescreenTexture.dispose();
        loadinggameTexture.dispose();
    }

}


