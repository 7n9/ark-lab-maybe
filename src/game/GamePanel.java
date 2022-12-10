package game;

import javax.swing.*;
import java.awt.*;

import static game.GameFrame.ballPosX;
import static game.GameFrame.ballPosY;

public class GamePanel extends JPanel {



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 700);
        /*ball*/
        Graphics ball = g;
        ball.setColor(Color.RED);
        ball.fillOval(ballPosX, ballPosY, 30 ,30);

        g.setFont(new Font("MyFont", 20, 20));
        g.setColor(Color.WHITE);
        g.drawString("your score : ", 20, 20);
        /*bumper*/
        Graphics bumper = g;
        bumper.setColor(Color.BLUE);

        int bumperX = 300;

        //bumper positioning
        if (bumperX > 940)
            bumperX = 900;
        if (bumperX < 0)
            bumperX = 0;
        bumper.fillRect(bumperX, 650, 100, 20);

        /*blocks*/
        Graphics block = g;
        for (int i = 0; i < GameFrame.n; i++) {
            block.setColor(GameFrame.block[i].getShapeColor());
            block.fillRect(GameFrame.block[i].posX, GameFrame.block[i].posY, 43, 20);
            block.setColor(Color.red);
            block.drawRect(GameFrame.block[i].posX, GameFrame.block[i].posY, 43, 20);
        }

    }
}
