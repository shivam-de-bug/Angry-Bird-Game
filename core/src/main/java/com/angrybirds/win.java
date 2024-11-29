package com.angrybirds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class win implements Screen {

    private final AngryBirdsGame game;
    private float t = 0;

    public win(AngryBirdsGame game) {
        this.game = game;
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float del) {
        AngryBirdsGame.win.play();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(game.winscreenTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        game.batch.end();

        t =t+ del;
        if (t > 2) {
            AngryBirdsGame.win.pause();
            game.setScreen(new LevelScreen(game));
        }
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
