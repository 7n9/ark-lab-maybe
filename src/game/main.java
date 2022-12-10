package game;

import javax.swing.JFrame;

public class main extends JFrame{


    public main(){
        initComponents();


    }

    public static void main(String[] args) {
        tickThread = new ThreadRunTick();
        tickThread.start();
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
