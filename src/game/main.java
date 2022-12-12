package game;

import game.GameFrame;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main{


    public static void main(String[] args) {
        renderGlobal = new RenderGlobal();

        initDisplay();
        initGL();

        arqLoop();

//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new GameFrame();
//            }
//        });
        cleanUP();
    }



    private static void arqLoop(){
        while(!Display.isCloseRequested()){
            getInput();
            update();
            render();

        }
    }

    private static void getInput() {
    }

    private static void update() {
    }

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();

        glColor3f(0.25f, 0.5f, 0.2f);

        renderGlobal.drawRect(20, 20, 10, 100);

        Display.update();
    }

    private static void initGL() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glClearColor(0, 0, 0, 1);
        glDisable(GL_DEPTH_TEST);

    }

    private static void initDisplay() {
        try{
            Display.setDisplayMode(new DisplayMode(1000, 700));
            Display.setResizable(false);
            Display.create();
            Display.setVSyncEnabled(true);
        }catch(Exception e){
            //e.printStackTrace();
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private static void cleanUP() {
        Display.destroy();
    }


    private static RenderGlobal renderGlobal;
}
