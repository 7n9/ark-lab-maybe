package game;

import javax.swing.*;
import java.awt.*;

import static game.GameFrame.ballPosX;
import static game.GameFrame.ballPosY;

public class GamePanel extends JPanel {



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color rainbow = new Color(Color.HSBtoRGB((System.currentTimeMillis() % 1000L) / 1000.0F, 0.55F, 0.95F), false);
        Color darkerRainbow = new Color(Color.HSBtoRGB((System.currentTimeMillis() % 1000L) / 1000.0F, 0.55F, 0.55F), false);
        g.setColor(rainbow);

        g.fillRect(0, 0, 1000, 700);
        g.setColor(Color.BLACK);
        g.fillRect(2, 2, 996, 696);

        /*ball*/
        Graphics ball = g;
        ball.setColor(Color.RED);
        ball.fillOval(ballPosX, ballPosY, 20 ,20);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.WHITE);
        g.drawString("Your score : " + GameFrame.score, 20, 20);


        /*bumper*/
        Graphics bumper = g;
        bumper.setColor(Color.BLUE);
        //bumper positioning
        int passBumperX = GameFrame.bumperX;
        if (passBumperX > 896)
            GameFrame.bumperX = 896;
        if (passBumperX < 2)
            GameFrame.bumperX = 2;
        bumper.fillRect(passBumperX, 600, 100, 20);

        /*blocks*/
        Graphics block = g;
        for (int i = 0; i < GameFrame.blockCount; i++) {
            Block gBlock = GameFrame.block[i];
            block.setColor(gBlock.getShapeColor());
            block.fillRect(gBlock.posX , gBlock.posY, gBlock.blockWidth, gBlock.blockHeight);
            block.setColor(darkerRainbow);
            block.drawRect(gBlock.posX, gBlock.posY, gBlock.blockWidth, gBlock.blockHeight);
        }

    }

    public Dimension getPreferredSize() {
        return new Dimension(1000, 700);
    }
}
