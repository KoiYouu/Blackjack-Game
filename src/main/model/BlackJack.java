package model;

import java.util.ArrayList;

public class BlackJack {
    protected ArrayList<Cards> hand; // players hand with all its cards
    protected boolean stand; // True if player is standing, false if player is not

    // EFFECT: Super constructor for dealer and player with an empty hand of cards and not standing
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

    // EFFECTS: adds up all cards in hand and returns their total value
    public int handValue() {
        int handValueSoft = 0;
        int handValueHard = 0;
        boolean aceExists = false;
        for (Cards card : hand) {
            handValueHard += card.getValue();
            if (card.getCardName() == "A") {
                aceExists = true;
            }
        }
        handValueSoft = handValueHard + 10;
        if (handValueHard < 12 && aceExists) {
            return handValueSoft;
        } else {
            return  handValueHard;
        }
    }

    // MODIFIES: this
    // EFFECTS: clears hand of all cards
    public void clearHand() {
        hand.clear();
    }

    // REQUIRES index to be >=0 and < hand.size()
    // EFFECTS: returns the info of the card at our given position
    public String getCards(int index) {
        return hand.get(index).getCardInfo();
    }

    // MODIFIES: This
    // EFFECTS: Sets the player to be standing
    public void setStand() {
        stand = true;
    }

    // MODIFIES: This
    // EFFECTS: Sets the player to not be standing
    public void setNotStand() {
        stand = false;
    }


    public ArrayList<Cards> getHand() {
        return hand;
    }

    public boolean isStand() {
        return stand;
    }
}
