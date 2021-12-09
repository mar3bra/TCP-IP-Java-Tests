package clients;
import java.net.InetAddress;

public class Client {
    
    public final InetAddress IP; 
    public int[] ping;

    public Client(InetAddress ip){
        this.IP = ip;
        this.ping = new int[0];
    }
    
}
