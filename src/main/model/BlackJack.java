package model;

import java.util.ArrayList;

public class BlackJack {
    protected ArrayList<Cards> hand; // players hand with all its cards
    protected boolean stand; // True if player is standing, false if player is not

    public BlackJack() {
        hand = new ArrayList<>();
        stand = false;
    }

    // MODIFIES: this
    // EFFECTS: adds a new random card to our hand and returns what card we got
    public Cards hitCard() {
        Cards newCard = new Cards();
        hand.add(newCard);
        return newCard;
    }

    // MODIFIES: this
    // EFFECTS: clears hand of all cards
    public void clearHand() {
        hand.clear();
    }

    // returns the info of the card at our given position
    public String getCards(int index) {
        return hand.get(index).getCardInfo();
    }

    // MODIFIES: This
    // EFFECTS: Sets the player to be standing
    public void setStand() {
        stand = true;
    }

    public ArrayList<Cards> getHand() {
        return hand;
    }

    public boolean isStand() {
        return stand;
    }
}
