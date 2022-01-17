package controllers;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Keyboard implements KeyListener{

    static public Boolean[] keys = new Boolean[250];
    static public String typing = ""; 
    static public Boolean enterPressed = false;
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyChar() == '\n'){
            enterPressed = true;
        }
        typing += e.getKeyChar();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyChar() < 250){
            keys[e.getKeyChar()] = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyChar() < 250){
            keys[e.getKeyChar()] = false;
        }
        
    }
    
}
