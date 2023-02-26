package model;

import java.util.ArrayList;

public class ListOfPlayers {
    private ArrayList<Player> players;

    public ListOfPlayers(int numPlayers, int startingBalance) {
        players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(startingBalance));
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getPlayers(int index) {
        return players.get(index);
    }

    public String getAllPlayersCards() {
        String allCards = "";
        for (Player player: players) {
            allCards += "Player " + player.getPlayerID() + " has "
                + player.getAllCards();
        }
        return allCards;
    }
}
