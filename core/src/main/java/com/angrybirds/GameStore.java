
package com.angrybirds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class GameStore implements Serializable {
    private int currentLevelNumber;
    private int totalScore;
    private SerializableLevelState currentLevelState;
    public static String originalTexturePath;


    public GameStore() {
        currentLevelNumber = 1;
        totalScore = 0;
    }

    public void setCurrentLevelNumber(int levelNumber) {
        this.currentLevelNumber = levelNumber;
    }

    public int getCurrentLevelNumber() {
        return currentLevelNumber;
    }

    public void setTotalScore(int score) {
        this.totalScore = score;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setCurrentLevelState(SerializableLevelState state) {
        this.currentLevelState = state;
    }

    public SerializableLevelState getCurrentLevelState() {
        return currentLevelState;
    }

    public void saveGame(String filename) {
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        String jsonString = json.toJson(this);

        FileHandle file = Gdx.files.local(filename);
        file.writeString(jsonString, false);
    }

    public static GameStore loadGame(String filename) {
        try {
            FileHandle file = Gdx.files.local(filename);
            if (!file.exists()) {
                return null;
            }

            Json json = new Json();
            return json.fromJson(GameStore.class, file.readString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static  void clearSaveFile(String SAVE_FILE) {
        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            writer.write("{}"); // Overwrite with an empty JSON object
            System.out.println("Save file cleared successfully.");
        } catch (IOException e) {
            System.err.println("Error clearing save file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static class SerializableLevelState implements Serializable {
        // Serializable representations of game objects
        public SerializableBird[] birds;
        public SerializableBlock[] blocks;
        public SerializablePig[] pigs;

        // Game state variables
        public int remainingBirds;
        public int currentBirdIndex;
        public int score;

        //         Capture the current game state
        public void captureGameState(GameScreen1 gameScreen) {
            // Capture birds
            birds = new SerializableBird[gameScreen.birds.size];
            for (int i = 0; i < gameScreen.birds.size; i++) {
                birds[i] = new SerializableBird(gameScreen.birds.get(i));
            }

            // Capture blocks
            blocks = new SerializableBlock[gameScreen.blocks.size];
            for (int i = 0; i < gameScreen.blocks.size; i++) {
                blocks[i] = new SerializableBlock(gameScreen.blocks.get(i));
            }

            // Capture pigs
            pigs = new SerializablePig[gameScreen.pigs.size];
            for (int i = 0; i < gameScreen.pigs.size; i++) {
                pigs[i] = new SerializablePig(gameScreen.pigs.get(i));
            }

            // Capture other game state
            remainingBirds = gameScreen.remainingBirds;
            currentBirdIndex = gameScreen.birds.indexOf(gameScreen.currentBird, true);
            score = gameScreen.score;
        }
        public void captureGameState2(GameScreen2 gameScreen) {
            // Capture birds
            birds = new SerializableBird[gameScreen.birds.size];
            for (int i = 0; i < gameScreen.birds.size; i++) {
                birds[i] = new SerializableBird(gameScreen.birds.get(i));
            }

            // Capture blocks
            blocks = new SerializableBlock[gameScreen.blocks.size];
            for (int i = 0; i < gameScreen.blocks.size; i++) {
                blocks[i] = new SerializableBlock(gameScreen.blocks.get(i));
            }

            // Capture pigs
            pigs = new SerializablePig[gameScreen.pigs.size];
            for (int i = 0; i < gameScreen.pigs.size; i++) {
                pigs[i] = new SerializablePig(gameScreen.pigs.get(i));
            }

            // Capture other game state
            remainingBirds = gameScreen.remainingBirds;
            currentBirdIndex = gameScreen.birds.indexOf(gameScreen.currentBird, true);
            score = gameScreen.score;
        }
        public void captureGameState3(GameScreen3 gameScreen) {
            // Capture birds
            birds = new SerializableBird[gameScreen.birds.size];
            for (int i = 0; i < gameScreen.birds.size; i++) {
                birds[i] = new SerializableBird(gameScreen.birds.get(i));
            }

            // Capture blocks
            blocks = new SerializableBlock[gameScreen.blocks.size];
            for (int i = 0; i < gameScreen.blocks.size; i++) {
                blocks[i] = new SerializableBlock(gameScreen.blocks.get(i));
            }

            // Capture pigs
            pigs = new SerializablePig[gameScreen.pigs.size];
            for (int i = 0; i < gameScreen.pigs.size; i++) {
                pigs[i] = new SerializablePig(gameScreen.pigs.get(i));
            }

            // Capture other game state
            remainingBirds = gameScreen.remainingBirds;
            currentBirdIndex = gameScreen.birds.indexOf(gameScreen.currentBird, true);
            score = gameScreen.score;
        }

        // Restore the game state
        public void restoreGameState(GameScreen1 gameScreen) {

            gameScreen.birds.clear();
            gameScreen.blocks.clear();
            gameScreen.pigs.clear();

            // Restore birds
            for (SerializableBird serializableBird : birds) {
                Bird bird = serializableBird.reconstructBird(gameScreen.game);
                gameScreen.birds.add(bird);
            }

            // Restore blocks
            for (SerializableBlock serializableBlock : blocks) {
                BuildingBlock block = serializableBlock.reconstructBlock(gameScreen.game);
                gameScreen.blocks.add(block);
            }

            // Restore pigs
            for (SerializablePig serializablePig : pigs) {
                Pig pig = serializablePig.reconstructPig(gameScreen.game);
                gameScreen.pigs.add(pig);
            }

            // Restore game state
            gameScreen.remainingBirds = remainingBirds;
            gameScreen.currentBird = gameScreen.birds.get(currentBirdIndex);
            gameScreen.score = score;
        }
        public void restoreGameState2(GameScreen2 gameScreen) {

            gameScreen.birds.clear();
            gameScreen.blocks.clear();
            gameScreen.pigs.clear();

            // Restore birds
            for (SerializableBird serializableBird : birds) {
                Bird bird = serializableBird.reconstructBird(gameScreen.game);
                gameScreen.birds.add(bird);
            }

            // Restore blocks
            for (SerializableBlock serializableBlock : blocks) {
                BuildingBlock block = serializableBlock.reconstructBlock(gameScreen.game);
                gameScreen.blocks.add(block);
            }

            // Restore pigs
            for (SerializablePig serializablePig : pigs) {
                Pig pig = serializablePig.reconstructPig(gameScreen.game);
                gameScreen.pigs.add(pig);
            }

            // Restore game state
            gameScreen.remainingBirds = remainingBirds;
            gameScreen.currentBird = gameScreen.birds.get(currentBirdIndex);
            gameScreen.score = score;
        }
        public void restoreGameState3(GameScreen3 gameScreen) {

            gameScreen.birds.clear();
            gameScreen.blocks.clear();
            gameScreen.pigs.clear();

            // Restore birds
            for (SerializableBird serializableBird : birds) {
                Bird bird = serializableBird.reconstructBird(gameScreen.game);
                gameScreen.birds.add(bird);
            }

            // Restore blocks
            for (SerializableBlock serializableBlock : blocks) {
                BuildingBlock block = serializableBlock.reconstructBlock(gameScreen.game);
                gameScreen.blocks.add(block);
            }

            // Restore pigs
            for (SerializablePig serializablePig : pigs) {
                Pig pig = serializablePig.reconstructPig(gameScreen.game);
                gameScreen.pigs.add(pig);
            }

            // Restore game state
            gameScreen.remainingBirds = remainingBirds;
            gameScreen.currentBird = gameScreen.birds.get(currentBirdIndex);
            gameScreen.score = score;
        }



        // Serializable Bird representation
        public static class SerializableBird implements Serializable {
            public float x, y;
            public float velocityX, velocityY;
            public float scale;
            public float power;
            public boolean launched;
            public String birdType;

            public SerializableBird() {
            }

            public SerializableBird(Bird bird) {
                x = bird.getPosition().x;
                y = bird.getPosition().y;
                velocityX = bird.getVelocity().x;
                velocityY = bird.getVelocity().y;
                scale = bird.getScale();
                power = bird.getPower();
                launched = bird.launched;

                // Determine bird type
                if (bird instanceof RedBird) birdType = "red";
                else if (bird instanceof YellowBird) birdType = "yellow";
                else if (bird instanceof BlueBird) birdType = "blue";
                else birdType = "generic";
            }

            public Bird reconstructBird(AngryBirdsGame game) {
                Bird bird;
                switch (birdType) {
                    case "red":
                        bird = new RedBird(game.redbirdTexture, x, y, scale);
                        break;
                    case "yellow":
                        bird = new YellowBird(game.yellowbirdTexture, x, y, scale);
                        break;
                    case "blue":
                        bird = new BlueBird(game.bluebirdTexture, x, y, scale,true);
                        break;
                    default:
                        bird = new Bird(game.redbirdTexture, x, y, scale, power);
                }

                bird.setPosition(x, y);
                bird.velocity.set(velocityX, velocityY);
                bird.launched = launched;  // Preserve launch state
                return bird;
            }
        }

        // Serializable Block representation
        public static class SerializableBlock implements Serializable {
            public float x, y;
            public float scale;
            public float health;
            public boolean isDestroyed;
            public String blockType;
            public boolean isVertical;
            public String texturePath;
            public String brokenTexturePath;
            public boolean ishit;
            public int pathno;

            public SerializableBlock() {
            }

            public SerializableBlock(BuildingBlock block) {
                x = block.getPosition().x;
                y = block.getPosition().y;
                scale = block.getScale();
                health = block.getCurrentHealth();
                isDestroyed = block.isDestroyed();
                isVertical = block.isVertical();

                // Determine block type and texture paths
                blockType = block.type.name();

                brokenTexturePath = block.newtexturepath;
                ishit = block.ishit();
                originalTexturePath = block.getBlockTexture().toString();
                pathno = block.pathno;
                texturePath = getTexturePathForBlock(block);


            }


            private String getTexturePathForBlock(BuildingBlock block) {
                pathno = block.pathno;
                String path = "brokeglass.jpeg"; // Default value if no conditions are met
                if (pathno == 1) {
                    path = "wood.jpeg";
                } else if (pathno == 2) {
                    path = "woodvertical.png";
                } else if (pathno == 3) {
                    path = "glass.jpg";
                } else if (pathno == 4) {
                    path = "glassver.jpg";
                } else if (pathno == 5) {
                    path = "steel.jpeg";
                } else if (pathno == 6) {
                    path = "steelver.jpeg";
                } else if (pathno == 7) {
                    path = "brokewood.jpeg";
                } else if (pathno == 8) {
                    path = "brokewoodver.jpeg";
                } else if (pathno == 9) {
                    path = "brokeglass.jpeg";
                } else if (pathno == 10) {
                    path = "brokesteelver.jpeg";
                } else if (pathno == 11) {
                    path = "brokesteel.jpeg";
                } else if (pathno == 12) {
                    path = "brokesteelver.jpeg";
                }
                return path;
            }


            public BuildingBlock reconstructBlock(AngryBirdsGame game) {

                Texture texture = new Texture(texturePath);
//            Texture texture = new Texture(originalTexturePath);
                BuildingBlock.BlockType type = BuildingBlock.BlockType.valueOf(blockType);

                BuildingBlock block = new BuildingBlock(
                    texture, x, y, scale, type, isVertical, brokenTexturePath, pathno
                );


                block.health = health;
                block.isDestroyed = isDestroyed;
                block.ishit = ishit;

                return block;
            }
        }

        // Serializable Pig representation
        public static class SerializablePig implements Serializable {
            public float x, y;
            public float scale;
            public float health;
            public boolean isDestroyed;
            public String pigType;
            public boolean hasBeenHit;

            public SerializablePig() {
            }

            public SerializablePig(Pig pig) {
                x = pig.getPosition().x;
                y = pig.getPosition().y;
                scale = pig.getScale();
                health = pig.getCurrentHealth();
                isDestroyed = pig.isDestroyed();
                hasBeenHit = pig.hasBeenHit;
                pigType = pig.type.name();
            }

            public Pig reconstructPig(AngryBirdsGame game) {
                Pig.PigType type = Pig.PigType.valueOf(pigType);
                Pig pig = new Pig(game.pigTexture, x, y, scale, type);

                // Restore pig state
                pig.health = health;
                pig.isDestroyed = isDestroyed;
                pig.hasBeenHit = hasBeenHit;

                // If hit, change texture
                if (hasBeenHit) {
                    pig.setTexture("NO");
                }

                return pig;
            }
        }


    }
}
