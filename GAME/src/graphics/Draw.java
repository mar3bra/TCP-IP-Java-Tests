package graphics;

import javax.swing.JFrame;

import client.Client;

import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.lang.reflect.WildcardType;

import controllers.Keyboard;
import net.Net;
import client.Client;
import java.awt.Font;

public class Draw extends Canvas{

    private final int WIDTH;
    private final int HEIGHT;
    public int room = 0;
    private JFrame window;
    public String ip = "";
    public int port = 0;
    Keyboard keyboard = new Keyboard();

    public Draw(String n, int w, int h){

        WIDTH = w;
        HEIGHT = h;

        setPreferredSize(new Dimension(w,h));
        setBackground(new Color(10,10,10));
        window = new JFrame(n);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setLayout(new BorderLayout());
        window.add(this, BorderLayout.CENTER);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setResizable(false);

        addKeyListener(keyboard);

    
    }
    public void game(Client[] clients){
        BufferStrategy strat = getBufferStrategy();
        if(strat == null){
            createBufferStrategy(2);
            return;
        }

        Graphics g2 = strat.getDrawGraphics();
        Graphics2D g = (Graphics2D) g2;

        g.clearRect(0, 0, WIDTH, HEIGHT);

        for (int i = 0; i < clients.length; i++) {
            g.setColor(clients[i].COLOR);
            g.fillRect(clients[i].x, clients[i].y, 30, 30);
            g.setColor(new Color(225,225,225));
            g.drawString(clients[i].NAME, clients[i].x - 15, clients[i].y - 10);
        }

        strat.show();
    }

    public void menu(){
        BufferStrategy strat = getBufferStrategy();
        if(strat == null){
            createBufferStrategy(2);
            return;
        }


        Graphics g2 = strat.getDrawGraphics();
        Graphics2D g = (Graphics2D) g2;

        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(new Color(255,255,255));
        g.drawString("Introdueix la IP i el PORT del servidor de la següent manera: ex:. 192.1.1.2,3000", 100, 200);
        g.drawString("Entrada de text:" + keyboard.typing, 100, 250);

        if (keyboard.enterPressed){
            String text = keyboard.typing;
            int num = 0;
            int[] array = new int[2];
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == ':'){
                    array[num] = i;
                    num++;
                }
            }

            for (int j = 0; j < array[0]; j++) {
                ip += text.charAt(j);
            }

            String sport = "";
            for (int j = array[0] + 1; j < array[1]; j++) {
                sport += text.charAt(j);
            }

            String dport = "";
            for (int j = array[1] + 1; j < text.length() - 1; j++) {
                dport += text.charAt(j);
            }

            port = Integer.valueOf(sport);
            room = 1;
            keyboard.enterPressed = false;
            Net.setConfig(ip, port, Integer.valueOf(dport));

        }
        


        strat.show();



    }

}
