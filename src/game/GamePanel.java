package game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {


    int ballPosx = 300, ballPosy = 300;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        /*ball*/
        Graphics ball = g;
        ball.setColor(Color.getHSBColor((System.currentTimeMillis() % 10000)/10000, 0.9F, 0.9F));
        ball.fillOval(300, 300, 50 ,50);

        /*bumper*/
        Graphics bumper = g;
        bumper.setColor(Color.BLUE);
        bumper. fillRect(100, 50, 20, 40);

    }
}
