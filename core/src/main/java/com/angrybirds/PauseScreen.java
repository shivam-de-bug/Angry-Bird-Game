
package com.angrybirds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PauseScreen implements Screen {

    private final AngryBirdsGame game;
    private Rectangle resumebuttonbounds;
    private Rectangle homebuttonbounds;

    private final int currentLevel;


    public PauseScreen(final AngryBirdsGame game, int currentLevel) {
        this.game = game;
        this.currentLevel = currentLevel;

        resumebuttonbounds = new Rectangle(500, 100, 120,130);
        homebuttonbounds=new Rectangle(680, 100, 120,130);
       AngryBirdsGame.music.play();


    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0.5f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());

            String load="LOAD";
            if (resumebuttonbounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                Screen loadedScreen = GameStateManager.getInstance().loadGameState(game, currentLevel);
                AngryBirdsGame.music.pause();;

                game.setScreen(loadedScreen);
//                game.setScreen(new GameScreen1(game,load));
            }


            if (homebuttonbounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                AngryBirdsGame.music.pause();;

                game.setScreen(new SaveScreen(game));
            }
        }
        game.batch.begin();
        game.batch.draw(game.pausescreenTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //game.batch.draw(game.woodblockTexture, 680, 100, 120,130);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {}
}
