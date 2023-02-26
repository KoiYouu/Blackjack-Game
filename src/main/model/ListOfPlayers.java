package model;

import java.util.ArrayList;

public class ListOfPlayers {
    private ArrayList<Player> players;

    // REQUIRES: numPlayers >= 1, startingBalance >= 1
    // EFFECTS: Creates an array of players with the number of players being the given numPlayers
    // and every player has the same balance startingBalance
    public ListOfPlayers(int numPlayers, int startingBalance) {
        players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(startingBalance));
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    // REQUIRES: index >= 0 and < players.size()
    // EFFECTS: gets the player in the listofplayers at the given index
    public Player getPlayers(int index) {
        return players.get(index);
    }

    // EFFECTS: returns a string of each players cards
    public String getAllPlayersCards() {
        String allCards = "";
        for (Player player: players) {
            allCards = allCards + "Player " + player.getPlayerID() + " has "
                + player.getAllCards();
        }
        return allCards;
    }
}
