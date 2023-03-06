package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfGamblersTest {

    private ListOfGamblers listOfGamblers;

    @BeforeEach
    void runBefore() {
        listOfGamblers = new ListOfGamblers(2, 1000);
    }

    @Test
    void testConstructor() {
        assertEquals(2, listOfGamblers.getGamblers().size());
        assertEquals(1000, listOfGamblers.getGamblers(0).getBalance());
        assertEquals(1000, listOfGamblers.getGamblers(1).getBalance());
    }

    @Test
    void getPlayers() {
        assertEquals(43, listOfGamblers.getGamblers(0).getGamblerID());
        assertEquals(44, listOfGamblers.getGamblers(1).getGamblerID());
    }

    @Test
    void getAllPlayersCards() {
        listOfGamblers.getGamblers(0).hitCard();
        listOfGamblers.getGamblers(1).hitCard();
        int randNum1 = listOfGamblers.getGamblers(0).hand.get(0).getRandNum();
        int randNum2 = listOfGamblers.getGamblers(1).hand.get(0).getRandNum();
        String compare1 = "Player 41 has " + " [Name: " + Cards.getDeck(randNum1 % 13)
                + " Suit: " + Cards.getSuits(randNum1 % 4) + "]";
        String compare2 = " Player 42 has " + " [Name: " + Cards.getDeck(randNum2 % 13)
                + " Suit: " + Cards.getSuits(randNum2 % 4) + "] ";
        String compare = compare1 + compare2;
        assertEquals(compare, listOfGamblers.getAllGamblersCards());
    }
}
