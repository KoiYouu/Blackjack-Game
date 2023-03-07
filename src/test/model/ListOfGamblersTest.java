package model;

import org.json.JSONObject;
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
    void testGetPlayers() {
        assertEquals(45, listOfGamblers.getGamblers(0).getGamblerID());
        assertEquals(46, listOfGamblers.getGamblers(1).getGamblerID());
    }

    @Test
    void testGetAllPlayersCards() {
        listOfGamblers.getGamblers(0).hitCard();
        listOfGamblers.getGamblers(1).hitCard();
        int randNum1 = listOfGamblers.getGamblers(0).hand.get(0).getRandNum();
        int randNum2 = listOfGamblers.getGamblers(1).hand.get(0).getRandNum();
        String compare1 = "Player 43 has " + " [Name: " + Cards.getDeck(randNum1 % 13)
                + " Suit: " + Cards.getSuits(randNum1 % 4) + "]";
        String compare2 = " Player 44 has " + " [Name: " + Cards.getDeck(randNum2 % 13)
                + " Suit: " + Cards.getSuits(randNum2 % 4) + "] ";
        String compare = compare1 + compare2;
        assertEquals(compare, listOfGamblers.getAllGamblersCards());
    }

    @Test
    void testToJson() {
        listOfGamblers.getGamblers(0).addDraw();
        listOfGamblers.getGamblers(0).addLoss();
        listOfGamblers.getGamblers(0).addWin();
        listOfGamblers.getGamblers(0).setBalance(500);
        listOfGamblers.getGamblers(1).addLoss();
        listOfGamblers.getGamblers(1).addLoss();
        listOfGamblers.getGamblers(1).addLoss();
        String jsonObject = listOfGamblers.toJson().toString();
        assertEquals("{\"gambler\":[{\"wins\":1,\"balance\":500,\"draws\":1,\"gamblerID\":47,\"losses\":1},"
                + "{\"wins\":0,\"balance\":1000,\"draws\":0,\"gamblerID\":48,\"losses\":3}]}", jsonObject);
    }
}
