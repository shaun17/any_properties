package sw.java.elk.test;

import java.util.HashMap;
import java.util.Map;

public class PlayGame {
    public static Map<String,Integer> sources = new HashMap(){{put("player1",0);put("player2",0);}};

    public static String game(Player winer){
        Integer winnerSource = sources.get(winer.getName());
        winnerSource = getSource(winnerSource);
        sources.put(winer.getName(),winnerSource);

        Integer player1Source = sources.get("player1");
        Integer player2Source = sources.get("player2");

        int abs = Math.abs(player1Source - player2Source);
        if(abs==0){
            return "draw";
        }
        if(abs==10){
            return getPoint(player1Source,player2Source);
        }
        if(abs==20){
            return getWinner(player1Source,player2Source);
        }
        return player1Source+":"+player2Source;
    }

    private static Integer getSource(Integer source ){
        if(source<30){
            source+=15;
        }else{
            source+=10;
        }
        return source;
    }

    private static String getPoint(Integer player1Source,Integer player2Source ){
        if( player1Source > player2Source){
            return "point:"+player2Source;
        }
        return player1Source+":point";
    }

    private static String getWinner(Integer player1Source,Integer player2Source ){
        String res;
        if( player1Source > player2Source){
            res =  "win:"+player2Source;
        }else{
            res =  player1Source+":win";
        }
        System.out.println(res);
        System.exit(-1);
        return "";
    }

}
