package com.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class GameScreen3 implements Screen {
    public AngryBirdsGame game;
    public  Array<Bird> birds;
    public Array<BuildingBlock> blocks;
    public Array<Pig> pigs;

    public Bird currentBird;
    public Vector2 slingshot;
    private Vector2 dragStart;
    private Vector2 dragPos;

    private boolean isDragging;
    private boolean birdLaunched;
    private boolean showTrajectory;

    private static final float SLINGSHOT_X = 160+10;
    private static final float SLINGSHOT_Y = 300-20;
    private static final float MAX_DRAG_DISTANCE = 100f;
    private static final float MIN_VELOCITY = 5f;

    private Rectangle pauseButtonBounds;
    private Rectangle backButtonBounds;
    private Rectangle restartButtonBounds;
    public int score;

    private GameState gameState;
    public int remainingBirds;
    private boolean levelComplete;
    private boolean levelFailed;

    private Vector2 groundLevel;
    private boolean birdReady = false;
    private Array<BuildingBlock> blocksToRemove = new Array<>();
    private String todo;


    public GameScreen3(final AngryBirdsGame game,String todo) {
        this.game = game;
        this.birds = new Array<>();
        this.blocks = new Array<BuildingBlock>();
        this.pigs = new Array<>();
        this.slingshot = new Vector2(SLINGSHOT_X, SLINGSHOT_Y);
        this.gameState = GameState.READY;
        this.score = 0;
        this.dragPos= new Vector2();

        this.showTrajectory = false;
        this.groundLevel = new Vector2(0, 180);
        initializeLevel();
        findinput();
        this.todo=todo;



        if (todo.equals("LOAD")) {
            GameStore gameState = GameStateManager.getInstance().getCurrentGameState();
            if (gameState != null && gameState.getCurrentLevelState() != null) {
                gameState.getCurrentLevelState().restoreGameState3(this);


                System.out.println("Game Loaded Successfully:");
                System.out.println("Remaining Birds: " + remainingBirds);
                System.out.println("Current Score: " + score);
                System.out.println("Pigs Remaining: " + pigs.size);
            }
        }
    }

    private void initializeLevel() {


        birds.add(new RedBird(game.redbirdTexture, SLINGSHOT_X, SLINGSHOT_Y-150+20, 0.25f));
        birds.add(new YellowBird(game.yellowbirdTexture, SLINGSHOT_X - 50, SLINGSHOT_Y-150+30, 0.16f));
        birds.add(new YellowBird(game.yellowbirdTexture, SLINGSHOT_X - 100, SLINGSHOT_Y-150+20, 0.16f));
        birds.add(new RedBird(game.redbirdTexture, SLINGSHOT_X - 100, SLINGSHOT_Y-150+20, 0.25f));
        birds.add(new BlueBird(game.bluebirdTexture, SLINGSHOT_X - 100, SLINGSHOT_Y-150+20, 0.25f,false));

        currentBird = birds.first();
        remainingBirds = birds.size;

        //Blocks
        blocks.add(new StoneBlock(game.stonevertexture, 800, 400, 0.5f, true,"brokesteelver.jpeg",6));
        blocks.add(new StoneBlock(game.stonevertexture, 852, 230, 0.5f, true,"brokesteelver.jpeg",6));  // Vertical block 1
        blocks.add(new StoneBlock(game.stonevertexture, 980, 230, 0.5f, true,"brokesteelver.jpeg",6));  // Vertical block 2
        blocks.add(new StoneBlock(game.stonevertexture,950,400,0.5f,true,"brokesteelver.jpeg",6));
        blocks.add(new StoneBlock(game.stonevertexture,1070,400,0.5f,true,"brokesteelver.jpeg",6));


//        blocks.add(new WoodBlock(game.woodblockTexture, 830-18, 295, 0.6f, false,"brokewood.jpeg",1));
        blocks.add(new GlassBlock(game.glasstexture,804,295,0.6f,false,"brokeglass.jpeg",3));
        blocks.add(new GlassBlock(game.glasstexture,900,180,0.3f,false,"brokeglass.jpeg",3));
        blocks.add(new StoneBlock(game.stonetexture, 952, 295, 0.7f, false,"brokesteel.jpeg",5));
        blocks.add(new StoneBlock(game.stonetexture, 952, 420, 0.7f, false,"brokesteel.jpeg",5));
        blocks.add(new StoneBlock(game.stonetexture, 1100, 200, 0.6f, false,"brokesteel.jpeg",5));
        //pigs
        pigs.add(new SmallPig(game.pigTexture, 825, 300));
        pigs.add(new SmallPig(game.pigTexture,895,190));
        pigs.add(new MediumPig(game.pigTexture, 975, 300));
        pigs.add(new LargePig(game.pigTexture, 1110, 200));

        //buttons
        pauseButtonBounds = new Rectangle(1175, 605, 100, 100);
        backButtonBounds = new Rectangle(15, 600, 100, 100);
        restartButtonBounds = new Rectangle(1175, 480, 100, 100);
    }



    private static  String SAVE_FILE = "level_3_save.json";



    public void saveGameState() {
        try {
            GameStore gameProgress = new GameStore();
            GameStore.SerializableLevelState levelState = new GameStore.SerializableLevelState();
            levelState.captureGameState3(this);

            gameProgress.setCurrentLevelNumber(3); // Hardcoded for now

            gameProgress.setCurrentLevelState(levelState);
            gameProgress.setTotalScore(score);
            gameProgress.saveGame(SAVE_FILE);

            System.out.println("Game saved successfully! "+"level3");
        } catch (Exception e) {
            System.err.println("Error saving game state: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadGameState() {
        try {
            GameStore loadedProgress = GameStore.loadGame(SAVE_FILE);

            if (loadedProgress != null) {

                birds.clear();
                blocks.clear();
                pigs.clear();
                GameStore.SerializableLevelState levelState = loadedProgress.getCurrentLevelState();
                if (levelState != null) {
                    levelState.restoreGameState3(this);
                }
                score = loadedProgress.getTotalScore();
                gameState = GameState.READY;
                birdLaunched = false;
                isDragging = false;
                showTrajectory = false;

                if (!birds.isEmpty()) {
                    currentBird = birds.get(birds.indexOf(currentBird, true));
                }

                System.out.println("Game loaded successfully!"+ "level3");
            } else {
                System.out.println("No saved game found.");
                initializeLevel();
            }
        } catch (Exception e) {
            System.err.println("Error loading game state: " + e.getMessage());
            e.printStackTrace();
            initializeLevel();
        }
    }

    GameScreen3 screenInstance = this;


    private void findinput() {


        Gdx.input.setInputProcessor(new InputAdapter() {


            @Override
            public boolean keyDown(int keycode) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
                    saveGameState();
                    return true;
                }

                if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
                    loadGameState();
                    return true;
                }

                return false;
            }
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector2 touchPos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);

                //   saveGameState();
                if (pauseButtonBounds.contains(touchPos.x, touchPos.y)) {
                    saveGameState();
                    GameStateManager.getInstance().saveGameState(3, screenInstance);  // Save current state
                    game.setScreen(new PauseScreen(game, 3));  // Pass current level number

                }
                if (backButtonBounds.contains(touchPos.x, touchPos.y)) {

                    saveGameState();
                    game.setScreen(new LevelScreen(game));
                    return true;
                }
                if (restartButtonBounds.contains(touchPos.x, touchPos.y)) {
                    game.setScreen(new GameScreen3(game,"NOTHING TO DO"));
                    return true;
                }


                if (!birdReady) {
                    currentBird.setPosition(SLINGSHOT_X, SLINGSHOT_Y);
                    birdReady = true;
                    return true;
                }



                if (gameState == GameState.READY && birdReady && isTouchingBird(touchPos)) {

                    isDragging = true;
                    dragStart = touchPos.cpy();
                    showTrajectory = true;
                    return true;
                }
                if (gameState == GameState.READY && currentBird.launched &&
                    (currentBird instanceof YellowBird || currentBird instanceof BlueBird)) {
                    if(currentBird instanceof YellowBird){
                        currentBird.useSpecial();
                    }
                    // BlueBird specific logic with improved splitting mechanism
                    if (currentBird instanceof BlueBird) {
                        BlueBird blueBird = (BlueBird) currentBird;

                        // Check if the bird has not already split and is in flight
                        if (!blueBird.hasSplit() && blueBird.launched) {
                            blueBird.useSpecial(); // Mark as split

                            // Create bird copies with proper tracking
                            blueBird.createCopies(game);
                            Array<BlueBird> newCopies=blueBird.getCopies();
                            // Add the copied birds to the game's bird list
                            for (BlueBird copyBird : newCopies) {
                                copyBird.setLaunched(true); // Explicitly set launched state
                            }
                        }
                    }

                    return true;
                }
                return false;
            }



            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                if (isDragging) {
                    dragPos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
                    Vector2 dragVector = dragPos.cpy().sub(slingshot);

                    float distance = dragVector.len();

                    if (distance > MAX_DRAG_DISTANCE) {
                        dragVector.nor().scl(MAX_DRAG_DISTANCE);
                    }


                    currentBird.setPosition(
                        slingshot.x + dragVector.x,
                        slingshot.y + dragVector.y
                    );



                    return true;
                }
                if (gameState == GameState.READY && currentBird.launched &&
                    (currentBird instanceof YellowBird || currentBird instanceof BlueBird)) {
                    if(currentBird instanceof YellowBird){
                        currentBird.useSpecial();
                    }
                    // BlueBird specific logic with improved splitting mechanism
                    if (currentBird instanceof BlueBird) {
                        BlueBird blueBird = (BlueBird) currentBird;

                        // Check if the bird has not already split and is in flight
                        if (!blueBird.hasSplit() && blueBird.launched) {
                            blueBird.useSpecial(); // Mark as split

                            // Create bird copies with proper tracking
                            blueBird.createCopies(game);
                            Array<BlueBird> newCopies=blueBird.getCopies();
                            // Add the copied birds to the game's bird list
                            for (BlueBird copyBird : newCopies) {
                                copyBird.setLaunched(true); // Explicitly set launched state
                            }
                        }
                    }

                    return true;
                }
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                if (isDragging) {
                    Vector2 releasePos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
                    Vector2 launchpos = slingshot.cpy().sub(releasePos);


                    float dragDistance = launchpos.len();
                    float launchPower = Math.min(dragDistance / MAX_DRAG_DISTANCE, 1.0f);

                    launchpos.nor().scl(launchPower * 2000);
                    currentBird.launch(launchpos);

                    isDragging = false;
                    showTrajectory = false;
//
                    gameState = GameState.READY;
                    remainingBirds--;




                    return true;
                }
                return false;
            }
        });
    }




    private boolean isTouchingBird(Vector2 touchPos) {
        Rectangle birdBounds = new Rectangle(
            currentBird.getPosition().x,
            currentBird.getPosition().y,
            currentBird.getBirdTexture().getWidth() * currentBird.getScale(),
            currentBird.getBirdTexture().getHeight() * currentBird.getScale()
        );
        return birdBounds.contains(touchPos.x, touchPos.y);
    }


    private void blockdestruction(BuildingBlock destroyedBlock) {
        //  saveGameState();
        Array<BuildingBlock> tempBlocks = new Array<>(blocks);

        for (BuildingBlock block : tempBlocks) {
            if (block.getPosition().y > destroyedBlock.getPosition().y &&
                Math.abs(block.getPosition().x - destroyedBlock.getPosition().x) <
                    (destroyedBlock.getBlockTexture().getWidth() * destroyedBlock.getScale() +
                        block.getBlockTexture().getWidth() * block.getScale())) {

                block.takeDamage(1);

                if (!block.isSupported(blocks, groundLevel.y)) {
                    block.velocity.y = -100;
                }
            }
        }
    }

    public void checkGameState() {
        Vector2 pos = currentBird.getPosition();

        if (pigs.size == 0) {
            gameState = GameState.LEVEL_COMPLETE;
        }

        else if (remainingBirds == 0 && !birdLaunched && gameState != GameState.BIRD_FLYING&& ( pos.x < 0 || pos.x > Gdx.graphics.getWidth() ||
            pos.y < 0 || pos.y > Gdx.graphics.getHeight())) {

            gameState = GameState.LEVEL_FAILED;
        }
    }

    private boolean Collisionforbirds(Bird bird, Object target) {
        Rectangle birdBounds = new Rectangle(
            bird.getPosition().x,
            bird.getPosition().y,
            bird.getBirdTexture().getWidth() * bird.getScale(),
            bird.getBirdTexture().getHeight() * bird.getScale()
        );

        Rectangle targetBounds;
        if (target instanceof BuildingBlock) {
            BuildingBlock block = (BuildingBlock) target;
            targetBounds = new Rectangle(
                block.getPosition().x,
                block.getPosition().y,
                block.getBlockTexture().getWidth() * block.getScale(),
                block.getBlockTexture().getHeight() * block.getScale()
            );
        } else if (target instanceof Pig) {
            Pig pig = (Pig) target;
            targetBounds = new Rectangle(
                pig.getPosition().x,
                pig.getPosition().y,
                pig.getPigTexture().getWidth() * pig.getScale(),
                pig.getPigTexture().getHeight() * pig.getScale()
            );
        } else {
            return false;
        }

        return birdBounds.overlaps(targetBounds);
    }




    @Override
    public void show() {
        findinput();
    }

    @Override
    public void render(float delta) {
        AngryBirdsGame.music.play();

        Gdx.gl.glClearColor(0.5f, 0.8f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        GameScreen3 screenInstance = this;
//
// block position
        if (gameState == GameState.READY) {
            for (Pig pig: pigs){
                float y = groundLevel.y;
                if(pig.getPosition().y<=y){
//
                    pig.hasBeenHit=true;
                    pig.takeDamage(11f);
                    pig.setTexture("YES");

                }
               }
//               for (Pig pig: pigs){
//                if(pig.hasBeenHit){
//                    pig.setTexture("YES");
//                }
//                else {
//                    pig.setTexture("NO");}
//            }
            currentBird.update(delta);
            blocksToRemove.clear();

            Array<BuildingBlock> tempBlocks = new Array<>(blocks);

            for (BuildingBlock block : tempBlocks) {
                // block with all blocks and ground level
                block.update(delta, blocks, groundLevel.y);

                // If a block is destroyed
                if (block.isDestroyed()) {
                    blocksToRemove.add(block);
                    blockdestruction(block);
                }
            }

            blocks.removeAll(blocksToRemove, true);
            //new position of pigs
            for (Pig pig : pigs) {

                pig.newposition(delta, blocks, groundLevel.y);
            }


            //  Collisions

            Array<BuildingBlock> tempoblock = new Array<>(blocks);
            Array<Pig> tempPigs = new Array<>(pigs);

            for (BuildingBlock block : tempoblock) {
                if (Collisionforbirds(currentBird, block)) {
                    AngryBirdsGame.blockdam.play();

                    block.settexture();
                    block.takeDamage(currentBird.getDamage());

                    if (block.isDestroyed()) {
                        blockdestruction(block);
                        score += 100;

                        if (!blocksToRemove.contains(block, true)) {
                            blocksToRemove.add(block);
                        }
                    }
                }
            }

            for (Pig pig : tempPigs) {
                if (Collisionforbirds(currentBird, pig)) {
                    AngryBirdsGame.pigdam.play();

                    pig.hasBeenHit=true;

                    pig.setTexture("YES");


                    pig.takeDamage(currentBird.getDamage());
                    if (pig.isDestroyed()) {
                        pigs.removeValue(pig, true);
                        score += 500;
                    }
                }
            }





            if (gameState != GameState.BIRD_FLYING) {

                checkGameState();
            }
            Vector2 pos = currentBird.getPosition();

            if (( pos.x < 0 || pos.x > Gdx.graphics.getWidth() ||
                pos.y < 0 || pos.y > Gdx.graphics.getHeight()) || (birdLaunched && currentBird.getVelocity().len() < MIN_VELOCITY)) {
//                next bird
                if (remainingBirds > 0) {
                    currentBird = birds.get(birds.size - remainingBirds);
                    currentBird.setPosition(slingshot.x, slingshot.y);
                    birdLaunched = false;
                    for (Pig pig: pigs){
//                        if(pig.hasBeenHit){
                            pig.setTexture("YES");
//                        }
//                        else {
//                        pig.setTexture("NO");}
                    }
                    gameState = GameState.READY;
                } else {
                    checkGameState();
                }
            }

        }

        checkGameState();

        game.batch.begin();

        game.batch.draw(game.gameScreenTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//    blocks
        for (BuildingBlock block : blocks) {
            game.batch.draw(block.getBlockTexture(),
                block.getPosition().x,
                block.getPosition().y,
                block.getBlockTexture().getWidth() * block.getScale(),
                block.getBlockTexture().getHeight() * block.getScale());
        }
//    pigs
        for (Pig pig : pigs) {
            game.batch.draw(pig.getPigTexture(),
                pig.getPosition().x,
                pig.getPosition().y,
                pig.getPigTexture().getWidth() * pig.getScale(),
                pig.getPigTexture().getHeight() * pig.getScale()
            );
        }
//    birds
        game.batch.draw(currentBird.getBirdTexture(),
            currentBird.getPosition().x,
            currentBird.getPosition().y,
            currentBird.getBirdTexture().getWidth() * currentBird.getScale(),
            currentBird.getBirdTexture().getHeight() * currentBird.getScale()
        );

        int offset = 1;
        for (int i = birds.size - remainingBirds; i < birds.size; i++) {
            Bird bird = birds.get(i);
            if (bird != currentBird) {
                game.batch.draw(bird.getBirdTexture(),
                    SLINGSHOT_X - (offset * 50),
                    SLINGSHOT_Y-150,
                    bird.getBirdTexture().getWidth() * bird.getScale(),
                    bird.getBirdTexture().getHeight() * bird.getScale()
                );
                offset++;
            }
        }        game.batch.draw(game.dragtextture, 50, Gdx.graphics.getHeight() - 50, 00f, 00f);

        if (gameState == GameState.LEVEL_COMPLETE) {
            score=0;
            AngryBirdsGame.music.pause();
            GameStore.clearSaveFile(SAVE_FILE);

            game.setScreen(new win(game));
        } else if (gameState == GameState.LEVEL_FAILED && gameState!=GameState.BIRD_FLYING ) {
            score=0;
            AngryBirdsGame.music.pause();
            GameStore.clearSaveFile(SAVE_FILE);


            game.setScreen(new lose(game));
        }

        if (isDragging) {
            // Band;
            if (currentBird == null) return;

            ShapeRenderer shapeRenderer = new ShapeRenderer();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled); // Changed to Filled for thicker lines
            shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);

            float birdCenterX = currentBird.getPosition().x +
                (currentBird.getBirdTexture().getWidth() * currentBird.getScale() / 2);
            float birdCenterY = currentBird.getPosition().y +
                (currentBird.getBirdTexture().getHeight() * currentBird.getScale() / 2);

            shapeRenderer.rectLine(SLINGSHOT_X - 10 + 52, SLINGSHOT_Y + 30, birdCenterX, birdCenterY, 15); // 5 is the line width
            shapeRenderer.rectLine(SLINGSHOT_X + 10 + 57, SLINGSHOT_Y + 24, birdCenterX, birdCenterY, 15);

            shapeRenderer.end();
            Vector2 gravity = new Vector2(0, -980f);
            Vector2 launchpos = slingshot.cpy().sub(dragPos).scl(16f);

            Vector2 position = currentBird.getPosition().cpy();
            position.x += 80;
            position.y += 40;
            Vector2 velocity = launchpos.cpy();
//        dragingline



            shapeRenderer = new ShapeRenderer();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(0.2f,
                0.4f, 0.5f, 0.5f);

            float timeStep = 0.1f;
            Vector2 previousPoint = position.cpy();

            for (int i = 0; i < 10; i++) {
                position.add(
                    velocity.x * timeStep,
                    velocity.y * timeStep + 0.5f * gravity.y * timeStep * timeStep
                );
                velocity.add(0, gravity.y * timeStep);

                // Draw a thick dashed line segment
                shapeRenderer.rectLine(previousPoint.x, previousPoint.y, position.x, position.y, 5);  // Adjust the width as needed

                previousPoint = position.cpy();
            }
            shapeRenderer.end();
        }


        Array<BlueBird> copiedBirds = new Array<>();
        for (Bird bird : birds) {
            if (bird instanceof BlueBird) {
                BlueBird blueBird = (BlueBird) bird;
                if (blueBird.hasSplit() && !blueBird.getCopies().isEmpty()) {
                    copiedBirds.addAll(blueBird.getCopies());
                }
            }
        }

        for (BlueBird copyBird : copiedBirds) {
            copyBird.update(delta);
            game.batch.draw(copyBird.getBirdTexture(),
                copyBird.getPosition().x,
                copyBird.getPosition().y,
                copyBird.getBirdTexture().getWidth() * copyBird.getScale(),
                copyBird.getBirdTexture().getHeight() * copyBird.getScale()
            );

            // Add collision checks for copied birds similar to the main bird
            for (BuildingBlock block :new Array<>(blocks)) {
                if (Collisionforbirds(copyBird, block)) {
                    block.settexture();
                    block.takeDamage(copyBird.getDamage());

                    if (block.isDestroyed()) {
                        blockdestruction(block);
                        score += 100;
                    }
                }
            }

            for (Pig pig : pigs) {
                if (Collisionforbirds(copyBird, pig)) {
                    pig.hasBeenHit = true;
                    pig.takeDamage(copyBird.getDamage());
                    if (pig.isDestroyed()) {
                        pigs.removeValue(pig, true);
                        score += 500;
                    }
                }
            }
        }
        game.batch.end();
    }


    private enum GameState {
        READY,
        BIRD_FLYING,
        LEVEL_COMPLETE,
        LEVEL_FAILED
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
    public void dispose() {
        for (Bird bird : birds) {
            bird.dispose();
        }
        for (BuildingBlock block : blocks) {
            block.dispose();
        }
        for (Pig pig : pigs) {
            pig.dispose();
        }
    }
    public boolean isLevelComplete() {
        return pigs.size == 0;
    }

    public void startNewLevel() {
        // Reset level state
        initializeLevel();
        gameState = GameState.READY;
    }
}










