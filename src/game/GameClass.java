package game;

import game.elements.Ball;
import game.elements.Block;
import game.elements.Bumper;

import org.lwjgl.input.Keyboard;

import java.awt.Color;

import java.util.Random;


public class GameClass {

    public GameClass(RenderGlobal rg, int windowWidth, int windowHeight){
        this.renderGlobal = rg;
        this.width = windowWidth;
        this.height = windowHeight;
        block = new Block[2000];
        blockCount = 0;
        score = 0;
        isGameOver = false;
        random = new Random(System.currentTimeMillis());
        addBlocks();

        bumper = new Bumper(renderGlobal);
        bumper.setPosX(width/2.0f - (float)bumper.getBumperWidth()/2);

        ball = new Ball(renderGlobal, bumper, this);
    }


    public void getInput() {
        if(!isGameOver){
            if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && bumper.getPosX() > 2) {
                bumper.incrementPosX(-4.0f);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && bumper.getPosX() < width - bumper.getBumperWidth() - 4.0f) {
                bumper.incrementPosX(4.0f);
            }
        }else{
            if(Keyboard.isKeyDown(Keyboard.KEY_Y)){
                restartGame();
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_N)){
                System.exit(0);
            }
        }

    }

    private void restartGame() {
        block = new Block[2000];
        blockCount = 0;
        score = 0;
        addBlocks();
        bumper.setPosX(width/2.0f - (float)bumper.getBumperWidth()/2);
        ball.setPosXY(500 - ball.getBallRadius(), 600);
        ball.setMotionXY(1.5f, 1.5f);
        isGameOver = false;
    }


    public void updateOnFrame() {
        ball.updateOnFrame(block, blockCount);
    }

    public void render() {
        rainbow = new Color(Color.HSBtoRGB((System.currentTimeMillis() % 1000L) / 1000.0F, 0.55F, 0.45F), false);

        renderGlobal.drawFilledRectWithColor(0, 0, width, height, rainbow.getRGB());
        renderGlobal.drawFilledRectWithColor(2, 2, width - 4, height - 4, Color.black.getRGB());

        for (int i = 0; i < blockCount; i++) {
            block[i].render(renderGlobal);
        }
        bumper.render();
        ball.render();

        if(isGameOver){
            renderGlobal.drawFilledRectWithColor(300, 250, 400, 200, Color.WHITE.getRGB());
            renderGlobal.drawFilledRectWithColor(305, 255, 390, 190, Color.BLACK.getRGB());
            renderGlobal.drawCenteredFont(500, 280, 0.5f, "GAME OVER!", Color.RED);
            renderGlobal.drawCenteredFont(500, 330, 0.2f, "Final score: " + score, Color.WHITE);
            renderGlobal.drawCenteredFont(500, 370, 0.3f, "Restart? Y/N", Color.RED);

        }else{
            renderGlobal.drawFont(10.0f, 10.0f, 0.3f, "Score: " + score, Color.MAGENTA);
        }
    }


    private void addBlocks(){
        for (int i = 1; i <= 18; i++) {
            for (int j = 1; j <= 10; j++) {
                block[blockCount] = new Block();
                block[blockCount].setShapeColor(random.nextInt(6) + 1);
                block[blockCount].posX = -48 + i * 55;//48.5, half a pixel diff?
                block[blockCount].posY = 30 + j * 25;
                block[blockCount].getAABB().setAABB(-48 + i * 55, 30 + j * 25, -48 + i * 55 + block[blockCount].blockWidth, 30 + j * 25 + block[blockCount].blockHeight);
                blockCount++;
            }
        }
    }


    public void addScore(int amount){
        score += amount;
    }

    public void setGameOver(boolean isItOver){
        isGameOver = isItOver;
    }

    public boolean isGameOver(){
        return isGameOver;
    }

    private Block block[];
    private int blockCount;
    private RenderGlobal renderGlobal;
    private Random random;
    private Bumper bumper;
    private Ball ball;
    private int width, height;
    private int score;
    private boolean isGameOver;
    private Color rainbow;
}
