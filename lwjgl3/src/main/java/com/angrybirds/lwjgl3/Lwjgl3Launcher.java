package com.angrybirds.lwjgl3;

import com.angrybirds.AngryBirdsGame;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Lwjgl3Launcher {
    public static void main(String[] args) {

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Angry Birds");
        config.setWindowedMode(1280, 720);
        config.setWindowIcon("start_screen.jpeg");
        config.setResizable(false);
        new Lwjgl3Application(new AngryBirdsGame(), config);


    }
}

