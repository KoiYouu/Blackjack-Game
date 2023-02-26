package ui;

import model.Dealer;
import model.ListOfPlayers;
import model.Player;

import java.util.Locale;
import java.util.Scanner;

public class BlackJackGame {
    private Scanner userInput = new Scanner(System.in);
    private Dealer dealer;
    private ListOfPlayers listOfPlayers;
    private boolean gameOver = false;


    // EFFECTS: Creates a game of blackjack
    public BlackJackGame() {
        this.startGame();
    }

    // MODIFIES: this
    // EFFECTS: Starts up the game of blackjack, creates as many players as the user specifies with each
    // player getting the specified balance. Displays the menu options to quit or play, if neither option is chosen
    // a message will appear telling the user to pick a given option. if quit is selected game exits, if play is
    // selected the game starts, with bets being collected and the game playing out.
    public void startGame() {
        preGameSetUp();

        while (!gameOver) {
            displayMenuOptions();
            String input = userInput.next();
            input = input.toLowerCase();

            if (input.equals("q")) {
                gameOver = true;
            } else if (input.equals("p")) {
                getBets();
                playGame();

            } else {
                System.out.println("Please choose from given options.");
            }

        }
        System.out.println("See you next time!");
    }

    // REQUIRES: bet >= 1 and <= players balance
    // MODIFIES: player
    // EFFECT: sets all the players bets and takes away the bet from their balance
    public void getBets() {
        for (Player player: listOfPlayers.getPlayers()) {
            System.out.println("Player " + player.getPlayerID() + " your current balance is: $"
                    + player.getBalance());
            System.out.println("How much would you like to bet? [Must range from 1-" + player.getBalance()
                    + "]");
            int bet = userInput.nextInt();
            player.setBet(bet);
        }
    }

    public void playGame() {
        beginRound();
        for (Player player: listOfPlayers.getPlayers()) {
            playersTurn(player);

        }
    }



    public void playersTurn(Player player) {
        System.out.println("Player " + player.getPlayerID() + "\'s turn:");
        System.out.println("Dealers cards: " + dealer.getDealersCards());
        System.out.println("Players cards: " + player.getAllCards());
        displayGameOptions();
    }

    // REQUIRES: numPlayers >= 1, startingMoney >= 1
    // MODIFIES: ListOfPlayers
    // EFFECTS: Starts the game and sets up the number of players, balance for each player,
    // and initializes the dealer
    public void preGameSetUp() {
        System.out.println("Welcome To BlackJack!");
        System.out.println("This BlackJack table pays 2:1");
        System.out.println("Standard BlackJack rules apply, but there is no splitting");
        System.out.println("");
        System.out.println("Please enter how many players are playing");
        int numPlayers = userInput.nextInt();
        System.out.println("You have selected " + numPlayers + " players");
        System.out.println("");
        System.out.println("Please enter how much starting balance to give to each player");
        int startingMoney = userInput.nextInt();
        System.out.println("You have given everyone $" + startingMoney + " to start with!");
        System.out.println("");
        listOfPlayers = new ListOfPlayers(numPlayers, startingMoney);
        dealer = new Dealer();
    }

    // MODIFIES: dealer, player
    // EFFECTS: starts the game of blackjack off by giving the deal 2 cards, 1 face up and another facedown, then
    // gives every player 2 cards and displays the current state of the game.
    public void beginRound() {
        System.out.println("Dealers turn!");
        System.out.println("");
        dealer.startingDealerTurn();
        System.out.println("Dealer draws: " + dealer.getDealersCards());
        System.out.println("");
        System.out.println("Now drawing 2 cards for each player:");
        System.out.println("");
        System.out.println("");
        for (int i = 0; i < listOfPlayers.getPlayers().size(); i++) {
            listOfPlayers.getPlayers(i).hitCard();
            listOfPlayers.getPlayers(i).hitCard();
        }
        System.out.println(listOfPlayers.getAllPlayersCards());
    }

    // EFFECTS: displays the menu options
    public void displayMenuOptions() {
        System.out.println("Press \"p\" to play");
        System.out.println("Press \"q\" to quit");
    }

    // EFFECTS: displays the game options
    public void displayGameOptions() {
        System.out.println("Press \"h\" to hit");
        System.out.println("Press \"s\" to stand");
        System.out.println("Press \"d\" to double");
    }
}
