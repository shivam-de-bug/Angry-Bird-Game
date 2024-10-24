
package com.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameScreen1 implements Screen {

    private final AngryBirdsGame game;
    private RedBird redbird;
    private BlueBird bluebird;
    private YellowBird yellowbird;

    private WoodBlock woodblock1;
    private WoodBlock woodblock2;
    private WoodBlock woodblock3;
    private WoodBlock woodblock4;
    private WoodBlock woodblock5;
    private WoodBlock woodblock6;
    private WoodBlock woodblock7;

    private LargePig pig1;
    private MediumPig pig2;
    private SmallPig pig3;



    private Rectangle pauseButtonBounds;
    private Rectangle backButtonBounds;
    private Rectangle restartbuttonBounds;
    private Rectangle winbuttonsbounds;
    private Rectangle losebuttonsbounds;
    public GameScreen1(final AngryBirdsGame game) {
        this.game = game;

        backButtonBounds = new Rectangle(15, 600, 100,100);
        pauseButtonBounds = new Rectangle(1175, 605, 100,100);
        restartbuttonBounds=new Rectangle(1175,480,100,100);
        winbuttonsbounds=new Rectangle( 1175,355,100,100);
        losebuttonsbounds=new Rectangle(1175,225,100,100);


        redbird = new RedBird(game.redbirdTexture, 105, 170, 0.25f);
        yellowbird=new YellowBird(game.yellowbirdTexture,58,180,0.16f);
        bluebird = new BlueBird(game.bluebirdTexture, 00, 170, 0.25f);


        woodblock1 = new WoodBlock(game.woodblockTexture, 950, 250, 0.5f);
        woodblock2 = new WoodBlock(game.woodblockTexture, 1050, 250, 0.5f);
        woodblock3 = new WoodBlock(game.woodblockTexture, 950, 250, 0.60f);
        woodblock4 = new WoodBlock(game.woodblockTexture, 1135, 250, 0.6f);
        woodblock5 = new WoodBlock(game.woodblockTexture, 1000, 335, 0.5f);
        woodblock6 = new WoodBlock(game.woodblockTexture, 1000, 335, 0.6f);
        woodblock7 = new WoodBlock(game.woodblockTexture, 1085, 335, 0.6f);

        pig1= new LargePig(game.pigTexture, 1000, 350);
        pig2= new MediumPig(game.pigTexture, 1100, 270);
        pig3= new SmallPig(game.pigTexture, 950, 270);


    }

    @Override
    public void show() {
    }
    private void drawBlock(WoodBlock block) {
        game.batch.draw(game.woodblockTexture, block.getPosition().x, block.getPosition().y, block.getScale() * block.getBlockTexture().getWidth(),block.getScale() * block.getBlockTexture().getHeight());
    }
    private void drawBlock_vertically(WoodBlock block) {
        TextureRegion woodblockTextureRegion = new TextureRegion(game.woodblockTexture);
        float scaleX = block.getScale();
        float scaleY = block.getScale();
        game.batch.draw(
            woodblockTextureRegion,
            block.getPosition().x,
            block.getPosition().y,
            0,0,
            scaleX * block.getBlockTexture().getWidth(),
            scaleY * block.getBlockTexture().getHeight(),
            scaleX,
            scaleY,
            270
        );


    }
    private void drawBird(Bird bird) {
        game.batch.draw(bird.getBirdTexture(), bird.getPosition().x, bird.getPosition().y,
            bird.getScale() * bird.getBirdTexture().getWidth(), bird.getScale() * bird.getBirdTexture().getHeight());
    }
    private void drawPig(Pig pig){
        game.batch.draw(game.pigTexture, pig.getPosition().x, pig.getPosition().y, pig.getScale()*pig.getPigTexture().getWidth(), pig.getScale()*pig.getPigTexture().getHeight());

    }
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.5f, 0.8f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            if (pauseButtonBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                game.setScreen(new PauseScreen(game));
            }
            if (backButtonBounds.contains(touchPos.x, Gdx.graphics.getHeight() - touchPos.y)) {
                game.setScreen(new LevelScreen(game));
            }
            if(restartbuttonBounds.contains(touchPos.x,Gdx.graphics.getHeight() - touchPos.y)){
                game.setScreen(new GameScreen1(game));
            }
            if(winbuttonsbounds.contains(touchPos.x,Gdx.graphics.getHeight() - touchPos.y)){
                game.setScreen(new win(game));
            }
            if(losebuttonsbounds.contains(touchPos.x,Gdx.graphics.getHeight() - touchPos.y)){
                game.setScreen(new lose(game));
            }

        }

        game.batch.begin();
        game.batch.draw(game.gameScreenTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //game.batch.draw(game.woodblockTexture, 1175,225,100,100);

        drawBird(redbird);
        drawBird(yellowbird);
        drawBird(bluebird);


        drawBlock(woodblock1);
        drawBlock(woodblock2);
        drawBlock_vertically(woodblock3);
        drawBlock_vertically(woodblock4);
        drawBlock(woodblock5);
        drawBlock_vertically(woodblock6);
        drawBlock_vertically(woodblock7);

        drawPig(pig1);
        drawPig(pig2);
        drawPig(pig3);
        game.batch.end();
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
