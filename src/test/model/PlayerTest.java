package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest extends BlackJackTest {

    private Player player;

    @BeforeEach
    void runBefore() {
        player = new Player(1000);
        blackjack = new Player(1000);
    }

    @Test
    void testConstructor() {
        assertEquals(39, player.getPlayerID());
        assertEquals(1000, player.getBalance());
        assertEquals(0, player.getWins());
        assertEquals(0, player.getDraws());
        assertEquals(0, player.getLosses());
        assertEquals(0, player.getBet());
        assertFalse(player.isStand());
        assertEquals(0, player.getHand().size());
    }

    @Test
    void testAddWin() {
        player.addWin();
        assertEquals(1, player.getWins());
    }

    @Test
    void testAddDraw() {
        player.addDraw();
        assertEquals(1, player.getDraws());
    }

    @Test
    void testAddLoss() {
        player.addLoss();
        assertEquals(1, player.getLosses());
    }

    @Test
    void testAddBalance() {
        player.addBalance(1000);
        assertEquals(2000, player.getBalance());
        assertEquals(2420, player.addBalance(420));
    }

    @Test
    void testRemoveBalance() {
        player.removeBalance(400);
        assertEquals(600,player.getBalance());
        assertEquals(0,player.removeBalance(600));
    }

    @Test
    void testPlayerDouble() {
        player.setBet(50);
        assertEquals(50, player.getBet());
        assertEquals(950, player.getBalance());
        player.playerDouble();
        assertEquals(100, player.getBet());
        assertEquals(900, player.getBalance());
        assertEquals(1,player.getHand().size());
    }

    @Test
    void testGetAllCards() {
        player.hitCard();
        player.hitCard();
        int randNum1 = player.getHand().get(0).getRandNum();
        int randNum2 = player.getHand().get(1).getRandNum();
        String compare = " [Name: " + Cards.getDeck(randNum1) +" Suit: "
                + Cards.getSuits(randNum1 % 4) + "]  [Name: "
                + Cards.getDeck(randNum2) + " Suit: "
                + Cards.getSuits(randNum2 % 4) + "] ";
        assertEquals(compare, player.getAllCards());
    }

    @Test
    void testSetBet() {
        player.setBet(150);
        assertEquals(150, player.getBet());
        assertEquals(850, player.getBalance());
    }

    @Test
    void testPlayerWin() {
        player.hitCard();
        player.setStand();
        player.setBet(50);
        assertEquals(1, player.getHand().size());
        assertTrue(player.isStand());
        assertEquals(50, player.getBet());
        assertEquals(950, player.getBalance());
        player.playerWin();
        assertEquals(0, player.getHand().size());
        assertFalse(player.isStand());
        assertEquals(0, player.getBet());
        assertEquals(1050, player.getBalance());
    }

    @Test
    void testPlayerPush() {
        player.hitCard();
        player.setStand();
        player.setBet(50);
        assertEquals(1, player.getHand().size());
        assertTrue(player.isStand());
        assertEquals(50, player.getBet());
        assertEquals(950, player.getBalance());
        player.playerPush();
        assertEquals(0, player.getHand().size());
        assertFalse(player.isStand());
        assertEquals(0, player.getBet());
        assertEquals(1000, player.getBalance());
    }

    @Test
    void testPlayerLose() {
        player.hitCard();
        player.setStand();
        player.setBet(50);
        assertEquals(1, player.getHand().size());
        assertTrue(player.isStand());
        assertEquals(50, player.getBet());
        assertEquals(950, player.getBalance());
        player.playerLoss();
        assertEquals(0, player.getHand().size());
        assertFalse(player.isStand());
        assertEquals(0, player.getBet());
        assertEquals(950, player.getBalance());
    }
}
