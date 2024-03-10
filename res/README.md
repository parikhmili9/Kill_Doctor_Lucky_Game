# CS 5010 Semester Project

This repo represents the coursework for CS 5010, the Fall 2022 Edition!

**Name:** Mili Bimal Parikh and Shreyas Sameer Terdalkar

**Email:** parikh.mi@northeastern.edu terdalkar.s@northeastern.edu

**Preferred Name:** Mili and Shreyas



### About/Overview

This game is called Kill Doctor Lucky Board Game.
It has been implemented using MVC Design Pattern.
The game lets the user create a world of Doctor Lucky's Mansion or any other world of the user's choice by letting it add the world specifications as per a particular format.
The game contains various rooms and allows the user to create multiple players to compete and play the game.
The game has a target named Doctor Lucky which needs to be killed to win the game.
The players are allowed to take turns and perform various actions to play the game.
The model is responsible for the creation of the world, its game characters and weapons.
It is also responsible for the actual implementation of the game.
The controller is responsible for controlling the working of the model and the view thus controlling the user interaction with the game.
The view is the graphical visual representation of the game.
It is responsible to display the game play using its several components.


### List of Features

Game of Doctor Lucky's World generates graphical representation of the world. 
It allows the character of Doctor Lucky to move through the World monotonically and automatically at each turn.
The latest milestone also introduces the target's pet which enters the world along with the target, however, then modifies its trajectory. 
In addition, It provides with details of certain Room and its Neighbors, weapons kept in the room and any visitors i.e. Players or Target.
The game allows adding of new players, both human controlled and computer controlled.
The game allows us to display player information such as its name, its current location and weapons in its arsenal.
The players are given turns in the order in which they are added to the game.
Once maximum no of turns are reached, game is called off.
The human player is allowed to pick a weapon available from its current location, look around to see neighboring spaces, move to its neighboring spaces.
The computer player performs any of same activities automatically.
In order to win, the players must collect weapons and stay closer to the target. When in same room, the players can attack and try to damage target's health.
The game introduces a few twists in the game. The players are allowed peeping in other rooms to keep an eye on others while they attempt to kill the target.
In such situation they may get successful in creating obstacles for other players thus creating a competition.
Furthermore, the pet makes the room in which it is residing invisible. This creates advantage for any player who want to be become invisible so as to successfully attempt damage to the target.
The players are also allowed to move the pet from one location to other.
The game allows addition of unlimited number of players to play the game.
Any of the players can win the game by making enough damages on the target.



### How to Run

Run milestone4.jar file from /res folder. Pass argument as file path of world specification text file ,maximum no of turns and an integer 0. - "././res/mansion.txt 100 0"
The integer has been introduced for purpose of testing computer player behaviour by making it behaviour. For normal conditions, the integer must be 0. For testing, this integer can take values among 1,2,3.
1 represents computer player picking a weapon, 2 represents computer looking around and 3 represents computer moving the pet.



### How to Use the Program

Run milestone4.jar file from /res folder. Pass argument as file path of world specification text file ,maximum no of turns and an integer 0. - "././res/mansion.txt 100 0"
The integer has been introduced for purpose of testing computer player behaviour by making it behaviour. For normal conditions, the integer must be 0. For testing, this integer can take values among 1,2,3.
1 represents computer player picking a weapon, 2 represents computer looking around and 3 represents computer moving the pet.

The game begins by displaying Welcome to Doctor Lucky Board Game.
There are 2 options in the menu bar. Start a game or other options.
Click on Quit under options to quit the game any time during the play.
Click on Help to enter this dialog. This contains all the instructions on how to play the game.
Select Enter the world into Doctor Lucky's Mansion to start the game with preloaded specifications.
Select Enter the world of your choice to start the game with custom-made game specifications.
Upon selecting second option, enter the filepath for the custom-made game to start the game.
Once the game begins, the game board will appear.
Top left corner displays the target health and information about its movements
Top right corner displays no of turns left before the game to end and whose turn it is.
Top center part contains two buttons to add human and computer players to play the game.
Select respective buttons, enter the name of player ( it should be single string without space)
and name of the room where the player will enter. Room names must be exactly as per specifications.
Once a player enters the game, the game begins by alloting turn to the first player added.
The cental part of the game board displays the graphical representation of the game world.
The players can be added any time during the game. They will be alloted turns in the order of their addition.
The bottom part of the game board displays status of the game during the game play.
The game board displays the world with its different rooms and the target.
The game board displays players as they enter the world. You can click on the player icon of the player
with current turn to display the player information.
The game also has a target's pet. It is invisible and makes everything around it invisible.
The pet enters the world along with the target and keeps wandering on each turn making others in its room invisible.
The target wanders in the world as well evading itself from getting targetted.
The player is able to move to its neighbouring rooms by clicking on it when it is its turn.
The player is able to pick a weapon to attack the target by pressing P on keyboard.
The player is given suggestion on what to pick and must enter the weapon name to pick it up.
Each player is allowed 3 weapons to be picked at a time.
The player is able to look around to plan its moves by pressing L on keyboard.
This displays all the information regarding the player's room and its neighbours.
The player is able to move the pet to its desired location by pressing M on the keyboard.
Enter the room to put the pet in that room however, the pet wanders off to another location immediately.
If the player is in the same room as the target it can attack the target by pressing A on keyboard.
This displays the available weapons with the player from which the player can choose to attack with.
The player can always choose poke to damage the target if it does or doesn't have any weapons.
With enough damage, target's health reduces to zero and the final attacker wins the game.
If no of turns exhaust before anyone winning the game gets over.


### Example Runs


1. Welcome Frame
/res/milestone4/run1.png
2. New Game
/res/milestone4/run2.png
3. Help
/res/milestone4/run3.png
4. Add Player
/res/milestone4/run4.png
5. Add Computer
/res/milestone4/run5.png
6. Game Play, Players and Target
/res/milestone4/run6.png
7. Look
/res/milestone4/run7.png
8. Move
/res/milestone4/run8.png
9. MovePet
/res/milestone4/run9.png
10. Pick
/res/milestone4/run10.png
11. Kill
/res/milestone4/run11.png
12. Poke
/res/milestone4/run12.png




### Design/Model Changes

Many getter methods and corresponding fields have been introduced in the model so as to fulfill requirements of the gui based game.
The text based controller has been replaced by Gui based controller.
The view has been introduced which implements the graphical representation of the game.
The view listens to the user inputs in the form of key presses, mouse clicks or component initiation and triggers corresponding features of the controller. The controller then triggers appropriate model methods to actuate the palyer action.



### Assumptions

The format of the specification file of the World is assumed to be fixed and it is read and parsed accordingly.



### Limitations

Computer player should not be added before adding at least one human player, as the computer player plays in the loop until it wins or the no of turns get exhausted.
The key listeners perform the intended activity multiple times.
The mouse listeners sometimes trigger an unending loop of error popups.
The game cannot be restarted once game beigns. The game must be ended and rerun again to restart instead.
The user can only avail the player info of the player with current turn.



### Citations

1. https://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java
2. https://www.baeldung.com/find-list-element-java
3. https://www.techiedelight.com/read-text-file-using-filereader-java/
4. https://stackoverflow.com/questions/66258854/what-is-a-javadoc-summary-fragment
5. https://www.vogella.com/tutorials/Mockito/article.html
6. lab05-controller/test/test/FailingAppendable.java


