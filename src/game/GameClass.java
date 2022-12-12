package game;

import game.elements.Block;
import game.elements.Bumper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.awt.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.lwjgl.opengl.GL11.*;

public class GameClass {

    public GameClass(RenderGlobal rg, int windowWidth, int windowHeight){
        this.renderGlobal = rg;
        this.width = windowWidth;
        this.height = windowHeight;
        block = new Block[2000];
        blockCount = 0;
        random = new Random(System.currentTimeMillis());
        addBlocks();

        bumper = new Bumper(renderGlobal);
        bumper.setPosX(width/2.0f - (float)bumper.getBumperWidth()/2);
    }


    public void getInput() {
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) && bumper.getPosX() > 0){
            bumper.incrementPosX(-2.5f);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && bumper.getPosX() < width - bumper.getBumperWidth()){
            bumper.incrementPosX(2.5f);
        }
    }

    public void update() {

    }

    public void render() {
        for (int i = 0; i < blockCount; i++) {
            block[i].render(renderGlobal);
        }
        bumper.render();
    }


    private void addBlocks(){
        for (int i = 1; i <= 18; i++) {
            for (int j = 1; j <= 10; j++) {
                block[blockCount] = new Block();
                block[blockCount].setShapeColor(random.nextInt(6) + 1);
                block[blockCount].posX = -48 + i * 55;//48.5, half a pixel diff?
                block[blockCount].posY = height - (30 + j * 25);
                blockCount++;
            }
        }
    }


    private Block block[];
    private int blockCount;

    private RenderGlobal renderGlobal;
    private Random random;
    private Bumper bumper;
    private int width, height;
}
