package com.angrybirds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class Loadinggame implements Screen {

    private final AngryBirdsGame game;
    private float t = 0;
    private int level;
    private static final String SAVE_FILE_PREFIX = "level_";
    private static final String SAVE_FILE_SUFFIX = "_save.json";

    public Loadinggame(final AngryBirdsGame game,int level) {
        this.game = game;
        this.level=level;
       AngryBirdsGame.music.play();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float del) {


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(game.loadinggameTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();

        t =t+ del;
        if (t > 2) {
            AngryBirdsGame.music.pause();;

//            game.setScreen(new GameScreen1(game,"NOTHING TO DO"));
            loadLevel();
        }
    }
    private void loadLevel() {
        String filename = SAVE_FILE_PREFIX + level + SAVE_FILE_SUFFIX;
        Screen levelScreen = GameStateManager.getInstance().loadGameState(game, level);
        game.setScreen(levelScreen);
    }
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
