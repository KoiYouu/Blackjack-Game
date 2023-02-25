package model;

import java.util.Random;

public class Cards {
    private String cardName; // name of the card ex. A, 2, 3, ..., J, Q, K
    private int value; // value of the card in a blackjack game
    private String suit; // suit of the card
    private boolean facingUp; // true if card is face up, false if it is face down

    private final String[] deck = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" }; // possible cards
    private final int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11}; // value of cards
    private final String[] suits = {"C", "D", "H", "S"}; // possible suits

    private Random random = new Random();

    // EFFECTS: Creates a playing card with a random cardName and its corresponding value, random suit,
    // and keeps it face up.
    public Cards() {
        int randNum = random.nextInt(deck.length);
        cardName = deck[randNum];
        value = values[randNum];
        suit = suits[random.nextInt(suits.length)];
        facingUp = true;
    }

    // MODIFIES: this
    // EFFECTS: sets card to be face down
    public void setCardFaceDown() {
        facingUp = false;
    }

    // MODIFIES: this
    // EFFECTS: sets card to be face up
    public void setCardFaceUp() {
        facingUp = true;
    }

    // EFFECTS: return the cards info as a string
    public String getCardInfo() {
        if (this.facingUp) {
            return "[Name: " + this.getCardName()
                     + " Suit: " + this.getSuit() + " ]";
        } else {
            return "[X]";
        }
    }

    public int getValue() {
        return value;
    }

    public String getCardName() {
        return cardName;
    }

    public String getSuit() {
        return suit;
    }

    public boolean isFacingUp() {
        return facingUp;
    }


}
