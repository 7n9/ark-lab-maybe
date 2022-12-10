package game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements KeyListener {

    GameFrame(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*set dimentions*/
        setSize(200,300);

        setLayout(null);

        /*add key listener as this gameframe*/
        addKeyListener(this);

        /*center the window*/
        setLocationRelativeTo(null);

        /*display on screen*/
        setVisible(true);
    }


    /*KeyListener implemented methods*/
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
