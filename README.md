# Blackjack Game

## Introduction / Rules

Blackjack is one of the most popular games at casinos.
The objective of the game is to beat the dealer / house. 
Every gambler is dealt 2 cards at the beginning of the game
and takes turns to "hit" (ask for another card), stand
(keep his current hand as it is), or double (hit and double
the current hand stake). There is another action you can do which 
is split (create 2 hands from a pair) but that seems a bit too advanced
for now, maybe I will implement it later in the project.
The dealer also has a hand which follows a strict set of rules.
The dealer must hit until their hand value reaches 17 or higher
at which point they stand. f the dealer has a soft 17 
(a hand value of 17 including an Ace counted as 11), 
they must hit.If a gambler's hand exceeds 21, they lose the round, 
even if the dealer also busts. If both the gambler and dealer 
have the same hand value, the round is a push (tie) and the
gambler's bet is returned. The dealer only reveals one of their cards at
the start of the round, with the second card revealed only after all
players have completed their turns.

## Reasons I Am Making This Project

### What will the application do?

This program will replicate a game of blackjack as if you
were sitting at a real blackjack table at a casino (minus the ability
to split your hand). It will  have all the proper rules and 
functionality that you would expect this program to have.

### Who Will Use The Application

People who want to practice blackjack without real stakes,
gamble for fun without real stakes, practice card counting,
or people who want to play blackjack with a friend at their house
but do not own a playing card set.

### Interest To Me

This is of large interest to me because personally blackjack
has always been something I wanted to learn how to play super well
(i.e learning to card count) and this program would help me do that.
Also I think that being able to just mess around in blackjack without real
stakes is really nice as I won't actually turn into a gambling addict and lose
real money.

## How To Play

First launch the game with the run button located at the top, 
Specify how many players are playing and the starting balance for everyone. The game
then proceeds as a regular blackjack game with each gambler specifying to hit, stand, or double
during their turns. This continues until all players run out of money, or you close the game.

## User Stories

- As a user, I want to be able to add more balance to my list of players.

- As a user, I want to be able to add a card to my list of players.

- As a user, I want to be able to add a gambler to my list of players.

- As a user, I want to be able to view a scoreboard of my list of players.

- As a user, I want to have the option to be able to save the progress 
of all the players with their wins, draws, losses, and balance 
or just exit the program

- As a user, I want to be able to load my progress that I had saved
or start a game new.

## Citation

A large part of the saving and loading of game data to and from a json file was adapted from
the CPSC 210 team, big thank you to them. This includes the tests and some methods. 
You can find their code here at https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

## Instruction For Grader

- You can generate the first required action related to adding a Gambler to a ListOfGamblers by 
clicking the Add Player button on the main menu and following the instructions then clicking confirm
- You can generate the second required action related to adding Cards to a Gambler by hitting the Hit
button on the play screen. To reach the play screen hit Play on the main menu

- You can locate my visual component by saving, loading, or playing the game

- You can save the state of my application by clicking the save button on the main menu

- You can reload the state of my application by clicking the load save button on the main menu

## Phase 4: Task 2
Created 3 Gamblers with $1000 and added to ListOfGamblers


Wed Apr 12 18:26:53 PDT 2023
Set Gambler 1's bet to 100


Wed Apr 12 18:26:53 PDT 2023
Removed 100 from Gambler 1's balance


Wed Apr 12 18:26:54 PDT 2023
Set Gambler 2's bet to 500


Wed Apr 12 18:26:54 PDT 2023
Removed 500 from Gambler 2's balance


Wed Apr 12 18:26:57 PDT 2023
Set Gambler 3's bet to 69


Wed Apr 12 18:26:57 PDT 2023
Removed 69 from Gambler 3's balance


Wed Apr 12 18:26:57 PDT 2023
Added Card to Dealer


Wed Apr 12 18:26:57 PDT 2023
Added Card to Dealer


Wed Apr 12 18:26:57 PDT 2023
Set 2nd Dealer Card to face down


Wed Apr 12 18:26:57 PDT 2023
Added card to Gambler 1


Wed Apr 12 18:26:57 PDT 2023
Added card to Gambler 1


Wed Apr 12 18:26:57 PDT 2023
Added card to Gambler 2


Wed Apr 12 18:26:57 PDT 2023
Added card to Gambler 2


Wed Apr 12 18:26:57 PDT 2023
Added card to Gambler 3


Wed Apr 12 18:26:57 PDT 2023
Added card to Gambler 3


Wed Apr 12 18:26:59 PDT 2023
Added card to Gambler 1


Wed Apr 12 18:27:02 PDT 2023
Added card to Gambler 2


Wed Apr 12 18:27:05 PDT 2023
Set Gambler 3's bet to 138


Wed Apr 12 18:27:05 PDT 2023
Removed 138 from Gambler 3's balance


Wed Apr 12 18:27:05 PDT 2023
Set Gambler 3's bet to 138


Wed Apr 12 18:27:05 PDT 2023
Removed 276 from Gambler 3's balance


Wed Apr 12 18:27:05 PDT 2023
Added card to Gambler 3


Wed Apr 12 18:27:06 PDT 2023
Set Gambler 3's bet to 276


Wed Apr 12 18:27:06 PDT 2023
Removed 276 from Gambler 3's balance


Wed Apr 12 18:27:06 PDT 2023
Set Gambler 3's bet to 276


Wed Apr 12 18:27:06 PDT 2023
Removed 552 from Gambler 3's balance


Wed Apr 12 18:27:06 PDT 2023
Added card to Gambler 3


Wed Apr 12 18:27:08 PDT 2023
Added Card to Dealer


Wed Apr 12 18:27:08 PDT 2023
Set Dealer to Stand


Wed Apr 12 18:27:08 PDT 2023
Added 200 to Gambler 1's balance


Wed Apr 12 18:27:08 PDT 2023
Set Gambler 1 to have 0 bet


Wed Apr 12 18:27:08 PDT 2023
Added Win to Gambler 1


Wed Apr 12 18:27:08 PDT 2023
Set Gambler 1's hand to be empty


Wed Apr 12 18:27:08 PDT 2023
Set Gambler 1 to be not stand


Wed Apr 12 18:27:08 PDT 2023
Added 1000 to Gambler 2's balance


Wed Apr 12 18:27:08 PDT 2023
Set Gambler 2 to have 0 bet


Wed Apr 12 18:27:08 PDT 2023
Added Win to Gambler 2


Wed Apr 12 18:27:08 PDT 2023
Set Gambler 2's hand to be empty


Wed Apr 12 18:27:08 PDT 2023
Set Gambler 2 to be not stand


Wed Apr 12 18:27:08 PDT 2023
Added 552 to Gambler 3's balance


Wed Apr 12 18:27:08 PDT 2023
Set Gambler 3 to have 0 bet


Wed Apr 12 18:27:08 PDT 2023
Added Win to Gambler 3


Wed Apr 12 18:27:08 PDT 2023
Set Gambler 3's hand to be empty


Wed Apr 12 18:27:08 PDT 2023
Set Gambler 3 to be not stand


Wed Apr 12 18:27:08 PDT 2023
Cleared hand of dealer


