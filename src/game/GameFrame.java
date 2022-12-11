package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameFrame extends JFrame implements KeyListener {

    GameFrame(){
        ballPosX = 490;
        ballPosY = 550;
        gamePanel = new GamePanel();
        block = new Block[2000];
        gameOver = false;
        blockCount = 0;
        score = 0;
        random = new Random(System.currentTimeMillis());
        motionModifierRandom = random.nextInt(5) - 2;
        rightleftupdownModifierRandomBool = random.nextBoolean();
        ballMotionX = 5 + motionModifierRandom;
        if(rightleftupdownModifierRandomBool){
            ballMotionX = -ballMotionX;
        }
        ballMotionY = 5 + motionModifierRandom;
        rightleftupdownModifierRandomBool = random.nextBoolean();
        if(rightleftupdownModifierRandomBool){
            ballMotionY = -ballMotionY;
        }
        bumperX = 450;


        /*add panel to frame*/
        add(gamePanel);
        /*self-explanatory*/
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        /*no-resize*/
        setResizable(false);
        /*title*/
        setTitle("ArkQnoid");
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

                    ballPosX += ballMotionX;
                    ballPosY -= ballMotionY;
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
        /*bumper movement*/
        if(!gameOver){
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (bumperX > 2) {
                    bumperX -= 10;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (bumperX < 896) {
                    bumperX += 10;
                }
            }
        }else{
            if (e.getKeyCode() == KeyEvent.VK_Y) {
                ballPosX = 490;
                ballPosY = 550;
                block = new Block[2000];
                blockCount = 0;
                score = 0;
                motionModifierRandom = random.nextInt(5) - 2;
                rightleftupdownModifierRandomBool = random.nextBoolean();
                ballMotionX = 5 + motionModifierRandom;
                if(rightleftupdownModifierRandomBool){
                    ballMotionX = -ballMotionX;
                }
                ballMotionY = 5 + motionModifierRandom;
                rightleftupdownModifierRandomBool = random.nextBoolean();
                if(rightleftupdownModifierRandomBool){
                    ballMotionY = -ballMotionY;
                }
                bumperX = 450;
                /*readd blocks*/
                for (int i = 1; i <= 18; i++) {
                    for (int j = 1; j <= 10; j++) {
                        block[blockCount] = new Block();
                        block[blockCount].setShapeColor(random.nextInt(6) + 1);
                        block[blockCount].posX = -48 + i * 55;
                        block[blockCount].posY = 30 + j * 25;
                        blockCount++;
                    }
                }
                gameOver = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_N) {
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public static int ballPosX;
    public static int ballPosY;
    public static Block[] block;
    public static int blockCount;
    public static int bumperX;
    public static int score;
    public static int motionModifierRandom;
    public static boolean gameOver;

    private boolean rightleftupdownModifierRandomBool;
    private Random random;
    private int ballMotionX, ballMotionY;
    private final GamePanel gamePanel;
}
