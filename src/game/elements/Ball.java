package game.elements;

import game.AABB;
import game.RenderGlobal;

import java.awt.*;

public class Ball {

    public Ball(RenderGlobal rg, Bumper b){
        renderGlobal = rg;
        bumper = b;
        ballCircumference = 20.0f;
        ballRadius = ballCircumference/2.0f;
        posX = 500 - ballRadius;
        posY = 200;
        motionX = 0.5f;
        motionY = 0.5f;
        axisAligned = new AABB(posX - ballRadius, posY - ballRadius, posX + ballRadius, posY + ballRadius);
    }


    public void updateOnFrame(Block[] block, int blockCount) {
        posY += motionY;
        posX += motionX;

        axisAligned.setAABB(posX - ballRadius, posY - ballRadius, posX + ballRadius, posY + ballRadius);

        if(posX - ballRadius <= 0){
            motionX = -motionX;
        }
        if(posX + ballRadius >= 1000){
            motionX = -motionX;
        }
        if(posY + ballRadius >= 700){
            motionY = -motionY;
        }
        if(posY - ballRadius <= 0){
            motionY = -motionY;
        }

        for(int i = 0; i < blockCount; i++){
            if(axisAligned.intersects(block[i].getAABB())){
                block[i].setPosX(-100);
                block[i].getAABB().setAABB(0, 0, 0, 0);
                motionY = -motionY;
            }
        }

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

    private float posX, posY;
    private float motionX, motionY;
    private float ballRadius, ballCircumference;
    private RenderGlobal renderGlobal;
    private Bumper bumper;
    private AABB axisAligned;


}
