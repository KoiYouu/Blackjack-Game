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



    public BlackJackGame() {
        this.startGame();
    }

    public void startGame() {
        preGameSetUp();

        while (!gameOver) {
            displayMenuOptions();
            String input = userInput.next();
            input = input.toLowerCase();

            if (input.equals("q")) {
                gameOver = true;
            } else if (input.equals("p")) {
                playGame();
            } else {
                System.out.println("Please choose from given options.");
            }

        }
        System.out.println("See you next time!");
    }

    public void playGame() {
        beginRound();
        for (Player player: listOfPlayers.getPlayers()) {
            playersTurn(player);
        }

    }

    public void playersTurn(Player player) {

    }

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

    public void displayMenuOptions() {
        System.out.println("Press \"p\" to play");
        System.out.println("Press \"q\" to quit");
    }

    public void displayGameOptions() {
        System.out.println("Press \"h\" to hit");
        System.out.println("Press \"s\" to stand");
        System.out.println("Press \"d\" to double");
    }
}
