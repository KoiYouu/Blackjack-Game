package model;

// represents the dealer in a game of blackjack
public class Dealer extends Player {

    // EFFECTS: Creates a dealer with an empty hand and is not standing
    public Dealer() {
        super();
    }

    // MODIFIES: this
    // EFFECTS: adds 2 starting cards to the dealers hands
    // and sets the 2nd one face down, then returns the cards as a string
    public String startingDealerTurn() {
        hitCard();
        EventLog.getInstance().logEvent(new Event("Added Card to Dealer"));
        hitCard();
        EventLog.getInstance().logEvent(new Event("Added Card to Dealer"));
        this.hand.get(1).setCardFaceDown();
        EventLog.getInstance().logEvent(new Event("Set 2nd Dealer Card to face down"));
        return this.hand.get(0).getCardInfo() + this.hand.get(1).getCardInfo();
    }

    // MODIFIES: this
    // EFFECT: makes the dealer hit until their hand value is >= 17
    // and then makes the dealer stand.
    public void dealersTurn() {
        this.hand.get(1).setCardFaceUp();
        while (this.handValueSoft() <= 17) {
            hitCard();
            EventLog.getInstance().logEvent(new Event("Added Card to Dealer"));
        }
        this.setStand();
        EventLog.getInstance().logEvent(new Event("Set Dealer to Stand"));
    }

    // EFFECT: Returns a string of all the dealers cards
    public String getDealersCards() {
        String dealersCards = "";
        for (Cards card : this.hand) {
            dealersCards = dealersCards + " " + card.getCardInfo();
        }
        return dealersCards;
    }
}
