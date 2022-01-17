import java.io.IOException;

import javax.sound.midi.Synthesizer;

import client.Client;
import client.Clients;
import controllers.Keyboard;
import graphics.Draw;
import java.awt.Color; 

import net.Net;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;



public class Game implements Runnable{
    private final String IP;
    private final int PORT; 
    private Net net;

    private Thread gameThread;
    private Thread netThread;


    private Draw draw = new Draw("Concepte de videojoc", 1000, 800);
    private Game(){
        IP = "";
        PORT = 0;
        /*
        try {
            net = new Net("localhost", 3050);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */
        net = new Net();
        netThread = new Thread(net, "Net");            
        gameThread = new Thread(this, "game");

        Clients.addClient("lola", (byte)1);



    }


    private void update(){
        System.out.println("g");
        netThread.run(); 
    
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        long nanoTime = System.nanoTime();
        long delta = 0;
        final int nano = 1_000_000_000;
        int i = 100; 
        int aps = nano / i;
        
        
        while (true){
            //El codi de repetició de conexió va aquí
            long startNanoTime = System.nanoTime();
            delta += startNanoTime - nanoTime;
            nanoTime = startNanoTime;
            

            if (draw.room == 0){
                draw.menu();
                
            } else {
                
                if (delta >= aps ){
                    delta -= aps;
                    update();
                                  
                }

                draw.game(Clients.clients);
            }
        }
        
    }


    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.gameThread.start();   
        //game.netThread.start();
  
    }

}


