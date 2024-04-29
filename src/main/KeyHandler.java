package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, sprint, attack=false, rolling=false,escape;
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W){
            upPressed=true;
        }
        if(code==KeyEvent.VK_S){
            downPressed=true;
        }
        if(code==KeyEvent.VK_A){
            leftPressed=true;
        }
        if(code==KeyEvent.VK_D){
            rightPressed=true;
        }
        if(code==KeyEvent.VK_SHIFT){
            sprint=true;
        }
        if (code==KeyEvent.VK_ENTER){
            attack=true;
        }
        if (code==KeyEvent.VK_SPACE){
            rolling=true;
        }
        if(code==KeyEvent.VK_ESCAPE){
            escape=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W){
            upPressed=false;
        }
        if(code==KeyEvent.VK_S){
            downPressed=false;
        }
        if(code==KeyEvent.VK_A){
            leftPressed=false;
        }
        if(code==KeyEvent.VK_D){
            rightPressed=false;
        }
        if(code==KeyEvent.VK_SHIFT){
            sprint=false;
        }
        if (code==KeyEvent.VK_ENTER){
            attack=false;
        }
        if(code==KeyEvent.VK_ESCAPE){
            escape=false;
        }
    }
}
