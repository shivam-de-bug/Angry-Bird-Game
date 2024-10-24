
package com.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class HomeScreen implements Screen {

    private final AngryBirdsGame game;
    private Rectangle startButtonBounds;
    private Rectangle exitButtonBounds;

    public HomeScreen(final AngryBirdsGame game) {
        this.game = game;
        startButtonBounds = new Rectangle(500, 280, 280, 70);
        exitButtonBounds = new Rectangle(530, 190, 220, 60);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0.5f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();
        game.batch.draw(game.homeScreenTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        game.batch.end();


        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

            if (startButtonBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                game.setScreen(new LevelScreen(game));
                dispose();
            }
            if (exitButtonBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                Gdx.app.exit();
            }
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
