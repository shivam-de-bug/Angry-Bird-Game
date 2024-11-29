package com.angrybirds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class lose implements Screen {

    private final AngryBirdsGame game;
    private float t = 0;

    public lose(AngryBirdsGame game) {
        this.game = game;
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float del) {
        AngryBirdsGame.lose.play();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(game.losescreenTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        game.batch.end();

        t =t+ del;
        if (t > 3) {
            AngryBirdsGame.lose.pause();
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
