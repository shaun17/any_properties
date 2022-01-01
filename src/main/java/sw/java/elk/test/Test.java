package sw.java.elk.test;

import java.util.Collection;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {

        System.out.println("12345".substring(5));
        System.out.println("12345".substring(1,2));
        System.out.println("12345".substring(1,3));

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        System.out.println(PlayGame.game(player1));
        System.out.println(PlayGame.game(player2));
        System.out.println(PlayGame.game(player2));
        System.out.println(PlayGame.game(player1));
        System.out.println(PlayGame.game(player1));
        System.out.println(PlayGame.game(player2));
        System.out.println(PlayGame.game(player2));
        System.out.println(PlayGame.game(player1));
        System.out.println(PlayGame.game(player1));
        System.out.println(PlayGame.game(player1));
        System.out.println(PlayGame.game(player1));
        System.out.println(PlayGame.game(player1));
        System.out.println(PlayGame.game(player1));
        StringBuffer s = new StringBuffer();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        Collection<Object> values = objectObjectHashMap.values();

    }
}
