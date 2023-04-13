package model;

import org.json.JSONObject;

// represents the gambler in a game of blackjack
public class Gambler extends Player {
    private int gamblerID; // unique gambler id
    private int balance; // gamblers balance
    private int wins; // gamblers wins
    private int draws; // gamblers draws
    private int losses; // gamblers losses
    private int bet; // Current bet
    private static int count = 1;

    /*
     * REQUIRES: startingBalance must be > 0
     * EFFECTS: Creates a gambler with the given starting balance, with an empty hand of cards,
     * a bet of $0, makes the gambler not standing, no wins / losses / draws, and a unique gambler ID.
     */
    public Gambler(int startingBalance) {
        super();
        gamblerID = count++;
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

    // REQUIRES: removedBalance > 0
    // MODIFIES: this
    // EFFECTS: Takes away removedBalance from balance and returns the new balance
    public int removeBalance(int removedBalance) {
        balance -= removedBalance;
        return balance;
    }

    // REQUIRES: balance >= bet
    // MODIFIES: this
    // EFFECTS: doubles the gamblers bet, takes away the gamblers balance and gives the gambler another card.
    public void gamblerDouble() {
        addBalance(bet);
        setBet(this.bet * 2);
        EventLog.getInstance().logEvent(new Event("Set Gambler " + this.gamblerID + "'s bet to "
                + this.bet));
        EventLog.getInstance().logEvent(new Event("Removed " + this.bet * 2 + " from Gambler "
                + this.gamblerID + "'s balance"));
        this.hitCard();
    }

    // MODIFIES: this
    // EFFECTS: adds a new random card to our hand and returns what card we got
    @Override
    public Cards hitCard() {
        Cards newCard = new Cards();
        hand.add(newCard);
        EventLog.getInstance().logEvent(new Event("Added card to Gambler " + this.gamblerID));
        return newCard;
    }


    // EFFECTS: returns all the gamblers cards in a single string
    public String getAllCards() {
        String cardInfo = "";
        for (Cards card : hand) {
            cardInfo = cardInfo + " " + card.getCardInfo() + " ";
        }
        return cardInfo;
    }

    // Requires bet to be > 0 and <= balance
    // MODIFIES: This
    // EFFECTS: Sets the gamblers bet to the given value and takes away the bet from balance
    public void setBet(int bet) {
        this.bet = bet;
        EventLog.getInstance().logEvent(new Event("Set Gambler " + this.gamblerID + " to " + this.bet));
        this.balance -= bet;
        EventLog.getInstance().logEvent(new Event("Removed " + bet + " from Gambler " + this.gamblerID
                + "'s balance"));
    }

    // MODIFIES: this
    // EFFECTS: adds double the gamblers bet to their balance, resets their bet to 0, adds a win, clears their hand,
    // and sets them to not stand
    public void gamblerWin() {
        this.balance += this.bet * 2;
        EventLog.getInstance().logEvent(new Event("Added " + this.bet * 2 + " to Gambler "
                + this.gamblerID + "'s balance"));
        this.bet = 0;
        EventLog.getInstance().logEvent(new Event("Set Gambler " + this.gamblerID + " to have 0 bet"));
        this.addWin();
        EventLog.getInstance().logEvent(new Event("Added Win to Gambler " + this.gamblerID));
        this.hand.clear();
        EventLog.getInstance().logEvent(new Event("Set Gambler " + this.gamblerID + "'s hand to be empty"));
        this.setNotStand();
        EventLog.getInstance().logEvent(new Event("Set Gambler " + this.gamblerID + " to be not stand"));
    }

    // MODIFIES: this
    // EFFECTS: returns the gamblers bet, resets their bet to 0, adds a draw, clears their hand,
    // and sets them to not stand
    public void gamblerPush() {
        this.balance += this.bet;
        EventLog.getInstance().logEvent(new Event("Added " + this.bet + " to Gambler "
                + this.gamblerID + "'s balance"));
        this.bet = 0;
        EventLog.getInstance().logEvent(new Event("Set Gambler " + this.gamblerID + " to have 0 bet"));
        this.addDraw();
        EventLog.getInstance().logEvent(new Event("Added Draw to Gambler " + this.gamblerID));
        this.hand.clear();
        EventLog.getInstance().logEvent(new Event("Set Gambler " + this.gamblerID + "'s hand to be empty"));
        this.setNotStand();
        EventLog.getInstance().logEvent(new Event("Set Gambler " + this.gamblerID + " to be not stand"));
    }

    // MODIFIES: this
    // EFFECT: gambler does not get their bet back, resets their bet to 0, adds a loss, clears their hand,
    // and sets them to not stand
    public void gamblerLoss() {
        this.bet = 0;
        EventLog.getInstance().logEvent(new Event("Set Gambler " + this.gamblerID + " to have 0 bet"));
        this.addLoss();
        EventLog.getInstance().logEvent(new Event("Added Loss to Gambler " + this.gamblerID));
        this.hand.clear();
        EventLog.getInstance().logEvent(new Event("Set Gambler " + this.gamblerID + "'s hand to be empty"));
        this.setNotStand();
        EventLog.getInstance().logEvent(new Event("Set Gambler " + this.gamblerID + " to be not stand"));

    }

    public int getGamblerID() {
        return gamblerID;
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

    // MODIFIES: this
    // EFFECTS: sets gamblerID to given ID
    public void setGamblerID(int gamblerID) {
        this.gamblerID = gamblerID;
    }

    // MODIFIES: this
    // EFFECTS: sets wins to given wins
    public void setWins(int wins) {
        this.wins = wins;
    }

    // MODIFIES: this
    // EFFECTS: sets losses to given losses
    public void setLosses(int losses) {
        this.losses = losses;
    }

    // MODIFIES: this
    // EFFECTS: sets draws to given draws
    public void setDraws(int draws) {
        this.draws = draws;
    }

    // MODIFIES: this
    // EFFECTS: sets balance to given balance
    public void setBalance(int balance) {
        this.balance = balance;
    }

    // EFFECTS: creates a json object from gambler data
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("gamblerID", gamblerID);
        json.put("balance", balance);
        json.put("wins", wins);
        json.put("draws", draws);
        json.put("losses", losses);
        return json;
    }

}
