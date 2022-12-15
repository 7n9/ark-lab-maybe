package game.elements;

import game.AABB;
import game.GameClass;
import game.RenderGlobal;

import java.awt.*;

public class Ball {

    public Ball(RenderGlobal rg, Bumper b, GameClass gameClass){
        renderGlobal = rg;
        bumper = b;
        this.gameClass = gameClass;
        ballCircumference = 20.0f;
        ballRadius = ballCircumference/2.0f;
        posX = 500 - ballRadius;
        posY = 600;
        motionX = 1.5f;
        motionY = 1.5f;
        axisAligned = new AABB(posX - ballRadius, posY - ballRadius, posX + ballRadius, posY + ballRadius);
    }


    public void updateOnFrame(Block[] block, int blockCount) {
        if(!gameClass.isGameOver()){
            posY += motionY;
            posX += motionX;

            axisAligned.setAABB(posX - ballRadius, posY - ballRadius, posX + ballRadius, posY + ballRadius);

            if (posX - ballRadius <= 2) {
                motionX = -motionX;
            }
            if (posX + ballRadius >= 996) {
                motionX = -motionX;
            }
            if (posY + ballRadius >= 696) {
                gameClass.setGameOver(true);
            }
            if (posY - ballRadius <= 2) {
                motionY = -motionY;
            }
        }

        for(int i = 0; i < blockCount; i++){
            if(axisAligned.intersects(block[i].getAABB())){
                block[i].setPosX(-100);
                block[i].getAABB().setAABB(0, 0, 0, 0);
                motionY = -motionY;
                gameClass.addScore(1);
            }
        }
        //todo collisions still
        if(isBallCollidedWithBumper(bumper)){
            motionY = -motionY;
        }
    }

    public void render(){
        renderGlobal.drawCircle(posX, posY, ballRadius, Color.RED.getRGB());
        renderGlobal.drawCircle(posX, posY, 2, Color.WHITE.getRGB());
    }

    private boolean isBallCollidedWithBumper(Bumper bumper){
        if(posX + ballRadius >= bumper.getPosX() && posX - ballRadius <= bumper.getPosX() + bumper.getBumperWidth() && posY - ballRadius <= bumper.getPosY() + bumper.getBumperHeight() && posY + ballRadius >= bumper.getPosY()){
            return true;
        }else{
            return false;
        }
    }

    public void setPosXY(float posX, float posY){
        this.posX = posX;
        this.posY = posY;
    }

    public void setMotionXY(float motionX, float motionY){
        this.motionX = motionX;
        this.motionY = motionY;
    }

    public float getBallRadius(){
        return ballRadius;
    }

    private float posX, posY;
    private float motionX, motionY;
    private float ballRadius, ballCircumference;
    private RenderGlobal renderGlobal;
    private Bumper bumper;
    private AABB axisAligned;
    private GameClass gameClass;


}
