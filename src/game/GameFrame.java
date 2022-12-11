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
        score = 0;
        random = new Random(System.currentTimeMillis());
        ballMotionX = 5;
        ballMotionY = 5;
        bumperX = 450;


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
        for (int i = 1; i <= 18; i++) {
            for (int j = 1; j <= 10; j++) {
                block[blockCount] = new Block();
                block[blockCount].setShapeColor(random.nextInt(6) + 1);
                block[blockCount].posX = -48 + i * 55;//48.5, half a pixel diff?
                block[blockCount].posY = 30 + j * 25;
                blockCount++;
            }
        }



        ActionListener listener = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    //todo ball detections
                    if(ballPosX <2){
                        ballMotionX = -ballMotionX;
                    }
                    if(ballPosX + 20 > 996){
                        ballMotionX = -ballMotionX;
                    }
                    if(ballPosY < 0){
                        ballMotionY = -ballMotionY;
                    }
                    if(ballPosY + 20 > 700){
                        gameOver = true;
                    }
                    //todo ball begin
                    //todo random ball start?

                    ballPosX += ballMotionX;
                    ballPosY -= ballMotionY;
                    //todo collisions with blocks
                    //todo maybe add sidehit to blocks?

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
        Timer timer = new Timer(20, listener);
        timer.start();
        pack();
    }


    /*block collidion check*/
    private boolean isBallCollidedWithBlock(Block block){
        if(ballPosY >= block.posY && ballPosY < block.posY + block.blockHeight && ballPosX > block.posX && ballPosX < block.posX + block.blockWidth){
            score += 1;
            return true;
        }else{
            return false;
        }
    }
    /*bumper collision check*/
    private boolean isBallCollidedWithBumper(){
        //bumperx
        //ballwidth&height = 20
        if(ballPosX + 20 >= bumperX && ballPosX < bumperX + 100 && ballPosY + 20 > 600 && ballPosY < 620){
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
            if (bumperX > 2) {
                bumperX -= 10;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if (bumperX < 896) {
                bumperX += 10;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public static int ballPosX;
    public static int ballPosY;
    private final GamePanel gamePanel;
    public static Block[] block;
    public static int blockCount;
    public static int bumperX;
    public static int score;

    private Random random;
    private int ballMotionX, ballMotionY;
    private boolean gameOver;
}
