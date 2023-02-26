package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DealerTest extends BlackJackTest {

    private Dealer dealer;

    @BeforeEach
    void runBefore() {
        dealer = new Dealer();
        blackjack = new Dealer();
    }

    @Test
    void testConstructor() {
        assertFalse(dealer.isStand());
        assertEquals(0, dealer.hand.size());
    }

    @Test
    void testStartingDealerTurn() {
        dealer.startingDealerTurn();
        assertEquals(2 , dealer.hand.size());
        assertFalse(dealer.hand.get(1).getFacingUp());
        assertTrue(dealer.hand.get(0).getFacingUp());
    }

    @Test
    void testDealersTurn() {
        dealer.startingDealerTurn();
        dealer.dealersTurn();
        for (Cards card: dealer.hand) {
            assertTrue(card.getFacingUp());
        }
        assertTrue(dealer.isStand());
        assertTrue(2 <= dealer.hand.size());
    }

    @Test
    void testGetDealersCardsAFaceDownCard() {
        dealer.startingDealerTurn();
        int randNum = dealer.getHand().get(0).getRandNum();
        String compare = " [Name: " + Cards.getDeck(randNum)
                + " Suit: " + Cards.getSuits(randNum % 4) + "]" + " [X]";
        assertEquals(compare, dealer.getDealersCards());

    }

    @Test
    void testGetDealersCardAllFaceUp() {
        dealer.hitCard();
        dealer.hitCard();
        int randNum1 = dealer.getHand().get(0).getRandNum();
        int randNum2 = dealer.getHand().get(1).getRandNum();
        String compare = " [Name: " + Cards.getDeck(randNum1)
                + " Suit: " + Cards.getSuits(randNum1 % 4) + "]"
                + " [Name: " + Cards.getDeck(randNum2)
                + " Suit: " + Cards.getSuits(randNum2 % 4) + "]";
        assertEquals(compare, dealer.getDealersCards());
    }
}
