package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class BlackJackTest {

    protected BlackJack blackjack;

    @Test
    void testConstructor() {
        assertFalse(blackjack.isStand());
        assertEquals(0, blackjack.hand.size());
    }

    @Test
    void testHitCard() {
        blackjack.hitCard();
        int randNum = blackjack.hand.get(0).getRandNum();
        assertEquals(Cards.getValues(randNum), blackjack.hand.get(0).getValue());
        assertEquals(Cards.getDeck(randNum), blackjack.hand.get(0).getCardName());
        assertEquals(Cards.getSuits(randNum % 4), blackjack.hand.get(0).getSuit());
    }

    @Test
    void testClearHand(){
        blackjack.hitCard();
        blackjack.hitCard();
        assertEquals(2, blackjack.getHand().size());
        blackjack.clearHand();
        assertEquals(0, blackjack.getHand().size());
    }

    @Test
    void testSetStandAndNotStand() {
        assertFalse(blackjack.isStand());
        blackjack.setStand();
        assertTrue(blackjack.isStand());
        blackjack.setNotStand();
        assertFalse(blackjack.isStand());
    }

    @Test
    void testGetCard() {
        blackjack.hitCard();
        int randNum = blackjack.hand.get(0).getRandNum();
        String cardInfo = "[Name: " + Cards.getDeck(randNum)
                + " Suit: " + Cards.getSuits(randNum % 4) + "]";
        assertEquals(cardInfo ,blackjack.getCards(0));
    }

    @Test
    void testHandValue1Card() {
        blackjack.hitCard();
        int randNum = blackjack.hand.get(0).getRandNum();
        assertEquals(Cards.getValues(randNum), blackjack.handValue());
    }

    @Test
    void testHandValueMultipleCards() {
        blackjack.hitCard();
        blackjack.hitCard();
        blackjack.hitCard();
        int randNum1 = blackjack.hand.get(0).getRandNum();
        int randNum2 = blackjack.hand.get(1).getRandNum();
        int randNum3 = blackjack.hand.get(2).getRandNum();
        int totalValue = Cards.getValues(randNum1) + Cards.getValues(randNum2) + Cards.getValues(randNum3);
        assertEquals(totalValue, blackjack.handValue());
    }
}
