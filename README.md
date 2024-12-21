# Angry Birds Game Development using Advanced Programming

## Project Overview
This project is a Java-based implementation of the popular Angry Birds game using the libGDX framework. It focuses on applying advanced programming concepts such as object-oriented principles (OOP), design patterns, and serialization to deliver a robust, modular, and feature-rich game.

---

## Features

### Gameplay Features
1. **Level Progression:**
   - Play multiple levels with unique structures, pigs, and birds.
   - Restart levels upon win or loss.

2. **Game State Management:**
   - Pause and resume the game while maintaining progress.
   - Save and load game states automatically during transitions.
   - Return to the Home screen while saving the current game state.

3. **Bird and Block Variants:**
   - Three types of birds:
     - **Red Bird:** Standard bird.
     - **Yellow Bird:** Accelerates mid-flight upon player input.
     - **Blue Bird:** Splits into smaller birds during flight.
   - Blocks of varying durability:
     - **Wood**, **Glass**, and **Steel**.
   - Pigs with varying hit requirements based on size.

4. **Debug Information:**
   - Displays game state, score, remaining pigs, and birds in the terminal during key actions in console.

### Serialization and Game Saves
- Save the game state, including:
  - Collapsed structures.
  - Hits dealt to pigs.
  - Remaining birds and progress within the current level.
- Restore game progress from saved states using a dedicated menu.



## Concepts Used
1. **Polymorphism:**
   - All screen classes (e.g., `GameScreen1`, `HomeScreen`, `LevelScreen`) implement the `Screen` interface provided by libGDX, overriding methods like `render()` for specific behavior.
   - Bird and Pig subclasses extend generic parent classes to minimize code redundancy.

2. **Inheritance:**
   - Classes like `SmallPig`, `MediumPig`, and `LargePig` inherit from a base `Pig` class.
   - Bird subclasses (e.g., `RedBird`, `BlueBird`, `YellowBird`) inherit from a base `Bird` class.
   - `AngryBirdsGame` extends libGDX's `Game` class for core game management.

3. **Encapsulation:**
   - Game properties (e.g., textures, scores) are encapsulated within classes, exposing only necessary methods.

4. **Abstraction:**
   - High-level abstraction for screen management, enabling smooth transitions between game states without exposing implementation details.

5. **Design Patterns:**
   - Utilized at least two design patterns to ensure modularity and extensibility.

---

## Installation and Setup

### Prerequisites
- **JDK**: Version 11 or higher.
- **Gradle**: Build tool for managing dependencies and automation.
- **libGDX**: Java game development framework.

### Steps to Set Up
1. **Download JDK:**
   ```bash
   sdk install java 11.0.11-open
   ```

2. **Download Gradle:**
   - Comes bundled with the libGDX setup tool.

3. **Set Up LibGDX Project:**
   - Download `gdx-setup.jar` from the [official LibGDX site](https://libgdx.com/).
   - Run the setup tool:
     ```bash
     java -jar gdx-setup.jar
     ```
   - Configure the project with appropriate settings (e.g., package name, extensions, platforms).

4. **Import Project into IDE:**
   - Open the `build.gradle` file in your IDE (e.g., IntelliJ IDEA).
   - Allow Gradle to sync dependencies.

5. **Run the Game:**
   - Locate `DesktopLauncher.java` and run it:
     ```bash
     ./gradlew desktop:run
     ```

---

## Acknowledgments
- **Images:** Some images are sourced from the internet; others were created using Canva.
- **References:**
  - libGDX official documentation and wiki.
  - Setup guides from the libGDX website.

---

## Repository Link
[Angry Birds Game Repository](https://github.com/shivam-de-bug/Angry-Bird-Game)

---



