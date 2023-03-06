package model;

import java.util.ArrayList;

public class ListOfGamblers {
    private ArrayList<Gambler> gamblers;

    // REQUIRES: numGamblers >= 1, startingBalance >= 1
    // EFFECTS: Creates an array of gamblers with the number of gamblers being the given numGamblers
    // and every gambler has the same balance startingBalance
    public ListOfGamblers(int numGamblers, int startingBalance) {
        gamblers = new ArrayList<>();
        for (int i = 0; i < numGamblers; i++) {
            gamblers.add(new Gambler(startingBalance));
        }
    }

    public ArrayList<Gambler> getGamblers() {
        return gamblers;
    }

    // REQUIRES: index >= 0 and < gamblers.size()
    // EFFECTS: gets the gambler in the listofgamblers at the given index
    public Gambler getGamblers(int index) {
        return gamblers.get(index);
    }

    // EFFECTS: returns a string of each gamblers cards
    public String getAllGamblersCards() {
        String allCards = "";
        for (Gambler gambler : gamblers) {
            allCards = allCards + "Player " + gambler.getGamblerID() + " has "
                + gambler.getAllCards();
        }
        return allCards;
    }
}
