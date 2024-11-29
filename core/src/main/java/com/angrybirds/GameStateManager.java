// GameStateManager.java
package com.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class GameStateManager {
    private static final String SAVE_FILE_PREFIX = "level_";
    private static final String SAVE_FILE_SUFFIX = "_save.json";
    private static GameStateManager instance;
    private GameStore currentGameState;

    private GameStateManager() {
        currentGameState = new GameStore();
    }

    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }

    public void saveGameState(int levelNumber, Screen currentScreen) {
        try {
            // Create new game state
            currentGameState = new GameStore();
            currentGameState.setCurrentLevelNumber(levelNumber);

            // Capture level state based on screen type
            GameStore.SerializableLevelState levelState = new GameStore.SerializableLevelState();
            if (currentScreen instanceof GameScreen1) {
                levelState.captureGameState((GameScreen1) currentScreen);
            }
            else if (currentScreen instanceof GameScreen2) {
                levelState.captureGameState2((GameScreen2) currentScreen);
            }
            else if (currentScreen instanceof GameScreen3) {
                levelState.captureGameState3((GameScreen3) currentScreen);
            }

            currentGameState.setCurrentLevelState(levelState);

            // Save to file
            String filename = SAVE_FILE_PREFIX + levelNumber + SAVE_FILE_SUFFIX;
            currentGameState.saveGame(filename);

        } catch (Exception e) {
            Gdx.app.error("GameStateManager", "Error saving game state: " + e.getMessage());
        }
    }

//    public Screen loadGameState(AngryBirdsGame game, int levelNumber) {
//        try {
//            String filename = SAVE_FILE_PREFIX + levelNumber + SAVE_FILE_SUFFIX;
//            GameStore loadedState = GameStore.loadGame(filename);
//
//            if (loadedState != null) {
//                currentGameState = loadedState;
//
//                // Create appropriate screen based on level number
//                switch (levelNumber) {
//                    case 1:
//                        return new GameScreen1(game, "LOAD");
////                    case 2:
////                        return new GameScreen2(game, "LOAD");
////                    case 3:
////                        return new GameScreen3(game, "LOAD");
//                    default:
//                        return new GameScreen1(game, "LOAD");
//                }
//            }
//        } catch (Exception e) {
//            Gdx.app.error("GameStateManager", "Error loading game state: " + e.getMessage());
//        }

    public Screen loadGameState(AngryBirdsGame game, int levelNumber) {
        try {
            String filename = SAVE_FILE_PREFIX + levelNumber + SAVE_FILE_SUFFIX;
//           String filename= "level1_save.json";
            GameStore loadedState = GameStore.loadGame(filename);

            if (loadedState != null) {
                currentGameState = loadedState;

                // Print previous game state information
                System.out.println("Loading Level " + levelNumber + " Save:");
                System.out.println("Total Score: " + loadedState.getTotalScore());

                // Check previous level result
                if (loadedState.getCurrentLevelState() != null) {
                    System.out.println("Previous Attempt Details:");
                    System.out.println("Remaining Birds: " + loadedState.getCurrentLevelState().remainingBirds);
                    System.out.println("Score at Save: " + loadedState.getCurrentLevelState().score);

                    // Optionally, add a hint about previous outcome
                    if (loadedState.getCurrentLevelState().remainingBirds == 0) {
                        System.out.println("Previous Attempt: LEVEL FAILED");
                    } else if (loadedState.getCurrentLevelState().pigs.length == 0) {
                        System.out.println("Previous Attempt: LEVEL COMPLETED");
                    } else {
                        System.out.println("Previous Attempt: IN PROGRESS");
                    }
                }

                System.out.println(filename);

                // Create appropriate screen based on level number
                switch (levelNumber) {
                    case 1:
                        return new GameScreen1(game, "LOAD");
                    case 2:
                        return new GameScreen2(game, "LOAD");
                    case 3:
                        return new GameScreen3(game, "LOAD");
                    // Add cases for other levels as you expand
                    default:
                        return new GameScreen1(game, "LOAD");
                }
            }
        } catch (Exception e) {
            Gdx.app.error("GameStateManager", "Error loading game state: " + e.getMessage());
        }

        // Return new game screen if load fails
        return createNewGameScreen(game, levelNumber);
    }

    private Screen createNewGameScreen(AngryBirdsGame game, int levelNumber) {
        switch (levelNumber) {
            case 1:
                return new GameScreen1(game, "NEW");
            case 2:
                return new GameScreen2(game, "NEW");
            case 3:
                return new GameScreen3(game, "NEW");
            default:
                return new GameScreen1(game, "NEW");
        }
    }

    public GameStore getCurrentGameState() {
        return currentGameState;
    }
}
