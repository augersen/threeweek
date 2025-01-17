package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class Controller implements KeyListener{
    public boolean aPressed,dPressed, shiftPressed, leftArrowPressed, rightArrowPressed;

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_A){
            this.aPressed = true;

        }
        if(code == KeyEvent.VK_D){
            this.dPressed = true;
        }

        if(code == KeyEvent.VK_SHIFT){
            this.shiftPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            this.leftArrowPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            this.rightArrowPressed = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_A){
            this.aPressed = false;
        }
        if(code == KeyEvent.VK_D){
            this.dPressed = false;
        }
        if(code == KeyEvent.VK_SHIFT){
            this.shiftPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            this.leftArrowPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            this.rightArrowPressed = false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}
