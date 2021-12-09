import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import graphics.Chart;
import graphics.Line;

import mouse.Mouse;

public class App implements Runnable{

    private Boolean engineOn = true;
    private final String NAME = "Charts <3 by Marc Blanc Sans";
    private final int WIDTH = 1400;
    private final int HEIGHT = 800;
    private Thread thread;
    private Chart chart;
    private Line file = new Line("testTCPLatency1");
    private Line file2 = new Line("testTCPLatency2");
    private Line file3 = new Line("testTCPLatency3");



    
    

    private App(){
        thread = new Thread(this,"all");
        chart = new Chart(NAME, WIDTH, HEIGHT);
        chart.addMouseListener(new Mouse(chart.getPanels()));

    }

    public static void main(String[] args) throws Exception {
        System.out.println("Starting");
        App app = new App();
        app.thread.start();
    }
    

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("Thread initialized");
        long nanoTime = System.nanoTime();
        long delta = 0;
        final int nano = 1_000_000_000;
        int aps = nano / 20;
        
        
        while (engineOn){
            //El codi de repetició de conexió va aquí
            long startNanoTime = System.nanoTime();
            delta += startNanoTime - nanoTime;
            nanoTime = startNanoTime;
            
            if (delta >= aps){
                
            }
            chart.draw();

        }
    }
}
