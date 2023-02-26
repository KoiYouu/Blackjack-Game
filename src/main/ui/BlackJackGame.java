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

    // MODIFIES: this, player, dealer
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
                payOut();
                resetEveryone();
            } else if (input.equals("b")) {
                addBalanceToPlayer();
            } else if (input.equals("n")) {
                addPlayer();
            } else if (input.equals("s")) {
                viewScoreBoard();
            } else {
                System.out.println("Please choose from given options.");
            }
        }
        System.out.println("See you next time!");
    }

    // MODIFIES: dealer
    // EFFECTS: clears dealers hand and makes him not stand
    public void resetEveryone() {
        dealer.clearHand();
        dealer.setNotStand();
    }

    // EFFECTS: prints out a scoreboard of every players wins losses draws and balance
    public void viewScoreBoard() {
        System.out.println("");
        for (Player player: listOfPlayers.getPlayers()) {
            System.out.println("Player " + player.getPlayerID() + " stats: |Wins "
                    + player.getWins() + "| |Losses " + player.getLosses()
                        + "| |Draws " + player.getDraws() + "| |Balance $" + player.getBalance() + "|");
        }
        System.out.println("");
    }

    // REQUIRES: startingBalance > 0
    // MODIFIES: this, listOfPlayers, player
    // EFFECTS: creates a player with the specified starting balance and adds it to the end of listofplayers
    public void addPlayer() {
        System.out.println("Please specify the starting balance of the new player");
        int input = 0;
        while (input < 1) {
            input = userInput.nextInt();
            if (input < 1) {
                System.out.println("Please enter a valid number");
            }
        }
        listOfPlayers.getPlayers().add(new Player(input));
        int lastIndex = listOfPlayers.getPlayers().size() - 1;
        System.out.println("Successfully added new player with ID "
                + listOfPlayers.getPlayers(lastIndex).getPlayerID()
                    + " with balance of $" + listOfPlayers.getPlayers(lastIndex).getBalance());

    }

    // REQUIRES: moneyToAdd > 0 and input for player index to be >= 1 and <= listOfPlayers.getPlayers().size()
    // MODIFIES: this, player
    // EFFECTS: adds specified money to the specified player
    public void addBalanceToPlayer() {
        System.out.println("Please specify which player to add money to from [1-" + listOfPlayers.getPlayers().size()
                + "]");
        int input = 0;
        while (input < 1 || input > listOfPlayers.getPlayers().size()) {
            input = userInput.nextInt();
            if (input < 1 || input > listOfPlayers.getPlayers().size()) {
                System.out.println("Please enter a valid number");
            }
        }
        System.out.println("Selected player " + input + "\n");
        System.out.println("Specify how much money to add");
        int moneyToAdd = 0;
        while (moneyToAdd < 1) {
            moneyToAdd = userInput.nextInt();
            if (moneyToAdd < 1) {
                System.out.println("Please enter a valid number");
            }
        }
        System.out.println("Adding $" + moneyToAdd);
        listOfPlayers.getPlayers(input - 1).addBalance(moneyToAdd);
        System.out.println("Player " + listOfPlayers.getPlayers(input - 1).getPlayerID()
                + " has received $" + moneyToAdd + " and their new balance is $"
                    + listOfPlayers.getPlayers(input - 1).getBalance());
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
            int bet = 0;
            while (bet <= 0 || bet > player.getBalance()) {
                bet = userInput.nextInt();
                if (bet <= 0 || bet > player.getBalance()) {
                    System.out.println("Please enter a valid number");
                }
            }
            player.setBet(bet);
        }
    }

    // MODIFIES: this, dealer, player
    // EFFECTS: Plays out a single round of blackjack asking for every players actions,
    // and then finially finishing it off with the dealers turn
    public void playGame() {
        beginRound();
        for (Player player: listOfPlayers.getPlayers()) {
            playersTurn(player);
        }
        System.out.println("Dealers turn!");
        dealer.dealersTurn();
        System.out.println("Dealers Cards: " + dealer.getDealersCards());
        System.out.println("Finished that round!");
    }

    // MODIFIES: player
    // EFFECTS: pays out the player if they won according to regular blackjack rules,
    // or does nothing if player did not win, returns players bet if they pushed
    public void payOut() {
        System.out.println("Now to recap who won and lost with their respective payouts! \n");
        for (Player player: listOfPlayers.getPlayers()) {
            if (player.handValue() <= 21 && dealer.handValue() > 21) {
                System.out.println("Player " + player.getPlayerID() + " wins!");
                System.out.println("They earned a total of $" + player.getBet() + "\n");
                player.playerWin();
            } else if (player.handValue() <= 21 && player.handValue() > dealer.handValue()) {
                System.out.println("Player " + player.getPlayerID() + " wins!");
                System.out.println("They earned a total of $" + player.getBet() + "\n");
                player.playerWin();
            } else if (player.handValue() <= 21 && player.handValue() == dealer.handValue()) {
                System.out.println("Player " + player.getPlayerID() + " pushes!");
                System.out.println("They managed to keep their $" + player.getBet() + "\n");
                player.playerPush();
            } else {
                System.out.println("Player " + player.getPlayerID() + " loses!");
                System.out.println("They lost a total of $" + player.getBet() + "\n");
                player.playerLoss();
            }
        }
    }

    // REQUIRES: non-null player object, double to be inputted only if player has enough money
    // MODIFIES: player
    // EFFECTS: gets the players input for what to do during their turn and acts accordingly,
    // h for hit, s for stand, d for double, stops allowing user to interact once card total reaches > 21
    // or they stand.
    public void playersTurn(Player player) {
        System.out.println("Player " + player.getPlayerID() + "\'s turn:");
        System.out.println("Dealers cards: " + dealer.getDealersCards());
        System.out.println("Players cards: " + player.getAllCards());
        while (!player.isStand() && player.handValue() <= 21) {
            displayGameOptions();
            String input = userInput.next();
            input.toLowerCase();
            if (input.equals("h")) {
                playerHit(player);
            } else if (input.equals("d")) {
                playerDouble(player);
            } else if (input.equals("s")) {
                playerStand(player);
            } else {
                System.out.println("Please choose from one of the listed options.");
                displayGameOptions();
            }
        }
    }

    // REQUIRES: non-null player object
    // MODIFIES: player
    // EFFECT: gives player another card and displays all the players current cards
    // gives a message if the hit busts the player
    public void playerHit(Player player) {
        player.hitCard();
        System.out.println("Hit!");
        System.out.println("Cards: " + player.getAllCards());
        if (player.handValue() > 21) {
            System.out.println("Bust!");
        }
        System.out.println("");
    }

    // REQUIRES: non-null player object
    // MODIFIES: player
    // EFFECT: gives player another card, doubles their bet,
    // and displays all the players current cards
    // gives a message if the hit busts the player
    public void playerDouble(Player player) {
        player.playerDouble();
        System.out.println("Double!");
        System.out.println("Cards: " + player.getAllCards());
        if (player.handValue() > 21) {
            System.out.println("Bust!");
        }
        System.out.println("");
    }

    // REQUIRES: non-null player object
    // MODIFIES: player
    // EFFECT: changes the player to be standing and shows all their cards
    public void playerStand(Player player) {
        player.setStand();
        System.out.println("Stand!");
        System.out.println("Cards: " + player.getAllCards());
        System.out.println("");
    }

    // REQUIRES: numPlayers >= 1, startingMoney >= 1
    // MODIFIES: ListOfPlayers
    // EFFECTS: Starts the game and sets up the number of players, balance for each player,
    // and initializes the dealer
    public void preGameSetUp() {
        System.out.println("Welcome To BlackJack!");
        System.out.println("This BlackJack table pays 2:1 \n");
        System.out.println("Please enter how many players are playing (Recommended size to be less then 6)");
        int numPlayers = 0;
        while (numPlayers < 1) {
            numPlayers = userInput.nextInt();
            if (numPlayers < 1) {
                System.out.println("Please enter valid number");
            }
        }
        System.out.println("You have selected " + numPlayers + " players \n");
        System.out.println("Please enter how much starting balance to give to each player");
        int startingMoney = 0;
        while (startingMoney < 1) {
            startingMoney = userInput.nextInt();
            if (startingMoney < 1) {
                System.out.println("Please enter valid number");
            }
        }
        System.out.println("You have given everyone $" + startingMoney + " to start with! \n");
        listOfPlayers = new ListOfPlayers(numPlayers, startingMoney);
        dealer = new Dealer();
    }

    // MODIFIES: dealer, player
    // EFFECTS: starts the game of blackjack off by giving the deal 2 cards, 1 face up and another face down, then
    // gives every player 2 cards and displays the current state of the game.
    public void beginRound() {
        System.out.println("Dealers turn!");
        System.out.println("");
        dealer.startingDealerTurn();
        System.out.println("Dealer draws: " + dealer.getDealersCards());
        System.out.println("");
        System.out.println("Now drawing 2 cards for each player:");
        System.out.println("");
        for (int i = 0; i < listOfPlayers.getPlayers().size(); i++) {
            listOfPlayers.getPlayers(i).hitCard();
            listOfPlayers.getPlayers(i).hitCard();
        }
        System.out.println(listOfPlayers.getAllPlayersCards());
        System.out.println("");
    }

    // EFFECTS: displays the menu options
    public void displayMenuOptions() {
        System.out.println("Press \"p\" to play");
        System.out.println("Press \"q\" to quit");
        System.out.println("Press \"b\" to add balance to a player");
        System.out.println("Press \"n\" to add a new player");
        System.out.println("Press \"s\" to view the scoreboard");
    }

    // EFFECTS: displays the game options
    public void displayGameOptions() {
        System.out.println("Press \"h\" to hit");
        System.out.println("Press \"s\" to stand");
        System.out.println("Press \"d\" to double");
    }
}
