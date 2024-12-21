


This is our Github Repo. link: https://github.com/shivam-de-bug/Angry-Bird-Game

We have implemented Angry Birds game using libGDX library.

## Game Functionality

### Level Progression
- When the game starts, you can play any level.
- If you *win* or *lose*, the level can be restarted to play again.

### Game State Management
- *Pause and Back*:
    - If you pause the game and click the *Back* button, the game saves automatically.
- *Pause and Resume*:
    - If you pause the game and then *resume*, the game automatically loads from where you left off.
- *Pause and Home*:
    - If you pause the game and return to the *Home* screen, the game saves the current state.
    - When you return to the level, it will *only restart/reset* if you explicitly click the *Reset* button on the level screen.

### Birds and Blocks
- Three different blocks are available: **wood**, **glass**, and **steel**, each with different durability.
- Three types of birds are included:
    - **Red bird**: Standard bird.
    - **Yellow bird**: Has a special ability to go faster when you click on the screen while it is flying.
    - **Blue bird**: Splits into smaller birds when you click on the screen during its flight.

### Debug Information
- The following information is printed in the terminal:
    - Current *game state*
    - Current *score*
    - Number of *pigs* remaining
    - Number of *birds* remaining
- This information is displayed when you:
    - *Resume* the game.
    - *Enter a level* where you left off.


Concepts used here
Polymorphism : All the different screen classes such as GameSreen1,HomeScreen,LevelScreen,etc, implements screen interface which is offered by libGDX and employ their own particular display logic by over-riding the render method of screen class. Code reuse is promoted by using common characteristics of classes under the concept of polymorphism. In this case, screen classes utilize general Screen behaviors whereas bird classes use general Bird traits in order to minimize redundancy.
Inheritance: Inheritance is used because we have created classes like SmallPig,MediumPig,LargePig which are inheriting the parent class Pig similarly blueBird,redBird and yellowBird classes are inheriting Bird class. The AngryBirdsGame class inherits from the Game superclass. This relationship allows AngryBirdsGame to use core Game methods, allowing features such as game state management without having to rewrite the underlying logic.
Classes:We have defined classes for the different parts of the game like for HomeScreen,StartScreen,lose,win,SaveScreen,Loadinggame,level,GameScreen1,etc all which have single responsibility jobs and leads to loosely coupled classes.
Abstraction:Screen classes offer higher abstraction for game states and thereby the AngryBirdsGame class deals with the state transitions without dealing directly with the screen details.
Encapsulation:All the pieces of the game are enclosed within a class.All properties, for instance, redbirdTexture or woodblockTexture, can be accessed only within the AngryBirdsGame class or any classes of its own. That ensures each object has protected state and exposes only needed behavior through public methods.
The classes and components are modularly designed so they become open to extension but closed to modification of code.
Class boundaries and the responsibility of code have defined clear, organized responsibilities, thereby enhancing readability and maintainability of the code. It can be separated into clearly differentiated classes-gamescreens-textures so that a part may be modified or expanded with much less risk of creating side effects elsewhere.






We used game screen image from internet and we have used canva for some images we have used.
We read wiki documentation on libGDX website. We used setup documentation on libGDX website for reference for setup.

These are the steps to setup libGDX in your system:
Step 1: Download JDK
LibGDX supports JDK 11 and higher.
download JDK from the Oracle site or Open JDK via SDKMAN:

sdk install java 11.0.11-open
End

Step 2: Download Build Tool - Gradle
LibGDX relies on Gradle to manage its artifacts and automate much of the build process. The Gradle wrapper comes bundled with the LibGDX setup.
Step 3: Download Setup Tool LibGDX
Go to the official LibGDX Setup App page.
Download LibGDX Project Setup Jar (for example, gdx-setup.jar).
Step 4: Run the Setup Tool
Use LibGDX Project Setup to create a new project

Open a terminal or command prompt and change into the folder in which you downloaded gdx-setup.jar
Running the following command
java -jar gdx-setup.jar
Step 5: Configure the Project Setup
Once the setup UI opens, you should be prompted to configure your project:
End.
Package: Your package namespace (for example, com.mygame.app).
Game Class: The class name. By default it is Main.
Destination: The folder where you want the project to be generated.
Sub Projects: Select target platforms. Here, you can select more than one, Desktop, Android, iOS, HTML etc
Extensions: Choose any LibGDX extension that you need, e.g. Box2D, controllers, etc
Clicking on Generate will create a project with Gradle support in the chosen directory.

Step 6: Import the Project into an IDE
a) Using IntelliJ IDEA:

Open IntelliJ IDEA.

From the welcome screen select Open and in the opened window choose the directory where the LibGDX project was generated.

Select the root directory which contains build.gradle file.

IntelliJ will automatically import the Gradle project and download the necessary dependencies.

a) Using IntelliJ IDEA:
Open the desktop/src/./DesktopLauncher.java

Right-click somewhere inside the file, then select Run 'DesktopLauncher.main()'.

Step 8: Gradle Commands
You can also build or run the project from the terminal with Gradle. Navigate to the project directory, and run this:

./gradlew desktop:runi


Now you are ready to run!

