package game;

import game.GameFrame;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;

public class main{


    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameFrame();
            }
        });

    }
}
