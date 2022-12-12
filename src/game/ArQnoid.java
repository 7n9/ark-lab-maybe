package game;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;


import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArQnoid {


    public static void main(String[] args) {
        desiredWidth = 1000;
        desiredHeight = 700;
        renderGlobal = new RenderGlobal();
        game = new GameClass(renderGlobal, desiredWidth, desiredHeight);
        initDisplay();
        initGL();
        arqLoop();
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
        game.getInput();
    }

    private static void update() {
        game.update();
    }

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();

        game.render();
        //renderGlobal.drawRectWithColor(450, 300, 100, 100, Color.MAGENTA.getRGB());
        //renderGlobal.drawCircle(100, 100, 30, Color.HSBtoRGB((System.currentTimeMillis() % 10000L)/10000.0f, 0.55f, 0.95f));

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
            Display.setDisplayMode(new DisplayMode(desiredWidth, desiredHeight));
            Display.setResizable(false);
            Display.create();
            Display.setTitle("ArQnoid");
            Display.setVSyncEnabled(true);
        }catch(Exception e){
            //e.printStackTrace();
            Logger.getLogger(ArQnoid.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private static void cleanUP() {
        Display.destroy();
    }


    private static RenderGlobal renderGlobal;
    private static GameClass game;
    private static int desiredWidth, desiredHeight;
}
