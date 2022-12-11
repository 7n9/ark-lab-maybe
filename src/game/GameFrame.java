package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameFrame extends JFrame implements KeyListener {

    GameFrame(){
        ballPosX = 300;
        ballPosY = 300;
        gamePanel = new GamePanel();
        block = new Block[2000];
        gameOver = false;
        n = 0;

        add(gamePanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        /*set dimentions*/
        setSize(1000,700);
        /*center the window*/
        setLocationRelativeTo(null);
        /*display on screen*/
        setVisible(true);
        addKeyListener(this);

        /*add blocks*/
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 10; j++) {
                block[n] = new Block();
                block[n].setShapeColor(new Random().nextInt(6) + 1);
                block[n].posX = i * 45;
                block[n].posY = j * 25;
                n++;
            }
        }



        ActionListener listener = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    //todo ball detections
                    //todo ball begin
                    ballPosX += ballMotionX;
                    ballPosY += ballMotionY;
                    //todo collisions with blocks
                }
                gamePanel.repaint();
            }
        };
        Timer timer = new Timer(50, listener);
        timer.start();
    }




    public static int ballPosX;
    public static int ballPosY;
    private static GamePanel gamePanel;
    public static Block[] block;
    public static int n;

    public int ballMotionX = 5, ballMotionY = 5;
    private boolean gameOver;


    /*keyListener implement methods*/
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //todo bumper movement
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
