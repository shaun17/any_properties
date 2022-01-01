package sw.java.elk.kafka;

import org.apache.shiro.crypto.hash.Hash;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static Map<String,Integer> players = new HashMap(){{put("player1",0);put("player2",0);}};
    public static void main(String[] args) {
        System.out.println(getSouce("player1","player2"));
        System.out.println(getSouce("player2","player1"));

    }

    /**
     *  winer is the first args
     * @param winner
     * @param other
     * @return
     */
    public static String getSouce(String winner,String other){
        Integer winerSource = players.get(winner);
        winerSource+=15;
        Integer otherSource = players.get(other);
        if(winner.equals("player1")){
            return winerSource+":"+otherSource;
        }else{
            return otherSource+":"+winerSource;
        }

    }
}
