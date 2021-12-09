package storage;

import java.io.File;  
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private int[] ping;

    public Storage(){
        ping = new int[0];
    }

    public void addPing(int ping){
        int[] newArray = new int[this.ping.length + 1];
        System.arraycopy(this.ping, 0, newArray, 0, this.ping.length);
        newArray[newArray.length - 1] = ping;
        this.ping = newArray;

    }

    public int[] getPingArray(){
        return this.ping;
    }

    public void save(){

        try {
            File file = new File("testTCP1.txt");
            if (file.createNewFile()) {
              System.out.println("File created: " + file.getName());
            } else {
              System.out.println("File already exists.");
            }

            FileWriter writer = new FileWriter("testTCPLatencyStrange.txt");

            writer.write("[new]\r\n");
            int[] avgArray = new int[20];
            int avg = 0;
            for (int i = 0; i < ping.length; i++) {
                writer.write(Integer.toString(ping[i]) + "\r\n");
                avgArray[i%20] = ping[i];
                if (0 == i % 20) { //avgArray plena
                    int result = 0;
                    
                    for (int j = 0; j < avgArray.length; j++) {
                        result += avgArray[j];                     
                    }
                    result = result/ping.length;
                    avg += result; 
                }
            }
            writer.write("//average of "+Integer.toString(avg)+"ns");
            System.out.println(avg);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        

    }
    
}
