# Super Mario Bros

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#run">Run</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

<img width="1267" alt="Screenshot 2022-12-08 at 2 02 03 PM" src="https://user-images.githubusercontent.com/65887526/206576705-723843ee-ac9f-461a-a7ca-861b087e3bae.png">

### Detailed Interworkings and Relationships Between Key Components

#### Game Engine
**Function**: The Game Engine orchestrates the core game mechanics, including the main game loop which updates game logic and renders graphics in response to player interactions and game rules.

**Relationships**:
- **With Game Objects**: Updates game objects each frame based on game logic and player inputs, adjusting positions and states.
- **With I/O Handlers**: Processes inputs from I/O Handlers to modify game states and trigger actions in game objects, such as movements and attacks.
- **With Game Object Managers**: Utilizes these managers to dynamically create, modify, or remove game objects as dictated by gameplay needs and player actions.
- **With Game Map**: Interacts with the Game Map to correctly place game objects and enforce game rules related to environmental constraints.

#### Game Objects
**Function**: Represents all interactive entities in the game, each equipped with properties like position and behaviors like move or interact.

**Relationships**:
- **With Game Engine**: Controlled by the game engine, receiving updates on their state and interactions every game loop.
- **With Game Object Managers**: Managed by these managers, which handle their lifecycle events such as spawning and removal.
- **With I/O Handlers**: Respond directly to player inputs received via I/O handlers, affecting behavior (e.g., moving or jumping).
- **With Game Map**: Engage with static elements of the map, such as colliding with obstacles or navigating terrain.

#### Game Object Managers
**Function**: Specialized systems that manage specific groups of game objects, responsible for their instantiation, destruction, and state management.

**Relationships**:
- **With Game Engine**: Implement changes as directed by the game engine to update game objects according to gameplay rules.
- **With Game Objects**: Oversee the lifecycle of game objects, from creation to removal based on game dynamics.
- **With Game Map**: Use map data to strategically place or remove objects in alignment with game progression.

#### I/O Handlers
**Function**: Manage all player interactions with the game through input devices (like keyboards and mice) and facilitate the output of game state changes to the display.

**Relationships**:
- **With Game Engine**: Forward player inputs to the game engine, influencing game state adjustments.
- **With Game Objects**: Directly affect game objects through player commands, triggering specific actions like movements and abilities.

#### Game Map
**Function**: Defines the layout and environmental boundaries of the game world, incorporating static elements like terrain, platforms, and scenery.

**Relationships**:
- **With Game Engine**: Provides a structural framework for the game engine's operations, setting physical limits and interaction rules.
- **With Game Objects**: Acts as the physical space where game objects exist and interact, including elements such as collision detection and navigation.
- **With Game Object Managers**: Informs managers about optimal object placement and removal in response to player advancement and environmental cues.

This comprehensive overview of the key components and their interactions provides a deeper understanding of the game's architecture and operational dynamics, essential for both developers and enthusiasts looking to engage with or modify the game.


### Built With

- Java version: openjdk-19
- IDE: Intellij 2022.2.3 (Ultimate Edition)
- Resources:
  - [Super Mario Bros Reference Manual](https://themushroomkingdom.net/smb_breakdown.shtml)
  - [Map Editor](https://www.mapeditor.org)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

### Game Controls
|       Action        |          Key         |
|:-------------------:|----------------------|
|      Move Left      |         LEFT         |
|      Move Right     |         RIGHT        |
|        Jump         |          UP          |
|   Shoot Fireballs   |           F          |
| Select in Main Menu |          ESC         |
| Pause/Resume in Game|          ESC         |


<!-- 
### Prerequisites

This is an example of how to list things you need to use the software and how to install them.
* npm
  ```sh
  npm install npm@latest -g 
  ``` -->

### Installation

- If you have the JavaLauncher.app on your device, you can run the .jar as follows: Right Click .jar -> Open with -> JavaLauncher.app(default).
Otherwise proceed to the steps below to build and run the project in Intellij.

#### Package an application into a JAR﻿

When the code is compiled and ready, you can package your application in a Java archive (JAR) to share it with other developers. A built Java archive is called an artifact.

#### Create an artifact configuration for the JAR﻿

1. From the main menu, select **File | Project Structure ⌘;** and click **Artifacts**.

2. Click **+** , point to **JAR**, and select **From modules with dependencies**.

3. To the right of the **Main Class** field, click  and select the main class in the dialog that opens (for example, **HelloWorld (com.example.helloworld)**).
    - IntelliJ IDEA creates the artifact configuration and shows its settings in the right-hand part of the **Project Structure** dialog.

4. Apply the changes and close the dialog.

<p align="center">
  <img width="550" height="300" src="https://user-images.githubusercontent.com/65887526/207480030-311925b9-5c77-415f-938e-e6033e7fb40d.gif">
</p>

#### Build the JAR artifact﻿

1. From the main menu, select **Build | Build Artifacts**.

2. Point to the created **.jar (HelloWorld:jar)** and select **Build**.
    - If you now look at the **out/artifacts** folder, you'll find your **.jar** file there.

<p align="center">
  <img width="550" height="300" src="https://user-images.githubusercontent.com/65887526/207480783-6e36e27d-c03c-4a75-9086-a26b0b3eeda5.png">

</p>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->

## Usage

- to be updated...

## Run

- To run a Java application packaged in a JAR, IntelliJ IDEA allows you to create a dedicated run configuration.

### Create a run configuration﻿

1. Press **⇧ ⌘ A**, find and run the **Edit Configurations** action.

2. In the **Run/Debug Configurations** dialog, click **+** and select **JAR Application**.

3. Add a name for the new configuration.

<p align="center">
  <img width="550" height="300" src="https://user-images.githubusercontent.com/65887526/207481530-0ef477ee-4c08-4457-bc5f-48cfcefa2729.gif">
</p>

4. In the Path to JAR field, click  and specify the path to the JAR file on your computer.

5. Under Before launch, click  **+**, select Build Artifacts in the dialog that opens.
    - Doing this means that the JAR is built automatically every time you execute the run configuration.
<p align="center">
  <img width="550" height="300" src="https://user-images.githubusercontent.com/65887526/207482126-947bec8d-08e1-4be8-824a-9e4c13491c4c.gif">
</p>

- Run configurations allow you to define how you want to run your application, with which arguments and options. You can have multiple run configurations for the same application, each with its own settings.

- After completing the steps above you are ready to Run the Game using Intellij.

