package sw.java.elk.test;

public class Player {
    public Player(String name){
        this.name=name;
    }
    public String name;
    public Integer source  = Integer.valueOf(0);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
