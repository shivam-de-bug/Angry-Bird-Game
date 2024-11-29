package com.angrybirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
    public Texture dragtextture;
    public Texture woodvertexture;
    public Texture glasstexture;
    public Texture glassvertexture;
    public Texture stonetexture;
    public Texture stonevertexture;
   public static Music music;
    public static Music blockdam ;
    public static Music pigdam;
    public static  Music win;
    public static  Music lose;


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
        dragtextture=new Texture("drag.png");
        woodvertexture=new Texture("woodvertical.png");
        glasstexture=new Texture("glass.jpg");
        glassvertexture=new Texture("glassver.jpg");
        stonetexture=new Texture("steel.jpeg");
        stonevertexture=new Texture("steelver.jpeg");

        this.setScreen(new StartScreen(this));

        music= Gdx.audio.newMusic(Gdx.files.internal("angrybird.ogg"));

        music.setLooping(true);
        blockdam=Gdx.audio.newMusic(Gdx.files.internal("blockdam.mp3"));
            pigdam= Gdx.audio.newMusic(Gdx.files.internal("dam.mp3"));
            win=Gdx.audio.newMusic(Gdx.files.internal("win.mp3"));
            lose=Gdx.audio.newMusic(Gdx.files.internal("lose.mp3"));

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
        dragtextture.dispose();
        woodvertexture.dispose();
    }


}


