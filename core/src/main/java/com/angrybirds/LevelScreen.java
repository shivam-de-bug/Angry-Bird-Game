
package com.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class LevelScreen implements Screen {

    private final AngryBirdsGame game;
    private Rectangle backButtonBounds;
    private Rectangle level1ButtonBounds;
    private Rectangle level2ButtonBounds;
    private Rectangle level3ButtonBounds;

    public LevelScreen(final AngryBirdsGame game) {
        this.game = game;


        backButtonBounds = new Rectangle(850, 170, 190, 65);

        level1ButtonBounds = new Rectangle(850, 420, 190, 65);
        level2ButtonBounds = new Rectangle(850, 330, 190, 65);
        level3ButtonBounds = new Rectangle(850, 250, 190, 65);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        AngryBirdsGame.music.play();
        Gdx.gl.glClearColor(0, 0.5f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(game.levelScreenTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //game.batch.draw(game.woodblockTexture,850, 420, 190, 65);
                game.batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

            if (backButtonBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                AngryBirdsGame.music.pause();;

                game.setScreen(new HomeScreen(game));
                dispose();
            }
            if (level1ButtonBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                AngryBirdsGame.music.pause();
                System.out.println("Level 1 selected");
                game.setScreen(new Loadinggame(game,1));
                dispose();
            } else if (level2ButtonBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                AngryBirdsGame.music.pause();
                game.setScreen(new Loadinggame(game,2));

                System.out.println("Level 2 selected");
            } else if (level3ButtonBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                AngryBirdsGame.music.pause();
                game.setScreen(new Loadinggame(game,3));

                System.out.println("Level 3 selected");
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
