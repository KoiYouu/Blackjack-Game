package model;

import java.util.ArrayList;

public class Player extends BlackJack {
    private int playerID; // unique player id
    private int balance; // players balance
    private int wins; // players wins
    private int draws; // players draws
    private int losses; // players losses
    private int bet; // Current bet
    private static int count = 1;

    /*
     * REQUIRES: startingBalance must be > 0
     * EFFECTS: Creates a player with the given starting balance, with an empty hand of cards,
     * a bet of $0, makes the player not standing, no wins / losses / draws, and a unique player ID.
     */
    public Player(int startingBalance) {
        super();
        playerID = count++;
        balance = startingBalance;
        wins = 0;
        draws = 0;
        losses = 0;
        bet = 0;
    }

    // MODIFIES: this
    // EFFECTS: Increment wins by 1
    public void addWin() {
        this.wins += 1;
    }

    // MODIFIES: this
    // EFFECTS: Increment draws by 1
    public void addDraw() {
        this.draws += 1;
    }

    // MODIFIES: this
    // EFFECTS: Increment losses by 1
    public void addLoss() {
        this.losses += 1;
    }

    // REQUIRES: additionalBalance > 0
    // MODIFIES: this
    // EFFECTS: adds additionalBalance to current balance and returns the new balance
    public int addBalance(int additionalBalance) {
        balance += additionalBalance;
        return balance;
    }

    // REQUIRES: removedBalance < 0
    // MODIFIES: this
    // EFFECTS: Takes away removedBalance from balance and returns the new balance
    public int removeBalance(int removedBalance) {
        balance -= removedBalance;
        return balance;
    }

    // REQUIRES: balance >= bet
    // MODIFIES: this
    // EFFECTS: doubles the players bet, takes away the players balance and gives the player another card.
    public Cards playerDouble() {
        setBet(this.bet * 2);
        removeBalance(bet);
        return hitCard();
    }


    // EFFECTS: returns all the players cards in a single string
    public String getAllCards() {
        String cardInfo = "";
        for (Cards card : hand) {
            cardInfo = cardInfo + " " + card.getCardInfo() + " ";
        }
        return cardInfo;
    }

    // Requires bet to be > 0 and <= balance
    // MODIFIES: This
    // EFFECTS: Sets the players bet to the given value and takes away the bet from balance
    public void setBet(int bet) {
        this.bet = bet;
        this.balance -= bet;
    }

    // MODIFIES: this
    // EFFECTS: adds double the players bet to their balance, resets their bet to 0, adds a win, and clears their hand
    public void playerWin() {
        this.balance += this.bet * 2;
        this.bet = 0;
        this.addWin();
        this.hand.clear();
    }

    // MODIFIES: this
    // EFFECTS: returns the players bet, resets their bet to 0, adds a draw, and clears their hand
    public void playerPush() {
        this.balance += this.bet;
        this.bet = 0;
        this.addDraw();
        this.hand.clear();
    }

    // MODIFIES: this
    // EFFECT: player does not get their bet back, resets their bet to 0, adds a loss, and clears their hand
    public void playerLoss() {
        this.bet = 0;
        this.addLoss();
        this.hand.clear();
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getBalance() {
        return balance;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

    public int getBet() {
        return bet;
    }

}
