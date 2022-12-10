package game;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;

public class main{


    public static void main(String[] args) {
        tickThread = new ThreadRunTick();
        tickThread.start();
        new GameFrame();
    }

    public static void onTick(){
        System.out.println("TICK!");
        tickCount += 1;
    }

    private void initComponents(){


    }
    private static ThreadRunTick tickThread;
    public static long tickCount = 0;
}
