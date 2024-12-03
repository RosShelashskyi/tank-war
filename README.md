# **Tank War**
Rostyslav Shelashskyi

## How to build
First, clone the repository to your local machine.  
`git clone git@github.com:RosShelashskyi/tank-war.git`

Ensure Maven and JavaFX Runtime are installed.  
Navigate to the repository and run 
`mvn javafx:jlink`  

If the build succeeds, the Mac/Linux executable will be located in  
`tank-war/targer/app/bin/app`  

The Windows executable will be located in  
`tank-war/targer/app/bin/app.bat`

## Project Structure and Overview
Class diagram for Tank War:  
![Tank War Class Diagram](https://github.com/user-attachments/assets/7f2774be-97dd-4b6f-bdc7-a106a08e200e)

Tank War is a simple game made with JavaFX. The game's core functions, such as game setup and cleanup, the main game loop, and victory/defeat conditions and logic are located in TankWar.java.
The `classes` folder contains the classes used by the game to represent game objects and their behavior. 

### TankWar.java
The entry point of the program that handles core functionality, instantiates classes, and contains the main game loop.
The game loop is implemented using JFX AnimationTimer, and is run (I'm assuming) at every frame, detecting player's inputs and updating AI behavior.
This file also handles win and defeat conditions, and offers the player to play again if they win or lose. 

### GameObject.java
The game object class is an abstract class that defines the behavior of moving game objects, such as tanks or missiles.
It defines functions for changing the directon the object is facing, moving the object, and detecting collisions.

### Wall.java
This class represents walls. It is essentially an abstraction for JFX's Rectangle object. The class allows the developer to create new walls and specify their dimensions and location to place them on the map.

### Medkit.java
This class represents medkits used to heal the player tank. The medkit is consumed and removed when the player tank touches it.

### Tank.java
Represents tank objects. Inherits from the GameObject class. In addition to GameObject's behavior, Tank have health, can shoot missiles, and take damage from them.  

### Missile.java
Represents missiles shot by tank. Once shot, a missile will move in the direction it is facing until it hits a wall or another tank. Defines methods for moving indefinitely in a straight line, detecting collisions, and dealing damage to tanks.

### PlayerTank.java
Represents the tank controlled by the player. In addition to methods defined in Tank, PlayerTank contains a health bar and extended logic for health and healing. Player tank controls are defined in TankWar.java.

### AITank.java
Represents the tanks controlled by the game. Defines methods for AI behavior that are called every frame by TankWar.java. The AI algorithm is as follows: move 50-200 px in a random direction, shoot in the direction of the player tank, and pause for a minute. Repeat.
