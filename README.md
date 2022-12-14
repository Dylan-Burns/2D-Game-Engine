# Super Mario Bros

<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->






<!-- PROJECT SHIELDS -->

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

This Project was programmed using Java for the purposes of the CSC 413 Term Project (Fall 2022). The intent was to create a 2D game that was both functional and intuitive to play. I believe this goal was achieved through a multifaceted approach involving object Oriented Analysis and Design principles accompanied by proper research and an eagerness to bring the game to fruition.

The main concept of the game I designed was heavily based on the Classic Super Mario Bros released by Nintendo in September of 1985. This game was and still is considered an icon of the times when computer graphics and software initially took their first steps outside of large corporations and into the hands of people with a passion for creation itself.

The development of Super Mario bros can be broken down into 5 main components: The Game Engine, Game Objects, Game Object Managers, I/O handlers, and the Game Map. Aside from the main components, we also have to manage the window in which the game is played, manage sound and animation, check collisions and notify the appropriate managers to identify and handle those collision, and lastly ensure we render all these components onto the screen in response to the multivariate interactions in the game.

It is important to note that all of these concepts were implemented with the intention of following best coding practices for Object Oriented Software Design:

- **Cohesion** - Classes describe single entities, and all class operations fit together to support an object with a predefined purpose. Entities too large for a single class are broken down into smaller subcomponents – each with their own responsibility.

- **Consistency** - The code structure (for the most part) follows the standard Java style and naming conventions: 
  - Descriptive names for classes, data fields, and methods
  - Class structure from top to bottom (imports, class header, data fields, constructors, and methods)
  - Proper use of access modifiers to grant or restrict access
  - Singletons for objects that require individuality (i.e. Game Engine)

- **Encapsulation** -  It is important to maintain a certain level of abstraction within the project. Objects are given data based on absolute necessity to reach full functionality. This makes code easier to debug and maintain.

- **Clarity** - Each class has a general contract at the beginning detailing its responsibility and implementation.

- **Inheritance & Aggregation** - Classes were designed using the is-a and has-a relationship. If the Object is-a variation of the parent object, we use inheritance. If the Object has-a relationship to the object we use aggregation. (i.e. Person:Student -> inheritance | Person:Dog -> aggregation)

**Super Mario Bros** is played on a 2D grid using the x-z axis (left, right, up, down). The goal of the game is to reach the flag and the right-most end of the map, all the while dipping, diving and dodging from Goomba, Koopa Troopa, and gaps in the ground. The user can hit bricks revealing prizes (Fire Flower, One-up mushroom, Magic mushroom) or coins. The main User Interface allows the user to select 3 different options(Start Game, Help, About). Once a map has been selected there is a User Interface in the top of the screen notifying you of your health status, points, coins, and time count.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

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

- If you have the JavaLauncher.app on your device, you can run the .jar as follows: Right Click .jar -> Open with -> JavaLauncher.app(default).
Otherwise proceed to the steps below to build and run the project in Intellij.
<!-- 
### Prerequisites

This is an example of how to list things you need to use the software and how to install them.
* npm
  ```sh
  npm install npm@latest -g 
  ``` -->

### Installation

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

<!-- USAGE EXAMPLES -->

## Usage

- to be updated...

## Run

- To run a Java application packaged in a JAR, IntelliJ IDEA allows you to create a dedicated run configuration.

### Create a run configuration﻿

1. Press **⇧ ⌘ A**, find and run the Edit Configurations action.

2. In the Run/Debug Configurations dialog, click **+** and select JAR Application.

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

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Reques

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

- Dylan Burns
- [Email](dburns6@sfsu.edu)
- [LinkedIn](www.linkedin.com/in/dylan-burns-)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->!

