package graphics;

import java.awt.Color;

public class Panel{
    public final int x;
    public final int y;
    public final int w;
    public final int h;
    public final Color color;
    public Object[] objects = new Object[0];
    
    public Panel(int x, int y, int w, int h, Color color){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }

    public void addObject(Object o){
        Object[] newArray = new Object[objects.length + 1];
        System.arraycopy(objects, 0, newArray, 0, objects.length);
        newArray[newArray.length-1] = o; 
        objects = newArray;
    }
}