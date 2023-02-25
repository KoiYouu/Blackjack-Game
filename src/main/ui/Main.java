package ui;

import model.Player;

public class Main {
    public static void main(String[] args) {
        Player player = new Player(1000);
        player.hitCard();
        player.hitCard();
        player.hitCard();
        player.hitCard();
        System.out.println(player.getCards(0));
        System.out.println(player.getCards(1));
        System.out.println(player.getCards(2));
        System.out.println(player.getCards(3));
    }
}
