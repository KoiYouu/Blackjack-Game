package ui;

import model.Dealer;
import model.ListOfPlayers;

import java.util.Scanner;

public class BlackJackApp {
    private Scanner userInput = new Scanner(System.in);
    private Dealer dealer;
    private ListOfPlayers listOfPlayers;
    private boolean gameOver = false;

    public BlackJackApp() {
        this.startGame();
    }

    public void startGame() {
        System.out.println("Welcome To BlackJack!");
        System.out.println("Please enter how many players are playing");
        int numPlayers = userInput.nextInt();
        System.out.println("You have selected " + numPlayers + " players");
        System.out.println("Please enter how much starting balance to give to each player");
        int startingMoney = userInput.nextInt();
        listOfPlayers = new ListOfPlayers(numPlayers, startingMoney);
        dealer = new Dealer();
        System.out.println(dealer.startingDealerTurn());
    }
}
