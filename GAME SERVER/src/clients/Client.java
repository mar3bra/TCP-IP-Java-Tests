package clients;
import java.net.InetAddress;

public class Client {
    
    public final InetAddress IP; 
    public final int PORT;
    public final byte COLOR;

    //public int[] ping;
    public final String NICK;
    public int x = 100;
    public int y = 100;
    public int request = 0;



    public Client(InetAddress ip, int port, String nick, byte color){
        this.IP = ip;
        //this.ping = new int[0];
        this.PORT = port;
        this.NICK = nick;
        this.COLOR = color;
        
    }

    public void movePlayer(Boolean left, Boolean right, Boolean up, Boolean down){

        
        if (left == true){
            x-=2;

        }

        if (right == true){
            x+=2;
  
        }

        if (up == true){
            y-=2;

        }

        if (down == true){
            y+=2;

        }
    }
    
}
