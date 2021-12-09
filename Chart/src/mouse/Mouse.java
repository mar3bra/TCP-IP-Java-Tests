package mouse;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import graphics.Panel;
import graphics.Scroller;

public class Mouse implements MouseInputListener {
    private Panel[] panels;
    public Mouse(Panel[] panels){
        this.panels = panels;
    }

    //FOR A BETTER RENDIMENT OF APP IS NECESSARY TO CATCH PANELS WHICH REQUIRE MOUSE CLICKS ONLY ONE TIME!!! 

    @Override
    public void mouseClicked(MouseEvent e) {
        
        // TODO Auto-generated method stub

        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].objects.length; j++) { //not need to iterate each panel every time :(
                String objectClass = panels[i].objects[j].getClass().getName();
                if(objectClass == "graphics.Scroller"){
                    Scroller o = (Scroller)panels[i].objects[j];
                    if(o.x + panels[i].x < e.getX() && o.x + panels[i].x + (o.w == 0 ? panels[i].w : o.w) > e.getX() && o.y + panels[i].y < e.getY() && panels[i].y + o.y + o.h > e.getY()){
                        o.subx = (e.getX() - (o.x + panels[i].x) - 20) < 0 ? 0 : e.getX() - (o.x + panels[i].x) - 20;
                    }
        
                }
            }
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
        
    }
    
}
