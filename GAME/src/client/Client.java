package client;


import java.awt.Color; 
import controllers.Keyboard;

public class Client {

    public final String NAME;
    public final Color COLOR;

    public int x;
    public int y;
    //public final Color COLOR;

    public Client(String name, Color color){
        this.NAME = name;
        this.COLOR = color;
        
        this.x = 250;
        this.y = 250;
    }
    public void updatePosition(int x, int y) {
        this.x = x+128;
        this.y = y+128;
    }
}
