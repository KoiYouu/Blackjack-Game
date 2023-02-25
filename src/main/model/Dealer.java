package model;

import java.util.ArrayList;

public class Dealer extends BlackJack {
    public Dealer() {
        super();
    }

    public void dealersTurn() {
        this.hand.get(1).setCardFaceUp();
    }
}
