package graphics;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.BorderLayout;
import java.awt.BasicStroke;


public class Chart extends Canvas {
    private JFrame window;
    private final int WIDTH;
    private final int HEIGHT;
    private final int MARGIN_H = 300;
    private final int MARGIN_V = 100;
    private Scroller scroller = new Scroller(0, 30, 0, 20, 0);
    private static Line[] lines = new Line[0];
    private static String[] lineNames = new String[0];
    private static Color[] lineNamesColor = new Color[0];


    private Panel[] panels;
    private Panel topPanel;
    private Panel bottomPanel;
    private Panel leftPanel;
    private Panel rightPanel;
    private Panel chartPanel;


    private final int chartMarginH = 70;
    private final int chartMarginV = 30;
    private final int hLines;



    public Chart(String n, int w, int h){

        setPreferredSize(new Dimension(w,h));
        window = new JFrame(n);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setLayout(new BorderLayout());
        window.add(this, BorderLayout.CENTER);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        
        WIDTH = w;
        HEIGHT = h;
        hLines = (int)Math.floor((float)(h-2*MARGIN_V-2*chartMarginV)/ 10.0f);
        //Set Panels
        topPanel = new Panel(MARGIN_H, 0, WIDTH- 2*MARGIN_H, MARGIN_V, new Color(230,230,230));
        bottomPanel = new Panel(MARGIN_H, HEIGHT-MARGIN_V, WIDTH - 2*MARGIN_H, MARGIN_V, new Color(230,230,230));
        leftPanel = new Panel(0, 0, MARGIN_H, HEIGHT, new Color(230,230,230));
        rightPanel = new Panel(WIDTH - MARGIN_H, 0, MARGIN_H, HEIGHT, new Color(230,230,230));
        chartPanel = new Panel(MARGIN_H, MARGIN_V, WIDTH - 2*MARGIN_H, HEIGHT - 2*MARGIN_V, new Color(245,245,245));
        
        bottomPanel.addObject(new Text(5, 20, "Scroller:"));
        bottomPanel.addObject(scroller);

        leftPanel.addObject(new Text(10, 20, "Author: Marc Blanc Sans"));
        leftPanel.addObject(new Text(10, 40, "Free usage & distribution"));
        leftPanel.addObject(new Text(10, 60, "contact: marcbs444@gmail.com"));

        panels = new Panel[5];
        panels[0] = topPanel;       //Make enum hardly recomendated
        panels[1] = bottomPanel;
        panels[2] = leftPanel;
        panels[3] = rightPanel;
        panels[4] = chartPanel;
        
        //Chart settings
        

    }

    public Panel[] getPanels(){
        return panels;
    }

    public static void addLine(Line line){
        Line[] newArray = new Line[lines.length + 1];
        System.arraycopy(lines, 0, newArray, 0, lines.length);
        newArray[newArray.length-1] = line; 
        lines = newArray;
    
    }

    public static void addLineName(String src, Color color) {
        String[] newArray = new String[lineNames.length + 1];
        System.arraycopy(lineNames, 0, newArray, 0, lineNames.length);
        newArray[newArray.length-1] = src; 
        lineNames = newArray;
        
        Color[] newArray2 = new Color[lineNamesColor.length + 1];
        System.arraycopy(lineNamesColor, 0, newArray2, 0, lineNamesColor.length);
        newArray2[newArray.length-1] = color; 
        lineNamesColor = newArray2;
    }

    public void draw(){
        BufferStrategy strat = getBufferStrategy();

        if(strat == null){
            createBufferStrategy(2);
            return;
        }

        Graphics g2 = strat.getDrawGraphics();
        Graphics2D g = (Graphics2D) g2;

        g.clearRect(0, 0, WIDTH, HEIGHT);
        //borders
        //draw panels
        for (int i = 0; i < panels.length; i++) {
            g.setColor(panels[i].color);
            g.fillRect(panels[i].x, panels[i].y, panels[i].w, panels[i].h);   
        }
        //draw objects of all panels
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].objects.length; j++) {
                String objectClass = panels[i].objects[j].getClass().getName();
                if(objectClass == "graphics.Scroller"){
                    Scroller o = (Scroller)panels[i].objects[j];
                    g.setColor(new Color(180,180,180));
                    g.fillRect(panels[i].x + o.x, panels[i].y + o.y, o.w == 0 ? panels[i].w : o.w, o.h);
                    g.setColor(new Color(150,150,150));
                    g.fillRect(panels[i].x + o.x + o.subx, panels[i].y + o.y , 40, 20);

                } else if(objectClass == "graphics.Text"){
                    Text o = (Text)panels[i].objects[j];
                    g.setColor(new Color(10,10,10));
                    g.drawString(o.text, o.x + panels[i].x, o.y + panels[i].y);

                }
            } 
        }

        //Draw Chart :)!

        //Lines default
        g.setColor(new Color(180,180,180));
        g.setStroke(new BasicStroke(1));
        for (int i = 1; i < 11; i++) {
            
            g.drawLine(panels[4].x + chartMarginH, panels[4].y + panels[4].h - hLines*i - chartMarginV, panels[4].x + panels[4].w - chartMarginH, panels[4].y + panels[4].h - hLines*i - chartMarginV); 
        }

        g.setColor(new Color(80,80,80));
        g.setStroke(new BasicStroke(2));
        g.drawLine(panels[4].x + chartMarginH, panels[4].y + chartMarginV, panels[4].x + chartMarginH, panels[4].y + panels[4].h - chartMarginV);
        g.drawLine(panels[4].x + chartMarginH, panels[4].y + panels[4].h - chartMarginV, panels[4].x + panels[4].w - chartMarginH, panels[4].y + panels[4].h - chartMarginV);
        
        
        int spacing = (int)Math.floor((panels[4].w-2*chartMarginH)/20);
        int scrollerIndex = (int)Math.floor((float)(scroller.subx*1000)/(float)(scroller.w == 0 ? panels[1].w : scroller.w)); //fix this!
        
        //lineNames
        for (int i = 0; i < lineNames.length; i++) {
            g.setColor(lineNamesColor[i]);
            g.fillRect(panels[3].x + 20,panels[3].y + 300+i*20, 10, 10);
            g.setColor(new Color(20,20,20));

            g.drawString(lineNames[i], panels[3].x + 35,panels[3].y + 308+i*20);
        }
        
        //Y-numbers edit ns
        for (int i = 0; i < 11; i++) {
            int str = (int)Math.floor((float)Line.max/(float)hLines);
            int x = chartPanel.x + 5;
            int y = chartPanel.y + chartPanel.h - chartMarginV - i*hLines;
            g.drawString(Integer.toString(str*i) + "ns", x, y); 
        }

        //X-Numbers
        for (int j = scrollerIndex; j < scrollerIndex+20; j++) {
            int y = panels[4].y + panels[4].h - chartMarginV + 15;
            int x = panels[4].x + chartMarginH + (j-scrollerIndex)*spacing;
            g.drawString(Integer.toString(j), x, y);
 
        //Draw Lines

        }
        g.setStroke(new BasicStroke(1));
        for (int i = 0; i < lines.length; i++) {
            g.setColor(lines[i].color);
            for (int j = scrollerIndex; j < scrollerIndex+20; j++) {
                int y = lines[i].vertex[j];
                int x = panels[4].x + chartMarginH + (j-scrollerIndex)*spacing;
                int yFixed = panels[4].y + panels[4].h  - chartMarginV - (int)(Math.floor(((float) y/(float)Line.max)*(float)(HEIGHT-2*MARGIN_V-2*chartMarginV)));
                int y2 = lines[i].vertex[j+1];
                int x2 = panels[4].x + chartMarginH + (j-scrollerIndex+1)*spacing;
                int yFixed2 = panels[4].y + panels[4].h  - chartMarginV - (int)(Math.floor(((float) y2/(float)Line.max)*(float)(HEIGHT-2*MARGIN_V-2*chartMarginV)));
                g.drawLine(x, yFixed, x2, yFixed2);

            }
        }
        
       strat.show();
    }


}


