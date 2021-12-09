package graphics;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class Line {
    
    public int[] vertex;
    public final Color color;
    private final static Color[] colors = {new Color(255,0,0),new Color(0,255,0),new Color(0,0,255),new Color(0,255,255)};
    private static int i = 0;
    public static int min = 0;
    public static int max = 0;
    
    public Line(String src){
        color = colors[i];
        i++;
        try {
            
            Scanner scanner = new Scanner(new File("src/lines/" + src + ".txt"));
            System.out.println("File Founded");
            int count = 0;
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (s.toCharArray()[0] != '[' && s.toCharArray()[0] != '/'){
                    count++;
                }
            }
            vertex = new int[count];
            count = 0;
            scanner.close();
            scanner = new Scanner(new File("src/lines/"+ src + ".txt"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (s.toCharArray()[0] != '[' && s.toCharArray()[0] != '/'){
                    int v = Integer.valueOf(s);
                    vertex[count] = v;
                    count++;

                    if (v > max)
                        max = v + (int)Math.floor(0.1f*(float)v);
                }
            }
            Chart.addLine(this);
            Chart.addLineName(src, this.color);

            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
