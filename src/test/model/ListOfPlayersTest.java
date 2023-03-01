package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfPlayersTest {

    private ListOfPlayers listOfPlayers;

    @BeforeEach
    void runBefore() {
        listOfPlayers = new ListOfPlayers(2, 1000);
    }

    @Test
    void testConstructor() {
        assertEquals(2, listOfPlayers.getPlayers().size());
        assertEquals(1000, listOfPlayers.getPlayers(0).getBalance());
        assertEquals(1000, listOfPlayers.getPlayers(1).getBalance());
    }

    @Test
    void getPlayers() {
        assertEquals(43, listOfPlayers.getPlayers(0).getPlayerID());
        assertEquals(44, listOfPlayers.getPlayers(1).getPlayerID());
    }

    @Test
    void getAllPlayersCards() {
        listOfPlayers.getPlayers(0).hitCard();
        listOfPlayers.getPlayers(1).hitCard();
        int randNum1 = listOfPlayers.getPlayers(0).hand.get(0).getRandNum();
        int randNum2 = listOfPlayers.getPlayers(1).hand.get(0).getRandNum();
        String compare1 = "Player 41 has " + " [Name: " + Cards.getDeck(randNum1)
                + " Suit: " + Cards.getSuits(randNum1 % 4) + "]";
        String compare2 = " Player 42 has " + " [Name: " + Cards.getDeck(randNum2)
                + " Suit: " + Cards.getSuits(randNum2 % 4) + "] ";
        String compare = compare1 + compare2;
        assertEquals(compare, listOfPlayers.getAllPlayersCards());
    }
}
