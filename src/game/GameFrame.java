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
        blockCount = 0;

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
                block[blockCount] = new Block();
                block[blockCount].setShapeColor(new Random().nextInt(6) + 1);
                block[blockCount].posX = i * 45;
                block[blockCount].posY = j * 25;
                blockCount++;
            }
        }



        ActionListener listener = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    //todo ball detections

                    //todo ball begin
                    ballPosX += ballMotionX;
                    ballPosY -= ballMotionY;
                    //todo collisions with blocks
                    /*block collisions*/
                    for(int i = 0; i < blockCount; i++){
                        if(isBallCollidedWithBlock(block[i])){
                            block[i].posX = -100;
                            ballMotionY = -ballMotionY;
                        }
                    }
                    if(isBallCollidedWithBumper()){
                        ballMotionY = -ballMotionY;
                    }
                    System.out.println(ballPosY);
                }
                gamePanel.repaint();
            }
        };
        Timer timer = new Timer(50, listener);
        timer.start();
    }


    /*block collidion check*/
    private boolean isBallCollidedWithBlock(Block block){
        if(ballPosY >= block.posY && ballPosY < block.posY + block.blockHeight && ballPosX > block.posX && ballPosX < block.posX + block.blockWidth){
            return true;
        }else{
            return false;
        }
    }
    /*bumper collision check*/
    private boolean isBallCollidedWithBumper(){
        //bumperx
        //ballwidth&height = 20
        if(ballPosX + 20 >= bumperX && ballPosX < bumperX + 100 && ballPosY + 20 > 600){
            return true;
        }else{
            return false;
        }
    }

    /*keyListener implement methods*/
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //todo bumper movement
        /*bumper movement*/
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            bumperX -= 5;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            bumperX += 5;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public static int ballPosX;
    public static int ballPosY;
    private static GamePanel gamePanel;
    public static Block[] block;
    public static int blockCount;
    public static int bumperX = 300;

    public int ballMotionX = 5, ballMotionY = 5;
    private boolean gameOver;
}
